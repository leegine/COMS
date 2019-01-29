head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3ToSuccKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17  呉艶飛(sinocom)　@新規作成
*/
package webbroker3.triggerorder.define;

/**
 * キー項目
 *                                                                     
 * @@author 呉艶飛
 * @@version 1.0
 */
public interface WEB3ToSuccKeyItemDef
{

    /**
     * 商品区分
     */
    public static final String COMMODITY_TYPE = "commodityType";
    
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * 市場コード
     */
    public static final String MARKET_CODE = "marketCode";

    /**
     * 口座区分
     */
    public static final String TAX_TYPE = "taxType";
    
    /**
     * 取引区分
     */
    public static final String TRADING_TYPE = "tradingType";

    /**
     * 弁済区分
     */
    public static final String REPAYMENT_DIV = "repaymentDiv";
    
    /**
     * 値段条件
     */
    public static final String PRICE_COND_TYPE = "priceCondType";
        
    /**
     * 執行条件
     */
    public static final String EXEC_COND_TYPE = "execCondType";
    
    /**
     * 発注条件区分
     */
    public static final String ORDER_COND_TYPE = "orderCondType";
    
    /**
     * 注文時間 
     */
    public static final String ORDER_DATE = "orderDate";
    
    /**
     * 発注日
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";
    
    /**
     * 注文有効期限
     */
    public static final String EXPIRATION_DATE = "expirationDate";

}
@
