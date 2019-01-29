head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioListDownloadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name�@@�@@�@@�@@: �o���\���⍇���_�E�����[�hCSV (WEB3AdminAioListDownloadCSV.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/02 �����q (���u) �V�K�쐬 �d�l�ύX���f�� No.694�A697�A698�A700
Revision History : 2007/02/19 �������I (SCS) �d�l�ύX���f�� No.708�A�����̖�� No.007
Revision History : 2009/03/18 ���u�� (���u) �d�l�ύX���f�� No.1155
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.aio.define.WEB3AIOAccountTypeValueDef;
import webbroker3.aio.define.WEB3AdminAioDivDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV)<BR>
 * �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV�t�@@�C���f�[�^�N���X<BR>
 *
 * @@author �����q (���u)
 * @@version 1.0
 */
public class WEB3AdminAioListDownloadCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3AdminAioListDownloadCSV.class);

    /**
     * (������ʃ��x��)<BR>
     * ������ʃx��<BR>
     */
    private static final String ORDER_TYPE_LABEL = "�������";

    /**
     * (��n�����x��)<BR>
     * ��n�����x��<BR>
     */
    private static final String DELIVERY_DATE_LABEL = "��n��";

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    private static final String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    private static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �ڋq�����x��<BR>
     */
    private static final String ACCOUNT_NAME_LABEL = "�ڋq��";

    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    private static final String ORDER_DATE_LABEL = "��������";

    /**
     * (�X�e�[�^�X���x��)<BR>
     * �X�e�[�^�X���x��<BR>
     */
    private static final String STATUS_LABEL = "�X�e�[�^�X";

    /**
     * (�������z���x��)<BR>
     * �������z���x��<BR>
     */
    private static final String CASHINAMT_LABEL = "�������z";

    /**
     * (�o�����z���x��)<BR>
     * �o�����z���x��<BR>
     */
    private static final String CASHOUTAMT_LABEL = "�o�����z";

    /**
     * (���͌o�H���x��)<BR>
     * ���͌o�H���x��<BR>
     */
    private static final String INPUT_ROOT_LABEL = "���͌o�H";

    /**
     * (���͎҃��x��)<BR>
     * ���͎҃��x��<BR>
     */
    private static final String TRADER_LABEL = "���͎�";

    /**
     * (������񃉃x��)<BR>
     * ������񃉃x��<BR>
     */
    private static final String ACCOUNT_INFO_LABEL = "�������";

    /**
     * (�������_SONAR�����\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_SONAR�����\��<BR>
     */
    private static final String SONAR_CASHIN = "SONAR����";

    /**
     * (�������_�o�[�`���������\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�o�[�`���������\��<BR>
     */
    private static final String VIRTUAL_CASHIN = "�o�[�`��������";

    /**
     * (�������_�l�b�g�����\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�l�b�g�����\��<BR>
     */
    private static final String NET_CASHIN = "�l�b�g����";

    /**
     * (�������_�U��(����؋�������a���)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�U��(����؋�������a���)�\��<BR>
     */
    private static final String MARGIN_FROM_DEPOSIT_AMOUNT = "�U��(����؋�������a���)";

    /**
     * (�������_�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)�\��<BR>
     */
    private static final String DEPOSIT_AMOUNT_FROM_FX_GUARANTEE = "�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)";

    /**
     * (�������_���̑��U��(X����a���)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_���̑��U��(X����a���)�\��<BR>
     */
    private static final String FROM_OTHER_TRANSFER = "���̑��U��(X����a���)";

    /**
     * (�������_�o���\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�o���\��<BR>
     */
    private static final String CASHOUT = "�o��";

    /**
     * (�������_�U��(�a������犔��؋���)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�U��(�a������犔��؋���)�\��<BR>
     */
    private static final String FROM_DEPOSIT_AMOUNT_MARGIN = "�U��(�a������犔��؋���)";

    /**
     * (�������_�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)�\��<BR>
     */
    private static final String FX_GUARANTEE_FROM_DEPOSIT_AMOUNT = "�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)";

    /**
     * (�������_���̑��U��(�a�������X)�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �������_���̑��U��(�a�������X)�\��<BR>
     */
    private static final String TO_OTHER_TRANSFER = "���̑��U��(�a�������X)";

    /**
     * (�X�e�[�^�X_�����\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �X�e�[�^�X_�����\��<BR>
     */
    private static final String ORDER_STATUS_COMPLETE = "����";

    /**
     * (�X�e�[�^�X_�������\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �X�e�[�^�X_�������\��<BR>
     */
    private static final String ORDER_STATUS_NOT_TRANSACTION = "������";

    /**
     * (�X�e�[�^�X_�G���[�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �X�e�[�^�X_�G���[�\��<BR>
     */
    private static final String ORDER_STATUS_ACCEPTED_ERROR = "�G���[";

    /**
     * (���͌o�H_WEB�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * ���͌o�H_WEB�\��<BR>
     */
    private static final String INPUT_ROOT_WEB = "WEB";
    
    /**
     * (���͌o�H_SONAR�\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * ���͌o�H_SONAR�\��<BR>
     */
    private static final String INPUT_ROOT_SONAR = "SONAR";

    /**
     * (���͌o�H_�R�[���\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * ���͌o�H_�R�[���\��<BR>
     */
    private static final String INPUT_ROOT_CALL = "�R�[��";

    /**
     * (���͌o�H_�Ǘ��ҕ\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * ���͌o�H_�Ǘ��ҕ\��<BR>
     */
    private static final String INPUT_ROOT_ADMIN = "�Ǘ���";

    /**
     * (�a���敪_���ʗa���\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �a���敪_���ʗa���\��<BR>
     */
    private static final String DEPOSIT_DIV_GENERAL = "���ʗa��";

    /**
     * (�a���敪_�����a���\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �a���敪_�����a���\��<BR>
     */
    private static final String DEPOSIT_DIV_ACCOUNT = "�����a��";

    /**
     * (�a���敪_���̑��\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �a���敪_���̑��\��<BR>
     */
    private static final String DEPOSIT_DIV_OTHER = "���̑�";

    /**
     * (�a���敪_���~�a���\��)<BR>
     * �萔��`�v���p�e�B<BR>
     * �a���敪_���~�a���\��<BR>
     */
    private static final String DEPOSIT_DIV_SAVING = "���~�a��";

    /**
     * (���o���ꗗ�_�E�����[�hCSV)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@roseuid 45B9A47A0348
     */
    public WEB3AdminAioListDownloadCSV()
    {
        // super()�ɂăC���X�^���X�𐶐�����B
        super();

        // create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B
        this.createKeyHeader();

        // create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B
        this.createColumnHeader();
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B <BR>
     * <BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b
     * �g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.������ʃ��x��<BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.��n�����x��<BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�������x��<BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�����������x��<BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * �|�@@index = 6 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X���x��<BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�������z���x��<BR>
     * �@@�J�����ԍ��F 7 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 8 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�o�����z���x��<BR>
     * �@@�J�����ԍ��F 8 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 9 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.���͌o�H���x��<BR>
     * �@@�J�����ԍ��F 9 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 10 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.���͎҃��x��<BR>
     * �@@�J�����ԍ��F 10 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 11 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.������񃉃x��<BR>
     * �@@�J�����ԍ��F 11 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * @@roseuid 45B9A49800F7
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 12;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index = 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ORDER_TYPE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.DELIVERY_DATE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat("yyyy/MM/dd"));

        //index = 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.BRANCH_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_CODE_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_NAME_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ORDER_DATE_LABEL,
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index = 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.STATUS_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.CASHINAMT_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.CASHOUTAMT_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 9
        l_models[9] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.INPUT_ROOT_LABEL,
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 10
        l_models[10] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.TRADER_LABEL,
                10,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 11
        l_models[11] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL,
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME );
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B  <BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@roseuid 45B9A68F0116
     */
    private void createKeyHeader()
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);

        String[] l_strKeyHeader = new String[1];

        l_strKeyHeader[0] =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");

        this.setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������)<BR>
     * ������ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������ʃ��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F<BR>
     * �@@�@@(�����j������� == "1002(��������)" ���A�i�����j�����o�H�敪 == "9(HOST)"
     * �̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_SONAR�����\���B<BR>
     * <BR>
     * �@@�@@(�����j������� == "1002(��������)" ���A�i�����j�����o�H�敪 ==
     * "D(�����A�g)" �̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�o�[�`���������\���B<BR>
     * <BR>
     * �@@�@@(�����j������� == "1002(��������)" ���A�i�����j.com�f�r�b�g���ώ���ԍ�
     * is not null �̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�l�b�g�����\���B<BR>
     * <BR>
     * �@@�@@(�����j������� == "1008(�U��(����؋�������a���))" �̏ꍇ<BR>
     * �@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�U��(����؋�������a���)�\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))" �̏ꍇ<BR>
     * �@@�@@�|
     * �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)�\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1018(���̑��U�֒���(X����a���))" �̏ꍇ<BR>
     * �@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_���̑��U��(X����a���)�\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1001(�o������)" �̏ꍇ<BR>
     * �@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�o���\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1007(�U�֒���(�a������犔��؋���))" �̏ꍇ<BR>
     * �@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�U��(�a������犔��؋���)�\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1011(�ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�))"
     * �̏ꍇ<BR>
     * �@@�@@�|
     * �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)�\���B <BR>
     * <BR>
     * �@@�@@(�����j������� == "1017(���̑��U�֒���(�a�������X))" �̏ꍇ<BR>
     * �@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_���̑��U��(�a�������X)�\���B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strOrderType - (�������)<BR>
     * �������<BR>
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@param l_strComdebitNumber - (.com�f�r�b�g���ώ���ԍ�)<BR>
     * .com�f�r�b�g���ώ���ԍ�<BR>
     * @@roseuid 45B9A6B0022F
     */
    public void setOrderType(int l_intLineNumber,
        String l_strOrderType, String l_strOrderRootDiv, String l_strComdebitNumber)
    {
        final String STR_METHOD_NAME = " setOrderType(int, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //   this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //   [����]
        //   ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������ʃ��x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ORDER_TYPE_LABEL);

        //�Q�j�l�Z�b�g
        //   this.set���ڒl()�ɂāA�l���Z�b�g����B
        //   [����]
        //   �s�ԍ��F �i�����j�s�ԍ�
        //   �J�����F �P�j�Ŏ擾�����J�������f��
        //   �l�F
        String l_strValue = null;
        //�@@�@@(�����j������� == "1002(��������)" ���A�i�����j�����o�H�敪 == "9(HOST)" �̏ꍇ
        //�@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_SONAR�����\���B
        if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.SONAR_CASHIN;
        }
        //�@@�@@(�����j������� == "1002(��������)" ���A�i�����j�����o�H�敪 == "D(�����A�g)" �̏ꍇ
        //�@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�o�[�`���������\���B
        else if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_strOrderRootDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.VIRTUAL_CASHIN;
        }
        //�@@�@@(�����j������� == "1002(��������)" ���A�i�����j.com�f�r�b�g���ώ���ԍ� is not null �̏ꍇ
        //�@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�l�b�g�����\���B
        else if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && l_strComdebitNumber != null)
        {
            l_strValue = WEB3AdminAioListDownloadCSV.NET_CASHIN;
        }
        //�@@�@@(�����j������� == "1008(�U��(����؋�������a���))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�U��(����؋�������a���)�\���B
        else if (String.valueOf(
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.MARGIN_FROM_DEPOSIT_AMOUNT;
        }
        //�@@�@@(�����j������� == "1012(�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�ב֕ۏ؋��U��(�ב֕ۏ؋�����a���)�\���B
        else if (String.valueOf(
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;
        }
        //�@@�@@(�����j������� == "1018(���̑��U�֒���(X����a���))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_���̑��U��(X����a���)�\���B
        else if (String.valueOf(OrderTypeEnum.FROM_OTHER_TRANSFER.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FROM_OTHER_TRANSFER;
        }
        //�@@�@@(�����j������� == "1001(�o������)" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�o���\���B
        else if (String.valueOf(OrderTypeEnum.CASH_OUT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.CASHOUT;
        }
        //�@@�@@(�����j������� == "1007(�U�֒���(�a������犔��؋���))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�U��(�a������犔��؋���)�\���B
        else if (String.valueOf(
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FROM_DEPOSIT_AMOUNT_MARGIN;
        }
        //�@@�@@(�����j������� == "1011(�ב֕ۏ؋��U�֒���(�a�������ב֕ۏ؋�))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_�ב֕ۏ؋��U��(�a�������ב֕ۏ؋�)�\���B
        else if (String.valueOf(
            OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        }
        //�@@�@@(�����j������� == "1017(���̑��U�֒���(�a�������X))" �̏ꍇ
        //�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�������_���̑��U��(�a�������X)�\���B
        else if (String.valueOf(OrderTypeEnum.TO_OTHER_TRANSFER.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.TO_OTHER_TRANSFER;
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.��n�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�����j��n��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@roseuid 45B9A6BA027D
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.��n�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.DELIVERY_DATE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F �i�����j��n��
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datDeliveryDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.���X�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�i�����j���X�R�[�h<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@roseuid 45B9A6C50164
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.BRANCH_CODE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F�@@�i�����j���X�R�[�h
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���Z�b�g����B  <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�i�����j�ڋq�R�[�h<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@roseuid 45BEFDF300D3
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_CODE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F�@@�i�����j�ڋq�R�[�h
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq��)<BR>
     * �ڋq�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�����x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
     * �@@�l�F�@@�i�����j�ڋq��<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountName - (�ڋq��)<BR>
     * �ڋq��<BR>
     * @@roseuid 45B9A6D0000C
     */
    public void setAccountName(int l_intLineNumber, String l_strAccountName)
    {
        final String STR_METHOD_NAME = "setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F�@@���o���ꗗ�_�E�����[�hCSV.�ڋq�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_NAME_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F�@@�i�����j�ڋq��
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��������)<BR>
     * �����������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�����j�󒍓���<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datReceivedDate - (�󒍓���)<BR>
     * �󒍓���<BR>
     * @@roseuid 45B9AA7301E6
     */
    public void setOrderDate(int l_intLineNumber, Date l_datReceivedDate)
    {
        final String STR_METHOD_NAME = "setOrderDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�����������x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ORDER_DATE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F �i�����j�󒍓���
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datReceivedDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�X�e�[�^�X)<BR>
     * �X�e�[�^�X���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �@@�@@<BR>
     * �@@�@@�i����.�X�e�[�^�X == "3(������)"�A���A����.��������敪
     * = "0(�����l)"�j�̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�����\���B<BR>
     * <BR>
     * �@@�@@�i����.�X�e�[�^�X == ("1(��t��)"����"2(������)")�A���A����.��������敪 =
     * "0(�����l)"�j�̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�������\���B<BR>
     * <BR>
     *  �@@�@@�i����.�X�e�[�^�X == "6(�������s)"�j�̏ꍇ<BR>
     * �@@�@@�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�G���[�\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X<BR>
     * @@param l_strOrderCancelDiv - (��������敪)<BR>
     * ��������敪<BR>
     * @@roseuid 45B9AA8500CD
     */
    public void setStatus(int l_intLineNumber, String l_strStatus, String l_strOrderCancelDiv)
    {
        final String STR_METHOD_NAME = "setStatus(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.STATUS_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l
        String l_strValue = null;

        //�i����.�X�e�[�^�X == "3(������)�A���A����.��������敪
        // = "0(�����l)"�j�̏ꍇ
        //�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�����\���B
        if (String.valueOf(OrderStatusEnum.ORDERED.intValue()).equals(l_strStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_COMPLETE;
        }

        //�i����.�X�e�[�^�X == ("1(��t��)"����"2(������)")�A���A����.��������敪 = "0(�����l)"�j�̏ꍇ
        //�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�������\���B
        else if ((String.valueOf(OrderStatusEnum.ACCEPTED.intValue()).equals(l_strStatus)
            || String.valueOf(OrderStatusEnum.ORDERING.intValue()).equals(l_strStatus))
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_NOT_TRANSACTION;
        }

        //�i����.�X�e�[�^�X == "6(�������s)"�j�̏ꍇ
        //�|�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�X�e�[�^�X_�G���[�\���B
        else if (String.valueOf(OrderStatusEnum.NOT_ORDERED.intValue()).equals(l_strStatus))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_ACCEPTED_ERROR;
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���z)<BR>
     * �������z�A�o�����z���Z�b�g����B<BR>
     * <BR>
     * �P�j(����)�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�������z���x��<BR>
     * <BR>
     * �Q�j(����)�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F    <BR>
     * �@@�@@�i�����j���o���敪 == "0(����)"�̏ꍇ<BR>
     * �@@�@@�|�i�����j���z<BR>
     * <BR>
     * �R�j(�o��)�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�o�����z���x��<BR>
     * <BR>
     * �S�j(�o��)�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �R�j�Ŏ擾�����J�������f��<BR>
     *      �l�F    <BR>
     *  �@@�@@�i�����j���o���敪 == "1(�o��)"�̏ꍇ<BR>
     * �@@�@@�|�i�����j���z<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCash - (���z)<BR>
     * ���z<BR>
     * @@param l_strAioDiv - (���o���敪)<BR>
     * ���o���敪<BR>
     * <BR>
     * 0�F�@@����<BR>
     * 1�F�@@�o��<BR>
     * @@roseuid 45B9AADA03BC
     */
    public void setCash(int l_intLineNumber, String l_strCash, String l_strAioDiv)
    {
        final String STR_METHOD_NAME = "setCash(int, String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = null;

        //(����)�J�������f���擾
        //   this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //   [����]
        //   ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�������z���x��
        if (WEB3AdminAioDivDef.CASH_IN.equals(l_strAioDiv))
        {
            l_gentradeCsvColumnModel =
                this.getColumnModel(WEB3AdminAioListDownloadCSV.CASHINAMT_LABEL);
        }

        //(�o��)�J�������f���擾
        //   this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //   [����]
        //   ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.�o�����z���x��
        else if(WEB3AdminAioDivDef.CASH_OUT.equals(l_strAioDiv))
        {
            l_gentradeCsvColumnModel =
                this.getColumnModel(WEB3AdminAioListDownloadCSV.CASHOUTAMT_LABEL);
        }

        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCash);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���͌o�H)<BR>
     * ���͌o�H���Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.���͌o�H���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *  �l�F
     *�@@�@@�i����.���͌o�H == "1(�R�[���Z���^�[)"�j�̏ꍇ
     *�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_�R�[���\���B
     *�@@�@@�i����.���͌o�H == "9(HOST)")�̏ꍇ
     *�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_SONAR�\���B
     *�@@�@@�i����.���͌o�H == "A(�Ǘ���)"�j�̏ꍇ
     *�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_�Ǘ��ҕ\���B
     *�@@�@@�i��L�ȊO�A���A����.���͌o�H != null�j�̏ꍇ
     *�@@�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_WEB�\���B
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInputRoot - (���͌o�H)<BR>
     * ���͌o�H<BR>
     * @@roseuid 45B9AB2E011C
     */
    public void setInputRoot(int l_intLineNumber, String l_strInputRoot)
    {
        final String STR_METHOD_NAME = "setInputRoot(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.���͌o�H���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.INPUT_ROOT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F
        String l_strValue = null;
		//�@@�i����.���͌o�H == "1(�R�[���Z���^�[)"�j�̏ꍇ
		//�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_�R�[���\���B
		if (WEB3OrderRootDivDef.CALLCENTER.equals(l_strInputRoot))
		{
		    l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_CALL;
		}
        //�@@�i����.���͌o�H == "9(HOST)")�̏ꍇ
		//�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_SONAR�\���B
        else if (WEB3OrderRootDivDef.HOST.equals(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_SONAR;
        }
		//�@@�i����.���͌o�H == "A(�Ǘ���)"�j�̏ꍇ
		//�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_�Ǘ��ҕ\���B
        else if (WEB3OrderRootDivDef.ADMIN.equals(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_ADMIN;
        }        
		//�@@�i��L�ȊO�A���A����.���͌o�H != null�j�̏ꍇ
		//�@@�| �Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.���͌o�H_WEB�\���B
        else if (WEB3StringTypeUtility.isNotEmpty(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_WEB;
        }  

		this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���͎�)<BR>
     * ���͎҂��Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.���͎҃��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F �i�����j���͎�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strTrader - (���͎�)<BR>
     * ���͎�<BR>
     * @@roseuid 45B9AB56010D
     */
    public void setTrader(int l_intLineNumber, String l_strTrader)
    {
        final String STR_METHOD_NAME = "setTrader(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.���͎҃��x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.TRADER_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F ����.�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�l
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTrader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������)<BR>
     * ���������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     *    this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������񃉃x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     *    this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �i�����j�s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *   �l�F
     * �i�����j��s�R�[�h�@@+�@@�i�����j�x�X�R�[�h�@@+�@@�������(*1)�@@+�@@�i�����j�����ԍ�<
     * BR>
     * <BR>
     * �@@(*1)������ʂ��ȉ��̒ʂ�Z�b�g����<BR>
     * �@@�E�i�����j������� ==
     * "1"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���ʗa���\���B<BR>
     * �@@�E�i�����j������� ==
     * "2"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_�����a���\���B<BR>
     * �@@�E�i�����j������� ==
     * "3"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���̑��\���B<BR>
     * �@@�E�i�����j������� ==
     * "4"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���~�a���\���B<BR>
     * �@@����L�ȊO��null���Z�b�g����<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strBankCode - (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     * @@param l_strBankBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     * @@param l_strAccountType - (�������)<BR>
     * �������<BR>
     * @@param l_strAccountCode - (�����ԍ�)<BR>
     * �����ԍ�<BR>
     * @@roseuid 45B9AB79014B
     */
    public void setAccountInfo(int l_intLineNumber, String l_strBankCode,
        String l_strBankBranchCode, String l_strAccountType, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountInfo(int, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //   this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //   [����]
        //   ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������񃉃x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL);

        //�@@(*1)������ʂ��ȉ��̒ʂ�Z�b�g����
        //�@@�E�i�����j������� == "1"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���ʗa���\���B
        //�@@�E�i�����j������� == "2"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_�����a���\���B
        //�@@�E�i�����j������� == "3"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���̑��\���B
        //�@@�E�i�����j������� == "4"�̏ꍇ�A�Ǘ��ғ��o���ꗗ�_�E�����[�hCSV.�a���敪_���~�a���\���B
        //�@@����L�ȊO��null���Z�b�g����
        String l_strAccountTypeDeposit = null;
        StringBuffer l_strValue = new StringBuffer("");
        if (WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_GENERAL;
        }
        else if (WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_ACCOUNT;
        }
        else if (WEB3FinSaveDivDef.OTHER.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_OTHER;
        }
        else if (WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_SAVING;
        }
        else
        {
            l_strAccountTypeDeposit = "";
        }

        //�Q�j�l�Z�b�g
        //   this.set���ڒl()�ɂāA�l���Z�b�g����B
        //   [����]
        //   �s�ԍ��F �i�����j�s�ԍ�
        //   �J�����F �P�j�Ŏ擾�����J�������f��
        //  �l�F �i�����j��s�R�[�h�@@+�@@�i�����j�x�X�R�[�h�@@+�@@�������(*1)�@@+�@@�i�����j�����ԍ�
        if (l_strBankCode != null)
        {
            l_strValue.append(l_strBankCode);
        }
        if (l_strBankBranchCode != null)
        {
            l_strValue.append(l_strBankBranchCode);
        }
        if ( !"".equals(l_strAccountTypeDeposit))
        {
            l_strValue.append(l_strAccountTypeDeposit);
        }
        if (l_strAccountCode != null)
        {
            l_strValue.append(l_strAccountCode);
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue.toString());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������)<BR>
     * ���������Z�b�g����B<BR>
     * <BR>
     * �P�j�J�������f���擾<BR>
     * this.get�J�������f��()�ɂāACSV�J�������f�����擾����B<BR>
     * <BR>
     * [����]<BR>
     * ���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������񃉃x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * this.set���ڒl()�ɂāA�l���Z�b�g����B<BR>
     * <BR>
     * [����]<BR>
     * �s�ԍ��F �i�����j�s�ԍ�<BR>
     * �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     * �l�F"���FX"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     */
    public void setAccountInfo(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = "setAccountInfo(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F ���o���ꗗ�_�E�����[�hCSV.������񃉃x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂāA�l���Z�b�g����B
        //[����]
        //�s�ԍ��F �i�����j�s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F"���FX"
        this.setValue(
            l_intLineNumber,
            l_gentradeCsvColumnModel,
            WEB3AIOAccountTypeValueDef.OSAKA_FX);

        log.exiting(STR_METHOD_NAME);
    }
}
@
