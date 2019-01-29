head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradingTimeManagement.java;


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
Revesion History : 2005/11/16 戸倉 (SRA) 新規作成

*/
package webbroker3.feq;

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
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusDao;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

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
public class WEB3FeqTradingTimeManagement
         extends WEB3GentradeTradingTimeManagement
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
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqTradingTimeManagement.class);    

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
     *  －取得した受付日時の曜日を取得し、土曜日または日曜日の場合は”非営業日”。<BR>
     * 　@以外の場合、カレンダテーブルを受付日時の日付部分で検索し、<BR>
     * 行の営業日区分を取得する。行が取得できなかった場合は、営業日<BR>
     * 区分を”通常営業日”とする。<BR>
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

        
        //海外営業日チェック
		//３）で取得したレコードを元に以下の条件で外株海外市場カレンダーテーブルを検索し、
		//該当レコードが取得できた場合は注文受付不可とし、例外をスローする。
		//[検索条件]
		//証券会社コード = 取引時間テーブル.証券会社コード
		//市場コード = 取引時間テーブル.市場コード
		//日付 = (以下のとおり)
		//	取引時間テーブル.発注日計算 == "当日"の場合、
		//		ThreadLocalSystemAttributesRegistryより取得した受付日時の日付部分
		//	取引時間テーブル.発注日計算 == "翌営業日"の場合、
		//		ThreadLocalSystemAttributesRegistryより取得した受付日時の国内翌営業日
		//営業日区分 = "非営業日"
		//
		//※３）で取得したレコードが複数ある場合は、それぞれのレコードについて処理を行い、
		//１件でも該当レコードが存在しない場合は注文受付可とする。
		
		String l_strBizDate = "";
		String l_strBizDateCalc;
		Date l_bizDate;
		WEB3GentradeBizDate l_dateCalc =
					new WEB3GentradeBizDate(l_tsOrderAcceptDate);
		for (int i = 0; i < l_intLength; i++)
		{
			l_tradingTimeRow = (TradingTimeRow)l_lisRecords.get(i);
			//証券会社コード
			l_strInstitutionCode = l_tradingTimeRow.getInstitutionCode();
			//市場コード
			l_strMarketCode = l_tradingTimeRow.getMarketCode();
			//日付
			l_strBizDateCalc = l_tradingTimeRow.getBizdateCalcParameter();
			if(WEB3BizDateCalcParameterDef.DAY_BIZ_DATE.equals(l_strBizDateCalc)){
				//取引時間テーブル.発注日計算 == "当日"の場合、
				//ThreadLocalSystemAttributesRegistryより取得した受付日時の日付部分
				SimpleDateFormat l_formatDate = GtlUtils.getThreadSafeSimpleDateFormat("yyyy/MM/dd");
				l_strBizDate = l_formatDate.format(l_tsOrderAcceptDate);
				
			}else if(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE.equals(l_strBizDateCalc)){
				//取引時間テーブル.発注日計算 == "翌営業日"の場合、
				//ThreadLocalSystemAttributesRegistryより取得した受付日時の国内翌営業日
				l_bizDate = l_dateCalc.roll(1);
				l_strBizDate = WEB3DateUtility.formatDate(l_bizDate,"yyyy/MM/dd");
			}
			//営業日区分
			l_bizDateType = WEB3BizDateTypeDef.NO_BIZ_DATE;
			
			//外株海外市場カレンダーテーブルを検索する
			l_sbWhere = new StringBuffer();
			l_sbWhere.append(" institution_code = ? ");
			l_sbWhere.append(" and market_code = ? ");
			l_sbWhere.append(" and biz_date = ? ");
			l_sbWhere.append(" and biz_date_type = ? ");

			ArrayList l_lstObjFeqCalendarWhere = new ArrayList();
			//証券会社コード
			l_lstObjFeqCalendarWhere.add(l_strInstitutionCode.trim());
			//市場コード
			l_lstObjFeqCalendarWhere.add(l_strMarketCode.trim());
			//日付
			l_lstObjFeqCalendarWhere.add(l_strBizDate.trim());
			//営業日区分
			l_lstObjFeqCalendarWhere.add(l_bizDateType);
			
			l_intSize = l_lstObjFeqCalendarWhere.size();
			Object[] l_objFeqCalendarWhere = new Object[l_intSize];
			for (int j = 0; j < l_intSize; j++)
			{
				l_objFeqCalendarWhere[j] = l_lstObjFeqCalendarWhere.get(j);
			}
			
			List l_lisRecordsTemp = null;
			try
			{
				QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
				l_lisRecordsTemp = l_QueryProcessor.doFindAllQuery(
					FeqCalendarRow.TYPE,
					l_sbWhere.toString(),
					l_objFeqCalendarWhere);
			}
			catch (DataFindException dfe)
			{
				log.error(STR_METHOD_NAME, dfe);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					WEB3FeqTradingTimeManagement.class.getName()
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
					WEB3FeqTradingTimeManagement.class.getName()
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
					WEB3FeqTradingTimeManagement.class.getName()
						+ "."
						+ STR_METHOD_NAME,
					dne.getMessage(),
					dne);
			}
			
			//１件でも該当レコードが存在しない場合は注文受付可とする。
			int l_intLengthTemp = l_lisRecordsTemp.size();
			if (l_intLengthTemp == 0)
			{
				log.debug(STR_METHOD_NAME + "：受付時間帯");
				log.exiting(STR_METHOD_NAME);
				return;

			}
		}
		
		throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02301,
				WEB3FeqTradingTimeManagement.class.getName()
					+ "."
					+ STR_METHOD_NAME,
				"該当市場が休業日の為、注文できません。");
				

    }
}@
