head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3IfoKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  ZouRui(sinocom)　@新規作成
*/
package webbroker3.ifo.define;

/**
 * キー項目
 *                                                                     
 * @@author Zou Rui & zhang wei
 * @@version 1.1
 */
public interface WEB3IfoKeyItemDef
{

    /**
     * 銘柄名
     */
    public static final String PRODUCT_NAME = "opProductName";

    /**
     * 取引市場
     */
    public static final String TRADE_MARKET = "marketCode";

    /**
     * 取引区分
     */
    public static final String TRADE_DIVISION = "tradingType";

    /**
     * 注文時間
     */
    public static final String ORDER_TIME = "orderDate";

    /**
     * 注文有効期限
     */
    public static final String ORDER_EXPIRATION_DATE = "expirationDate";

    /**
     * 建日
     */
    public static final String OPEN_DATE = "openDate";

    /**
     * 損益
     */
    public static final String INCOME = "income";
    
    /**
     * 損益（諸経費込）
     */
    public static final String INCOME_COST = "incomeCost";
    
    /**
     * 建区分
     */
    public static final String CONTRACT_DIVISION = "contractType";
    
    /**
     * OP銘柄コード
     */
    public static final String PRODUCTCODE = "opProductCode";
    
    /**
     * 決済状態区分
     */
    public static final String SETTLEMENT_STATUS_DIVISION = "settlementType";
    
    /**
     * 先物銘柄コード
     */
    public static final String FUTPRODUCTCODE = "futProductCode";
    
    /**
     * 残高照会銘柄コード
     */
    public static final String BR_PRODUCTCODE = "productCode";
    
    /**
     * 発注日
     */
    public static final String BIZ_DATE = "orderBizDate";
    
    /**
     * 建単価
     */
    public static final String CONTRACT_PRICE = "contractPrice";    
}@
