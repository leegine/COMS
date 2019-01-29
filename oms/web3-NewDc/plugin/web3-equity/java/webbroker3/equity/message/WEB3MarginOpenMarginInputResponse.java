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
filename	WEB3MarginOpenMarginInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建注文入力レスポンス(WEB3MarginOpenMarginInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 凌建平 (中訊) 新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
                   2006/12/25 張騰宇 (中訊) モデル 1085
*/

package webbroker3.equity.message;

/**
 * （信用取引新規建注文入力レスポンス）。<br>
 * <br>
 * 信用取引新規建注文入力レスポンスクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputResponse extends WEB3MarginInputCommonResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (新規建可能額)<BR>
     * 買建の場合は新規買建可能額、売建の場合は新規売建可能額
     */
    public String marginTradingPower;
    
    /**
     * (銘柄名)<BR>
     * 銘柄指定の場合使用。
     */
    public String productName;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コード<BR>
     * <BR>
     * ※銘柄指定の場合は、該当銘柄が扱える市場のみ<BR>
     */
    public String[] marketList;
    
    /**
     * (口座区分一覧)<BR>
     * 0：一般 1：特定
     */
    public String[] taxTypeList;
    
    /**
     * (弁済一覧)<BR>
     * 信用取引弁済の一覧。
     */
    public WEB3MarginRepaymentUnit[] repaymentList;
    
    /**
     * (注文期限区分一覧)<BR>
     * 1：当日限り　@2：出来るまで注文
     */
    public String[] expirationDateTypeList;
    
    /**
     * (発注条件区分一覧)<BR>
     * 0：指定なし　@1：逆指値　@2：W指値
     */
    public String[] orderCondTypeList;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

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
     * @@roseuid 4140477E0182
     */
    public WEB3MarginOpenMarginInputResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginOpenMarginInputResponse(WEB3MarginOpenMarginInputRequest l_request)
    {
        super(l_request);
    }
}
@
