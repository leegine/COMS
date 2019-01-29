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
filename	WEB3GentradeTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引時間管理(WEB3GentradeTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/13 鄒政 (中訊) 新規作成
Revesion History : 2004/08/11 孟東 (中訊) 変更027を対応
Revesion History : 2004/08/11 WEB3-XBIFO-A-CD-0088を対応
Revesion History : 2005/07/08 孟東 (中訊) get発注日(void)に外株処理を追加
Revesion History : 2005/07/08 孟東 (中訊) get市場閉局警告外株市場を追加
Revesion History : 2005/07/20 石炎 (中訊) isTradeCloseTimeZone()を追加
Revesion History : 2005/07/20 石炎 (中訊) isTradeCloseTimeZone(Date)を追加
Revesion History : 2005/07/21 石炎 (中訊) get発注日(void)に外株連携処理を追加
Revesion History : 2005/07/25 孟東 (中訊) isトリガ発行()を修正
Revesion History : 2005/10/07 孟東 (中訊) isトリガ発行()を修正
Revesion History : 2005/10/07 孟東 (中訊) validate連続注文受付可能()を追加
Revesion History : 2005/10/18 孟東 (中訊) is市場開局時間帯()を修正
Revesion History : 2005/11/30 孟東 (中訊) validateダウンロード時間帯()を追加
Revesion History : 2006/02/16 凌建平 (中訊) 共通仕様変更管理台帳No.174、175
Revesion History : 2006/02/26 凌建平 (中訊) 共通仕様変更管理台帳No.177
Revesion History : 2006/03/24 凌建平 (中訊) 共通仕様変更管理台帳No.181を対応
Revesion History : 2006/05/11 凌建平 (中訊)【共通】仕様変更・モデルNo.189
Revesion History : 2006/06/01 凌建平 (中訊)【共通】仕様変更・モデルNo.195
Revesion History : 2007/06/11 栄イ (中訊)【共通】仕様変更・モデルNo.233、237、239、243、244、246
Revesion History : 2007/06/15 栄イ (中訊)【共通】仕様変更・モデルNo.248
Revesion History : 2007/06/15 栄イ (中訊)【共通】仕様変更・モデルNo.249
Revesion History : 2007/06/22 栄イ (中訊)【共通】仕様変更・モデルNo.253
Revesion History : 2007/06/27 栄イ (中訊)【共通】仕様変更・モデルNo.251
Revesion History : 2007/06/27 栄イ (中訊)【共通】仕様変更・モデルNo.256
Revesion History : 2007/06/29 栄イ (中訊)【共通】仕様変更・モデルNo.254
Revesion History : 2007/07/03 栄イ (中訊)【共通】仕様変更・モデルNo.259
Revesion History : 2007/07/05 栄イ (中訊)【共通】仕様変更・モデルNo.263
Revesion History : 2007/12/17 武波 (中訊)【共通】仕様変更・モデルNo.289
Revesion History : 2007/12/18 周墨洋 (中訊)【共通】仕様変更・モデルNo.296
Revesion History : 2008/03/13 栄イ (中訊)【共通】仕様変更・モデルNo.321
Revesion History : 2008/04/16 栄イ (中訊)【共通】仕様変更・モデルNo.325
Revesion History : 2008/04/19 栄イ (中訊)【共通】仕様変更・モデルNo.326
Revesion History : 2008/04/21 栄イ (中訊)【共通】仕様変更・モデルNo.327
Revesion History : 2008/12/25 齋藤 (FTL) getBizDateType(Timestamp)を主キー検索に変更
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderAcceptStatusDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.CalendarDao;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarDao;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * @@author 孟 東
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
/**
 * 取引時間管理<BR>
 * 取引時間に関連した手続きを提供するクラス。<BR>
 */
public class WEB3GentradeTradingTimeManagement
{

    /**
     * ThreadLocalに保存する受付日時の変数名。
     */
    public static String TIMESTAMP_TAG = "xblocks.gtl.attributes.systemtimestamp";

    /**
     * ThreadLocalに保存するオフセットの変数名。
     */
    public static String OFFSET_TAG = "xblocks.gtl.attributes.bizdate.offset";

    /**
     * ThreadLocalに保存する取引カレンダコンテキストの変数名。
     */
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";

    /**
     * ThreadLocalに保存する、発注日計算用の基準日時の変数名。
     */
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingTimeManagement.class);

    /**
     * validate注文受付可能<BR>
     * 注文受付可能かをチェックする。<BR>
     * <BR>
     * １）　@緊急停止、バッチ中チェック<BR>
     * 注文受付ステイタステーブルを取引カレンダコンテキストの内容で読み、<BR>
     * 取得した行の注文受付ステイタスが”通常”でない場合は例外をスローする。<BR>
     * （注文受付不可のステイタスには、”バッチ処理中”、”緊急停止中”が<BR>
     * あり、例外メッセージをわける）<BR>
     * -バッチ処理中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -システム緊急停止中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * ２）　@取引時間コンテキスト(*1)の注文受付トランザクション＝”07”（照会）<BR>
     *  の場合は、以降の処理は行わずにreturnする。<BR>
     *  取引時間コンテキスト(*1)の注文受付トランザクション≠”07”（照会）の <BR>
     *  場合は、以下の処理を続行する。 <BR>
     * ３）　@受付不可時間帯チェック<BR>
     * 　@取引時間テーブルを以下の条件で検索し、該当行の「受付可能」<BR>
     * 項目が"受付不可"であれば、注文受付不可と判定する。<BR>
     * -注文受付ステイタスが受付中以外-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00013<BR>
     * 　@該当行が複数行ある場合は、１件でも「受付可能」であれば注文<BR>
     * 受付可能とする。<BR>
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
     * 　@該当行が存在しない場合は、該当データなし（システムエラー）<BR>
     * として例外をスローする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80005<BR>
     * <BR>
     *  (*1)　@取引時間コンテキストの取得<BR>
     *  －ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
     * <BR>
     * <BR>
     *  (*2) 受付時間の取得<BR>
     *  －ThreadLocalSystemAttributesRegistryより受付日時を取得し、<BR>
     * 取得した日時の時間部分。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.system_
     * timestamp")<BR>
     * <BR>
     *  (*3) 営業日区分の取得<BR>
     *  －this.get営業日区分()をコールして取得する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAccept() throws WEB3BaseException
    {
        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;
        String l_strMarketCode;
        String l_strTradingTimeType;
        String l_strProductCode;
        final String STR_METHOD_NAME = "validateOrderAccept()";
        log.entering(STR_METHOD_NAME);

        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
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
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
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

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // バッチ処理中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // 緊急停止中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3GentradeTradingTimeManagement.class.getName()
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

        //３）受付不可時間帯チェック

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
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        ArrayList l_lstObjTradingTimeWhere = new ArrayList();
        //証券会社コード
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //部店コード
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //受付時間区分
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //営業日区分
        l_lstObjTradingTimeWhere.add(l_bizDateType);
//        //受付時間
//        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
//        //受付時間
//        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

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
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "：受付不可時間帯");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.debug(STR_METHOD_NAME + "：受付時間帯");
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * isトリガ発行<BR>
     * 市場へリアルタイムにトリガを発行するかを判定する。<BR>
     * <BR>
     * １）　@発注条件判定<BR>
     * 　@発注条件 == ”逆指値”の場合、falseを返却する。<BR>
     * 　@※引数の発注条件 != nullの場合のみ、当判定を行う。<BR>
     * 　@※引数の発注条件 == nullの場合は、当判定は行わず、以降の処理を行う。<BR> 
     * <BR>
     * ２）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * ("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * ３）ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * ４）　@受付日の営業日区分判定。<BR>
     * 　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は<BR>
     * ”休日”と判定し、falseを返却し処理を終了する。<BR>
     * 　@以外の場合、受付日時の日付部分でカレンダテーブルを検索し、<BR>
     * 行の営業日区分を取得する。行が取得できなかった場合は、営業日<BR>
     * 区分を”通常営業日”とする。<BR>
     * <BR>
     * 　@但し、外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）、<BR>
     * 外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、営業日区分”非営業日”とする。<BR> 
     * <BR>
     * 【取引時間コンテキスト.受付時間区分 == 外国株式】<BR>
     * 　@[外株海外市場カレンダテーブル検索条件]<BR>
     * 　@証券会社コード = 取引時間コンテキスト.証券会社コード<BR>
     * 　@市場コード = 取引時間コンテキスト.市場コード<BR>
     * 　@日付 = （受付日時の日付部分）<BR>
     * <BR>
     * ５）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@　@[検索キー]<BR>
     *  証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     *  部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     *  市場コード：　@取引時間コンテキストの同名プロパティ<BR>
     *  受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     *  商品コード：　@取引時間コンテキストの銘柄コード<BR>
     *  営業日区分：　@判定した営業日区分<BR>
     *  開始時間　@＜＝ （受付日時の時間部分） ＜＝ 終了時間<BR>
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

        // １）　@発注条件判定
        //発注条件 == ”逆指値”の場合、falseを返却する。
        //　@※引数の発注条件 != nullの場合のみ、当判定を行う。 
        //  ※引数の発注条件 == nullの場合は、当判定は行わず、以降の処理を行う。 
        if ((l_strOrderingCondition != null) && 
            (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderingCondition)))
        {
            log.debug(STR_METHOD_NAME + "：発注条件 == ”逆指値”");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        // ２）　@ThreadLocalSystemAttributesRegistryより、受付日を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        // ３）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // ４）　@受付日の営業日区分判定。
        //営業日区分の取得
        l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

        //受付時間区分
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //市場コード
        l_strMarketCode = l_clendarContext.getMarketCode();
        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();

        //外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug(STR_METHOD_NAME + "：受付日時は土曜日又は日曜日の場合です。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //部店コード
        l_strBranchCode = l_clendarContext.getBranchCode();
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
                    + "." + STR_METHOD_NAME);
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
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere = {
            l_strInstitutionCode,    //証券会社コード
            l_strBranchCode,        //部店コード
            l_strMarketCode,        //市場コード
            l_strTradingTimeType, //受付時間区分
            l_strBizDateType,       //営業日区分
            l_strProductCode       //商品コード
//            l_strAcceptTime,     //受付時間
//            l_strAcceptTime     //受付時間
            };

        List l_lisRecords;
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
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow =
                (TradingTimeRow) l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
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

        //get市場トリガ発行
        String l_strSubmitMarketTrigger = l_tradingTimeRow.getSubmitMarketTrigger();

        //「市場トリガ発行」項目が”SONARへMQトリガを実施する”であればtrue、以外はfalseを返却する。
        if (WEB3SubmitMarketTriggerDef.TRIGGER.equals(l_strSubmitMarketTrigger))
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
     * (setTimestamp)<BR>
     * xTradeが利用する現在日時をセットする。<BR>
     * （サービスインタセプタより使用する）<BR>
     * <BR>
     *  －プロセス開始時の時間をLocalThreadにセットする。<BR>
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
    public static void setTimestamp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setTimestamp()";
        log.entering(STR_METHOD_NAME);

        // Timestampの初期化
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            null);

        // Timestamp設定
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            l_processTime
            );

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
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

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
//        l_lstObjTradingTimeWhere.add(l_strTime);
//        //受付時間
//        l_lstObjTradingTimeWhere.add(l_strTime);

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
            //修正 by shiyan 要件変更（ミニ株）
            //l_offset = new Integer(-1);
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
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>
     *     　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "xblocks.gtl.attributes.system_timestamp")<BR>
     * <BR>
     * 　@ただし、ThreadLocalSystemAttributesRegistryに、<BR>
     * 　@設定キー："web3.attributes.basetimestampfororderbizdate"<BR>
     * 　@（発注日計算用の基準日時）の値がセットされている場合は、<BR>
     * 　@以降の処理で受付日時として、<BR>
     * 　@設定キー："web3.attributes.basetimestampfororderbizdate"の値を使用する。<BR>
     * <BR>
     * ２）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * "web3.tradingcalendarcontext")<BR>
     * <BR>
     * 取得したコンテキストの以下の項目にnullが格納されていた場合は、<BR>
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
     * ３）　@受付日の営業日区分判定。<BR>
     * 　@取得した受付日時の曜日を取得し、土曜日または日曜日の場合は”<BR>
     * 非営業日”と判定し。<BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。行が取得できなかった場合は、営業日<BR>
     * 区分を”通常営業日”とする。<BR>
     * <BR>
     * 但し、外株・外株振替連携の場合（取引時間コンテキスト.受付時間区分 == 外国株式 or 外国株式振替連携）、<BR>
     * 外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、営業日区分”非営業日”とする。<BR>
     * <BR>
     * 【取引時間コンテキスト.受付時間区分 == 外国株式】<BR> 
     * [外株海外市場カレンダテーブル検索条件]<BR>
     * 証券会社コード = 取引時間コンテキスト.証券会社コード<BR>
     * 市場コード = 取引時間コンテキスト.市場コード<BR>
     * 日付 = （受付日時の日付部分）<BR>
     * <BR>
     * 【取引時間コンテキスト.受付時間区分 == 外国株式振替連携】<BR>
     * 　@[外株海外市場カレンダテーブル検索条件]<BR>
     * 　@証券会社コード = 取引時間コンテキスト.証券会社コード<BR>
     * 　@市場コード = 香港（※１）<BR>
     * 　@日付 = （受付日時の日付部分）<BR>
     * 　@（※１）取引時間コンテキスト.市場コードには、香港をセットしない。<BR> 
     * <BR>
     * ４）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキストの同名プロパティ。<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@銘柄コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@営業日区分：　@判定した営業日区分<BR>
     * <BR>
     * 　@取得した行のうち、<BR>
     * 　@開始時間 <= （受付日時の時間部分） <= 終了時間に<BR>
     * 　@該当する行の「発注日計算」項目に応じて以下の値を返却する。<BR>
     *      (*) 該当行が存在しない場合は、データ不整合（システムエラー）<BR>
     * <BR>
     * 　@として例外をスローする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80006<BR>
     * <BR>
     * （発注日計算 = 当日）：受付日時の日付部分を返却する。<BR>
     * （発注日計算 = 翌営業日）：受付日時の翌営業日を返却する。<BR>
     * （発注日計算 = ２営業日後）：受付日時の２営業日後を返却する<BR>
     * ※外株・外株振替連携の場合（取引時間コンテキスト.受付時間区分 == 外国株式 or 外国株式振替連携）、<BR>
     * 営業日計算には営業日計算.calc外株営業日()を使用する。<BR>
     * 但し、外株振替連携の場合は、営業日計算.calc外株営業日()の<BR>
     * 引数.市場コードには、香港をセットする。 <BR>
     * （取引時間コンテキスト.市場コードには、香港をセットしない。）<BR> 
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     * @@roseuid 4014CEEA0375
     */
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        //  ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // ただし、ThreadLocalSystemAttributesRegistryに、
        // 設定キー："web3.attributes.basetimestampfororderbizdate"
        // （発注日計算用の基準日時）の値がセットされている場合は、
        Object l_objOrderBizDate = ThreadLocalSystemAttributesRegistry.getAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
        if (l_objOrderBizDate != null)
        {
            // 以降の処理で受付日時として、
            // 設定キー："web3.attributes.basetimestampfororderbizdate"の値を使用する。
            l_tsOrderAcceptDate = (Timestamp) l_objOrderBizDate;
        }

        //２）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        if(l_clendarContext == null)
        {
            log.info("ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得ない");
            return null;
        }
        
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
        //受付日時の時間部分
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strTime = l_format.format(l_tsOrderAcceptDate);

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、データ不整合として
        //例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        // 　@取引時間コンテキスト.市場コード
        // 　@取引時間コンテキスト.受付時間区分
        // 　@取引時間コンテキスト.銘柄コード
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null)
            || (l_strMarketCode == null)
            || (l_strTradingTimeType == null)
            || (l_strProductCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }

        //３）　@受付日の営業日区分判定
        //営業日区分の取得
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        
        //外株・外株振替連携の場合（取引時間コンテキスト.受付時間区分 == 外国株式 or 外国株式振替連携）
        //*********** モデル No.145  by shiyan ***********
        String l_strFeqMarketCode = l_strMarketCode;
        if(WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            l_strFeqMarketCode = WEB3MarketCodeDef.HONGKONG;
        }
        
        if(WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType) || 
            WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        
        //取引時間テーブルを検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
//        l_sbWhere.append(" and start_time <= ? ");
//        l_sbWhere.append(" and end_time >= ? ");

        Object[] l_objTradingTimeWhere =
        {
            l_strInstitutionCode.trim(), //証券会社コード
            l_strBranchCode.trim(),      //部店コード
            l_strMarketCode.trim(),      //市場コード
            l_strTradingTimeType.trim(), //受付時間区分
            l_strProductCode,            //銘柄コード
            l_strBizDateType            //営業日区分
//            l_strTime,                   //受付時間
//            l_strTime                    //受付時間
        };

        List l_lisRecords;
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
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            //該当行が存在しない場合は、データ不整合（システムエラー）として例外をスローする。 
            //************* モデル No.145 by shiyan ****************
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "取引時間テーブル検索： 件数 = 0");
        }
        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strTime) &&
                Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strTime) )
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

        // （発注日計算 = 当日）：受付日時の日付部分を返却する。
        // （発注日計算 = 翌営業日）：受付日時の翌営業日を返却する。
        // （発注日計算 = ２営業日後）：受付日時の２営業日後を返却する。
        Date l_bizDate;
        WEB3GentradeBizDate l_dateCalc =
            new WEB3GentradeBizDate(l_tsOrderAcceptDate);
        String l_strBizdateCalcParameter = l_tradingTimeRow.getBizdateCalcParameter();
        //※外株・外株振替連携の場合（取引時間コンテキスト.受付時間区分 == 外国株式 or 外国株式振替連携）
        //************* モデル No.145 by shiyan ****************
        if(WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType) || 
            WEB3TradingTimeTypeDef.FEQ_CON.equals(l_strTradingTimeType))
        {
            if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    0);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    1);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.calcFeqBizDate(
                    l_strInstitutionCode,
                    l_strFeqMarketCode,
                    l_tsOrderAcceptDate,
                    2);
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    "発注日計算 = " + l_strBizdateCalcParameter);
            }
        }
        else
        {
            if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_tsOrderAcceptDate;
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.roll(1);
            }
            else if (WEB3BizDateCalcParameterDef.NEXT_TWO_BIZ_DATE.equals(l_strBizdateCalcParameter))
            {
                l_bizDate = l_dateCalc.roll(2);
            }
            else
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    "発注日計算 = " + l_strBizdateCalcParameter);
            }
        }
        
        //時間部分をクリア
        log.exiting(STR_METHOD_NAME);
        return WEB3DateUtility.toDay(l_bizDate);
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
     * @@param l_confirmBizDate - 確認時発注日
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 406937AB0203
     */
    public static Date getOrderBizDate(Date l_confirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if (l_bizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_confirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_bizDate,l_confirmBizDate) != 0)
        {
            log.debug(STR_METHOD_NAME + " ： 取得した発注日 != 引数の確認時発注日");
            //取得した発注日と引数の確認時発注日が違う日付であれば例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "発注日 = " + l_bizDate + " , 確認時発注日 = " + l_confirmBizDate);
        }

        log.exiting(STR_METHOD_NAME);
        return l_confirmBizDate;

    }

    /**
     * get発注日<BR>
     * 発注日を取得する。 <BR>
     * <BR>
     * <BR>
     * １）　@発注日を取得する。 <BR>
     * <BR>
     * 　@　@this.get発注日(引数.発注日)にて発注日を取得する。 <BR>
     * <BR>
     * ２）　@立会区分を取得する。 <BR>
     * <BR>
     * 　@　@this.get立会区分()にて立会区分を取得する。 <BR>
     * <BR>
     * ３）　@発注日を返却する。 <BR>
     * <BR>
     * 　@　@２）で取得した立会区分 == 引数.立会区分であれば１）で取得した発注日を返却する。 <BR>
     * 　@　@上記以外は、例外(*1)をthrowする。 <BR>
     * <BR>
     * <BR>
     * (*1) throwするメッセージ：  <BR>
     * 「立会時間が変わりました。お手数ですが、もう一度入力し直してください。」<BR>
     * 　@class:WEB3BusinessLayerException<BR>
     * 　@tag:　@BUSINESS_ERROR_02842<BR>
     * @@param l_datConfirmBizDate - 確認時発注日
     * @@param l_strSessionType - 立会区分
     * @@return Date
     * @@throws WEB3BaseException
     */
    public static Date getOrderBizDate(Date l_datConfirmBizDate, String l_strSessionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date, String)";
        log.entering(STR_METHOD_NAME);

        //発注日を取得する。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_datConfirmBizDate);
        //立会区分を取得する。
        String l_strSessionTypeTemp = WEB3GentradeTradingTimeManagement.getSessionType();
        //発注日を返却する。
        if (!WEB3Toolkit.isEquals(l_strSessionTypeTemp, l_strSessionType))
        {
            log.debug("立会時間が変わりました。お手数ですが、もう一度入力し直してください。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02842,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "立会時間が変わりました。お手数ですが、もう一度入力し直してください。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datBizDate;
    }

    /**
     * is市場開局時間帯<BR>
     * 市場閉局時間帯かどうかを返却する。<BR>
     * 市場開局時間帯の場合true、市場閉局時間帯の場合falseを返却する。
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>
     *  　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     *     "xblocks.gtl.attributes.system_timestamp")<BR>
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
     * ３）　@受付日の営業日区分判定。<BR>
     * 　@this.get営業日区分()をコールして取得する。<BR>
     * <BR>
     * 　@但し、外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）、<BR>
     * 外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、営業日区分”非営業日”とする。<BR>
     * <BR>
     * 【取引時間コンテキスト.受付時間区分 == 外国株式】<BR>
     * 　@[外株海外市場カレンダテーブル検索条件]<BR>
     * 　@証券会社コード = 取引時間コンテキスト.証券会社コード<BR>
     * 　@市場コード = 取引時間コンテキスト.市場コード<BR>
     * 　@日付 = （受付日時の日付部分）<BR>
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
     * 　@市場トリガ発行：<BR>
     *      取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、<BR>
     *      ”SONARへMQトリガを実施する”<BR>
     *      取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、<BR>
     *      検索キーに指定しない。<BR>
     * <BR>
     * 　@該当行が存在しない場合は、true（開局中）を返却する。<BR>
     * <BR>
     * 　@取得した行より、以下の通り戻り値を判定する。<BR>
     * <BR>
     *  （市場開局時間帯(*1)　@≦　@（受付日時の時間部分）≦　@<BR>
     *  市場閉局時間帯(*2)）であればtrue、以外はfalseを返却する。<BR>
     *
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

        boolean l_boReturn;

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)
                (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

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
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                   + "." + STR_METHOD_NAME);
        }

        // ３）　@受付日の営業日区分判定。
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        
        //外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType =
                getFeqBizDateType(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_tsOrderAcceptDate);
            if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        if (l_strBizDateType.equals(WEB3BizDateTypeDef.NO_BIZ_DATE))
        {
            log.debug("受付日時( " + l_tsOrderAcceptDate + ") ： 非営業日の場合。");
            return false;
        }

        // ４） 取引時間取得
        //  以下の検索キーで取引時間テーブルを検索する。
        //[検索キー]
        //* 　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //* 　@部店コード：　@取引時間コンテキストの同名プロパティ
        //* 　@市場コード：　@取引時間コンテキストの同名プロパティ
        //* 　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //* 　@銘柄コード：　@取引時間コンテキストの同名プロパティ
        //* 　@営業日区分：　@判定した営業日区分
        //* 　@発注日計算：　@”当日”
        //* 　@市場トリガ発行：
        //*      取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、
        //*      ”SONARへMQトリガを実施する”
        //*      取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、
        //*      検索キーに指定しない。
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTradingTimeType) == false)
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        List l_lstTradingTimeWhere = new ArrayList();
        l_lstTradingTimeWhere.add(l_strInstitutionCode);
        l_lstTradingTimeWhere.add(l_strBranchCode);
        l_lstTradingTimeWhere.add(l_strMarketCode);
        l_lstTradingTimeWhere.add(l_strTradingTimeType);
        l_lstTradingTimeWhere.add(l_strProductCode);
        l_lstTradingTimeWhere.add(l_strBizDateType);
        l_lstTradingTimeWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTradingTimeType) == false)
        {
            l_lstTradingTimeWhere.add(WEB3EnforcementDef.ENFORCEMENT);
        }
        Object[] l_objTradingTimeWhere = 
            new Object[l_lstTradingTimeWhere.size()];
        l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

        List l_lstStartRecords;
        List l_lstEndRecords;
        String l_strMarketStartTime;
        String l_strMarketEndTime;
        // 開局時刻の最小値を求める
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time",
                null,
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
        // 該当行が存在しない場合は、true（開局中）を返却する。
        int l_intSize = l_lstStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("該当行が存在しない場合は、true（開局中）を返却する。");
            return true;
        }

        l_strMarketStartTime = ((TradingTimeRow)l_lstStartRecords.get(0)).getStartTime();

        // 閉局時刻の最大値を求める
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstEndRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "end_time desc",
                null,
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
        // 該当行が存在しない場合は、true（開局中）を返却する。
        int l_intEndSize = l_lstEndRecords.size();
        if (l_intEndSize == 0)
        {
            log.debug("該当行が存在しない場合は、true（開局中）を返却する。");
            return true;
        }

        l_strMarketEndTime = ((TradingTimeRow)l_lstEndRecords.get(0)).getEndTime();

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
            l_boReturn = true;
            log.debug("*** 取引時間管理***   市場開局時間帯");
        }
        else
        {
            l_boReturn = false;
            log.debug("*** 取引時間管理***   市場閉局時間帯");
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * get市場閉局時間<BR>
     * 市場閉局時間を取得する。<BR>
     * <BR>
     * １）ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>　@
     *     ThreadLocalSystemAttributesRegistry.getAttribute(  <BR>
     *      "xblocks.gtl.attributes.systemtimestamp")<BR>
     *  <BR>
     * ２）　@受付日の営業日区分判定。  <BR>
     *  －取得した受付日時の曜日を取得し、土曜日または日曜日の場合は  <BR>
     *     営業日区分を”非営業日”とする。 <BR>
     *  －以外の場合、カレンダテーブルを受付日時の日付部分で検索し、 <BR>
     *     行の営業日区分を取得する。行が取得できなかった場合は、 <BR>
     *     営業日区分を”通常営業日”とする。 <BR>
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
     * <BR>
     * <BR>
     * ４）　@取引時間取得<BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。<BR>
     *   該当行が存在しない場合、　@23時59分59秒を返却する。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@市場コード：　@引数の市場コード<BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
     * 　@商品コード：　@引数の商品コード<BR>
     * 　@営業日区分：　@判定した営業日区分<BR>
     * 　@発注日計算：　@”当日”<BR>
     * 　@市場トリガ発行： <BR>
     *     取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、<BR>
     *     ”SONARへMQトリガを実施する”<BR>
     *    取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、<BR>
     *    検索キーに指定しない。<BR>
     * <BR>
     * 　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する。
     * <BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * （株の場合）<BR>
     * 1:東京 2:大阪 3:名古屋 4:京都 5:広島<BR>
     * 6:福岡 8:札幌 9:NNM 10: JASDAQ<BR>
     * <BR>
     * （株でない場合）<BR>
     * 0：DEFAULT（指定なし）<BR>
     *
     * @@param l_strProductCode - 商品コード<BR>
     * 投信の場合、銘柄コード<BR>
     * 先物／オプションの場合、原資産銘柄コード<BR>
     * 以外、0：DEFAULT<BR>
     *
     * @@return java.lang.String
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40641B76030C
     */
    public static String getTradeCloseTime(
        String l_strMarketCode,
        String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTime(String, String)";
        log.entering(STR_METHOD_NAME);

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        Calendar l_CalendarBizDate = Calendar.getInstance();
        l_CalendarBizDate.setTime(l_tsOrderAcceptDate);

        // ２） 受付日の営業日区分判定。
        String l_strBizDateType = null;
        l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        // ３） ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // ４） 取引時間取得
        //  以下の検索キーで取引時間テーブルを検索する。
        // 取引時間テーブル(trading_time)検索
        List l_lisStartRecords;

        // 証券会社コード：　@取引時間コンテキストの同名プロパティ<BR>
        // 部店コード：　@取引時間コンテキストの同名プロパティ<BR>
        // 市場コード：　@引数の市場コード<BR>
        // 受付時間区分：　@取引時間コンテキストの同名プロパティ<BR>
        // 商品コード：　@引数の商品コード<BR>
        // 営業日区分：　@判定した営業日区分<BR>
        // 発注日計算：　@”当日”<BR>
        // 市場トリガ発行：　@
        //    取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、<BR>
        //    ”SONARへMQトリガを実施する”<BR>
        //    取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、<BR>
        //    検索キーに指定しない。<BR>
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()) == false)
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        List l_lstTradingTimeWhere = new ArrayList();
        l_lstTradingTimeWhere.add(l_clendarContext.getInstitutionCode());
        l_lstTradingTimeWhere.add(l_clendarContext.getBranchCode());
        l_lstTradingTimeWhere.add(l_strMarketCode);
        l_lstTradingTimeWhere.add(l_clendarContext.getTradingTimeType());
        l_lstTradingTimeWhere.add(l_strProductCode);
        l_lstTradingTimeWhere.add(l_strBizDateType);
        l_lstTradingTimeWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if(WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()) == false)
        {
            l_lstTradingTimeWhere.add(WEB3EnforcementDef.ENFORCEMENT);
        }
        Object[] l_objTradingTimeWhere = 
            new Object[l_lstTradingTimeWhere.size()];
        l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisStartRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
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

        // 該当行が存在しない場合、　@23時59分59秒を返却する
        int l_intSize = l_lisStartRecords.size();
        if (l_intSize == 0)
        {
            log.debug("****** 市場閉局時間：取引時間テーブルを検索して、" +
                "該当行が存在しない場合、23時59分59秒を返却する");
            return WEB3GentradeTimeDef.MAX_RETURN;
        }

        //上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisStartRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.debug("****** 市場閉局時間：[" + l_strEndTime + "]");
        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * (get市場閉局時間<立会時間帯>)<BR>
     * 立会時間帯での市場閉局時間を取得する。 <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG) <BR>
     * <BR>
     * ２）　@受付日の営業日区分判定。 <BR>
     * 　@－取得した受付日時の曜日を取得し、土曜日または日曜日の場合は営業日区分を  <BR>
     * 　@　@　@”非営業日”とする。  <BR>
     * 　@－以外の場合、カレンダテーブルを受付日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。  <BR>
     * 　@　@　@行が取得できなかった場合は、営業日区分を”通常営業日”とする。  <BR>
     * <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * ４）　@取引カレンダコンテキストから市場コード、商品コードを取得する。 <BR>
     * 　@※処理終了時に取引カレンダコンテキストへ再設定を行う。 <BR>
     * <BR>
     * ５）　@取引カレンダコンテキストへ市場コード、商品コード(*1)の設定を行う。 <BR>
     * <BR>
     * 　@(*1) 引数.市場コード、引数.商品コード <BR>
     * <BR>
     * ６）　@取引時間取得 <BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。 <BR>
     * <BR>
     * 　@[検索キー] <BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@市場コード：　@引数の市場コード <BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@商品コード：　@引数の商品コード <BR>
     * 　@営業日区分：　@判定した営業日区分 <BR>
     * 　@発注日計算：　@”当日” <BR>
     * 　@市場トリガ発行： <BR>
     * 　@　@　@取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、<BR>
     * ”SONARへMQトリガを実施する” <BR>
     * 　@　@　@取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、<BR>
     * 検索キーに指定しない。 <BR>
     * 　@立会区分：　@this.get立会区分() <BR>
     * <BR>
     * 　@上記に一致する行のうち、開始時間が一番遅い行の終了時間を取得する。  <BR>
     * <BR>
     * ７）　@取引カレンダコンテキストへ市場コード、商品コード(*2)の再設定を行う。 <BR>
     * <BR>
     * 　@(*2) ４）で取得した市場コード、商品コード <BR>
     * <BR>
     * ８）　@６）で取得した終了時間を返却する。 <BR>
     * 　@※６）の検索結果から該当行が存在しない場合、23時59分59秒を返却する。<BR>
     * <BR>
     * @@param l_strMarketCode - 市場コード<BR>
     * （株の場合） <BR>
     * 1:東京 2:大阪 3:名古屋 4:京都 5:広島 <BR>
     * 6:福岡 8:札幌 9:NNM 10: JASDAQ <BR>
     * @@param l_strProductCode - 商品コード<BR>
     * 投信の場合、銘柄コード<BR>
     * 先物／オプションの場合、原資産銘柄コード<BR>
     * 以外、0：DEFAULT<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 40641B76030C
     */
    public static String getTradeCloseTimeSessionTimeZone(
        String l_strMarketCode,
        String l_strProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTradeCloseTimeSessionTimeZone(String, String)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // ２） 受付日の営業日区分判定。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptDate);

        //３）　@ThreadLocalSystemAttributesRegistryより、
        //取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);

        //４）　@取引カレンダコンテキストから市場コード、商品コードを取得する。
        String l_strMarketCodeTemp = l_clendarContext.getMarketCode();
        String l_strProductCodeTemp = l_clendarContext.getProductCode();

        //５）　@取引カレンダコンテキストへ市場コード、商品コードの設定を行う。
        l_clendarContext.setMarketCode(l_strMarketCode);
        l_clendarContext.setProductCode(l_strProductCode);

        //６）　@取引時間取得
        //　@以下の検索キーで取引時間テーブルを検索する。
        //　@該当行が存在しない場合、　@23時59分59秒を返却する。
        List l_lisRecords;

        //　@証券会社コード：　@取引時間コンテキストの同名プロパティ
        //　@部店コード：　@取引時間コンテキストの同名プロパティ
        //　@市場コード：　@引数の市場コード
        //　@受付時間区分：　@取引時間コンテキストの同名プロパティ
        //　@商品コード：　@引数の商品コード
        //　@営業日区分：　@判定した営業日区分
        //　@発注日計算：　@”当日”
        //　@市場トリガ発行：
        //　@　@　@取引時間コンテキスト.受付時間区分!=”立会外分売”の場合は、”SONARへMQトリガを実施する”
        //　@　@　@取引時間コンテキスト.受付時間区分==”立会外分売”の場合は、検索キーに指定しない。
        //　@立会区分：　@this.get立会区分()

        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");
        l_sbWhere.append(" and bizdate_calc_parameter = ? ");
        if (!WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()))
        {
            l_sbWhere.append(" and submit_market_trigger = ? ");
        }
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
        if (l_strSessionType != null)
        {
            l_sbWhere.append(" and session_type = ? ");
        }
        else
        {
            l_sbWhere.append(" and session_type is null ");
        }
        List l_lisWhere = new ArrayList();
        l_lisWhere.add(l_clendarContext.getInstitutionCode());
        l_lisWhere.add(l_clendarContext.getBranchCode());
        l_lisWhere.add(l_strMarketCode);
        l_lisWhere.add(l_clendarContext.getTradingTimeType());
        l_lisWhere.add(l_strProductCode);
        l_lisWhere.add(l_strBizDateType);
        l_lisWhere.add(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE);
        if (!WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_clendarContext.getTradingTimeType()))
        {
            l_lisWhere.add(WEB3SubmitMarketTriggerDef.TRIGGER);
        }
        if (l_strSessionType != null)
        {
            l_lisWhere.add(l_strSessionType);
        }
        Object[] l_objWhere =
            new Object[l_lisWhere.size()];
        l_lisWhere.toArray(l_objWhere);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                "start_time desc",
                null,
                l_objWhere);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //７）　@取引カレンダコンテキストへ市場コード、商品コード(*2)の再設定を行う。
        l_clendarContext.setMarketCode(l_strMarketCodeTemp);
        l_clendarContext.setProductCode(l_strProductCodeTemp);

        // 該当行が存在しない場合、　@23時59分59秒を返却する
        if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTimeDef.MAX_RETURN;
        }

        //上記に一致する行のうち、開始時間が一番遅い行の終了時間を返却する
        TradingTimeRow l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(0);
        String l_strEndTime = l_tradingTimeRow.getEndTime();
        log.exiting(STR_METHOD_NAME);
        return l_strEndTime;
    }

    /**
     * get市場閉局警告市場<BR>
     * 取引終了警告文を表示する時間帯にある市場の市場コードを配列で返却する。<BR>
     *
     * シーケンス図<BR>
     * 「（取引時間モデル）get市場閉局警告市場」参照。<BR>
     * @@param l_genBranch - 部店オブジェクト
     * @@param l_productTypeEnum - 銘柄タイプ
     * @@param l_strMargineTradeDiv - 信用取引区分 <BR>
     * <BR>
     * 0： DEFAULT（信用取引以外） <BR>
     * 1：制度信用 <BR>
     * 2：一般信用 <BR>
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4020D80D0331
     */
    public static String[] getTradeCloseMarket(
        WEB3GentradeBranch l_genBranch,
        ProductTypeEnum l_productType,
        String l_strMargineTradeDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseMarket(WEB3GentradeBranch,ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        //1 ) 非営業日の場合は、警告市場を取得しないこととする。
        //ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //受付日の営業日区分判定。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptTime);
        if(l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            return null;
        }

        // ２ ) 引数の銘柄タイプ＝”株式”、
        //かつ 信用取引区分≠”0：DEFAULT”の場合は、
        //this.get市場閉局警告市場（部店市場弁済別）にdelegate、
        //戻り値を返却し、処理を終了する。
        if((l_productType.equals(ProductTypeEnum.EQUITY))
           &&( ! WEB3MarginTradingDivDef.DEFAULT.equals(l_strMargineTradeDiv)))
        {
            return getTradeCloseSuspensionMarketRepayment(l_genBranch,l_strMargineTradeDiv);
        }

        // ３) get市場警告文表示
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            l_productType,
            l_strMargineTradeDiv,
            WEB3FuturesOptionDivDef.DEFAULT);
        if (l_lngMessageSuspension == 0)
        {
            return null;
        }

        // ４) get受付日時の年月日部分
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        // ５) ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // ６) get（部店市場別）取扱条件(部店)
        WEB3GentradeBranchMarketDealtCond[] l_handlingCondBranchMarkets =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(l_genBranch);
        if(l_handlingCondBranchMarkets == null)
        {
            return null;
        }

        // ７) （部店市場別）取扱条件オブジェクト毎のLOOP
        String l_strMarketCode;
        WEB3GentradeBranchMarketDealtCond l_tmpHandlingCondBranchMarket = null;
        int l_intLength = l_handlingCondBranchMarkets.length;
        ArrayList l_lstMarketCodes = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            l_tmpHandlingCondBranchMarket = l_handlingCondBranchMarkets[i];

            l_strMarketCode = l_tmpHandlingCondBranchMarket.getMarketCode();
            //ArrayListの要素中に、その市場コードが含まれていない場合のみ、
            //その市場コードをArrayListにaddする
            if(l_lstMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }

            //is取扱可能
            if (l_tmpHandlingCondBranchMarket.isHandlingPossible(l_productType))
            {

                //get市場閉局時間(HHmmss)
                String l_strTradeCloseTime = getTradeCloseTime(
                    l_strMarketCode,
                    l_clendarContext.getProductCode());
                //get （市場閉局時間の[%d]分前）
                Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                    l_strOrderAcceptYMD + l_strTradeCloseTime,
                    "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                    l_datTradeCloseTime,
                    -l_lngMessageSuspension);

                //（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間
                //の場合、市場コードをArrayListに追加する。
                //※ [%d]の分数は、部店.get市場閉局警告文表示()によって取得した数値。
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                   && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstMarketCodes.add(l_tmpHandlingCondBranchMarket.getMarketCode());
                }

            }
        }

        l_intLength = l_lstMarketCodes.size();
        String[] l_strMarkets = new String[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_strMarkets[i] = (String)l_lstMarketCodes.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMarkets;
    }



    /**
     * get市場閉局警告指数<BR>
     * 取引終了警告文を表示する時間帯にある指数の原資産銘柄コードを<BR>
     * 配列で返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（取引時間モデル）get市場閉局警告指数」参照。<BR>
     *
     * @@param l_branch - 部店オブジェクト
     * @@param l_strFutureOptionDiv - (先物／オプション区分) <BR>
     * 　@0：DEFAULT（先物オプション以外） <BR>
     * 　@1：先物 <BR>
     * 　@2：オプション <BR>
     *
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406423ED033B
     */
    public static String[] getTradeCloseSuspension(
        WEB3GentradeBranch l_branch,
        String l_strFutureOptionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseSuspension(WEB3GentradeBranch , String )";
        log.entering(STR_METHOD_NAME);
        
        //ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        
        //休日判定
        //　@営業日区分を取得し、
        //”非営業日”の場合は警告市場を取得せずに処理を終了する
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptTime);
        if(l_strBizDateType.compareTo(WEB3BizDateTypeDef.NO_BIZ_DATE) == 0)
        {
            return null;
        }
        
        //get市場警告文表示
        long l_lngMessageSuspension = l_branch.getMarketMessageSuspension(
            ProductTypeEnum.IFO,
            WEB3MarginTradingDivDef.DEFAULT,
            l_strFutureOptionType);
        //部店.get市場閉局警告文表示()の戻り値が0の場合は、
        //取引終了警告文言を表示しないと判定し、nullを返却し処理を終了する。
        if (l_lngMessageSuspension == 0)
        {
            return null;
        }

        //1.4 get（部店指数別）取扱条件(String, String, String)
        //	(部店指数別）取扱条件::get（部店指数別）取扱条件)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexes =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                l_branch.getInstitution().getInstitutionCode(),
                l_branch.getBranchCode(),
                l_strFutureOptionType);

        Date l_datOrderAcceptTime = new Date(l_tsOrderAcceptTime.getTime());

        //get受付日時の時間部分
        String l_strOrderAcceptDate =
            WEB3DateUtility.formatDate(l_datOrderAcceptTime, "yyyyMMdd");

        List l_lstBranchIndexs = new ArrayList();

        int l_intLength = l_branchIndexes.length;
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3GentradeBranchIndexDealtCond l_branchIndex = l_branchIndexes[i];

            if (!l_branchIndex.isHandlingPossible())
            {
                continue;
            }

            //ArrayListの要素中に、その原資産銘柄コードが含まれていない場合のみ、
            //その市場コードをArrayListにaddする
            //(Bugzilla Bug 1124  「入出金JPU00550」  修正)
            if(l_lstBranchIndexs.contains(l_branchIndex.getUnderlyingProductCode()))
            {
                continue;
            }

            //get市場閉局時間<立会時間帯>
            String l_strTradeCloseTime = getTradeCloseTimeSessionTimeZone(
                WEB3MarketCodeDef.DEFAULT,
                l_branchIndex.getUnderlyingProductCode());

            //（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間
            //の場合、原資産銘柄コード をArrayListに追加する。
            //※ [%d]の分数は、部店.get市場閉局警告文表示()によって取得した数値。

            Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                l_strOrderAcceptDate + l_strTradeCloseTime,
                "yyyyMMddHHmmss");

            Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                l_datTradeCloseTime,
                -l_lngMessageSuspension);

            //（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間
            if (WEB3DateUtility.compareToSecond(l_datOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                && WEB3DateUtility.compareToSecond(l_datOrderAcceptTime,l_datTradeCloseTime) <= 0)
            {
                l_lstBranchIndexs.add(l_branchIndex.getUnderlyingProductCode());
            }
        }
        
        int l_intSize = l_lstBranchIndexs.size();
        String[] l_strBranchIndexes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strBranchIndexes[i] = (String)l_lstBranchIndexs.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBranchIndexes;
    }

    /**
     * get市場閉局警告市場（部店市場弁済別）<BR>
     * 取引終了警告文を表示する時間帯にある市場の市場コードを配列で返却する。<BR>
     * <BR>
     * ※取扱可能市場／不可市場を「（部店市場弁済別）取扱条件テーブル」<BR>
     * ※より判定する、信用取引にて使用。<BR>
     * ※当メソッドはprotectedメソッドとし、<BR>
     * ※信用取引のサービスからはget市場閉局警告市場( )メソッド<BR>
     * ※のほうをコールするものとする。<BR>
     * シーケンス図<BR>
     * 「（取引時間モデル）get市場閉局警告市場（部店市場弁済別）」参照。<BR>
     *  <BR>
     *  １） 引数で指定された部店が、市場警告文言表示対象かどうかを判定する。<BR>
     *   部店.get市場警告文表示(ProductTypeEnum.”株式”（EQUITY） により判定。<BR>
     *   戻り値＝0（市場警告文を表示しない）の場合は、nullを返却し処理を終了する。<BR>
     *  <BR>
     *  ２）　@引数で指定された部店の、取引可能市場の市場コード一覧を <BR>
     *   取得しArrayListに設定する。
     *  <BR>
     *    ２－１）　@（部店市場弁済別）取扱条件.get（部店市場弁済別）取扱 <BR>
     *     条件(部店)により、引数で指定された部店の「（部店市場弁済別）取扱 <BR>
     *     条件」オブジェクトを全て取得する。
     *  <BR>
     *    ２－２）　@ThreadLocalSystemAttributesRegistry.getAttribute( )により、<BR>
     *     受付日時を取得する。<BR>
     *  <BR>
     *    ２－３）　@引数で指定された部店の取引可能市場の市場コード一覧を <BR>
     *     ArrayListに設定する。  ２－１）で取得した「（部店市場弁済別）取扱条件」 <BR>
     *     オブジェクト数分、以下の（１）～（４）を行う。<BR>
     *      　@（１）パラメータ.信用取引区分!="制度／一般信用(両方)"の場合、<BR>
     *             パラメータ.信用取引区分!=「（部店市場弁済別）取扱条件」 <BR>
     *             オブジェクト.弁済区分である「（部店市場弁済別）取扱条件」 <BR>
     *             オブジェクトはスキップする。<BR>
     *        （２）is取扱可能( )==falseの場合、その「（部店市場弁済別）取扱条件」 <BR>
     *              オブジェクトはスキップする。<BR>
     *        （３）is取扱可能( )==trueの場合、その「（部店市場弁済別）取扱条件」 <BR>
     *              オブジェクトの市場コードを取得する。 <BR>
     *        （４）ArrayListの要素をチェックし、（２）で取得した市場コードが含まれていない <BR>
     *             場合はaddする。<BR>
     *  <BR>
     *     ２－４）　@２－３）で作成したArrayListより、市場警告文言表示対象の <BR>
     *      市場コード一覧を作成する。 ２－３）で作成したArrayListの要素数分、<BR>
     *      以下の（１）～（２）を行う。 <BR>
     *         （１）get市場閉局時間(ArrayListの要素（市場コード）, "0"（DEFAULT）) <BR>
     *              により、市場閉局時間を取得する。 <BR>
     *         （２）（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間<BR>
     *              の場合、その市場コードをArrayListに残しておく。<BR>
     *              上記以外の場合は、その市場コードをArrayListからremoveする。 <BR>
     *              ※ [%d]の分数は、部店.get市場閉局警告文表示()によって取得した数値。<BR>
     *  <BR>
     *     ２－５）　@２－４）の結果のArrayListを、配列に変換して返却する。 <BR>
     *  <BR>
     * @@param l_genBranch - 部店オブジェクト<BR>
     * @@param l_strMargineTradeDiv - 信用取引区分 <BR>
     *      0： DEFAULT（信用取引以外） <BR>
     *      1：制度信用 <BR>
     *      2：一般信用 <BR>
     *      3：制度／一般信用(両方)<BR>
     * <BR>
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406423ED033B
     */
    protected static String[] getTradeCloseSuspensionMarketRepayment(
        WEB3GentradeBranch l_genBranch,
        String l_strMargineTradeDiv)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseSuspensionMarketRepayment(WEB3GentradeBranch , String )";
        log.entering(STR_METHOD_NAME);

        // 1) get市場警告文表示
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.EQUITY,
            l_strMargineTradeDiv,
            WEB3FuturesOptionDivDef.DEFAULT);
        //部店.get市場閉局警告文表示()の戻り値が0の場合は、
        //取引終了警告文言を表示しないと判定し、nullを返却し処理を終了する
        if(l_lngMessageSuspension == 0)
        {
            return null;
        }

        // 2) get （部店市場弁済別）取扱条件オブジェクト
        WEB3GentradeBranchMarketRepayDealtCond[] l_branchMarketRepayDealtConds =
            WEB3GentradeBranchMarketRepayDealtCond.getBranchMarketRepayDealtCond(l_genBranch);
        if(l_branchMarketRepayDealtConds == null)
        {
            return null;
        }

        // 3) 受付日時を取得する
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //get受付日時の年月日部分
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        //ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        // 4) 取得した（部店市場弁済別）取扱条件オブジェクト毎のLOOP処理
        WEB3GentradeBranchMarketRepayDealtCond l_tmpBranchMarketRepayDealtCond = null;
        String l_strMarketCode;
        ArrayList l_lstMarketCodes = new ArrayList();
        int l_intSize = l_branchMarketRepayDealtConds.length;
        for(int i = 0; i < l_intSize; i++)
        {
            l_tmpBranchMarketRepayDealtCond = l_branchMarketRepayDealtConds[i];

            l_strMarketCode = l_tmpBranchMarketRepayDealtCond.getMarketCode();
            //ArrayListの要素中に、その市場コードが含まれていない場合のみ、
            //その市場コードをArrayListにaddする
            if(l_lstMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }

            //パラメータ.信用取引区分!="制度／一般信用(両方)"の場合、
            //パラメータ.信用取引区分!=「（部店市場弁済別）取扱条件」
            //オブジェクト.弁済区分である「（部店市場弁済別）取扱条件」
            //オブジェクトはスキップする
            if( ! l_strMargineTradeDiv.equals(WEB3MarginTradingDivDef.MARKET_MARGIN_OR_INSTITUTION_MARGIN))
            {
                BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow =
                    (BranchMarketRepayDealtCondRow)l_tmpBranchMarketRepayDealtCond.getDataSourceObject();
                if( ! l_strMargineTradeDiv.equals(l_branchMarketRepayDealtCondRow.getRepaymentDiv()))
                {
                    continue;
                }
            }

            //is取扱可能
            if (l_tmpBranchMarketRepayDealtCond.isHandlingPossible())
            {

                //get市場閉局時間(HHmmss)
                String l_strTradeCloseTime =
                    getTradeCloseTime(
                        l_strMarketCode,
                        l_clendarContext.getProductCode());
                //get （市場閉局時間の[%d]分前）
                Date l_datTradeCloseTime =
                    WEB3DateUtility.getDate(
                        l_strOrderAcceptYMD + l_strTradeCloseTime,
                        "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime =
                    WEB3DateUtility.addMinute(
                        l_datTradeCloseTime,
                        -l_lngMessageSuspension);

                //（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間
                //の場合、その市場コードをArrayListに残しておく。
                //上記以外の場合は、その市場コードをArrayListからremoveする。
                //※ [%d]の分数は、部店.get市場閉局警告文表示()によって取得した数値。
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                    && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstMarketCodes.add(l_strMarketCode);
                }

            }
        }

        l_intSize = l_lstMarketCodes.size();
        String[] l_strMarketCodes = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strMarketCodes[i] = (String)l_lstMarketCodes.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strMarketCodes;
    }


    /**
     * validate閉局後訂正取消受付可能<BR>
     * 注文の訂正／取消の受付が可能かどうか判定する。<BR>
     * （*市場閉局後～証券会社毎に通知を受けて行う出来終了通知終了までの間は、<BR>
     * 　@注文訂正／取消受付を不可とする。）<BR>
     * <BR>
     * オーバーロードメソッドに委譲（deligate）する。<BR>
     * <BR>
     * [validate閉局後訂正取消受付可能()に指定する引数]<BR>
     * 銘柄タイプ：　@引数.銘柄タイプ<BR>
     * 先物／オプション区分：　@”DEFAULT”<BR>
     * @@param l_productType - 銘柄タイプ。
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 406C03130282
     */
    public static void validateTradeCloseChangeOrCancel(ProductTypeEnum l_productType)
        throws WEB3BaseException
    {
        validateTradeCloseChangeOrCancel(l_productType, WEB3FuturesOptionDivDef.DEFAULT);
    }

    /**
     * validate閉局後訂正取消受付可能<BR>
     * 注文の訂正／取消の受付が可能かどうか判定する。<BR>
     * （*市場閉局後～証券会社毎に通知を受けて行う出来終了通知終了までの間は、<BR>
     * 　@注文訂正／取消受付を不可とする。）<BR>
     * <BR>
     * １）　@this.is市場開局時間帯( )==trueの場合は、<BR>
     * 　@　@　@訂正取消可能と判定し、何もせずにreturnする。<BR>
     * <BR>
     * ２）　@取引時間管理.is市場開局時間帯( )==falseの場合は、<BR>
     * 　@　@　@【出来終了テーブル】を引数、及び取引カレンダコンテキストの内容<BR>
     *       で読み、該当データなしの場合は例外をthrowする。<BR>
     * -該当データなし-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00812<BR>
     * <BR>
     * 　@　@　@＜検索条件＞<BR>
     * 　@　@　@証券会社コード＝取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@　@　@銘柄タイプ＝　@引数.銘柄タイプ<BR>
     * 　@　@　@先物／オプション区分＝　@引数.先物／オプション区分<BR>
     * 　@　@　@出来終了区分＝"DEFAULT" <BR>
     * <BR>
     *  (*1)　@取引時間コンテキストの取得<BR>
     *
     * －ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。<BR>
     * <BR>　@
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * "web3.tradingcalendarcontext")<BR>
     * @@param l_productType - 銘柄タイプ。
     * @@param l_strFuturesType - 先物／オプション区分<BR>
     * 0：DEFAULT<BR>
     * 1：先物<BR>
     * 2：オプション<BR>
     * <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 407CBA67036A
     */
    public static void validateTradeCloseChangeOrCancel(
        ProductTypeEnum l_productType,
        String l_strFuturesType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeCloseChangeOrCancel(ProductTypeEnum,String)";
        log.entering(STR_METHOD_NAME);

        //１）this.is市場開局時間帯( )==trueの場合は、
        //    訂正取消可能と判定し、何もせずにreturnする。
        if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //取引時間管理.is市場開局時間帯( )==falseの場合は、
        // 【出来終了テーブル】を引数、及び取引カレンダコンテキストの内容で読み、
        // 該当データなしの場合は例外をthrowする。
        else
        {
            //ThreadLocalSystemAttributesRegistryより、
            // 取引カレンダコンテキストを取得する。
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            OrderexecutionEndRow l_orderexecutionEndRow = null;
            try
            {
                l_orderexecutionEndRow =
                    OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    l_clendarContext.getInstitutionCode(),
                    l_productType,
                    l_strFuturesType,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
            }
            catch (DataException de)
            {
                log.error(STR_METHOD_NAME, de);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "."
                        + STR_METHOD_NAME,
                    de.getMessage(),
                    de);
            }

            if (l_orderexecutionEndRow == null)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00812,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return;

    }

    /**
     * reset銘柄コード<BR>
     * LocalThread中の取引時間コンテキスト.銘柄コードのリセットを行う。<BR>
     * <BR>
     * ※ 先物、オプション、投資信託は、銘柄ごとに取引時間を設定している。<BR>
     * 　@そのため、照会等、サービス処理中に複数銘柄を扱う場合は<BR>
     * 　@取引銘柄オブジェクト取得前に、<BR>
     * 　@取引時間コンテキストの銘柄コードをリセットする必要がある。<BR>
     * <BR>
     * １）　@取引時間コンテキスト取得<BR>
     * ThreadLocalSystemAttributesRegistryより取引時間コンテキストを取得する。
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * ２）　@銘柄コードセット<BR>
     * 取得した取引時間コンテキストオブジェクトに銘柄コードをセットする。<BR>
     * <BR>
     * 取引カレンダコンテキスト.set銘柄コード(銘柄コード)<BR>
     * @@param l_strProductCode - 銘柄コード<BR>
     * <BR>
     * 投信の場合、銘柄コード<BR>
     * 先物／オプションの場合、原資産銘柄コード<BR>
     * @@roseuid 407F8FCB0231
     */
    public static void resetProductCode(String l_strProductCode)
    {
        //      ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //銘柄コードセット
        l_clendarContext.setProductCode(l_strProductCode);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset市場コード<BR>
     * LocalThread中の取引時間コンテキスト.銘柄コードのリセットを行う。<BR>
     * <BR>
     * ※ 照会等、サービス処理中に複数市場を扱う場合は<BR>
     * 　@取引銘柄オブジェクト取得前に、<BR>
     * 　@取引時間コンテキストの市場コードをリセットする必要がある。<BR>
     * <BR>
     * １）　@取引時間コンテキスト取得<BR>
     * ThreadLocalSystemAttributesRegistryより取引時間コンテキストを取得する。
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * ２）　@市場コードセット<BR>
     * 取得した取引時間コンテキストオブジェクトに銘柄コードをセットする。<BR>
     * <BR>
     * 取引カレンダコンテキスト.set市場コード(市場コード)<BR>
     * @@param l_strMarketCode - 市場コード
     * @@roseuid 407F936F033B
     */
    public static void resetMarketCode(String l_strMarketCode)
    {
        //ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //市場コードセット
        l_clendarContext.setMarketCode(l_strMarketCode);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset受付時間区分<BR>
     * LocalThread中の取引時間コンテキスト.受付時間区分のリセットを行う。<BR>
     * <BR>
     * １）　@取引時間コンテキスト取得<BR>
     * ThreadLocalSystemAttributesRegistryより取引時間コンテキストを取得する。
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * ２）　@受付時間区分セット<BR>
     * 取得した取引時間コンテキストオブジェクトに受付時間区分をセットする。<BR>
     * <BR>
     * 取引カレンダコンテキスト.set受付時間区分(受付時間区分)<BR>
     * @@param l_strTradingTimeType - 受付時間区分
     * @@roseuid 407F9AC602ED
     */
    public static void resetTradingTimeType(String l_strTradingTimeType)
    {
        //ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //受付時間区分セット
        l_clendarContext.setTradingTimeType(l_strTradingTimeType);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * reset注文受付トランザクション<BR>
     * <BR>
     * LocalThread中の取引時間コンテキスト.注文受付トランザクションの<BR>
     * リセットを行う。<BR>
     * １）　@取引時間コンテキスト取得<BR>
     * ThreadLocalSystemAttributesRegistryより取引時間コンテキストを取得する<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * ２）　@注文受付トランザクションセット<BR>
     * 取得した取引時間コンテキストオブジェクトに注文受付トランザクションを<BR>
     * セットする。<BR>
     * 取引カレンダコンテキスト.set注文受付トランザクション(注文受付トランザクション)<BR>
     * <BR>
     * @@param l_strOrderAcceptTransaction - 注文受付トランザクション
     * @@roseuid 409EFFC401A7
     */
    public static void resetOrderAcceptTransaction(String l_strOrderAcceptTransaction)
    {
        //ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //注文受付トランザクションセット
        l_clendarContext.setOrderAcceptTransaction(l_strOrderAcceptTransaction);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }
    
    /**
     * reset注文受付商品<BR>
     *  <BR>
     * LocalThread中の取引時間コンテキスト.注文受付トランザクションの<BR>
     * リセットを行う。<BR>
     * <BR>
     * １）　@取引時間コンテキスト取得<BR>
     * ThreadLocalSystemAttributesRegistryより取引時間コンテキストを取得する<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * TRADING_CAL_CONTEXT_PATH)<BR>
     * <BR>
     * ２）　@注文受付商品セット<BR>
     * 取得した取引時間コンテキストオブジェクトに注文受付商品をセットする。<BR>
     * 取引カレンダコンテキスト.set注文受付商品(注文受付商品)<BR>
     * <BR>
     * @@param l_strOrderAcceptProduct - 注文受付商品
     */
    public static void resetOrderAcceptProduct(String l_strOrderAcceptProduct)
    {
        //ThreadLocalSystemAttributesRegistryより、
        // 取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        //注文受付商品セット
        l_clendarContext.setOrderAcceptProduct(l_strOrderAcceptProduct);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);
    }

    /**
     * (setBusinessTimestamp)<BR>
     *<BR>
     * xTradeが利用する業務日時をセットする。<BR>
     * １）　@業務日付取得 <BR>
     * TradingSystem.getBizDate()にて業務日付を取得する。 <BR>
     * ２）　@業務日時編集 <BR>
     * 以下の通り、業務日時（Timestamp型）を編集する。<BR>　@
     * 業務日付＋マシン日付のHH:MM:SS <BR>
     * ３）　@ThreadLocalSystemAttributesRegistryのTIMESTAMP_TAG<BR>
     * 属性に業務日時をセットする。<BR>
     * ThreadLocalSystemAttributesRegistry.setAttribute( <BR>
     *     TIMESTAMP_TAG, <BR>
     *     現在日時 <BR>
     *     );  <BR>
     */
    public static void setBusinessTimestamp()
    {
        final String STR_METHOD_NAME = "setBusinessTimestamp";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            null);

        //１）　@業務日付取得
        // TradingSystem.getBizDate()にて業務日付を取得する。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Date l_datBizDate = l_tradingSys.getBizDate();

        //２）　@業務日時編集
        //以下の通り、業務日時（Timestamp型）を編集する。
        //業務日付＋マシン日付のHH:MM:SS

        //get 業務日付
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strBizDateYMD = l_format.format(l_datBizDate);
        //get マシン日付のHH:MM:SS
        Date l_machineTime = GtlUtils.getSystemTimestamp();
        l_format = GtlUtils.getThreadSafeSimpleDateFormat("HH:mm:ss");
        String l_strMachineTime = l_format.format(l_machineTime);
        //get 業務日時
        Date l_datBusinessTime = WEB3DateUtility.getDate(
            l_strBizDateYMD + l_strMachineTime,
            "yyyyMMddHH:mm:ss");

        //３）　@ThreadLocalSystemAttributesRegistryのTIMESTAMP_TAG
        //属性に業務日時をセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(
            TIMESTAMP_TAG,
            new Timestamp(l_datBusinessTime.getTime()));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (営業日区分取得)<BR>
     *<BR>
     * 引数の[受付日時]Timestampを元に、曜日を取得して土日だった場合、<BR>
     * 「非営業日」を返す。<BR>
     *<BR>
     * [受付日時]が土日以外の場合、カレンダ・テーブルを検索し、<BR>
     * 検索結果が0行の場合、「営業日」を返す。<BR>
     * 上記以外は、「非営業日」を返す。<BR>
     *<BR>
     * @@param l_tsOrderAcceptDate - 受付日時
     * @@return
     */
    public static String getBizDateType(Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getBizDateType(Timestamp)";
        CalendarRow l_CalendarRow = null;

        String l_strBizDateType;

        Calendar l_calendarBizDate = Calendar.getInstance();
        l_calendarBizDate.setTime(l_tsOrderAcceptDate);

        // DB検索用に時分秒ミリ秒を初期化
        l_calendarBizDate.set(Calendar.HOUR_OF_DAY, 0);
        l_calendarBizDate.set(Calendar.MINUTE, 0);
        l_calendarBizDate.set(Calendar.SECOND, 0);
        l_calendarBizDate.set(Calendar.MILLISECOND, 0);


        try
        {
            l_CalendarRow = CalendarDao.findRowByPk(new Timestamp(l_calendarBizDate.getTimeInMillis()));
        }
        catch (DataFindException l_dfe)
        {
            //no operation
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_CalendarRow != null)
        {
            l_strBizDateType = l_CalendarRow.getBizDateType();
            return l_strBizDateType;
        }

        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
        {
            // 土曜日
            l_strBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
        }
        else if (
            l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            // 日曜日
            l_strBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
        }
        else
        {
            l_strBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
        }

        return l_strBizDateType;

    }

    /**
     * (isミニ株取引終了警告)<BR>
     * <BR>
     * 取引終了警告文を表示する時間帯にある場合trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     *  <BR>
     * １）　@市場閉局警告文表示を取得する。<BR>
     * 部店.get市場警告文表示()にて、警告文表示を取得する。<BR>
     * 戻り値が0の場合は、取引終了文言を表示しないと判定し、<BR>
     * falseを返却する。<BR>
     *  <BR>
     *    [get市場警告文表示()に指定する引数]<BR>
     *    銘柄タイプ：　@ProductTypeEnum.株式<BR>
     *    信用取引区分：　@0：DEFAULT<BR>
     *    先物／オプション区分：　@0：DEFAULT<BR>
     *  <BR>
     * ２）　@発注日取得<BR>
     * get発注日()にて、現在の発注日を取得する。<BR>
     *  <BR>
     * ３）　@現在の受付日時取得<BR>
     * ThreadLocalSystemAttributesRegistryより、受付日時を<BR>
     * 取得する。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("<BR>
     *  xblocks.gtl.attributes.systemtimestamp")<BR>
     *  <BR>
     * ４）　@シフト後の発注日取得<BR>
     * <BR>
     * ４－１）　@受付時間シフトシフト <BR>
     *  ３）で取得した現在の受付日時の[%d]分後を取得し、<BR>
     *  ThreadLocalSystemAttributesRegistryの<BR>
     *  "xblocks.gtl.attributes.systemtimestamp"属性にセットする。<BR>
     *  <BR>
     * ※ [%d]の分数は、部店.get市場閉局警告文表示()によって<BR>
     * 取得した数値。<BR>
     *  <BR>
     * ４－２）　@シフト後の発注日取得<BR>
     * get発注日()にて、シフト後の発注日を取得する。<BR>
     *  <BR>
     * ４－３）　@受付時間を戻す<BR>
     * ThreadLocalSystemAttributesRegistryの<BR>
     * "xblocks.gtl.attributes.systemtimestamp"属性を、３）で<BR>
     * 取得した現在の受付日時に戻す。<BR>
     *  <BR>
     * ５）　@戻り値判定 <BR>
     * 発注日が変わっている場合（２）で取得した現在の発注日 != ４）で<BR>
     * 取得したシフト後の発注日）、trueを返却する。以外、falseを返却する<BR>
     *  <BR>
     * @@param l_genbBranch - 部店オブジェクト
     * @@throws WEB3BaseException
     * @@return boolean
     * @@roseuid 409EFFC401A7
     */
    public static boolean isMiniStockSuspension(WEB3GentradeBranch l_genBranch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isMiniStockSuspension(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        long l_lngMarketMessageSuspension = 0L;
        
        //１）　@市場閉局警告文表示を取得する。
        //部店.get市場警告文表示()にて、警告文表示を取得する。
        //戻り値が0の場合は、取引終了文言を表示しないと判定し、falseを返却する。
        l_lngMarketMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.EQUITY,
            WEB3MarginTradingDivDef.DEFAULT,
            WEB3FuturesOptionDivDef.DEFAULT);
        if(l_lngMarketMessageSuspension == 0L)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@発注日取得
        //get発注日()にて、現在の発注日を取得する。
        Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //３）　@現在の受付日時取得
        //ThreadLocalSystemAttributesRegistryより、受付日時を取得する
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        
        //現在の受付日時の[%d]分後を取得する
        Date l_acceptDateAddMinute = WEB3DateUtility.addMinute(
            l_tsOrderAcceptDate,
            l_lngMarketMessageSuspension);
        Timestamp l_tsAcceptDateAddMinute = new Timestamp(l_acceptDateAddMinute.getTime());
            
        //４－１）　@受付時間シフトシフト 
        //  ３）で取得した現在の受付日時の[%d]分後を取得し、ThreadLocalSystemAttributesRegistry
        //の"xblocks.gtl.attributes.systemtimestamp"属性にセットする。
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,l_tsAcceptDateAddMinute);
        
        // ４－２）　@シフト後の発注日取得
        //get発注日()にて、シフト後の発注日を取得する。
        Date l_shiftedBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //４－３）　@受付時間を戻す
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,l_tsOrderAcceptDate);
        
        //５）　@戻り値判定 
        //発注日が変わっている場合（２）で取得した現在の発注日 != ４）で
        //取得したシフト後の発注日）、trueを返却する。以外、falseを返却する
        if(WEB3DateUtility.compareToDay(l_bizDate,l_shiftedBizDate) != 0)
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
     * (get市場閉局警告外株市場)<BR>
     * 取引終了警告文を表示する時間帯にある外株市場の市場コードを<BR>
     * 配列で返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（取引時間モデル）get市場閉局警告外株市場」参照。<BR>
     * <BR>
     * @@param l_genBranch 部店オブジェクト
     * @@return String[]
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4020D80D0331
     */
    public static String[] getTradeCloseFeqMarket(
        WEB3GentradeBranch l_genBranch)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getTradeCloseFeqMarket(WEB3GentradeBranch)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strFeqMarkets = null;
        String l_strMarketCode = null;
        
        //2:(*1)　@営業日区分を取得し、”非営業日”の場合は
        //警告市場を取得せずに処理を終了する。
        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        //２）　@受付日の営業日区分判定。
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsOrderAcceptTime);
        //３）　@海外の営業日区分判定
        String l_strFeqBizDateType =
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(l_tsOrderAcceptTime);
        //４）　@２）、３）で判定した営業日区分のいずれかが”非営業日”の場合は、
        //nullを返却し処理を終了する。
        if(WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType) || 
            WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
        {
            return null;
        }

        //3:get市場警告文表示(ProductTypeEnum, String, Strin)
        long l_lngMessageSuspension = l_genBranch.getMarketMessageSuspension(
            ProductTypeEnum.FOREIGN_EQUITY,
            WEB3MarginTradingDivDef.DEFAULT,
            WEB3FuturesOptionDivDef.DEFAULT
            );
        
        //4:(*2)　@戻り値判定    
        //部店.get市場閉局警告文表示()の戻り値が0の場合は、
        //取引終了警告文言を表示しないと判定し、nullを返却し処理を終了する。        
        if(l_lngMessageSuspension == 0)
        {
            return null;
        }

        //5:get（部店市場別.外株）取扱条件(部店)
        WEB3GentradeFeqBranchMarketDealtCond[] l_feqHandlingCondBranchMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getFeqHandlingCondBranchMarket(l_genBranch);
        if(l_feqHandlingCondBranchMarkets == null)
        {
            return null;
        }

        //8:(*)get（部店市場別.外株）取扱条件()の戻り値の要素数文Loop処理
        int l_intLength = l_feqHandlingCondBranchMarkets.length;

        // ４) get受付日時の年月日部分
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat(
            "yyyyMMdd");
        String l_strOrderAcceptYMD = l_format.format(l_tsOrderAcceptTime);

        //取引カレンダコンテキストを取得する
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        WEB3GentradeFeqBranchMarketDealtCond l_tmpFeqHandlingCondBranchMarket = null;
        ArrayList l_lstFeqMarketCodes = new ArrayList();

        for (int i = 0; i < l_intLength; i++)
        {
            l_tmpFeqHandlingCondBranchMarket = l_feqHandlingCondBranchMarkets[i];

            //10:get市場コード()
            l_strMarketCode = l_tmpFeqHandlingCondBranchMarket.getMarketCode();
            
            //ArrayListの要素中に、その市場コードが含まれていない場合のみ、
            //その市場コードをArrayListにaddする
            if(l_lstFeqMarketCodes.contains(l_strMarketCode))
            {
                continue;
            }
            
            boolean l_isHandlingPossible = 
                l_feqHandlingCondBranchMarkets[i].isHandlingPossible(ProductTypeEnum.FOREIGN_EQUITY);
            //9:（is取扱可能() == false）の場合、
            //その要素についての処理を行わない。
            if(l_isHandlingPossible)
            {

                //11:get市場閉局時間(String, String)(HHmmss)
                String l_strTradeCloseTime = getTradeCloseTime(
                    l_strMarketCode,
                    l_clendarContext.getProductCode());

                //get （市場閉局時間の[%d]分前）
                Date l_datTradeCloseTime = WEB3DateUtility.getDate(
                    l_strOrderAcceptYMD + l_strTradeCloseTime,
                    "yyyyMMddHHmmss");
                Date l_datMessageSuspensionTime = WEB3DateUtility.addMinute(
                    l_datTradeCloseTime,
                    -l_lngMessageSuspension);
                //12:add(市場コード : Object)
                //（市場閉局時間の[%d]分前） <= （受付日時の時間部分） <= 市場閉局時間の
                //場合、市場コードをArrayListに追加する。
                //※ [%d]の分数は、部店.get市場閉局警告文表示()によって取得した数値。
                if (WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datMessageSuspensionTime) >= 0
                   && WEB3DateUtility.compareToSecond(l_tsOrderAcceptTime,l_datTradeCloseTime) <= 0)
                {
                    l_lstFeqMarketCodes.add(l_strMarketCode);
                }
            }
        }

        int l_intSize = l_lstFeqMarketCodes.size();
        String[] l_feqMarkets = new String[l_intSize];
        
        if(l_intSize > 0)
        {
            l_lstFeqMarketCodes.toArray(l_feqMarkets);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqMarkets;
    }

    /**
     * (海外の営業日区分取得)<BR>
     * －外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、<BR>
     * 営業日区分”非営業日”とする。<BR>
     * <BR>
     * [外株海外市場カレンダテーブル検索条件]<BR>
     * 　@証券会社コード = 引数.証券会社コード<BR>
     * 　@市場コード = 引数.市場コード<BR>
     * 　@日付 = （引数.受付日時の日付部分）<BR>
     *<BR>
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strMarketCode 市場コード
     * @@param l_tsOrderAcceptDate 受付日時
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getFeqBizDateType(
        String l_strInstitutionCode,
        String l_strMarketCode,
        Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqBizDateType(String, String, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //外株海外市場カレンダーRow
        FeqCalendarRow l_feqCalendarRow = null;

        //受付日時の日付部分
        Timestamp l_strOrderAcceptYMD = 
            new Timestamp(WEB3DateUtility.toDay(l_tsOrderAcceptDate).getTime());
        
        String l_strBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
        
        try
        {
            l_feqCalendarRow =
                FeqCalendarDao.findRowByPk(
                    l_strInstitutionCode,
                    l_strMarketCode,
                    l_strOrderAcceptYMD);
            
            l_strBizDateType = l_feqCalendarRow.getBizDateType();
        }
        catch (DataFindException e)
        {
            log.debug("外株海外市場カレンダーテープルに" 
                + "証券会社コード = " + l_strInstitutionCode
                + " 市場コード = " + l_strMarketCode
                + " 受付日時 = " + l_strOrderAcceptYMD
                + " のレコードが無いので、「営業日」とする");
        }
        catch (DataQueryException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
        catch (DataNetworkException e)
        {
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                e.getMessage(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBizDateType;
    }

    /**
     * (海外の営業日区分取得)<BR>
     * －外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、<BR>
     * 営業日区分”非営業日”とする。<BR>
     * <BR>
     * [外株海外市場カレンダテーブル検索条件]<BR>
     * 　@証券会社コード = 取引時間コンテキスト.証券会社コード<BR>
     * 　@市場コード = 取引時間コンテキスト.市場コード<BR>
     * 　@日付 = （受付日時の日付部分）<BR>
     *<BR>
     * @@param l_tsOrderAcceptDate 受付日時
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getFeqBizDateType(Timestamp l_tsOrderAcceptDate)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getFeqBizDateType(Timestamp)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strMarketCode = null;

        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //市場コード
        l_strMarketCode = l_clendarContext.getMarketCode();
        
        String l_strBizType;
            l_strBizType = getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsOrderAcceptDate
                );

        log.exiting(STR_METHOD_NAME);       
        return l_strBizType;
    }
    
    /**
     * is取引所休憩時間帯() <BR>
     * 取引所が休憩時間帯の場合はtrueを、そうでない場合はfalseを返却する。<BR> 
     * 引け後や非営業日の場合／立会外分売の場合は、false（休憩時間帯ではない）を<BR>
     * 返却する。<BR> 
     *<BR>
     * １）　@this.is市場開局時間帯() == false（閉局中／非営業日）の場合は、<BR> 
     *      false（休憩時間帯ではない）を返却する。 <BR>
     *      以外（＝営業日で場中の場合）、以下の処理を行う。<BR> 
     *<BR>
     * ２）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを<BR>
     * 取得する。<BR> 
     *       ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR> 
     *<BR>
     *      ・取得した取引時間コンテキスト.受付時間区分 == "立会外分売"の場合は、<BR> 
     *        false（休憩時間帯ではない）を返却する。 <BR>
     *<BR>
     *      ・this.is夕場時間帯() == trueの場合は、false（休憩時間帯ではない）を返却する。 <BR>
     *<BR>
     *      ・取得したコンテキストの以下の項目にnullが格納されていた場合は、<BR> 
     *        データ不整合として例外をスローする。 <BR>
     *<BR>
     *       取引時間コンテキスト.証券会社コード<BR> 
     *       取引時間コンテキスト.部店コード <BR>
     *       取引時間コンテキスト.市場コード <BR>
     *       取引時間コンテキスト.受付時間区分 <BR>
     *       取引時間コンテキスト.銘柄コード <BR>
     *<BR>
     * ３）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR> 
     *       ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR> 
     *<BR>
     * ４）　@受付日の営業日区分判定。<BR> 
     *       カレンダテーブルを取得した受付日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。 <BR>
     *       行が取得できなかった場合は、営業日区分を”通常営業日”とする。<BR> 
     *<BR>
     * ５）　@取引時間取得<BR> 
     *       以下の検索キーで取引時間テーブルを検索する。<BR> 
     *<BR>
     *     　@[検索キー]<BR> 
     *　@      証券会社コード：　@取引時間コンテキストの同名プロパティ<BR> 
     *      　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     *      　@市場コード：　@取引時間コンテキストの同名プロパティ <BR>
     *      　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     *        銘柄コード：　@取引時間コンテキストの同名プロパティ <BR>
     *      　@営業日区分：　@判定した営業日区分 <BR>
     *　@      開始時間　@<= （受付日時の時間部分（HHMMSS）） <= 終了時間<BR> 
     *<BR>
     *      　@取得した行より、以下の通り戻り値を判定する。 <BR>
     *      　@－上記に一致する行の「市場トリガ発行」項目が <BR>
     *　@    　@　@”SONARへMQトリガを実施しない”であればtrue（休憩時間帯である）、<BR> 
     *　@     　@　@以外はfalse（休憩時間帯ではない）を返却する。 <BR>
     *      　@－上記に一致する行が存在しない場合は、<BR>
     *      　@データ不整合（システムエラー）として例外をスローする。<BR> 
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public static boolean isTradeCloseTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeCloseTimeZone()";
        log.entering(STR_METHOD_NAME);

        boolean l_boReturn;

        // １）　@this.is市場開局時間帯() == false（閉局中／非営業日）の場合は、 
        //        false（休憩時間帯ではない）を返却する。 
        if (isTradeOpenTimeZone() == false)
        {
            l_boReturn = false;
        }
        else
        {
            //２）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。 
            //      ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext") 
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

            //・取得した取引時間コンテキスト.受付時間区分 == "立会外分売"の場合は、 
            //   false（休憩時間帯ではない）を返却する。                 
            String l_strTempTradingTimeType = l_clendarContext.getTradingTimeType();
            if (WEB3TradingTimeTypeDef.SALES_OUTSIDE_MARKET.equals(l_strTempTradingTimeType))
            {
                l_boReturn = false;
            }
            //・this.is夕場時間帯() == trueの場合は、false（休憩時間帯ではない）を返却する。
            else if (WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
            {
                l_boReturn = false;
            }
            else
            {
                //・取得したコンテキストの以下の項目にnullが格納されていた場合は、 
                //　@データ不整合として例外をスローする。 
                //
                //　@取引時間コンテキスト.証券会社コード 
                //　@取引時間コンテキスト.部店コード 
                //　@取引時間コンテキスト.市場コード 
                //　@取引時間コンテキスト.受付時間区分 
                //　@取引時間コンテキスト.銘柄コード 
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
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        WEB3GentradeTradingTimeManagement.class.getName()
                           + "." + STR_METHOD_NAME);
                }

                //３）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 
                //      ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp") 
                Timestamp l_tsOrderAcceptDate =
                    (Timestamp)
                        (ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

                //受付日時の時間部分（HHMMSS）を取得する。 
                String l_strOrderAcceptDateHHMMSS = 
                    WEB3DateUtility.formatDate(l_tsOrderAcceptDate, "HHmmss");

                //４）　@受付日の営業日区分判定。 
                //     カレンダテーブルを取得した受付日時の日付部分で検索し、行の営業日区分を取得する。 
                //     行が取得できなかった場合は、営業日区分を”通常営業日”とする。 
                String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);

                //５）　@取引時間取得 
                //      以下の検索キーで取引時間テーブルを検索する。 
                //
                //     [検索キー] 
                //     証券会社コード：　@取引時間コンテキストの同名プロパティ 
                //     部店コード：　@取引時間コンテキストの同名プロパティ 
                //     市場コード：　@取引時間コンテキストの同名プロパティ 
                //     受付時間区分：　@取引時間コンテキストの同名プロパティ 
                //     銘柄コード：　@取引時間コンテキストの同名プロパティ 
                //     営業日区分：　@判定した営業日区分 
                //     開始時間　@<= （受付日時の時間部分（HHMMSS）） <= 終了時間 
                StringBuffer l_sbWhere = new StringBuffer();
                l_sbWhere.append(" institution_code = ? ");
                l_sbWhere.append(" and branch_code = ? ");
                l_sbWhere.append(" and market_code = ? ");
                l_sbWhere.append(" and trading_time_type = ? ");
                l_sbWhere.append(" and product_code = ? ");
                l_sbWhere.append(" and biz_date_type = ? ");
                l_sbWhere.append(" and start_time <= ? ");
                l_sbWhere.append(" and end_time >= ? ");

                List l_lstTradingTimeWhere = new ArrayList();
                l_lstTradingTimeWhere.add(l_strInstitutionCode);
                l_lstTradingTimeWhere.add(l_strBranchCode);
                l_lstTradingTimeWhere.add(l_strMarketCode);
                l_lstTradingTimeWhere.add(l_strTradingTimeType);
                l_lstTradingTimeWhere.add(l_strProductCode);
                l_lstTradingTimeWhere.add(l_strBizDateType);
                l_lstTradingTimeWhere.add(l_strOrderAcceptDateHHMMSS);
                l_lstTradingTimeWhere.add(l_strOrderAcceptDateHHMMSS);

                Object[] l_objTradingTimeWhere = 
                    new Object[l_lstTradingTimeWhere.size()];
                l_lstTradingTimeWhere.toArray(l_objTradingTimeWhere);

                List l_lstRecords;

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_lstRecords = l_queryProcessor.doFindAllQuery(
                        TradingTimeRow.TYPE,
                        l_sbWhere.toString(),
                        l_objTradingTimeWhere);

                }
                catch (DataException de)
                {
                    log.error(STR_METHOD_NAME, de);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        WEB3GentradeTradingTimeManagement.class.getName() + "." 
                            + STR_METHOD_NAME,
                        de.getMessage(),
                        de);
                }

                // 上記に一致する行が存在しない場合は、データ不整合（システムエラー）として例外をスローする。 
                int l_intSize = l_lstRecords.size();
                if (l_intSize == 0)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME);
                }
        
                //　@－上記に一致する行の「市場トリガ発行」項目が 
                //  ”SONARへMQトリガを実施しない”であればtrue（休憩時間帯である）、 
                //  以外はfalse（休憩時間帯ではない）を返却する。 

                String l_strSubmitMarketTrigger = 
                    ((TradingTimeRow)l_lstRecords.get(0)).getSubmitMarketTrigger();
                if (WEB3SubmitMarketTriggerDef.NOT_TRIGGER.equals(l_strSubmitMarketTrigger))
                {
                    l_boReturn = true;
                    log.debug("*** 取引時間管理***   休憩時間帯である");
                }
                else
                {
                    l_boReturn = false;
                    log.debug("*** 取引時間管理***   休憩時間帯でない");
                }                
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * is取引所休憩時間帯 (Date 判定対象日時) <BR>
     * 引数で指定された判定対象日時において、 <BR>
     * 取引所が休憩時間帯の場合はtrueを、そうでない場合はfalseを返却する。<BR> 
     * 引け後や非営業日の場合／立会外分売の場合は、false（休憩時間帯ではない）を<BR>
     * 返却する。<BR> 
     *<BR>
     *  １）　@TIMESTAMP_TAG の値を取得し、メソッド内のローカル変数に記憶させる。<BR> 
     *        TIMESTAMP_TAGの値の取得： <BR>
     *        ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG)<BR> 
     *<BR>
     *  ２）　@TIMESTAMP_TAG に、引数の「判定対象日時」をセットする。<BR> 
     *        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG,<BR> 
     * 引数の「判定対象日時」)<BR> 
     *<BR>
     *  ３）　@this.is取引所休憩時間帯(void)をコールする。<BR> 
     *        例外がthrowされた場合、４）を行ってから、<BR>
     *        throwされた例外をそのままthrowする。<BR> 
     *<BR>
     *  ４）　@TIMESTAMP_TAG の設定値を、元に戻す。<BR>
     *        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, １）で<BR>
     *        記憶させておいた元の値)<BR> 
     *<BR>
     *  ５）　@３）の戻り値を返却する。<BR> 
     *<BR>
     * @@param l_date 判定対象日時
     * @@return java.lang.boolean
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public static boolean isTradeCloseTimeZone(
        Date l_date) 
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isTradeCloseTimeZone(Date)";
        log.entering(STR_METHOD_NAME);

        boolean l_boReturn;

        //１）　@TIMESTAMP_TAG の値を取得し、メソッド内のローカル変数に記憶させる。 
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)(ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG));

        //２）　@TIMESTAMP_TAG に、引数の「判定対象日時」をセットする。 
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_date);

        //３）　@this.is取引所休憩時間帯(void)をコールする。 
        try
        {
            l_boReturn = isTradeCloseTimeZone();
        }
        catch (WEB3SystemLayerException l_e)
        {
            //４）　@TIMESTAMP_TAG の設定値を、元に戻す。 
            ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_tsOrderAcceptDate);
            throw new WEB3SystemLayerException(
                l_e.getErrorInfo(),
                WEB3GentradeTradingTimeManagement.class.getName() + "." 
                    + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        
        //４）　@TIMESTAMP_TAG の設定値を、元に戻す。 
        ThreadLocalSystemAttributesRegistry.setAttribute(TIMESTAMP_TAG, l_tsOrderAcceptDate);

        log.exiting(STR_METHOD_NAME);
        return l_boReturn;
    }

    /**
     * (validate連続注文受付可能)<BR>
     * 連続注文の受付が可能かどうか判定する。<BR>
     * （*市場閉局後～注文繰越終了までの間は、連続注文受付を不可とする。）<BR>
     * <BR>
     * １）現在日時を取得する。<BR>
     * <BR>
     * ２）取引時間管理.get市場閉局時間()で、最終閉局時間を取得する。<BR>
     * <BR>
     * 　@　@[引数設定仕様]<BR>
     * 　@　@　@市場コード：　@取引カレンダコンテキストの同項目<BR>
     * 　@　@　@銘柄コード：　@取引カレンダコンテキストの同項目<BR>
     * <BR>
     * ３）以下の条件に該当する場合、パラメータ.証券会社.get注文繰越処理区分()をコールする。<BR>
     * <BR>
     * 　@　@　@・部店.is夕場実施() == true and 取引時間管理.is夕場時間帯() == true <BR>
     * and 取引時間管理.isトリガ発行() == false） (*1)<BR>
     * 　@　@　@　@※部店は引数.証券会社、取引カレンダコンテキスト.部店コードから取得したもの <BR>
     * 　@　@　@・１）で取得した現在日時 > ２）で取得した最終閉局時間 (*2)<BR>
     * <BR>
     * 　@　@上記メソッドの戻り値が"処理済"以外の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@[is夕場実施()に指定する引数]<BR>
     * 　@　@　@銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * <BR>
     * 　@　@[isトリガ発行()に指定する引数]<BR>
     * 　@　@　@発注条件：　@null<BR>
     * <BR>
     * 　@　@[get注文繰越処理区分()に指定する引数]<BR>
     * 　@　@　@銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * 　@　@　@先物／オプション区分：　@パラメータ.先物／オプション区分<BR>
     * 　@　@　@出来終了区分：　@パラメータ.出来終了区分<BR>
     * <BR>
     * (*1) 日中立会時間終了～夕場立会時間開始の時間帯<BR>
     * (*2) 最終閉局時間以降の時間帯<BR>
     * @@param l_institution 証券会社
     * @@param l_productTypeEnum 銘柄タイプ
     * @@param l_strFutureOptionType 先物／オプション区分
     * （0：DEFAULT　@1：先物　@2：オプション）<BR>
     * @@param l_strExecutionEndType 出来終了区分
     * （1：夕場前出来終了（夕場実施する会社）　@DEFAULT 0（出来終了（最終）））<BR>
     * @@throws WEB3BaseException
     */
    public static void validateTriggerOrderAccept(
        WEB3GentradeInstitution l_institution,
        ProductTypeEnum l_productTypeEnum,
        String l_strFutureOptionType,
        String l_strExecutionEndType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "validateTriggerOrderAccept(Institution, ProductTypeEnum, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        String l_strMarketCode = l_clendarContext.getMarketCode();
        String l_strProductCode = l_clendarContext.getProductCode();
        String l_strBranchCode = l_clendarContext.getBranchCode();
        WEB3GentradeBranch l_branch = null;

        //部店は引数.証券会社、取引カレンダコンテキスト.部店コードから取得したもの
        try
        {
            l_branch = new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        String l_strTradeCloseTime =
            WEB3GentradeTradingTimeManagement.getTradeCloseTime(
                l_strMarketCode,
                l_strProductCode);
        Timestamp l_tsOrderAcceptTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptTime);

        //以下の条件に該当する場合、パラメータ.証券会社.get注文繰越処理区分()をコールする。
        //  日中立会時間終了～夕場立会時間開始の時間帯
        //  最終閉局時間以降の時間帯
        if ((l_strAcceptTime.compareTo(l_strTradeCloseTime) > 0)
            || (l_branch.isEveningSessionEnforcemented(l_productTypeEnum)
                && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                && !WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null)))
        {
            String l_strCarryoverEndType = 
                l_institution.getCarryoverEndType(
                    l_productTypeEnum,
                    l_strFutureOptionType,
                    l_strExecutionEndType);
            if (!WEB3CarryoverEndTypeDef.COMPLETE_PROCESS.equals(l_strCarryoverEndType))
            {
                log.debug("市場閉局後～注文繰越終了までの間は、連続注文受付を不可とする。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02240,
                    WEB3GentradeTradingTimeManagement.class.getName() +
                    "." + STR_METHOD_NAME,
                    "市場閉局後～注文繰越終了までの間は、連続注文受付を不可とする。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateダウンロード時間帯)<BR>
     * ダウンロードが可能な時間帯かをチェックする<BR>
     * <BR>
     * １）　@ダウンロード時間帯チェック<BR>
     * 　@取引時間テーブルを以下の条件で検索し、該当行の「受付可能」項目が"<BR>
     * 受付不可"であれば、ダウンロード不可と判定する。<BR>
     * 　@該当行が複数行ある場合は、１件でも「受付可能」であればダウンロード可能とする。<BR>
     * <BR>
     * 　@[検索キー]<BR>
     * 　@証券会社コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@部店コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@市場コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     *　@　@※但し、取引時間コンテキストの市場コードプロパティがnullであれば、<BR>
     * 市場コードは検索条件に含めない（すべての市場を対象とする）<BR>
     * 　@受付時間区分：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     * 　@営業日区分：　@(*3)<BR>
     * 　@銘柄コード：　@取引時間コンテキスト(*1)の同名プロパティ<BR>
     *　@　@※但し、取引時間コンテキストの銘柄コードプロパティがnullであれば、<BR>
     * 銘柄コードは検索条件に含めない（すべての銘柄を対象とする）<BR>
     * 　@開始時間 <= 受付時間(*2) <=　@終了時間<BR>
     * <BR>
     * 　@該当行が存在しない場合は、データ不整合（システムエラー）として例外をスローする。<BR>
     * <BR>
     * (*1)　@取引時間コンテキストの取得<BR>
     * －ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * (*2) 受付時間の取得<BR>
     * －ThreadLocalSystemAttributesRegistryより受付日時を取得し、取得した日時の時間部分。<BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * (*3) 営業日区分の取得<BR>
     * －取得した受付日時の曜日を取得し、土曜日または日曜日の場合は”非営業日”。<BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、行の営業日区分を取得する。<BR>
     * 行が取得できなかった場合は、営業日区分を”通常営業日”とする。<BR>
     * @@throws WEB3BaseException
     */
    public static void validateDownloadTimeZone()
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateDownloadTimeZone()";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strMarketCode = null;
        String l_strTradingTimeType = null;
        String l_strProductCode = null;

        //(*1)　@取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        //部店コード
        l_strBranchCode = l_clendarContext.getBranchCode();
        //市場コード
        l_strMarketCode = l_clendarContext.getMarketCode();
        //受付時間区分
        l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //銘柄コード
        l_strProductCode = l_clendarContext.getProductCode();

        //(*2) 受付時間の取得
        Timestamp l_tsOrderAcceptDate = 
            (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);
        SimpleDateFormat l_format = 
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        //(*3) 営業日区分
        String l_bizDateType = getBizDateType(l_tsOrderAcceptDate);

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
        //例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        // 　@取引時間コンテキスト.受付時間区分
        if ((l_strInstitutionCode == null) || 
            (l_strBranchCode == null) || 
            (l_strTradingTimeType == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        StringBuffer l_sbWhere = new StringBuffer();
        ArrayList l_lstObjTradingTimeWhere = new ArrayList();

        //証券会社コード
        l_sbWhere.append(" institution_code = ? ");
        l_lstObjTradingTimeWhere.add(l_strInstitutionCode.trim());
        //部店コード
        l_sbWhere.append(" and branch_code = ? ");
        l_lstObjTradingTimeWhere.add(l_strBranchCode.trim());
        //市場コード
        if (l_strMarketCode != null)
        {
            l_sbWhere.append(" and market_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strMarketCode.trim());
        }
        //受付時間区分
        l_sbWhere.append(" and trading_time_type = ? ");
        l_lstObjTradingTimeWhere.add(l_strTradingTimeType.trim());
        //営業日区分
        l_sbWhere.append(" and biz_date_type = ? ");
        l_lstObjTradingTimeWhere.add(l_bizDateType);
        //銘柄コード
        if (l_strProductCode != null)
        {
            l_sbWhere.append(" and product_code = ? ");
            l_lstObjTradingTimeWhere.add(l_strProductCode.trim());
        }
        //開始時間 <= 受付時間(*2) <=　@終了時間
        l_sbWhere.append(" and start_time <= ? and end_time >= ?");
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);
        l_lstObjTradingTimeWhere.add(l_strAcceptTime);

        int l_intSize = l_lstObjTradingTimeWhere.size();
        Object[] l_objTradingTimeWhere = new Object[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_objTradingTimeWhere[i] = l_lstObjTradingTimeWhere.get(i);
        }

        List l_lisRecords = null;
        try
        {
            //取引時間テーブルを検索する
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objTradingTimeWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //件数チェック
        int l_intLength = l_lisRecords.size();
        if (l_intLength == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                "取引時間テーブル検索： 件数 = 0");
        }

        //複数行ある場合は、１件でも「受付可能」であれば注文受付可能とする
        TradingTimeRow l_tradingTimeRow = null;
        String l_strEnableOrder = null;
        for (int i = 0; i < l_intLength; i++)
        {
            l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (WEB3EnableOrderDef.ENABLE.equals(l_tradingTimeRow.getEnableOrder()))
            {
                l_strEnableOrder = l_tradingTimeRow.getEnableOrder();
                break;
            }
        }
        if (l_strEnableOrder == null)
        {
            log.debug(STR_METHOD_NAME + "：ダウンロード不可時間帯");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02302,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set発注日計算用基準日時)<BR>
     * <BR>
     * LocalThread中の発注日計算用の基準日時のセットを行う。<BR>
     * <BR>
     * ※ 引け後にずれ込んで到着した下りキューを、当日発注扱いで処理する必要がある場合、<BR>
     * ※ 当メソッドで当日発注扱いとなる日時を設定する。<BR>
     * <BR>
     * 引数の基準日時を、LocalThreadのBASE_TIMESTAMP_FOR_ORDER_BIZ_DATEにセットする。<BR>
     * ※ThreadLocalSystemAttributesRegistry.setAttribute()でセット。<BR>
     * @@param l_strTradingTimeType - 基準日時<BR>
     * 発注日計算用の基準日時。<BR>
     */
    public static void setBaseTimestampForOrderBizDate(Timestamp l_tsBizDate)
    {
        final String STR_METHOD_NAME = "setBaseTimestampForOrderBizDate(Timestamp)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
            l_tsBizDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (clear発注日計算用基準日時)<BR>
     * <BR>
     * LocalThread中の発注日計算用の基準日時のクリアを行う。<BR>
     * <BR>
     * LocalThreadのBASE_TIMESTAMP_FOR_ORDER_BIZ_DATEをクリアする。<BR>
     * ※ThreadLocalSystemAttributesRegistry.setAttribute()でnullをセット。<BR>
     */
    public static void clearBaseTimestampForOrderBizDate()
    {
        final String STR_METHOD_NAME = "clearBaseTimestampForOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is取引所受付前時間帯)<BR>
     * <BR>
     * WebⅢ時間でオンライン（開局）時間帯の場合に、<BR> 
     * 取引所時間で、該当取引所が受付前（開局前）の時間帯かどうかを判定する。<BR> 
     * （取引所時間：　@市場マスタ.｛注文受付開始時刻、注文受付終了時刻｝で設定）<BR> 
     * <BR>
     * １）　@this.is市場開局時間帯()==falseの場合は、falseを返却する。<BR> 
     * <BR>
     * ２）　@this.is市場開局時間帯()==true、かつ <BR>
     * 　@　@（市場の注文受付開始時刻(*1) >  現在日時(*2)のHHMMSS） の場合、trueを返却する。<BR> 
     * 　@　@以外、falseを返却する。 <BR>
     * <BR>
     * (*1)注文受付開始時刻 <BR>
     * 取引カレンダコンテキスト.証券会社コード、市場コードに該当する <BR>
     * 市場オブジェクト.注文受付開始時刻。<BR>
     * ※市場マスタには"HH:MM"フォーマットで設定されているので、<BR> 
     * ※"HHMM00"でチェック。<BR> 
     * <BR>
     * (*2)現在日時 <BR>
     * GtlUtils.getSystemTimestamp()の戻り値。<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public static boolean isTradeAcceptBeforeTimeZone() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isTradeAcceptBeforeTimeZone()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //取引時間コンテキストの取得
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                    TRADING_CAL_CONTEXT_PATH);

            //証券会社を取得
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(
                l_context.getInstitutionCode());
            
            //市場を取得
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(
                l_institution,
                l_context.getMarketCode());
            MarketRow l_marketRow = (MarketRow) l_market.getDataSourceObject();
            
            //(*1)注文受付開始時刻を取得
            String l_strOpenTime = l_marketRow.getOpenTime();
            
            //注文受付開始時刻フォーマット
            SimpleDateFormat l_dateParse = GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.TIME_PARSE_HM);
            Date l_datOpenTime = l_dateParse.parse(l_strOpenTime);
            
            SimpleDateFormat l_dateFormat = GtlUtils.getThreadSafeSimpleDateFormat(
                WEB3GentradeTimeDef.TIME_FORMAT_HM0);
            l_strOpenTime = l_dateFormat.format(l_datOpenTime);
            
            // (*2)現在日時 
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
            String l_strSystemTime = WEB3DateUtility.formatDate(
                l_tsSystemTimestamp, 
                WEB3GentradeTimeDef.TIME_FORMAT_HMS);
            
            //is市場開局時間帯()を取得
            boolean l_blnIsTradeOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            if (l_blnIsTradeOpenTimeZone && l_strOpenTime.compareTo(l_strSystemTime) > 0)
            {
                //２）　@this.is市場開局時間帯()==true、かつ 
                //（市場の注文受付開始時刻(*1) >  現在日時(*2)のHHMMSS） の場合、
                // trueを返却する。 
                log.exiting(STR_METHOD_NAME);
            	return true;
            }

            //１）this.is市場開局時間帯()==falseの場合は、falseを返却する。
            //　@以外、falseを返却する。 
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataNetworkException l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (DataQueryException l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        catch (Exception l_exp)
        {
            log.error(STR_METHOD_NAME, l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
    }

    /**
     * (isオンラインサービス開始後)<BR>
     * <BR>
     * オンラインサービス開始後か判定する。<BR>
     * <BR>
     * １）　@システムプリファ@レンスからオンラインサービス開始時間を取得する。<BR>
     * <BR>
     * ２）　@以下条件に該当する場合、true（オンラインサービス開始後）を返却する。<BR>
     * 　@　@　@以外、false（オンラインサービス開始前）を返却する。<BR>
     * <BR>
     * 　@　@　@　@現在日時の時間部分　@≧　@オンラインサービス開始時間<BR>
     * <BR>
     * @@return boolean
     */
    public static boolean isOnlineServiceStartAfter()
    {
        final String STR_METHOD_NAME = "isOnlineServiceStartAfter()";
        log.entering(STR_METHOD_NAME);

        //１）　@システムプリファ@レンスからオンラインサービス開始時間を取得する。
        String l_strValue = GtlUtils.getTradingSystem().getPreference(
                                WEB3SystemPreferencesNameDef.ONLINE_SERVICE_START_TIME);

        //値を取得する。
        if (l_strValue == null)
        {
        	log.exiting(STR_METHOD_NAME);
        	return false;
        }

        //現在日時を取得
        Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
        String l_strSystemTime = WEB3DateUtility.formatDate(
            l_tsSystemTimestamp, 
            WEB3GentradeTimeDef.TIME_FORMAT_HMS);

        //２）　@以下条件に該当する場合、true（オンラインサービス開始後）を返却する。 
        // 現在日時の時間部分　@≧　@オンラインサービス開始時間
        if (l_strValue != null 
        	&& !"".equals(l_strValue.trim()) 
        	&& l_strSystemTime.compareTo(l_strValue.trim()) >= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        // 以外、false（オンラインサービス開始前）を返却する。 
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is夕場時間帯)<BR>
     * <BR>
     * 夕場時間帯かどうかを判定する。 <BR>
     * <BR>
     * １）　@this.get立会区分()をコールする。 <BR>
     * <BR>
     * ２）　@１）の戻り値が”夕場”であればtrue、以外はfalseを返却する。 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isEveningSessionTimeZone() throws WEB3SystemLayerException
    {
        return WEB3SessionTypeDef.EVENING_SESSION.equals(
            WEB3GentradeTradingTimeManagement.getSessionType());
    }

    /**
     * (get立会区分)<BR>
     * <BR>
     * 該当時間の立会区分を取得する。 <BR>
     * （夕場実施会社で夕場時間帯の場合は夕場。以外はNULL。） <BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 取引時間管理.TIMESTAMP_TAG) <BR>
     * <BR>
     * ２）　@ThreadLocalSystemAttributesRegistryより、<BR>
     * 取引カレンダコンテキストを取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * ３）　@受付日の営業日区分判定。 <BR>
     * 　@取得した受付日時の曜日を取得し、<BR>
     * 土曜日または日曜日の場合は”休日”と判定し、営業日区分を”非営業日”とする。 <BR>
     * 　@以外の場合、受付日時の日付部分でカレンダテーブルを検索し、<BR>
     * 行の営業日区分を取得する。<BR>
     * 行が取得できなかった場合は、営業日区分を”通常営業日”とする。 <BR>
     * <BR>
     * 　@但し、外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）、<BR>
     * 外株海外市場カレンダテーブルを以下の条件で検索し、<BR>
     * 該当行の営業日区分が”非営業日”の場合、営業日区分”非営業日”とする。 <BR>
     * <BR>
     *  【取引時間コンテキスト.受付時間区分 == 外国株式】 <BR>
     * 　@[外株海外市場カレンダテーブル検索条件] <BR>
     * 　@証券会社コード = 取引時間コンテキスト.証券会社コード <BR>
     * 　@市場コード = 取引時間コンテキスト.市場コード <BR>
     * 　@日付 = （受付日時の日付部分） <BR>
     * <BR>
     * ４）　@取引時間取得 <BR>
     * 　@以下の検索キーで取引時間テーブルを検索する。 <BR>
     * <BR>
     * 　@[検索キー] <BR>
     * 　@証券会社コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@部店コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@市場コード：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@受付時間区分：　@取引時間コンテキストの同名プロパティ <BR>
     * 　@商品コード：　@取引時間コンテキストの銘柄コード <BR>
     * 　@営業日区分：　@判定した営業日区分 <BR>
     * 　@開始時間　@<= （受付日時の時間部分） <= 終了時間 <BR>
     * <BR>
     * 　@上記に一致する行の「立会区分」を返却する。 <BR>
     * 　@上記に一致する行が存在しない場合は、<BR>
     * データ不整合（システムエラー）として例外をスローする。 <BR>
     * 　@class: WEB3SystemLayerException<BR>
     * 　@tag:   SYSTEM_ERROR_80006<BR>
     * <BR>
     * @@return String
     * @@throws WEB3SystemLayerException
     */
    public static String getSessionType() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getSessionType()";
        log.entering(STR_METHOD_NAME);

        //１）　@ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        //２）　@ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);

        //３）　@受付日の営業日区分判定。
        //営業日区分の取得
        String l_strBizDateType = getBizDateType(l_tsOrderAcceptDate);
        //受付時間区分
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        //証券会社コード
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        //市場コード
        String l_strMarketCode = l_clendarContext.getMarketCode();

        //外株の場合（取引時間コンテキスト.受付時間区分 == 外国株式）
        if (WEB3TradingTimeTypeDef.FOREIGN_STOCK.equals(l_strTradingTimeType))
        {
            String l_strFeqBizDateType = getFeqBizDateType(
                l_strInstitutionCode,
                l_strMarketCode,
                l_tsOrderAcceptDate);
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
            {
                l_strBizDateType = l_strFeqBizDateType;
            }
        }

        //４）　@取引時間取得
        //部店コード
        String l_strBranchCode = l_clendarContext.getBranchCode();
        //商品コード
        String l_strProductCode = l_clendarContext.getProductCode();

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
        //データ不整合として例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        // 　@取引時間コンテキスト.市場コード
        // 　@取引時間コンテキスト.受付時間区分
        // 　@取引時間コンテキスト.銘柄コード
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
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        // ５） 取引時間テーブルを検索する
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        Object[] l_objWhere = {
            l_strInstitutionCode,  //証券会社コード
            l_strBranchCode,       //部店コード
            l_strMarketCode,       //市場コード
            l_strTradingTimeType,  //受付時間区分
            l_strProductCode,      //商品コード
            l_strBizDateType,      //営業日区分
            };

        List l_lisRecords;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                TradingTimeRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //件数チェック
        if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        //受付時間の取得(取得した日時の時間部分)
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strAcceptTime = l_format.format(l_tsOrderAcceptDate);

        TradingTimeRow l_tradingTimeRow = null;
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
            if (Long.parseLong(l_tmpTradingTimeRow.getStartTime()) <= Long.parseLong(l_strAcceptTime)
                && Long.parseLong(l_tmpTradingTimeRow.getEndTime()) >= Long.parseLong(l_strAcceptTime))
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }
        if (l_tradingTimeRow == null)
        {
            log.debug("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }
        //get立会区分
        String l_strSessionType = l_tradingTimeRow.getSessionType();

        log.exiting(STR_METHOD_NAME);
        return l_strSessionType;
    }

    /**
     * (validate閉局後訂正取消受付可能)<BR>
     * 注文の訂正／取消の受付が可能かどうか判定する。  <BR>
     * （*後場閉局後～後場出来終了または、  <BR>
     * 　@市場閉局後～市場閉局後出来終了の間、  <BR>
     * 　@注文訂正／取消を不可とする。）  <BR>
     * <BR>
     * ※引数,立会区分・発注日には、訂正取消元注文の値を設定する。 <BR>
     * <BR>
     * １）　@引数.部店.is夕場実施()をコールする。  <BR>
     * <BR>
     * 　@　@　@[引数]  <BR>
     * 　@　@　@銘柄タイプ：　@引数.銘柄タイプ  <BR>
     * <BR>
     * <BR>
     * ２）　@夕場実施会社（「１）」の戻り値 ＝ true）かつ <BR>
     * 　@　@　@市場開局時間帯（this.is市場開局時間帯() ＝ true）かつ  <BR>
     * 　@　@　@this.get立会区分() ≠ 引数.立会区分の場合 <BR>
     * 　@　@　@（日中登録した注文） <BR>
     * <BR>
     * 　@　@　@下記条件で【出来終了テーブル】を検索し、<BR>
     * 該当データなしの場合は例外(*1)をthrowする。  <BR>
     * 　@　@　@該当データありの場合、何もせずreturnする。 <BR>
     * <BR>
     * 　@　@　@＜検索条件＞  <BR>
     * 　@　@　@　@証券会社コード：　@取引時間コンテキスト(*3)の同名プロパティ  <BR>
     * 　@　@　@　@銘柄タイプ：　@引数.銘柄タイプ  <BR>
     * 　@　@　@　@先物／オプション区分：　@引数.先物／オプション区分  <BR>
     * 　@　@　@　@出来終了区分：　@”夕場前出来終了” <BR>
     * <BR>
     * <BR>
     * ３）　@this.get発注日() ＞ 引数.発注日の場合  <BR>
     * <BR>
     * 　@　@　@下記条件で【出来終了テーブル】を検索し、<BR>
     * 該当データなしの場合は例外(*2)をthrowする。  <BR>
     * 　@　@　@該当データありの場合、何もせずreturnする。 <BR>
     * <BR>
     * 　@　@　@＜検索条件＞  <BR>
     * 　@　@　@　@証券会社コード：　@取引時間コンテキスト(*3)の同名プロパティ  <BR>
     * 　@　@　@　@銘柄タイプ：　@引数.銘柄タイプ  <BR>
     * 　@　@　@　@先物／オプション区分：　@引数.先物／オプション区分  <BR>
     * 　@　@　@　@出来終了区分：　@DEFAULT <BR>
     * <BR>
     * <BR>
     * ４）　@上記条件に該当しない場合、何もせずreturnする。  <BR>
     * <BR>
     * <BR>
     * (*1) throwするメッセージ：  <BR>
     * 「後場閉局後―証券会社毎に通知を受けて行う後場閉局後の出来終了通知終了までの間は、  <BR>
     * 注文訂正／取消受付を不可とします。」  <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02824<BR>
     * <BR>
     * (*2) throwするメッセージ：  <BR>
     * 「市場閉局後―証券会社毎に通知を受けて行う出来終了通知終了までの間は、  <BR>
     * 注文訂正／取消受付を不可とします。」  <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00812<BR>
     * <BR>
     * (*3)　@取引時間コンテキストの取得  <BR>
     * ?ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。  <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TRADING_CAL_CONTEXT_PATH) <BR>
     * <BR>
     * @@param l_productType 銘柄タイプ<BR>
     * @@param l_strFutureOptionDiv 先物／オプション区分<BR>
     * @@param l_branch 部店<BR>
     * @@param l_strSessionType 立会区分<BR>
     * 注文単位.立会区分を設定する。<BR>
     * @@param l_datBizDate 発注日<BR>
     * 注文単位.発注日を設定する。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public static void validateTradeCloseChangeOrCancel(
        ProductTypeEnum l_productType,
        String l_strFutureOptionDiv,
        WEB3GentradeBranch l_branch,
        String l_strSessionType,
        Date l_datBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateTradeCloseChangeOrCancel(ProductTypeEnum, String, WEB3GentradeBranch, String, Date)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
            TRADING_CAL_CONTEXT_PATH);
        //証券会社コード
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();

        //夕場実施会社（「引数.部店.is夕場実施()の戻り値 ＝ true）かつ
        //市場開局時間帯（this.is市場開局時間帯() ＝ true）かつ
        //this.get立会区分() ≠ 引数.立会区分の場合
        //（日中登録した注文）
        if (l_branch.isEveningSessionEnforcemented(l_productType)
            && WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone()
            && !WEB3Toolkit.isEquals(WEB3GentradeTradingTimeManagement.getSessionType(), l_strSessionType))
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and future_option_div = ? ");
            l_sbWhere.append(" and orderexecution_end_type = ? ");

            Object[] l_objWheres = {
                l_strInstitutionCode,                      //証券会社コード
                l_productType,                             //銘柄タイプ
                l_strFutureOptionDiv,                      //先物／オプション区分
                WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END,
                };

            List l_lisRecords;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    OrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWheres);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                log.debug("受付時間外エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02824,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    "後場閉局後―証券会社毎に通知を受けて行う後場閉局後の出来終了通知終了までの間は、"
                        + "注文訂正／取消受付を不可とします。");
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //this.get発注日() ＞ 引数.発注日の場合
        else if (WEB3DateUtility.compare(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_datBizDate) > 0)
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            l_sbWhere.append(" and product_type = ? ");
            l_sbWhere.append(" and future_option_div = ? ");
            l_sbWhere.append(" and orderexecution_end_type = ? ");

            Object[] l_objWhere = {
                l_strInstitutionCode,                      //証券会社コード
                l_productType,                             //銘柄タイプ
                l_strFutureOptionDiv,                      //先物／オプション区分
                WEB3OrderexecutionEndTypeDef.DEFAULT,
                };

            List l_lisRecords;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_queryProcessor.doFindAllQuery(
                    OrderexecutionEndRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(l_dqe.getMessage(), l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(l_dne.getMessage(), l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }
            if ((l_lisRecords == null) || (l_lisRecords.isEmpty()))
            {
                log.debug("受付時間外エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00812,
                    WEB3GentradeTradingTimeManagement.class.getName()
                        + "." + STR_METHOD_NAME,
                    "市場閉局後―証券会社毎に通知を受けて行う出来終了通知終了までの間は、"
                        + "注文訂正／取消受付を不可とします。");
            }
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
    }

    /**
     * (is立会時間帯)<BR>
     * 立会時間帯かどうか返却する。 <BR>
     * 立会時間帯であればtrue、立会時間帯でなければfalseを返却する。 <BR>
     * <BR>
     * １） 以下の条件判定を行う。 <BR>
     * <BR>
     * 　@　@市場へリアルタイムにトリガを発行する（this.isトリガ発行() == true） または、 <BR>
     * 　@　@市場休憩時間帯である（this.is取引所休憩時間帯() == true） <BR>
     * <BR>
     * 　@　@【条件に該当する場合】 <BR>
     * <BR>
     * 　@　@　@　@true（立会時間帯）を返却する。 <BR>
     * <BR>
     * 　@　@【上記以外の場合】 <BR>
     * <BR>
     * 　@　@　@　@false（立会時間帯ではない）を返却する。 <BR>
     * <BR>
     * <BR>
     * 　@　@[isトリガ発行()に指定する引数］ <BR>
     * <BR>
     * 　@　@　@　@発注条件 ： null <BR>
     * <BR>
     * 　@　@[is取引所休憩時間帯()に指定する引数］ <BR>
     * <BR>
     * 　@　@　@　@引数なし<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@return boolean
     */
    public static boolean isSessionTimeZone()
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isSessionTimeZone()";
        log.entering(STR_METHOD_NAME);

        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(null)
            || WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isRLS非同期処理時間帯)<BR>
     * ルールエンジンサーバへの通知を非同期で処理する時間帯を判定する。 <BR>
     * 市場閉局時間帯 or 夕場対応商品の繰越処理は非同期処理を行う。 <BR>
     * 以外は、同期処理される。 <BR>
     * <BR>
     * １） ThreadLocalSystemAttributesRegistryより、RLSへの非同期通知フラグを取得する。 <BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * 　@　@WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY <BR>
     * 　@　@)  <BR>
     * <BR>
     * ２）判定を行う。 <BR>
     * 　@２－１）　@非同期処理時間帯の判定 <BR>
     * 　@　@this.is立会時間帯() == false <BR>
     * 　@　@　@または、１）で取得した値がBooleanEnum.TRUEの場合、 <BR>
     * 　@　@trueを返却する。 <BR>
     * <BR>
     * 　@２－２）　@同期処理時間帯の判定 <BR>
     * 　@　@以外の場合、falseを返却する。 <BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     */
    public static boolean isRlsAsyncTreatmentTimeZone() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isRLSAsyncTreatmentTimeZone()";
        log.entering(STR_METHOD_NAME);

        //１） ThreadLocalSystemAttributesRegistryより、RLSへの非同期通知フラグを取得する。
        BooleanEnum l_rlsAsyncNotifyFlag =
            (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3ThreadLocalSystemAttributePathDef.ORDER_CARRYOVER_ASYNC_RLS_NOTIFY);

        //２－１）　@非同期処理時間帯の判定
        if ((!WEB3GentradeTradingTimeManagement.isSessionTimeZone())
            || (BooleanEnum.TRUE.equals(l_rlsAsyncNotifyFlag)))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //２－２）　@同期処理時間帯の判定
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (getPTS発注日)<BR>
     * 発注日を取得する。<BR>
     * <BR>
     * １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.<BR>
     * 　@getAttribute("xblocks.gtl.attributes.systemtimestamp")<BR>
     * <BR>
     * 　@ただし、ThreadLocalSystemAttributesRegistryに、<BR>
     * 　@設定キー："web3.attributes.basetimestampfororderbizdate"<BR>
     * 　@（発注日計算用の基準日時）の値がセットされている場合は、<BR>
     * 　@以降の処理で受付日時として、<BR>
     * 　@設定キー："web3.attributes.basetimestampfororderbizdate"の値を使用する。<BR>
     * <BR>
     * <BR>
     * ２） ThreadLocalSystemAttributesRegistryより、<BR>
     * 　@取引カレンダコンテキストを取得する。<BR>
     * 　@ThreadLocalSystemAttributesRegistry.<BR>
     * 　@getAttribute("web3.tradingcalendarcontext")<BR>
     * <BR>
     * 　@取得したコンテキストの以下の項目にnullが格納されていた場合は、<BR>
     * 　@データ不整合として例外をスローする。<BR>
     * <BR>
     * 　@　@取引時間コンテキスト.証券会社コード<BR>
     * 　@　@取引時間コンテキスト.部店コード<BR>
     * 　@　@取引時間コンテキスト.市場コード<BR>
     * 　@　@取引時間コンテキスト.受付時間区分<BR>
     * 　@　@取引時間コンテキスト.銘柄コード<BR>
     * <BR>
     * <BR>
     * ３） 受付日の営業日区分判定<BR>
     * 　@this.getPTS営業日区分()をコールする。<BR>
     * <BR>
     * 　@[getPTS営業日区分()に設定する引数]<BR>
     * 　@日付： １）で取得した受付日時の日付部分<BR>
     * <BR>
     * <BR>
     * ４） 取引時間取得<BR>
     * <BR>
     * 　@４－１） 以下の検索キーで取引時間テーブルを検索する。<BR>
     * <BR>
     * 　@　@[検索キー]<BR>
     * 　@　@証券会社コード： 取引時間コンテキストの同名プロパティ<BR>
     * 　@　@部店コード： 取引時間コンテキストの同名プロパティ<BR>
     * 　@　@市場コード： 取引時間コンテキストの同名プロパティ<BR>
     * 　@　@受付時間区分： 取引時間コンテキストの同名プロパティ<BR>
     * 　@　@銘柄コード： 取引時間コンテキストの同名プロパティ<BR>
     * 　@　@営業日区分： ３）で取得した営業日区分<BR>
     * <BR>
     * ４－２） ４－１）で取得した行のうち、<BR>
     * 　@　@開始時間 <= （受付日時の時間部分） <= 終了時間<BR>
     *　@　@ に該当する行の「発注日計算」項目に応じて以下の値を返却する。<BR>
     * 　@　@(*) 該当行が存在しない場合は、<BR>
     * 　@　@データ不整合（システムエラー）として例外をスローする。<BR>
     * <BR>
     * ４－２－１） 発注日計算 = 前営業日 の場合、受付日時の前営業日を返却する。<BR>
     * 　@　@　@　@営業日計算.calcPTS営業日()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[calcPTS営業日()の引数]<BR>
     * 　@　@　@　@基準日： 受付日時<BR>
     * 　@　@　@　@加算／減算日数： -1 （前営業日）<BR>
     * <BR>
     * ４－２－２） 発注日計算 = 当日 の場合、受付日時の日付部分を返却する。<BR>
     * <BR>
     * ４－２－３） 発注日計算 = 翌営業日 の場合、受付日時の翌営業日を返却する。<BR>
     * 　@　@　@　@営業日計算.calcPTS営業日()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[calcPTS営業日()の引数]<BR>
     * 　@　@　@　@基準日： 受付日時<BR>
     * 　@　@　@　@加算／減算日数： 1 （翌営業日）<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3SystemLayerException
     */
    public static Date getPTSOrderBizDate() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getPTSOrderBizDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datPTSOrderBizDate = null;

        // １） ThreadLocalSystemAttributesRegistryより、受付日時を取得する。
        // ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.systemtimestamp")
        Timestamp l_tsOrderAcceptDate =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(TIMESTAMP_TAG);

        // ただし、ThreadLocalSystemAttributesRegistryに、
        // 設定キー："web3.attributes.basetimestampfororderbizdate"
        // （発注日計算用の基準日時）の値がセットされている場合は、
        Object l_objOrderBizDate =
            ThreadLocalSystemAttributesRegistry.getAttribute(BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE);
        if (l_objOrderBizDate != null)
        {
            // 以降の処理で受付日時として、
            // 設定キー："web3.attributes.basetimestampfororderbizdate"の値を使用する。
            l_tsOrderAcceptDate = (Timestamp)l_objOrderBizDate;
        }

        // ２） ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得する。
        // ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext")
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);

        if (l_clendarContext == null)
        {
            log.info("ThreadLocalSystemAttributesRegistryより、取引カレンダコンテキストを取得ない");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // 証券会社コード
        String l_strInstitutionCode = l_clendarContext.getInstitutionCode();
        // 市場コード
        String l_strMarketCode = l_clendarContext.getMarketCode();
        // 部店コード
        String l_strBranchCode = l_clendarContext.getBranchCode();
        // 受付時間区分
        String l_strTradingTimeType = l_clendarContext.getTradingTimeType();
        // 銘柄コード
        String l_strProductCode = l_clendarContext.getProductCode();

        // 取得したコンテキストの以下の項目にnullが格納されていた場合は、
        // データ不整合として例外をスローする。
        // 取引時間コンテキスト.証券会社コード
        // 取引時間コンテキスト.部店コード
        // 取引時間コンテキスト.市場コード
        // 取引時間コンテキスト.受付時間区分
        // 取引時間コンテキスト.銘柄コード
        if (l_strInstitutionCode == null
                || l_strBranchCode == null
                || l_strMarketCode == null
                || l_strTradingTimeType == null
                || l_strProductCode == null)
        {
            log.error("データ不整合エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        // ３） 受付日の営業日区分判定<BR>
        // this.getPTS営業日区分()をコールする。<BR>
        // [getPTS営業日区分()に設定する引数]<BR>
        // 日付： １）で取得した受付日時の日付部分
        Timestamp l_tsOrderAcceptDateDay =
            new Timestamp(WEB3DateUtility.toDay(l_tsOrderAcceptDate).getTime());
        String l_strBizDateType = getPTSBizDateType(l_tsOrderAcceptDateDay);

        // ４） 取引時間取得
        // ４－１） 以下の検索キーで取引時間テーブルを検索する。
        // [検索キー]
        // 証券会社コード： 取引時間コンテキストの同名プロパティ
        // 部店コード： 取引時間コンテキストの同名プロパティ
        // 市場コード： 取引時間コンテキストの同名プロパティ
        // 受付時間区分： 取引時間コンテキストの同名プロパティ
        // 銘柄コード： 取引時間コンテキストの同名プロパティ
        // 営業日区分： ３）で取得した営業日区分
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_code = ? ");
        l_sbWhere.append(" and market_code = ? ");
        l_sbWhere.append(" and trading_time_type = ? ");
        l_sbWhere.append(" and product_code = ? ");
        l_sbWhere.append(" and biz_date_type = ? ");

        Object[] l_objTradingTimeWhere =
        {
            l_strInstitutionCode.trim(),
            l_strBranchCode.trim(),
            l_strMarketCode.trim(),
            l_strTradingTimeType.trim(),
            l_strProductCode,
            l_strBizDateType
        };

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    TradingTimeRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        // ４－２） ４－１）で取得した行のうち、
        // 開始時間 <= （受付日時の時間部分） <= 終了時間
        // に該当する行の「発注日計算」項目に応じて以下の値を返却する。
        // (*) 該当行が存在しない場合は、
        // データ不整合（システムエラー）として例外をスローする。
        int l_intSize = l_lisRecords.size();
        if (l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        // 受付日時の時間部分
        SimpleDateFormat l_format =
            GtlUtils.getThreadSafeSimpleDateFormat(WEB3GentradeTimeDef.TIME_FORMAT_HMS);
        String l_strOrderAcceptDateTime = l_format.format(l_tsOrderAcceptDate);

        TradingTimeRow l_tradingTimeRow = null;
        for (int i = 0; i < l_intSize; i++)
        {
            TradingTimeRow l_tmpTradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);

            if (l_tmpTradingTimeRow.getStartTime().compareTo(l_strOrderAcceptDateTime) <= 0
                    && l_strOrderAcceptDateTime.compareTo(l_tmpTradingTimeRow.getEndTime()) <= 0)
            {
                l_tradingTimeRow = l_tmpTradingTimeRow;
                break;
            }
        }

        if (l_tradingTimeRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                "データ不整合エラー。");
        }

        String l_strBizdateCalcParameter = l_tradingTimeRow.getBizdateCalcParameter();
        WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsOrderAcceptDate);

        if (WEB3BizDateCalcParameterDef.BEFORE_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // ４－２－１） 発注日計算 = 前営業日 の場合、受付日時の前営業日を返却する。
            // 営業日計算.calcPTS営業日()をコールする。
            // [calcPTS営業日()の引数]
            // 基準日： 受付日時
            // 加算／減算日数： -1 （前営業日）
            Date l_datPTSOrderBizDateBefore =
                l_gentradeBizDate.calcPTSBizDate(l_tsOrderAcceptDate, -1);
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_datPTSOrderBizDateBefore);
        }
        else if (WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // ４－２－２） 発注日計算 = 当日 の場合、受付日時の日付部分を返却する。
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_tsOrderAcceptDate);
        }
        else if (WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizdateCalcParameter))
        {
            // ４－２－３） 発注日計算 = 翌営業日 の場合、受付日時の翌営業日を返却する。
            // 営業日計算.calcPTS営業日()をコールする。
            // [calcPTS営業日()の引数]
            // 基準日： 受付日時
            // 加算／減算日数： 1 （翌営業日）
            Date l_datPTSOrderBizDateAfter =
                l_gentradeBizDate.calcPTSBizDate(l_tsOrderAcceptDate, 1);
            l_datPTSOrderBizDate = WEB3DateUtility.toDay(l_datPTSOrderBizDateAfter);
        }

        log.exiting(STR_METHOD_NAME);
        return l_datPTSOrderBizDate;
    }

    /**
     * (getPTS営業日区分)<BR>
     * 引数.日付の営業日区分を返却する。<BR>
     * <BR>
     * １）　@引数.日付の曜日を取得する。<BR>
     * <BR>
     * 　@　@　@取得した曜日が日曜日の場合、”非営業日”を返却する。<BR>
     * 　@　@　@以外の場合、以降の処理を続ける。<BR>
     * <BR>
     * ２）　@引数.日付の日付部分でカレンダーテーブルを検索する。<BR>
     * <BR>
     * ３）　@this.get営業日区分()をコールし、前日の営業日区分を取得する。<BR>
     * <BR>
     * 　@　@　@[get営業日区分()に設定する引数]<BR>
     * 　@　@　@日付：　@引数.日付の前日<BR>
     * <BR>
     * ４）　@１）で取得した曜日が土曜日の場合、<BR>
     * 　@　@　@もしくは、２）にて該当データが取得できた場合<BR>
     * <BR>
     * 　@４－１）　@３）で取得した前日の営業日区分が”終日営業日”の場合<BR>
     * 　@　@　@　@　@　@　@”終日営業日（午前のみ）”を返却する。<BR>
     * <BR>
     * 　@４－２）　@３）で取得した前日の営業日区分が”非営業日”または”半日営業日”の場合<BR>
     * 　@　@　@　@　@　@　@”非営業日”を返却する。<BR>
     * <BR>
     * ５）　@４）の条件に該当しない場合、<BR>
     * <BR>
     * 　@５－１）　@３）で取得した前日の営業日区分が”終日営業日”の場合<BR>
     * 　@　@　@　@　@　@　@”終日営業日”を返却する。<BR>
     * <BR>
     * 　@５－２）　@３）で取得した前日の営業日区分が”非営業日”または”半日営業日”の場合<BR>
     * 　@　@　@　@　@　@　@”終日営業日（午後のみ）”を返却する。<BR>
     * @@param l_tsDate - (日付)<BR>
     * 日付<BR>
     * @@return String
     */
    public static String getPTSBizDateType(Timestamp l_tsDate)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "getPTSBizDateType(Timestamp)";
        CalendarRow l_calendarRow = null;

        String l_strPTSBizDateType = null;

        Calendar l_calendarBizDate = Calendar.getInstance();
        l_calendarBizDate.setTime(l_tsDate);

        // DB検索用に時分秒ミリ秒を初期化
        l_calendarBizDate.set(Calendar.HOUR_OF_DAY, 0);
        l_calendarBizDate.set(Calendar.MINUTE, 0);
        l_calendarBizDate.set(Calendar.SECOND, 0);
        l_calendarBizDate.set(Calendar.MILLISECOND, 0);

        // １）　@引数.日付の曜日を取得する。
        // 取得した曜日が日曜日の場合、”非営業日”を返却する。
        // 以外の場合、以降の処理を続ける。
        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
        {
            l_strPTSBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
            return l_strPTSBizDateType;
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //２）　@引数.日付の日付部分でカレンダーテーブルを検索する。
            List l_lisResults = l_queryProcessor.doFindAllQuery(CalendarRow.TYPE);
            boolean l_blnIsTrue = false;
            for (int i = 0; i < l_lisResults.size(); i++)
            {
                l_calendarRow = (CalendarRow)l_lisResults.get(i);
                if (WEB3DateUtility.compareToDay(l_calendarRow.getHoliday(), l_calendarBizDate.getTime()) == 0)
                {
                    l_blnIsTrue = true;
                    break;
                }
            }

            if (!l_blnIsTrue)
            {
                l_calendarRow = null;
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBサーバとの通信に失敗した", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@this.get営業日区分()をコールし、前日の営業日区分を取得する。
        Date l_datDateBefore = WEB3DateUtility.addDay(l_tsDate, -1);
        String l_strBizDateTypeBefore = getBizDateType(new Timestamp(l_datDateBefore.getTime()));

        //４）　@１）で取得した曜日が土曜日の場合、もしくは、２）にて該当データが取得できた場合
        if (l_calendarBizDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
            || l_calendarRow != null)
        {
            //４－１）　@３）で取得した前日の営業日区分が”終日営業日”の場合
            //”終日営業日（午前のみ）”を返却する。
            if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateTypeBefore))
            {
                l_strPTSBizDateType = WEB3BizDateTypeDef.ALL_BIZ_DATE_AM;
            }
            else if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateTypeBefore))
            {
                //４－２）　@３）で取得した前日の営業日区分が”非営業日”または”半日営業日”の場合
                //”非営業日”を返却する。
                l_strPTSBizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
            }
        }
        else
        {
            if (WEB3BizDateTypeDef.BIZ_DATE.equals(l_strBizDateTypeBefore))
            {
                //５－１）　@３）で取得した前日の営業日区分が”終日営業日”の場合
                //”終日営業日”を返却する。
                l_strPTSBizDateType = WEB3BizDateTypeDef.BIZ_DATE;
            }
            else if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_AM.equals(l_strBizDateTypeBefore)
                || WEB3BizDateTypeDef.BIZ_DATE_PM.equals(l_strBizDateTypeBefore))
            {
                //５－２）　@３）で取得した前日の営業日区分が”非営業日”または”半日営業日”の場合
                //”終日営業日（午後のみ）”を返却する。
                l_strPTSBizDateType = WEB3BizDateTypeDef.ALL_BIZ_DATE_PM;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPTSBizDateType;
    }

    /**
     * validate注文受付ステイタス<BR>
     * 注文受付可能かをチェックする。 <BR>
     * <BR>
     * １）　@緊急停止、バッチ中チェック <BR>
     * 注文受付ステイタステーブルを取引カレンダコンテキストの内容で読み、 <BR>
     * 取得した行の注文受付ステイタスが”通常”でない場合は例外をスローする。 <BR>
     * <BR>
     * （注文受付不可のステイタスには、”バッチ処理中”、”緊急停止中”があり、例外メッセージをわける）<BR>
     * -バッチ処理中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00011<BR>
     * -システム緊急停止中-<BR>
     * class    : WEB3BusinessLayerException<BR>
     * tag      : BUSINESS_ERROR_00012<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4014CD6801CF
     */
    public static void validateOrderAcceptStatus() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderAcceptStatus()";
        log.entering(STR_METHOD_NAME);

        //取引時間コンテキストの取得
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);

        String l_strInstitutionCode;
        String l_strBranchCode;
        String l_strOrderAccProduct;
        String l_strOrderAccTransaction;
        String l_strOrderAcceptStatus;

        //証券会社コード
        l_strInstitutionCode = l_clendarContext.getInstitutionCode().trim();
        //部店コード
        l_strBranchCode = l_clendarContext.getBranchCode();
        //注文受付商品
        l_strOrderAccProduct = l_clendarContext.getOrderAcceptProduct();
        //注文受付トランザクション
        l_strOrderAccTransaction = l_clendarContext.getOrderAcceptTransaction();

        //取得したコンテキストの以下の項目にnullが格納されていた場合は、
        //例外をスローする。
        //   取引時間コンテキスト.証券会社コード
        // 　@取引時間コンテキスト.部店コード
        if ((l_strInstitutionCode == null)
            || (l_strBranchCode == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                WEB3GentradeTradingTimeManagement.class.getName()
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
        catch (DataFindException l_dfe)
        {
            l_strOrderAcceptStatus = WEB3OrderAcceptStatusDef.NORMAL;
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        if (WEB3OrderAcceptStatusDef.BATCH.equals(l_strOrderAcceptStatus))
        {
            // バッチ処理中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00011,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }
        else if (WEB3OrderAcceptStatusDef.SCRAM.equals(l_strOrderAcceptStatus))
        {
            // 緊急停止中
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "."
                    + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
