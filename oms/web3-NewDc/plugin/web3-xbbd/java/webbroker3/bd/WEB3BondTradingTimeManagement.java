head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取引時間管理(WEB3BondTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/7 郭英 (中訊) 新規作成
                      : 2006/09/29 張騰宇 (中訊) モデル 094
                      : 2006/10/11 張騰宇 (中訊) モデル 120
Revesion History : 2007/07/11 何文敏 (中訊) 仕様変更・モデル198
*/

package webbroker3.bd;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.common.define.WEB3TradingTimeCheckDivDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券取引時間管理)
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3BondTradingTimeManagement extends WEB3GentradeTradingTimeManagement
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3BondTradingTimeManagement.class);

    /**
     * (validate注文受付可能)<BR>
     * 注文受付可能かをチェックする。 <BR>
     * <BR>
     * １）　@緊急停止、バッチ中チェック <BR>
     * 注文受付ステイタステーブルを取引カレンダコンテキストの内容で読み、 <BR>
     * 取得した行の注文受付ステイタスが”通常”でない場合は例外をスローする。 <BR>
     * <BR>
     * （注文受付不可のステイタスには、”バッチ処理中”、”緊急停止中”があり、<BR>
     * 　@　@例外メッセージをわける） <BR>
     * -バッチ処理中-<BR>
     * 　@　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@: BUSINESS_ERROR_00011<BR>
     * -システム緊急停止中-<BR>
     * 　@　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@: BUSINESS_ERROR_00012<BR>
     * <BR>
     * ２）　@取引時間コンテキスト(*1)の注文受付トランザクション＝”07”（照会）の場合は、 <BR>
     * 　@　@　@以降の処理は行わずにreturnする。 <BR>
     * 　@　@　@取引時間コンテキスト(*1)の注文受付トランザクション≠”07”（照会）の場合は、 <BR>
     * 　@　@　@以下の処理を続行する。 <BR>
     *３）　@債券銘柄.取引時間チェック区分 == ”取引時間をチェックしない"の場合は、<BR>
     *　@　@　@以降の処理は行なわずにreturnする。 <BR>
     *　@　@　@債券銘柄.取引時間チェック区分 ≠ ”取引時間をチェックしない"の場合は、<BR>
     *　@　@　@以下の処理を続行する。<BR>
     * <BR>
     * 4）　@受付不可時間帯チェック <BR>
     * 　@取引時間テーブルを以下の条件で検索し、<BR>
     * 　@　@該当行の「受付可能」項目が"受付不可"であれば、注文受付不可と判定する。 <BR>
     * 　@　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@: BUSINESS_ERROR_00013<BR>
     * 　@該当行が複数行ある場合は、１件でも「受付可能」であれば注文受付可能とする。 <BR>
     * <BR>
     * 　@[検索キー] <BR>
     * 　@証券会社コード：　@取引時間コンテキスト(*1)の同名プロパティ <BR>
     * 　@部店コード：　@取引時間コンテキスト(*1)の同名プロパティ <BR>
     * 　@市場コード：　@取引時間コンテキスト(*1)の同名プロパティ <BR>
     * 　@　@※但し、取引時間コンテキストの市場コードプロパティがnullであれば、<BR>
     * 　@　@　@　@市場コードは検索条件に含めない（すべての市場を対象とする） <BR>
     * 　@受付時間区分：　@取引時間コンテキスト(*1)の同名プロパティ <BR>
     * 　@営業日区分：　@(*3) <BR>
     * 　@銘柄コード：　@取引時間コンテキスト(*1)の同名プロパティ <BR>
     * 　@　@※但し、取引時間コンテキストの銘柄コードプロパティがnullであれば、<BR>
     * 　@　@　@　@銘柄コードは検索条件に含めない（すべての銘柄を対象とする） <BR>
     * 　@開始時間 <= 受付時間(*2) <=　@終了時間　@ <BR>
     * <BR>
     * 　@該当行が存在しない場合は、データ不整合（システムエラー）として例外をスローする。 <BR>
     * 　@　@　@　@class　@: WEB3SystemLayerException<BR>
     * 　@　@　@　@tag　@　@: SYSTEM_ERROR_80005<BR>
     * <BR>
     *５）海外市場営業日チェック  <BR>
     *　@　@this.is海外市場営業日()の戻り値 == false の場合、例外をスローする。  <BR>
     * 　@　@　@　@class　@: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@　@: BUSINESS_ERROR_02667<BR>
     * <BR>
     *　@　@[引数]  <BR>
     *　@　@　@債券銘柄：　@引数.債券銘柄  <BR>
     *　@　@　@国内発注日：　@get発注日（）の戻り値 <BR>
     * <BR>
     * ================================================================================<BR>
     * <BR>
     * (*1)　@取引時間コンテキストの取得 <BR>
     * －ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext") <BR>
     * <BR>
     * (*2) 受付時間の取得 <BR>
     * －ThreadLocalSystemAttributesRegistryより受付日時を取得し、取得した日時の時間部分。 <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") <BR>
     * <BR>
     * (*3) 営業日区分の取得 <BR>
     * －取得した受付日時の曜日を取得し、土曜日または日曜日の場合は”非営業日”。 <BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、行の営業日区分を取得する。<BR>
     * 　@行が取得できなかった場合は、営業日区分を”通常営業日”とする。 <BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CEE39F0103
     */
    public static void validateOrderAccept(WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAccept(String)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //証券会社コード
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //部店コード
        String l_strBranchCode = l_clendarContext.getBranchCode();
        //市場コード
        String l_strMarketCode = l_clendarContext.getMarketCode();
        //注文受付商品
        String l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //注文受付トランザクション
        String l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();
        //受付時間区分
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //銘柄コード
        String l_strProductCode = l_clendarContext.getProductCode();

        String l_strOrderAcceptStatus = null;

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
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
        catch (DataFindException nfe)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
        }
        catch (DataQueryException dqe)
        {
            log.error(STR_METHOD_NAME, dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // バッチ処理中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // 緊急停止中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        // ２）　@取引時間コンテキスト(*1)の注文受付トランザクション＝”07”（照会）
        // の場合は、以降の処理は行わずにreturnする
        if(WEB3OrderAccTransactionDef.REFERENCE.equals(l_strOrderAccTransaction))
        {
            log.info("注文受付トランザクション＝”07”（照会） ：　@受付可能");
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //３）　@債券銘柄.取引時間チェック区分 == ”取引時間をチェックしない"の場合は、
        //    以降の処理は行なわずにreturnする。
        //    債券銘柄.取引時間チェック区分 ≠ ”取引時間をチェックしない"の場合は、
        //　@　@以下の処理を続行する
        if (WEB3TradingTimeCheckDivDef.TRADING_TIME_NOT_CHECK.equals(l_bondProduct.getTradingTimeCheckDiv()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //4）受付不可時間帯チェック

        //受付時間の取得(取得した日時の時間部分)
        Timestamp l_tsOrderAcceptDate = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(
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
        l_sbWhere.append(" and start_time <= ? ");
        l_sbWhere.append(" and end_time >= ? ");

        ArrayList l_lstObjTradingTimeWhere = new ArrayList();
        //証券会社コード
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //部店コード
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //受付時間区分
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //営業日区分
        l_lstObjTradingTimeWhere.add(l_bizDateType);
        //受付時間
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
        //受付時間
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "取引時間テーブル検索： 件数 = 0");
        }

        //１件でも「受付可能」であれば注文受付可能とする
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder()) &&
                Long.parseLong(l_tradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime) &&
                Long.parseLong(l_tradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime) )
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
                break;
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "：受付不可時間帯");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3BondTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        //５）海外市場営業日チェック
        //    this.is海外市場営業日()の戻り値 == false の場合、例外をスローする。
        //    [引数]
        //       債券銘柄：　@引数.債券銘柄
        //       国内発注日：　@get発注日（）の戻り値
        WEB3BondTradingTimeManagement l_bondTradingTimeManagement = new WEB3BondTradingTimeManagement();
        boolean l_blnIsForeignMarketBizDate = l_bondTradingTimeManagement.isForeignMarketBizDate(l_bondProduct, getOrderBizDate());
        if ( !l_blnIsForeignMarketBizDate)
        {
            log.debug("発注日が海外市場営業日ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02667,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "発注日が海外市場営業日ではありません。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is海外市場営業日)<BR>
     *海外市場営業日チェックを行なう。 <BR>
     *<BR>
     *１）債券銘柄.カレンダー連携市場コード == nullの場合、trueを返却する。 <BR>
     *<BR>
     *２）債券銘柄.カレンダー連携市場コード != null の場合、以下の処理を行なう。 <BR>
     *<BR>
     *２－１）以下の条件で外株海外市場カレンダーテーブルを検索し、<BR>
     *該当レコードが取得できた場合、falseを返却する。 <BR>
     *　@　@　@　@それ以外の場合、trueを返却する。 <BR>
     *<BR>
     *　@　@　@　@[検索条件]  <BR>
     *　@　@　@　@証券会社コード = 債券銘柄.証券会社コード  <BR>
     *　@　@　@　@市場コード = 債券銘柄.カレンダー連携市場コード  <BR>
     *　@　@　@　@日付 = 国内発注日  <BR>
     *　@　@　@　@営業日区分 = ”非営業日”<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄
     * @@param l_datBizDate - (国内発注日)<BR>
     * 国内発注日
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isForeignMarketBizDate(
        WEB3BondProduct l_bondProduct,
        Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isForeignMarketBizDate(WEB3BondProduct,Date) ";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3BondTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）債券銘柄.カレンダー連携市場コード == nullの場合、trueを返却する。
        if (l_bondProduct.getCalLinkedMarketCode() == null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //２）債券銘柄.カレンダー連携市場コード != null の場合、以下の処理を行なう。
        //２－１）以下の条件で外株海外市場カレンダーテーブルを検索し、該当レコードが取得できた場合、falseを返却する。
        //　@　@それ以外の場合、trueを返却する

        //外株海外市場カレンダーテーブルを検索
        String l_sbWhere =
            " institution_code = ? " +
            " and market_code = ? " +
            " and biz_date = ? " +
            " and biz_date_type = ? ";

        Object[] l_objForeignMarketBizDaterWhere =
            {l_bondProduct.getInstitution().getInstitutionCode(),
             l_bondProduct.getCalLinkedMarketCode(),
             l_datBizDate,
             WEB3BizDateTypeDef.NO_BIZ_DATE};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                FeqCalendarRow.TYPE,
                l_sbWhere,
                l_objForeignMarketBizDaterWhere);
        }
        catch (DataFindException dfe)
        {
            log.error(STR_METHOD_NAME, dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
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
                WEB3BondTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                dne.getMessage(),
                dne);
        }

        if (l_lisRecords != null && !l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
    }

    /**
     * (get国内債券締切時刻)<BR>
     * 国内債券の締切時刻を取得する。<BR>
     * <BR>
     * 1）ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。<BR>
     * 　@　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@　@取引時間管理.TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * 2) 営業日区分の取得<BR>
     * 　@　@get営業日区分をコールする。<BR>
     * 　@　@[引数]<BR>
     * 　@　@日付：引数.日付<BR>
     * 3) 取引時間を取得する。<BR>
     * 　@　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * 　@　@[検索キー]<BR>
     * 　@　@証券会社コード：取引時間コンテキストの同名プロパティ<BR>
     * 　@　@部店コード：取引時間コンテキストの同名プロパティ<BR>
     * 　@　@市場コード：取引時間コンテキストの同名プロパティ<BR>
     * 　@　@受付時間区分：取引時間コンテキストの同名プロパティ<BR>
     * 　@　@商品コード：取引時間コンテキストの銘柄コード<BR>
     * 　@　@営業日区分：2)で取得した営業日区分<BR>
     * 　@　@発注日計算：「1：翌営業日」<BR>
     * 　@　@※ 取得したレコードが1件以外なら「データ不整合エラー」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3SystemLayerException<BR>
     * 　@　@　@　@tag  :　@SYSTEM_ERROR_80006<BR>
     * <BR>
     * 4) 取得した取引時間Row.開始時間を返却する。<BR>
     * <BR>
     * @@param l_datDate - (日付)<BR>
     * 日付<BR>
     * @@return String
     * @@throws WEB3BaseException
     * 
     */
    public String getBondDomesticLimitTime(Date l_datDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBondDomesticLimitTime(Date)";
        log.entering(STR_METHOD_NAME);

        // 1）ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        // ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TRADING_CAL_CONTEXT_PATH)
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // 営業日区分の取得
        // get営業日区分をコールする。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datDate.getTime()));

        // 3) 取引時間を取得する。
        // 以下の検索キーで取引時間テーブルを検索する。
        // 証券会社コード：取引時間コンテキストの同名プロパティ
        // 部店コード：取引時間コンテキストの同名プロパティ
        // 市場コード：取引時間コンテキストの同名プロパティ
        // 受付時間区分：取引時間コンテキストの同名プロパティ
        // 商品コード：取引時間コンテキストの銘柄コード
        // 営業日区分：2)で取得した営業日区分
        // 発注日計算：「1：翌営業日」
        List l_lisStartRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");

        List l_lisTradingTimeWheres = new ArrayList();
        l_lisTradingTimeWheres.add(l_clendarContext.getInstitutionCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getBranchCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getMarketCode());
        l_lisTradingTimeWheres.add(l_clendarContext.getTradingTimeType());
        l_lisTradingTimeWheres.add(l_clendarContext.getProductCode());
        l_lisTradingTimeWheres.add(l_strBizDateType);
        l_lisTradingTimeWheres.add(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);

        Object[] l_tradingTimeWheres =
            new Object[l_lisTradingTimeWheres.size()];
        l_lisTradingTimeWheres.toArray(l_tradingTimeWheres);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_tradingTimeWheres);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // ※ 取得したレコードが1件以外なら「データ不整合エラー」の例外をスローする。
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize != 1)
        {
            log.debug("データ不整合エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー");
        }

        // 4) 取得した取引時間Row.開始時間を返却する。 
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strStartTime = l_tradingTimeRow.getStartTime();

        log.exiting(STR_METHOD_NAME);
        return l_strStartTime;
    }
}
@
