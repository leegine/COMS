head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引返済注文入力レスポンスクラス(WEB3MarginCloseMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/16 李松峰 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 修正
*/
package webbroker3.equity.message;


/**
 * （信用取引返済注文入力レスポンス）。<br>
 * <br>
 * 信用取引返済注文入力レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginCloseMarginInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_closeMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101757L;    
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
     * 0：一般　@1：特定<BR>
     */
    public String taxType;
    
    /**
     * (取引区分)<BR>
     * <BR>
     * 5：買建返済注文（売返済）　@6：売建返済注文（買返済）<BR>
     */
    public String tradingType;
    
    /**
     * (弁済)<BR>
     * （信用取引弁済）<BR>
     */
    public WEB3MarginRepaymentUnit repayment;
    
    /**
     * (注文期限区分一覧)<BR>
     * 1：当日限り　@2：出来るまで注文<BR>
     */
    public String[] expirationDateTypeList;
    
    /**
     * (発注条件区分一覧)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String[] orderCondTypeList;
    
    /**
     * (時価(現在値))<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
    //スーパークラスに移動
    //public String currentPrice;
    
    /**
     * (前日比)<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
	//スーパークラスに移動
    //public String comparedPreviousDay;
    
    /**
     * (取引時間(時価発表時間))<BR>
     * 値がついていないときは未設定。<BR>
     * <BR>
     * 銘柄指定の場合使用。<BR>
     */
	//スーパークラスに移動
    //public Date currentPriceTime;
    
    /**
     * (建株明細一覧)<BR>
     */
    public WEB3MarginContractUnit[] contractUnits;
    
    /**
     * @@roseuid 4140485A012E
     */
    public WEB3MarginCloseMarginInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginCloseMarginInputResponse(WEB3MarginCloseMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
