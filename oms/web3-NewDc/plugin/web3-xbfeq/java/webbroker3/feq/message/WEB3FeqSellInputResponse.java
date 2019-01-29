head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付入力レスポンス(WEB3FeqSellInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (外国株式売付入力レスポンス)<BR>
 * 外国株式売付入力レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellInputResponse extends WEB3FeqInputCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (決済区分一覧)<BR>
     * 決済区分一覧<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String[] settleDivList;
    
    /**
     * (概算簿価単価)<BR>
     * 概算簿価単価<BR>
     * <BR>
     * ※特定口座の場合、設定<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     * <BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     * <BR>
     */
    public String productName;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * N1：香港 上海 N2：深セン X1：上海<BR>
     * <BR>
     */
    public String marketCode;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     * <BR>
     */
    public String orderQuantity;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * 0：一般　@　@1：特定<BR>
     * <BR>
     */
    public String taxType;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     * A0：USD A3：HKD Z4：EUR<BR>
     * 注文単価区分が「指値」の場合セット<BR>
     * <BR>
     */
    public String currencyCode;
    
    /**
     * @@roseuid 42CE3A030280
     */
    public WEB3FeqSellInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqSellInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
