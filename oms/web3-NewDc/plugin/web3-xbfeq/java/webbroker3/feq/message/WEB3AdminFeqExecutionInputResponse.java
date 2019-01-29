head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力レスポンス(WEB3AdminFeqExecutionInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式出来入力レスポンス)<BR>
 * 管理者外国株式出来入力レスポンスクラス
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * (識別コード)<BR>
     * 識別コード（注文番号）
     */
    public String requestNumber;
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String orderId;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定
     */
    public String taxType;
    
    /**
     * (伝票番号)<BR>
     * 伝票番号
     */
    public String orderNumber;
    
    /**
     * (注文時間)<BR>
     * 注文時間
     */
    public Date orderDate;
    
    /**
     * (発注日)<BR>
     * 発注日
     */
    public Date orderBizDate;
    
    /**
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名
     */
    public String productName;
    
    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：売付
     */
    public String dealingType;
    
    /**
     * (注文数量)<BR>
     * 注文数量
     */
    public String orderQuantity;
    
    /**
     * (注文単価)<BR>
     * 注文単価
     */
    public String orderPrice;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨決済<BR>
     * 1：外貨決済
     */
    public String settleDiv;
    
    /**
     * (約定為替レート)<BR>
     * 約定為替レート
     */
    public String execExchangeRate;
    
    /**
     * @@roseuid 42CE39FE007D
     */
    public WEB3AdminFeqExecutionInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFeqExecutionInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
