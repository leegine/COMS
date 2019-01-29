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
filename	WEB3MarginCloseMarginChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引訂正返済入力レスポンスクラス(WEB3MarginCloseMarginChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
Revesion History : 2006/11/03 張騰宇(中訊) モデル 948
Revesion History : 2007/06/05 何文敏 (中訊) 仕様変更・モデル1166
*/
package webbroker3.equity.message;

import java.util.Date;

/**
 * （信用取引訂正返済入力レスポンス）。<br>
 * <br>
 * 信用取引訂正返済入力レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginChangeInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginChangeInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101641L;      
    /**
     * (銘柄コード)<BR>
     */
    public String productCode;
    
    /**
     * (銘柄名)
     */
    public String productName;
    
    /**
     * (市場コード)
     */
    public String marketCode;
    
    /**
     * (口座区分)
     */
    public String taxType;
    
    /**
     * (取引区分)<BR>
     * <BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）
     */
    public String tradingType;
    
    /**
     * (弁済)<BR>
     * （信用取引弁済）
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (決済順序区分)<BR>
     * 0：ランダム　@1：単価益順　@2：単価損順　@3：建日順<BR>
     * <BR>
     * 一括返済時に設定された値
     */
    public String closingOrder;
    
    /**
     * (建株明細一覧)
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * (注文株数)
     */
    public String orderQuantity;
    
    /**
     * (内出来株数)<BR>
     */
    public String partContQuantity;
    
    /**
     * (注文単価区分)<BR>
     * <BR>
     * 0：成行　@1：指値
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * <BR>
     * 注文単価区分が「指値」の場合に設定
     */
    public String limitPrice;
    
	/**
	 * (値段条件)<BR>
	 * 0:指定なし　@1:現在値指値　@3:優先指値　@5:成行残数指値　@ 7:成行残数取消
	 */
	public String priceCondType;
    
    
    /**
     * (執行条件)<BR>
     * <BR>
     * 1：無条件 3:寄付　@4:引け　@7:不出来引け成行
     */
    public String execCondType;
    
    /**
     * (注文期限区分)<BR>
     * 1：当日限り　@2：出来るまで注文
     */
    public String expirationDateType;
    
    /**
     * (注文有効期限)<BR>
     * 注文期限区分が「2：出来るまで注文」の場合に設定
     */
    public Date expirationDate;
    
    /**
     * (発注条件区分)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値
     */
    public String orderCondType;
    
    /**
     * (逆指値用発注条件単価)<BR>
     * 発注条件区分が「1：逆指値」の場合設定される
     */
    public String stopOrderCondPrice;
    
    /**
     * (逆指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * <BR>
     * 発注条件区分が「1：逆指値」の場合設定される
     */
    public String stopOrderCondOperator;
    
    /**
     * (Ｗ指値用発注条件単価)<BR>
     * 発注条件区分が「2：W指値」の場合設定される
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (Ｗ指値用発注条件演算子)<BR>
     * 1：以上　@2：以下<BR>
     * <BR>
     * 発注条件区分が「2：W指値」の場合設定される
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (Ｗ指値用注文単価区分)<BR>
     * 0：成行　@1：指値<BR>
     * 発注条件単価が、「2：W指値」の場合設定される
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (Ｗ指値用注文単価)<BR>
     * Ｗ指値用注文単価区分が、「1：指値」の場合設定される
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
     * 発注条件区分または元発注条件区分が「2：W指値」の場合、設定される。<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (Ｗ指値用切替前執行条件)<BR>
     * 1：無条件 3：寄付 4：引け 7：不出来引け成行<BR>
     * <BR>
     * 発注条件区分または元発注条件区分が「2：W指値」の場合、設定される。<BR>
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
     * ※元Ｗ指値用注文単価区分が、1：指値の場合設定される<BR>
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
     * 訂正前の概算受渡代金を設定。
     */
    public String estimatedPrice;

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
     * (時価（現在値）)<BR>
     * 値がついていないときは基準値を設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。
     */
    //スーパークラスに移動
    //public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。
     */
    //スーパークラスに移動
    //public String comparedPreviousDay;
    
    /**
     * (取引時間（時価発表時間）)<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。
     */
    //スーパークラスに移動
    //public Date currentPriceTime;
    
    /**
     * @@roseuid 414044F103D2
     */
    public WEB3MarginCloseMarginChangeInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCloseMarginChangeInputResponse(WEB3MarginCloseMarginChangeInputRequest l_request)
    {
        super(l_request);
    }
}
@
