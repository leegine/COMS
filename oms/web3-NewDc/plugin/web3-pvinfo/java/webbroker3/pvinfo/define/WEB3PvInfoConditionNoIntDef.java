head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.09.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoConditionNoIntDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�������ԍ�Integer Values(WEB3PvInfoConditionNoIntDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/07 ���(���u) �V�K�쐬
Revesion History : 2006/05/22 �юu��(���u) �d�l�ύX ���f��063
Revesion History : 2006/09/12 �����F(���u) �d�l�ύX���f��070
Revesion History : 2007/02/27 ����(���u) �d�l�ύX���f��073
Revision History : 2007/07/13 �Ӑ�(���u) �d�l�ύX���f��082
Revision History : 2007/09/13 �g�E�N�|(���u) �d�l�ύX���f��093
Revision History : 2007/12/10 �g�E�N�|(���u) �d�l�ύX���f��094
Revision History : 2008/02/18 �đo�g(���u) �d�l�ύX���f��102
Revision History : 2008/10/07 ���O(���u) �d�l�ύX���f��110
Revision History : 2008/10/07 ���m�a(���u) �d�l�ύX���f��107
*/
package webbroker3.pvinfo.define;

/** 
 * �\�������ԍ�Integer Values
 * @@author ���
 * @@version 1.0
 */
public interface WEB3PvInfoConditionNoIntDef
{

    /**
     * 0000�F�_�C���N�g�w��@@�@@�@@�@@�@@  �@@�@@
     */
    public final static int DIRECT_ASSIGN = 0;

    /**
     * 1001:������������&�M�p�����J�� �@@�@@
     */
    public final static int DEPOSIT_REQUEST_MARGIN_ACC_OPEN = 1001;

    /**
     * 1002:������������&�M�p�������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static int DEPOSIT_REQUEST_MARGIN_ACC_CLOSE = 1002;

    /**
     * 1003���֋������@@�@@�@@�@@  �@@�@@
     */
    public final static int ADVANCE_GENERATION = 1003;

    /**
     * 1004:���֋����с@@�@@�@@�@@  �@@�@@
     */
    public final static int ADVANCE_RESULTS = 1004;

    /**
     * 1005:�؋����s���@@�@@�@@�@@  �@@�@@
     */
    public final static int IFO_DEPOSIT_SHORTAGE = 1005;

    /**
     * 1006:���ϊ����ԋ߁i�ꃖ���O�j�̌��ʕۗL �@@�@@�@@�@@  �@@�@@
     */
    public final static int SETTLE_BEF_AMONTH_CONTRACT_HAS = 1006;

    /**
     * 1007:���ϊ����ԋ߁i��T�ԑO�j�̌��ʕۗL�@@�@@�@@  �@@�@@
     */
    public final static int SETTLE_BEF_AWEEK_CONTRACT_HAS = 1007;

    /**
     * 1008:�M�p�����J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static int MARGIN_ACCOUNT_OPEN = 1008;

    /**
     * 1009:�M�p�������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static int MARGIN_ACCOUNT_CLOSE = 1009;

    /**
     * 1010:�I�v�V���������J�݁@@�@@�@@�@@  �@@�@@
     */
    public final static int OPTION_ACCOUNT_OPEN = 1010;

    /**
     * 1011:�����ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int STOCKS_HAS = 1011;

    /**
     * 1012:�M�p���ʕۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int MARGIN_CONTRACT_HAS = 1012;

    /**
     * 1013:���M�ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int MUTUAL_HAS = 1013;

    /**
     * 1014:�ݓ��ۗL�@@�@@�@@  �@@�@@
     */
    public final static int RUITO_HAS = 1014;

    /**
     * 1015:�I�v�V�������ʕۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int OPTION_CONTRACT_HAS = 1015;

    /**
     * 1016:�~�j���ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int MINI_STOCK_HAS = 1016;

    /**
     * 1017:�敨�ۗL�@@�@@�@@�@@  �@@�@@
     */
    public final static int FUTURE_HAS = 1017;

    /**
     * 1018:�a����L��&�،��c�����@@�@@�@@�@@  �@@�@@
     */
    public final static int ACCOUNT_BAL_NON_SECURITIES_BAL = 1018;

    /**
     * 1019:�a�������&�،��c�����@@�@@�@@  �@@�@@
     */
    public final static int NON_ACCOUNT_BAL_NON_SECURITIES_BAL = 1019;

    /**
     * 1020:�����E�M�p���������i�����j �@@�@@�@@�@@  �@@�@@
     */
    public final static int STOCK_MARGIN_ORDER_GENERATION_TODAY = 1020;

    /**
     * 1021:�����E�M�p���������i�����j �@@�@@�@@�@@  �@@�@@
     */
    public final static int STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY = 1021;

    /**
     * 1022:�����E�M�p��蔭���@@�@@�@@�@@  �@@�@@
     */
    public final static int STOCK_MARGIN_EXECUTE_GENERATION = 1022;

    /**
     * 1023:�S�ڋq�@@�@@�@@�@@  �@@�@@
     */
    public final static int ALL_ACCOUNT = 1023;

    /**
     * 1024:���[���A�h���X���o�^�@@�@@�@@�@@  �@@�@@
     */
    public final static int MAIL_ADDRESS_NON_REGIST = 1024;

    /**
     * 1025:IPO���I�@@�@@�@@�@@  �@@�@@
     */
    public final static int IPO_ELECTION = 1025;

    /**
     * 1026:IPO�J�グ���I�@@�@@�@@�@@  �@@�@@
     */
    public final static int IPO_RETRY_ELECTION = 1026;

    /**
     * 1027:�����~�@@�@@�@@�@@  �@@�@@
     */
    public final static int TRADE_SUSPENSION = 1027;

    /**
     * 1028:���O�C���p�X���[�h�ύX�v
     */
    public final static int LOGIN_PASSWORD_CHANGE = 1028;
    
    /**
     * 1029:�O���،������J��
     */
    public final static int FEQ_ACCOUNT_OPEN = 1029;
    
    /**
     * 1030:�O���ۗL
     */
    public final static int FEQ_HAS = 1030;
    
    /**
     * 1031:�O�����������i�����j
     */
    public final static int FEQ_ORDER_GENERATION_TODAY = 1031;
    
    /**
     * 1032:�O�����������i�����j
     */
    public final static int FEQ_ORDER_GENERATION_NEXT_DAY = 1032;
    
    /**
     * 1033:�O����蔭��
     */
    public final static int FEQ_EXECUTE_GENERATION = 1033;

    /**
     * 1034�F���o�C����p�����J��
     */
    public final static int ONLY_MOBILE_OPEN = 1034;

    /**
     * 1035�F���o�C����p�������J��
     */
    public final static int ONLY_MOBILE_NOT_OPEN = 1035;

    /**
     * 1036�F�،��S�ۃ��[�������J��
     */
    public final static int SECURED_LOAN_ACCOUNT_OPEN = 1036;

    /**
     * 1037�F���ʌ�t�����11�����o��
     */
    public final static int FROM_DELIVERY_DATE_11MONTH = 1037;

    /**
     * 1038�FPTS�����J��
     */
    public final static int PTS_ACCOUNT_OPEN = 1038;

    /**
     * 1039�FPTS�������J��
     */
    public final static int PTS_ACCOUNT_CLOSE = 1039;

    /**
     * 1041:20������1����30������5���ȉ�
     */
    public final static int BREAK_1DAY_AND_5DAY_DOWN = 1041;
    
    /**
     * 1042�F20������1����30������6��
     */
    public final static int BREAK_1DAY_AND_6DAY = 1042;

    /**
     * 1043�F20������2����30������6���ȉ�
     */
    public final static int BREAK_2DAY_AND_6DAY_DOWN = 1043;

    /**
     * 1044�F20������3���ȏ�
     */
    public final static int BREAK_3DAY_OVER = 1044;

    /**
     * 1045�F30������2�`4��
     */
    public final static int BREAK_2TO4DAY = 1045;

    /**
     * 1046�F30������5��
     */
    public final static int BREAK_5DAY = 1046;

    /**
     * 1047�F30������6��
     */
    public final static int BREAK_6DAY = 1047;

    /**
     * 1048�F30������7���ȏ�
     */
    public final static int BREAK_7DAY_OVER = 1048;
    
    /**
     * 1049:�ꕔ�o����~
     */
    public final static int PART_PAYMENT_STOP = 1049;
    
    /**
     * 1050:�S�z�o����~
     */
    public final static int FULL_PAYMENT_STOP = 1050;
    
    /**
     * 1051:�萔�������L�����y�[��
     */
    public final static int ACC_INFO_CAMPAIGN = 1051;

    /**
     * 1054:�s�����������M�p�������J��
     */
    public final static int SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE = 1054;

    /**
     * 1055:�s�����������M�p�����J��
     */
    public final static int SHORT_FALL_GENERATION_MARGIN_ACC_OPEN = 1055;

    /**
     * 1056:��ꐅ���Ǐؔ���
     */
    public final static int FIRST_ADDITIONAL_DEPOSIT_OCCUR = 1056;

    /**
     * 1057:��񐅏��Ǐؔ���
     */
    public final static int SECOND_ADDITIONAL_DEPOSIT_OCCUR = 1057;

    /**
     * 1058�F�@@CFD�����J��
     */
    public final static int CFD_ACCOUNT_OPEN = 1058;

    /**
     * 1059�F�@@CFD�������J��
     */
    public final static int CFD_ACCOUNT_CLOSE = 1059;

}
@
