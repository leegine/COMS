head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioServiceIntercepter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオサービスインタセプタ(WEB3TrialCalcProfitLossCalServiceInterceptor.java)
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

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

/**
 * （計算サービスポートフォリオサービスインタセプタ）<BR>
 * <BR>
 * ポートフォリオサービスインタセプタクラス。<BR>
 * * ----<English>--------------------<BR>
 *
 * WEB3TrialCalcPortfolioServiceIntercepter<BR>
 * @@author Honnappa Manjula
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioServiceIntercepter implements Interceptor
{
    /**
      * ログ出力ユーティリティ 。 < BR >
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioServiceIntercepter.class);

    /**
      * l_strMethodName is a private variable.
      */
    private String strMethodName = "";

    /**
     * constructor
     */
    public WEB3TrialCalcPortfolioServiceIntercepter()
    {
    }

   /**
    * サービスメソッド開始時にコールされる。<BR>
    * <BR>
    * 取引カレンダが利用するコンテキストを生成する。<BR>
    * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
    * <BR>
    * １）　@取引カレンダコンテキストに内容をセットする。<BR>
    * 　@－OpLoginSecurityServiceの内容より<BR>
    * 　@　@取引時間コンテキストのプロパティをセットする。<BR>
    * <BR>
    * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
    * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
    * 　@取引カレンダコンテキスト.市場コード = null<BR>
    * 　@取引カレンダコンテキスト.受付時間区分 = "01：株式・信用"<BR>
    * 　@取引カレンダコンテキスト.銘柄コード = "0：DEFAULT"<BR>
    * 　@取引カレンダコンテキスト.注文受付商品 = "22：顧客サービス"<BR>
    * 　@取引カレンダコンテキスト.注文受付トランザクション = "07：照会"<BR>
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
    *   WEB3GentradeTradingClendarContext.marketCode  = null<BR>
    *   WEB3GentradeTradingClendarContext.tradingTimeType = '01'(Equity)<BR>
    *   WEB3GentradeTradingClendarContext.productCode = "0：DEFAULT"<BR>
    *   WEB3GentradeTradingClendarContext.orderAcceptProduct = '22'(Customer
    * Service)<BR>
    *   WEB3GentradeTradingClendarContext.orderAcceptTransaction  = '07'(Refer)<BR>
    * <BR>
    *  - Set dealings time context using
    * "ThreadLocalSystemAttributesRegistry.setAttribute( )"<BR>
    *    Set key : WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
    * <BR>
    * 2)　@Set orderDate and dateRoll<BR>
    *  - Call WEB3GentradeTradingTimeManagement.setTimestamp()<BR>
    * @@param l_method - サービスメソッドオブジェクト
    * @@param l_serviceParam 引数 - サービスメソッド引数
    * @@return java.lang.Object
    * @@roseuid 419338980002
    */
   public Object onCall(Method l_method, Object[] l_serviceParam)
   {
        strMethodName = "onCall(Method,Object[])";
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        String l_strMarketCode = null;
       long l_lngAccountId = 0;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        log.entering(strMethodName);

        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            MainAccount l_mainAccount;
            l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_mainAccount = l_accMgr.getMainAccount(l_lngAccountId);
            l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);

            l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
            l_context.setBranchCode(l_strBranchCode);

        } catch (NotFoundException l_ex)
        {
            log.error(
                l_ex.getMessage(),
                new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + strMethodName,
                    l_ex.toString(),
                    l_ex));
        }

        l_context.setMarketCode(null);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.ACCOUNT_SERVICE);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();

        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(strMethodName, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + strMethodName,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(strMethodName);

        // to be  seen during testing iof null has to be returned or an object i.e l_context
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
    * <BR>
    * @@param l_objOnCallReturnValue - onCallリターン値
    * @@param l_objServiceMethodReturnValue 値 - サービスメソッドリターン値
    * @@roseuid 419338980005
    */
   public void onReturn(Object l_objOnCallReturnValue, Object l_objServiceMethodReturnValue)
   {
        strMethodName = "onReturn(Object,Object)";
        log.entering(strMethodName);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

         log.exiting(strMethodName);
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
    * @@param l_objOnCallReturnValue - onCallリターン値
    * @@param l_throwable - 例外オブジェクト
    * @@roseuid 419338980008
    */
   public void onThrowable(Object l_objOnCallReturnValue, Throwable l_throwable)
   {
       strMethodName = "onThrowable(Object,Throwable)";
               log.entering(strMethodName);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
           null);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
           null);

       ThreadLocalSystemAttributesRegistry.setAttribute(
           WEB3GentradeTradingTimeManagement.OFFSET_TAG,
           null);

       log.exiting(strMethodName);

   }
}
@
