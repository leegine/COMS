head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerErrorDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����]�̓G���[�敪Def(WEB3TPTradingPowerErrorDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 nakazato(ACT) �V�K�쐬
                   2006/09/11 ���G�� (���u) ���f��No.008
Revesion History : 2007/09/26 �И��� (���u) ���f��No.193
Revesion History : 2008/12/11 ���� (���u) ���f��No.379
*/
package webbroker3.tradingpower.define;

/**
 * (����]�̓G���[�敪Def)
 */
public interface WEB3TPTradingPowerErrorDivDef
{

    /**
     * (�a����s���G���[)
     */
    public static final String LACK_ACCOUNT_BALANCE = "1";

    /**
     * (��K���G���[)
     */
    public static final String MARGIN_SEC_ERROR = "2";

    /**
     * (�����~�G���[)
     */
    public static final String TRADING_STOP_ERROR = "3";

    /**
     * (���̑����i���t�]�̓G���[)
     */
    public static final String OTHER_TRADING_STOP_ERROR = "4";

    /**
     * (�M�p�V�K���]�͒�~�G���[)
     */
    public static final String MARGIN_OPEN_POSITION_STOP_ERROR = "5";

    /**
     * (�o���]�͒�~�G���[)
     */
    public static final String PAYMENT_STOP_ERROR = "6";

    /**
     * (�ۏ؋��s���G���[)
     */
    public static final String LACK_MARGIN_POWER = "7";

    /**
     * (�i���S�ہj�a����s���G���[)
     */
    public static final String INC_DEPOSIT_LACK_ACCOUNT_BALANCE = "8";

    /**
     * (�i���S�ہj�ۏ؋��s���G���[)
     */
    public static final String INC_DEPOSIT_LACK_MARGIN_POWER = "9";
    
    /**
     * (�a����S�ۏo���]�͒�~�G���[)
     */
    public static final String CASH_DEPOSIT_PAYMENT_STOP_ERROR = "10";
    
    /**
     * (�،��S�ۃ��[�����z���b�N�G���[)
     */
    public static final String SECURITY_DEPOSIT_LOAN_LOCK_ERROR = "11";

    /**
     * (����ۏ؋���L�����߃G���[)
     */
    public static final String RECEIPT_DEPOSIT_RATE_OVER_ERROR = "12";
}
@
