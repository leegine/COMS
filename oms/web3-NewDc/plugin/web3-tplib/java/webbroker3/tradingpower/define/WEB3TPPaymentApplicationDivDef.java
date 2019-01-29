head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.50.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentApplicationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込区分定義(WEB3TPPayAutoCalcDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/02 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 出金申込区分
 */
public interface WEB3TPPaymentApplicationDivDef 
{
	
	/**
	 * バイキング手数料
	 */
	public static String BUYKING = "A0";	
	
    /**
     * 出金予約付き投信解約
     */
    public final static String MF_SELL_WITH_CASH_OUT = "mf";    
}
@
