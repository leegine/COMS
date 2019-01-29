head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketPreferencesNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : プリファ@レンス名定数定義インタフェイス(WEB3MarketPreferencesNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/30 孟東(sinocom) 新規作成
Revesion History : 2006/09/18 栄イ(中訊) 共通仕様変更管理台帳・ＤＢレイアウトNo421
Revesion History : 2007/03/16 栄イ(中訊) ＤＢレイアウト(市場用プリファ@レンステーブル)による
Revesion History : 2007/12/19 孟暁シン(中訊) 【共通】仕様変更管理台帳No566(ＤＢレイアウト)を対応
Revesion History : 2010/01/12 趙林鵬 (中訊) 【共通】仕様変更管理台帳 ＤＢレイアウトNo.703
*/
package webbroker3.common.define;

/**
 * 市場用プリファ@レンステーブルのプリファ@レンス名定数定義インタフェイス
 *
 * @@author meng-d
 * @@version 1.0
 */
public interface WEB3MarketPreferencesNameDef
{
    /**
     * feq.order.change：注文の訂正が可能かどうか　@　@　@  　@　@
     */
    public final static String FEQ_ORDER_CHANGE = "feq.order.change";

    /**
     * feq.sle.broker：外株市場連動対象市場かどうか　@　@　@  　@　@
     */
    public final static String FEQ_SLE_BROKER = "feq.sle.broker";

    /**
     * feq.order.quantity.limit：最大注文数量チェック可否　@　@　@  　@　@
     */
    public final static String FEQ_ORDER_QUANTITY_LIMIT = "feq.order.quantity.limit";

    /**
     * feq.order.quantity.size：最大注文数量桁数チェック可否
     */
    public final static String FEQ_ORDER_QUANTITY_SIZE = "feq.order.quantity.size";
    
    /**
     * equity.pts.market.div：PTS市場区分
     */
    public final static String EQUITY_PTS_MARKET_DIV = "equity.pts.market.div";
    
    /**
     * feq.buy.commision.rate:外株買付徴収率(暫定）
     */
    public final static String FEQ_BUY_COMMISION_LATE = "feq.buy.commision.rate";

    /**
     * feq.day.trade.market.div:外株日計り市場区分
     */
    public final static String FEQ_DAY_TRADE_MARKET_DIV = "feq.day.trade.market.div";
}
@
