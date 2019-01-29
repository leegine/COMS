head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文入力レスポンス(WEB3EquityOrderBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/19 欒学峰 (中訊) 新規作成
Revesion History : 2004/12/13 桑原 (SRA) 残案件対応　@仕様変更管理台帳　@No.385
Revesion History : 2006/12/25 柴双紅(中訊) モデルNo.1085
*/

package webbroker3.equity.message;

/**
 * （現物株式買付注文入力レスポンス）。<BR>
 * <BR>
 * 現物株式買付注文入力応答　@レスポンスデータクラス
 * @@version 1.0
 */
public class WEB3EquityBuyInputResponse extends WEB3EquityInputCommonResponse
{

    /**
     * (買付可能金額) <BR>
     * 現物株式買付余力<BR>
     */
    public String tradingPower;

    /**
     * 銘柄名 <BR>
     * 直接指定の場合に必要 <BR>
     */
    public String productName;

    /**
     * 市場コード一覧 <BR>
     * 選択可能市場一覧 <BR>
     */
    public String[] marketList;

    /**
     * 口座区分一覧 <BR>
     * 0：一般　@　@1：特定 <BR>
     */
    public String[] taxTypeList;

    /**
     * 注文期限区分一覧 <BR>
     * 1：当日限り　@2：出来るまで注文 <BR>
     */
    public String[] expirationDateTypeList;

    /**
     * 発注条件区分一覧 <BR>
     * 0：指定なし　@1：逆指値　@2：W指値 <BR>
     */
    public String[] orderCondTypeList;

    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;

    /**
     * (分売価格)<BR>
     * 分売価格<BR>
     */
    //public String offFloorOrderPrice;
    

    /**
     * ポリモルフィックタイプ。<BR>
     */
    public static final String PTYPE = "equity_order_buy_input";

    /**
     * シリアルバージョンUID <BR>
     */
    public static final long serialVersionUId = 200405081330L;

    /**
     * @@roseuid 409B37000261
     */
    public WEB3EquityBuyInputResponse()
    {

    }
    /**
     * @@roseuid 409B37000261
     */
    public WEB3EquityBuyInputResponse(WEB3EquityBuyInputRequest l_request)
    {
        super(l_request);
    }
}
@
