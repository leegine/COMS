head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcKeyItemDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : Webbroker3-TrialCalc WEB3TrialCalcKeyItemDef(WEB3TrialCalcKeyItemDef.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.define;

/**
 * Webbroker3-TrialCalc WEB3TrialCalcKeyItemDef.<BR>
 * @@author Umadevi
 * @@version 1.1
 */
public interface WEB3TrialCalcKeyItemDef
{
    /**
     * 1 : 銘柄コード
     */
    public static final String PRODUCT_CODE = "productCode";
    /**
    * 2 : 市場コード
    */
    public static final String MARKET_CODE = "marketCode";
    /**
    * 3 : 株数
    */
    public static final String ORDER_QUANTITY = "orderQuantity";
    /**
    * 4 :時価
    */
    public static final String CURRENT_PRICE = "currentPrice";
    /**
    * 5 :買付単価
    */
    public static final String BUY_PRICE = "buyPrice";
    /**
    * 6 :買付代金
    */
    public static final String BUY_AMOUNT = "buyAmount";
    /**
    * 7 :評価額
    */
    public static final String APPRAISAL_PRICE = "appraisalPrice";
    /**
    * 8 :評価損益
    */
    public static final String APPRAISAL_PROFIT_LOSS = "appraisalProfitLoss";
    /**
    * 9 :評価損益率
    */
    public static final String APPRAISAL_PROFIT_LOSS_RATE = "appraisalProfitLossRate";
	/**
	* 10 :入力／特定区分
	*/
	public static final String INPUT_CAPITALCAIN_DIV = "inputCapitalGainDiv";

}
@
