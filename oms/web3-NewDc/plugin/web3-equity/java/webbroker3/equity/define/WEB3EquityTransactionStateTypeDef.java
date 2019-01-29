head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityTransactionStateTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����󋵋敪 �萔��`�C���^�t�F�C�X(WEB3EquityTransactionStateTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/27 �Ջ`�g(���u) �쐬
*/
package webbroker3.equity.define;

/**
 * @@author �Ջ`�g
 * @@version 1.0
 */
public interface WEB3EquityTransactionStateTypeDef {

    /**
     * ----------------------��t�敪:��t����------------------------------------------------
     */
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �����l
     * 000:�����󋵋敪
     */
    public final static String NOT_PROMISE_INITIAL_VALUE = "000";

    /**
     * ����ԋ敪 �����, ���������E����敪 �����
     * 001:�����󋵋敪
     */
    public final static String NOT_PROMISE_CANCELING = "001";

    /**
     * ����ԋ敪 �����, ���������E����敪 �ꕔ�������
     * 002:�����󋵋敪
     */
    public final static String NOT_PROMISE_PART_CANCELED = "002";

    /**
     * ����ԋ敪 �����, ���������E����敪 �S���������
     * 003:�����󋵋敪
     */
    public final static String NOT_PROMISE_CANCELED = "003";

    /**
     * ����ԋ敪 �����, ���������E����敪 ������s
     * 004:�����󋵋敪
     */
    public final static String NOT_PROMISE_CANCEL_ERROR = "004";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 ������
     * 005:�����󋵋敪
     */
    public final static String NOT_PROMISE_CHANGING = "005";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �ꕔ��������
     * 006:�����󋵋敪
     */
    public final static String NOT_PROMISE_PARTIALLY_CHANGED = "006";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �S����������
     * 007:�����󋵋敪
     */
    public final static String NOT_PROMISE_CHANGED = "007";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �������s
     * 008:�����󋵋敪
     */
    public final static String NOT_PROMISE_CHANGE_ERROR = "008";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �G���[
     * 009:�����󋵋敪
     */
    public final static String NOT_PROMISE_ERROR = "009";    

    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 �����----------------------------------------
     */
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �����l
     * 100:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_INITIAL_VALUE = "100";

    /**
     * ����ԋ敪 �����, ���������E����敪 �����
     * 101:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCELING = "101";

    /**
     * ����ԋ敪 �����, ���������E����敪 �ꕔ�������
     * 102:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_PART_CANCELED = "102";

    /**
     * ����ԋ敪 �����, ���������E����敪 �S���������
     * 103:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCELED = "103";

    /**
     * ����ԋ敪 �����, ���������E����敪 ������s
     * 104:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CANCEL_ERROR = "104";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 ������
     * 105:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGING = "105";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �ꕔ��������
     * 106:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_PARTIALLY_CHANGED = "106";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �S����������
     * 107:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGED = "107";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �������s
     * 108:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_CHANGE_ERROR = "108";
    
    /**
     * ����ԋ敪 �����, ���������E����敪 �G���[
     * 109:�����󋵋敪
     */
    public final static String NOT_NAN_NOT_PROMISE_ERROR = "109";    

    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 �ꕔ����----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 110:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_INITIAL_VALUE = "110";

    /**
     * ���������E����敪 �����
     * 111:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCELING = "111";

    /**
     * ���������E����敪 �ꕔ�������
     * 112:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_PART_CANCELED = "112";

    /**
     * ���������E����敪 �S���������
     * 113:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCELED = "113";

    /**
     * ���������E����敪 ������s
     * 114:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CANCEL_ERROR = "114";
    
    /**
     * ���������E����敪 ������
     * 115:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGING = "115";
    
    /**
     * ���������E����敪 �ꕔ��������
     * 116:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_PARTIALLY_CHANGED = "116";
    
    /**
     * ���������E����敪 �S����������
     * 117:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGED = "117";
    
    /**
     * ���������E����敪 �������s
     * 118:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_CHANGE_ERROR = "118";
    
    /**
     * ���������E����敪 �G���[
     * 119:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_COMPLETE_ERROR = "119";    


    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 �S������----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 120:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_INITIAL_VALUE = "120";

    /**
     * ���������E����敪 �����
     * 121:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCELING = "121";

    /**
     * ���������E����敪 �ꕔ�������
     * 122:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_PART_CANCELED = "122";

    /**
     * ���������E����敪 �S���������
     * 123:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCELED = "123";

    /**
     * ���������E����敪 ������s
     * 124:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CANCEL_ERROR = "124";
    
    /**
     * ���������E����敪 ������
     * 125:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGING = "125";
    
    /**
     * ���������E����敪 �ꕔ��������
     * 126:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_PARTIALLY_CHANGED = "126";
    
    /**
     * ���������E����敪 �S����������
     * 127:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGED = "127";
    
    /**
     * ���������E����敪 �������s
     * 128:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_CHANGE_ERROR = "128";
    
    /**
     * ���������E����敪 �G���[
     * 129:�����󋵋敪
     */
    public final static String NOT_NAN_ALL_COMPLETE_ERROR = "129";    


    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 ����----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 130:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_INITIAL_VALUE = "130";

    /**
     * ���������E����敪 �����
     * 131:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CANCELING = "131";

    /**
     * ���������E����敪 �ꕔ�������
     * 132:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_PART_CANCELED = "132";

    /**
     * ���������E����敪 �S���������
     * 133:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CANCELED = "133";

    /**
     * ���������E����敪 ������s
     * 134:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CANCEL_ERROR = "134";
    
    /**
     * ���������E����敪 ������
     * 135:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CHANGING = "135";
    
    /**
     * ���������E����敪 �ꕔ��������
     * 136:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_PARTIALLY_CHANGED = "136";
    
    /**
     * ���������E����敪 �S����������
     * 137:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CHANGED = "137";
    
    /**
     * ���������E����敪 �������s
     * 138:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_CHANGE_ERROR = "138";
    
    /**
     * ���������E����敪 �G���[
     * 139:�����󋵋敪
     */
    public final static String NOT_NAN_LAPSE_ERROR = "139";    


    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 �ꕔ����----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 140:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_INITIAL_VALUE = "140";

    /**
     * ���������E����敪 �����
     * 141:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCELING = "141";

    /**
     * ���������E����敪 �ꕔ�������
     * 142:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_PART_CANCELED = "142";

    /**
     * ���������E����敪 �S���������
     * 143:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCELED = "143";

    /**
     * ���������E����敪 ������s
     * 144:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CANCEL_ERROR = "144";
    
    /**
     * ���������E����敪 ������
     * 145:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGING = "145";
    
    /**
     * ���������E����敪 �ꕔ��������
     * 146:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_PARTIALLY_CHANGED = "146";
    
    /**
     * ���������E����敪 �S����������
     * 147:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGED = "147";
    
    /**
     * ���������E����敪 �������s
     * 148:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_CHANGE_ERROR = "148";
    
    /**
     * ���������E����敪 �G���[
     * 149:�����󋵋敪
     */
    public final static String NOT_NAN_ONE_LAPSE_ERROR = "149";    

    /**
     * ----------------------��t�敪:��t��--------����ԋ敪 �����----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 150:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_INITIAL_VALUE = "150";

    /**
     * ���������E����敪 �����
     * 151:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCELING = "151";

    /**
     * ���������E����敪 �ꕔ�������
     * 152:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_PART_CANCELED = "152";

    /**
     * ���������E����敪 �S���������
     * 153:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCELED = "153";

    /**
     * ���������E����敪 ������s
     * 154:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CANCEL_ERROR = "154";
    
    /**
     * ���������E����敪 ������
     * 155:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGING = "155";
    
    /**
     * ���������E����敪 �ꕔ��������
     * 156:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_PARTIALLY_CHANGED = "156";
    
    /**
     * ���������E����敪 �S����������
     * 157:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGED = "157";
    
    /**
     * ���������E����敪 �������s
     * 158:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_CHANGE_ERROR = "158";
    
    /**
     * ���������E����敪 �G���[
     * 159:�����󋵋敪
     */
    public final static String NOT_NAN_PROMISE_CANCEL_ERROR = "159";    

    /**
     * ----------------------��t�敪:��t�G���[--------����ԋ敪 �����----------------------------------------
     */
    
    /**
     * ���������E����敪 �����l
     * 200:�����󋵋敪
     */
    public final static String NOT_NAN_EXEC_TYPE_ERROR = "200";

    
}
@
