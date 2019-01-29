head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderExecutionRefCommon.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文約定照会共通Unit (WEB3AdminOROrderExecutionRefCommon.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 黄　@浩澎(中訊) モデル変更点087・088・090・091
                   2006/10/16 周捷 (中訊) 仕様変更・モデル060、069
                   2006/12/04 唐性峰 (中訊) モデルNo.086
                   2006/12/19 張騰宇 (中訊) 仕様変更・モデル087
Revesion History : 2007/07/01 張騰宇(中訊) モデルNo.101
Revesion History : 2007/08/14 何文敏(中訊) モデルNo.105
Revesion History : 2008/10/02 大澤(SRA) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.130
*/
package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (管理者注文約定照会共通Unit)<BR>
 * <BR>
 * 管理者注文約定照会共通Unitクラス<BR>
 * <BR>
 * WEB3AdminOROrderExecutionRefCommon<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderExecutionRefCommon extends Message
{
    /**
     * (ID)<BR>
     * <BR>
     * 注文ＩＤ<BR>
     * <BR>
     * id<BR>
     * <BR>
     */
    public String id;

    /**
     * (部店コード)<BR>
     * <BR>
     * 部店コード<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * <BR>
     * 顧客コード<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     */
    public String accountName;

    /**
     * (扱者コード(SONAR))<BR>
     * <BR>
     * 顧客の扱者コード(SONAR)<BR>
     * <BR>
     */
    public String sonarTraderCode;

    /**
     * (商品区分)<BR>
     * <BR>
     * 商品区分<BR>
     * <BR>
     * 0：　@現物株式<BR>
     * 1：　@信用取引<BR>
     * 2：　@株式ミニ投資<BR>
     * 3：　@オプション<BR>
     * 4：　@先物<BR>
     * 5：　@投信<BR>
     * 6：　@累投<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * productDiv<BR>
     * 0: Def.EQUITY<BR>
     * 1: Def.MARGIN<BR>
     * 2: Def.MINI_STOCK<BR>
     * 3: Def.OPTION<BR>
     * 4: Def.FUTURE<BR>
     * 5: Def.MF<BR>
     * 6: Def.RUITO<BR>
     * <BR>
     */
    public String productDiv;

    /**
     * (取引区分)<BR>
     * <BR>
     * 取引区分<BR>
     * <BR>
     * 1：　@現物買付注文<BR>
     * 2：　@現物売付注文<BR>
     * 3：　@新規買建注文<BR>
     * 4：　@新規売建注文<BR>
     * 5：　@買建返済注文<BR>
     * 6：　@売建返済注文<BR>
     * 7：　@現引注文<BR>
     * 8：　@現渡注文<BR>
     * 99：　@立会外分売<BR>
     * 101：　@ミニ株買付注文<BR>
     * 102：　@ミニ株売付注文<BR>
     * 201：　@投資信託買付注文<BR>
     * 202：　@投資信託売付注文<BR>
     * 203：　@投資信託募集注文<BR>
     * 204：　@投資信託乗換注文<BR>
     * 501：　@累投買付注文<BR>
     * 502：　@累投売付注文<BR>
     * 601：　@指数先物新規買建注文<BR>
     * 602：　@指数先物新規売建注文<BR>
     * 603：　@指数先物売建返済注文<BR>
     * 604：　@指数先物買建返済注文<BR>
     * 605：　@指数オプション新規買建注文<BR>
     * 606：　@指数オプション新規売建注文<BR>
     * 607：　@指数オプション売建返済注文<BR>
     * 608：　@指数オプション買建返済注文<BR>
     * 701：　@外国株式買付<BR>
     * 702：　@外国株式売付<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * tradingDiv<BR>
     * 1: Def.EQUITY_BUY<BR>
     * 2: Def.EQUITY_SELL<BR>
     * 3: Def.MARGIN_LONG<BR>
     * 4: Def.MARGIN_SHORT<BR>
     * 5: Def.CLOSE_MARGIN_LONG<BR>
     * 6: Def.CLOSE_MARGIN_SHORT<BR>
     * 7: Def.SWAP_MARGIN_LONG<BR>
     * 8: Def.SWAP_MARGIN_SHORT<BR>
     * 99: Def.SALES_OUTSIDE_MARKET <BR>
     * 101: Def.MINI_STOCK_BUY<BR>
     * 102: Def.MINI_STOCK_SELL<BR>
     * 201: Def.MF_BUY<BR>
     * 202: Def.MF_SELL<BR>
     * 203: Def.MF_RECRUIT<BR>
     * 204: Def.MF_SWITCHING<BR>
     * 501: Def.RUITO_BUY<BR>
     * 502: Def.RUITO_SELL<BR>
     * 601: Def.IDX_FUTURES_BUY_TO_OPEN<BR>
     * 602: Def.IDX_FUTURES_SELL_TO_OPEN<BR>
     * 603: Def.IDX_FUTURES_BUY_TO_CLOSE<BR>
     * 604: Def.IDX_FUTURES_SELL_TO_CLOSE<BR>
     * 605: Def.IDX_OPTIONS_BUY_TO_OPEN<BR>
     * 606: Def.IDX_OPTIONS_SELL_TO_OPEN<BR>
     * 607: Def.IDX_OPTIONS_BUY_TO_CLOSE<BR>
     * 608: Def.IDX_OPTIONS_SELL_TO_CLOSE<BR>
     * 701: Def.FEQ_BUY<BR>
     * 702: Def.FEQ_SELL<BR>
     * <BR>
     */
    public String tradingType;

    /**
     * (注文数量)<BR>
     * <BR>
     * 注文数量<BR>
     * <BR>
     * orderQuantity<BR>
     * <BR>
     */
    public String orderQuantity;

    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * <BR>
     * orderPriceDiv<BR>
     * 0: Def.UNLIMIT_PRICE　@1: Def.LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * <BR>
     * 注文単価<BR>
     * <BR>
     * orderPrice<BR>
     * <BR>
     */
    public String orderPrice;

    /**
     * (概算受渡代金)<BR>
     * <BR>
     * 概算受渡代金<BR>
     * <BR>
     * estimatedPrice<BR>
     * <BR>
     */
    public String estimatedPrice;

    /**
     * (注文チャネル)<BR>
     * <BR>
     * 注文チャネル<BR>
     * <BR>
     * 0：　@営業店<BR>
     * 1：　@インターネット<BR>
     * 2：　@コールセンター<BR>
     * 3：　@モバイル<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderChannel<BR>
     * <BR>
     * 0: Def.BRANCH<BR>
     * 1: Def.INTERNET<BR>
     * 2: Def.CALL_CENTER<BR>
     * 3: Def.MOBILE<BR>
     * <BR>
     */
    public String orderChannel;

    /**
     * (注文状態区分)<BR>
     * <BR>
     * 注文状態区分<BR>
     * <BR>
     * 0：　@その他<BR>
     * 1：　@受付済（新規注文）<BR>
     * 2：　@発注中（新規注文）<BR>
     * 3：　@発注済（新規注文）<BR>
     * 6：　@発注失敗（新規注文）<BR>
     * 7：　@受付済（変更注文）<BR>
     * 8：　@発注中（変更注文）<BR>
     * 10：　@発注済（変更注文）<BR>
     * 11：　@発注失敗（変更注文）<BR>
     * 12：　@受付済（取消注文）<BR>
     * 13：　@発注中（取消注文）<BR>
     * 14：　@発注済（取消注文）<BR>
     * 15：　@発注失敗（取消注文）<BR>
     * 20：　@一部失効<BR>
     * 21：　@全部失効<BR>
     * 22：　@無効<BR>
     * 23：　@手動失効<BR>
     * 50：　@繰越済<BR>
     * 51：　@繰越失敗<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderState<BR>
     * <BR>
     * 0: Def.UNDEFINED<BR>
     * 1: Def.ACCEPTED<BR>
     * 2: Def.ORDERING<BR>
     * 3: Def.ORDERED<BR>
     * 6: Def.NOT_ORDERED<BR>
     * 7: Def.MODIFY_ACCEPTED）<BR>
     * 8: Def.MODIFYING<BR>
     * 10: Def.MODIFIED<BR>
     * 11: Def.NOT_MODIFIED<BR>
     * 12: Def.CANCEL_ACCEPTED<BR>
     * 13: Def.CANCELLING<BR>
     * 14: Def.CANCELLED<BR>
     * 15: Def.NOT_CANCELLED<BR>
     * 20: Def.PART_INAFFECTED<BR>
     * 21: Def.FULL_INAFFECTED<BR>
     * 22: Def.CLOSED<BR>
     * 50: Def.TRANSFERED<BR>
     * 51: Def.NOT_TRANSFERED<BR>
     * <BR>
     */
    public String orderState;

    /**
     * (注文時間)<BR>
     * <BR>
     * 注文時間<BR>
     * <BR>
     * orderDate<BR>
     * <BR>
     */
    public Date orderDate;

    /**
     * (発注日)<BR>
     * <BR>
     * 発注日<BR>
     * <BR>
     * orderBizDate<BR>
     * <BR>
     */
    public Date orderBizDate;

    /**
     * (執行条件)<BR>
     * <BR>
     * 1：無条件　@3：寄付　@4：引け　@7：不出来引け成行<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execCondType<BR>
     * 1: Def.NO_CONDITION<BR>
     * 3: Def.AT_MARKET_OPEN<BR>
     * 4: Def.AT_MARKET_CLOSE<BR>
     * 7: Def.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * <BR>
     */
    public String execCondType;

    /**
     * (注文有効期限)<BR>
     * <BR>
     * 注文有効期限<BR>
     * <BR>
     * ※出来るまで注文の場合設定<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * expirationDate<BR>
     * <BR>
     * If order only today, set null<BR>
     * <BR>
     */
    public Date expirationDate = null;

    /**
     * (発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondType<BR>
     * 0: Def.DEFAULT<BR>
     * 1: Def.STOP_LIMIT_PRICE <BR>
     * 2: Def.W_LIMIT_PRICE<BR>
     * <BR
     */
    public String orderCondType;

    /**
     * (発注条件単価)<BR>
     * <BR>
     * 発注条件単価<BR>
     * <BR>
     * ※発注条件区分が、"逆指値"または"W指値"の場合、セットする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderCondPrice<BR>
     * Set if orderCondType = Def.STOP_LIMIT_PRICE or <BR>
     * Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String orderCondPrice = null;

    /**
     * (発注条件演算子)<BR>
     * <BR>
     * 発注条件演算子<BR>
     * <BR>
     * 1：以上　@2：以下<BR>
     * <BR>
     * ※発注条件区分が、"逆指値"または"W指値"の場合、セットする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * condOperator<BR>
     * <BR>
     * 1: Def.OVER<BR>
     * 2.: Def.UNDER<BR>
     * Set if orderCondType = Def.STOP_LIMIT_PRICE or <BR>
     * Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String condOperator = null;

    /**
     * (Ｗ指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * <BR>
     * 発注条件区分「2：W指値」の場合設定<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * wLimitOrderPriceDiv<BR>
     * <BR>
     * 0: Def.MARKET_PRICE<BR>
     * 1: Def.LIMIT_PRICE<BR>
     * <BR>
     * Set if orderCondType is Def.W_LIMIT_PRICE<BR>
     * <BR>
     */
    public String wLimitOrderPriceDiv = null;

    /**
     * (Ｗ指値用注文単価)<BR>
     * <BR>
     * Ｗ指値用注文単価区分「1：指値」の場合設定<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * wlimitOrderCondPrice<BR>
     * <BR>
     * Set if wLimitOrderPriceDiv is 1: Def.WLIMIT_ORDER_PRICE_DIV_LIMIT_PRICE<BR>
     * <BR>
     */
    public String wlimitOrderCondPrice = null;

    /**
     * (約定数量)<BR>
     * <BR>
     * 約定数量<BR>
     * <BR>
     * ※約定がある場合はセット。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execQuantity<BR>
     * <BR>
     * ※Set if execution<BR>
     * <BR>
     */
    public String execQuantity = null;

    /**
     * (約定単価)<BR>
     * <BR>
     * 約定単価<BR>
     * <BR>
     * ※約定がある場合はセット。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execPrice<BR>
     * <BR>
     * ※Set if execution<BR>
     * <BR>
     */
    public String execPrice = null;

    /**
     * (扱者コード)<BR>
     * <BR>
     * 扱者コード<BR>
     * <BR>
     * ※コールセンターからの注文の場合セット。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * traderCode<BR>
     * <BR>
     * ※Set if an order from call center<BR>
     * <BR>
     */
    public String traderCode = null;

    /**
     * (約定状態区分)<BR>
     * <BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：　@未約定<BR>
     * 1：　@一部成立<BR>
     * 2：　@全部成立<BR>
     * 3：　@約定処理中(一部成立)<BR>
     * 4：　@約定処理中(全部成立)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * execType<BR>
     * 0: Def.EXEC_TYPE_NOT_PROMISE<BR>
     * 1: Def.EXEC_TYPE_ONE_COMPLETE<BR>
     * 2: Def.EXEC_TYPE_ALL_COMPLETE<BR>
     * 3: Def.EXEC_PROCESSING_ONE_COMPLETE<BR>
     * 4: Def.EXEC_PROCESSING_ALL_COMPLETE<BR>
     * <BR>
     */
    public String execType;

    /**
     * (訂正取消区分)<BR>
     * <BR>
     * 訂正取消区分<BR>
     * <BR>
     * 0：　@初期値<BR>
     * 1：　@取消中<BR>
     * 2：　@一部取消完了<BR>
     * 3：　@全部取消完了<BR>
     * 4：　@取消失敗<BR>
     * 5：　@訂正中<BR>
     * 6：　@一部訂正完了<BR>
     * 7：　@全部訂正完了<BR>
     * 8：　@訂正失敗<BR>
     * 9：　@エラー<BR>
     * A：　@W指値注文切替中<BR>
     * B：　@W指値注文一部切替完了<BR>
     * C：　@W指値注文全部切替完了<BR>
     * D：　@W指値注文切替失敗<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * changeCancelDiv<BR>
     * 0: Def.INITIAL_VALUE<BR>
     * 1: Def.CANCELING<BR>
     * 2: Def.PART_CANCELED<BR>
     * 3: Def.CANCELED<BR>
     * 4: Def.CANCEL_ERROR<BR>
     * 5: Def.CHANGING<BR>
     * 6: Def.PARTIALLY_CHANGED<BR>
     * 7: Def.CHANGED<BR>
     * 8: Def.CHANGE_ERROR<BR>
     * 9: Def.ERROR<BR>
     * <BR>
     */
    public String changeCancelDiv;

    /**
     * (注文経路区分)<BR>
     * <BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット　@4：i-mode　@5：Vodafone<BR>
     * 6：AU　@9：HOST　@ A：管理者　@C：リッチクライアント　@F：IVR（自動応答電話）<BR>
     * G：強制決済<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * orderRootDiv<BR>
     * 1: Def.CALLCENTER<BR>
     * 2: Def.PC <BR>
     * 3: Def.SLINGSHOT<BR>
     * 4: Def.I_MODE<BR>
     * 5: Def.VODAFONE<BR>
     * 6: Def.AU<BR>
     * 9: Def.HOST<BR>
     * <BR>
     */
    public String orderRootDiv;

    /**
     * (受渡日)<BR>
     * <BR>
     * 受渡日<BR>
     * <BR>
     * deliveryDate<BR>
     * <BR>
     */
    public Date deliveryDate;

    /**
     * (夕場前繰越対象フラグ)<BR>
     * false：夕場前繰越なし<BR>
     * true：夕場前繰越あり<BR>
     */
    public boolean eveningSessionCarryoverFlag;

    /**
     * (立会区分)<BR>
     * 1：夕場（夕場実施する会社の夕場時間帯のみ設定）　@NULL：その他<BR>
     */
    public String sessionType = null;

    /**
     * @@roseuid 4212FBC50017
     */
    public WEB3AdminOROrderExecutionRefCommon()
    {

    }
}
@
