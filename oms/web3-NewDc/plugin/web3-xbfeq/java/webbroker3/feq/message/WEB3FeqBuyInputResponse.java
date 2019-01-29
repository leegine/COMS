head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.37.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式買付入力レスポンス(WEB3FeqBuyInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (外国株式買付入力レスポンス)<BR>
 * 外国株式買付入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBuyInputResponse extends WEB3FeqInputCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (買付可能額（円貨）)<BR>
     * 買付可能額（円貨）<BR>
     */
    public String tradingPowerYen;
    
    /**
     * (買付可能額（外貨）一覧)<BR>
     * 買付可能額（外貨）の配列<BR>
     * <BR>
     * ※リクエストで銘柄コードが指定されてた場合は、その銘柄の通貨についてのみを設定。<BR>
     */
    public WEB3FeqTradingPowerUnit[] tradingPowerFrnList;
    
    /**
     * (口座区分一覧)<BR>
     * 口座区分一覧<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String[] taxTypeList;
    
    /**
     * (決済区分一覧)<BR>
     * 決済区分一覧<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String[] settleDivList;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※リクエストで銘柄コードが指定されてた場合、設定。<BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     * <BR>
     * ※リクエストで銘柄コードが指定されてた場合、設定。<BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     * <BR>
     * ※リクエストで銘柄コードが指定されてた場合、設定。<BR>
     */
    public String productName;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※N1：香港 N2：深セン X1：上海。<BR>
     */
    public String marketCode;
    
    /**
     * @@roseuid 42CE3A06002E
     */
    public WEB3FeqBuyInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
