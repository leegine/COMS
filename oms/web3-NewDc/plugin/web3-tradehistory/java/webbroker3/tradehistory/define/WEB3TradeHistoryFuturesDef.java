head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.44.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryFuturesDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物 (WEB3TradeHistoryFuturesDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  温 顕 法@(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 先物  定数定義インタフェイス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public interface WEB3TradeHistoryFuturesDef
{

    /**
     *50：　@株式先物
     */
    public final static String STOCK_FUTURES = "50";
    
    /**
     *52：　@債券先物
     */
    public final static String BOND_FUTURES = "52";

    /**
     *55：　@海外先物
     */
    public final static String FOREIGN_FUTURES = "55";

}
@
