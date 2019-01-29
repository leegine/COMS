head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信取引時間管理(WEB3MutualFundTradingTimeManagement)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06  黄建 (中訊) 新規作成
Revesion History : 2004/12/06 于美麗 (中訊) 残対応
Revesion History : 2006/10/12 周捷 (中訊) モデル 498
Revesion History : 2007/02/07 唐性峰 (中訊) モデル 538
*/
package webbroker3.mf;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundProduct;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.define.WEB3MFOrderQuantityType;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信取引時間管理<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualFundTradingTimeManagement
    extends WEB3GentradeTradingTimeManagement
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundTradingTimeManagement.class);

    /**
     * 注文受付締切時間を取得する。<BR>
     * <BR>
     * １）　@取引カレンダコンテキストを取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute()をコールして、<BR>
     * 取引時間カレンダコンテキスト<BR>
     * 　@を取得する。<BR>
     * 　@［getAttributeに渡すパラメタ］<BR>
     * 　@　@属性名： "web3.tradingcalendarcontext"<BR>
     * <BR>
     * ２）　@証券会社オブジェクトを取得する。<BR>
     * 　@拡張アカウントマネージャ.getInstitution()をコールして、<BR>
     * 証券会社オブジェクトを取得する。<BR>
     * 　@［getInstitutionに渡すパラメタ］<BR>
     * 　@　@証券会社コード： 取引時間コンテキスト.証券会社コード<BR>
     * <BR>
     * ３）　@拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@拡張投信銘柄マネージャ.get 、<BR>
     * 拡張投信取引銘柄オブジェクトを取得する。<BR>
     * 　@［get投信取引銘柄に渡すパラメタ］<BR>
     * 　@　@証券会社： 取得した証券会社オブジェクト<BR>
     * 　@　@銘柄コード： 取引時間コンテキスト.銘柄コード<BR>
     * <BR>
     * ４）取引カレンダ詳細オブジェクトを取得する。<BR>
     * <BR>　@GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalenda<BR>
     *       rDetails()をコールして<BR>
     * 　@取引カレンダ詳細オブジェクトを取得する。<BR>
     * 　@［getTradingCalendarDetailsに渡すパラメタ]<BR>
     * 　@　@取引銘柄ID： 取得した拡張投信取引銘柄.getTradedProductId()の戻り値<BR>
     * <BR>
     * ５）　@取得した取引カレンダ詳細.getTradeCloseTime()の戻り値を返す。<BR>
     * @@return String
     * @@roseuid 40B1D31D00AB
     */
    public static String getOrderCloseTime()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderCloseTime()";
        log.entering(STR_METHOD_NAME);

        //取引カレンダ詳細オブジェクトを
        TradingCalendarDetails l_tradeCalendarDetails = null;

        //拡張投信取引銘柄クラス
        WEB3MutualFundTradedProduct l_mutualFundTradedProduct = null;

        //１）　@取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        if (l_context == null)
           {
               log.debug("致命的なシステムエラー。");
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   "WEB3MutualFundTradingTimeManagement"+ "." + STR_METHOD_NAME,
                   "取引カレンダコンテキストを取得できない");
           }

        // ２）　@証券会社オブジェクトを取得する。

        //証券会社
        Institution l_institution = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //拡張アカウントマネージャ取得する。
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //証券会社取得
            l_institution =
                (Institution) l_accMgr.getInstitution(
                    l_context.getInstitutionCode());

            // ３）　@拡張投信取引銘柄オブジェクトを取得する。
            WEB3MutualFundProductManager l_mutualFundProductManager =
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            l_mutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) l_mutualFundProductManager.getMutualFundTradedProduct(
                    l_institution,
                    l_context.getProductCode());

            //４）取引カレンダ詳細オブジェクトを取得する。
            long l_lngProductId =
                l_mutualFundTradedProduct.getTradedProductId();
            l_tradeCalendarDetails =
                GtlUtils.getFinObjectManager().getTradingCalendarModel().getTradingCalendarDetails(l_lngProductId);
            log.debug("取引カレンダ詳細オブジェクトProductId  = " + l_lngProductId );
            log.debug("取引カレンダ詳細オブジェクトgetTradeCloseTime  = " + l_tradeCalendarDetails.getTradeCloseTime() );
        }
        catch (NotFoundException l_ex)
        {
            log.error("Not Found 該当の補助口座  with " +
                "(証券会社)l_institution =  " +
                    l_institution +
                " and (銘柄コード)l_strProductCode = " +
                    l_context.getProductCode());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ５）　@取得した取引カレンダ詳細.getTradeCloseTime()の戻り値を返す。
        log.exiting(STR_METHOD_NAME);
        return l_tradeCalendarDetails.getTradeCloseTime();
    }

    /**
     * isトリガ発行<BR>
     * 市場へリアルタイムにトリガを発行するかを判定する。<BR>
     * （取引時間管理.isトリガ発行()のオーバーライド）<BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）　@受付日の営業日区分判定。<BR>
     * 　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は<BR>
     * ”休日”と判定し、falseを返却し処理を終了する。<BR>
     * 　@以外の場合、受付日時の日付部分でカレンダテーブルを検索し、<BR>
     * 行の営業日区分を取得する。行が取得できなかった場合は、営業日<BR>
     * 区分を”通常営業日”とする。<BR>
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>　@ThreadLocalSystemAttributesRegistry.ge
     * tAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * ４） 外国投信（半日営業日） または、外貨MMFの場合、falseを返す。<BR>
     * <BR>
     * 　@　@４－１）　@拡張投信銘柄オブジェクトを生成する。<BR>
     * 　@　@　@　@　@　@　@拡張投信銘柄マネージャのget投信銘柄 (証券会社, 銘柄コード)をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[get投信銘柄の引数]<BR>
     * 　@　@　@　@　@　@　@証券会社：拡張アカウントマネージャ.getＩnstitution(取引カレンダコンテキスト.証券会社コード)<BR>
     * 　@　@　@　@　@　@　@銘柄コード：取引カレンダコンテキスト.銘柄コード <BR>
     * <BR>
     * 　@　@４－２） ①@または②の場合falseを返す。<BR>
     * <BR>
     * 　@　@　@　@①@　@営業日区分 = 半日営業日　@かつ <BR>
     * 　@　@      （ （拡張投信銘柄.is外国投信()==true）　@または <BR>
     * 　@　@    　@　@  （拡張投信銘柄.isFWF()==true かつ 投信銘柄.通貨コード != ”円”） ）<BR>
     * <BR>
     *     　@　@②　@拡張投信銘柄.is外貨MMF() ==true <BR>
     * ５）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード（SONAR）：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@商品コード：　@取引時間コンテキストの同名プロパティ
     * 　@営業日区分：　@判定した営業日区分<BR>
     * 　@開始時間 <= （受付日時の時間部分） <= 終了時間 <BR>
     * <BR>　@
     * 　@上記に一致する行の「市場トリガ発行」項目が”SONARへMQトリガを<BR>
     * 実施する”であればtrue、以外はfalseを返却する。<BR>
     * 　@上記に一致する行が存在しない場合は、該当データなし<BR>
     * （システムエラー）として例外をスローする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * @@param l_strOrderingCondition - 発注条件<BR>
     * 　@0： DEFAULT<BR>
     * 　@1： 逆指値<BR>
     * 　@2： W指値<BR>
     *
     * @@return java.lang.boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 4014CD9D000A
     */
    public static boolean isSubmitMarketTrigger(String l_strOrderingCondition)
        throws WEB3SystemLayerException
    {
        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strBizDateType;
        String l_strProductCode;
        final String STR_METHOD_NAME = "isSubmitMarketTrigger(String)";
        log.entering(STR_METHOD_NAME);

        // １）　@ThreadLocalSystemAttributesRegistryより、受付日を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        // ２）　@受付日の営業日区分判定。
        //営業日区分の取得
        l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug(STR_METHOD_NAME + "：受付日時は土曜日又は日曜日の場合です。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //４）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //４） 外国投信（半日営業日） または、外貨MMFの場合、falseを返す。

        //　@　@４－１）　@拡張投信銘柄オブジェクトを生成する。
        //　@　@　@　@　@　@　@　@　@拡張投信銘柄マネージャのget投信銘柄 (証券会社, 銘柄コード)をコールする。
        //　@　@　@　@　@　@　@　@　@[get投信銘柄の引数]
        //　@　@　@　@　@　@　@　@　@証券会社：拡張アカウントマネージャ.getＩnstitution(取引カレンダコンテキスト.証券会社コード)
        //　@　@　@　@　@　@　@　@　@銘柄コード：取引カレンダコンテキスト.銘柄コード
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3MutualFundProduct l_mutualFundProduct = null;
        try
        {
            l_mutualFundProduct =
                (WEB3MutualFundProduct)l_mutualManager.getMutualFundProduct(
                    l_accMgr.getInstitution(l_clendarContext.getInstitutionCode()),
                    l_clendarContext.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
        }

        //　@　@４－２） ①@または②の場合falseを返す。
        //    　@　@①@　@営業日区分 = 半日営業日　@かつ
        //　@　@         （ （拡張投信銘柄.is外国投信()==true）　@または
        //　@　@　@　@    　@　@  （拡張投信銘柄.isFWF()==true かつ 投信銘柄.通貨コード != ”円”） ）
        //    　@　@②　@拡張投信銘柄.is外貨MMF() ==true
        boolean l_blnIsForeignFund = l_mutualFundProduct.isForeignFund();
        boolean l_blnIsFWF = l_mutualFundProduct.isFWF();
        boolean l_blnIsFrgnMmf = l_mutualFundProduct.isFrgnMmf();
        if ((WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateType)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateType))
            && (l_blnIsForeignFund
                || (l_blnIsFWF 
                    && !WEB3MFOrderQuantityType.EN.equals(l_mutualFundProduct.getCurrencyCode()))))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        if (l_blnIsFrgnMmf)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //部店コード
        l_strBranchCode = l_clendarContext.getBranchCode();
        //市場コード
        l_strMarketCode = l_clendarContext.getMarketCode();
        //受付時間区分
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //商品コード
        l_strProductCode = l_clendarContext.getProductCode();

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、データ不整合として
        //例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        // 　@取引時間コンテキスト.市場コード
        // 　@取引時間コンテキスト.受付時間区分
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    "データ不整合");
        }

        //受付時間の取得(取得した日時の時間部分)
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        // ５） 取引時間テーブルを検索する
        //「市場トリガ発行」項目が”SONARへMQトリガを実施する”であれば
        // true、以外はfalseを返却する。

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and start_time <= ? ");
        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere = {
            l_strInstitutionCode, //証券会社コード
            l_strBranchCode,      //部店コード
            l_strMarketCode,      //市場コード
            l_strTradingTimeType, //受付時間区分
            l_strProductCode,     //商品コード
            l_strBizDateType,     //営業日区分
            l_strAcceptTime,      //受付時間
            l_strAcceptTime       //受付時間
            };

        TradingTimeParams l_tradingTimeParams = null;
        List l_lisRecords;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWhere);
        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dfe.getMessage(),
                dfe);
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dqe.getMessage(),
                dqe);
        }
        catch (DataNetworkException dne)
        {
            log.error(STR_METHOD_NAME, dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        //件数チェック
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "取引時間テーブル検索： 件数 = 0");
        }
        l_tradingTimeParams = (TradingTimeParams)l_lisRecords.get(0);

        //get市場トリガ発行
        String l_strSubmit_market_trigger = l_tradingTimeParams.getSubmitMarketTrigger();

        //「市場トリガ発行」項目が”SONARへMQトリガを実施する”であればtrue、以外はfalseを返却する。
        if (l_strSubmit_market_trigger.equals(WEB3SubmitMarketTriggerDef.TRIGGER))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * （親クラス”Date get発注日(Date)”のオーバーライド）<BR>
     *     this.get投信発注日()にて発注日を取得する。 <BR>
     *     取得した発注日と引数の確認時発注日が違う日付であれば <BR>
     *     例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)完了時処理で使用する。<BR>
     * <BR>
     * @@param l_datCheckDate - 確認時発注日<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getOrderBizDate(Date l_datCheckDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        // this.get投信発注日()にて発注日を取得する
        Date l_datCurrentBizDate = getMutualOrderBizDate();

        // 取得した発注日と引数の確認時発注日が違う日付であれば
        if(WEB3DateUtility.compareToDay(l_datCurrentBizDate, l_datCheckDate) != 0 )
        {
            log.debug("取得した発注日と引数の確認時発注日が違う日付である");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "取得した発注日と引数の確認時発注日が違う日付である");
        }

        //　@取得した発注日を返す。
        log.exiting(STR_METHOD_NAME);
        log.debug("発注日 = " + l_datCurrentBizDate);
        return l_datCurrentBizDate;
    }

    /**
     * (get投信発注日)<BR>
     * 海外市場カレンダーを考慮した発注日を取得する。 <BR>
     *<BR>
     *１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 <BR>
     *　@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     *      "xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     *２）　@受付日の営業日区分判定。 <BR>
     *　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は営業日区分を”非営業日”とする。<BR>
     *　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、行の営業日区分を取得する。 <BR>
     *　@行が取得できなかった場合は、営業日区分を”通常営業日”とする。<BR>
     * <BR>
     *３）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。<BR>
     *　@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     *４）　@国内発注日取得 <BR>
     *　@this.get発注日()を用いて、発注日を取得する。 <BR>
     * <BR>
     *５）　@海外発注日取得 <BR>
     *　@－取得した発注日を引数に、投信海外市場カレンダ.is休日()をコールする。 <BR>
     *　@－is休日()＝true の場合、発注日計算を用いて翌営業日を取得する。<BR>
     *　@（海外発注日が取得できるまで、繰り返す） <BR>
     * <BR>
     *　@－is休日()＝false の場合、その発注日を返却する。<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getMutualOrderBizDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //３）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        //　@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        //４）　@国内発注日取得
        //　@this.get発注日()を用いて、発注日を取得する。
        Date l_datNationalBizDate =
            WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //５）　@海外発注日取得
        //　@－取得した発注日を引数に、投信海外市場カレンダ.is休日()をコールする。
        WEB3AdminMutualFrgncal l_adminMutualFrgncal =
            new WEB3AdminMutualFrgncal();

        String l_strInstitutionCode = l_context.getInstitutionCode();
        String l_strProductCode = l_context.getProductCode();
        boolean l_blnIsHoliday =
            l_adminMutualFrgncal.isHoliday(
                l_strInstitutionCode,
                l_strProductCode,
                new Timestamp(l_datNationalBizDate.getTime()));

        //　@－is休日()＝true の場合、発注日計算を用いて翌営業日を取得する。
        //　@（海外発注日が取得できるまで、繰り返す）
        while(l_blnIsHoliday)
        {
            l_datNationalBizDate =
                new WEB3GentradeBizDate(new Timestamp(l_datNationalBizDate.getTime())).roll(1);
            log.debug("国内発注日 = " + l_datNationalBizDate);

            l_blnIsHoliday =
                l_adminMutualFrgncal.isHoliday(
                    l_strInstitutionCode,
                    l_strProductCode,
                    new Timestamp(l_datNationalBizDate.getTime()));
        }

        log.exiting(STR_METHOD_NAME);
        log.debug("投信発注日 = " + l_datNationalBizDate);
        return l_datNationalBizDate;
    }

    /**
     * (get投信翌営業日)<BR>
     * 海外運用投信銘柄を考慮した現在日付からの翌営業日を取得する。 <BR>
     *（管理者機@能銘柄条件登録で使用） <BR>
     * <BR>
     * <BR>
     *１）this.get投信発注日()から、発注日を取得する。  <BR>
     * <BR>
     *２）　@以下を繰り返す。  <BR>
     *(1)　@翌営業日（国内市場のみを考慮）の取得  <BR>
     *　@・営業日計算オブジェクトを生成する。  <BR>
     *　@　@　@[コンストラクタに渡す引数]  <BR>
     *　@　@　@　@基準日＝前回のroll()の戻り値  <BR>
     *　@・roll()をコール。  <BR>
     *　@　@　@[rollに渡す引数]  <BR>
     *　@　@　@　@加算／減算日数＝１  <BR>
     * <BR>
     *(2)　@以下の条件で「カレンダーテーブル」を検索。 <BR>
     *　@　@　@[検索条件]  <BR>
     *　@　@　@　@日付＝(1)のroll()の戻り値 and  <BR>
     *　@　@　@　@営業日区分＝”非営業日”  <BR>
     * <BR>
     *(3)　@(2)の検索結果＝0件の場合、海外市場カレンダー.is休日()をコールする。  <BR>
     *　@　@　@　@証券会社コード＝引数.証券会社コード  <BR>
     *　@　@　@　@銘柄コード＝引数.銘柄コード  <BR>
     *　@　@　@　@日付＝(1)のroll()の戻り値  <BR>
     * <BR>
     *　@・falseが返却された場合、繰り返し処理を抜け、(1)のroll()の戻り値を返却する。 <BR>
     * <BR>
     *(4)　@(2)の検索結果!=0件の場合、または(3)の結果が true の場合、(1)に戻る。 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strMutualProductCode - 銘柄コード
     * @@return Timestamp
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Timestamp getMutualNextOrderBizDate(
            String l_strInstitutionCode,
            String l_strMutualProductCode
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualNextOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //１）this.get投信発注日()から、発注日を取得する。
        Date l_datBizDate = getMutualOrderBizDate();

        //２）　@以下を繰り返す。
        Timestamp l_datBaseDate = new Timestamp(l_datBizDate.getTime());
        boolean l_blnIsHoliday = true;
        do
        {
            //(1)　@翌営業日（国内市場のみを考慮）の取得
            //　@・営業日計算オブジェクトを生成する。
            //　@　@　@[コンストラクタに渡す引数]
            //　@　@　@　@基準日＝前回のroll()の戻り値
            WEB3GentradeBizDate l_GentradeBizDate =
                new WEB3GentradeBizDate(l_datBaseDate);

            //　@・roll()をコール。
            //　@　@　@[rollに渡す引数]
            //　@　@　@　@加算／減算日数＝１
            Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);

            //(2)　@以下の条件で「カレンダーテーブル」を検索。
            //　@　@　@[検索条件]
            //　@　@　@　@日付＝(1)のroll()の戻り値 and
            //　@　@　@　@営業日区分＝”非営業日”
            String l_whereClause = "holiday = ? and biz_date_type = ?";
            Object l_bindVars[] = { l_datNextBizDate, WEB3BizDateTypeDef.NO_BIZ_DATE};
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                        l_whereClause,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //(3)　@(2)の検索結果＝0件の場合、海外市場カレンダー.is休日()をコールする。
            //　@　@　@　@証券会社コード＝引数.証券会社コード
            //　@　@　@　@銘柄コード＝引数.銘柄コード
            //　@　@　@　@日付＝(1)のroll()の戻り値
            //　@・falseが返却された場合、繰り返し処理を抜け、(1)のroll()の戻り値を返却する。
            if(l_lisRows == null || l_lisRows.size() == 0)
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                l_blnIsHoliday =
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strMutualProductCode,
                        l_datNextBizDate);
            }
            l_datBaseDate = l_datNextBizDate;
        }while(l_blnIsHoliday);

        log.exiting(STR_METHOD_NAME);
        log.debug("発注日 = " + l_datBizDate);
        return l_datBaseDate;
    }

    /**
     * (注文締切時間更新)<BR>
     * 注文締切時間更新処理を行う。 <BR>
     * <BR>
     *シーケンス図「（投信）注文締切時間更新」参照<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_arrBranchCodes[] - 部店コード一覧
     * @@param l_strProductCode - 銘柄コード
     * @@param l_bizDateType - 営業日区分
     * @@param l_strOrderCloseStartTime - 注文締切開始時間
     * @@param l_strOrderCloseendTime - 注文締切終了時間
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static void updateOrderCloseTime(
            String l_strInstitutionCode,
            String[] l_arrBranchCodes,
            String l_strProductCode,
            String l_bizDateType,
            String l_strOrderCloseStartTime,
            String l_strOrderCloseEndTime
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCloseTime()";
        log.entering(STR_METHOD_NAME);

        if(l_arrBranchCodes == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "該当パラメータはNullである");
        }

        // 1)現在日付の取得
        Timestamp l_datSystemDate =
            GtlUtils.getTradingSystem( ).getSystemTimestamp();

        // 2)トリガ発行時間帯の更新
        // 注文締切開始時間の入力がある場合のみ実施
        if( l_strOrderCloseStartTime != null)
        {
            for(int i = 0; i < l_arrBranchCodes.length; i++)
            {
                // 2 - 1)以下の条件で「取引時間テーブル」を検索し、トリガ発行時間帯を取得する。
                // [検索条件]
                // 証券会社コード＝引数.証券会社コード and
                // 部店コード＝引数.部店コード(*) and
                // 市場コード＝”DEFAULT” and
                // 受付時間区分＝”投資信託” and
                // 商品コード＝引数.銘柄コード and
                // 営業日区分＝引数.営業日区分 and
                // 市場トリガ発行＝”SONARへMQトリガを実施する” and
                // 受付可能＝”受付可能” and
                // 発注日計算＝”当日”
                // [並び順]
                // 開始時間　@降順
                String l_whereClause = " institution_code = ? and " +
                    " branch_code = ? and " +
                    " market_code = ? and " +
                    " trading_time_type = ? and " +
                    " product_code = ? and " +
                    " biz_date_type = ? and " +
                    " submit_market_trigger = ? and " +
                    " enable_order = ? and " +
                    " bizdate_calc_parameter = ? ";

                Object l_bindVars[] = {
                    l_strInstitutionCode,
                    l_arrBranchCodes[i],
                    WEB3MarketCodeDef.DEFAULT,
                    WEB3TradingTimeTypeDef.MUTUAL_FUND,
                    l_strProductCode,
                    l_bizDateType,
                    WEB3SubmitMarketTriggerDef.TRIGGER,
                    WEB3EnableOrderDef.ENABLE,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE};

                String l_strSortCond = " start_time ";
                List l_lisRows = null;
                try
                {
                    l_lisRows =
                        Processors.getDefaultProcessor().doFindAllQuery(
                            TradingTimeRow.TYPE,
                            l_whereClause,
                            l_strSortCond,
                            null,
                            l_bindVars);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__", l_ex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                TradingTimeParams l_tradingTimeParams = null;
                if(l_lisRows != null && l_lisRows.size() != 1)
                {
                    log.debug("__データ不整合エラー。__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        "データ不整合エラー。");
                }
                else
                {
                    TradingTimeRow l_tradingTimeRow =
                        (TradingTimeRow) l_lisRows.get(0);

                    l_tradingTimeParams =
                        new TradingTimeParams(l_tradingTimeRow);

                    Date l_datStartTime =
                        WEB3DateUtility.getDate(l_strOrderCloseStartTime +"00", "HHmmss");
                    l_datStartTime = WEB3DateUtility.addSecond(
                        l_datStartTime,-1L);
                    l_tradingTimeParams.setEndTime(
                        WEB3DateUtility.formatDate(l_datStartTime, "HHmmss"));
                    l_tradingTimeParams.setLastUpdatedTimestamp(l_datSystemDate);
                }

                // 2 - 2) doUpdateQuery
                try
                {
                    log.debug("l_tradingTimeParams = " + l_tradingTimeParams);
                    Processors.getDefaultProcessor().doUpdateQuery(l_tradingTimeParams);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("__DataNetworkException__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("__DataQueryException__");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        // 3) 引数.部店コードの要素数分、繰り返す
        for(int i = 0; i<l_arrBranchCodes.length; i++)
        {
            String l_strStartTime = null;
            String l_strEndTime = null;

            // 3 - 1)以下の条件で「取引時間テーブル」を検索し、”取引停止時間帯”を取得する。
            // [検索条件]
            // 証券会社コード＝引数.証券会社コード and
            // 部店コード＝引数.部店コード[n](*) and
            // 市場コード＝”DEFAULT” and
            // 受付時間区分＝”投資信託” and
            // 商品コード＝引数.銘柄コード and
            // 営業日区分＝引数.営業日区分 and
            // 市場トリガ発行＝”SONARへMQトリガを実施しない” and
            // 受付可能＝”受付不可” and
            // 発注日計算＝”翌営業日”
            // [並び順]
            // 開始時間　@降順
            String l_whereClause = " institution_code = ? and " +
                " branch_code = ? and " +
                " market_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " submit_market_trigger = ? and " +
                " enable_order = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVars[] = {
                l_strInstitutionCode,
                l_arrBranchCodes[i],
                WEB3MarketCodeDef.DEFAULT,
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_strProductCode,
                l_bizDateType,
                WEB3SubmitMarketTriggerDef.NOT_TRIGGER,
                WEB3EnableOrderDef.DISABLED,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE};

            String l_strSortCond = " start_time ";
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        TradingTimeRow.TYPE,
                        l_whereClause,
                        l_strSortCond,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            if(l_lisRows != null && l_lisRows.size() != 1)
            {
                log.debug("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    "データ不整合エラー。");
            }
            else
            {
                TradingTimeRow l_tradingTimeRow =
                    (TradingTimeRow) l_lisRows.get(0);

                if(l_strOrderCloseStartTime == null)
                {
                    if(l_tradingTimeRow.getStartTime().compareTo(l_tradingTimeRow.getEndTime()) == 0)
                    {
                        l_strStartTime = WEB3DateUtility.formatDate(
                            WEB3DateUtility.addSecond(
                                WEB3DateUtility.getDate(
                                    l_tradingTimeRow.getStartTime(), "HHmmss"),1L), "HHmmss");
                    }
                    else
                    {
                        l_strStartTime = l_tradingTimeRow.getStartTime();
                    }
                }
                else
                {
                    l_strStartTime = l_strOrderCloseStartTime + "00";
                }
                if(l_strOrderCloseEndTime == null)
                {
                    l_strEndTime = l_tradingTimeRow.getEndTime();
                }
                else
                {
                    l_strEndTime = l_strOrderCloseEndTime + "00";
                }
            }

            // 3 - 2) 以下の条件で、「取引時間テーブル」に削除処理を行う。
            // [削除条件]
            // 証券会社コード＝引数.証券会社コード and
            // 部店コード＝引数.部店コード[n] (*) and
            // 市場コード＝”DEFAULT” and
            // 受付時間区分＝”投資信託” and
            // 商品コード＝引数.銘柄コード and
            // 営業日区分＝引数.営業日区分 and
            // 営業日計算＝”翌営業日”
            String l_whereClauseDelete = " institution_code = ? and " +
                " branch_code = ? and " +
                " market_code = ? and " +
                " trading_time_type = ? and " +
                " product_code = ? and " +
                " biz_date_type = ? and " +
                " bizdate_calc_parameter = ? ";

            Object l_bindVarsDelete[] = {
                l_strInstitutionCode,
                l_arrBranchCodes[i],
                WEB3MarketCodeDef.DEFAULT,
                WEB3TradingTimeTypeDef.MUTUAL_FUND,
                l_strProductCode,
                l_bizDateType,
                WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE};

            try
            {
                log.debug("delete TradingTimeRow ! ");
                Processors.getDefaultProcessor().doDeleteAllQuery(
                    TradingTimeRow.TYPE,
                    l_whereClauseDelete,
                    l_bindVarsDelete);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 3 - 3) 以下の設定値をもって「取引時間テーブル」に新規行を追加する。
            // [設定値]
            // 証券会社コード＝引数.証券会社コード
            // 部店コード＝引数.部店コード[n] (*)
            // 市場コード＝”DEFAULT”
            // 受付時間区分＝”投資信託”
            // 商品コード＝引数.銘柄コード
            // 営業日区分＝引数.営業日区分
            // 開始時間＝引数.注文締切開始時間(*)
            // (*) ただし、引数.注文締切開始時間=引数.注文締切終了時間の場合は、
            // 注文締切開始時間の１秒前の時間を設定する。
            // 終了時間＝引数.注文締切終了時間の１秒前の時間
            // 市場トリガ発行＝”SONARへMQトリガを実施しない”
            // 受付可能＝”受付不可”
            // 発注日計算＝”翌営業日”
            // 作成日付＝＜現在日付＞で取得した日付
            // 更新日付＝＜現在日付＞で取得した日付

            TradingTimeParams l_TradingTimePramas = new TradingTimeParams();
            l_TradingTimePramas.setInstitutionCode(l_strInstitutionCode);
            l_TradingTimePramas.setBranchCode(l_arrBranchCodes[i]);
            l_TradingTimePramas.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_TradingTimePramas.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            l_TradingTimePramas.setProductCode(l_strProductCode);
            l_TradingTimePramas.setBizDateType(l_bizDateType);
            l_TradingTimePramas.setStartTime(l_strOrderCloseStartTime);

            Date l_datStarTime =
                WEB3DateUtility.getDate(l_strStartTime, "HHmmss");
            Date l_datEndTime =
                WEB3DateUtility.getDate(l_strEndTime, "HHmmss");

            if (l_strOrderCloseEndTime == null)
            {
                Date l_datStarTimeMinus1s =
                    WEB3DateUtility.addSecond(l_datStarTime,-1L);
                if (WEB3DateUtility.compareTime(l_datStarTimeMinus1s, l_datEndTime) == 0)
                {
                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(l_datStarTimeMinus1s, "HHmmss"));
                }
                else
                {
                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(l_datStarTime, "HHmmss"));
                }

                l_TradingTimePramas.setEndTime(
                                WEB3DateUtility.formatDate(l_datEndTime, "HHmmss"));
            }
            else
            {
                if (WEB3DateUtility.compareTime(l_datStarTime, l_datEndTime) == 0)
                {

                    l_TradingTimePramas.setStartTime(
                        WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                            l_datStarTime,-1L), "HHmmss"));
                }
                else
                {
                    l_TradingTimePramas.setStartTime(WEB3DateUtility.formatDate(l_datStarTime, "HHmmss"));
                }
                l_TradingTimePramas.setEndTime(
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                        l_datEndTime,-1L), "HHmmss"));
            }
            l_TradingTimePramas.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.NOT_TRIGGER);
            l_TradingTimePramas.setEnableOrder(WEB3EnableOrderDef.DISABLED);
            l_TradingTimePramas.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_TradingTimePramas.setCreatedTimestamp(l_datSystemDate);
            l_TradingTimePramas.setLastUpdatedTimestamp(l_datSystemDate);

            try
            {
                log.debug("l_TradingTimePramas = " + l_TradingTimePramas);
                Processors.getDefaultProcessor().doInsertQuery(l_TradingTimePramas);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 3 - 4)以下の設定値をもって「取引時間テーブル」に新規行を追加する。
            // [設定値]
            // 証券会社コード＝引数.証券会社コード
            // 部店コード＝引数.部店コード[n] (*)
            // 市場コード＝”DEFAULT”
            // 受付時間区分＝”投資信託”
            // 商品コード＝引数.銘柄コード
            // 営業日区分＝引数.営業日区分
            // 開始時間＝引数.注文締切終了時間
            // 終了時間＝”235959”
            // 市場トリガ発行＝”SONARへMQトリガを実施しない”
            // 受付可能＝”受付可能”
            // 発注日計算＝”翌営業日”
            // 作成日付＝＜現在日付＞で取得した日付
            // 更新日付＝＜現在日付＞で取得した日付

            TradingTimeParams l_TradingTimePramas2 = new TradingTimeParams();

            l_TradingTimePramas2.setInstitutionCode(l_strInstitutionCode);
            l_TradingTimePramas2.setBranchCode(l_arrBranchCodes[i]);
            l_TradingTimePramas2.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_TradingTimePramas2.setTradingTimeType(WEB3TradingTimeTypeDef.MUTUAL_FUND);
            l_TradingTimePramas2.setProductCode(l_strProductCode);
            l_TradingTimePramas2.setBizDateType(l_bizDateType);
            if (l_strOrderCloseEndTime == null)
            {
                l_TradingTimePramas2.setStartTime(
                    WEB3DateUtility.formatDate(WEB3DateUtility.addSecond(
                        l_datEndTime,1L), "HHmmss"));
            }
            else
            {
                l_TradingTimePramas2.setStartTime(
                    WEB3DateUtility.formatDate(l_datEndTime, "HHmmss"));
            }
            l_TradingTimePramas2.setEndTime("235959");
            l_TradingTimePramas2.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.NOT_TRIGGER);
            l_TradingTimePramas2.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_TradingTimePramas2.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_TradingTimePramas2.setCreatedTimestamp(l_datSystemDate);
            l_TradingTimePramas2.setLastUpdatedTimestamp(l_datSystemDate);

            try
            {
                log.debug("l_TradingTimePramas2 = " + l_TradingTimePramas2);
                Processors.getDefaultProcessor().doInsertQuery(l_TradingTimePramas2);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (set日付ロール)<BR>
	 * 日付ロールをセットする。<BR>
	 * <BR>
	 *  －取引時間テーブルの内容をLocalThreadにセットする。<BR>
	 *  －日付ロールをLocalThreadにセットする。<BR>
	 * <BR>
	 * ※ 日付ロールのセット<BR>
	 * 取引時間管理の該当行.発注日計算 == 0：当日）の場合、セットしない。(*1)<BR>
	 * 取引時間管理の該当行.発注日計算 == 1：翌営業日）の場合、セットしない。(*1)<BR>
	 * 取引時間管理の該当行.発注日計算 == 2：2営業日後）の場合、1<BR>
	 * <BR>
	 * (*1)日付ロールをセットしない場合、デフォルト値（0）がセットされる。<BR>
	 *
	 * @@throws webbroker3.common.WEB3SystemLayerException
	 * @@roseuid 403D907801E4
	 */
	public static void setDateRole() throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "setDateRole()";
		log.entering(STR_METHOD_NAME);

		// Timestampの取得
		java.sql.Timestamp l_processTime =
			(Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

		//ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する
		WEB3GentradeTradingClendarContext l_clendarContext =
			(WEB3GentradeTradingClendarContext)
				ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

		//証券会社コード
		String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
		//市場コード
		String l_strMarketCode = l_clendarContext.getMarketCode();
		//部店コード
		String l_strBranchCode = l_clendarContext.getBranchCode();
		//受付時間区分
		String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
		//銘柄コード
		String l_strProductCode = l_clendarContext.getProductCode();
		//営業日区分
		String l_strBizDateType = getBizDateType(l_processTime);

		//取得したコンテキストの以下の項目にnullが格納されていた場合は、データ不整合として
		//例外をスローする。
		//   取引時間コンテキスト.証券会社コード
		// 　@取引時間コンテキスト.部店コード
		// 　@取引時間コンテキスト.受付時間区分
		if ((l_strInstitutionCode == null)
			|| (l_strBranchCode == null)
			|| (l_strTradingTimeType == null))
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80006,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ STR_METHOD_NAME);
		}

		//取引時間テーブルを検索する
		StringBuffer l_sbWhere = new StringBuffer();
		l_sbWhere.append(" institution_code = ? ");
		l_sbWhere.append(" and branch_code = ? ");
		l_sbWhere.append(" and trading_time_type = ? ");
		l_sbWhere.append(" and biz_date_type = ? ");

		ArrayList l_lstObjTradingTimeWhere = new ArrayList();
		//証券会社コード
		l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
		//部店コード
		l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
		//受付時間区分
		l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
		//営業日区分
		l_lstObjTradingTimeWhere.add(l_strBizDateType);

		//受付時間
		SimpleDateFormat l_format =
			GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
		String l_strTime = l_format.format(l_processTime);

		//市場コード
		if (l_strMarketCode != null)
		{
			l_sbWhere.append(" and market_code = ? ");
			l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
		}
		//銘柄コード
		if (l_strProductCode != null)
		{
			l_sbWhere.append(" and  product_code  = ? ");
			l_lstObjTradingTimeWhere.add(l_strProductCode.trim());
		}

		int l_intSize = l_lstObjTradingTimeWhere.size();
		Object[] l_objTradingTimeWhere = new Object[l_intSize];
		for (int i = 0; i < l_intSize; i++)
		{
			l_objTradingTimeWhere[i] = l_lstObjTradingTimeWhere.get(i);
		}

		List l_lisRecords = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			l_lisRecords = l_queryProcessor.doFindAllQuery(
				TradingTimeRow.TYPE,
				l_sbWhere.toString(),
				l_objTradingTimeWhere);
		}
		catch (DataFindException dfe)
		{
			log.error(STR_METHOD_NAME, dfe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dfe.getMessage(),
				dfe);
		}
		catch (DataQueryException dqe)
		{
			log.error(STR_METHOD_NAME, dqe);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dqe.getMessage(),
				dqe);
		}
		catch (DataNetworkException dne)
		{
			log.error(STR_METHOD_NAME, dne);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				dne.getMessage(),
				dne);
		}

		//件数チェック
		int l_intLength = l_lisRecords.size();
		if (l_intLength == 0)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "." + STR_METHOD_NAME,
				"取引時間テーブル検索： 件数 = 0");
		}
		TradingTimeRow l_tradingTimeRow = null;
		for (int i = 0; i < l_intLength; i++)
		{
			TradingTimeRow l_tmpTradingTimeRow =
				(TradingTimeRow) l_lisRecords.get(i);
			if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime)
				&& Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime))
			{
				l_tradingTimeRow = l_tmpTradingTimeRow;
				break;
			}
		}

		if (l_tradingTimeRow == null)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				WEB3GentradeTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				"取引時間テーブル検索： 件数 = 0");
		}

		//set営業日区分
		l_clendarContext.setBizDateType(l_tradingTimeRow.getBizDateType());
		//set市場トリガ発行
		l_clendarContext.setSubmitMarketTrigger(
			l_tradingTimeRow.getSubmitMarketTrigger());
		//set発注日計算
		l_clendarContext.setBizdateCalcParameter(
			l_tradingTimeRow.getBizdateCalcParameter());

		//set受付可能(１件でも「受付可能」であれば注文受付可能とする)
		String l_strEnableOrder = null;
		TradingTimeRow l_tempRow = null;

		for (int i = 0; i < l_intLength; i++)
		{
			l_tempRow = (TradingTimeRow) l_lisRecords.get(i);
			if (WEB3EnableOrderDef.ENABLE.equals(l_tempRow.getEnableOrder()))
			{
				l_strEnableOrder = l_tempRow.getEnableOrder();
			}
		}
		if (l_strEnableOrder == null)
		{
			l_strEnableOrder = WEB3EnableOrderDef.DISABLED;
		}
		l_clendarContext.setEnableOrder(l_strEnableOrder);

		// 取引時間コンテキストをセットする
		ThreadLocalSystemAttributesRegistry.setAttribute(
			TRADING_CAL_CONTEXT_PATH,
			l_clendarContext);

		// 営業日ロールをセット
		Integer l_offset = null;
		if (l_clendarContext.getBizdateCalcParameter().equals(
			WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE))
		{
			l_offset = new Integer(1);
		}
		else  if (l_clendarContext.getBizdateCalcParameter().equals(
			WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE))
		{
			l_offset = new Integer(0);
		}
		else
		{
			l_offset = new Integer(0);
		}
		ThreadLocalSystemAttributesRegistry.setAttribute(OFFSET_TAG, l_offset);

		log.debug(
			"取引カレンダキー：["
				+ "証券会社="
				+ l_strInstitutionCode
				+ ", 部店="
				+ l_strBranchCode
				+ ", 市場="
				+ l_strMarketCode
				+ ", 取引時間タイプ="
				+ l_strTradingTimeType
				+ ", 営業日タイプ="
				+ l_strBizDateType
				+ ", 開始時間="
				+ l_tradingTimeRow.getStartTime()
				+ "]");
		log.debug("営業日ロール:[" + l_clendarContext.getBizdateCalcParameter() + "]");

		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (get乗換発注日)<BR>
     * 乗換注文の発注日を返す。<BR>
     * <BR>
     * １）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。<BR>
     * <BR>
     * １－１）投信取引時間管理.reset銘柄コード()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 銘柄コード： 引数.乗換先銘柄コード<BR>
     * <BR>
     * １－２）投信取引時間管理.reset注文受付トランザクション()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    注文受付トランザクション： ”買付”<BR>
     * <BR>
     * １－３）投信取引時間管理.set日付ロール()をコールする。<BR>
     * <BR>
     * ２）乗換先銘柄の発注日を取得する。<BR>
     * <BR>
     *    this.get発注日()をコールする。<BR>
     * <BR>
     *    ⇒(A)とする。<BR>
     * <BR>
     * ３）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。<BR>
     * <BR>
     * ３－１）投信取引時間管理.reset銘柄コード()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    銘柄コード： 引数.乗換元銘柄コード<BR>
     * <BR>
     * ３－２）投信取引時間管理.reset注文受付トランザクション()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    注文受付トランザクション： ”売付”<BR>
     * <BR>
     * ３－３）投信取引時間管理.set日付ロール()をコールする。<BR>
     * <BR>
     * ４）乗換元銘柄の発注日を取得する。<BR>
     * <BR>
     *    this.get発注日()をコールする。<BR>
     * <BR>
     *    ⇒(B)とする。<BR>
     * <BR>
     * ５）発注日を決定し返却する。<BR>
     * <BR>
     * ５－１）乗換元銘柄の発注日（=(A)） == 乗換先銘柄の発注日（=(B)） の場合、
     * 乗換元銘柄の発注日を返却する。<BR>
     * <BR>
     * ５－２）(A) != (B) の場合、(A) == (B) となるまで以下の処理を行い、(A)を返却する。<BR>
     * <BR>
     * ５－２－１）(A) < (B) の場合、(A)の翌営業日を算出し、それを(A)とする。<BR>
     * <BR>
     *     this.get投信翌営業日()をコールする。<BR>
     * <BR>
     *     [引数]<BR>
     *     証券会社コード： 取引カレンダコンテキスト.証券会社コード<BR>
     *     銘柄コード： 引数.乗換元銘柄コード<BR>
     *     基準日： (A)<BR>
     * <BR>
     * ５－２－２）(A) > (B) の場合、(B)の翌営業日を算出し、それを(B)とする。<BR>
     * <BR>
     *     this.get投信翌営業日()をコールする。<BR>
     * <BR>
     *     [引数]<BR>
     *     証券会社コード： 取引カレンダコンテキスト.証券会社コード<BR>
     *     銘柄コード： 引数.乗換先銘柄コード<BR>
     *     基準日： (B)<BR>
     * <BR>
     * @@param l_strProductCode - 乗換元銘柄コード
     * @@param l_strSwtProductCode - 乗換先銘柄コード
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getSwtOrderBizDate(
    		String l_strProductCode,
    		String l_strSwtProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwtOrderBizDate(String,String)";
        log.entering(STR_METHOD_NAME);

        // １）取引カレンダコンテキストを乗換先銘柄の情報でリセットする。
        // １－１）投信取引時間管理.reset銘柄コード()をコールする。
        // [引数]
        // 銘柄コード： 引数.乗換先銘柄コード
		resetProductCode(l_strSwtProductCode);

        // １－２）this.reset注文受付トランザクション()をコールする。
        // [引数]
        // 注文受付トランザクション： ”買付”
        resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        
        // １－３）this.set日付ロール()をコールする。
        setDateRole();
        
        // ２）乗換先銘柄の発注日を取得する。
        // this.get投信発注日()をコールする。
        Date l_datOrderBizDate = getMutualOrderBizDate();

        // ３）取引カレンダコンテキストを乗換元銘柄の情報でリセットする。
        // ３－１）this.reset銘柄コード()をコールする。
        // [引数]
        // 銘柄コード： 引数.乗換元銘柄コード
		resetProductCode(l_strProductCode);

        // ３－２）this.reset注文受付トランザクション()をコールする。
        // [引数]<BR>
        // 注文受付トランザクション： ”売付”
        resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        
        // ３－３）this.set日付ロール()をコールする。
        setDateRole();
        
        // ４）乗換元銘柄の発注日を取得する。
        // this.get投信発注日()をコールする。
        Date l_datSwtOrderBizDate = getMutualOrderBizDate();
        
        // ５）発注日を決定し返却する。
        if (l_datOrderBizDate.equals(l_datSwtOrderBizDate))
        {
            // ５－１）乗換先銘柄の発注日（=(A)） == 乗換元銘柄の発注日（=(B)） の場合、
            // 乗換元銘柄の発注日を返却する。
        	return l_datSwtOrderBizDate;
        }
        else 
        {
            // ５－２）(A) != (B) の場合、(A) == (B) となるまで以下の処理を行い、(B)を返却する。<BR>
        	while (!l_datOrderBizDate.equals(l_datSwtOrderBizDate))
        	{
                //取引カレンダコンテキスト取得
                WEB3GentradeTradingClendarContext l_context =
                    (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

                String l_strInstitutionCode = l_context.getInstitutionCode();

                if (l_datOrderBizDate.compareTo(l_datSwtOrderBizDate) < 0)
        		{
        	        //５－２－１）(A) < (B) の場合、(A)の翌営業日を算出し、それを(A)とする。<BR>
        	        //     this.get投信翌営業日()をコールする。<BR>
        	        //     [引数]<BR>
        	        //     証券会社コード： 取引カレンダコンテキスト.証券会社コード<BR>
        	        //     銘柄コード： 引数.乗換先銘柄コード<BR>
        	        //     基準日： (A)<BR>
                	l_datOrderBizDate =
                		getMutualNextOrderBizDate(
                			l_strInstitutionCode,
                			l_strSwtProductCode,
                			l_datOrderBizDate);
        		}
        		else 
        		{
        	        // ５－２－２）(A) > (B) の場合、(B)の翌営業日を算出し、それを(B)とする。<BR>
        	        //     this.get投信翌営業日()をコールする。<BR>
        	        //     [引数]<BR>
        	        //     証券会社コード： 取引カレンダコンテキスト.証券会社コード<BR>
        	        //     銘柄コード： 引数.乗換元銘柄コード<BR>
        	        //     基準日： (B)<BR>
                	l_datSwtOrderBizDate =
                		getMutualNextOrderBizDate(
                			l_strInstitutionCode,
                			l_strProductCode,
                			l_datSwtOrderBizDate);
        		}
        	}
        }

        log.exiting(STR_METHOD_NAME);
        log.debug("乗換発注日 = " + l_datSwtOrderBizDate);
        
        return l_datSwtOrderBizDate;
    }

    /**
     * (get乗換発注日)<BR>
     * 乗換注文の発注日を取得する。<BR>
     *     this.get乗換発注日()にて発注日を取得する。 <BR>
     *     取得した発注日と引数の確認時発注日が違う日付であれば <BR>
     *     例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)乗換注文の確認・完了時処理で使用する。<BR>
     * <BR>
     * @@param l_strProductCode - 乗換元銘柄コード
     * @@param l_strSwtProductCode - 乗換先銘柄コード
     * @@param l_datCheckDate - 確認時発注日<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getSwtOrderBizDate(
    		String l_strProductCode,
    		String l_strSwtProductCode,
    		Date l_datCheckDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwtOrderBizDate(String,String,Date)";
        log.entering(STR_METHOD_NAME);

        // this.get乗換発注日()にて発注日を取得する
        Date l_datCurrentBizDate = getSwtOrderBizDate(l_strProductCode,l_strSwtProductCode);

        // 取得した発注日と引数の確認時発注日が違う日付であれば
        if(WEB3DateUtility.compareToDay(l_datCurrentBizDate, l_datCheckDate) != 0 )
        {
            log.debug("取得した発注日と引数の確認時発注日が違う日付である");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "取得した発注日と引数の確認時発注日が違う日付である");
        }

        //　@取得した発注日を返す。
        log.exiting(STR_METHOD_NAME);
        log.debug("発注日 = " + l_datCurrentBizDate);
        
        return l_datCurrentBizDate;
    }

    /**
     * (get投信翌営業日)<BR>
     * 海外運用投信銘柄を考慮した基準日からの翌営業日を取得する。<BR>
     * <BR>
     * 引数.基準日を(A)とし、以下を繰り返す。<BR>
     * <BR>
     * １）　@翌営業日（国内市場のみを考慮）の取得<BR>
     *　@・営業日計算オブジェクトを生成する。<BR>
     *　@　@　@[コンストラクタに渡す引数]<BR>
     *　@　@　@　@基準日＝(A)<BR>
     *　@・roll()をコール。<BR>
     *　@　@　@[rollに渡す引数]<BR>
     *　@　@　@　@加算／減算日数＝１<BR>
     * <BR>
     * ２）　@以下の条件で「カレンダーテーブル」を検索。<BR>
     *　@　@　@[検索条件]<BR>
     *　@　@　@　@日付＝１）の戻り値 and<BR>
     *　@　@　@　@営業日区分＝”非営業日”<BR>
     * <BR>
     * ３）　@２）の検索結果＝0件の場合、海外市場カレンダー.is休日()をコールする。<BR>
     *　@　@　@　@[引数]<BR>
     *　@　@　@　@証券会社コード： 引数.証券会社コード<BR>
     *　@　@　@　@銘柄コード： 引数.銘柄コード<BR>
     *　@　@　@　@日付： １）の戻り値<BR>
     * <BR>
     *　@・falseが返却された場合、繰り返し処理を抜け、１）の戻り値を返却する。<BR>
     * <BR>
     * ４）　@２）の検索結果!=0件 または ３）の結果==true の場合、１）の戻り値を(A)とし、１）に戻る。<BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strMutualProductCode - 銘柄コード
     * @@param l_datOrgBaseDate - 基準日
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 40AD8533002E
     */
    public static Date getMutualNextOrderBizDate(
            String l_strInstitutionCode,
            String l_strMutualProductCode,
            Date l_datOrgBaseDate
            ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualNextOrderBizDate(String,String,Date)";
        log.entering(STR_METHOD_NAME);

        //２）　@引数.基準日を(A)とし、以下を繰り返す。
        Timestamp l_datBaseDate = new Timestamp(l_datOrgBaseDate.getTime());
        boolean l_blnIsHoliday = true;
        do
        {
            //１）　@翌営業日（国内市場のみを考慮）の取得
            //　@・営業日計算オブジェクトを生成する。
            //　@　@　@[コンストラクタに渡す引数]
            //　@　@　@　@基準日＝(A)
            WEB3GentradeBizDate l_GentradeBizDate =
                new WEB3GentradeBizDate(l_datBaseDate);

            //　@・roll()をコール。
            //　@　@　@[rollに渡す引数]
            //　@　@　@　@加算／減算日数＝１
            Timestamp l_datNextBizDate = l_GentradeBizDate.roll(1);

            //２）　@以下の条件で「カレンダーテーブル」を検索。
            //　@　@　@[検索条件]
            //　@　@　@　@日付＝１）の戻り値 and
            //　@　@　@　@営業日区分＝”非営業日”
            String l_whereClause = "holiday = ? and biz_date_type = ?";
            Object l_bindVars[] = { l_datNextBizDate, WEB3BizDateTypeDef.NO_BIZ_DATE};
            List l_lisRows = null;
            try
            {
                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        CalendarRow.TYPE,
                        l_whereClause,
                        null,
                        l_bindVars);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("__DataNetworkException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //３）　@２）の検索結果＝0件の場合、海外市場カレンダー.is休日()をコールする。
            //　@　@　@　@[引数]
            //　@　@　@　@証券会社コード： 引数.証券会社コード
            //　@　@　@　@銘柄コード： 引数.銘柄コード
            //　@　@　@　@日付： １）の戻り値
            //　@・falseが返却された場合、繰り返し処理を抜け、１）の戻り値を返却する。
            if(l_lisRows == null || l_lisRows.size() == 0)
            {
                WEB3AdminMutualFrgncal l_adminMutualFrgncal =
                    new WEB3AdminMutualFrgncal();
                l_blnIsHoliday =
                    l_adminMutualFrgncal.isHoliday(
                        l_strInstitutionCode,
                        l_strMutualProductCode,
                        l_datNextBizDate);
            }
            
            l_datBaseDate = l_datNextBizDate;
        }while(l_blnIsHoliday);

        log.exiting(STR_METHOD_NAME);
        log.debug("翌営業日 = " + l_datBaseDate);
        
        return l_datBaseDate;
    }
    
    /**
     * (get投信発注日)<BR>
     * 一括送信を考慮した発注日を取得する。<BR> 
     * <BR>
     * １）　@投信発注日を取得する。<BR> 
     * 　@　@　@this．get投信発注日(引数無し)をコールする。 <BR>
     * <BR>
     * ２) 引数：一括区分 = falseの場合 <BR>
     * 　@　@　@戻り値として、投信発注日を返す <BR>
     * <BR>    
     *     ３）引数：一括区分 = trueの場合<BR> 
     *  <BR>   
     * 　@　@　@　@３)－１　@部店を取得する。<BR> 
     * 　@　@　@　@　@　@　@　@　@拡張アカウントマネージャ．get部店()<BR> 
     * 　@　@　@　@　@　@　@　@　@[get部店の引数] <BR>
     * 　@　@　@　@　@　@　@　@　@証券会社コード：取引カレンダーコンテキスト．証券会社コード<BR> 
     * 　@　@　@　@　@　@　@　@　@部店コード　@　@　@：取引カレンダーコンテキスト．部店コード<BR>
     * <BR>        
     *　@　@　@　@ ３）－２　@拡張投信銘柄を取得する。<BR>   
     * 　@　@　@　@　@　@　@　@　@拡張投信銘柄マネージャ．get投信銘柄() <BR>
     * 　@　@　@　@　@　@　@　@　@[get投信銘柄()の引数] <BR>
     * 　@　@　@　@　@　@　@　@　@　@証券会社：取得した部店．getInstitution()<BR> 
     * 　@　@　@　@　@　@　@　@　@　@銘柄コード： 取引カレンダコンテキスト．銘柄コード<BR> 
     * <BR>                                      
     * 　@　@　@　@３）－３ wk終了日に以下の値をセットする。<BR> 
     * 　@　@　@　@　@　@３）－３－１　@引数：注文種別　@= 201：投資信託買注文の場合<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@wk終了日 =　@買付終了日（*1）<BR> 
     * <BR>                                          
     * 　@　@　@　@　@　@３）－３－２　@引数：注文種別　@= 202：投資信託売注文の場合<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@wk終了日 =　@解約乗換終了日（*2）<BR> 
     *   <BR>                                            
     * 　@　@　@　@　@　@３）－３－３　@引数：注文種別　@= 203：投資信託募集注文の場合 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@wk終了日 =　@募集終了日（*3）<BR> 
     * <BR>                                                  
     * 　@　@　@　@　@　@３）－３－４　@引数：注文種別が上記以外の場合 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@戻り値として、投信発注日を返す。<BR> 
     * <BR>                                                  
     * 　@　@　@　@３）－４　@wk終了日　@< 投信発注日の場合<BR> 
     * 　@　@　@　@　@　@　@　@　@戻り値として、投信発注日を返す。<BR> 
     *  <BR>                                                 
     * 　@　@　@　@３）－５　@３）－４以外の場合 <BR>
     * 　@　@　@　@　@　@　@　@　@戻り値として、wk終了日を返す。<BR> 
     * <BR>                                                  
     * （*1）買付終了日　@　@　@ ：　@((MutualFundProductRow) 取得した拡張投信銘柄.getDataSourceObject()).<BR>
     * get買付終了日()<BR>
     * （*2）解約乗換終了日 ：　@((MutualFundProductRow) 取得した拡張投信銘柄.getDataSourceObject()).<BR>
     * get解約乗換終了日() <BR>
     * （*3）募集終了日　@　@　@ ：　@取得した拡張投信銘柄．get募集終了日 <BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別 <BR>
     * <BR>
     * 201：投資信託買注文 <BR>
     * 202：投資信託売注文 <BR>
     * 203：投資信託募集注文<BR>
     * @@param l_blnNorealDiv - (一括区分)<BR>
     * 一括区分<BR> 
     * <BR>
     * １) 募集注文の場合 <BR>
     * 　@１)－１ 部店用プリファ@レンス．投信募集注文一括送信区分 = 「一括送信する」の場合はtrue<BR> 
     * 　@１)－２ それ以外の場合はfalse<BR> 
     * <BR>
     * ２) 買付/売却注文の場合<BR> 
     * 　@２－１) 投信銘柄マスター．特定日取引銘柄区分 = 特定日取引銘柄の場合はtrue<BR> 
     * 　@２－２) それ以外の場合はfalse <BR>
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getMutualOrderBizDate(
        OrderTypeEnum l_orderType, boolean l_blnNorealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@投信発注日を取得する。 
        // 　@this．get投信発注日(引数無し)をコールする。 
        Date l_datOrderBizDate = getMutualOrderBizDate();
        
        //２) 引数：一括区分 = falseの場合 
        //戻り値として、投信発注日を返す 
        if (!l_blnNorealDiv)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datOrderBizDate;
        }

        //３）引数：一括区分 = trueの場合 
        else
        {
            //３)－１　@部店を取得する。 
            // 　@拡張アカウントマネージャ．get部店() 
            // 　@[get部店の引数] 
            // 　@証券会社コード：取引カレンダーコンテキスト．証券会社コード 
            // 　@部店コード　@　@　@：取引カレンダーコンテキスト．部店コード 
            
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TRADING_CAL_CONTEXT_PATH);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = null;
            Branch l_branch = null;
            Date l_datWkEndDate = null;
            try
            {
                l_institution = l_accMgr.getInstitution(l_clendarContext.getInstitutionCode());
                l_branch = l_accMgr.getBranch(l_institution, l_clendarContext.getBranchCode());
                
                //３）－２　@拡張投信銘柄を取得する。   
                //   拡張投信銘柄マネージャ．get投信銘柄() 
                //    [get投信銘柄()の引数] 
                //    証券会社：取得した部店．getInstitution() 
                //    銘柄コード： 取引カレンダコンテキスト．銘柄コード 
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
                WEB3MutualFundProductManager l_mfProductManager = 
                    (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
                MutualFundProduct l_mfProduct = l_mfProductManager.getMutualFundProduct(
                    l_branch.getInstitution(), l_clendarContext.getProductCode());
                
                //３）－３ wk終了日に以下の値をセットする。 
                //   ３）－３－１　@引数：注文種別　@= 201：投資信託買注文の場合 
                //     　@　@　@　@　@　@　@wk終了日 =　@買付終了日（*1） 
                MutualFundProductRow l_mfProductRow = 
                    (MutualFundProductRow) l_mfProduct.getDataSourceObject();
                if (OrderTypeEnum.MF_BUY.equals(l_orderType))
                {
                    l_datWkEndDate = l_mfProductRow.getBuyEndDate();
                }
                else if (OrderTypeEnum.MF_SELL.equals(l_orderType))
                {
                    //   ３）－３－２　@引数：注文種別　@= 202：投資信託売注文の場合 
                    //     　@　@　@　@　@　@　@wk終了日 =　@解約乗換終了日（*2） 
                    l_datWkEndDate = l_mfProductRow.getSellSwtEndDate();
                }
                else if (OrderTypeEnum.MF_RECRUIT.equals(l_orderType))
                {
                    //   ３）－３－３　@引数：注文種別　@= 203：投資信託募集注文の場合 
                    //     　@　@　@　@　@　@　@wk終了日 =　@募集終了日（*3） 
                    l_datWkEndDate = l_mfProductRow.getRecruitEndDate();
                }
                else
                {
                    //   ３）－３－４　@引数：注文種別が上記以外の場合 
                    //     　@　@　@　@　@　@　@戻り値として、投信発注日を返す。 
                    l_datWkEndDate = l_datOrderBizDate;
                }

                //   ３）－４　@wk終了日　@< 投信発注日の場合 
                //     　@　@　@　@　@　@　@戻り値として、投信発注日を返す。 
                if (WEB3DateUtility.compare(l_datWkEndDate, l_datOrderBizDate) < 0)
                {
                    l_datWkEndDate = l_datOrderBizDate;
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("Error In 部店、証券会社または銘柄コードを取得する ");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //   ３）－５　@３）－４以外の場合 
            //     　@　@　@　@　@　@　@戻り値として、wk終了日を返す。
            log.exiting(STR_METHOD_NAME);
            return l_datWkEndDate;
        }
    }
    
    /**
     * (get発注日)<BR>
     * 一括送信を考慮した発注日を取得する。 <BR>
     * <BR>
     * １） 投信発注日を取得する。<BR> 
     * 　@　@this．get投信発注日() <BR>
     * 　@　@[get投信発注日の引数] <BR>
     * 　@　@注文種別 ： 引数．注文種別 <BR>
     * 　@　@一括区分 ： 引数．一括区分<BR> 
     * <BR>
     * ２） 取得した投信発注日（YYYYMMDD）  !=  引数．確認時発注日（YYYYMMDD）の場合<BR> 
     * 　@　@例外（発注日のチェックエラー：BUSINESS_ERROR_00205）をスローする。<BR> 
     * <BR>
     * ３） ２）で例外が発生しなかった場合は、引数．確認時発注日を返す。<BR>
     * @@param l_datCheckDate - (確認時発注日)<BR>
     * 確認時発注日
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別<BR> 
     * <BR>
     * 201：投資信託買注文<BR> 
     * 202：投資信託売注文<BR> 
     * 203：投資信託募集注文
     * @@param l_blnNorealDiv - (一括区分)<BR>
     * 一括区分<BR> 
     * <BR>
     * １) 募集注文の場合<BR> 
     * 　@１)－１ 部店用プリファ@レンス．投信募集注文一括送信区分 = 「一括送信する」の場合はtrue<BR> 
     * 　@１)－２ それ以外の場合はfalse<BR> 
     * <BR>
     * ２) 買付/売却注文の場合<BR> 
     * 　@２－１) 投信銘柄マスター．特定日取引銘柄区分 = 特定日取引銘柄の場合はtrue<BR> 
     * 　@２－２) それ以外の場合はfalse 
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getOrderBizDate(
        Date l_datCheckDate, 
        OrderTypeEnum l_orderType, 
        boolean l_blnNorealDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１） 投信発注日を取得する。 
        //  this．get投信発注日() 
        //  [get投信発注日の引数] 
        //  注文種別 ： 引数．注文種別 
        //  一括区分 ： 引数．一括区分
        Date l_datMutualOrderBizDate = getMutualOrderBizDate(l_orderType, l_blnNorealDiv);
        
        //２） 取得した投信発注日（YYYYMMDD）  !=  引数．確認時発注日（YYYYMMDD）の場合 
        //  例外（発注日のチェックエラー：BUSINESS_ERROR_00205）をスローする。 
        if (WEB3DateUtility.compareToDay(l_datMutualOrderBizDate, l_datCheckDate) != 0)
        {
            log.debug("取得した発注日と引数の確認時発注日が違う日付である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "取得した発注日と引数の確認時発注日が違う日付である");
        }
        //３） ２）で例外が発生しなかった場合は、引数．確認時発注日を返す。
        
        log.exiting(STR_METHOD_NAME);
        return l_datCheckDate;      
    }

    
    /**
     * (get特定日取引銘柄解約発注日)<BR>
     * （確認/完了処理用）<BR>
     * 特定日取引銘柄の解約乗換終了日を取得し、発注日チェックを行う。 <BR>
     * <BR>
     * １） 投信銘柄マスタ．解約乗換終了日を取得する。<BR> 
     * <BR>
     * ２） 取得した解約乗換終了日（YYYYMMDD）  !=  引数．確認時発注日（YYYYMMDD）の場合<BR> 
     * 　@　@例外（発注日のチェックエラー：BUSINESS_ERROR_00205）をスローする。<BR> 
     * <BR>
     * ３） ２）で例外が発生しなかった場合は、引数．確認時発注日を返す。<BR>
     * <BR>
     * @@param l_datCheckDate - (確認時発注日)<BR>
     * @@return Date
     * @@throws WEB3BaseException 
     */
    public static Date getUnitTypeProductSellOrderBizDate(
        Date l_datCheckDate ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMutualOrderBizDate(OrderTypeEnum, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）  投信銘柄マスタ．解約乗換終了日を取得する。 
        Date l_datSellSwtEndDate = null;
        
        //拡張投信銘柄クラス
        MutualFundProduct l_mfProduct = null;

        //　@取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_context =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                 WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        if (l_context == null)
           {
               log.debug("致命的なシステムエラー。");
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   "WEB3MutualFundTradingTimeManagement"+ "." + STR_METHOD_NAME,
                   "取引カレンダコンテキストを取得できない");
           }

        //証券会社
        Institution l_institution = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        //拡張アカウントマネージャ取得する。
        WEB3GentradeAccountManager l_accMgr =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        try
        {
            //証券会社取得
            l_institution =
                (Institution) l_accMgr.getInstitution(
                    l_context.getInstitutionCode());

            //拡張投信銘柄オブジェクトを取得する。
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager)l_tradingModule.getProductManager();
            l_mfProduct = l_mutualFundProductManager.getMutualFundProduct(
            		 l_institution,
            		 l_context.getProductCode());
            MutualFundProductRow l_mfProductRow = 
                (MutualFundProductRow) l_mfProduct.getDataSourceObject();

            //投信銘柄マスタ．解約乗換終了日を取得する。
            l_datSellSwtEndDate = l_mfProductRow.getSellSwtEndDate();

            
        }
        catch (NotFoundException l_ex)
        {
            log.error("Not Found  " +
                "(証券会社)l_institution =  " +
                    l_institution +
                " and (銘柄コード)l_strProductCode = " +
                    l_context.getProductCode());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        
        //２） 取得した解約乗換終了日（YYYYMMDD）  !=  引数．確認時発注日（YYYYMMDD）の場合 
        //  例外（発注日のチェックエラー：BUSINESS_ERROR_00205）をスローする。 
        if (WEB3DateUtility.compareToDay(l_datSellSwtEndDate, l_datCheckDate) != 0)
        {
            log.debug("取得した発注日と引数の確認時発注日が違う日付である");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                "WEB3MutualFundTradingTimeManagement" + "." + STR_METHOD_NAME,
                "取得した発注日と引数の確認時発注日が違う日付である");
        }
        //３） ２）で例外が発生しなかった場合は、引数．確認時発注日を返す。
        
        log.exiting(STR_METHOD_NAME);
        return l_datCheckDate;      
    }
 
   
}
@
