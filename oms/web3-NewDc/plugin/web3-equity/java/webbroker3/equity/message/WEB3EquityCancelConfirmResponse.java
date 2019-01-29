head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文取消確認レスポンス(WEB3EquityCancelOrderConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 李雲峰 (中訊) 新規作成
Revesion History : 2004/12/08 岡村（SAR）残案件対応 Ｎｏ.０５９＆Ｎｏ.０６０
Revesion History : 2004/12/14 桑原 (SRA) 残案件対応　@No.385
                   2006/08/29 張騰宇(中訊) モデル 972
                   2006/11/02 張騰宇(中訊) モデル 948
                   2006/12/14 柴双紅(中訊) モデル1082
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.equity.message.WEB3EquityConfirmCommonResponse;

/**
 * （現物株式注文取消確認レスポンス）。<BR>
 * <BR>
 * 現物株式注文取消確認応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityCancelConfirmResponse extends WEB3EquityConfirmCommonResponse
{

    /**
     * (銘柄コード)<BR>
     */
    public String productCode;

    /**
     * (銘柄名)<BR>
     */
    public String productName;

    /**
     * (市場コード)<BR>
     */
    public String marketCode;

    /**
     * (口座区分)<BR>
     * <BR>
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (取引区分)<BR>
     * <BR>
     * 1：現物買注文　@2：現物売注文　@99：立会外分売<BR>
     */
    public String tradingType;

    /**
     * (注文株数)<BR>
     */
    public String orderQuantity;

    /**
     * (内出来株数)<BR>
     */
    public String partContQuantity;

    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0:成行　@1:指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * <BR>
     * 注文単価区分が「指値」の場合に設定<BR>
     */
    public String limitPrice;

    /**
     * (概算簿価単価)<BR>
     */
    public String estimatedBookPrice;

    /**
     * (値段条件)<BR>
     * <BR>
     * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@7:成行残数取消<BR>
     */
    public String priceCondType;

    /**
     * (執行条件)<BR>
     * <BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行<BR>
     */
    public String execCondType;

    /**
     * (注文期限区分)<BR>
     * <BR>
     * 1：当日限り　@2：出来るまで注文<BR>
     */
    public String expirationDateType;

    /**
     * (注文有効期限)<BR>
     * <BR>
     * 注文期限区分が「出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate;

    /**
     * (発注条件区分)<BR>
     * <BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String orderCondType;

    /**
     * (逆指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     */
    public String stopOrderCondPrice;

    /**
     * (逆指値用発注条件演算子)<BR>
     * <BR>
     * 発注条件区分が「1：逆指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String stopOrderCondOperator;

    /**
     * (W指値用発注条件単価)<BR>
     * <BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (W指値用発注条件演算子)<BR>
     * <BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     * 1：以上　@2：以下<BR>
     */
    public String wlimitOrderCondOperator;

    /**
     * (W指値用注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値<BR>
     * 発注条件区分が、「2：W指値」の場合設定される<BR>
     */
    public String wLimitOrderPriceDiv;

    /**
     * (W指値用注文単価)<BR>
     * <BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される<BR>
     */
    public String wLimitPrice;

    /**
     * (Ｗ指値用執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分が、「2：W指値」の場合設定される。<BR>
     */
    public String wlimitExecCondType;

    /**
     * (Ｗ指値用有効状態区分)<BR>
     * 0：リミット注文有効　@1：ストップ注文有効<BR>
     * 2：ストップ注文失効済<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合設定される。<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (Ｗ指値用切替前注文単価)<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される。<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * 発注条件区分または元発注条件区分が、「2：W指値」の場合、設定される。<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (元発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     * ※発注条件執行後の場合に設定<BR>
     */
    public String orgOrderCondType;

    /**
     * (元発注条件単価)<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (元発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * ※元発注条件区分が、1：逆指値、2：Ｗ指値の場合<BR>
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
     * (時価区分)<BR>
     * 1:現在値　@　@2:売気配値　@　@3:買気配値　@　@4:前日終値<BR>
     */
    public String currentPriceDiv;
    
    /**
     * (時価(現在値))<BR>
     */
    public String currentPrice;

    /**
     * (前日比)
     */
    public String comparedPreviousDay;

    /**
     * (取引時間(時価発表時間))<BR>
     */
    public Date currentPriceTime;

    /**
     * (現在値)
     */
    public String boardCurrentPrice;

    /**
     * (現在値時刻)
     */
    public Date boardCurrentPriceTime;

    /**
     * (現在値区分)
     */
    public String boardCurrentPriceDiv;

    /**
     * (現在値前日比)
     */
    public String boardChange;

    /**
     * (出来高)
     */
    public String volume;

    /**
     * (出来高時刻)
     */
    public Date volumeTime;

    /**
     * (買気配値タイトル区分)
     */
    public String askPriceTitle;

    /**
     * (買気配値)
     */
    public String askPrice;

    /**
     * (買気配値時刻)
     */
    public Date askPriceTime;

    /**
     * (売気配値タイトル区分)
     */
    public String bidPriceTitle;

    /**
     * (売気配値)
     */
    public String bidPrice;

    /**
     * (売気配値時刻)
     */
    public Date bidPriceTime;

    /**
     * (基準値段)
     */
    public String basePrice;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200405161153L;

    /**
     * @@roseuid 40AC537602DD
     */
    public WEB3EquityCancelConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 引数で与えられたリクエストオブジェクトを基に
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     * @@roseuid 405023760250
     */
    public WEB3EquityCancelConfirmResponse(WEB3EquityCancelConfirmRequest l_request)
    {
        super(l_request);
    }
}
@
