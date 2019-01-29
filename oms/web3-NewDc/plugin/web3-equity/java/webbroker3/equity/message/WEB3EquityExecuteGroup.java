head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会注文単位(WEB3EquityExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 張坤芳 (中訊) 新規作成
Revesion History : 2004/12/07 岡村（SAR）残案件対応 Ｎｏ.０５９＆Ｎｏ.３７５
Revesion History : 2004/12/13 桑原 (SRA) 残案件対応 No.385
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更941
Revesion History : 2006/07/15 栄イ 【株式】仕様変更管理台帳・モデル952を対応
                   2006/08/29 張騰宇(中訊) モデル 972
                   2006/11/01 張騰宇(中訊) モデル 948,989
Revesion History : 2007/07/27 何文敏(中訊) モデル No.1184
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * （現物株式注文約定照会注文単位）。<BR>
 * <BR>
 * 現物株式注文約定照会 注文単位　@データクラス<BR>
 * <BR>
 * 現物株式：「注文約定照会」<BR>
 * 現物株式：「訂正取消一覧」の両画面で使用する。
 * @@version 1.0
 */
public class WEB3EquityExecuteGroup extends Message
{

    /**
     * (ID)<BR>
     * 画面非表示項目<BR>
     * 「注文履歴一覧」画面への遷移に必要<BR>
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
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (取引区分)<BR>
     * 1：現物買注文　@2：現物売注文　@99：立会外分売<BR>
     */
    public String tradingType;

    /**
     * (値段条件)<BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消<BR>
     */
    public String priceCondType;

    /**
     * (執行条件)<BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;

    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * （逆指値用発注条件単価）<BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;
    
    /**
     * （逆指値用発注条件演算子）<BR>
     * 発注条件区分が「1：逆指値」の場合設定される <BR>
     * 1：以上　@2：以下
     */
    public String stopOrderCondOperator;
    
    /**
     * （W指値用発注条件単価）<BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;
    
    /**
     * （W指値用発注条件演算子）<BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     * 1：以上　@2：以下  <BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * （W指値用注文単価区分）<BR>
     * 0：成行　@1：指値 <BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * （W指値用注文単価）<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行 <BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (元発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orgOrderCondType;
    
    /**
     * (元発注条件単価)<BR>
     * 元発注条件単価<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (元発注条件演算子)<BR>
     * 1：以上　@2：以下 <BR>
     */
    public String orgOrderCondOperator;

    /**
     * (元Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (元Ｗ指値用注文単価)<BR>
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される。<BR>
     */
    public String orgWlimitPrice;

    /**
     * (元Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * ※元発注条件区分が2：Ｗ指値の場合<BR>
     */
    public String orgWlimitExecCondType;

    /**
     * (注文株数)<BR>
     * 注文株数<BR>
     */
    public String orderQuantity;

    /**
     * (注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String limitPrice;

    /**
     * (約定株数)<BR>
     * 合計約定株数<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;

    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (受渡代金)<BR>
     * 受渡代金<BR>
     */
    public String deliveryPrice;
    
    /**
     * (概算損益)<BR>
     * 概算損益<BR>
     */
    public String estimatedProfitLoss;

    /**
     * (注文時間)<BR>
     * 注文受付日時<BR>
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
     * コード定義：（U:\10.プロダクション\01.要件定義\02.機@能定義書\96.標準要件）<BR>
     * 「NEW注文約定照会組合せ表.xls」シート【注文約定照会　@処理状況】参照。<BR>
     */
    public String transactionStateType;

    /**
     * (訂正可能フラグ)<BR>
     * true：訂正可能　@　@false：訂正不可<BR>
     */
    public boolean changeFlag;

    /**
     * (取消可能フラグ)<BR>
     * true：取消可能　@　@false：取消不可<BR>
     */
    public boolean cancelFlag;

    /**
     * (注文チャネル)<BR>
     * 注文チャネル<BR>
     * <BR>
     * 0：営業店　@1：インターネット　@2：コールセンタ　@3：モバイル<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    public String orderChannel;

    /**
     * (注文経路区分)<BR>
     * コード定義中<BR>
     * <BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット<BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@7：スリングショット（無料）　@9：HOST<BR>
     * A：管理者　@B：保証金自動振替バッチ　@C：リッチクライアント<BR>
     * F：IVR（自動応答電話）　@G：強制決済<BR>
     * （コールセンターの時のみ使用）<BR>
     */
    public String orderRootDiv;

    /**
     * (オペレータコード)<BR>
     * 取扱者コード
     * （コールセンターの時のみ使用）<BR>
     */
    public String operatorCode;

    /**
     * (約定明細一覧)<BR>
     * 注文情報に紐付いた約定情報の一覧<BR>
     * （現物株式注文約定照会約定の配列）<BR>
     */
    public webbroker3.equity.message.WEB3EquityExecuteUnit[] executeUnits;

    /**
     * (遅延区分)<BR>
     * 0：正常　@1：遅延<BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」の場合設定される<BR>
     */
    public String delayDiv;

    /**
     * (手動発注可能フラグ)<BR>
     * true：手動発注可能　@　@false：手動発注不可<BR>
     * <BR>
     * 発注条件区分が、「1：逆指値」または「2：W指値」で、<BR>
     * 手動発注が可能である場合、trueが設定される。<BR>
     */
    public boolean manualFlag;

    /**
     * (現物株式注文約定照会注文単位)<BR>
     * コンストラクタ<BR>
     * @@roseuid 407CC8090353
     */
    public WEB3EquityExecuteGroup()
    {

    }
}
@
