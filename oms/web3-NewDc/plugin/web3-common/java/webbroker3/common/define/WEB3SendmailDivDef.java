head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SendmailDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M���[���敪  �萔��`�N���X(WEB3SendmailDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 ���o�� (SinoCom) �V�K�쐬
Revesion History : 2005/07/05 �Г� (���u) ������胁�[���̔ԍ��ƒ�`�����C��
                                          �؋����AIPO�ƊO���Ȃǂ̒�`��ǉ�
Revesion History : 2009/01/06 ��іQ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g668
Revesion History : 2009/02/12 ��іQ (���u) ���ʎd�l�ύX�E�c�a���C�A�E�g672
*/
package webbroker3.common.define;

/**
 * ���M���[���敪  �萔���`����B
 *
 * @@author ���o��
 * @@version 1.0
 */
public interface WEB3SendmailDivDef
{

    /**
     * 01�F�T�[�r�X���p�@@
     */
    public final static String SRVREGI = "01";

    /**
     * 0101�F�T�[�r�X���p�\���m�F���[���@@
     */
    public final static String SRVREGI_CONFIRM_MAIL = "0101";

    /**
     * 0102�F�T�[�r�X���p�������[��
     */
    public final static String SRVREGI_TERM_MAIL = "0102";

    /**
     * 0103�F�T�[�r�X���p�������[�����M�҈ꗗ���[��
     */
    public final static String SRVREGI_TERM_MAILSENDER_LIST_MAIL = "0103";

    /**
     * 02�F�����J��
     */
    public final static String ACCOPEN = "02";

    /**
     * 0201�F�����J�ݐ\�����[��
     */
    public final static String ACCOPEN_APPLICATION_MAIL = "0201";

    /**
     * 0202�F�����J�݊������[��
     */
    public final static String ACCOPEN_COMPLETE_MAIL = "0202";

    /**
     * 03�F�⍇���Ǘ�
     */
    public final static String FAQ = "03";

    /**
     * 0301�F�⍇�����[��
     */
    public final static String FAQ_INPUT = "0301";
    
	/**
	 * 0302�F�⍇����t���[��
	 */
	public final static String FAQ_COMPLETE = "0302";

    /**
     * 04�F���q�l���
     */
    public final static String ACCINFO = "04";

    /**
     * 0401�F�ē����[��
     */
    public final static String ACCINFO_GUIDE_MAIL = "0401";

    /**
     * 05�F�A���Ǘ�
     */
    public final static String INFORM = "05";

    /**
     * 0501�F�A����ʁF�O�P�p���[��
     */
    public final static String INFORM_01_MAIL = "0501";

    /**
     * 0502�F�A����ʁF�O�Q�p���[��
     */
    public final static String INFORM_02_MAIL = "0501";

    /**
     * 0503�F�A����ʁF�O�R�p���[��
     */
    public final static String INFORM_03_MAIL = "0503";

    /**
     * 0504�F�A����ʁF�O�S�p���[��
     */
    public final static String INFORM_04_MAIL = "0504";

    /**
     * 06�F�؋����Ǘ�
     */
    public final static String DEPOSIT = "06";

    /**
     * 0601�F�؋������[��
     */
    public final static String DEPOSIT_NOTIFY = "0601";

    /**
     * 06011�F�؋����s���̂��m�点���[��
     */
    public final static String DEPOSIT_SHORTAGE_NOTIFY = "06011";

    /**
     * 06012�F�؋����s���̂��m�点�y�ы������Δ������s���[��
     */
    public final static String DEPOSIT_SHORTAGE_COERCION_OPPOSITION = "06012";

    /**
     * 06013�F�������Δ������s���[��
     */
    public final static String DEPOSIT_COERCION_OPPOSITION = "06013";

    /**
     * 0602�F�؋����s���ڋq�ꗗ���[��
     */
    public final static String DEPOSIT_SHORTAGE_LIST = "0602";

    /**
     * 07�F���o��
     */
    public final static String AIO = "07";

    /**
     * 0701�F�����A�����[��
     */
    public final static String AIO_DEPOSIT_INFORM = "0701";

    /**
     * 08�FIPO
     */
    public final static String IPO = "08";
    
    /**
     * 0801�F���I���ʃ��[��
     */
    public final static String IPO_LOT_RESULT = "0801";

    /**
     * 08010001�F���I���ʃ��[���i���I�j
     */
    public final static String IPO_ELECTION = "08010001";

    /**
     * 08010002�F���I���ʃ��[���i�⌇�j
     */
    public final static String IPO_SUPPLEMENT = "08010002";

    /**
     * 08010003�F���I���ʃ��[���i���I�j
     */
    public final static String IPO_DEFEAT = "08010003";

    /**
     * 09�F�����~�j����
     */
    public final static String MINI_STOCK = "09";

    /**
     * 0901�F�����~�j����
     */
    public final static String MINI_EQUITY = "0901";

    /**
     * 10�F�O������
     */
    public final static String FEQ = "10";
    
    /**
     * 1001�F�V�K������t���[��(�O������)
     */
    public final static String FEQ_ORDER_ACCEPT = "1001";

    /**
     * 1002�F����������t���[��(�O������)
     */
    public final static String FEQ_ORDER_CHANGE = "1002";

    /**
     * 1003�F���������t���[��(�O������)
     */
    public final static String FEQ_ORDER_CANCEL = "1003";

    /**
     * 20�F����
     */
    public final static String EQUITY = "20";

    /**
     * 2001�F���������
     */
    public final static String EQUITY_ORDER_EXEC_STATUS = "2001";

    /**
     * 20010�F��������胁�[��
     */
    public final static String EQUITY_ORDER_UNEXECUTED = "20010";

    /**
     * 20011�F�����ꕔ��胁�[��
     */
    public final static String EQUITY_ORDER_PARTIALLY_EXECUTED = "20011";

    /**
     * 20012�F������胁�[��
     */
    public final static String EQUITY_ORDER_EXECUTED = "20012";

    /**
     * 20013�F�����������[��
     */
    public final static String EQUITY_ORDER_CLOSE = "20013";

    /**
     * 21�F�敨
     */
    public final static String FUTURES = "21";

    /**
     * 2101�F�敨�����
     */
    public final static String FUTURES_ORDER_EXEC_STATUS = "2101";

    /**
     * 21010�F�敨����胁�[��
     */
    public final static String FUTURES_ORDER_UNEXECUTED = "21010";

    /**
     * 21011�F�敨�ꕔ��胁�[��
     */
    public final static String FUTURES_ORDER_PARTIALLY_EXECUTED = "21011";

    /**
     * 21012�F�敨��胁�[��
     */
    public final static String FUTURES_ORDER_EXECUTED = "21012";

    /**
     * 21013�F�敨�������[��
     */
    public final static String FUTURES_ORDER_CLOSE = "21013";

    /**
     * 22�FOP
     */
    public final static String OPTION = "22";

    /**
     * 2201�FOP�����
     */
    public final static String OPTION_ORDER_EXEC_STATUS = "2201";

    /**
     * 22010�FOP����胁�[��
     */
    public final static String OPTION_ORDER_UNEXECUTED = "22010";

    /**
     * 22011�FOP�ꕔ��胁�[��
     */
    public final static String OPTION_ORDER_PARTIALLY_EXECUTED = "22011";

    /**
     * 22012�FOP��胁�[��
     */
    public final static String OPTION_ORDER_EXECUTED = "22012";

    /**
     * 22013�FOP�������[��
     */
    public final static String OPTION_ORDER_CLOSE = "22013";

    /**
     * 31�F���ӏ��ʒm���[��
     */
    public final static String ATTENTION_INFO_NOTIFY = "31";

    /**
     * 3101�F���ӏ��i������j
     */
    public final static String ATTENTION_INFO_SELL_STOP_INFO = "3101";

    /**
     * 3102�F���ӏ��i�����l�����j
     */
    public final static String TTENTION_INFO_LIMIT_RANGE_INFO = "3102";

    /**
     * 3103�F���ӏ��i�t���[�t�H�[�}�b�g�j
     */
    public final static String TTENTION_INFO_FREE_FORMAT = "3103";

    /**
     * 3104�F���ӏ��i�G���[�j
     */
    public final static String TTENTION_INFO_ERROR = "3104";
}
@
