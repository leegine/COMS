head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TransactionCategoryDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �@@�\�J�e�S���R�[�h �萔��`�C���^�t�F�C�X(WEB3TransactionCategoryDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
Revesion History : 2006/02/20 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g344
Revesion History : 2006/03/17 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g350
Revesion History : 2006/03/28 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g363
Revesion History : 2006/03/31 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g357
Revesion History : 2006/05/11 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g371
Revesion History : 2006/05/26 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g372
Revesion History : 2006/06/20 ������(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g379
Revesion History : 2006/07/21 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g394�A395
Revesion History : 2006/07/21 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g401
Revesion History : 2006/08/30 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g409
Revesion History : 2006/10/12 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g434
Revesion History : 2006/10/24 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g439
Revesion History : 2007/01/15 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g454
Revesion History : 2007/01/25 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g452
Revesion History : 2007/01/31 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g458
Revesion History : 2007/04/23 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g474
Revesion History : 2007/05/29 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g493
Revesion History : 2007/07/20 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g528
Revesion History : 2007/09/10 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g542
Revesion History : 2007/10/09 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g556
Revesion History : 2007/11/26 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g578
Revesion History : 2008/01/28 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g591
Revesion History : 2008/03/04 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g612
Revesion History : 2008/03/11 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g614
Revesion History : 2008/03/24 �h�C(���u) ���ʎd�l�ύX�E�c�a���C�A�E�g617
Revesion History : 2008/09/24 ������ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g642
Revesion History : 2008/12/16 ��іQ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g666
Revesion History : 2009/01/05 ��іQ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g669
Revesion History : 2009/03/04 ��іQ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g675
*/
package webbroker3.common.define;

/**
 * �Ǘ��Ҍ���.�@@�\�J�e�S���R�[�h �萔��`�C���^�t�F�C�X
 *
 * @@author �A�C��(SRA)
 * @@version 1.0
 */
public interface WEB3TransactionCategoryDef
{

    /**
     * �ڋq��{���i��{�j
     */
    public static final String ACCINFO_BASE_INFO = "A0101";

    /**
     *�@@�ڋq�U����Ǘ�
     */
    public static final String ACCINFO_TRANSFER_ACCOUNT = "A0102";

    /**
     * �ڋq����Ǘ��FA0103
     */
    public static final String ACCINFO_TRADING = "A0103";

    /**
     * �Ïؔԍ��Ǘ�
     */
    public static final String ACCINFO_PASSWORD = "A0104";

    /**
     * �ڋq�o�^�Ǘ����j���[
     */
    public static final String ACCINFO_REGISTER_MANAGE_MENU = "A0105";

    /**
     * �����ؑցE�d�q��t�\���Ǘ�
     */
    public static final String ACCOUNT_SWITCH_ELECTRON_DELI_APPLY = "A0106";

    /**
     * �]�͊Ǘ�
     */
    public static final String TRADING_POWER = "A0201";

    /**
     * �v���C�x�[�g�C���t�H���[�V����
     */
    public static final String PRIVATE_INFO = "A0301";

    /**
     * ���[����� �@@
     */
    public static final String MAILINFO = "A0302";

    /**
     * �A���Ǘ� �@@
     */
    public static final String INFORM = "A0303";
    
    /**
     * �⍇���Ǘ�
     */
    public static final String FAQ = "A0304";
    
    /**
     * �⍇���Ǘ��i���������E�~�j���E�M�p����j
     */
    public static final String FAQ_EQUITY = "A0305";
    
    /**
     * �⍇���Ǘ��i�����w���敨�E�I�v�V�����j
     */
    public static final String FAQ_XBIFO = "A0306";
    
    /**
     * �⍇���Ǘ��i�O�������j
     */
    public static final String FAQ_FOREIGN_EQUITY = "A0307";
    
    /**
     * �⍇���Ǘ��i���M�E�ݓ��j
     */
    public static final String FAQ_XBMF_XBRUITO = "A0308";
    
    /**
     * �⍇���Ǘ��iIPO�j
     */
    public static final String FAQ_IPO = "A0309";
    
    /**
     * �⍇���Ǘ��i�T�[�r�X���p�j
     */
    public static final String FAQ_SRVREGI = "A0310";
    
    /**
     * �⍇���Ǘ��i���o���j
     */
    public static final String FAQ_AIO = "A0311";
    
    /**
     * �⍇���Ǘ��i�����J�݁j
     */
    public static final String FAQ_ACCOPEN = "A0312";
    
    /**
     * �⍇���Ǘ��i���q�l���j
     */
    public static final String FAQ_ACCOUNTINFO = "A0313";
    
    /**
     * �⍇���Ǘ��i�v���C�x�[�g�C���t�H���[�V�����j
     */
    public static final String FAQ_PRIVATEINFO = "A0314";
    
    /**
     * �⍇���Ǘ��i��������j
     */
    public static final String FAQ_TRADEHISTORY = "A0315";
    
    /**
     * �⍇���Ǘ��i����c���񍐏��j
     */
    public static final String FAQ_TRADE_BALANCE_REPORT = "A0316";
    
    /**
     * �⍇���Ǘ��i���o�ɗ����j
     */
    public static final String FAQ_SEC_RECEIPT_DELIVERY_ACTION = "A0317";
    
    /**
     * �⍇���Ǘ��i�؋����U�ցj
     */
    public static final String FAQ_DEPOSIT_TRANSFER = "A0318";
    
    /**
     * �⍇���Ǘ��i���Y�]�́j
     */
    public static final String FAQ_ASSET_POWER = "A0319";
    
    /**
     * �⍇���Ǘ��i���v���׏Ɖ�j
     */
    public static final String FAQ_PROFIT_LOSS_INQUIRY = "A0320";
    
    /**
     * �⍇���Ǘ��i�뉿�P�����׏ڍׁj
     */
    public static final String FAQ_UNIT_PRICE_DETAILS = "A0321";
    
    /**
     * �⍇���Ǘ��i�|�C���g�V�X�e���j
     */
    public static final String FAQ_POINTSYSTEM = "A0322";
    
    /**
     * �⍇���Ǘ��i�ב֏؋����j
     */
    public static final String FAQ_EXCHANGE_DEPOSIT = "A0323";
    
    /**
     * �⍇���Ǘ��i�v�Z�T�[�r�X�j
     */
    public static final String FAQ_CALC_SERVICE = "A0324";
    
    /**
     * �⍇���Ǘ��i�d�q��t�T�[�r�X�j
     */
    public static final String FAQ_ELEC_PAY_SERVICE = "A0325";
    
    /**
     * �⍇���Ǘ��i�O�������U�֘A�g�j
     */
    public static final String FAQ_FEQ_CON = "A0326";

    /**
     * �����J�� �@@
     */
    public static final String ACC_OPEN = "A0401";

    /**
     * �R�� �@@
     */
    public static final String JUDGE = "A0402";

    /**
     * �����J�ݐ\���A�b�v���[�h
     */
    public static final String ACC_OPEN_APPLY_UPLOAD = "A0403";

    /**
     * PTS�ڋq�Ǘ�
     */
    public static final String PTS_ACCOUNT_MANAGE = "A0501";

    /**
     * �o������ �@@
     */
    public static final String AIO_CASH_OUT_INQ = "B0101";

    /**
     * ��s�A�g �@@
     */
    public static final String BANK_COOPERATION = "B0102";

    /**
     * ���Z�@@�֌��ϘA�g�@@
     */
    public static final String AIO_SETTLE_REPORT = "B0201";

    /**
     * �����A�� �@@
     */
    public static final String AIO_CASH_IN_NOTICE = "B0301";

    /**
     * �ב֕ۏ؋��Ǘ�(�����Ǘ��E�����J�݊Ǘ�) �@@
     */
    public static final String FX_ACCOUNT_MANAGE = "B0401";

    /**
     * �ב֕ۏ؋��Ǘ�(�U�֊Ǘ�) �@@
     */
    public static final String FX_TRANSFER_MANAGE = "B0402";

    /**
     * �O�������U�֘A�g(�����Ǘ��E�����J�݊Ǘ�) �@@
     */
    public static final String FEQ_ACCOUNT_MANAGE = "B0501";

    /**
     * �O�������U�֘A�g(�U�֊Ǘ�) �@@
     */
    public static final String FEQ_TRANSFER_MANAGE = "B0502";

    /**
     * �،��S�ۃ��[��(�����J�݊Ǘ�)
     */
    public static final String SECURITY_LOAN_ACCOUNT_OPEN_MANAGE = "B0601";

    /**
     * �،��S�ۃ��[��(�S�ۖ����Ǘ�)
     */
    public static final String SECURITY_LOAN_PRODUCT_MANAGE = "B0602";

    /**
     * �،��S�ۃ��[��(�o���\�z����Ǘ�)
     */
    public static final String SECURITY_LOAN_PAYMENT_TRADING_POWER_CONTROL_MANAGE = "B0603";

    /**
     * ���������i�ڋq�E�����ʎ����~�j �@@
     */
    public static final String EQTYPE_ACCOUNT_PRODUCT_STOP = "C0101";

    /**
     * ���������i�����ݒ�j �@@
     */
    public static final String EQTYPE_PRODUCT_SETTING = "C0102";

    /**
     * ���������i�������Ɖ�j �@@
     */
    public static final String EQTYPE_ORDER_EXEC_INQUIRY = "C0103";

    /**
     * ���������i����O�����j �@@
     */
    public static final String EQTYPE_OFF_FLOOR_ORDER = "C0104";

    /**
     * ���M�i�����Ǘ��j �@@
     */
    public static final String ADMIN_MUTUAL_CONDITIONS = "C0201";

    /**
     * ���M�i�J�����_�[�Ǘ��j �@@
     */
    public static final String ADMIN_MUTUAL_FRGNCAL = "C0202";

    /**
     * ���M�E�ݓ��i�������Ɖ�j �@@
     */
    public static final String XBMF_XBRUITO_ORDER_EXEC_INQUIRY = "C0203";

    /**
     * �ݓ��i�����Ǘ��j�@@
     */
    public static final String ADMIN_RUITO_TRADE_STOP = "C0204";

    /**
     * ���M�i�]�͒����j
     */
    public static final String ADMIN_MUTUAL_TRADING_POWER_ADJUST = "C0205";

    /**
     * �敨�E�n�o�i�������Ɖ�j �@@
     */
    public static final String XBIFO_ORDER_EXEC_INQUIRY = "C0301";

    /**
     * �O���i��ב֓o�^�j �@@
     */
    public static final String FEQ_BASE_EXCHANGE_REGIST = "C0401";
    
    /**
     * �O���i�������Ǘ��j
     */
    public static final String FEQ_ORDER_EXEC_MANAGE = "C0402";
    
    /**
     * �O���i�����Ǘ��j
     */
    public static final String FEQ_PRODUCT_MANAGE = "C0403";
    
    /**
     * �O���i���n�萔���Ǘ��j
     */
    public static final String FEQ_LOCATION_COMMISSION_MANAGE = "C0404";
    
    /**
     * �O���i�J�����_�[�Ǘ��j
     */
    public static final String FEQ_CALENDAR_MANAGE = "C0405";
    
    /**
     * �h�o�n�Ǘ��i�V�K�o�^�j �@@
     */
    public static final String ADMIN_IPO_PROD_REG = "C0501";

    /**
     * �h�o�n�Ǘ��i�����Ǘ��j �@@
     */
    public static final String ADMIN_IPO_PROD_OPE = "C0502";

    /**
     * �T�[�r�X���p�Ǘ��i�T�[�r�X�j �@@
     */
    public static final String SRVREGI_SERVICE = "C0601";

    /**
     * �T�[�r�X���p�Ǘ��i�ڋq�j �@@
     */
    public static final String SRVREGI_ACCOUNT = "C0602";

    /**
     * �T�[�r�X���p�Ǘ��i�O���A�g�j
     */
    public static final String SRVREGI_OTHERORG = "C0603";

    /**
     * �|�C���g�V�X�e���i�|�C���g�����j �@@
     */
    public static final String POINT_EXCHANGE = "C0701";

    /**
     * �|�C���g�V�X�e���i�ڋq�ʃ|�C���g�Ǘ��j �@@
     */
    public static final String POINT_MANAGE_BY_CUSTOMER = "C0702";

    /**
     * �|�C���g�V�X�e���i�|�C���g�ꊇ�����j �@@
     */
    public static final String POINT_PACKAGE_ADJUST = "C0703";

    /**
     * �|�C���g�V�X�e���i�i�i�Ǘ��j �@@
     */
    public static final String POINT_PREMIUM_MANAGE = "C0704";

    /**
     * ����Ǘ��i���O�C���j �@@
     */
    public static final String TRADE_MANAGEMENT_LOGIN = "C0801";

    /**
     * ����Ǘ��i���i�j �@@
     */
    public static final String TRADE_MANAGEMENT_PRODUCT = "C0802";

    /**
     * ����Ǘ��i�s��j
     */
    public static final String TRADE_MANAGEMENT_MARKET = "C0803";

    /**
     * ���������Ǘ�
     */
    public static final String ORDER_COUNT_MANAGEMENT = "C0804";

    /**
     * ���̑������Ǘ�
     */
    public static final String OTHER_ORDER_COUNT_MANAGEMENT = "C0805";

    /**
     * �萔���Ǘ� �@@
     */
    public static final String ACCINFO_COMMISSION = "C0901";

    /**
     * �n�C�p�[�{�b�N�X�萔���Ǘ� �@@
     */
    public static final String ACCINFO_HYPERBOX_COMMISSION = "C0902";

    /**
     * �萔�������L�����y�[���Ǘ�
     */
    public static final String ACCINFO_COMMISSION_DISCOUNT_CAMPAIGN = "C0903";

    /**
     * �t�����g�����i�ʒm�����Q�Ɓj �@@
     */
    public static final String ADMIN_FRONT_NOTICE_HIST_REF = "C1001";

    /**
     * �t�����g�����i�����o�H�ؑցj�@@
     */
    public static final String ADMIN_FRONT_ROUTE_SWITCH = "Z0201";

    /**
     * �Ǘ��ҊǗ� �@@
     */
    public static final String ADMINMC_ADMINISTRATOR = "D0101";

    /**
     * �Ǘ��Ҍ����Ǘ� �@@
     */
    public static final String ADMINMC_PERMISSION = "D0102";

    /**
     * �I�y���[�^�Ǘ�
     */
    public static final String ADMINMC_OPERATOR = "D0201";

    /**
     * �敨�E�n�o�i�g���K�[�����Ɖ�j
     */
    public static final String TRIGGER_ORDER_INQUIRY = "C0302";

    /**
     * �敨�E�n�o�i�g���K�[�����蓮�����j
     */
    public static final String TRIGGER_ORDER_HANDLER_SEND = "C0303";

    /**
     * �敨�E�n�o�i�蓮�����j
     */
    public static final String MANUAL_EXPIRE = "C0304";

    /**
     * C0105�F���������i�g���K�[�����Ɖ�j
     */
    public static final String DOMESTIC_EQUITY_TRIGGER_ORDER_INQUIRY = "C0105";

    /**
     * C0106�F���������i�g���K�[�����蓮�����j
     */
    public static final String DOMESTIC_EQUITY_TRIGGER_ORDER_MANUAL = "C0106";

    /**
     * C0806:�g���K�[�����蓮����
     */
    public static final String TRIGGER_ORDER_MANUAL_EXPIRE = "C0806";

    /**
     * Z0101:�L���[�e�[�u���X�e�[�^�X�X�V
     */
    public static final String HOST_STATUS_UPDATE = "Z0101";

    /**
     * C0807:�g���K�[�����戵��~
     */
    public static final String TRIGGER_ORDER_TRADING_STOP = "C0807";

    /**
     * C0808:��ב֓o�^
     */
    public static final String BASE_FX_RATE_REG = "C0808";
    
    /**
     * B0103:�o�[�`�������������A�b�v���[�h
     */
    public static final String VIRTUAL_ACC_CASHIN_UPLOAD = "B0103";

    /**
     * C0107:���������i�蓮�����j
     */
    public static final String DOMESTIC_EQUITY_MANUAL_EXPIRE = "C0107";

    /**
     * C0108:���������i�������ρj
     */
    public static final String DOMESTIC_EQUITY_FORCED_SETTLE = "C0108";

    /**
     * C0109:���������i�o�����́E�o������j
     */
    public static final String DOMESTIC_EQUITY_EXEC_INPUT_CANCEL = "C0109";

    /**
     * Z0102:�����P�ʃe�[�u���X�e�[�^�X�X�V
     */
    public static final String ORDER_UNIT_UPDATE = "Z0102";

    /**
     * Z0103:�f�[�����g���K�[�e�[�u���X�e�[�^�X�X�V
     */
    public static final String DAEMON_TRIGGER_UPDATE = "Z0103";

    /**
     * Z0104:�A�b�v���[�h�e�[�u���I�������X�V
     */
    public static final String UPLOAD_END_TIMESTAMP_UPDATE = "Z0104";

    /**
     * C1101:���i�����Ǘ��j
     */
    public static final String BOND_PRODUCT_MANAGE = "C1101";

    /**
     * C1102:���i�����́j
     */
    public static final String BOND_EXECUTE_INPUT = "C1102";

    /**
     * C1103:���i�������Ɖ�j
     */
    public static final String BOND_ORDER_EXECUTE_REFERENCE = "C1103";

    /**
     * C1104:���i���ύX�A������j
     */
    public static final String BOND_EXECUTE_MODIFY_CANCEL = "C1104";

    /**
     * F0101:�R���󋵏Ɖ�
     */
    public static final String EXAMINATION_STATUS_REFERENCE = "F0101";

    /**
     * 
     * G0101:�����@@��t�{���o�^
     */
    public static final String FPT_REG = "G0101";

    /**
     * G0102:�����@@��t�{���o�^�Ɖ�
     */
    public static final String FPT_REGIST_INQUIRY = "G0102";

    /**
     * G0103:�����@@��t�{�������Ɖ�
     */
    public static final String FPT_HISTORY_INQUIRY = "G0103";

    /**
     * G0104:�����@@��t�{���A�b�v���[�h
     */
    public static final String FPT_UPLOAD = "G0104";

    /**
     * G0105:���ʖ������������O�A�E�g
     */
    public static final String FPT_FORCE_LOGOUT = "G0105";

    /**
     * C1301:IP�ʃ��O�C���񐔈ꗗ
     */
    public static final String IP_LOGIN_FREQUENCY_LIST = "C1301";

    /**
     * A0404:�����J�ݎ��������f�[�^�폜
     */
    public static final String ACC_OPEN_APPLY_DATA_DEL = "A0404";

    /**
     * C0110:���������i���ӏ��Ɖ�j
     */
    public static final String DOMESTIC_EQUITY_ATTENTION_INFO_REFERENCE = "C0110";

    /**
     * C0305:�؋����s���󋵏Ɖ�
     */
    public static final String DEPOSIT_SHORTAGE_REFERENCE = "C0305";
}@
