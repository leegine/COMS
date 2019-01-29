head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力停止区分Def(WEB3TPTradingStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/28 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * (取引余力停止区分Def)
 */
public interface WEB3TPTradingStopDivDef
{
    /**
     * (取引可能 or 余力可)
     */
    public static final String TRADING_OK = "0";

    /**
     * (取引停止 or 余力不可)
     */
    public static final String TRADING_STOP = "1";

}
@
