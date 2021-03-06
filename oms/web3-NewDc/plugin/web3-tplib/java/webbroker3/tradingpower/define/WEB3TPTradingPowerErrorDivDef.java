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
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ζψ]ΝG[ζͺDef(WEB3TPTradingPowerErrorDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/25 nakazato(ACT) VKμ¬
                   2006/09/11 GΜ (u) fNo.008
Revesion History : 2007/09/26 Π±μ (u) fNo.193
Revesion History : 2008/12/11 « (u) fNo.379
*/
package webbroker3.tradingpower.define;

/**
 * (ζψ]ΝG[ζͺDef)
 */
public interface WEB3TPTradingPowerErrorDivDef
{

    /**
     * (aθΰs«G[)
     */
    public static final String LACK_ACCOUNT_BALANCE = "1";

    /**
     * (ρKG[)
     */
    public static final String MARGIN_SEC_ERROR = "2";

    /**
     * (ζψβ~G[)
     */
    public static final String TRADING_STOP_ERROR = "3";

    /**
     * (»ΜΌ€it]ΝG[)
     */
    public static final String OTHER_TRADING_STOP_ERROR = "4";

    /**
     * (MpVK]Νβ~G[)
     */
    public static final String MARGIN_OPEN_POSITION_STOP_ERROR = "5";

    /**
     * (oΰ]Νβ~G[)
     */
    public static final String PAYMENT_STOP_ERROR = "6";

    /**
     * (ΫΨΰs«G[)
     */
    public static final String LACK_MARGIN_POWER = "7";

    /**
     * (iSΫjaθΰs«G[)
     */
    public static final String INC_DEPOSIT_LACK_ACCOUNT_BALANCE = "8";

    /**
     * (iSΫjΫΨΰs«G[)
     */
    public static final String INC_DEPOSIT_LACK_MARGIN_POWER = "9";
    
    /**
     * (aθΰSΫoΰ]Νβ~G[)
     */
    public static final String CASH_DEPOSIT_PAYMENT_STOP_ERROR = "10";
    
    /**
     * (ΨSΫ[ΰzbNG[)
     */
    public static final String SECURITY_DEPOSIT_LOAN_LOCK_ERROR = "11";

    /**
     * (σόΫΨΰθL¦΄ίG[)
     */
    public static final String RECEIPT_DEPOSIT_RATE_OVER_ERROR = "12";
}
@
