head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingTimeTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t���ԋ敪�@@�萔��`�C���^�t�F�C�X(WEB3TradingTimeType)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 ���� ���D(SRA) �V�K�쐬
Revesion History : 2006/06/29 �h�C ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g386��Ή�
Revesion History : 2007/06/06 �L���E�ĕ� �c�a���C�A�E�g(������ԃe�[�u��)�ɂ��
Revesion History : 2007/07/11 �h�C ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g524��Ή�
Revesion History : 2007/09/14 �ЋŃV�� ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g544��Ή�
Revesion History : 2008/02/01 �h�C ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g605��Ή�
Revesion History : 2008/04/24 �h�C ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�g622��Ή�
Revesion History : 2008/9/24 ������ (���u) �y���ʁz�d�l�ύX�Ǘ��䒠 �c�a���C�A�E�gNo.649
*/
package webbroker3.common.define;

/**
 * ��t���ԋ敪�@@�萔��`�C���^�t�F�C�X�B
 *
 * @@author ���� ���D(SRA)
 * @@version 1.0
 */
public interface WEB3TradingTimeTypeDef
{
    /**
     * ���̑��F00
     */
    public static final String DEFAULT = "00";
    
    /**
     * �����E�M�p�F01
     */
    public static final String EQUITY = "01";
    
    /**
     * IPO�F02
     */
    public static final String IPO = "02";
    
    /**
     * ����O�����F03
     */
    public static final String SALES_OUTSIDE_MARKET = "03";
    
    /**
     * ��p�ۏ؋��F04
     */
    public static final String SUB_GURANTEE_FEE = "04";
    
    /**
     * �����~�j�����F05
     */
    public static final String MINI_STOCK = "05";
    
    /**
     * �����M���F06
     */
    public static final String MUTUAL_FUND = "06";
    
    /**
     * �����e�F07
     */
    public static final String MIDIUM_TERM_GOV_FUND = "07";
    
    /**
     * �l�l�e�i�ݒ�j�F08
     */
    public static final String MMF_SET = "08";
    
    /**
     * �l�l�e�i�ݒ�E���j�F09
     */
    public static final String MMF_SET_CANCEL = "09";
    
    /**
     * �O�������F10
     */
    public static final String FOREIGN_STOCK = "10";
    
    /**
     * �����w���I�v�V�����F11
     */
    public static final String INDEX_FUTURE_OP = "11";
    
    /**
     * �����w���I�v�V�����i��������j�F12
     */
    public static final String INDEX_FUTURE_OP_CHANGE_CANCEL = "12";
    
    /**
     * �؋����U�ցF13
     */
    public static final String MARGIN_TRANSFER = "13";
    
    /**
     * �����F14
     */
    public static final String RECIEPT = "14";
    
    /**
     * �l�q�e�F15
     */
    public static final String MRF = "15";
    
    /**
     * �o���F16
     */
    public static final String PAYMENT = "16";
    
    /**
     * �A�b�v���[�h�i�Ǘ��ҁj�F17
     */
    public static final String UPLOAD = "17";

    /**
     * �����A���F18
     */
    public static final String DEPOSIT_INFORM = "18";

    /**
     * �����E���n�F19
     */
    public static final String SWAP = "19";
    
    /**
     * �،��U�ցA�o�ɒʒm�@@�F20
     */
    public static final String SECURITY_TRANSFER = "20";
    
    /**
     * �|�C���g�V�X�e���F21
     */
    public static final String POINT_SYSTEM = "21";
    
    /**
     * �����J�݁F22
     */
    public static final String ACCOUNT_OPEN = "22";
    
    /**
     * �ב֕ۏ؋��F23
     */
    public static final String EXCHANGE_GUARANTEE = "23";
    
    /**
     * �O�������U�֘A�g�F24
     */
    public static final String FEQ_CON = "24";
    
    /**
     * ���F25
     */
    public static final String BOND = "25";
    
    /**
     * �A�b�v���[�h�I���i�Ǘ��ҁj�F26
     */
    public static final String UPLOAD_DAYLONG = "26";
    
    /**
     * �T�[�r�X���p�F27
     */
    public static final String SRVREGI = "27";
    
    
    /**
     * �V���O���T�C���I���F28
     */
    public static final String SINGLE_SIGN_ON = "28";

    /**
     * SONAR�A�g�i�Ǘ��ҁj�F29
     */
    public static final String SONAR_CON = "29";

    /**
     * 30�F�_�E�����[�h
     */
    public static final String DOWNLOAD = "30";

    /**
     * 31�F�����ʒm�ď���
     */
    public static final String CASHIN_NOTICE_REDEALING = "31";

    /**
     * 32�FIPO���I����(�Ǘ���)
     */
    public static final String IPO_LOT_ADMIN = "32";

    /**
     * 33�F���M�莞��z���t
     */
    public static final String MF_FIXED_BUYING = "33";

    /**
     * 35:�ڋq���`�[�쐬
     */
    public static final String ACCOUNTINFO_VOUCHER_CREATE = "35";

    /**
     * 36:������
     */
    public static final String DOMESTIC_BOND = "36";

    /**
     * 37:�،��S�ۃ��[��
     */
    public static final String SECURITY_LOAN = "37";

    /**
     * 38:�O�������i����j
     */
    public static final String FEQ_CANCEL = "38";

    /**
     * 39:�O�������i�����j
     */
    public static final String FEQ_CHANGE = "39";

    /**
     * 40�FCFD
     */
    public static final String CFD = "40";
}
@
