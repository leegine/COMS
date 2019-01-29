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
filename	WEB3FeqItemNameDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 項目名 定数定義インタフェイス(WEB3FeqItemNameDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 項目名 定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqItemNameDef
{
    /**
     * 運用コード
     */
    public static final String ORDER_EMP_CODE = "ORDER_EMP_CODE";
    
    /**
     * 注文No
     */
    public static final String ORDER_NO = "ORDER_NO";

    /**
     * 伝票No
     */
    public static final String VOUCHER_NO = "VOUCHER_NO";
    
    /**
     * 銘柄コード
     */
    public static final String PRODUCT_CODE = "PRODUCT_CODE";

    /**
     * 発注銘柄コード
     */
    public static final String ORDER_PRODUCT_CODE = "ORDER_PRODUCT_CODE";
    
    /**
     * 銘柄名
     */
    public static final String STANDARD_NAME = "STANDARD_NAME";

    /**
     * 売買
     */
    public static final String TRADE = "TRADE";
    
    /**
     * 決済方法@
     */
    public static final String SETTLE_DIV = "SETTLE_DIV";

    /**
     * 口座
     */
    public static final String ACCOUNT = "ACCOUNT";
    
    /**
     * 市場コード
     */
    public static final String MARKET_CODE = "MARKET_CODE";
    
    /**
     * 市場
     */
    public static final String MARKET = "MARKET";

    /**
     * 執行条件
     */
    public static final String EXECUTION_CONDITION_TYPE = "EXECUTION_CONDITION_TYPE";
    
    /**
     * 注文期限区分
     */
    public static final String ORDER_EXPIRATION_DATE_TYPE = "ORDER_EXPIRATION_DATE_TYPE";

    /**
     * 発注条件
     */
    public static final String ORDER_CONDITION_TYPE = "ORDER_CONDITION_TYPE";
    
    /**
     * 発注条件演算子
     */
    public static final String ORDER_COND_OPERATOR = "ORDER_COND_OPERATOR";

    /**
     * 発注条件単価
     */
    public static final String ORDER_COND_PRICE = "ORDER_COND_PRICE";
    
    /**
     * 訂正指値
     */
    public static final String LIMIT_PRICE = "LIMIT_PRICE";
    
	/**
	 * 発注日
	 */
	public static final String BIZ_DATE = "BIZ_DATE";

	/**
	 * 注文日時
	 */
	public static final String RECEIVED_DATE_TIME = "RECEIVED_DATE_TIME";

	/**
	 * 注文株数
	 */
	public static final String ORDER_QUANTITY = "ORDER_QUANTITY";

	/**
	 * 注文単価
	 */
	public static final String PRICE = "PRICE";
    
}
@
