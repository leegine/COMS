head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定詳細レスポンス(WEB3EquityExecuteDetailsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/18 張坤芳 (中訊) 新規作成
Revesion History : 2004/12/07 岡村（SAR）残案件対応 Ｎｏ.１６８
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応 No.385
Revesion History : 2006/07/05 肖志偉 (中訊) 仕様変更941
Revesion History : 2006/08/02 栄イ (中訊) 仕様変更 モデルNo.959を対応
                   2006/08/29 張騰宇(中訊) モデル 972
                   2006/11/01 張騰宇(中訊) モデル 948,989,999
                   2006/11/20 張騰宇(中訊) モデル 1056
Revesion History : 2007/07/24 何文敏(中訊) モデル 1184
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （現物株式注文約定詳細レスポンス）。<BR>
 * <BR>
 * 現物株式注文約定詳細応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityExecuteDetailsResponse extends WEB3GenResponse
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
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W指値用発注条件単価)<BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W指値用発注条件演算子)<BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W指値用注文単価)<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
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
     * 0:成行　@1:指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     */
    public String limitPrice;

    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;

    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     */
    public Date expirationDate;

    /**
     * (注文受付日)<BR>
     * 受注日時<BR>
     */
    public Date orderDate;

    /**
     * (注文状態区分)<BR>
     * 0:その他,1:受付済（新規注文）,2:発注中（新規注文）,<BR>
     * 3:発注済（新規注文）,6:発注失敗（新規注文）,<BR>
     * 7:受付済（変更注文）,8:発注中（変更注文）,<BR>
     * 10:発注済（変更注文）,11:発注失敗（変更注文）,<BR>
     * 12:受付済（取消注文）,13:発注中（取消注文）,<BR>
     * 14:発注済（取消注文）,15:発注失敗（取消注文）,<BR>
     * 20：一部失効, 21：全部失効, 22：無効,<BR> 
     * 24:切替注文 25:切替受付 26:切替完了 27:切替注文(失敗)<BR>
     * 50:繰越済　@51:繰越失敗<BR>
     * <BR>
     * ※）繰越済、繰越失敗のコードについては、現在<BR>
     * PS様よりDIR様に確認中<BR>
     */
    public String orderState;

    /**
     * (繰越エラーコード)<BR>
     * 0001：値幅エラー 0002：預り金不足エラー <BR>
     * 0003：株式残高不足エラー 0004：保証金不足エラー <BR>
     * 0005：建株残高不足エラー 0006：売買停止銘柄エラー <BR>
     * 0007：市場変更銘柄エラー 0008：買付余力エラー <BR>
     * 0009：売付可能数量エラー 0010：特定口座エラー <BR>
     * 0011：注文繰越スキップ銘柄エラー 9001：その他エラー <BR>
     * 注文状態区分が「51：繰越失敗」の場合セット <BR>
     * 注文繰越でエラーが発生した場合の、エラー理由のコード。 <BR>
     * 上記以外の場合はnullをセット。<BR>
     * <BR>
     * 注文繰越でエラーが発生した場合の、エラー理由のコード。<BR>
     * 上記以外の場合はnullをセット。<BR>
     */
    public String transferErrCode;

    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;


    /**
     * (受渡日)<BR>
     * 受渡日<BR>
     */
    public Date deliveryDate;

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
     * (約定代金)<BR>
     * 約定代金<BR>
     */
    public String execTotalPrice;

    /**
     * (約定状態区分)<BR>
     * 1:未約定　@2:一部約定　@<BR>
     */
    public String execType;

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
     * (手数料情報)<BR>
     * 手数料情報<BR>
     */
    public WEB3EquityCommissionInfoUnit commissionInfo;

    /**
     * (分割約定一覧)<BR>
     * 分割約定一覧<BR>
     */
    public WEB3EquityExecuteUnit[] executeUnits;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;

    /**
     * (訂正取消区分)<BR>
     * 0：初期値  1：取消中  2：一部取消完了　@3：全部取消完了　@4：取消失敗<BR>
     * 5：訂正中　@6：一部訂正完了　@7：全部訂正完了　@8：訂正失敗　@9：エラー<BR>
     */
    public String changeCancelDiv;

    /**
     * (注文経路区分)<BR>
     * 1：コールセンター　@2：ＰＣ　@3:スリングショット<BR>
     * 4：i-mode　@5：Vodafone　@6：AU　@7：スリングショット（無料）　@9：HOST<BR>
     * A：管理者　@B：保証金自動振替バッチ　@C：リッチクライアント<BR>
     * F：IVR（自動応答電話）　@G：強制決済<BR>
     */
    public String orderRootDiv;
    
    /**
     * (処理状況)<BR>
     * 「ﾒｯｾｰｼﾞ定義書_現物株式(共通).xls」<BR> 
     * 【処理状況区分定義】sheet参照。 <BR>
     */
    public String transactionStateType;

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
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_exec_details";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405111057L;

    /**
     * @@roseuid 40A288A40186
     */
    public WEB3EquityExecuteDetailsResponse(WEB3EquityExecuteDetailsRequest l_request)
    {
        super(l_request);
    }
    /**
     * @@roseuid 40A288A40186
     */
    public WEB3EquityExecuteDetailsResponse()
    {
    }    
}
@
