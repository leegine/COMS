head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoMRFCancelAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF取消受付UnitServiceインタセプタクラス(WEB3RuitoMRFCancelAcceptUnitServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * 累積投資MRF取消受付UnitServiceインタセプタクラス<BR>
 */
public class WEB3RuitoMRFCancelAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptUnitServiceInterceptor.class);

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@ －引数.サービスの引数[0]を累投注文単位オブジェクト<BR>
     *     にキャストする。<BR>
     * <BR>
     * 　@  －累投注文単位.getDataSourceObject().getMRF注文識別コード()<BR>
     *      の値と等しい識別コードの累投注文単位オブジェクトを取得する。<BR>
     * 　@　@拡張累投注文マネージャ.get注文単位()をコールして、<BR>
     *     累投注文単位オブジェクトを取得する。<BR>
     * 　@　@[get注文単位に渡すパラメタ]<BR>
     * 　@　@補助口座ID： 累投注文単位.getSubAccountId()の戻り値<BR>
     * 　@　@識別コード： <BR>
     *    累投注文単位.getDataSourceObject().getMRF注文識別コード()の戻り値<BR>
     * <BR>
     * 　@－部店オブジェクトの取得<BR>
     * 　@　@アカウントマネージャ.getBranch()を<BR>
     *     コールして部店オブジェクトを取得する。<BR>
     * 　@　@[getBranchに渡すパラメタ]<BR>
     * 　@　@部店ID： <BR>
     *     取得した累投注文単位.getBranchId()の戻り値<BR>
     * <BR>
     * 　@  －証券会社オブジェクトの取得<BR>
     * 　@　@取得した<BR>
     *      部店オブジェクト.getInstitution()をコールして<BR>
     *      証券会社オブジェクトを取得する。<BR>
     * <BR>
     * 　@   －取得した累投注文単位オブジェクト.getDataSourceObject()をコールし、<BR>
     *      累投注文単位Paramsを取得する。<BR>
     * <BR>
     * 　@   －注文受付トランザクションを取得する。<BR>
     * 　@　@(*)累投注文単位Params.getOrderType<BR>
     *        ==  OrderTypeEnum.RUITO_BUYの場合、<BR>
     *      注文受付トランザクションの値は”01：買付（新規建買）”<BR>
     * 　@　@(*)累投注文単位Params.getOrderType<BR>
     *         == OrderTypeEnum.RUITO_SELLの場合、<BR>
     *      注文受付トランザクションの値は”02：売付（新規建売）”<BR>
     * <BR>
     *    　@－受付時間区分を取得する。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値が<BR>
     *         RuitoTypeEnum.中期国債ファ@ンドの場合、<BR>
     * 　@　@ 　@受付時間区分の値はWEB3TradingTimeTypeDef.中国F。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値が<BR>
     *          RuitoTypeEnum.MMFで注文受付トランザクションの値が<BR>
     *           ”01：買付（新規建買）”の場合、<BR>
     * 　@　@  　@受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定）。<BR>
     * 　@　@(*) 累投注文単位Params.get累投タイプ()の値が<BR>
     *          RuitoTypeEnum.MMFで注文受付トランザクションの値が<BR>
     *          ”02：売付（新規建売）”の場合、<BR>
     * 　@　@  　@受付時間区分の値はWEB3TradingTimeTypeDef.MMF（設定解約）。<BR>
     * <BR>
     * 　@     －取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@　@　@取引カレンダコンテキスト.証券会社コード<BR>
     *          = 取得した証券会社オブジェクト.getInstitutionCode()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.部店コード<BR>
     *          = 取得した部店オブジェクト.getBranchCode()の戻り値<BR>
     * 　@　@　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT”<BR>
     * 　@　@　@取引カレンダコンテキスト.受付時間区分 = 取得した受付時間区分<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付商品 = ”08：累積投資”<BR>
     * 　@　@　@取引カレンダコンテキスト.注文受付トランザクション<BR>
     *          = 取得した注文受付トランザクション<BR>
     * <BR>
     * 　@    －ThreadLocalSystemAttributesRegistry.setAttribute( )<BR>
     *        にて取引時間コンテキストをセットする。<BR>
     * 　@  　@［setAttributeに渡すパラメタ］<BR>
     * 　@　@　@設定キー： 取引時間コンテキスト.TRADING_CALENDAR_TAG<BR>
     * 　@　@　@設定値： 取引時間コンテキスト<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@   －取引時間管理.setTimestamp()をコールする。<BR>
     * ３）　@口座をロックする。 <BR>
     * 　@－拡張アカウントマネージャ.lock口座( <BR>
     *      証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * ※引数はMRF注文取消キューParamsより編集。 <BR>
     * <BR>
     * @@param l_method - サービスメソッドオブジェクト<BR>
     * @@param l_serviceParam - サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 405812020263
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        final String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        if (l_serviceParam.length == 0)
        {
            log.debug("パラメータSizeは０できない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータSizeは０できない");
        }

        //－累投注文単位.getDataSourceObject().getMRF注文識別コード()
        RuitoOrderUnit l_ruitoOrderUnit = (RuitoOrderUnit) l_serviceParam[0];
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        
        String l_strMrfOrderRequestNumber = 
            l_ruitoOrderUnitRow.getMrfOrderRequestNumber();
      
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3RuitoOrderManager l_ruitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getOrderManager();

        Branch l_branch = null;
        Institution l_institution = null;
        AccountManager l_accMgr = null;
        RuitoOrderUnitParams l_ruitoOrderUnitParams = null;
        WEB3GentradeTradingClendarContext l_context = null;
        try
        {
            //拡張累投注文マネージャ.get注文単位()をコールして、
            //累投注文単位オブジェクトを取得する。
            log.debug("l_ruitoOrderUnit.accountId = " + l_ruitoOrderUnit.getAccountId());
            log.debug("l_ruitoOrderUnit.subAccountId = " + l_ruitoOrderUnit.getSubAccountId());
            log.debug("MrfOrderRequestNumber = " + l_strMrfOrderRequestNumber);
            l_ruitoOrderUnit = l_ruitoOrderManager.getRuitoOrderUnit(
                l_ruitoOrderUnit.getAccountId(),
                l_ruitoOrderUnit.getSubAccountId(),
                l_strMrfOrderRequestNumber);
               
            l_accMgr = l_finApp.getAccountManager();
            //証券会社・部店オブジェクト取得 NotFoundException
            l_branch = l_accMgr.getBranch(
                l_ruitoOrderUnit.getBranchId());
            log.debug("部店Id = " + l_ruitoOrderUnit.getBranchId());
            log.debug("部店Code = " + l_branch.getBranchCode());
            l_institution = l_branch.getInstitution();
            log.debug("証券会社Id = " + l_institution.getInstitutionId());
            log.debug("証券会社Code = " + l_institution.getInstitutionCode());

            //－注文受付トランザクションを取得する。
            String l_strOrderAcceptTransaction = null;
            l_ruitoOrderUnitParams =
                (RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject();
                
            log.debug("ruitoOrderUnit.OrderType = " + l_ruitoOrderUnitParams.getOrderType());
            
            if (OrderTypeEnum.RUITO_BUY.equals(
                l_ruitoOrderUnitParams.getOrderType()))
            {
                log.debug("ruitoOrderUnit.OrderType = 501");
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN;
                log.debug("OrderAcceptTransaction = " + l_strOrderAcceptTransaction);
            }
            else if (OrderTypeEnum.RUITO_SELL.equals(
                l_ruitoOrderUnitParams.getOrderType()))
            {
                log.debug("ruitoOrderUnit.OrderType = 502");
                l_strOrderAcceptTransaction = WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN;
                log.debug("OrderAcceptTransaction = " + l_strOrderAcceptTransaction);
            }

            //－受付時間区分を取得する。

            String l_strAcceptTimeDiv = null;
            int l_intRuitoType = l_ruitoOrderUnitParams.getRuitoType().intValue();

            if (l_intRuitoType == RuitoTypeEnum.CHUUKOKU_FUND.intValue())
            {
                log.debug("RuitoType = 2");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND;
                log.debug("受付時間区分:AcceptTimeDiv = " + 
                        WEB3TradingTimeTypeDef.MIDIUM_TERM_GOV_FUND);
            }
            else if (l_intRuitoType == RuitoTypeEnum.MMF.intValue() && 
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN.equals(l_strOrderAcceptTransaction))
            {
                log.debug("RuitoType = 1");
                log.debug("OrderAcceptTransaction = 01");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET;
                log.debug("受付時間区分:AcceptTimeDiv = " + 
                        WEB3TradingTimeTypeDef.MMF_SET);
            }
            else if (l_intRuitoType == RuitoTypeEnum.MMF.intValue() && 
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN.equals(l_strOrderAcceptTransaction))
            {
                log.debug("RuitoType = 1");
                log.debug("OrderAcceptTransaction = 02");
                l_strAcceptTimeDiv = WEB3TradingTimeTypeDef.MMF_SET_CANCEL;
                log.debug("受付時間区分:AcceptTimeDiv = " + 
                        WEB3TradingTimeTypeDef.MMF_SET_CANCEL);
            }
            l_context = new WEB3GentradeTradingClendarContext();
            //－取引時間コンテキストのプロパティをセットする。
            //取引カレンダコンテキスト.証券会社コード
            l_context.setInstitutionCode(l_institution.getInstitutionCode());
            //取引カレンダコンテキスト.部店コード
            l_context.setBranchCode(l_branch.getBranchCode());
            //取引カレンダコンテキスト.市場コード
            l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            //取引カレンダコンテキスト.銘柄コード
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
            //取引カレンダコンテキスト.受付時間区分
            l_context.setTradingTimeType(l_strAcceptTimeDiv);
            //取引カレンダコンテキスト.注文受付商品
            l_context.setOrderAcceptProduct(
                WEB3OrderAccProductDef.MONTHLY_INVESTMENT_PLAN);
            //取引カレンダコンテキスト.注文受付トランザクション
            l_context.setOrderAcceptTransaction(l_strOrderAcceptTransaction);

            //２）　@受付日時、日付ロールをセットする。
            // 取引時間コンテキストをセットする
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            log.debug("取引時間コンテキストのプロパティをセットする end.");
            //WEB3BaseException
            WEB3GentradeTradingTimeManagement.setTimestamp(); //取引時間管理
            
            String l_strAccountCode = l_accMgr.getMainAccount(
                    l_ruitoOrderUnitParams.getAccountId()).getAccountCode();
            
            //３）　@口座をロックする。 
            WEB3GentradeAccountManager l_gentradeAccMgr = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();       
            l_gentradeAccMgr.lockAccount(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("__NotFoundException__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("__WEB3GentradeTradingTimeManagement.setTimestamp Error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        log.exiting(STR_METHOD_NAME);
        return l_context;

    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの<BR>
     *     以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 405812020273
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistryの<BR>
     *     以下の内容をクリアする。<BR>
     * <BR>
     * 　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@取引時間管理.OFFSET_TAG<BR>
     * 　@取引カレンダコンテキスト.TRADING_CALENDAR_TAG<BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 405812020292
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
    }
}
@
