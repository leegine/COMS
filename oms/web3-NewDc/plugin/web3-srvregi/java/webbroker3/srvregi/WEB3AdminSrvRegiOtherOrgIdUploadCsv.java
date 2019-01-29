head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���A�gID�Ɖﱯ��۰��CSV(WEB3AdminSrvRegiOtherOrgIdUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/10 ���g (���u) �V�K�쐬 ���f��337,349,351,352
*/

package webbroker3.srvregi;

import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;


/**
 * (�O���A�gID�Ɖﱯ��۰��CSV)<BR>
 * �O���A�gID�Ɖﱯ��۰��CSV<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadCsv extends WEB3GentradeCsvUploadDataModel
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiOtherOrgIdUploadCsv.class);

    /**
     * (�ʔԃ��x��)<BR>
     * �萔��`�v���p�e�B�@@"�ʔ�"<BR>
     */
    public static  String SEQUENCE_NUMBER_LABEL = "�ʔ�";

    /**
     * (ID���x��)<BR>
     * �萔��`�v���p�e�B�@@"ID"<BR>
     */
    public static  String ID_LABEL = "ID";

    /**
     * (�p�X���[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�p�X���[�h�h<BR>
     */
    public static  String PASSWORD_LABEL = "�p�X���[�h" ;

    /**
     * (�X�e�[�^�X���x��)<BR>
     * �萔��`�v���p�e�B�@@"�X�e�[�^�X"<BR>
     */
    public static  String STATUS_LABEL = "�X�e�[�^�X";

    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�،���ЃR�[�h�h<BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "�،���ЃR�[�h";

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h<BR>
     */
    public static  String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�����R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����R�[�h�h<BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "�����R�[�h";

    /**
     * (�K�p����From���x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p����From"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "�K�p����From";

    /**
     * (�K�p����To���x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p����To"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "�K�p����To";

    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �萔��`�v���p�e�B�@@"�O���A�gID�Ɖﱯ��۰��"<BR>
     */
    public static  String UPLOAD_FILE_ID = "�O���A�gID�Ɖﱯ��۰��";

    /**
     * (�A�b�v���[�h�敪_�V�K�o�^)<BR>
     * �萔��`�v���p�e�B�@@"0"<BR>
     */
    private static  String UPLOAD_DIV_NEW_REGIST = "0";

    /**
     * (�A�b�v���[�h�敪_�ύX�o�^)<BR>
     * �萔��`�v���p�e�B�@@"1"<BR>
     */
    private static  String UPLOAD_DIV_CHANGE_REGIST = "1";

    /**
     * (�A�b�v���[�h_�V�K�o�^_���ڐ�)<BR>
     * �萔��`�v���p�e�B�@@4<BR>
     */
    private int uploadNewRegistItemCount = 4;

    /**
     * (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     */
    public String uploadDiv;

    /**
     * @@roseuid 47D1112C01B7
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCsv()
    {

    }

    /**
     * (�O���A�gID�Ɖﱯ��۰��CSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �|�����̃A�b�v���[�hID�iLong�j��long�^�ɕϊ����ăv���p�e�B�ɃZ�b�g����B<BR>
     * �@@�i�����̃A�b�v���[�hID�iLong�j��null�̏ꍇ�����Ȃ��j<BR>
     * @@param l_uploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 47BE50DF030B
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCsv(Long l_uploadId)
    {
        //�|�����̃A�b�v���[�hID�iLong�j��long�^�ɕϊ����ăv���p�e�B�ɃZ�b�g����B
        //�i�����̃A�b�v���[�hID�iLong�j��null�̏ꍇ�����Ȃ��j
        if (l_uploadId != null)
        {
            this.administratorUploadId = l_uploadId.longValue();
        }
    }

    /**
     * (get�A�b�v���[�h�t�@@�C��ID)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * @@return String
     * @@roseuid 473805A70021
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = "getUploadFileId()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return UPLOAD_FILE_ID;
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
     * (create�J�����w�b�_)<BR>
     * �A�b�v���[�h�敪�ɂ��J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * 1) this.is�A�b�v���[�h�V�K�o�^()�@@���@@true�@@�̏ꍇ�A<BR>
     * �@@�@@create�J�����w�b�__�V�K�o�^()���R�[�����A�o�^�p�̃J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * 2) this.is�A�b�v���[�h�V�K�o�^()�@@���@@false�@@�̏ꍇ�A<BR>
     * �@@�@@create�J�����w�b�__�ύX�o�^()���R�[�����A�ύX�p�̃J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * @@roseuid 47BCFD4602EE
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        //1) this.is�A�b�v���[�h�V�K�o�^()�@@���@@true�@@�̏ꍇ�A
        if (this.isUploadNewRegist())
        {
            //create�J�����w�b�__�V�K�o�^()���R�[�����A�o�^�p�̃J�����w�b�_���Z�b�g����B
            this.createColumnHeaderNewRegist();
        }
        else
        {
            //2) this.is�A�b�v���[�h�V�K�o�^()�@@���@@false�@@�̏ꍇ�A
            //create�J�����w�b�__�ύX�o�^()���R�[�����A�ύX�p�̃J�����w�b�_���Z�b�g����B
            this.createColumnHeaderChangeRegist();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�__�V�K�o�^)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * �@@�@@set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(long)<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�p�X���[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BE1DA300DE
     */
    private void createColumnHeaderNewRegist()
    {
        final String STR_METHOD_NAME = "createColumnHeaderNewRegist()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 4;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //�|�@@index = 0
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��
        //�J�����ԍ��F 0
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(long)
        //���t�t�H�[�}�b�g�F�@@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            SEQUENCE_NUMBER_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);

        //�|�@@index = 1
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��
        //�J�����ԍ��F 1
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ID_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 2
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�p�X���[�h���x��
        //�J�����ԍ��F 2
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            PASSWORD_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 3
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��
        //�J�����ԍ��F 3
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            STATUS_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�__�ύX�o�^)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(long)<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BE1DB4023F
     */
    private void createColumnHeaderChangeRegist()
    {
        final String STR_METHOD_NAME = "createColumnHeaderChangeRegist()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;
        //�|�@@index = 0
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��
        //�J�����ԍ��F 0
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l(long)
        //���t�t�H�[�}�b�g�F�@@null
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            SEQUENCE_NUMBER_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);

        //�|�@@index = 1
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��
        //�J�����ԍ��F 1
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ID_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 2
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��
        //�J�����ԍ��F 2
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            STATUS_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 3
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��
        //�J�����ԍ��F 3
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            INSTITUTION_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 4
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��
        //�J�����ԍ��F 4
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            BRANCH_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 5
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��
        //�J�����ԍ��F 5
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F�@@null
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            ACCOUNT_CODE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 6
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��
        //�J�����ԍ��F 6
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t
        //���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            APPLI_START_DATE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //�|�@@index = 7
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��
        //�J�����ԍ��F 7
        //���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t
        //���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")
        l_intIndex = l_intIndex + 1;
        l_models[l_intIndex] = new WEB3GentradeCsvColumnModel(
            APPLI_END_DATE_LABEL,
            l_intIndex,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat(WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        this.setColumnHeader(l_models);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪��ݒ肷��B<BR>
     * �i�������̃A�b�v���[�h�敪�����׍s������̕K�������null�A������not null���Z�b�g����Ă���j<BR>
     * <BR>
     * 1)�@@�����̃A�b�v���[�h�敪 != null�@@�̏ꍇ�A�����̃A�b�v���[�h�敪���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * 2)�@@�����̖��׍s������  != null�@@�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@2-1)�@@���׍s���<BR>
     * �@@���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA<BR>
     * �@@�@@��؂蕶�����Ƃ�token[]�ɕ�������B<BR>
     * �@@2-2)�@@2-1)�Ŏ擾����token[]�̗v�f�����擾���A ���̗v�f���ɂ��A�b�v���[�h�敪���Z�b�g<BR>
     * �@@2-2-1)�@@token[]�̗v�f�� == �A�b�v���[�h_�V�K�o�^_���ڐ��@@�̏ꍇ�@@<BR>
     * �@@�@@�@@�@@�@@�@@�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�V�K�o�^<BR>
     * �@@2-2-2)�@@2-2-1)�ȊO�@@�̏ꍇ�@@<BR>
     * �@@�@@�@@�@@�@@�@@�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�ύX�o�^<BR>
     * @@param l_strUploadDiv - (�A�b�v���[�h�敪)<BR>
     * �A�b�v���[�h�敪<BR>
     * @@param l_strDetailString - (���׍s������)<BR>
     * ���׍s������<BR>
     * <BR>
     * �� ","����؂蕶���ɂ������׍s������B<BR>
     * @@roseuid 47C276960104
     */
    public void setUploadDiv(String l_strUploadDiv, String l_strDetailString)
    {
        final String STR_METHOD_NAME = "setUploadDiv(String, String)";
        log.entering(STR_METHOD_NAME);

        //1) �����̃A�b�v���[�h�敪 != null�@@�̏ꍇ�A�����̃A�b�v���[�h�敪���v���p�e�B�ɃZ�b�g����B
        if (l_strUploadDiv != null)
        {
            this.uploadDiv = l_strUploadDiv;
        }

        //2) �����̖��׍s������  != null�@@�̏ꍇ�A�ȉ��̏������s���B
        if (l_strDetailString != null)
        {
            //2-1) ���׍s���
            //���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA��؂蕶�����Ƃ�token[]�ɕ�������B
            String token[] = l_strDetailString.split(regex);

            if (l_strDetailString.lastIndexOf(regex) == (l_strDetailString.length() - 1))
            {
                l_strDetailString = l_strDetailString + 1;
                token = l_strDetailString.split(regex);
                token[token.length - 1] = "";
            }

            //2-2) 2-1)�Ŏ擾����token[]�̗v�f�����擾���A ���̗v�f���ɂ��A�b�v���[�h�敪���Z�b�g
            //2-2-1) token[]�̗v�f�� == �A�b�v���[�h_�V�K�o�^_���ڐ��@@�̏ꍇ
            //�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�V�K�o�^
            if (token.length == uploadNewRegistItemCount)
            {
                this.uploadDiv = UPLOAD_DIV_NEW_REGIST;
            }
            else
            {
                //2-2-2) 2-2-1)�ȊO�@@�̏ꍇ
                //�A�b�v���[�h�敪 == �A�b�v���[�h�敪_�ύX�o�^
                this.uploadDiv = UPLOAD_DIV_CHANGE_REGIST;
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�A�b�v���[�h�敪)<BR>
     * this.�A�b�v���[�h�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 47C2864D03E0
     */
    public String getUploadDiv()
    {
        final String STR_METHOD_NAME = "getUploadDiv()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.uploadDiv;
    }

    /**
     * (is�A�b�v���[�h�V�K�o�^)<BR>
     * 1) �A�b�v���[�h�敪�𔻒肵�A�Ή�����boolean�l��ԋp����B<BR>
     * �@@1-1) �A�b�v���[�h�敪 == �A�b�v���[�h�敪_�V�K�o�^�@@�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@1-2) 1-1) �ȊO�@@�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * <BR>
     * @@return boolean
     * @@roseuid 47BEAC030388
     */
    public boolean isUploadNewRegist()
    {
        final String STR_METHOD_NAME = "isUploadNewRegist()";
        log.entering(STR_METHOD_NAME);

        //1-1) �A�b�v���[�h�敪 == �A�b�v���[�h�敪_�V�K�o�^�@@�̏ꍇ�Atrue��ԋp����B
        if (UPLOAD_DIV_NEW_REGIST.equals(uploadDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //1-2) 1-1) �ȊO�@@�̏ꍇ�Afalse��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get�ʔ�)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�ʔԂ�ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒ʔԂ��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĒʔԂ��擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�ʔԃ��x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return long
     * @@roseuid 47BCFD47001F
     */
    public long getSequenceNumber(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSequenceNumber(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�ʔԃ��x��)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(SEQUENCE_NUMBER_LABEL);

        //this.get���ڒl()�ɂĒʔԂ��擾���ԋp����B
        Long l_sequenceNumber = (Long)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

		if (l_sequenceNumber == null)
		{
			log.debug("�ʔԂ��擾�ł��܂���B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_03059,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�ʔԂ��擾�ł��܂���B");
		}

        log.exiting(STR_METHOD_NAME);
        return l_sequenceNumber.longValue();
    }

    /**
     * (getID)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.ID ��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s��ID ���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂ�ID ���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.ID)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BCFD4700AC
     */
    public String getId(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getId(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.ID)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(ID_LABEL);

        //this.get���ڒl()�ɂ�ID ���擾���ԋp����B
        String l_strId = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strId;
    }

    /**
     * (get�p�X���[�h)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�p�X���[�h ��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃p�X���[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂăp�X���[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�p�X���[�h)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BE1E900118
     */
    public String getPassword(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getPassword(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�p�X���[�h)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(PASSWORD_LABEL);

        //this.get���ڒl()�ɂăp�X���[�h���擾���ԋp����B
        String l_strPassword = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strPassword;
    }

    /**
     * (get�X�e�[�^�X)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�X�e�[�^�X��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃X�e�[�^�X���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂăX�e�[�^�X���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�X�e�[�^�X)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BCFD470148
     */
    public String getStatus(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getStatus(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�X�e�[�^�X)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(STATUS_LABEL);

        //this.get���ڒl()�ɂăX�e�[�^�X���擾���ԋp����B
        String l_strStatus = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strStatus;
    }

    /**
     * (get�،���ЃR�[�h)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�،���ЃR�[�h��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏،���ЃR�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂď،���ЃR�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�،���ЃR�[�h)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BCFD4701D4
     */
    public String getInstitutionCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getInstitutionCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�،���ЃR�[�h)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(INSTITUTION_CODE_LABEL);

        //this.get���ڒl()�ɂď،���ЃR�[�h���擾���ԋp����B
        String l_strInstitutionCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strInstitutionCode;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.���X�R�[�h��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.���X�R�[�h)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BCFD470271
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.���X�R�[�h)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(BRANCH_CODE_LABEL);

        //this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B
        String l_strBranchCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

    /**
     * (get�����R�[�h)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�����R�[�h��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌����R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČ����R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�����R�[�h)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return String
     * @@roseuid 47BCFD47030D
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�����R�[�h)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(ACCOUNT_CODE_LABEL);

        //this.get���ڒl()�ɂČ����R�[�h���擾���ԋp����B
        String l_strAccountCode = (String)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get�K�p����From)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�K�p����From��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓K�p����From���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēK�p����From���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�K�p����From)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 47BCFD4703A9
     */
    public Date getAppliStartDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAppliStartDate(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�K�p����From)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(APPLI_START_DATE_LABEL);

        //this.get���ڒl()�ɂēK�p����From���擾���ԋp����B
        Date l_datAppliStartDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_datAppliStartDate;
    }

    /**
     * (get�K�p����To)<BR>
     * �O���A�gID�Ɖﱯ��۰��CSV.�K�p����To��ԋp����B<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓K�p����To���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂēK�p����To���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�K�p����To)�̖߂�l�B<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@return Date
     * @@roseuid 47BCFD48005D
     */
    public Date getAppliEndDate(int l_intLineNo)
    {
        final String STR_METHOD_NAME = "getAppliEndDate(int)";
        log.entering(STR_METHOD_NAME);

        //�J�����F�@@get�J�������f��(�O���A�gID�Ɖﱯ��۰��CSV.�K�p����To)�̖߂�l�B
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(APPLI_END_DATE_LABEL);

        //this.get���ڒl()�ɂēK�p����To���擾���ԋp����B
        Date l_datAppliEndDate = (Date)this.getValue(l_intLineNo, l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
        return l_datAppliEndDate;
    }

    /**
     * (validate���׍s)<BR>
     * �A�b�v���[�h�敪�ɂ�薾�׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * 1)�@@this.is�A�b�v���[�h�V�K�o�^()�@@���@@true�@@�̏ꍇ�A �ȉ��̏��������s����B<BR>
     * �@@1-1)�@@this.get�ʔ�(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̒ʔԂ̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@1-1-1) �ʔ�<0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03059<BR>
     * �@@�@@1-1-2) ����>18���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03054<BR>
     * <BR>
     * �@@1-2)�@@this.getID(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ���ID�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@1-2-1)�@@ID==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02776<BR>
     * �@@�@@1-2-2)�@@ID!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03057<BR>
     * �@@�@@1-2-3)�@@ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * �@@1-3)�@@this.get�p�X���[�h(�s�ԍ�)�ɂĎ擾�����A<BR>
     * �@@�@@�@@�w��s�ԍ��̃p�X���[�h�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@1-3-1)�@@�p�X���[�h==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03058<BR>
     * �@@�@@1-3-2)�@@�p�X���[�h!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01916<BR>
     * �@@�@@1-3-3)�@@�p�X���[�h!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01915<BR>
     * <BR>
     * �@@1-4)�@@this.get�X�e�[�^�X(�s�ԍ�)�ɂĎ擾�����A<BR>
     * �@@�@@�@@�w��s�ԍ��̃X�e�[�^�X�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@1-4-1)�@@�X�e�[�^�X==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00889<BR>
     * �@@�@@1-4-2)�@@�X�e�[�^�X!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�E0�F�g�p��<BR>
     * �@@�@@�@@�E1�F�����i�폜�j<BR>
     * �@@�@@�@@�E9�F���g�p<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * <BR>
     * 2)�@@this.is�A�b�v���[�h�V�K�o�^()�@@���@@false�@@�̏ꍇ�A�ȉ��̏��������s����B<BR>
     * �@@2-1)�@@this.get�ʔ�(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̒ʔԂ̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@2-1-1) �ʔ�<0�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03059<BR>
     * �@@�@@2-1-2) ����>18���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03054<BR>
     * <BR>
     * �@@2-2)�@@this.getID(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ���ID�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@2-2-1)�@@ID==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02776<BR>
     * �@@�@@2-2-2)�@@ID!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03057<BR>
     * �@@�@@2-2-3)�@@ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * �@@2-3)�@@this.get�X�e�[�^�X(�s�ԍ�)�ɂĎ擾�����A<BR>
     * �@@�@@�@@�w��s�ԍ��̃X�e�[�^�X�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@2-3-1)�@@�X�e�[�^�X==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00889<BR>
     * �@@�@@2-3-2)�@@�X�e�[�^�X!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�E0�F�g�p��<BR>
     * �@@�@@�@@�E1�F�����i�폜�j<BR>
     * �@@�@@�@@�E9�F���g�p<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * �@@2-4)�@@this.get���X(�s�ԍ�)�ɂĎ擾�����A<BR>
     * �@@�@@�@@�w��s�ԍ��̕��X�R�[�h�̓��͍��ڒl���`�F�b�N����B<BR>
     * �@@�@@2-4-1)�@@���X�R�[�h!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_01729<BR>
     * �@@�@@2-4-2)�@@���X�R�[�h!=null�ł���A������!=3���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00834<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C290AC023E
     */
    public void validateDetailLine(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDetailLine(int)";
        log.entering(STR_METHOD_NAME);

        //1)�@@this.is�A�b�v���[�h�V�K�o�^()�@@���@@true�@@�̏ꍇ�A �ȉ��̏��������s����B
        if (this.isUploadNewRegist())
        {
            //1-1)�@@this.get�ʔ�(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̒ʔԂ̓��͍��ڒl���`�F�b�N����B
            long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

            //1-1-1)�ʔ�<0�̏ꍇ�A��O���X���[����B
            if (l_lngSequenceNumber < 0)
            {
                log.debug("�ʔԂ�0��菬�����l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03059,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ʔԂ�0��菬�����l�ł��B");
            }
            //1-1-2) ����>18���̏ꍇ�A��O���X���[����B
            if ((l_lngSequenceNumber + "").length() > 18)
            {
                log.debug("�ʔԂ̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ʔԂ̃T�C�Y���s���ł��B");
            }

            //1-2) this.getID(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ���ID�̓��͍��ڒl���`�F�b�N����B
            String l_strId = this.getId(l_intLineNo);

            //1-2-1) ID==null�̏ꍇ�A��O���X���[����B
            if (l_strId == null)
            {
                log.debug("ID��null�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02776,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID��null�B");
            }
            //1-2-2) ID!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strId))
            {
                log.debug("ID�����p�p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03057,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID�����p�p�����ȊO�̒l�ł��B");
            }

            //1-2-3) ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B
            if (l_strId.length() != 8)
            {
                log.debug("ID�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID�̃T�C�Y���s���ł��B");
            }

            //1-3) this.get�p�X���[�h(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̃p�X���[�h�̓��͍��ڒl���`�F�b�N����B
            String l_strPassword = this.getPassword(l_intLineNo);

            //1-3-1) �p�X���[�h==null�̏ꍇ�A��O���X���[����B
            if (l_strPassword == null)
            {
                log.debug("�p�X���[�h�������͂ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03058,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�X���[�h�������͂ł��B");
            }

            //1-3-2) �p�X���[�h!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strPassword))
            {
                log.debug("�p�X���[�h�i������j�̕����킪�s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01916,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�X���[�h�i������j�̕����킪�s���ł��B");
            }

            //1-3-3) �p�X���[�h!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B
            if (l_strPassword.length() != 8)
            {
                log.debug("�p�X���[�h�i������j�̒������s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01915,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�p�X���[�h�i������j�̒������s���ł��B");
            }

            //1-4) this.get�X�e�[�^�X(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̃X�e�[�^�X�̓��͍��ڒl���`�F�b�N����B
            String l_strStatus = this.getStatus(l_intLineNo);

            //1-4-1) �X�e�[�^�X==null�̏ꍇ�A��O���X���[����B
            if (l_strStatus == null)
            {
                log.debug("�X�e�[�^�X�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�����w��ł��B");
            }

            //1-4-2) �X�e�[�^�X!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            //�E0�F�g�p��
            //�E1�F�����i�폜�j
            //�E9�F���g�p
            if (!(WEB3OtherOrgStatusDef.USING.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus)))
            {
                log.debug("�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        else
        {
            //2) this.is�A�b�v���[�h�V�K�o�^()�@@���@@false�@@�̏ꍇ�A�ȉ��̏��������s����B
            //2-1)�@@this.get�ʔ�(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̒ʔԂ̓��͍��ڒl���`�F�b�N����B
            long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

            //2-1-1)�ʔ�<0�̏ꍇ�A��O���X���[����B
            if (l_lngSequenceNumber < 0)
            {
                log.debug("�ʔԂ�0��菬�����l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03059,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ʔԂ�0��菬�����l�ł��B");
            }

            //2-1-2) ����>18���̏ꍇ�A��O���X���[����B
            if ((l_lngSequenceNumber + "").length() > 18)
            {
                log.debug("�ʔԂ̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03054,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ʔԂ̃T�C�Y���s���ł��B");
            }

            //2-2) this.getID(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ���ID�̓��͍��ڒl���`�F�b�N����B
            String l_strId = this.getId(l_intLineNo);

            //2-2-1) ID==null�̏ꍇ�A��O���X���[����B
            if (l_strId == null)
            {
                log.debug("ID��null�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02776,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID��null�B");
            }

            //2-2-2) ID!=null�ł���A�����p�p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isLetterOrDigit(l_strId))
            {
                log.debug("ID�����p�p�����ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03057,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID�����p�p�����ȊO�̒l�ł��B");
            }

            //2-2-3) ID!=null�ł���A������!=8���̏ꍇ�A��O���X���[����B
            if (l_strId.length() != 8)
            {
                log.debug("ID�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ID�̃T�C�Y���s���ł��B");
            }

            //2-3) this.get�X�e�[�^�X(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̃X�e�[�^�X�̓��͍��ڒl���`�F�b�N����B
            String l_strStatus = this.getStatus(l_intLineNo);

            //2-3-1) �X�e�[�^�X==null�̏ꍇ�A��O���X���[����B
            if (l_strStatus == null)
            {
                log.debug("�X�e�[�^�X�����w��ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�����w��ł��B");
            }

            //2-3-2) �X�e�[�^�X!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
            //�E0�F�g�p��
            //�E1�F�����i�폜�j
            //�E9�F���g�p
            if (!(WEB3OtherOrgStatusDef.USING.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(l_strStatus)))
            {
                log.debug("�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�X�e�[�^�X�����݂��Ȃ��R�[�h�l�ł��B");
            }

            //2-4) this.get���X(�s�ԍ�)�ɂĎ擾�����A�w��s�ԍ��̕��X�R�[�h�̓��͍��ڒl���`�F�b�N����B
            String l_strBranchCode = this.getBranchCode(l_intLineNo);

            //2-4-1) ���X�R�[�h!=null�ł���A�����p�����ȊO���i�[����Ă���ꍇ�A��O���X���[����B
            if (l_strBranchCode != null
                && !WEB3StringTypeUtility.isDigit(l_strBranchCode))
            {
                log.debug("���X�R�[�h�����l�ȊO�̒l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�����l�ȊO�̒l�ł��B");
            }

            //2-4-2) ���X�R�[�h!=null�ł���A������!=3���̏ꍇ�A��O���X���[����B
            if (l_strBranchCode != null
                && (l_strBranchCode.length() != 3))
            {
                log.debug("���X�R�[�h�̃T�C�Y���s���ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���X�R�[�h�̃T�C�Y���s���ł��B");
            }
        }
    }

    /**
     * (validate�A�b�v���[�h�t�@@�C�����d��)<BR>
     * 1)�@@�d���ʔԂ��ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �@@1-1)�@@get�ʔ�(�s�ԍ�)�ɂāA�w��s�ԍ��̒ʔԂ��擾����B<BR>
     * �@@1-2)�@@�擾�����ʔԂƎw��s�ԍ����O�̖��׍s�̒ʔԂ��r����B<BR>
     * �@@1-3)�@@�����l�̍s�i�擾�����ʔ� == �w��s�ԍ����O�̖��׍s�̒ʔԁj�����݂���ꍇ�́A<BR>
     * �@@�@@�ʔԂ��d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03051<BR>
     * <BR>
     * <BR>
     * 2)�@@�d��ID���ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �@@2-1)�@@getID(�s�ԍ�)�ɂāA�w��s�ԍ���ID���擾����B<BR>
     * �@@2-2)�@@�擾����ID�Ǝw��s�ԍ����O�̖��׍s��ID���r����B<BR>
     * �@@2-3)�@@�����l�̍s�i�擾����ID == �w��s�ԍ����O�̖��׍s��ID�j�����݂���ꍇ�́A<BR>
     * �@@�@@ID���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03052<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47C26B36031F
     */
    public void validateUploadFileInnerRepeat(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateUploadFileInnerRepeat(int)";
        log.entering(STR_METHOD_NAME);

        //1) �d���ʔԂ��ǉ�����Ă��Ȃ����`�F�b�N���s���B
        //1-1) get�ʔ�(�s�ԍ�)�ɂāA�w��s�ԍ��̒ʔԂ��擾����B
        long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

        for (int i = 0; i < l_intLineNo; i++)
        {
            //1-2)�@@�擾�����ʔԂƎw��s�ԍ����O�̖��׍s�̒ʔԂ��r����B
            //1-3)�@@�����l�̍s�i�擾�����ʔ� == �w��s�ԍ����O�̖��׍s�̒ʔԁj�����݂���ꍇ�́A
            //�ʔԂ��d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B
            long l_lngSequenceNumberPrevious = this.getSequenceNumber(i);
            if (l_lngSequenceNumberPrevious == l_lngSequenceNumber)
            {
                log.debug("�d���ʔԃ`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03051,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d���ʔԃ`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
            }
        }

        //2) �d��ID���ǉ�����Ă��Ȃ����`�F�b�N���s���B
        //2-1) getID(�s�ԍ�)�ɂāA�w��s�ԍ���ID���擾����B
        String l_strId = this.getId(l_intLineNo);

        for (int i = 0; i < l_intLineNo; i++)
        {
            //2-2) �擾����ID�Ǝw��s�ԍ����O�̖��׍s��ID���r����B
            //2-3) �����l�̍s�i�擾����ID == �w��s�ԍ����O�̖��׍s��ID�j�����݂���ꍇ�́A
            //ID���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B
            String l_strIdPrevious = this.getId(i);
            if (l_strIdPrevious.equals(l_strId))
            {
                log.debug("�d��ID�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03052,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d��ID�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
            }
        }
    }

    /**
     * (validate���R�[�h������)<BR>
     * �A�b�v���[�h�敪�ɂ�薾�׍s�ƊY�����R�[�h�Ƃ̐������`�F�b�N���s���B<BR>
     * <BR>
     * <BR>
     * �@@�V�[�P���X�}�u�i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEvalidate�A�b�v���[�h�t�@@�C���v����<BR>
     * �@@�@@�@@validate���R�[�h������()�R�[���Q��<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �u�i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEvalidate�A�b�v���[�h�t�@@�C���v<BR>
     * �@@�@@�@@�@@�@@����validate���R�[�h������()�R�[��: <BR>
     * �@@�@@�@@�@@�@@1.12.8.4.1.1 get�O���A�g���()�̖߂�l != null�@@�̏ꍇ�A��O���X���[����<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03027<BR>
     * ==========================================================<BR>
     * <BR>
     * ========================================================<BR>
     * �V�[�P���X�} �u�i�T�[�r�X���p�j�O���A�gID�Ɖﱯ��۰�ށEvalidate�A�b�v���[�h�t�@@�C���v<BR>
     * �@@�@@�@@�@@�@@����validate���R�[�h������()�R�[��: <BR>
     * �@@�@@�@@�@@�@@1.12.8.5.1.1 get�O���A�g���()�̖߂�l == null�@@�̏ꍇ�A��O���X���[����<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_03019<BR>
     * ==========================================================<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BE645601D5
     */
    public void validateRecodeMatch(int l_intLineNo, String l_strSrvDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateRecodeMatch(int, String)";
        log.entering(STR_METHOD_NAME);

        //get�ʔ�(int)
        //�s�ԍ��F�@@����.�s�ԍ�
        long l_lngSequenceNumber = this.getSequenceNumber(l_intLineNo);

        //get�O���A�g���(long, String, boolean)
        //�ʔԁF  get�ʔ�()�̖߂�l
        //�T�[�r�X�敪�F  ����.�T�[�r�X�敪
        //is�s���b�N�F  false
        WEB3SrvRegiOtherOrgService l_srvRegiOtherOrgService =
            (WEB3SrvRegiOtherOrgService)Services.getService(WEB3SrvRegiOtherOrgService.class);
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            l_srvRegiOtherOrgService.getOtherOrgInfo(l_lngSequenceNumber, l_strSrvDiv, false);

        //is�A�b�v���[�h�V�K�o�^( )
        if (this.isUploadNewRegist())
        {
            // get�O���A�g���()�̖߂�l != null�@@�̏ꍇ�A��O���X���[����
            if (l_srvRegiOtherOrgInfoAdmin != null)
            {
                log.debug("�O���A�g�����擾����͂����Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03027,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���A�g�����擾����͂����Ȃ��B");
            }
        }
        else
        {
            //get�O���A�g���()�̖߂�l == null�@@�̏ꍇ�A��O���X���[����
            if (l_srvRegiOtherOrgInfoAdmin == null)
            {
                log.debug("�O���A�g�����擾�ł��܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03019,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "�O���A�g�����擾�ł��܂���B");
            }

            //get�X�e�[�^�X(int)
            //�s�ԍ��F�@@����.�s�ԍ�
            String l_strStatus = this.getStatus(l_intLineNo);

            //is�X�e�[�^�X�ύX�\(String)
            //�X�e�[�^�X�F  get�X�e�[�^�X()�̖߂�l
            l_srvRegiOtherOrgInfoAdmin.isStatusChangeable(l_strStatus);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
