head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.33.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCommonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文共通明細(WEB3FeqOrderCommonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 郭英 (中訊) 新規作成
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (外国株式注文共通明細)<BR>
 * 外国株式注文共通明細クラス
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3FeqOrderCommonUnit extends Message 
{
    
    /**
     * (注文ID)<BR>
     * 注文ID
     */
    public String orderId;
    
    /**
     * (運用コード)<BR>
     * 運用コード
     */
    public String managementCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     * （注文番号）
     */
    public String requestNumber;
    
    /**
     * (伝票番号)<BR>
     * 伝票番号
     */
    public String orderNumber;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
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
     * (市場コード)<BR>
     * 市場コード
     */
    public String marketCode;
    
    /**
     * (特定口座区分)<BR>
     * 特定口座区分<BR>
     * <BR>
     * 0：一般<BR>
     * 1：特定
     */
    public String taxType;
    
    /**
     * (売買区分)<BR>
     * 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：売付 
     */
    public String dealingType;
    
    /**
     * (決済区分)<BR>
     * 決済区分<BR>
     * <BR>
     * 0：円貨決済<BR>
     * 1：外貨決済
     */
    public String settleDiv;
    
    /**
     * (執行条件)<BR>
     * 執行条件<BR>
     * <BR>
     * 1：無条件<BR>
     * 3：寄付<BR>
     * 4：引け<BR>
     * 7：不出来引け成行
     */
    public String execCondType;
    
    /**
     * (注文有効期限)<BR>
     * 注文有効期限<BR>
     * <BR>
     * ※当日限り注文の場合は、null。
     */
    public Date expirationDate;
    
    /**
     * (発注条件)<BR>
     * 発注条件<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：逆指値<BR>
     * 2：W指値
     */
    public String orderCondType;
    
    /**
     * (発注条件単価)<BR>
     * 発注条件単価<BR>
     * <BR>
     * ※発注条件が”逆指値”もしくは”W指値”の場合、設定される。
     */
    public String orderCondPrice;
    
    /**
     * (発注条件演算子)<BR>
     * 発注条件演算子<BR>
     * <BR>
     * 1：以上<BR>
     * 2：以下<BR>
     * <BR>
     * ※発注条件が”逆指値”もしくは”W指値”の場合、設定される。
     */
    public String condOperator;
    
    /**
     * (W指値用注文単価区分)<BR>
     * W指値用注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値<BR>
     * <BR>
     * ※発注条件が”W指値”の場合、設定される。
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W指値用注文単価)<BR>
     * W指値用注文単価<BR>
     * <BR>
     * ※W指値用注文単価区分が”指値”の場合、設定される。
     */
    public String wLimitPrice;
    
    /**
     * (注文数量)<BR>
     * 注文数量
     */
    public String orderQuantity;
    
    /**
     * (注文単価区分)<BR>
     * 注文単価区分<BR>
     * <BR>
     * 0：成行<BR>
     * 1：指値
     */
    public String orderPriceDiv;
    
    /**
     * (注文単価)<BR>
     * 注文単価
     */
    public String orderPrice;
    
    /**
     * (通貨コード)<BR>
     * 通貨コード
     */
    public String currencyCode;
    
    /**
     * (概算受渡代金)<BR>
     * 概算受渡代金
     */
    public String estimatedPrice;
    
    /**
     * (概算受渡代金（外貨）)<BR>
     * 概算受渡代金（外貨）
     */
    public String foreignEstimatedPrice;
    
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
     * (顧客区分)<BR>
     * 顧客区分<BR>
     * <BR>
     * 0：　@一般<BR>
     * 1：　@同業者<BR>
     * 2：　@自己<BR>
     */
    public String accountDiv;
    
    /**
     * (外国株式注文共通明細)<BR>
     * コンストラクタ
     * @@roseuid 4282F6EE0134
     */
    public WEB3FeqOrderCommonUnit() 
    {
     
    }
}
@
