head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminFeqSortKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ソートキー 定数定義インタフェイス(WEB3AdminFeqSortKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 孟東 (中訊) 新規作成
                 : 2005/08/03 韋念瓊(中訊) レビュー
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * ソートキー 定数定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3AdminFeqSortKeyDef
{
    /**
     * 注文ID
     */
    public static final String ORDER_ID = "orderId";
    
    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";

    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

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
     * 決済区分
     */
    public static final String SETTLE_DIV = "settleDiv";

    /**
     * 注文時間
     */
    public static final String ORDER_START_DATE = "orderStartDate";

    /**
     * 発注日
     */
    public static final String ORDER_BIZ_DATE = "orderBizDate";
    
    /**
     * 執行条件
     */
    public static final String EXECCOND_TYPE = "execCondType";

    /**
     * 注文期限
     */
    public static final String EXPIRATION_DATE_TYPE = "expirationDateType";

    /**
     * 発注条件
     */
    public static final String ORDER_COND_TYPE = "orderCondType";
    
    /**
     * 受渡日
     */
    public static final String DELIVERY_DATE = "deliveryDate";

    /**
     * 運用コード
     */
    public static final String MANAGEMENT_CODE = "managementCode";

    /**
     * 識別コード
     */
    public static final String REQUEST_NUMBER = "requestNumber";

    /**
     * 約定番号
     */
    public static final String EXEC_NO = "execNo";
}
@
