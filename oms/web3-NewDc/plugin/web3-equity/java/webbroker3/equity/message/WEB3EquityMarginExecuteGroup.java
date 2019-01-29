head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式・信用注文約定照会注文単位(WEB3EquityMarginExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 関博(中訊) 新規作成
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1164
*/

package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (株式・信用注文約定照会注文単位)<BR>
 * 株式・信用注文約定照会注文単位クラス<BR>
 * @@author  関博
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteGroup extends Message
{

    /**
     * (ID)<BR>
     * 注文ID<BR>
     */
    public String id;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;

    /**
     * (口座区分)<BR>
     * 口座区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String taxType;

    /**
     * (口座区分（現引・現渡）)<BR>
     * 口座区分（現引・現渡）<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String swapTaxType;

    /**
     * (取引区分)<BR>
     * 取引区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String tradingType;

    /**
     * (弁済)<BR>
     * 弁済<BR>
     */
    public WEB3MarginRepaymentUnit repayment;

    /**
     * (建日)<BR>
     * 建日<BR>
     */
    public Date openDate;

    /**
     * (建単価)<BR>
     * 建単価<BR>
     */
    public String contractPrice;

    /**
     * (値段条件)<BR>
     * 値段条件<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String priceCondType;

    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String execCondType;

    /**
     * (発注条件区分)<BR>
     * 発注条件区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用発注条件単価)<BR>
     * 逆指値用発注条件単価<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * 逆指値用発注条件演算子<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (Ｗ指値用発注条件単価)<BR>
     * Ｗ指値用発注条件単価<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * Ｗ指値用発注条件演算子<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (Ｗ指値用注文単価区分)<BR>
     * Ｗ指値用注文単価区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * Ｗ指値用執行条件<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * Ｗ指値用有効状態区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * Ｗ指値用切替前注文単価<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * Ｗ指値用切替前執行条件<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (元発注条件区分)<BR>
     * 元発注条件区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orgOrderCondType;

    /**
     * (元発注条件単価)<BR>
     * 元発注条件単価<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (元発注条件演算子)<BR>
     * 元発注条件演算子<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orgOrderCondOperator;

    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 元Ｗ指値用注文単価区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (元Ｗ指値用注文単価)<BR>
     * 元Ｗ指値用注文単価<BR>
     */
    public String orgWlimitPrice;

    /**
     * (元Ｗ指値用執行条件)<BR>
     * 元Ｗ指値用執行条件<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (注文株数)<BR>
     * 注文株数<BR>
     */
    public String orderQuantity;

    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String limitPrice;

    /**
     * (約定株数)<BR>
     * 約定株数<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;

    /**
     * (概算受渡代金)<BR>
     * 通常売買、立会外分売、現引現渡 ： 概算受渡代金<BR>
     * 新規建 ： 概算建代金<BR>
     * 返済 ： 概算決済損益代金<BR>
     */
    public String estimatedPrice;

    /**
     * (受渡代金)<BR>
     * 通常売買、立会外分売、現引現渡 ： 受渡代金<BR>
     * 新規建 ： 建代金<BR>
     * 返済 ： 決済損益代金<BR>
     */
    public String deliveryPrice;

    /**
     * (概算損益)<BR>
     * 概算損益<BR>
     */
    public String estimatedProfitLoss;

    /**
     * (注文時間)<BR>
     * 注文時間<BR>
     */
    public Date orderDate;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     */
    public Date expirationDate;

    /**
     * (処理状況)<BR>
     * 処理状況<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String transactionStateType;

    /**
     * (訂正可能フラグ)<BR>
     * 訂正可能フラグ<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public boolean changeFlag;

    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public boolean cancelFlag;

    /**
     * (注文チャネル)<BR>
     * 注文チャネル<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orderChannel;

    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String orderRootDiv;

    /**
     * (オペレータコード)<BR>
     * オペレータコード<BR>
     */
    public String operatorCode;

    /**
     * (分割約定一覧)<BR>
     * 注文情報に紐付いた約定情報の一覧<BR>
     */
    public webbroker3.equity.message.WEB3EquityMarginExecuteUnit[] executeUnits;

    /**
     * (遅延区分)<BR>
     * 遅延区分<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public String delayDiv;

    /**
     * (手動発注可能フラグ)<BR>
     * 手動発注可能フラグ<BR>
     * <BR>
     * 設定値については、メッセージ定義書参照。<BR>
     */
    public boolean manualFlag;

    /**
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     * <BR>
     * 0：　@決済期日到来<BR>
     * 1：　@保証金維持率割（オンライン開始前・軽度）<BR>
     * 2：　@保証金維持率割（オンライン開始前・重度）<BR>
     * 3：　@保証金維持率割（場間）<BR>
     * 9：　@手動強制決済<BR>
     * <BR>
     * ※強制決済注文でない場合はnullがセットされる。<BR>
     */
    public String forcedSettleReason = null;

    /**
     * (強制失効区分)<BR>
     * 強制失効区分<BR>
     * <BR>
     * 0：　@オープン<BR>
     * 1：　@強制失効済<BR>
     */
    public String forcedLapseDiv;

    /**
     * (株式・信用注文約定照会注文単位)<BR>
     * コンストラクタ<BR>
     * @@roseuid 455C3B6301D6
     */
    public WEB3EquityMarginExecuteGroup()
    {

    }
}
@
