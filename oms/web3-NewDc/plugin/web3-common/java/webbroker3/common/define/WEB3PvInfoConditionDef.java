head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�������ԍ�(WEB3PvInfoConditionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ������(sinocom) �V�K�쐬
                   2005/10/07 ��  �(sinocom) �ǉ�
                   2006/5/22 ������(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo012
                   2006/9/13 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo013
                   2007/2/26 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo014
Revesion History : 2007/7/13 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo015
Revesion History : 2007/9/13 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo016
Revesion History : 2007/12/10 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo017
Revesion History : 2008/02/19 �h�C(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύX�E�c�a���C�A�E�gNo019
Revesion History : 2008/10/06 ������(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύXNo107
Revesion History : 2008/10/06 ������(���u) �v���C�x�[�g�C���t�H���[�V�����̎d�l�ύXNo110
*/
package webbroker3.common.define;

/** 
 * �\�������ԍ�
 * @@author ������
 * @@version 1.0
 */
public interface WEB3PvInfoConditionDef
{

    /**
     * 0000�F�_�C���N�g�w��@@�@@�@@�@@�@@  �@@�@@
     */
    public final static String DIRECT_ASSIGN = "0000";

    /**
     * 1001:������������&�M�p�����J�� �@@�@@
     */
    public final static String DEPOSIT_REQUEST_MARGIN_ACC_OPEN = "1001";

    /**
     * 1002:������������&�M�p�������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static String DEPOSIT_REQUEST_MARGIN_ACC_CLOSE = "1002";

    /**
     * 1003���֋������@@�@@�@@�@@  �@@�@@
     */
    public final static String ADVANCE_GENERATION = "1003";

    /**
     * 1004:���֋����с@@�@@�@@�@@  �@@�@@
     */
    public final static String ADVANCE_RESULTS = "1004";

    /**
     * 1005:�؋����s���@@�@@�@@�@@  �@@�@@
     */
    public final static String IFO_DEPOSIT_SHORTAGE = "1005";

    /**
     * 1006:���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL �@@�@@�@@�@@  �@@�@@
     */
    public final static String SETTLE_BEF_AMONTH_CONTRACT_HAS = "1006";

    /**
     * 1007:���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL�@@�@@�@@  �@@�@@
     */
    public final static String SETTLE_BEF_AWEEK_CONTRACT_HAS = "1007";

    /**
     * 1008:�M�p�����J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static String MARGIN_ACCOUNT_OPEN = "1008";

    /**
     * 1009:�M�p�������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static String MARGIN_ACCOUNT_CLOSE = "1009";

    /**
     * 1010:�I�v�V���������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static String OPTION_ACCOUNT_OPEN = "1010";

    /**
     * 1011:�����ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String STOCKS_HAS = "1011";

    /**
     * 1012:�M�p���ʕۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String MARGIN_CONTRACT_HAS = "1012";

    /**
     * 1013:���M�ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String MUTUAL_HAS = "1013";

    /**
     * 1014:�ݓ��ۗL�@@�@@�@@  �@@�@@
     */
    public final static String RUITO_HAS = "1014";

    /**
     * 1015:�I�v�V�������ʕۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String OPTION_CONTRACT_HAS = "1015";

    /**
     * 1016:�~�j���ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String MINI_STOCK_HAS = "1016";

    /**
     * 1017:�敨�ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static String FUTURE_HAS = "1017";

    /**
     * 1018:�a����L��&�،��c�����@@�@@�@@�@@  �@@�@@
     */
    public final static String ACCOUNT_BAL_NON_SECURITIES_BAL = "1018";

    /**
     * 1019:�a�������&�،��c�����@@�@@�@@  �@@�@@
     */
    public final static String NON_ACCOUNT_BAL_NON_SECURITIES_BAL = "1019";

    /**
     * 1020:�����E�M�p���������i�����j �@@�@@�@@�@@  �@@�@@
     */
    public final static String STOCK_MARGIN_ORDER_GENERATION_TODAY = "1020";

    /**
     * 1021:�����E�M�p���������i�����j �@@�@@�@@�@@  �@@�@@
     */
    public final static String STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY = "1021";

    /**
     * 1022:�����E�M�p��蔭���@@�@@�@@�@@  �@@�@@
     */
    public final static String STOCK_MARGIN_EXECUTE_GENERATION = "1022";

    /**
     * 1023:�S�ڋq�@@�@@�@@�@@  �@@�@@
     */
    public final static String ALL_ACCOUNT = "1023";

    /**
     * 1024:���[���A�h���X���o�^�@@�@@�@@�@@  �@@�@@
     */
    public final static String MAIL_ADDRESS_NON_REGIST = "1024";

    /**
     * 1025:IPO���I�@@�@@�@@�@@  �@@�@@
     */
    public final static String IPO_ELECTION = "1025";

    /**
     * 1026:IPO�J�グ���I�@@�@@�@@�@@  �@@�@@
     */
    public final static String IPO_RETRY_ELECTION = "1026";

    /**
     * 1027:�����~�@@�@@�@@�@@  �@@�@@
     */
    public final static String TRADE_SUSPENSION = "1027";

    /**
     * 1028:���O�C���p�X���[�h�ύX�v
     */
    public final static String LOGIN_PASSWORD_CHANGE = "1028";
    
    /**
     * 1029:�O���،������J��
     */
    public final static String FEQ_ACCOUNT_OPEN = "1029";

    /**
     * 1030:�O���ۗL
     */
    public final static String FEQ_HAS = "1030";

    /**
     * 1031:�O�����������i�����j
     */
    public final static String FEQ_ORDER_GENERATION_TODAY = "1031";

    /**
     * 1032:�O�����������i�����j
     */
    public final static String FEQ_ORDER_GENERATION_NEXT_DAY = "1032";

    /**
     * 1033:�O����蔭��
     */
    public final static String FEQ_EXECUTE_GENERATION = "1033";

    /**
     * 1034�F���o�C����p�����J��
     */
    public final static String ONLY_MOBILE_OPEN = "1034";

    /**
     * 1035�F���o�C����p�������J��
     */
    public final static String ONLY_MOBILE_NOT_OPEN = "1035";

    /**
     * 1036�F�،��S�ۃ��[�������J��
     */
    public final static String SECURED_LOAN_ACCOUNT_OPEN = "1036";

    /**
     * 1037�F���ʌ�t�����11�����o��
     */
    public final static String FROM_DELIVERY_DATE_11MONTH = "1037";

    /**
     * 1038�FPTS�����J��
     */
    public final static String PTS_ACCOUNT_OPEN = "1038";

    /**
     * 1039�FPTS�������J��
     */
    public final static String PTS_ACCOUNT_CLOSE = "1039";
    
    /**
     * 1041�F20������1����30������5���ȉ�
     */
    public final static String BREAK_1DAY_AND_5DAY_DOWN = "1041";

    /**
     * 1042�F20������1����30������6��
     */
    public final static String BREAK_1DAY_AND_6DAY = "1042";

    /**
     * 1043�F20������2����30������6���ȉ�
     */
    public final static String BREAK_2DAY_AND_6DAY_DOWN = "1043";

    /**
     * 1044�F20������3���ȏ�
     */
    public final static String BREAK_3DAY_OVER = "1044";

    /**
     * 1045�F30������2�`4��
     */
    public final static String BREAK_2TO4DAY = "1045";

    /**
     * 1046�F30������5��
     */
    public final static String BREAK_5DAY = "1046";

    /**
     * 1047�F30������6��
     */
    public final static String BREAK_6DAY = "1047";

    /**
     * 1048�F30������7���ȏ�
     */
    public final static String BREAK_7DAY_OVER = "1048";

    /**
     * 1049�F�ꕔ�o����~
     */
    public final static String PART_PAYMENT_STOP = "1049";

    /**
     * 1050�F�S�z�o����~
     */
    public final static String FULL_PAYMENT_STOP = "1050";

    /**
     * 1051�F�萔�������L�����y�[��
     */
    public final static String COMMISSION_DISCOUNT_CAMPAIGN = "1051";

    /**
     * 1052�F�@@�a�������
     */
    public final static String FROM_DEPOSIT_REQUEST = "1052";

    /**
     * 1053�F�@@�Ǐؐ���
     */
    public final static String ADDITIONAL_DEPOSIT_REQUEST = "1053";

    /**
     * 1054�F�@@�s�����������M�p�������J��
     */
    public final static String SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE = "1054";

    /**
     * 1055�F�@@�s�����������M�p�����J��
     */
    public final static String SHORT_FALL_GENERATION_MARGIN_ACC_OPEN = "1055";

    /**
     * 1056�F�@@��ꐅ���Ǐؔ���
     */
    public final static String FIRST_ADDITIONAL_DEPOSIT_OCCUR = "1056";

    /**
     * 1057�F�@@��񐅏��Ǐؔ���
     */
    public final static String SECOND_ADDITIONAL_DEPOSIT_OCCUR = "1057";

    /**
     * 1058�F�@@CFD�����J��
     */
    public final static String CFD_ACCOUNT_OPEN = "1058";

    /**
     * 1059�F�@@CFD�������J��
     */
    public final static String CFD_ACCOUNT_CLOSE = "1059";
}
@
