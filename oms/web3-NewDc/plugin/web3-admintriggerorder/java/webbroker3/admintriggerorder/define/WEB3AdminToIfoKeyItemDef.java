head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目定義インタフェイス(WEB3AdminToIfoKeyItemDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 譚漢江(中訊) 新規作成
*/

package webbroker3.admintriggerorder.define;

/**
 * キー項目 定義インタフェイス<BR>
 * 
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3AdminToIfoKeyItemDef
{
    /**
     * branchCode: 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * accountCode: 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * productCode: 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * marketCode: 市場コード
     */
    public static final String MARKET_CODE = "marketCode";

    /**
     * taxType: 口座区分
     */
    public static final String TAX_TYPE = "taxType";

    /**
     * productDiv: 商品区分
     */
    public static final String PRODUCT_DIV = "productDiv";

    /**
     * tradingType: 取引区分
     */
    public static final String TRADING_TYPE = "tradingType";

    /**
     * execCondType: 執行条件
     */
    public static final String EXEC_COND_TYPE = "execCondType";

    /**
     * expirationDate: 注文有効期限
     */
    public static final String EXPIRATION_DATE = "expirationDate";

    /**
     * orderDate: 注文時間
     */
    public static final String ORDER_DATE = "orderDate";

    /**
     * orderBizDate: 発注日
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";

    /**
     * deliveryDate: 受渡日
     */
    public static final String DELIVERY_DATE = "deliveryDate";

}
@
