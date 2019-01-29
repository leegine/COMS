head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算サービスインタセプタ(WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.trialcalc.define.WEB3TrialCalcEquityMiniDivDef;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointRequest;

/**
 * （計算サービス損益分岐点計算サービスインタセプタ）<BR>
 * <BR>
 * 損益分岐点計算サービスインタセプタクラス。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * Break-even point calculation service interceptor class. <BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor implements Interceptor
{
    /**
    * ログ出力ユーティリティ。<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor.class);

    /**
     * @@roseuid 41C81572004F
     */
    public WEB3TrialCalcProfitLossBreakEvenPointCalServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－サービスの引数[0]をリクエストデータオブジェクトにキャストする。<BR>
     * 　@－リクエストデータの内容と、OpLoginSecurityServiceの内容より<BR>
     * 　@　@取引時間コンテキストのプロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = (*1)<BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = (*2)<BR>
     * 　@取引カレンダコンテキスト.銘柄コード = "0：DEFAULT"<BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”22：顧客サービス”<BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = "07：照会"<BR>
     * <BR>
     * 　@(*1)リクエストデータの型＝「計算サービス損益分岐点計算入力リクエスト」の場合は
     * 、null。<BR>
     * 　@　@　@　@リクエストデータの型＝「計算サービス損益分岐点計算リクエスト」の場合は、
     * <BR>
     * 　@　@　@　@　@リクエストデータ.現物／ミニ株区分＝"現物株式"の場合は、"1：東京"。<BR>
     * 　@　@　@　@　@　@（東京市場を基準とした発注日で手数料を求めるため、"東京"を固定でセッ
     * ト。）<BR>
     * 　@　@　@　@　@リクエストデータ.現物／ミニ株区分＝"ミニ株"の場合は、"0：DEFAULT"。<BR
     * >
     * <BR>
     * 　@(*2)
     * リクエストデータの型＝「計算サービス損益分岐点計算入力リクエスト」の場合は、"01?
     * 株式・信用"。<BR>
     * 　@　@　@　@リクエストデータの型＝「計算サービス損益分岐点計算リクエスト」の場合は、
     * <BR>
     * 　@　@　@　@　@リクエストデータ.現物／ミニ株区分＝"現物株式"の場合は、"01：株式・信用
     * "。<BR>
     * 　@　@　@　@　@リクエストデータ.現物／ミニ株区分＝"ミニ株"の場合は、"05：株式ミニ投資
     * "。<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Call when the service method starts.<BR>
     * （It is called before service is executed from the xTrade kernel. ）<BR>
     * <BR>
     * 1) Set values in WEB3GentradeTradingClendarContext<BR>
     * <BR>
     *   - Cast the argument[0] of service　@request to the data object<BR>
     *   - Set the property of "dealings time context" from the request data and
     * OpLoginSecurityService<BR>
     * <BR>
     *   Edit from WEB3GentradeTradingClendarContext.institutionCode =
     * OpLoginSecurityService<BR>
     *   Edit from WEB3GentradeTradingClendarContext.branchCode =
     * OpLoginSecurityService<BR>
     *   WEB3GentradeTradingClendarContext.marketCode  = (*1)<BR>
     *   WEB3GentradeTradingClendarContext.tradingTimeType = (*2)<BR>
     *   WEB3GentradeTradingClendarContext.productCode = "0：DEFAULT"<BR>
     *   WEB3GentradeTradingClendarContext.orderAcceptProduct = '22'(Customer
     * Service)<BR>
     *   WEB3GentradeTradingClendarContext.orderAcceptTransaction  = '07'(Refer)<BR>
     * <BR>
     * (*1) If "Type of request data = [WEB3TrialCalcProfitLossCalcInputRequest]" ->
     * null<BR>
     *          If "Type of request data = [WEB3TrialCalcProfitLossCalcRequest]<BR>
     *            If "l_request.equityMiniDiv ='equity' -> '1'(TOKYO)<BR>
     *             （Because the commission is requested at the order date based on
     * the Tokyo market,<BR>
     *               "Tokyo" is set by fixation. ）<BR>
     *            If "l_request.equityMiniDiv = 'Mini stock division' ->
     * '0'(DEFAULT)<BR>
     * <BR>
     * (*2) If "Type of request data = [WEB3TrialCalcProfitLossCalcInputRequest] ->
     * '01'(equity/trust)<BR>
     *        If "Type of request data = [WEB3TrialCalcProfitLossCalcRequest]<BR>
     *          If "l_request.equityMiniDiv ='equity'"  -> '01'(equity/trust)<BR>
     *          If "l_request.equityMiniDiv = 'Mini stock division' '05'(The stocks
     * mini-investment)<BR>
     *                                              -> '02'(The stocks
     * mini-investment)<BR>
     * <BR>
     *  - Set dealings time context using
     * "ThreadLocalSystemAttributesRegistry.setAttribute( )"<BR>
     *    Set key : WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 2)　@Set orderDate and dateRoll<BR>
     *  - Call WEB3GentradeTradingTimeManagement.setTimestamp()<BR>
     * @@param l_method - java.lang.reflect.Method
     * @@param l_serviceParam java.lang.Object
     * @@return java.lang.Object
     * @@roseuid 419085A9035F
     */
    public Object onCall(Method l_method, Object[] l_serviceParam)
    {
        String l_methodName = "onCall(Method,Object[])";
             log.entering(l_methodName);
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        Object l_request = l_serviceParam[0];
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        try
        {
            OpLoginSecurityService l_opLoginSec =
                 (OpLoginSecurityService) Services.getService (OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            MainAccount l_mainAccount;
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)
                                                                    l_finApp.getAccountManager();
            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);
            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            log.error(
            l_ex.getMessage(), new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
            this.getClass().getName() + "." + l_methodName,
            l_ex.toString(),
            l_ex));
        }
        if (l_request instanceof WEB3TrialCalcBreakEvenPointInputRequest)
        {
            l_context.setMarketCode(null);
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        } else if (l_request instanceof WEB3TrialCalcBreakEvenPointRequest)
        {
            WEB3TrialCalcBreakEvenPointRequest l_profitLossRequest =
                                    ((WEB3TrialCalcBreakEvenPointRequest) l_request);
            if (l_profitLossRequest.equityMiniDiv.equals(WEB3TrialCalcEquityMiniDivDef.EQUITY))
            {
                l_context.setMarketCode(WEB3MarketCodeDef.TOKYO);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            } else
            {
                l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.MINI_STOCK);
            }
        } else
        {
            String l_strMethodName = null;
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName,
                "INPUT リクエスト NOT 損益分岐点計算リクエスト");
        }
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (WEB3BaseException l_ex)
        {
            log.error(l_methodName, l_ex);
            throw new WEB3BaseRuntimeException(l_ex.getErrorInfo(),
                    this.getClass().getName() + "." + l_methodName, l_ex.getMessage(), l_ex);
        }
        log.exiting(l_methodName);
        return (Object) l_context;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method ends, it is called. <BR>
     * Dealings calendar context clearn process. <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue Object
     * @@param l_objServiceMethodReturnValue Object
     * @@roseuid 419085A90362
     */
    public void onReturn(Object l_objOnCallReturnValue, Object l_objServiceMethodReturnValue)
    {
        String l_strMethodName = "onReturn(Object,Object)";
        log.entering(l_strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(l_strMethodName);

    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * When the service method throws out an exception, it is called.  <BR>
     * <BR>
     * Clear the next areas of ThreadLocalSystemAttributesRegistry<BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_objOnCallReturnValue Object
     * @@param l_throwable Throwable
     * @@roseuid 419085A90365
     */
    public void onThrowable(Object l_objOnCallReturnValue, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object,Throwable)";
        log.entering(l_strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        log.exiting(l_strMethodName);
    }
}
@
