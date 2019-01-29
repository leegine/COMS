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
filename	WEB3EquitySellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式売付注文入力応答　@レスポンスデータクラス(WEB3EquitySellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 鄭海良(中訊) 新規作成
                 : 2006/08/29 張騰宇(中訊) モデル 972
*/

package webbroker3.equity.message;

/**
 * （現物株式売付注文入力レスポンス）。<BR>
 * <BR>
 * 現物株式売付注文入力応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquitySellInputResponse extends WEB3EquityInputCommonResponse
{

    /**
     * 銘柄コード<BR>
     */
    public String productCode;

    /**
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * 選択可能市場一覧<BR>
     */
    public String[] marketList;

    /**
     * 口座区分<BR>
     * 0：一般　@　@1：特定　@　@5：ストックオプション<BR>
     */
    public String taxType;

    /**
     * 注文株数<BR>
     */
    public String orderQuantity;

    /**
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice;

    /**
     * 注文期限区分一覧<BR>
     * 1：当日限り　@2：出来るまで注文<BR>
     */
    public String[] expirationDateTypeList;

    /**
     * 発注条件区分一覧<BR>
     * 0：指定なし　@1：逆指値　@2：W指値<BR>
     */
    public String[] orderCondTypeList;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_order_sellinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200404301200L;

    /**
     * @@roseuid 4091F8C1033E
     */
    public WEB3EquitySellInputResponse()
    {

    }

    /**
      * コンストラクタ。<BR>
      * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
      *<BR>
      * @@param l_request リクエストオブジェクト
      */
    public WEB3EquitySellInputResponse(WEB3EquitySellInputRequest l_request)
    {
        super(l_request);
    }
}
@
