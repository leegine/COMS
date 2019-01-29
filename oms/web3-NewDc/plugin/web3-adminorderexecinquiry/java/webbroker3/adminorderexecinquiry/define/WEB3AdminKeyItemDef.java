head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminKeyItemDef(WEB3AdminKeyItemDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * WEB3AdminKeyItemDef<BR>
 *
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminKeyItemDef
{
    /**
     * BRANCH_CODE
     */
    public final static String BRANCH_CODE = "branchCode";

    /**
     * ACCOUNT_CODE
     */
    public final static String ACCOUNT_CODE = "accountCode";

    /**
     * SONAR_TRADER_CODE
     */
    public static final String SONAR_TRADER_CODE = "sonarTraderCode";

    /**
     * PRODUCT_CODE
     */
    public final static String PRODUCT_CODE = "productCode";

    /**
     * MARKET_CODE
     */
    public final static String MARKET_CODE = "marketCode";

    /**
     * TAX_TYPE
     */
    public final static String TAX_TYPE = "taxType";

    /**
     * TRADING_TYPE
     */
    public final static String TRADING_TYPE = "tradingType";

    /**
     * ORDER_DATE
     */
    public final static String ORDER_DATE = "orderDate";

    /**
     * ORDER_BIZ_DATE
     */
    public final static String ORDER_BIZ_DATE = "orderBizDate";

    /**
     * REPAYMENT_DIV
     */
    public final static String REPAYMENT_DIV = "repaymentDiv";

    /**
     * PRICE_COND_TYPE
     */
    public final static String PRICE_COND_TYPE = "priceCondType";

    /**
     * EXEC_COND_TYPE
     */
    public final static String EXEC_COND_TYPE = "execCondType";

    /**
     * EXPIRATION_DATE
     */
    public final static String EXPIRATION_DATE = "expirationDate";

    /**
     * ORDER_COND_TYPE
     */
    public final static String ORDER_COND_TYPE = "orderCondType";

    /**
     * DELIVERY_DATE
     */
    public final static String DELIVERY_DATE = "deliveryDate";

}@
