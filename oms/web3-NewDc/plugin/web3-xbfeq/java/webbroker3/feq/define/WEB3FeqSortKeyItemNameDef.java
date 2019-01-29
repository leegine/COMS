head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSortKeyItemNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : キー項目の項目名 定数定義インタフェイス(WEB3FeqSortKeyItemNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東 (中訊) 新規作成
                   2005/07/25 孟東 (中訊) 追加
                   2006/11/20 徐大方 (中訊) 障害管理 K00004,K00005
*/
package webbroker3.feq.define;

/**
 * キー項目の項目名 定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqSortKeyItemNameDef
{
    /**
     * 運用コード
     */
    public static final String ORDER_EMP_CODE = "managementCode";
    
    /**
     * 注文番号
     */
    public static final String ORDER_NO = "requestNumber";

    /**
     * 部店コード
     */
    public static final String BRANCH_CODE = "branchCode";
    
    /**
     * 顧客コード
     */
    public static final String ACCOUNT_CODE = "accountCode";

    /**
     * 特定口座区分
     */
    public static final String TAX_TYPE_DIV = "taxType";
    
    /**
     * 注文時間
     */
    public static final String ORDER_TIME = "orderDate";

    /**
     * 決済区分
     */
    public static final String SETTLE_DIV = "settleDiv";
    
    /**
     * 市場コード
     */
    public static final String MARKET_CODE = "marketCode";
    
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";

    /**
     * 売買区分
     */
    public static final String BUY_SELL_DIV = "dealingType";
    
    /**
     * 発注日
     */
    public static final String BIZ_DATE = "orderBizDate";

    /**
     * 現地銘柄コード
     */
    public static final String OFFSHORE_PRODUCT_CODE = "localProductCode";

    /**
     * 執行条件
     */
    public static final String EXEC_COND_TYPE = "execCondType";

    /**
     * 発注条件
     */
    public static final String ORDER_COND_TYPE = "orderCondType";

    /**
     * 注文有効期限
     */
    public static final String EXPIRATION_DATE = "expirationDate"; 
    
    /**
     * 処理区分
     */
    public static final String TRANSACTION_DIV = "transactionDiv"; 
    
    /**
     * 作成日付
     */
    public static final String CREATED_TIMESTAMP = "createTimeStamp";

    /**
     * 更新日付
     */
    public static final String LAST_UPDATED_TIMESTAMP = "updateTimeStamp";

    /**
     * 概算評価額(残高数量)
     */
    public static final String ESTIMATED_ASSET_BALANCE_QUANTITY = 
        "estimatedAssetBalanceQuantity";

    /**
     * 概算評価損益(残高数量) 
     */
    public static final String ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY = 
        "estimatedAppraisalProfitLossBalanceQuantity";

    /**
     * 銘柄名
     */
    public static final String PRODUCT_NAME = "standardNameKana";
    
    /**
     * 更新者コード
     */
    public static final String UPDATER_CODE = "updaterCode";
    
    /**
     * 経路区分
     */
    public static final String ORDER_ROOT_DIV = "orderRootDiv";
    
    /**
     * 受付区分
     */
    public static final String ACCEPT_DIV = "acceptDiv";
}
@
