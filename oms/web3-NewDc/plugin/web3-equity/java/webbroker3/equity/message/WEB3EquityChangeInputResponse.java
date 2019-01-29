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
filename	WEB3EquityChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文訂正入力レスポンス(WEB3EquityChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
Revesion History : 2004/12/08 岡村和明(SRA) 残案件対応 Ｎｏ.０５９
                   2006/08/29 張騰宇(中訊) モデル 972
                   2006/11/01 張騰宇(中訊) モデル 948
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * （現物株式注文訂正入力レスポンス）。<BR>
 * <BR>
 * 現物株式注文訂正入力応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityChangeInputResponse
    extends WEB3EquityInputCommonResponse
{

    /**
     * (買付可能金額)<BR>
     * 買付注文の場合のみｾｯﾄ。<BR>
     */
    public String tradingPower;

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
     * 1:東京　@2:大阪　@3:名古屋　@6:福岡　@8:札幌　@9:NNM　@10:JASDAQ<BR>
     */
    public String marketCode;

    /**
     * (口座区分)<BR>
     * 0：一般　@1：特定　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * (売買区分)<BR>
     * 1：買い　@　@2：売り<BR>
     */
    public String dealingType;

    /**
     * (注文株数)<BR>
     * 注文株数<BR>
     */
    public String orderQuantity;

    /**
     * (内出来株数)<BR>
     * リアル内出来株数<BR>
     */
    public String partContQuantity;

    /**
     * (概算簿価単価)<BR>
     * リアル概算簿価単価<BR>
     */
    public String estimatedBookPrice;

    /**
     * (注文単価区分)<BR>
     * 0:成行　@1:指値<BR>
     */
    public String orderPriceDiv;

    /**
     * (注文単価)<BR>
     * 注文単価区分が「指値」の場合に設定<BR>
     */
    public String limitPrice;

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
     * (注文期限区分)<BR>
     * 1：当日限り　@2：出来るまで注文<BR>
     */
    public String expirationDateType;

    /**
     * (注文有効期限)<BR>
     * 注文期限区分が「出来るまで注文」の場合に設定<BR>
     */
    public Date expirationDate;

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
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分が「2：W指値」の場合設定される<BR>
     */
    public String wlimitOrderCondPrice;

    /**
     * (Ｗ指値用発注条件演算子)<BR>
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
     * <BR>
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
     * (概算受渡代金)<BR>
     * 特定口座の場合に必要<BR>
     */
    public String estimatedPrice;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004052001L;

    /**
     * @@roseuid 409F417C00A1
     */
    public WEB3EquityChangeInputResponse()
    {

    }
    public WEB3EquityChangeInputResponse(WEB3EquityChangeInputRequest l_request)
    {
        super(l_request);
    }
}
@
