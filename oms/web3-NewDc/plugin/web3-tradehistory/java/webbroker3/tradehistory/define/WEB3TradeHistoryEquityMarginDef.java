head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryEquityMarginDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物・信用 (WEB3TradeHistoryEquityMarginDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/02  範慧琴(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 現物・信用”定数定義インタフェイス
 * 
 * @@author 範慧琴
 * @@version 1.0
 */
public interface WEB3TradeHistoryEquityMarginDef
{

    /**
     *10：　@現物
     */
    public final static String EQUITY = "10";
    
    /**
     *11：　@信用
     */
    public final static String MARGIN = "11";

}
@
