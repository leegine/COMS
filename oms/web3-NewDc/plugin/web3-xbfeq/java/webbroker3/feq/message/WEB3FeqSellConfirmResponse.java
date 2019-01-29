head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付確認レスポンス(WEB3FeqSellConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (外国株式売付確認レスポンス)<BR>
 * 外国株式売付確認レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (売付基準為替)<BR>
     * 売付基準為替<BR>
     */
    public String sellExchange;
    
    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (概算簿価単価)<BR>
     * 概算簿価単価<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * (確認時単価)<BR>
     * 確認時単価<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日<BR>
     */
    public Date checkDate;
    
    /**
     * (取引終了警告市場コード一覧)<BR>
     * 取引終了文言を表示する市場コードの一覧<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (手数料)<BR>
     * 手数料<BR>
     * ※リクエスト.決済区分に応じて、円貨/外貨の値をセットする。<BR>
     */
    public String commission;
    
    /**
     * (手数料消費税)<BR>
     * 手数料消費税<BR>
     * ※リクエスト.決済区分に応じて、円貨/外貨の値をセットする。 <BR>
     */
    public String commissionConsumptionTax;
    
    /**
     * @@roseuid 42CE3A03034B
     */
    public WEB3FeqSellConfirmResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3FeqSellConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
