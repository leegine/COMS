head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMProductCondListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式銘柄条件予定一覧リクエスト(WEB3AdminPMProductCondListRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminEquityLargeItemDivDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySmallItemDivDef;

/**
 * (管理者・株式銘柄条件予定一覧リクエスト)<BR>
 * <BR>
 * 管理者・株式銘柄条件予定一覧リクエストクラス<BR>
 * <BR>
 * WEB3AdminPMProductCondListRequest<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminPMProductCondListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_pm_product_cond_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * （銘柄コード）<BR>
     * <BR>
     * 銘柄コード<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     */
    public String productCode;

    /**
     * （大項目区分）<BR>
     * <BR>
     * 大項目区分<BR>
     * <BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * largeItemDiv<BR>
     * <BR>
     * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     */
    public String largeItemDiv;

    /**
     * （小項目区分）<BR>
     * <BR>
     * ※定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * smallItemDiv<BR>
     * <BR>
     * ※Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * values<BR>
     * <BR>
     */
    public String smallItemDiv;

    /**
     * （適用期間From）<BR>
     * <BR>
     * 予定の適用期間From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyStartDate;

    /**
     * （適用期間To）
     * <BR>
     * 予定の適用期間To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * applyEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String applyEndDate;

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMProductCondListRequest.class);

    /**
     * @@roseuid 41FD91FA0213
     */
    public WEB3AdminPMProductCondListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）銘柄コードチェック<BR>
     * 　@this.銘柄コード != nullの場合は、以下のチェックを行う。<BR>
     * 　@１－１）this.銘柄コードが以下の条件に該当する場合、<BR>
     * 　@　@　@　@　@「銘柄コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@　@・this.銘柄コード.length != 5<BR>
     * 　@　@　@　@　@・this.銘柄コード != 数値<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * ２）大項目区分チェック<BR>
     * 　@this.大項目区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）this.大項目区分の値が、定義値(*1)のいづれにも<BR>
     * 　@　@　@　@　@該当しない場合、「大項目区分が未定義の値」の<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * ３）小項目区分チェック<BR>
     * 　@this.小項目区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@３－１）this.小項目区分の値が、定義値(*1)の値のいづれにも<BR>
     * 　@　@　@　@　@該当しない場合、「小項目区分が未定義の値」の<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01450<BR>
     * <BR>
     * ４）適用期間Fromチェック<BR>
     * 　@this.適用期間From != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@４－１）this.適用期間Fromを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー(適用期間From)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01442<BR>
     * <BR>
     * ５）適用期間Toチェック<BR>
     * 　@this.適用期間To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@５－１）this.適用期間Toを日付型に変換できない場合、<BR>
     * 　@　@　@　@　@「入力日付エラー(適用期間To)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01443<BR>
     * <BR>
     * 　@５－２）this.適用期間From  == nullの場合、<BR>
     * 　@　@　@　@　@「日付未入力エラー(適用期間From)」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01444<BR>
     * <BR>
     * ６）適用期間整合性チェック<BR>
     * 　@this.適用期間From != null　@かつ　@this.適用期間To != nullの場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * 　@６－１）this.適用期間From > this.適用期間Toの場合、<BR>
     * 　@　@　@　@　@「適用期間From/To整合性エラー」の例外をスローする。<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01446<BR>
     * <BR>
     * (*1)定義値についてはDBレイアウト<BR>
     * 　@「株式銘柄条件設定テーブル仕様.xls」参照<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class)
     * <BR>
     * <BR>
     * 1)productCode check<BR>
     * 　@Check the followings if this.productCode != null<BR>
     * 　@1-1)If this.productCode meets with the following conditions<BR>
     * 　@　@　@　@　@Throw the exception "productCode error"<BR>
     * 　@　@　@　@　@・this.productCode.length != 5<BR>
     * 　@　@　@　@　@・this.productCode != numerical value<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 2)largeItemDiv check<BR>
     * 　@Check the followings if this.largeItemDiv != null<BR>
     * 　@2-1) If the value of this.largeItemDiv meets neither of defined values(*1)<BR>
     * 　@　@　@　@　@Throw the exception "largeItemDiv is an undefined value"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01449<BR>
     * <BR>
     * 3)smallItemDiv check<BR>
     * 　@Check the following if this.smallItemDiv != null<BR>
     * 　@3-1) If the value of this.smallItemDiv meets neither of defined
     * values(*1),<BR>
     * 　@　@　@　@　@Throw the exception "smallItemDiv is an undefined value"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01450<BR>
     * <BR>
     * 4)applyStartDate check<BR>
     * 　@Check the following if this.applyStartDate != null<BR>
     * 　@4-1) If it is unable to change this.applyStartDate to Date type,<BR>
     * 　@　@　@　@　@Throw the exception "input date error(applyStartDate)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01442<BR>
     * <BR>
     * 5) applyEndDate check<BR>
     * 　@Check the following if this.applyEndDate != null<BR>
     * 　@5-1)It is unable to change this.applyEndDate to Date type<BR>
     * 　@　@　@　@　@Throw the exception "input date error(applyEndDate)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01443<BR>
     * <BR>
     * 　@5-2) If this.applyStartDate  == null<BR>
     * 　@　@　@　@　@Throw the exception "no input date error(applyStartDate)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01444<BR>
     * <BR>
     * 6)correpondence of apply period check<BR>
     * 　@Check the followings if this.applyStartDate != null and this.applyEndDate !=
     * null<BR>
     * 　@6-1) If this.applyStartDate > this.applyEndDate<BR>
     * 　@　@　@　@　@Throw the exception "applyStartDate/applyEndDate correspondence
     * error"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01446<BR>
     * <BR>
     * (*1)Refer to the DB layout "eqtype_product_condition_table.xls" about defined
     * valuesBR>
     * <BR>
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     * @@roseuid 4181B32802B9
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate";
        log.entering(STR_METHOD_NAME);
        final int l_intFive = 5;
        int l_productCodeProductByteLength = WEB3StringTypeUtility.getByteLength(this.productCode);

        // 1-1if productCode is null, throw Exception.
        if (productCode != null)
        {
            if ((l_productCodeProductByteLength != l_intFive)
                || (!WEB3StringTypeUtility.isNumber(productCode)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /* 2-1  If the value of this.largeItemDiv meets neither
         of defined values of  eqtype_product_condition_table.xls throw exception*/
        if (largeItemDiv != null)
        {
            if ((!WEB3AdminEquityLargeItemDivDef.TRADING_REGULATION.equals(largeItemDiv))
                && (!WEB3AdminEquityLargeItemDivDef.BASIC_INFO.equals(largeItemDiv))
                && (!WEB3AdminEquityLargeItemDivDef.MARGIN_PRODUCT_INFO.equals(largeItemDiv))
                && (!WEB3AdminEquityLargeItemDivDef.DEPOSIT_RATE.equals(largeItemDiv))
                && (!WEB3AdminEquityLargeItemDivDef.SUBSTITUTE_SECURITY_INFO.equals(largeItemDiv))
                && (!WEB3AdminEquityLargeItemDivDef.PRICE_INFO.equals(largeItemDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01449,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        /* 3-1  If the value of this.smallItemDiv meets neither of defined
         values of  eqtype_product_condition_table.xls throw exception*/
        if (smallItemDiv != null)
        {
            if ((!WEB3AdminEquitySmallItemDivDef.TRADE_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_TRADE_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_TRADE_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.BUY_CASH_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SELL_CASH_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_SYS_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_SYS_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_SYS_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_SYS_STOP
                .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_SYS_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_SYS_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_GEN_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_GEN_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_CLOSE_MARGIN_GEN_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_CLOSE_MARGIN_GEN_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_SWAP_MARGIN_GEN_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_SWAP_MARGIN_GEN_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.BUY_SPOT_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef
                    .SELL_SPOT_MARKET_ORD_DES_STOP
                    .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_MS_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_MS_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_CMS_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef
                    .SHORT_CMS_MARKET_ORD_DES_STOP
                    .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_MG_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_MG_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_CMG_MARKET_ORD_DES_STOP.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef
                    .SHORT_CMG_MARKET_ORD_DES_STOP
                    .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_STOP.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.STANDARD_NAME.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LOT_SIZE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.COMPULSIVE_LIMITED_UNIT.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.OPEN_OTC_DIV.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LIST_TYPE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.TODAY_DEP_FUND_REG.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MINI_STOCK_MARKET.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.BUY_MINI_STOCK_TIME_LIMIT.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SELL_MINI_STOCK_TIME_LIMIT.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MARGIN_SYS_PRODUCT_TYPE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MARGIN_GEN_PRODUCT_TYPE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LONG_MARGIN_DEPOSIT_RATE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SHORT_MARGIN_DEPOSIT_RATE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef
                    .LONG_CASH_MARGIN_DEPOSIT_RATE
                    .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef
                    .SHORT_CASH_MARGIN_DEPOSIT_RATE
                    .equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.MARGIN_RATIO.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.ESTIMATION_PRICE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.SECURITIES_ESTIMATION_RATIO.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LAST_CLOSING_PRICE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.BASE_PRICE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_TYPE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.PRICE_RANGE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.COMPULSIVE_PRICE_RANGE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.PRICE_RANGE_UNIT_TYPE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.LOW_COMPULSIVE_PRICE_RANGE.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.HIGH_COMPULSIVE_PRICE_RANGE.
                equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.PRIMARY_MARKET.equals(smallItemDiv))
                && (!WEB3AdminEquitySmallItemDivDef.CAPITAL_GAIN_TAX_DEALING.equals(smallItemDiv)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01450,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

        }

        /* 4-1 If applyStartDate is unable to change this.applyStartDate to
         Date type throw exception*/
        if (this.applyStartDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.applyStartDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01442,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 5-1 If applyEndDate is unable to change this.applyStartDate to Date type throw exception
        if (applyEndDate != null)
        {
            if (!WEB3StringTypeUtility.isDateStr(this.applyEndDate, "yyyyMMdd"))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01443,
                    this.getClass().getName() + "." + STR_METHOD_NAME);

                // 5-2 if applyStartDate is  null throw exception
            }
            if (applyStartDate == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 6-1 if applyStartDate > applyEndDate throw exception
        if ((applyStartDate != null) && (applyEndDate != null))
        {
            Date l_applyStartDate = WEB3DateUtility.getDate(applyStartDate, "yyyyMMdd");
            Date l_applyEndDate = WEB3DateUtility.getDate(applyEndDate, "yyyyMMdd");

            int l_resultcompare = WEB3DateUtility.compare(l_applyStartDate, l_applyEndDate);
            if (l_resultcompare == 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01446,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /** (non-Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminPMProductCondListResponse(this);
    }
}
@
