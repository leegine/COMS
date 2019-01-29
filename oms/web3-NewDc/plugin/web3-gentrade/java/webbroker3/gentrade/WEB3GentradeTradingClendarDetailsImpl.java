head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingClendarDetailsImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引カレンダ詳細(WEB3GentradeTradingClendarDetailsImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/05 和田　@友一(SRA) 新規作成
Revesion History : 2004/02/16 今井　@高史(SRA) 実装
Revesion History : 2004/05/14 鄒政 (中訊) 修正
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引カレンダ詳細)<BR>
 * （TradingCalendarDetailインタフェイスの実装クラス）<BR>
 */
public class WEB3GentradeTradingClendarDetailsImpl
    implements TradingCalendarDetails
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingClendarDetailsImpl.class);

    /**
     * @@roseuid 40A430F7033D
     */
    public WEB3GentradeTradingClendarDetailsImpl()
    {

    }

    /**
     * (get休日)<BR>
     * （getHolidaysの実装）<BR>
     * <BR>
     * カレンダーテーブルを以下の条件で検索し、一致する行の日付をすべて返却する。<BR>
     * 　@[検索キー]<BR>
     * 　@営業日区分：　@”非営業日”<BR>
     * @@return Date[]
     * @@roseuid 4014AE5802D8
     */
    public Date[] getHolidays()
    {

        final String STR_METHOD_NAME = "getHolidays()";
        log.entering(STR_METHOD_NAME);

        //カレンダーテーブルを以下の条件で検索し、一致する行の日付をすべて返却する。
        //[検索キー]
        //営業日区分：”非営業日”
        List l_lisRecords = null;
        try
        {
            String l_strWhere = " biz_date_type = ? ";
            String l_strOrderBy = " holiday ";

            Object[] l_objCalendarWhere = { WEB3BizDateTypeDef.NO_BIZ_DATE };

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CalendarRow.TYPE,
                l_strWhere,
                l_strOrderBy,
                null,
                l_objCalendarWhere);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        int l_intLoop = 0;
        int l_intSize = l_lisRecords.size();
        CalendarRow l_calendarRow;
        Date[] l_datReturn = new Date[l_intSize];
        Date l_tmpDate;

        while (l_intLoop < l_intSize)
        {
            l_calendarRow = (CalendarRow)l_lisRecords.get(l_intLoop);
            l_tmpDate = new Date(l_calendarRow.getHoliday().getTime());
            l_datReturn[l_intLoop] = WEB3DateUtility.toDay(l_tmpDate);
            l_intLoop++;
        }

        log.exiting(STR_METHOD_NAME);
        return l_datReturn;
    }

    /**
     * (get市場開局時間)<BR>
     * <BR>
     * 市場開局時間を取得する。<BR>
     * （getTradeOpenTimeの実装）<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を<BR>
     * 取得する。ThreadLocalSystemAttributesRegistry.getAttribute("<BR>
     * xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     * ２）　@受付日の営業日区分判定。<BR>
     * 　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は<BR>
     * ”非営業日”と判定し、"000000"を返却し処理を終了する。<BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、行<BR>
     * の営業日区分を取得する。行が取得できなかった場合は、営業日区分<BR>
     * を”通常営業日”とする。<BR>
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを<BR>　@
     * 取得する。ThreadLocalSystemAttributesRegistry.getAttribute(" <BR>
     * web3.tradingcalendarcontext")<BR>
     * <BR>
     * ４）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@銘柄コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@営業日区分：　@判定した営業日区分<BR>
     * 　@発注日計算：　@”当日”<BR>
     * <BR>　@
     * 　@%上記に一致する行のうち、開始時間が一番早い行の開始時間を返却する<BR>
     * 　@%該当行が存在しない場合、　@”000000”を返却する。<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4014AE5802D9
     */
    public String getTradeOpenTime()
    {
        final String STR_METHOD_NAME = "getTradeOpenTime()";
        log.entering(STR_METHOD_NAME);

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。

        // 受付日時取得
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // ２） 受付日の営業日区分判定。
        String l_strBizDateType = null;
        try
        {
            l_strBizDateType =
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    l_tsOrderAcceptDate);

            // 土日の場合、非営業日として"000000"を返す
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                return WEB3GentradeTimeDef.MIN_RETURN;
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(STR_METHOD_NAME,wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                wse.getErrorMethod(),
                wse.getMessage(),
                wse);
        }

        // ３） ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキスト
        // を取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode =
            l_clendarContext.getInstitutionCode().trim();
        String l_strMarketCode = l_clendarContext.getMarketCode().trim();
        String l_strBranchCode = l_clendarContext.getBranchCode().trim();
        String l_strTradingTimeType =
            l_clendarContext.getTradingTimeType().trim();
        String l_strProductCode = l_clendarContext.getProductCode().trim();

        // 取引時間テーブル検索
        List l_lisRecords = null;
        try
        {
            // * 　@[検索キー]
            // * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ
            // * 　@部店コード：　@取引時間コンテキストの同名プロパティ
            // * 　@市場コード：　@取引時間コンテキストの同名プロパティ
            // * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ
            // * 　@銘柄コード：　@取引時間コンテキストの同名プロパティ
            // * 　@営業日区分：　@判定した営業日区分
            // * 　@発注日計算：　@”当日”
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("       institution_code       = ? ");
            l_sbWhere.append(" and   branch_code            = ? ");
            l_sbWhere.append(" and   market_code            = ? ");
            l_sbWhere.append(" and   trading_time_type      = ? ");
            l_sbWhere.append(" and   product_code           = ? ");
            l_sbWhere.append(" and   biz_date_type          = ? ");
            l_sbWhere.append(" and   bizdate_calc_parameter = ? ");

            Object[] l_objTradingTimeWhere =
                {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strTradingTimeType,
                    l_strProductCode,
                    l_strBizDateType,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE 
                };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
                l_objTradingTimeWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //該当行が存在しない場合、　@”000000”を返却する
        if (l_lisRecords.size() == 0)
        {
            log.debug("該当行が存在しない場合、　@”000000”を返却する");
            return WEB3GentradeTimeDef.MIN_RETURN;
        }
        
        //上記に一致する行のうち、開始時間が一番早い行の開始時間を返却する
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow) l_lisRecords.get(0);
        String l_strStartTime = l_tradingTimeRow.getStartTime();
        log.debug("市場開局時間：[" + l_strStartTime + "]");
        
        log.exiting(STR_METHOD_NAME);
        return l_strStartTime;
    }

    /**
     * (get市場閉局時間)<BR>
     * 市場閉局時間を取得する。<BR>
     * （getTradeCloseTimeの実装）<BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
     * <BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）　@受付日の営業日区分判定。<BR>
     * 　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は<BR>
     * ”非営業日”と判定し、"000000"を返却し処理を終了する。<BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、行<BR>
     * の営業日区分を取得する。行が取得できなかった場合は、営業日区分<BR>
     * を”通常営業日”とする。<BR>
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute
     * ("web3.tradingcalendarcontext")<BR>
     * <BR>
     * ４）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@銘柄コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@営業日区分：　@判定した営業日区分<BR>
     * 　@発注日計算：　@”当日”<BR>
     * <BR>　@
     * 　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する。
     * <BR>
     * 　@該当行が存在しない場合、　@”235959”を返却する。<BR>
     * <BR>
     * @@return java.lang.String
     * @@roseuid 4014AE5802DA
     */
    public String getTradeCloseTime()
    {
        final String STR_METHOD_NAME = "getTradeCloseTime()";
        log.entering(STR_METHOD_NAME);

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。

        // 受付日時取得
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));

        // ２） 受付日の営業日区分判定。
        String l_strBizDateType = null;
        try
        {
            l_strBizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(
                l_tsOrderAcceptDate);

            // 土日の場合、非営業日として"000000"を返す
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
            {
                return WEB3GentradeTimeDef.MIN_RETURN;
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(STR_METHOD_NAME,wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                wse.getErrorMethod(),
                wse.getMessage(),
                wse);
        }

        // ３） ThreadLocalSystemAttributesRegistryより、取引カレンダ
        // コンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        String l_strMarketCode = l_clendarContext.getMarketCode().trim();
        String l_strBranchCode = l_clendarContext.getBranchCode().trim();
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType().trim();
        String l_strProductCode = l_clendarContext.getProductCode().trim();

        // 取引時間テーブル検索
        List l_lisRecords = null;
        try
        {
            // * 　@[検索キー]
            // * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ
            // * 　@部店コード：　@取引時間コンテキストの同名プロパティ
            // * 　@市場コード：　@取引時間コンテキストの同名プロパティ
            // * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ
            // * 　@銘柄コード：　@取引時間コンテキストの同名プロパティ
            // * 　@営業日区分：　@判定した営業日区分
            // * 　@発注日計算：　@”当日”
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and branch_code = ? ");
            l_sbWhere.append(" and market_code = ? ");
            l_sbWhere.append(" and trading_time_type = ? ");
            l_sbWhere.append(" and product_code = ? ");
            l_sbWhere.append(" and biz_date_type = ? ");
            l_sbWhere.append(" and bizdate_calc_parameter = ? ");

            Object[] l_objTradingTimeWhere =
                {
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strMarketCode,
                    l_strTradingTimeType,
                    l_strProductCode,
                    l_strBizDateType,
                    WEB3BizDateCalcParameterDef.DAY_BIZ_DATE 
                };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objTradingTimeWhere);

        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    
        // 該当行が存在しない場合、　@”235959”を返却する
        if (l_lisRecords.size() == 0)
        {
            log.debug("該当行が存在しない場合、　@”235959”を返却する");
            return WEB3GentradeTimeDef.MAX_RETURN;
        }
        
        //上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow) l_lisRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.debug("市場閉局時間：[" + l_strEndTime + "]");

        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
            
    }
}
@
