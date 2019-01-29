head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoDemandListFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �u�b�N�r���f�B���O��fewCSV(WEB3AdminIpoDemandListFewCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/09 ꎉ� (���u) �V�K�쐬
                   2006/11/13 ꎉ� (���u) �d�l�ύX�E���f��161�A���f��163
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
 * (�u�b�N�r���f�B���O��fewCSV)<BR>
 * �u�b�N�r���f�B���O�󋵃_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X <BR>
 * �i���҃R�[�h�Ȃ��j<BR>
 *
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminIpoDemandListFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoDemandListFewCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h<BR>
     */
    public static final String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h<BR>
     */
    public static final String ACCOUNT_NAME_LABEL = "�ڋq��";

    /**
     * (�V�K�\���������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�V�K�\�������h<BR>
     */
    public static final String BOOKBUILDING_CREATED_TIMESTAMP = "�V�K�\������";

    /**
     * (�\�����ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\�����ʁh<BR>
     */
    public static final String ORDER_QUANTITY_LABEL = "�\������";

    /**
     * (�\�����i���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\�����i�h<BR>
     */
    public static final String ORDER_PRICE_LABEL = "�\�����i";

    /**
     * (�w�l�^���s���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�w�l�^���s�h<BR>
     */
    public static final String LIMIT_PRICE_MARKET_ORDER_LABEL = "�w�l�^���s";

    /**
     * (��l���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��l�h<BR>
     */
    public static final String BASE_PRICE_LABEL = "��l";

    /**
     * (�\�������z���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\�������z�h<BR>
     */
    public static final String BOOKBUILDING_PRICE_LABEL = "�\�������z";

    /**
     * (�o���]�̓��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�o���]�́h<BR>
     */
    public static final String TRADING_POWER_LABEL = "�o���]��";

    /**
     * (�\���z�ێ��҃��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\���z�ێ��ҁh<BR>
     */
    public static final String TRADING_POWER_CHECK = "�\���z�ێ���";

    /**
     * (�\����ԃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\����ԁh<BR>
     */
    public static final String ORDER_STATUS_LABEL = "�\�����";

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
     * (�\���z�ێ���_OK�\��)<BR>
     * �萔��`�v���p�e�B�@@�\���z�ێ��ҕ\�� OK<BR>
     */
    public static final String TRADING_POWER_CHECK_OK = "��";

    /**
     * (�\���z�ێ���_NG�\��)<BR>
     * �萔��`�v���p�e�B�@@�\���z�ێ��ҕ\�� NG<BR>
     */
    public static final String TRADING_POWER_CHECK_NG = "�~";

    /**
     * (�\�����_�V�K�\��)<BR>
     * �萔��`�v���p�e�B�@@�\�����_�V�K�\��<BR>
     */
    public static final String ORDER_STATUS_ORDERED = "�V�K";

    /**
     * (�\�����_�����\��)<BR>
     * �萔��`�v���p�e�B�@@�\�����_�����\��<BR>
     */
    public static final String ORDER_STATUS_MODIFIED = "����";

    /**
     * (�\�����_����\��)<BR>
     * �萔��`�v���p�e�B�@@�\�����_����\��<BR>
     */
    public static final String ORDER_STATUS_CANCELLED = "���";

    /**
     * (�]�͂Ȃ��̕\��)<BR>
     * �萔��`�v���p�e�B�@@�]�͂Ȃ��̕\��<BR>
     */
    public static final String TRADING_POWER_HYPHEN = "�|";

    /**
     * (�u�b�N�r���f�B���O��fewCSV)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     * @@roseuid 40E27ACA0335
     */
    public WEB3AdminIpoDemandListFewCsv(WEB3IpoProductImpl l_ipoProduct)
    {
        //super()�ɂăC���X�^���X�𐶐�����B
        super();

        final String STR_METHOD_NAME = " WEB3AdminIpoDemandListFewCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B
        this.createKeyHeader(l_ipoProduct);

        //create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *  (create�J�����w�b�_) <BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�ڋq�����x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�V�K�\���������x�� <BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@ <BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�����ʃ��x�� <BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�����i���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�w�l�^���s���x�� <BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.��l���x�� <BR>
     * �@@�J�����ԍ��F 7 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 8 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�������z���x�� <BR>
     * �@@�J�����ԍ��F 8 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 9 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�o���]�̓��x�� <BR>
     * �@@�J�����ԍ��F 9 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 10 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\���z�ێ��҃��x�� <BR>
     * �@@�J�����ԍ��F 10 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 11 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\����ԃ��x�� <BR>
     * �@@�J�����ԍ��F 11 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[12];

        //�u�b�N�r���f�B���O��fewCSV.���X�R�[�h���x��
        columnHeader[0] =
            new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�ڋq�R�[�h���x��
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�ڋq�����x��
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�V�K�\���������x��
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                BOOKBUILDING_CREATED_TIMESTAMP,
                3,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //�u�b�N�r���f�B���O��fewCSV.�\�����ʃ��x��
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(ORDER_QUANTITY_LABEL, 4, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�\�����i���x��
        columnHeader[5] =
            new WEB3GentradeCsvColumnModel(ORDER_PRICE_LABEL, 5, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�w�l�^���s���x��
        columnHeader[6] =
            new WEB3GentradeCsvColumnModel(
                LIMIT_PRICE_MARKET_ORDER_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //�u�b�N�r���f�B���O��fewCSV.��l���x��
        columnHeader[7] =
            new WEB3GentradeCsvColumnModel(BASE_PRICE_LABEL, 7, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�\�������z���x��
        columnHeader[8] =
            new WEB3GentradeCsvColumnModel(BOOKBUILDING_PRICE_LABEL, 8, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�o���]�̓��x��
        columnHeader[9] =
            new WEB3GentradeCsvColumnModel(TRADING_POWER_LABEL, 9, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�\���z�ێ��҃��x��
        columnHeader[10] =
            new WEB3GentradeCsvColumnModel(TRADING_POWER_CHECK, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�u�b�N�r���f�B���O��fewCSV.�\����ԃ��x��
        columnHeader[11] =
            new WEB3GentradeCsvColumnModel(ORDER_STATUS_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        this.setColumnHeader(columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����A<BR>
     * set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
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
     * @@roseuid 40E2587400E3
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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.���X�R�[�h���x��<BR>
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
     * ���X�h�c
     * @@throws WEB3BaseException
     * @@roseuid 40E2742302F6
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
            String l_strValue = l_branch.getBranchCode();

            //�l�Z�b�g
            this.setValue(l_intLineNumber, l_csvDataModel, l_strValue);
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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�ڋq�R�[�h���x��<BR>
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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�ڋq�����x��<BR>
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
     * �s�ԍ����w�肷��B
     * @@param l_lngAccountId - (�����h�c)<BR>
     * �����h�c
     * @@throws WEB3BaseException
     * @@roseuid 40E274AF0279
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
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0, 6));

            //�i�ڋq���j�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);

            //�i�ڋq���j�l�Z�b�g
            String l_strName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);

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
     * (set�V�K�\������)<BR>
     * �V�K�\���������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�V�K�\���������x�� <BR>
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
     * �s�ԍ����w�肷��B
     * @@param l_tsCreatedTimestamp - (�쐬����)<BR>
     * �쐬����
     * @@roseuid 40E27ACA0335
     */
    public void setBookbuildingCreatedTimestamp(int l_intLineNumber, Timestamp l_tsCreatedTimestamp)
    {
        final String STR_METHOD_NAME = " setBookbuildingCreatedTimestamp(int, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BOOKBUILDING_CREATED_TIMESTAMP);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_tsCreatedTimestamp);

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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�����ʃ��x�� <BR>
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
     * �s�ԍ����w�肷��B
     * @@param l_dblOrderQuantity - (�\������)<BR>
     * �\������
     * @@roseuid 40E27B690335
     */
    public void setOrderQuantity(int l_intLineNumber, double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_QUANTITY_LABEL);

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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�����i���x�� <BR>
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
     * �s�ԍ����w�肷��B
     * @@param l_dblOrderPrice - (�\�����i)<BR>
     * �\�����i
     * @@roseuid 40E27B9601DD
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
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�w�l�^���s���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�����̎w�l == 0�j�̏ꍇ�A�u�b�N�r���f�B���O��fewCSV.���s�\���B <BR>
     * �@@�@@�ȊO�A�u�b�N�r���f�B���O��fewCSV.�w�l�\���B <BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l
     * @@roseuid 40E26E790066
     */
    public void setLimitPriceMarketOrderPrice(int l_intLineNumber, double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = " setLimitPriceMarketOrderPrice(int, double)";
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

        log.debug("l_strIndication = " + l_strIndication);

        this.setValue(l_intLineNumber, l_csvDataModel, l_strIndication);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��l)<BR>
     * ��l���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.��l���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@��l<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblBasePrice - (��l)<BR>
     * ��l
     * @@roseuid 40EE858E02EF
     */
    public void setBasePrice(int l_intLineNumber, double l_dblBasePrice)
    {
        final String STR_METHOD_NAME = " setBasePrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BASE_PRICE_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblBasePrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\�������z)<BR>
     * �\�������z���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\�������z���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�\�������z<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblBookbuildingPrice - (�\�������z)<BR>
     * �\�������z
     * @@roseuid 40E27BC10112
     */
    public void setBookbuildingPrice(int l_intLineNumber, double l_dblBookbuildingPrice)
    {
        final String STR_METHOD_NAME = " setBookbuildingPrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BOOKBUILDING_PRICE_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblBookbuildingPrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�o���]��)<BR>
     * �o���]�͂��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�o���]�̓��x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�o���]��<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblTradingPower - (�o���]��)<BR>
     * �o���]��
     * @@roseuid 40E27BE50373
     */
    public void setTradingPower(int l_intLineNumber, double l_dblTradingPower)
    {
        final String STR_METHOD_NAME = " setTradingPower(int, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_LABEL);

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\���z�ێ���)<BR>
     * �\���z�ێ��҂��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\���z�ێ��҃��x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�\�������z <= �o���]�́j�̏ꍇ�A<BR>
     * �@@�@@�u�b�N�r���f�B���O��fewCSV.�\���z�ێ���_OK�\���B <BR>
     * �@@�@@�ȊO�A�u�b�N�r���f�B���O��fewCSV.�\���z�ێ���_NG�\���B <BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblBookbuildingPrice - (�\�������z)<BR>
     * �\�������z
     * @@param l_dblTradingPower - (�o���]��)<BR>
     * �o���]��
     * @@roseuid 40E2720103A2
     */
    public void setTradingPowerCheck(int l_intLineNumber, double l_dblBookbuildingPrice, double l_dblTradingPower)
    {
        final String STR_METHOD_NAME = " setTradingPowerCheck(int, double, double)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_CHECK);

        //�l�Z�b�g
        String l_strTradingPowerCheck = null;
        if (l_dblBookbuildingPrice <= l_dblTradingPower)
        {
            l_strTradingPowerCheck = TRADING_POWER_CHECK_OK;
        }
        else
        {
            l_strTradingPowerCheck = TRADING_POWER_CHECK_NG;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strTradingPowerCheck);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\�����)<BR>
     * �\����Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�u�b�N�r���f�B���O��fewCSV.�\����ԃ��x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�i�u�b�N�r���f�B���O�\����� == OrderStatusEnum.ORDERED�j�̏ꍇ�A<BR>
     * �@@�u�b�N�r���f�B���O��fewCSV.�\�����_�V�K�\���B<BR>
     * �@@�i�u�b�N�r���f�B���O�\����� == OrderStatusEnum.MODIFIED�j�̏ꍇ�A<BR>
     * �@@�u�b�N�r���f�B���O��fewCSV.�\�����_�����\���B <BR>
     * �@@�i�u�b�N�r���f�B���O�\����� == OrderStatusEnum.CANCELLED�j�̏ꍇ�A<BR>
     * �@@�u�b�N�r���f�B���O��fewCSV.�\�����_����\���B�@@<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_bookbuildingOrderStatus - (�u�b�N�r���f�B���O�\�����)<BR>
     * �u�b�N�r���f�B���O�\�����
     * @@roseuid 40EE84BF01A7
     */
    public void setOrderStatus(int l_intLineNumber, OrderStatusEnum l_bookbuildingOrderStatus)
    {
        final String STR_METHOD_NAME = " setOrderStatus(int, OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_bookbuildingOrderStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_STATUS_LABEL);

        //�l�Z�b�g
        String l_strOrderStatus = null;

        if ((OrderStatusEnum.ORDERED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_ORDERED;
        }
        else if ((OrderStatusEnum.MODIFIED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_MODIFIED;
        }
        else if ((OrderStatusEnum.CANCELLED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_CANCELLED;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strOrderStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�o���]�́|�]�͕\���Ȃ�)<BR>
     * ���ځu�o���]�́v��"�|"�i���]�͕\���Ȃ��j���Z�b�g����B<BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@�J�����F�@@�o���]�͂̃J�������f��<BR>
     * �@@�l�@@�@@�F�@@"�|"�i�n�C�t���j���Z�b�g<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     */
    public void setTradingPowerWithoutIndicate(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTradingPowerWithoutIndicate(int)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_LABEL);

        this.setValue(l_intLineNumber, l_csvDataModel, TRADING_POWER_HYPHEN);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\���z�ێ��ҁ|�]�͕\���Ȃ�)<BR>
     * ���ځu�\���z�ێ��ҁv��"�|"�i���]�͕\���Ȃ��j���Z�b�g����B<BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@�J�����F�@@�\���z�ێ��҂̃J�������f��<BR>
     * �@@�l�@@�@@�F�@@"�|"�i�n�C�t���j���Z�b�g<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     */
    public void setTradingPowerCheckWithoutIndicate(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTradingPowerCheckWithoutIndicate(int)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_CHECK);

        this.setValue(l_intLineNumber, l_csvDataModel, TRADING_POWER_HYPHEN);

        log.exiting(STR_METHOD_NAME);
    }
}
@
