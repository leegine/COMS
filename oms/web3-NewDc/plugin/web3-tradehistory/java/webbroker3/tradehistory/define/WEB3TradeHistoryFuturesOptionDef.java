head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryFuturesOptionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物・オプション(WEB3TradeHistoryFuturesOptionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27  温 顕 法@(中訊) 新規作成
*/
package webbroker3.tradehistory.define;

/**
 * 先物・オプション 定数定義インタフェイス
 * 
 * @@author 温 顕 法@
 * @@version 1.0
 */
public interface WEB3TradeHistoryFuturesOptionDef
{

    /**
     *50：　@株式先物
     */
    public final static String STOCK_FUTURES = "50";
    
    /**
     *51：　@株式オプション
     */
    public final static String STOCK_OPTION = "51";

    /**
     *52：　@債券先物
     */
    public final static String BOND_FUTURES = "52";

    /**
     *53：　@債券先物オプション
     */
    public final static String BOND_FUTURES_OPTION = "53";

    /**
     *54：　@店頭オプション
     */
    public final static String STORE_OPTION = "54";

    /**
     *55：　@海外先物
     */
    public final static String FOREIGN_FUTURES = "55";

    /**
     *56：　@海外先物オプション
     */
    public final static String FOREIGN_FUTURES_OPTION = "56";

    /**
     *57：　@株券オプション
     */
    public final static String STOCK_BOND_OPTION = "57";


}
@
