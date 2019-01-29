head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3EquityKeyItemDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20  WuYanFei(sinocom)　@新規作成
Revesion History : 2004/12/10 桑原 (SRA) 修正
*/
package webbroker3.equity.define;

/**
 * キー項目
 *                                                                     
 * @@author Wu Yan Fei
 * @@version 1.1
 */
public interface WEB3EquityKeyItemDef
{
    /**
     * 口座区分
     */
    public static final String ACCOUNTTYPE = "taxType";
    
	/**
	 * 弁済区分
	 */
	public static final String REPAYMENT_DIV = "repaymentDiv";
    
    /**
     * 弁済期限値
     */
    public static final String REPAYMENTNUM = "repaymentTimeLimit";

    /**
     * 建日
     */
    public static final String OPEN_DATE = "openDate";

    /**
     * 評価損益
     */
    public static final String INCOME = "appraisalProfitLoss";
    
	/**
	 * 評価損益（諸経費考慮）
	 */
	public static final String INCOME_COST = "appraisalProfitLossCost";
	
    /**
     * 建区分
     */
    public static final String CONTRACT_DIVISION = "contractType";

    /**
     * 銘柄コード
     */
    public static final String PRODUCTCODE = "productCode";

    /**
     * 期日
     */
    public static final String CLOSEDATE = "closeDate";
    
    /**
     * 取引区分
     */
    public static final String TRADETYPE = "tradingType";
    
    /**
     * 執行条件
     */
    public static final String EXECUTE_COND = "execCondType";
    
    /**
     * 発注条件
     */
    public static final String SEND_COND = "orderCondType";
    
    /**
     * 注文時間
     */
    public static final String ORDER_TIME = "orderDate";
    
    /**
     * 注文期限
     */
    public static final String ORDER_TIMELIMIT = "expirationDate";

    /**
     * 市場コード
     */
    public static final String TRADEMARKET = "marketCode";
    
    /**
     * 売買区分
     */
    public static final String DEALINGTYPE = "dealingType";
    
    /**
     * 値段条件
     */
    public static final String PRICE_COND = "priceCondType";
    
    /**
     * 発注日
     */
    public static final String SEND_DATE = "orderBizDate";
    
    /**
     * 受付開始日時
     */
    public static final String ORDER_START_DATE_TIME = "orderStartDatetime";
    
    /**
     * 受付終了日時
     */
    public static final String ORDER_END_DATE_TIME = "orderEndDatetime";
    
    /**
     * 概算評価額(残高株数)
     */
    public static final String ESTIMATED_ASSET_BALANCE_QUANTITY = "estimatedAssetBalanceQuantity";
    
    /**
     * 概算評価損益(残高株数)
     */
    public static final String ESTIMATED_INCOME_BALANCE_QUANTITY = "estimatedAppraisalProfitLossBalanceQuantity";
    
    /**
     * 建単価
     */
    public static final String CONTRACT_PRICE = "contractPrice";
}
@
