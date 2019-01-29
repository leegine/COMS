head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoInvalidOperationFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����I�y���[�V��������fewCSV(WEB3AdminIpoInvalidOperationFewCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/09 ꎉ� (���u) �V�K�쐬�@@�d�l�ύX�E���f��161
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�u�b�N�r���f�B���O�󋵃_�E�����[�h�ō쐬���� )<BR>
 * �����I�y���[�V��������CSV�t�@@�C���f�[�^�N���X <BR>
 * �i���҃R�[�h�Ȃ��j<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminIpoInvalidOperationFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoInvalidOperationFewCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h
     */
    public static final String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h
     */
    public static final String ACCOUNT_NAME_LABEL = "�ڋq��";

    /**
     * (��t�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h��t�����h
     */
    public static final String RECEPTION_DATE_LABEL = "��t����";

    /**
     * (�������e���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�������e�h
     */
    public static final String PROCESSING_LABEL = "�������e";

    /**
     * (�\�����ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\�����ʁh
     */
    public static final String ORDER_QUNTITY_LABEL = "�\������";

    /**
     * (�\�����i���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\�����i�h
     */
    public static final String ORDER_PRICE_LABEL = "�\�����i";

    /**
     * (�w�l�^���s���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�w�l�^���s�h
     */
    public static final String LIMIT_PRICE_MARKET_ORDER_LABEL = "�w�l�^���s";

    /**
     * (�w�l�\��)<BR>
     * �萔��`�v���p�e�B�@@�w�l<BR>
     */
    public static final String LIMIT_PRICE_INDICATION = "�w�l";

    /**
     * (���s�\��)<BR>
     * �萔��`�v���p�e�B�@@���s<BR>
     */
    public static final String MARKET_ORDER_INDICATION = "���s";

    /**
     * (�������e_�V�K�\��)<BR>
     * �萔��`�v���p�e�B�@@�������e_�V�K�\��
     */
    public static final String PROCESSING_ORDERED_INDICATION = "�V�K";

    /**
     * (�������e_�����\��)<BR>
     * �萔��`�v���p�e�B�@@�������e_�����\��
     */
    public static final String PROCESSING_CHANGE_INDICATION = "����";

    /**
     * (�������e_����\��)<BR>
     * �萔��`�v���p�e�B�@@�������e_����\��
     */
    public static final String PROCESSING_CANCEL_INDICATION = "���";

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     * @@roseuid 40F21E4701F5
     */
    public WEB3AdminIpoInvalidOperationFewCsv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        //super()�ɂăC���X�^���X�𐶐�����B
        super();

        final String STR_METHOD_NAME = " WEB3AdminIpoInvalidOperationFewCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B
        this.createKeyHeader(l_ipoProduct);

        //create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.��t�������x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�������e���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�\�����ʃ��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�\�����i���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.�w�l�^���s���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * @@roseuid 40F21E4701F7
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[8];

        //�����I�y���[�V��������fewCSV.���X�R�[�h���x��
        l_columnHeader[0] =
            new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�����I�y���[�V��������fewCSV.�ڋq�R�[�h���x��
        l_columnHeader[1] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�����I�y���[�V��������fewCSV.�ڋq�����x��
        l_columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�����I�y���[�V��������fewCSV.��t�������x��
        l_columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                RECEPTION_DATE_LABEL,
                3,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //�����I�y���[�V��������fewCSV.�������e���x��
        l_columnHeader[4] =
            new WEB3GentradeCsvColumnModel(PROCESSING_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�����I�y���[�V��������fewCSV.�\�����ʃ��x��
        l_columnHeader[5] =
            new WEB3GentradeCsvColumnModel(ORDER_QUNTITY_LABEL, 5, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�����I�y���[�V��������fewCSV.�\�����i���x��
        l_columnHeader[6] =
            new WEB3GentradeCsvColumnModel(ORDER_PRICE_LABEL, 6, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�����I�y���[�V��������fewCSV.�w�l�^���s���x��
        l_columnHeader[7] =
            new WEB3GentradeCsvColumnModel(
                LIMIT_PRICE_MARKET_ORDER_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        setColumnHeader(l_columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����A<BR>
     * �@@set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@IPO����.�����R�[�h<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@IPO����.������<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@roseuid 40F21E4701F8
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct)
    {
        final String STR_METHOD_NAME = " createKeyHeader(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        String[] l_strKeyHeader = new String[3];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSystem.getSystemTimestamp();

        l_strKeyHeader[0] = WEB3DateUtility.formatDate(l_processTime, "yyyy/MM/dd HH:mm:ss");
        l_strKeyHeader[1] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        l_strKeyHeader[2] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();

        setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV.���X�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���X�h�c�ɊY�����镔�X�I�u�W�F�N�g.getBranchCode()<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_lngBranchId - (���X�h�c)<BR>
     * ���X�h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F21E4701FA
     */
    public void setBranchCode(int l_intLineNumber, long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BRANCH_CODE_LABEL);

            //���X�h�c�ɊY�����镔�X�I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId);
            String l_strCode = l_branch.getBranchCode();

            //�l�Z�b�g
            this.setValue(l_intLineNumber, l_csvDataModel, l_strCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq)<BR>
     * �ڋq�R�[�h�A�ڋq�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾<BR>
     * �@@�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�ڋq�R�[�h���x��<BR>
     * <BR>
     * �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.getAccountCode()�̍�6byte<BR>
     * <BR>
     * �S�j�@@�i�ڋq���j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�ڋq�����x��<BR>
     * <BR>
     * �T�j�@@�i�ڋq���j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�S�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.get�ڋq�\����()<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_lngAccountId - (�����h�c)<BR>
     * �����h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F21E470205
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount(int, long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�ڋq�I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

            //�i�ڋq�R�[�h�j�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode = this.getColumnModel(ACCOUNT_CODE_LABEL);

            //�i�ڋq�R�[�h�j�l�Z�b�g
            String l_strCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0,6));

            //�i�ڋq���j�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);

            //�i�ڋq���j�l�Z�b�g
            String l_strName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);

        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��t����)<BR>
     * ��t�������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV��t�������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�쐬����<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_tsCreatedTimestamp - (�쐬����)<BR>
     * �쐬����<BR>
     * @@roseuid 40F21E470208
     */
    public void setReceptionDate(int l_intLineNumber, Timestamp l_tsCreatedTimestamp)
    {
        final String STR_METHOD_NAME = " setReceptionDate(int, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(RECEPTION_DATE_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_tsCreatedTimestamp);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������e)<BR>
     * �������e���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�������e���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.ORDERED�j�̏ꍇ�A�����I�y���[�V��������fewCSV�\�����_�V�K�\���B<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.MODIFIED�j�̏ꍇ�A�����I�y���[�V��������fewCSV�\�����_�����\���B<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.CANCELLED�j�̏ꍇ�A�����I�y���[�V��������fewCSV�\�����_����\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_orderStatus - (�u�b�N�r���f�B���O�\�����)<BR>
     * �u�b�N�r���f�B���O�\�����<BR>
     * @@roseuid 40F21EF0031E
     */
    public void setProcessing(int l_intLineNumber, OrderStatusEnum l_orderStatus)
    {
        final String STR_METHOD_NAME = " setProcessing(int, OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(PROCESSING_LABEL);

        //�l�Z�b�g
        String l_strProcessing = null;

        if ((OrderStatusEnum.ORDERED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_ORDERED_INDICATION;
        }
        else if ((OrderStatusEnum.MODIFIED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CHANGE_INDICATION;
        }
        else if ((OrderStatusEnum.CANCELLED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CANCEL_INDICATION;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strProcessing);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\������)<BR>
     * �\�����ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�\�����ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�\������<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_dblOrderQuantity - (�\������)<BR>
     * �\������ <BR>
     * @@roseuid 40F21E47020B
     */
    public void setOrderQuantity(int l_intLineNumber, double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_QUNTITY_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\�����i)<BR>
     * �\�����i���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�\�����i���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�\�����i<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_dblOrderPrice - (�\�����i)<BR>
     * �\�����i<BR>
     * @@roseuid 40F21E47020E
     */
    public void setOrderPrice(int l_intLineNumber, double l_dblOrderPrice)
    {
        final String STR_METHOD_NAME = " setOrderPrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_PRICE_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderPrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�w�l�^���s�l)<BR>
     * �w�l�^���s���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������fewCSV�w�l�^���s���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�����̎w�l == 0�j�̏ꍇ�A�����I�y���[�V��������fewCSV���s�\���B<BR>
     * �@@�@@�ȊO�A�����I�y���[�V��������fewCSV�w�l�\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * @@roseuid 40F21E470211
     */
    public void setLimitPriceMarketOrder(int l_intLineNumber, double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = " setLimitPriceMarketOrder(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LIMIT_PRICE_MARKET_ORDER_LABEL);

        //�l�Z�b�g
        String l_strIndication = null;
        if (l_dblLimitPrice == 0)
        {
            l_strIndication = MARKET_ORDER_INDICATION;
        }
        else
        {
            l_strIndication = LIMIT_PRICE_INDICATION;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strIndication);

        log.exiting(STR_METHOD_NAME);
    }
}
@
