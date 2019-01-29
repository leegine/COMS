head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradeStopFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3TradeStopFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 売買停止FLAG　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3TradeStopFlagDef
{
    /**
     * 0:FLAG OFF 
     */
    public static final String FLAG_OFF = "0";

    /**
     * 1:売買停止
     */
    public static final String TRADE_STOP = "1";
    
    /**
     * 2:売停止
     */
    public static final String SELL_STOP = "2";
    
    /**
     * 3:買停止
     */
    public static final String BUY_STOP = "3";

}
@
