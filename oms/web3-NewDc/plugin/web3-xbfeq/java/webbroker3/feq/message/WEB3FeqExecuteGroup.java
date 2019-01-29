head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文明細(WEB3FeqExecuteGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (外国株式注文明細)<BR>
 * 外国株式注文明細クラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqExecuteGroup extends Message 
{
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String id;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     */
    public String localProductCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定<BR>
     */
    public String taxType;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：売付<BR>
     */
    public String dealingType;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨<BR>
     * 1：外貨<BR>
     */
    public String settleDiv;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：無条件<BR>
     * 3：寄付<BR>
     * 4：引け<BR>
     * 7：不出来引け成行<BR>
     */
    public String execCondType;
    
    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値<BR>
     */
    public String orderCondType;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     */
    public Date orderBizDate;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     */
    public Date expirationDate;
    
    /**
     * (注文時間)<BR>
     * 注文時間<BR>
     */
    public Date orderDate;
    
    /**
     * (注文数量)<BR>
     * 注文数量<BR>
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価<BR>
     * <BR>
     * ※0の場合は、成行<BR>
     */
    public String limitPrice;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;
    
    /**
     * (約定数量)<BR>
     * 約定数量<BR>
     */
    public String execQuantity;
    
    /**
     * (約定単価)<BR>
     * 約定単価<BR>
     */
    public String execPrice;
    
    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金<BR>
     */
    public String estimatedPrice;
    
    /**
     * (概算受渡代金（外貨）)<BR>
     * 概算受渡代金（外貨）<BR>
     */
    public String foreignEstimatedPrice;
    
    /**
     * (処理状況)<BR>
     * 処理状況<BR>
     * <BR>
     * ※他商品の注文約定照会にある「処理状況」に準拠。<BR>
     */
    public String transactionStateType;
    
    /**
     * (訂正可能フラグ)<BR>
     * 訂正可能フラグ<BR>
     * <BR>
     * true：訂正可能<BR>
     * false：訂正不可<BR>
     */
    public boolean changeFlag;
    
    /**
     * (取消可能フラグ)<BR>
     * 取消可能フラグ<BR>
     * <BR>
     * true：取消可能<BR>
     * false：取消不可<BR>
     */
    public boolean cancelFlag;
    
    /**
     * (注文チャネル)<BR>
     * 注文チャネル<BR>
     * ※コールセンターの場合のみ表示<BR>
     * <BR>
     * 0:営業店<BR>
     * 1：インターネット<BR>
     * 2：コールセンター<BR>
     * 3：モバイル<BR>
     */
    public String orderChannel;
    
    /**
     * (注文経路区分)<BR>
     * 注文経路区分<BR>
     * ※コールセンターの場合のみ表示<BR>
     * <BR>
     * 1：コールセンター<BR>
     * 2：ＰＣ<BR>
     * 3：スリングショット<BR>
     * 4：i-mode<BR>
     * 5：Vodafone<BR>
     * 6：AU<BR>
     * 9：HOST<BR>
     * A：管理者<BR>
     */
    public String orderRootDiv;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     * ※コールセンターの場合のみ表示<BR>
     */
    public String traderCode;
    
    /**
     * (分割約定一覧)<BR>
     * 外国株式分割約定明細の配列<BR>
     */
    public WEB3FeqExecuteUnit[] executeUnits;
    
    /**
     * (外国株式注文明細)<BR>
     * コンストラクタ<BR>
     * @@roseuid 420766AC006D
     */
    public WEB3FeqExecuteGroup() 
    {
     
    }
}
@
