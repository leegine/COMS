head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS取引時間管理（WEB3EquityPTSTradingTimeManagement.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 トウ鋒鋼 (中訊) 新規作成
Revision History : 2007/12/20 トウ鋒鋼 (中訊) 仕様変更　@モデル1207、1248、1258、1259
Revision History : 2008/2/18 趙林鵬(中訊) モデルNo.1303
*/
package webbroker3.equity;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS取引時間管理)<BR>
 * PTS取引時間管理クラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3EquityPTSTradingTimeManagement extends WEB3GentradeTradingTimeManagement
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSTradingTimeManagement.class);

    /**
     * @@roseuid 47661650009C
     */
    public WEB3EquityPTSTradingTimeManagement()
    {

    }

    /**
     * (get発注日)<BR>
     * 発注日を取得する。 <BR>
     * （* 取引時間管理.getPTS発注日( )に委譲する。） <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4734311300DB
     */
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        //取引時間管理.getPTS発注日( )に委譲する。
        return WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
    }

    /**
     * (get市場閉局警告市場)<BR>
     * 取引終了警告文を表示する時間帯にある市場の市場コードを配列で返却する。<BR>
     * <BR>
     * １）　@受付日時の取得<BR>
     * 　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@"xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * ２）　@受付日の営業日区分の判定<BR>
     * <BR>
     * 　@２－１）　@this.get営業日区分()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get営業日区分()に設定する引数]<BR>
     * 　@　@　@　@　@　@日付：　@１）で取得した受付日時の日付部分<BR>
     * <BR>
     * 　@２－２）　@get営業日区分()の戻り値が”非営業日”の場合、<BR>
     * 　@　@　@　@　@　@nullを返却し処理を終了する。<BR>
     * <BR>
     * ３）　@市場警告文表示の取得<BR>
     * <BR>
     * 　@３－１）　@部店.get市場警告文表示()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[get市場警告文表示()に設定する引数]<BR>
     * 　@　@　@　@　@　@銘柄タイプ：　@引数.銘柄タイプ<BR>
     * 　@　@　@　@　@　@信用取引区分：　@引数.信用取引区分<BR>
     * 　@　@　@　@　@　@先物／オプション区分：　@先物／オプション区分.DEFAULT<BR>
     * <BR>
     * 　@３－２）　@get市場警告文表示()の戻り値が 0 の場合、<BR>
     * 　@　@　@　@　@　@取引終了警告文言を表示しないと判断し、<BR>
     * 　@　@　@　@　@　@nullを返却し処理を終了する。<BR>
     * <BR>
     * ４）　@（部店市場別・PTS）取扱条件オブジェクトの取得<BR>
     * 　@　@（部店市場別・PTS）取扱条件.get（部店市場別・PTS）取扱条件()をコールする。<BR>
     * <BR>
     * 　@　@[get（部店市場別・PTS）取扱条件()に設定する引数]<BR>
     * 　@　@部店：　@引数.部店<BR>
     * <BR>
     * ５）　@受付日時の取得<BR>
     * 　@　@取引カレンダコンテキスト.getAttribute()をコールする。<BR>
     * <BR>
     * 　@　@[getAttribute()に設定する引数]<BR>
     * 　@　@arg0：　@取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ６）　@市場コードのリストの取得<BR>
     * 　@　@４）で取得した（部店市場別・PTS）取扱条件オブジェクト毎に以下処理を行う。<BR>
     * <BR>
     * 　@　@６－１）　@オーバーナイト実施かどうかをチェックする。<BR>
     * 　@　@　@　@　@２）の戻り値が "終日営業日(午前のみ)" の場合、かつ<BR>
     * 　@　@　@　@　@this.isオーバーナイト実施() == false の場合、以降の処理をスキップする。<BR>
     * <BR>
     * 　@　@　@　@　@[isオーバーナイト実施()に設定する引数]<BR>
     * 　@　@　@　@　@市場コード：　@４）の戻り値.get市場コード()<BR>
     * <BR>
     * 　@　@６－２）　@取扱可能かどうかをチェックする。<BR>
     * 　@　@　@　@　@（部店市場別・PTS）取扱条件.is取扱可能()をコールする。<BR>
     * 　@　@　@　@　@取扱不可（is取扱可能() == false）の場合、以降の処理をスキップする。<BR>
     * <BR>
     * 　@　@　@　@　@[is取扱可能()に設定する引数]<BR>
     * 　@　@　@　@　@銘柄タイプ：　@引数.銘柄タイプ<BR>
     * <BR>
     * 　@　@６－３）　@市場閉局時間を取得する。<BR>
     * 　@　@　@　@　@this.get市場閉局時間()をコールする。<BR>
     * <BR>
     * 　@　@　@　@　@[get市場閉局時間()に設定する引数]<BR>
     * 　@　@　@　@　@市場コード：　@４）の戻り値.get市場コード()<BR>
     * 　@　@　@　@　@商品コード：　@取引カレンダコンテキストの同項目<BR>
     * <BR>
     * 　@　@６－４）　@以下条件に該当する場合、市場コードをArrayListに追加する。<BR>
     * 　@　@　@　@　@※[%d]の分数は、３－１）で取得した数値<BR>
     * <BR>
     * 　@　@　@　@　@　@（市場閉局時間の[%d]分前）　@≦　@（受付日時の時間部分）　@≦　@（市場閉局時間）<BR>
     * <BR>
     * ７）　@生成されたArrayListを返却する。<BR>
     * @@param l_genBranch - (部店)<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * @@param l_strMargineTradeDiv - (信用取引区分)<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 473431B800C3
     */
    public static String[] getTradeCloseMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productType,
        String l_strMargineTradeDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseMarket(WEB3GentradeBranch, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);

        if (l_genBranch == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@受付日時の取得
        //ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        //２）　@受付日の営業日区分の判定
        //　@２－１）　@this.get営業日区分()をコールする。
        String l_strBizDateType = WEB3EquityPTSTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        //　@２－２）　@get営業日区分()の戻り値が”非営業日”の場合、nullを返却し処理を終了する。
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //３）　@市場警告文表示の取得
        //　@３－１）　@部店.get市場警告文表示()をコールする。
        long l_lngMarketMessageSuspension = l_genBranch.getMarketMessageSuspension(
            l_productType, l_strMargineTradeDiv, WEB3FuturesOptionDivDef.DEFAULT);

        //　@３－２）　@get市場警告文表示()の戻り値が 0 の場合、取引終了警告文言を表示しないと判断し、
        //　@　@　@　@　@　@nullを返却し処理を終了する。
        if (l_lngMarketMessageSuspension == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //４）　@（部店市場別・PTS）取扱条件オブジェクトの取得
        //　@　@（部店市場別・PTS）取扱条件.get（部店市場別・PTS）取扱条件()をコールする。
        WEB3GentradeBranchMarketPTSDealtCond[] l_branchMarketPTSDealtConds =
            WEB3GentradeBranchMarketPTSDealtCond.getBranchMarketPTSDealtCond(l_genBranch);

        //５）　@受付日時の取得
        //　@　@取引カレンダコンテキスト.getAttribute()をコールする。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //６）　@市場コードのリストの取得
        List l_lisMarketCodes = new ArrayList();
        int l_intLength = l_branchMarketPTSDealtConds.length;
        WEB3GentradeBranchMarketPTSDealtCond l_branchMarketPTSDealtCond = null;
        String l_strMarketCode = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_branchMarketPTSDealtCond = l_branchMarketPTSDealtConds[i];

            l_strMarketCode = l_branchMarketPTSDealtCond.getMarketCode();

            //６－１）　@オーバーナイト実施かどうかをチェックする。
            if (WEB3BizDateTypeDef.ALL_BIZ_DATE_AM.equals(l_strBizDateType)
                && !isOverNightExecute(l_strMarketCode))
            {
                continue;
            }

            //６－２）　@取扱可能かどうかをチェックする。
            if (!l_branchMarketPTSDealtCond.isHandlingPossible(l_productType))
            {
                continue;
            }

            //６－３）　@市場閉局時間を取得する。
            String l_strTradeCloseTime = getTradeCloseTime(l_strMarketCode, l_clendarContext.getProductCode());

            String l_strOrderAcceptDate =
                WEB3DateUtility.formatDate(l_tsOrderAcceptDate, WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //市場閉局時間
            Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                l_strOrderAcceptDate + l_strTradeCloseTime,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS);

            //市場閉局時間の[%d]分前
            Date l_datTradeCloseTimeBefore =
                WEB3DateUtility.addMinute(l_datTradeCloseTime, -l_lngMarketMessageSuspension);

            //６－４）　@以下条件に該当する場合、市場コードをArrayListに追加する。
            //※[%d]の分数は、３－１）で取得した数値
            //（市場閉局時間の[%d]分前）　@≦　@（受付日時の時間部分）　@≦  （市場閉局時間）
            if (WEB3DateUtility.compareToSecond(l_datTradeCloseTimeBefore, l_tsOrderAcceptDate) <= 0
                && WEB3DateUtility.compareToSecond(l_tsOrderAcceptDate, l_datTradeCloseTime) <= 0)
            {
                l_lisMarketCodes.add(l_strMarketCode);
            }
        }

        //７）　@生成されたArrayListを返却する。
        String[] l_strTradeCloseMarkets = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strTradeCloseMarkets);

        log.exiting(STR_METHOD_NAME);
        return l_strTradeCloseMarkets;
    }

    /**
     * (get市場閉局時間)<BR>
     * 市場閉局時間を取得する。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 　@　@　@取引カレンダコンテキストを取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@"web3.tradingcalendarcontext") <BR>
     * <BR>
     * ２）　@取引時間取得 <BR>
     * <BR>
     * 　@２－１）　@以下の検索キーで取引時間テーブルを検索する。 <BR>
     * <BR>
     * 　@　@　@　@[検索キー] <BR>
     * 　@　@　@　@証券会社コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@市場コード：　@引数の市場コード <BR>
     * 　@　@　@　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@商品コード：　@引数の商品コード <BR>
     * 　@　@　@　@営業日区分：　@”終日営業日” <BR>
     * 　@　@　@　@発注日計算：　@”前営業日” <BR>
     * 　@　@　@　@市場トリガ発行： 　@”SONARへMQトリガを実施する”<BR>
     * <BR>
     * 　@　@　@　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する。 <BR>
     * <BR>
     * 　@２－２）　@２－１）で該当行が存在しない場合、<BR>
     * 　@　@　@　@　@　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@　@　@　@[検索キー] <BR>
     * 　@　@　@　@証券会社コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@市場コード：　@引数の市場コード <BR>
     * 　@　@　@　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@　@　@商品コード：　@引数の商品コード <BR>
     * 　@　@　@　@営業日区分：　@”終日営業日”<BR>
     * 　@　@　@　@発注日計算：　@”当日” <BR>
     * 　@　@　@　@市場トリガ発行： 　@”SONARへMQトリガを実施する”<BR>
     * <BR>
     * 　@　@　@　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する。 <BR>
     * <BR>
     * 　@２－３）　@２－２）で該当行が存在しない場合、23時59分59秒を返却する。 <BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_strProductCode - (商品コード)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 47343577014C
     */
    public static String getTradeCloseTime(String l_strMarketCode, String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTime(String, String)";
        log.entering(STR_METHOD_NAME);

        //１）ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //２）取引時間取得
        //　@２－１）　@以下の検索キーで取引時間テーブルを検索する。
        //　@[検索キー]
        //　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //　@部店コード：　@取引時間コンテキストの同名プロパティ
        //　@市場コード：　@引数の市場コード
        //　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //　@商品コード：　@引数の商品コード
        //　@営業日区分：　@”終日営業日”
        //　@発注日計算：　@”前営業日”
        //　@市場トリガ発行： 　@”SONARへMQトリガを実施する”
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_clendarContext.getInstitutionCode());
        l_lisWheres.add(l_clendarContext.getBranchCode());
        l_lisWheres.add(l_strMarketCode);
        l_lisWheres.add(l_clendarContext.getTradingTimeType());
        l_lisWheres.add(l_strProductCode);
        l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
        l_lisWheres.add(WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE);
        l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisStartRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisStartRecords.size();

        //２－２）　@２－１）で該当行が存在しない場合、以下の検索キーで取引時間テーブルを検索する。
        //　@[検索キー]
        //　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //　@部店コード：　@取引時間コンテキストの同名プロパティ
        //　@市場コード：　@引数の市場コード
        //　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //　@商品コード：　@引数の商品コード
        //　@営業日区分：　@”終日営業日”
        //　@発注日計算：　@”当日”
        //　@市場トリガ発行： 　@”SONARへMQトリガを実施する”
        if (l_intSize == 0)
        {
            l_lisWheres = new ArrayList();
            l_lisWheres.add(l_clendarContext.getInstitutionCode());
            l_lisWheres.add(l_clendarContext.getBranchCode());
            l_lisWheres.add(l_strMarketCode);
            l_lisWheres.add(l_clendarContext.getTradingTimeType());
            l_lisWheres.add(l_strProductCode);
            l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
            l_lisWheres.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
            l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

            l_objWheres = new Object[l_lisWheres.size()];
            l_lisWheres.toArray(l_objWheres);

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_sbWhere.toString(),
                    "start_time desc",
                    null,
                    l_objWheres);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            l_intSize = l_lisStartRecords.size();
            //２－３）　@２－２）で該当行が存在しない場合、23時59分59秒を返却する。
            if (l_intSize == 0)
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3GentradeTimeDef.MAX_RETURN;
            }
        }

        //　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する。
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();

        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * (get営業日区分)<BR>
     * 引数.日付の営業日区分を返却する。 <BR>
     * （* 取引時間管理.getPTS営業日区分( )に委譲する。） <BR>
     * @@param l_tsDate - (日付)<BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     * @@roseuid 473954DA00BA
     */
    public static String getBizDateType(Timestamp l_tsDate) throws WEB3SystemLayerException
    {
        //取引時間管理.getPTS営業日区分( )に委譲する。
        return WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);
    }

    /**
     * xTradeが利用する現在日時をセットする。 <BR>
     * （サービスインタセプタより使用する） <BR>
     * <BR>
     * －プロセス開始時の時間をLocalThreadにセットする。 <BR>
     * －取引時間テーブルの内容をLocalThreadにセットする。 <BR>
     * －日付ロールをLocalThreadにセットする。 （※デフォルト値：”0”をセットする。）<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 47415B55013F
     */
    public static void setTimestamp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setTimestamp()";
        log.entering(STR_METHOD_NAME);

        // Timestampの初期化
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            null);

        // Timestamp設定
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_processTime);

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
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //取引時間テーブルを検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        List l_lisObjTradingTimeWheres = new ArrayList();
        //証券会社コード
        l_lisObjTradingTimeWheres.add(l_strInstitutionCode.trim());
        //部店コード
        l_lisObjTradingTimeWheres.add(l_strBranchCode.trim());
        //受付時間区分
        l_lisObjTradingTimeWheres.add(l_strTradingTimeType.trim());
        //営業日区分
        l_lisObjTradingTimeWheres.add(l_strBizDateType);

        //受付時間
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_format.format(l_processTime);

        //市場コード
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lisObjTradingTimeWheres.add(l_strMarketCode.trim());
        }
        //銘柄コード
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code  = ? ");
            l_lisObjTradingTimeWheres.add(l_strProductCode.trim());
        }

        int l_intSize = l_lisObjTradingTimeWheres.size();
        Object[] l_objTradingTimeWheres = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objTradingTimeWheres[i] = l_lisObjTradingTimeWheres.get(i);
        }

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //件数チェック
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intLength; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow =
                (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime))
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }

        if (l_tradingTimeRow == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
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
            l_tempRow = (TradingTimeRow)l_lisRecords.get(i);
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
        ThreadLocalSystemAttributesRegistry.setAttribute(TRADING_CAL_CONTEXT_PATH, l_clendarContext);

        //日付ロールをLocalThreadにセットする。
        Integer l_intOffset = new Integer(0);
        ThreadLocalSystemAttributesRegistry.setAttribute(OFFSET_TAG, l_intOffset);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isオーバーナイト実施)<BR>
     * オーバーナイト実施しているかどうかを判定する。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 　@　@　@取引カレンダコンテキストを取得する。 <BR>
     * 　@　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@　@"web3.tradingcalendarcontext") <BR>
     * <BR>
     * ２）　@以下の検索キーで取引時間テーブルを検索する。 <BR>
     * <BR>
     * 　@　@[検索キー] <BR>
     * 　@　@証券会社コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@市場コード：　@引数の市場コード <BR>
     * 　@　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@　@商品コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@　@営業日区分：　@"終日営業日"<BR>
     * 　@　@発注日計算：　@"前営業日"<BR>
     * 　@　@市場トリガ発行：　@"SONARへMQとリガを実施する"<BR>
     * <BR>
     * ３）　@２）でレコードが取得できた場合、trueを返却する。<BR>
     * 　@　@取得できなかった場合、falseを返却する。<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 47590CD20284
     */
    public static boolean isOverNightExecute(String l_strMarketCode) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isOverNightExecute(String)";
        log.entering(STR_METHOD_NAME);

        //１）ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //２）　@以下の検索キーで取引時間テーブルを検索する。
        //　@[検索キー]
        //　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //　@部店コード：　@取引時間コンテキストの同名プロパティ
        //　@市場コード：　@引数の市場コード
        //　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //　@商品コード：　@取引時間コンテキストの同名プロパティ
        //　@営業日区分：　@”終日営業日”
        //　@発注日計算：　@”前営業日”
        //　@市場トリガ発行： 　@”SONARへMQトリガを実施する”
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisWheres = new ArrayList();
        l_lisWheres.add(l_clendarContext.getInstitutionCode());
        l_lisWheres.add(l_clendarContext.getBranchCode());
        l_lisWheres.add(l_strMarketCode);
        l_lisWheres.add(l_clendarContext.getTradingTimeType());
        l_lisWheres.add(l_clendarContext.getProductCode());
        l_lisWheres.add(WEB3BizDateTypeDef.BIZ_DATE);
        l_lisWheres.add(WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE);
        l_lisWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objWheres = new Object[l_lisWheres.size()];
        l_lisWheres.toArray(l_objWheres);

        List l_lisRecords = new ArrayList();

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        //３）　@２）でレコードが取得できた場合、trueを返却する。
        if (l_intSize != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //取得できなかった場合、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (validate注文受付可能)<BR>
     * 注文受付可能かをチェックする。<BR>
     * <BR>
     * １）　@緊急停止、バッチ中チェック<BR>
     * 注文受付ステイタステーブルを取引カレンダコンテキストの内容で読み、<BR>
     * 取得した行の注文受付ステイタスが”通常”でない場合は例外をスローする。<BR>
     * （注文受付不可のステイタスには、”バッチ処理中”、”緊急停止中”があり、<BR>
     * 例外メッセージをわける）<BR>
     * -バッチ処理中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -システム緊急停止中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * ２）　@取引時間コンテキスト(*1)の注文受付トランザクション＝”07”（照会）の場合は、<BR>
     *  以降の処理は行わずにreturnする。<BR>
     *  取引時間コンテキスト(*1)の注文受付トランザクション≠”07”（照会）の 場合は、<BR>
     *  以下の処理を続行する。 <BR>
     * ３）　@受付不可時間帯チェック<BR>
     * 　@取引時間テーブルを以下の条件で検索し、該当行の「受付可能」項目が"受付不可"であれば、<BR>
     * 　@注文受付不可と判定する。<BR>
     * -注文受付ステイタスが受付中以外-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00013<BR>
     * 　@該当行が複数行ある場合は、１件でも「受付可能」であれば注文受付可能とする。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@　@※但し、取引時間コンテキストの市場コードプロパティがnullであれば、<BR>
     * 市場コードは検索条件に含めない（すべての市場を対象とする）<BR>
     * 　@受付時間区分：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@営業日区分：　@(*3)<BR>
     * 　@銘柄コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     *     ※但し、取引時間コンテキストの銘柄コードプロパティがnullであれば、<BR>
     * 銘柄コードは検索条件に含めない（すべての銘柄を対象とする）<BR>
     * 　@開始時間 <= 受付時間(*2) <=　@終了時間<BR>
     * <BR>
     * 　@該当行が存在しない場合は、データ不整合（システムエラー）として例外をスローする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     *  (*1)　@取引時間コンテキストの取得<BR>
     *  －ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     *  (*2) 受付時間の取得<BR>
     *  －ThreadLocalSystemAttributesRegistryより受付日時を取得し、<BR>
     * 取得した日時の時間部分。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     *  (*3) 営業日区分の取得<BR>
     *  －this.get営業日区分()をコールして取得する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAccept() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strProductCode;

        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        //部店コード
        l_strBranchCode = l_clendarContext.getBranchCode();
        //市場コード
        l_strMarketCode = l_clendarContext.getMarketCode();
        //注文受付商品
        l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //注文受付トランザクション
        l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();
        //受付時間区分
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //銘柄コード
        l_strProductCode = l_clendarContext.getProductCode();

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
        //例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        // 　@取引時間コンテキスト.受付時間区分
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strTradingTimeType == null))
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        // １）　@緊急停止、バッチ中チェック
        try
        {
            //注文受付ステイタステーブル
            OrderAcceptStatusRow l_row =
                OrderAcceptStatusDao.findRowByInstitutionCodeBranchCodeOrderAccProductOrderAccTransaction(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strOrderAccProduct,
                    l_strOrderAccTransaction);

            if (l_row != null)
            {
                l_strOrderAcceptStatus = l_row.getOrderAcceptStatus();
            }
            else
            {
                l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
            }
        }
        catch (DataFindException l_ex)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // バッチ処理中
            log.debug("システムがバッチ処理中です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "システムがバッチ処理中です。");
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // 緊急停止中
            log.debug("システムが緊急停止中です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "システムが緊急停止中です。");
        }

        // ２）　@取引時間コンテキスト(*1)の注文受付トランザクション＝”07”（照会）
        // の場合は、以降の処理は行わずにreturnする
        if (WEB3OrderAccTransactionDef.REFERENCE.equals(l_strOrderAccTransaction))
        {
            log.info("注文受付トランザクション＝”07”（照会） ：　@受付可能");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //３）受付不可時間帯チェック

        //受付時間の取得(取得した日時の時間部分)
        Timestamp l_tsOrderAcceptDate = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
            TIMESTAMP_TAG);
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        //営業日区分の取得
        String l_bizDateType = getBizDateType(l_tsOrderAcceptDate);

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
        l_lstObjTradingTimeWhere.add(l_bizDateType);

        //市場コード
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
        }
        //銘柄コード
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code = ? ");
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
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //件数チェック
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            log.debug("取引時間テーブル検索： 件数 = 0");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "取引時間テーブル検索： 件数 = 0");
        }

        //１件でも「受付可能」であれば注文受付可能とする
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder())
                && Long.parseLong(l_tradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug("システムが受付可能時間外です。" + "：受付不可時間帯");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "システムが受付可能時間外です。");
        }

        log.debug(STR_METHOD_NAME + "：受付時間帯");
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is市場開局時間帯)<BR>
     * 市場閉局時間帯かどうかを返却する。<BR>
     * 市場開局時間帯の場合true、市場閉局時間帯の場合falseを返却する。<BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>
     *  　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     *     "xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ２）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "web3.tradingcalendarcontext")<BR>
     * 　@取得したコンテキストの以下の項目にnullが格納されていた場合は、<BR>
     * データ不整合として例外をスローする。<BR>
     * -データ不整合エラー-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80006<BR>
     * <BR>
     * 　@取引時間コンテキスト.証券会社コード<BR>
     * 　@取引時間コンテキスト.部店コード<BR>
     * 　@取引時間コンテキスト.市場コード<BR>
     * 　@取引時間コンテキスト.受付時間区分<BR>
     * 　@取引時間コンテキスト.銘柄コード<BR>
     * <BR>
     * ３）　@受付日の営業日区分取得。<BR>
     * 　@this.get営業日区分()をコールして取得する。<BR>
     * <BR>
     * 　@※get営業日区分()の戻り値が"非営業日"の場合は、falseを返却する。<BR>
     * <BR>
     * ４）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@商品コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@営業日区分：　@判定した営業日区分<BR>
     * 　@　@市場トリガ発行： ”SONARへMQトリガを実施する”<BR>
     * <BR>
     * 　@該当行が存在しない場合は、false（閉局中）を返却する。 <BR>
     * <BR>
     * 　@取得した行より、以下の通り戻り値を判定する。<BR>
     * <BR>
     *  （市場開局時間帯(*1)　@≦　@（受付日時の時間部分）≦ 市場閉局時間帯(*2)）<BR>
     *  であればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@(*1)市場開局時間：　@上記に一致する行のうち、開始時間が<BR>
     * 一番早い行の開始時間。<BR>
     * 　@(*2)市場閉局時間：　@上記に一致する行のうち、開始時間が<BR>
     * 一番遅い行の終了時間。<BR>
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     * @@roseuid 4020D19D025D
     */
    public static boolean isTradeOpenTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeOpenTimeZone()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnReturn;

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // ２） ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
        //データ不整合として例外をスローする。
        //* 　@取引時間コンテキスト.証券会社コード
        //* 　@取引時間コンテキスト.部店コード
        //* 　@取引時間コンテキスト.市場コード
        //* 　@取引時間コンテキスト.受付時間区分
        //* 　@取引時間コンテキスト.銘柄コード
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        String l_strBranchCode = l_clendarContext.getBranchCode();
        String l_strMarketCode = l_clendarContext.getMarketCode();
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        String l_strProductCode = l_clendarContext.getProductCode();
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //３）　@受付日の営業日区分取得。
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

        //get営業日区分()の戻り値が"非営業日"の場合は、falseを返却する。
        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug("受付日時( " + l_tsOrderAcceptDate + ") ： 非営業日の場合。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // ４） 取引時間取得
        //  以下の検索キーで取引時間テーブルを検索する。
        //[検索キー]
        //* 　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //* 　@部店コード：　@取引時間コンテキストの同名プロパティ
        //* 　@市場コード：　@取引時間コンテキストの同名プロパティ
        //* 　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //* 　@商品コード：　@取引時間コンテキストの同名プロパティ
        //* 　@営業日区分：　@判定した営業日区分
        //* 　@市場トリガ発行： ”SONARへMQトリガを実施する”
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and submit_market_trigger = ? ");

        List l_lisTradingTimeWheres = new ArrayList();
        l_lisTradingTimeWheres.add(l_strInstitutionCode);
        l_lisTradingTimeWheres.add(l_strBranchCode);
        l_lisTradingTimeWheres.add(l_strMarketCode);
        l_lisTradingTimeWheres.add(l_strTradingTimeType);
        l_lisTradingTimeWheres.add(l_strProductCode);
        l_lisTradingTimeWheres.add(l_strBizDateType);
        l_lisTradingTimeWheres.add(WEB3SubmitMarketTriggerDef.TRIGGER);

        Object[] l_objTradingTimeWheres =
            new Object[l_lisTradingTimeWheres.size()];
        l_lisTradingTimeWheres.toArray(l_objTradingTimeWheres);

        List l_lisStartRecords;
        List l_lisEndRecords;
        String l_strMarketStartTime;
        String l_strMarketEndTime;
        // 開局時刻の最小値を求める
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
                l_objTradingTimeWheres);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //　@該当行が存在しない場合は、false（閉局中）を返却する。
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("　@該当行が存在しない場合は、false（閉局中）を返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        l_strMarketStartTime = ((TradingTimeRow)l_lisStartRecords.get(0)).getStartTime();

        // 閉局時刻の最大値を求める
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisEndRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "end_time desc",
                null,
                l_objTradingTimeWheres);

        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        // 該当行が存在しない場合は、false（閉局中）を返却する。
        int l_intEndSize = l_lisEndRecords.size();
        if (l_intEndSize == 0)
        {
            log.debug("該当行が存在しない場合は、false（閉局中）を返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        l_strMarketEndTime = ((TradingTimeRow)l_lisEndRecords.get(0)).getEndTime();

        // 取得行より戻り値を判定する
        SimpleDateFormat l_simpleDateFormat =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_simpleDateFormat.format(l_tsOrderAcceptDate);

        log.debug("*** 取引時間管理*** 判定時刻 = " + l_strTime);
        log.debug("*** 取引時間管理*** 開局時間 = " + l_strMarketStartTime);
        log.debug("*** 取引時間管理*** 閉局時間 = " + l_strMarketEndTime);

        if ((l_strMarketStartTime.compareTo(l_strTime) <= 0)
            && (l_strTime.compareTo(l_strMarketEndTime) <= 0))
        {
            l_blnReturn = true;
            log.debug("*** 取引時間管理***   市場開局時間帯");
        }
        else
        {
            l_blnReturn = false;
            log.debug("*** 取引時間管理***   市場閉局時間帯");
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * get発注日<BR>
     * 発注日を取得する。<BR>
     * <BR>
     * this.get発注日()にて発注日を取得する。<BR>
     * 取得した発注日と引数の確認時発注日が違う日付であれば<BR>
     * 例外をスローする。<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : BUSINESS_ERROR_00205<BR>
     * <BR>
     * (*)完了時処理で使用する。<BR>
     * @@param l_datConfirmBizDate - 確認時発注日<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 406937AB0203
     */
    public static Date getOrderBizDate(Date l_datConfirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = getOrderBizDate();
        if (l_datBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datConfirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datConfirmBizDate) != 0)
        {
            log.debug("発注日が変わりました。お手数ですが、もう一度入力し直してください。");
            log.exiting(STR_METHOD_NAME);
            //取得した発注日と引数の確認時発注日が違う日付であれば例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "発注日が変わりました。お手数ですが、もう一度入力し直してください。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datConfirmBizDate;
    }
}
@
