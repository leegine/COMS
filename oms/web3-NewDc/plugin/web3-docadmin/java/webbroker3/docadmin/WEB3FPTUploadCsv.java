head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����@@��t�{���A�b�v���[�hCSV(WEB3FPTUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 ���g (���u) �V�K�쐬 ���f�� No.012,No.014,No.015
Revision History : 2007/12/21 �Ӑ� (���u) �d�l�ύX ���f�� No.020
Revision History : 2008/01/28 ���g (���u) �d�l�ύX ���f�� No.027,No.030,No.032
Revision History : 2008/02/13 ���g (���u) �����̖��002
*/

package webbroker3.docadmin;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CategCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.docadmin.define.WEB3FPTStatusDivDef;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����@@��t�{���A�b�v���[�hCSV)<BR>
 * �����@@��t�{���A�b�v���[�hCSV<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3FPTUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FPTUploadCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public String branchCodeLabel = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public String accountCodeLabel = "�ڋq�R�[�h";

    /**
     * (���ʌ�t�����x��)<BR>
     * ���ʌ�t�����x��<BR>
     */
    public String deliveryDateLabel = "���ʌ�t��";

    /**
     * (�d�q�������R�[�h���x��)<BR>
     * �d�q�������R�[�h���x��<BR>
     */
    public String batoProductCodeLabel = "�d�q�������R�[�h";

    /**
     * (�A�b�v���[�h���~)<BR>
     * �A�b�v���[�h���~�p�R�����g<BR>
     */
    public String uploadCancel = "���~";

    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �A�b�v���[�h�t�@@�C���h�c_�����@@��t�{���A�b�v���[�h<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     */
    public String uploadFileId = "�����@@��t�{���A�b�v���[�h";

    /**
     * (���E�s��)<BR>
     * �A�b�v���[�h�\���E�s���B<BR>
     */
    private long limitLines = 500;

    /**
     * (�����@@��t�{���A�b�v���[�hCSV)<BR>
     * �|this.create�J�����w�b�_()���R�[������B<BR>
     * @@roseuid 47380C5D004B
     */
    public WEB3FPTUploadCsv()
    {
        final String STR_METHOD_NAME = "WEB3FPTUploadCsv()";
        log.entering(STR_METHOD_NAME);

        //�|this.create�J�����w�b�_()���R�[������B
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�����@@��t�{���A�b�v���[�hCSV)<BR>
     * �A�b�v���[�h�f�[�^���f���𐶐�����B<BR>
     * <BR>
     * �����~�̂Ƃ��̂ݎg�p�B<BR>
     * [�R���X�g���N�^�̈���]<BR>
     * �A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c<BR>
     * @@param l_lngUploadFileID - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 47380CD90054
     */
    public WEB3FPTUploadCsv(long l_lngUploadFileID)
    {
        final String STR_METHOD_NAME = "WEB3FPTUploadCsv(long)";
        log.entering(STR_METHOD_NAME);

        //�A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c
        this.administratorUploadId = l_lngUploadFileID;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (check���׍s��)<BR>
     * ���׍s���̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j �V�X�e���v���t�@@�����X���l���擾����B<BR>
     * <BR>
     * [�����@@����.get�V�X�e���v���t�@@�����X()�Ɏw�肷�����]<BR>
     * "UL_DOCADMIN_DEVMANAGE_COUNT"<BR>
     * <BR>
     * �Q�j ���׍s�����`�F�b�N����B<BR>
     * �@@�Q-�P�j �����@@����.get�V�X�e���v���t�@@�����X() != null �̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�擾�����i���l�ϊ��ς́j�V�X�e���v���t�@@�����X�̒l < <BR>
     * �@@�@@�@@�@@�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   �Q-�Q�j �����@@����.get�V�X�e���v���t�@@�����X() == null �̏ꍇ<BR>
     *   �@@�@@�@@this.���E�s�� < ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length�̏ꍇ�A<BR>
     *   �@@�@@�@@��O���X���[����B<BR>
     * <BR>
     * ����O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR>
     * �w���R�[�h�������������E�l�𒴂��Ă��܂��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02418�j<BR>
     * @@param l_request - (l_request)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4756315601AE
     */
    public void checkDetailLines(WEB3AdminFPTUploadConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "checkDetailLines(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //�P�j �V�X�e���v���t�@@�����X���l���擾����B
        //[�����@@����.get�V�X�e���v���t�@@�����X()�Ɏw�肷�����]
        //"UL_DOCADMIN_DEVMANAGE_COUNT"
        String l_strValue =
            WEB3AdminFPTCommon.getSystemPreferences(
                WEB3SystemPreferencesNameDef.UL_DOCADMIN_DEVMANAGE_COUNT);

        //�Q�j ���׍s�����`�F�b�N����B
        //�Q-�P�j �����@@����.get�V�X�e���v���t�@@�����X() != null �̏ꍇ
        //�@@�擾�����i���l�ϊ��ς́j�V�X�e���v���t�@@�����X�̒l < ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length �̏ꍇ�A��O���X���[����B
        //�w���R�[�h�������������E�l�𒴂��Ă��܂��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02418�j
        if (l_strValue != null)
        {
            int l_intValue = Integer.parseInt(l_strValue);
            if (l_intValue < l_request.uploadFile.length)
            {
                log.debug("���R�[�h�������������E�l�𒴂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���R�[�h�������������E�l�𒴂��Ă��܂��B");
            }
        }
        else
        {
            //�Q-�Q�j �����@@����.get�V�X�e���v���t�@@�����X() == null �̏ꍇ
            //this.���E�s�� < ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��.length�̏ꍇ�A��O���X���[����B
            //�w���R�[�h�������������E�l�𒴂��Ă��܂��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02418�j
            if (this.limitLines < l_request.uploadFile.length)
            {
                log.debug("���R�[�h�������������E�l�𒴂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���R�[�h�������������E�l�𒴂��Ă��܂��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV�d�q�������R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV.���ʌ�t�����x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@"YYYYMMDD"<BR>
     * <BR>
     * <BR>
     * @@roseuid 47380418007E
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 4;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //�|�@@index = 0
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV.���X�R�[�h���x��
        //�J�����ԍ��F 0
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            branchCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 1
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV�ڋq�R�[�h���x��
        //�J�����ԍ��F 1
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            accountCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 2
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV�d�q�������R�[�h���x��
        //�J�����ԍ��F 2
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            batoProductCodeLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 3
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�����@@��t�{���A�b�v���[�hCSV.���ʌ�t�����x��
        //�J�����ԍ��F 3
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t
        //���t�t�H�[�}�b�g�F�@@"YYYYMMDD"
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            deliveryDateLabel,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�A�b�v���[�h�t�@@�C��ID)<BR>
     * �����@@��t�{���A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     * @@return String
     * @@roseuid 473805A70021
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = "getUploadFileId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.uploadFileId;
    }

    /**
     * (get�ڋq)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B<BR>
     * <BR>
     * [get�ڋq()�Ɏw�肷�����]<BR>
     * �،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * ���X�R�[�h�F�@@this.get���X�R�[�h(�s�ԍ�)<BR>
     * �����R�[�h�F�@@this.get�ڋq�R�[�h(�s�ԍ�)<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return WEB3GentradeMainAccount
     * @@throws WEB3BaseException
     * @@roseuid 473951A9013B
     */
    public WEB3GentradeMainAccount getMainAccount(
        int l_intLineNo, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(int, String)";
        log.entering(STR_METHOD_NAME);

        //�A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //[get�ڋq()�Ɏw�肷�����]
        //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h
        //���X�R�[�h�F�@@this.get���X�R�[�h(�s�ԍ�)
        //�����R�[�h�F�@@this.get�ڋq�R�[�h(�s�ԍ�)
        WEB3GentradeMainAccount l_mainAccount =
            l_accountManager.getMainAccount(
                l_strInstitutionCode,
                this.getBranchCode(l_intLineNo),
                this.getAccountCode(l_intLineNo));

        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 473804060319
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.accountCodeLabel);

        //this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B
        String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get���ʌ�t��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏��ʌ�t�����擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂď��ʌ�t�����擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.���ʌ�t�����x��)�̖߂�l�B<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 473804130207
     */
    public String getDeliveryDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getDeliveryDate(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.���ʌ�t�����x��)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.deliveryDateLabel);

        //this.get���ڒl()�ɂď��ʌ�t�����擾���ԋp����B
        Date l_datDeliveryDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        String l_strDeliveryDate = WEB3DateUtility.formatDate(
            l_datDeliveryDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveryDate;
    }

    /**
     * (get�d�q�������R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓d�q�������R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēd�q�������R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.�d�q�������R�[�h���x��)�̖߂�l�B<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     */
    public String getBatoProductCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBatoProductCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.�d�q�������R�[�h���x��)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.batoProductCodeLabel);

        //this.get���ڒl()�ɂēd�q�������R�[�h���擾���ԋp����B
        String l_strbatoProductCode = (String)this.getValue(
            l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strbatoProductCode;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 4738040501E2
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�����@@��t�{���A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(this.branchCodeLabel);

        //this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B
        String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���̑� ��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 473804140330
     */
    public ProductTypeEnum getProductType()
    {
        final String STR_METHOD_NAME = "getProductType()";
        log.entering(STR_METHOD_NAME);

        //ProductTypeEnum.���̑� ��ԋp����
        log.exiting(STR_METHOD_NAME);
        return ProductTypeEnum.OTHER;
    }

    /**
     * (update�A�b�v���[�h���~)<BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h���~���X�V����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = System.currentTimeMillis()<BR>
     * �@@�A�b�v���[�h���� = 0<BR>
     * �@@���l�P = this.�A�b�v���[�h���~<BR>
     * <BR>
     * ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47380D60036A
     */
    public void updateUploadCancel() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "updateUploadCancel()";
        log.entering(STR_METHOD_NAME);

        //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
        //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
        AdministratorUploadRow l_administratorUploadRow;
        try
        {
            l_administratorUploadRow =
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());

            if (l_administratorUploadRow == null)
            {
                //���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
                log.exiting(STR_METHOD_NAME);
                return;
            }

            AdministratorUploadParams l_administratorUploadParams =
                new AdministratorUploadParams(l_administratorUploadRow);

            //�A�b�v���[�h�I������ = System.currentTimeMillis()
            l_administratorUploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));

            //�A�b�v���[�h���� = 0
            l_administratorUploadParams.setUploadCount(0);

            //���l�P = this.�A�b�v���[�h���~
            l_administratorUploadParams.setNote1(this.uploadCancel);

            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            Processors.getDefaultProcessor().doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�d���ڋq)<BR>
     * �����Ώۃ��R�[�h�̏��ʌ�t�Ǘ��e�[�u���ւ̓o�^�󋵂��`�F�b�N����B<BR>
     * �o�^�A�b�v���[�h�����i�����敪�F0�j�̏ꍇ�A�o�^�ς݂ł���Η�O���X���[����B<BR>
     * �폜�A�b�v���[�h�����i�����敪�F1�j�̏ꍇ�A���o�^�ł���Η�O���X���[����B<BR>
     * <BR>
     * �܂��A�����Ώۃ��R�[�h���A�b�v���[�h�t�@@�C�����ŏd�����Ă���ꍇ��<BR>
     * ��O���X���[����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���ʌ�t�Ǘ��e�[�u���̃f�[�^�o�^�󋵂��`�F�b�N����B<BR>
     * <BR>
     * �@@�P-�P�j�@@������������쐬����B<BR>
     * <BR>
     * �@@�@@�P-�P-�P�j�@@��̕�����𐶐�����B<BR>
     * <BR>
     * �@@�@@�P-�P-�Q�j�@@�،���ЃR�[�h���P-�P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@"institution_code = ?"<BR>
     * <BR>
     * �@@�@@�P-�P-�R�j�@@���X�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "and branch_code = ? "<BR>
     * <BR>
     * �@@�@@�P-�P-�S�j�@@�ڋq�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "and account_code like ? %"<BR>
     * <BR>
     * �@@�@@�P-�P-�T�j�@@���ʋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "and document_div = ? "<BR>
     * <BR>
     * �@@�@@�P-�P-�U�j�@@�����R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "and product_code = ? "<BR>
     * <BR>
     * �@@�@@�P-�P-�V�j�@@����:�������.���ʌ�t������������������ɒǉ�����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "and delivery_date�@@=�@@?"<BR>
     * <BR>
     * �@@�@@�P-�P-�W�j�@@���ʎ�ރR�[�h����������������ɒǉ�����<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�������������� += "document_category�@@=�@@?"<BR>
     * <BR>
     * �@@�P-�Q�j�@@���������R���e�i���쐬����B<BR>
     * <BR>
     * �@@�@@�P-�Q-�P�j�@@���ArrayList�𐶐�����B<BR>
     * <BR>
     * �@@�@@�P-�Q-�Q�j�@@�،���ЃR�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@����.�،���ЃR�[�h ���P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�P-�Q-�R�j�@@���X�R�[�h <BR>
     * <BR>
     * �@@�@@�@@�@@�@@this.get���X�R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get���X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�@@�P-�Q-�S�j�@@�ڋq�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@this.get�ڋq�R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get�ڋq�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�@@�P-�Q-�T�j�@@���ʋ敪<BR>
     * <BR>
     * �@@�@@�@@�@@�@@'010' �� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�P-�Q-�U�j�@@�����R�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@this.get�d�q�������R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get�d�q�������R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�@@�P-�Q-�V�j�@@���ʌ�t��<BR>
     * <BR>
     * �@@�@@�@@�@@�@@this.get���ʌ�t��()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get���ʌ�t��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�@@�P-�Q-�W�j ���ʎ�ރR�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@this.get�d�q�������R�[�h()�̖߂�l��3���� �P-�Q-�P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[get�d�q�������R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�@@�P-�Q-�X�j�@@�P-�Q-�P�j��List��Object�z��ɕϊ�����B<BR>
     * <BR>
     * <BR>
     * �@@�P-�R�j�@@���ʌ�t�Ǘ��e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�P-�R-�P�j�@@���ʌ�t�Ǘ��I�u�W�F�N�g�𐶐�����<BR>
     * <BR>
     * �@@�@@�P-�R-�Q�j�@@�������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get���ʌ�t�Ǘ�()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�@@�@@�@@����������F �P-�P�j �ō쐬��������������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����f�[�^�R���e�i�F �P-�Q-�W�j �ō쐬�����f�[�^�R���e�i<BR>
     * <BR>
     * �@@�P-�S�j�@@����.�����敪 == "0" ���� �P-�R-�Q�j �̖߂�l���� > 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�w��̌ڋq�͊��ɓo�^����Ă��܂��B�v<BR>
     * �@@�@@�@@�@@�@@�iBUSINESS_ERROR_02950�j��O���X���[����B<BR>
     * <BR>
     * �@@�P-�T�j�@@����.�����敪 == "1" ���� �P-�R-�Q�j �̖߂�l���� == 0 �̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���ʌ�t�Ǘ��s���擾�ł��Ȃ��B�v<BR>
     * �@@�@@�@@�@@�@@�iBUSINESS_ERROR_02952�j��O���X���[����B<BR>
     * <BR>
     * <BR>
     * �Q�j�@@�A�b�v���[�h�t�@@�C�����ɏd�����Ȃ����`�F�b�N����B<BR>
     * <BR>
     * �@@�Q-�P�j �w��s�����O�̖��׍s���擾����B�i<BR>
     * �@@�@@�@@�i�ǉ��ϖ��׍s����Loop�j<BR>
     * <BR>
     * �@@�@@�@@�@@�擾�ς݂̖��׍s�S���ڂƎw��s�ԍ����O�̖��׍s�̑S���ڂ��r����B<BR>
     * �@@�@@�@@�@@�S���ڂ����S�Ɉ�v����s�����݂���ꍇ�́A�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * <BR>
     * �@@�@@�@@�@@����O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR>
     * �@@�@@�@@�@@�w�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�x<BR>
     * �@@�@@�@@�@@�i�G���[�R�[�h�FBUSINESS_ERROR_00517�j<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProcedureDiv - (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�o�^�A�b�v���[�h����<BR>
     * 1�F�폜�A�b�v���[�h����<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473AAADA0144
     */
    public void validateDuplicateAccount(
        int l_intLineNo,
        String l_strInstitutionCode,
        String l_strProcedureDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDuplicateAccount(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j ���ʌ�t�Ǘ��e�[�u���̃f�[�^�o�^�󋵂��`�F�b�N����B
        //�P-�P�j ������������쐬����B
        //�P-�P-�P�j ��̕�����𐶐�����B
        StringBuffer l_sbWhere = new StringBuffer();

        //�P-�P-�Q�j �،���ЃR�[�h���P-�P�j�̕�����ɒǉ�����B
        // "institution_code = ?"
        l_sbWhere.append(" institution_code = ? ");

        //�P-�P-�R�j ���X�R�[�h����������������ɒǉ�����B
        //�������������� += "and branch_code = ? "
        l_sbWhere.append(" and branch_code = ? ");

        //�P-�P-�S�j �ڋq�R�[�h����������������ɒǉ�����B
        //�������������� += "and account_code like ? %"
        l_sbWhere.append(" and account_code like ? || '%' ");

        //�P-�P-�T�j ���ʋ敪����������������ɒǉ�����B
        //�������������� += "and document_div = ? "
        l_sbWhere.append(" and document_div = ? ");

        //�P-�P-�U�j�@@�����R�[�h����������������ɒǉ�����B
        //�������������� += "and product_code = ? "
        l_sbWhere.append(" and product_code = ? ");

        //�P-�P-�V�j�@@����:�������.���ʌ�t������������������ɒǉ�����
        // �������������� += "and delivery_date = ?"
        l_sbWhere.append(" and delivery_date = ? ");

        //�P-�P-�W�j�@@���ʎ�ރR�[�h����������������ɒǉ�����
        //�������������� += "document_category = ?"
        l_sbWhere.append(" and document_category = ? ");

        //�P-�Q�j ���������R���e�i���쐬����B
        //�P-�Q-�P�j ���ArrayList�𐶐�����B
        List l_lisWheres = new ArrayList();

        //�P-�Q-�Q�j �،���ЃR�[�h
        //����.�،���ЃR�[�h ���P-�Q-�P�j��List�ɒǉ�����B
        l_lisWheres.add(l_strInstitutionCode);

        //�P-�Q-�R�j ���X�R�[�h
        //this.get���X�R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B
        //[get���X�R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        l_lisWheres.add(this.getBranchCode(l_intLineNo));

        //�P-�Q-�S�j �ڋq�R�[�h
        //this.get�ڋq�R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B
        //[get�ڋq�R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        l_lisWheres.add(this.getAccountCode(l_intLineNo));

        //�P-�Q-�T�j ���ʋ敪
        //'010' �� �P-�Q-�P�j��List�ɒǉ�����B
        l_lisWheres.add(WEB3CategCodeDef.DOCUMENT_DELIVERY);

        //�P-�Q-�U�j �����R�[�h
        //this.get�d�q�������R�[�h()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B
        String l_strBatoProductCode = this.getBatoProductCode(l_intLineNo);
        l_lisWheres.add(l_strBatoProductCode);

        //�P-�Q-�V�j ���ʌ�t��
        //this.get���ʌ�t��()�̖߂�l�� �P-�Q-�P�j��List�ɒǉ�����B
        //[get���ʌ�t��()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        l_lisWheres.add(this.getDeliveryDate(l_intLineNo));

        //�P-�Q-�W�j ���ʎ�ރR�[�h
        //this.get�d�q�������R�[�h()�̖߂�l��3���� �P-�Q-�P�j��List�ɒǉ�����B
        //[get�d�q�������R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        l_lisWheres.add(l_strBatoProductCode.subSequence(0, 3));

        //�P-�Q-�X�j �P-�Q-�P�j��List��Object�z��ɕϊ�����B
        Object[] l_wheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_wheres);

        //�P-�R-�Q�j �������s���B
        //[get���ʌ�t�Ǘ�()�Ɏw�肷�����]
        //����������F �P-�P�j �ō쐬��������������
        //�����f�[�^�R���e�i�F �P-�Q-�W�j �ō쐬�����f�[�^�R���e�i
        WEB3AdminFPTDocDeliveryManagement l_adminFPTDocDeliveryManagement =
            new WEB3AdminFPTDocDeliveryManagement();

        //�P-�R�j ���ʌ�t�Ǘ��e�[�u�����烌�R�[�h���擾����B
        //�P-�R-�P�j ���ʌ�t�Ǘ��I�u�W�F�N�g�𐶐�����
        List l_lisDocDivManagements =
            l_adminFPTDocDeliveryManagement.getDocDivManagement(
                l_sbWhere.toString(),
                l_wheres);

        //�P-�S�j ����.�����敪 == "0" ���� �P-�R-�Q�j �̖߂�l���� > 0 �̏ꍇ�A
        //�u�w��̌ڋq�͊��ɓo�^����Ă��܂��B�v
        //�iBUSINESS_ERROR_02950�j��O���X���[����B
        if (WEB3FPTStatusDivDef.LOGIN.equals(l_strProcedureDiv)
            && l_lisDocDivManagements.size() > 0)
        {
            log.debug("�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02950,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��̌ڋq�͊��ɓo�^����Ă��܂��B");
        }

        //�P-�T�j ����.�����敪 == "1" ���� �P-�R-�Q�j �̖߂�l���� == 0 �̏ꍇ�A
        //�u���ʌ�t�Ǘ��s���擾�ł��Ȃ��B�v
        //�iBUSINESS_ERROR_02952�j��O���X���[����B
        if (WEB3FPTStatusDivDef.DELETE.equals(l_strProcedureDiv)
            && l_lisDocDivManagements.size() == 0)
        {
            log.debug("���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02952,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�Ǘ��s���擾�ł��Ȃ��B");
        }

        //�Q�j �A�b�v���[�h�t�@@�C�����ɏd�����Ȃ����`�F�b�N����B
        // �Q-�P�j �w��s�����O�̖��׍s���擾����B�i�ǉ��ϖ��׍s����Loop�j
        //�擾�ς݂̖��׍s�S���ڂƎw��s�ԍ����O�̖��׍s�̑S���ڂ��r����B
        //�S���ڂ����S�Ɉ�v����s�����݂���ꍇ�́A�ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        String l_strBatoProductCodeByLineNo = this.getBatoProductCode(l_intLineNo);
        String l_strDeliveryDate = this.getDeliveryDate(l_intLineNo);

        //����O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B
        // �w�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�x�i�G���[�R�[�h�FBUSINESS_ERROR_00517�j
        for (int i = 0; i < l_intLineNo; i++)
        {
            String l_strBranchCodePrevious = this.getBranchCode(i);
            String l_strAccountCodePrevious = this.getAccountCode(i);
            String l_strBatoProductCodePrevious = this.getBatoProductCode(i);
            String l_strDeliveryDatePrevious = this.getDeliveryDate(i);

            if (l_strBranchCode.equals(l_strBranchCodePrevious)
                && l_strAccountCode.equals(l_strAccountCodePrevious)
                && l_strBatoProductCodeByLineNo.equals(l_strBatoProductCodePrevious)
                && l_strDeliveryDate.equals(l_strDeliveryDatePrevious))
            {
                log.debug("�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * �@@�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�ڋq�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * �@@�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * �@@�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * �@@�Q�|�S�j�@@this.get�ڋq()���R�[������B�ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR_02414�j��O���X���[����B<BR>
     * <BR>
     * �@@�@@[get�ڋq()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l<BR>
     * <BR>
     * �R�j ���ʌ�t���̃`�F�b�N<BR>
     * �@@get���ʌ�t��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���ʌ�t��()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@���ʌ�t�����擾�ł��Ȃ��ꍇ�́A�u���ʌ�t�����s���ł��B�v<BR>
     * �@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02961<BR>
     * <BR>
     * �S�j ���X�����`�F�b�N���s���B<BR>
     * �@@����.�Ǘ���.validate���X����()���R�[������B<BR>
     * <BR>
     * �@@�@@[validate���X����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h<BR>
     * <BR>
     * <BR>
     * �T�j �d�q�������R�[�h�̃`�F�b�N<BR>
     * �@@�@@get�d�q�������R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@[get�d�q�������R�[�h()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�T�|�P�j�@@�d�q�������R�[�h���擾�ł��Ȃ��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02995<BR>
     * �@@�T�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02995<BR>
     * �@@�T�|�R�j�@@��������7byte�łȂ��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02995<BR>
     * <BR>
     * <BR>
     * �U�j�@@�d�q�������R�[�h���݃`�F�b�N���s���B<BR>
     * �@@�U-�P�j �d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[�d�q�������R�[�h�Ǘ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@�@@�@@���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h<BR>
     * �@@�@@�@@���ʋ敪�F "010"<BR>
     * �@@�@@�@@�d�q�������R�[�h�F �T�j �Ŏ擾�����d�q�������R�[�h<BR>
     * <BR>
     * �@@�U-�Q�j�@@�d�q�������R�[�h�Ǘ�#is�d�q�������R�[�h()���R�[������B<BR>
     * �@@�@@�@@�@@�@@false���ԋp���ꂽ�ꍇ�A�u�d�q�������R�[�h���s���ł��B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02995<BR>
     * <BR>
     * �V�j�@@���ʎ�ރR�[�h���݃`�F�b�N���s���B<BR>
     * �@@�V-�P�j�@@���ʎ�ފǗ��I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[���ʎ�ފǗ�()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l<BR>
     * �@@�@@�@@���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h��v�f�Ƃ��钷���P��String�z��<BR>
     * �@@�@@�@@���ʎ�ރR�[�h�F �T�j �Ŏ擾�����d�q�������R�[�h�̍��R��<BR>
     * <BR>
     * �@@�V-�Q�j�@@���ʎ�ފǗ�#is���ʎ�ރR�[�h()���R�[������B<BR>
     * �@@�@@�@@�@@�@@false���ԋp���ꂽ�ꍇ�A�u�w�肵���d�q�������R�[�h������������܂��� �i��3�����s���j�B�v<BR>
     * �@@�@@�@@�@@�@@�@@�iBUSINESS_ERROR�j��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@class�@@�@@:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@tag�@@�@@�@@:�@@BUSINESS_ERROR_02996<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_administrator - (�Ǘ���)<BR>
     * �Ǘ��҃I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 473810A2000F
     */
    public void validateDetailLine(
        int l_intLineNumber,
        WEB3Administrator l_administrator) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDetailLine(int, WEB3Administrator)";
        log.entering(STR_METHOD_NAME);

        if (l_administrator == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }
        //�P�j�@@���X�R�[�h�̃`�F�b�N
        //get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get���X�R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);

        //�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A
        //�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v�iBUSINESS_ERROR_02414�j��O���X���[����B
        if (l_strBranchCode == null)
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v
        //�iBUSINESS_ERROR_02414�j��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v
        // �iBUSINESS_ERROR_02414�j��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N
        //get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get�ڋq�R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);

        //�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A
        //�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v�iBUSINESS_ERROR_02414�j��O���X���[����B
        if (l_strAccountCode == null)
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v
        //�iBUSINESS_ERROR_02414�j��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v
        //�iBUSINESS_ERROR_02414�j��O���X���[����B
        if (WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�Q�|�S�j�@@this.get�ڋq()���R�[������B�ڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�́A
        //�u���X�R�[�h�E�ڋq�R�[�h���s���ł��B�v�iBUSINESS_ERROR_02414�j��O���X���[����B
        //[get�ڋq()�Ɏw�肷�����]
        //�s�ԍ��F�@@�s�ԍ�
        //�،���ЃR�[�h�F�@@����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        try
        {
            this.getMainAccount(l_intLineNumber, l_administrator.getInstitutionCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("���X�R�[�h�E�ڋq�R�[�h���s���ł��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�R�j ���ʌ�t���̃`�F�b�N
        //get���ʌ�t��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get���ʌ�t��()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        String l_strDeliveryDate = this.getDeliveryDate(l_intLineNumber);

        //�R�|�P�j�@@���ʌ�t�����擾�ł��Ȃ��ꍇ�́A�u���ʌ�t�����s���ł��B�v�iBUSINESS_ERROR_02961�j��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strDeliveryDate))
        {
            log.debug("���ʌ�t�����s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02961,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ʌ�t�����s���ł��B");
        }

        //�S�j ���X�����`�F�b�N���s���B
        //����.�Ǘ���.validate���X����()���R�[������B
        //[validate���X����()�Ɏw�肷�����]
        //���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h
        l_administrator.validateBranchPermission(l_strBranchCode);

        //�T�j �d�q�������R�[�h�̃`�F�b�N
        //get�d�q�������R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        //[get�d�q�������R�[�h()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        String l_strBatoProductCode = this.getBatoProductCode(l_intLineNumber);

        // �T�|�P�j�@@�d�q�������R�[�h���擾�ł��Ȃ��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v
        if (WEB3StringTypeUtility.isEmpty(l_strBatoProductCode))
        {
            log.debug("�d�q�������R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h���s���ł��B");
        }

        //�T�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v
        if (!WEB3StringTypeUtility.isDigit(l_strBatoProductCode))
        {
            log.debug("�d�q�������R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h���s���ł��B");
        }

        //�T�|�R�j�@@��������7byte�łȂ��ꍇ�́A�u�d�q�������R�[�h���s���ł��B�v
        if (WEB3StringTypeUtility.getByteLength(l_strBatoProductCode) != 7)
        {
            log.debug("�d�q�������R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h���s���ł��B");
        }

        //�U�j �d�q�������R�[�h���݃`�F�b�N���s���B
        //�U-�P�j �d�q�������R�[�h�Ǘ��I�u�W�F�N�g�𐶐�����B
        //[�d�q�������R�[�h�Ǘ�()�Ɏw�肷�����]
        //�،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h
        //���ʋ敪�F "010"
        //�d�q�������R�[�h�F �T�j �Ŏ擾�����d�q�������R�[�h
        WEB3AdminFPTBatoProductCodeManagement l_batoProductCodeManagement =
            new WEB3AdminFPTBatoProductCodeManagement(
                l_administrator.getInstitutionCode(),
                l_strBranchCode,
                WEB3CategCodeDef.DOCUMENT_DELIVERY,
                l_strBatoProductCode);

        //�U-�Q�j �d�q�������R�[�h�Ǘ�#is�d�q�������R�[�h()���R�[������B
        //false���ԋp���ꂽ�ꍇ�A�u�d�q�������R�[�h���s���ł��B�v
        if (!l_batoProductCodeManagement.isBatoProductCode())
        {
            log.debug("�d�q�������R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02995,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�d�q�������R�[�h���s���ł��B");
        }

        //�V�j ���ʎ�ރR�[�h���݃`�F�b�N���s���B
        //�V-�P�j ���ʎ�ފǗ��I�u�W�F�N�g�𐶐�����B
        //[���ʎ�ފǗ�()�Ɏw�肷�����]
        //�،���ЃR�[�h�F ����.�Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F �P�j �Ŏ擾�������X�R�[�h��v�f�Ƃ��钷���P��String�z��
        //���ʎ�ރR�[�h�F �T�j �Ŏ擾�����d�q�������R�[�h�̍��R��
        String[] l_strBranchCodes = new String[1];
        l_strBranchCodes[0] = l_strBranchCode;
        WEB3AdminFPTDocCategoryManagement l_docCategoryManagement =
            new WEB3AdminFPTDocCategoryManagement(
                l_administrator.getInstitutionCode(),
                l_strBranchCodes,
                l_strBatoProductCode.substring(0, 3));

        //�V-�Q�j ���ʎ�ފǗ�#is���ʎ�ރR�[�h()���R�[������B
        //false���ԋp���ꂽ�ꍇ�A�u���ʎ�ރR�[�h���s���ł��B�v
        if (!l_docCategoryManagement.isDocumentCategory())
        {
            log.debug("�w�肵���d�q�������R�[�h������������܂��� �i��3�����s���j�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02996,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w�肵���d�q�������R�[�h������������܂��� �i��3�����s���j�B");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
