head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMTradeStopDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Trademanagement Trade Stop Div Def(WEB3AdminTMTradeStopDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/
package webbroker3.trademanagement.define;

/**
 * Trademanagement Trade Stop Div Def<BR>
 * WEB3AdminTMTradeStopDivDef interface<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3AdminTMTradeStopDivDef
{
    /**
     *  0：取引可能
     */
    public final static String NORMAL  = "0";

    /**
     * 1：取引停止
     */
    public final static String SUSPENTION = "1";

    /**
     * 2：　@取扱不可
     */
    public final static String DISABLE = "2";
}@
