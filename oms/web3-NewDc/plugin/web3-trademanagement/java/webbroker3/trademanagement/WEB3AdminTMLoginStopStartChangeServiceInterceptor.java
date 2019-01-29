head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginStopStartChangeServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ログイン停止再開変更サービスインタセプタ(WEB3AdminTMLoginStopStartChangeServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.trademanagement.define.WEB3AdminTMLoginStopStartOrderAcceptProductDef;

/**
* (管理者ログイン停止再開変更サービスインタセプタ)<BR>
* <BR>
* 管理者ログイン停止再開変更サービスインタセプタ クラス<BR>
* <BR>
* WEB3AdminTMLoginStopStartChangeServiceInterceptor<BR>
* <BR>
* WEB3AdminTMLoginStopStartChangeServiceInterceptor class<BR>
* <BR>
* @@author Umadevi
* @@version 1.0
*/

public class WEB3AdminTMLoginStopStartChangeServiceInterceptor implements Interceptor
{
    /**
    * ログ出力ユーティリティ。<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginStopStartChangeServiceInterceptor.class);
    /**
     * @@roseuid 41DD41190214
     */
    public WEB3AdminTMLoginStopStartChangeServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR>
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR>
     * 　@－OpLoginSecurityServiceの内容より取引時間コンテキストの<BR>
     * 　@　@　@プロパティをセットする。<BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.部店コード = OpLoginSecurityServiceより編集<BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.受付時間区分 = ”00：その他” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”00：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     *   設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * -----<English>---------------------------<BR>
     * <BR>
     * When the service method begins, it is called.<BR>
     * <BR>
     * Generate WEB3GentradeTradingClendarContext that the trade calender uses.<BR>
     * (It is called from the xTrade kernel  before service is executed. )<BR>
     * <BR>
     * １）　@Set the content to WEB3GentradeTradingClendarContext. <BR>
     * 　@－Set the property of WEB3GentradeTradingClendarContext from the content of
     * OpLoginSecurityService. <BR>
     * <BR>
     * 　@WEB3GentradeTradingClendarContext.InstitutionCode = Edit context from
     * OpLoginSecurityService.<BR>
     * 　@WEB3GentradeTradingClendarContext.branchCode =  Edit from
     * OpLoginSecurityService.<BR>
     *   WEB3GentradeTradingClendarContext.marketCode = ”0：Def.DEFAULT” <BR>
     * 　@WEB3GentradeTradingClendarContext.tradingTimeType = ”00：Def.DEFALT” <BR>
     * 　@WEB3GentradeTradingClendarContext.productCode = ”0：Def.DEFAULT” <BR>
     * 　@WEB3GentradeTradingClendarContext.orderAcceptProduct = ”00：Def.DEFAULT”
     * <BR>
     * 　@WEB3GentradeTradingClendarContext.orderAcceptTransaction =
     * ”07：Def.REFERENCE”<BR>
     * <BR>
     * 　@－Dealings time context is set with
     * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
     *      Set key： WEB3GentradeTradingTimeManagement,TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@Set the date roll and the reception date. <BR>
     * 　@－Call  WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
     * <BR>
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@return java.lang.Object
     * @@roseuid 417DF7A301C2
     */
    public Object onCall(java.lang.reflect.Method l_arg0, java.lang.Object[] l_arg1)
    {
        String STR_METHOD_NAME = "onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        try
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            log.debug("getService(OpLoginSecurityService.class): " + l_opLoginSec);

            long l_lngInstitutionId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.INSTITUTION_ID));
            long l_lngBranchId =
                Long.parseLong(l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.BRANCH_ID));
                
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            l_strInstitutionCode = l_accMgr.getInstitution(l_lngInstitutionId).getInstitutionCode();
            l_context.setInstitutionCode(l_strInstitutionCode);

            l_strBranchCode = l_accMgr.getBranch(l_lngBranchId).getBranchCode();
            l_context.setBranchCode(l_strBranchCode);
        } catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.toString());
        }
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.DEFAULT);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3AdminTMLoginStopStartOrderAcceptProductDef.DEFAULT);

        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        } catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
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
     * ----<English>--------------<BR>
     * <BR>
     * When  the service method ends, it is called. <BR>
     * WEB3GentradeTradingClendarContext clear process<BR>
     * <BR>
     * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@roseuid 417DF7A301E1
     */
    public void onReturn(java.lang.Object l_arg0, java.lang.Object l_arg1)
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
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG<BR>
     * 取引時間管理.OFFSET_TAG<BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * -----<English>-----------------------<BR>
     * <BR>
     * When the service method ends, it is called.<BR>
     * WEB3GentradeTradingClendarContext clear process.<BR>
     * <BR>
     * Clear the following content of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_arg0 Method
     * @@param l_arg1 Object
     * @@roseuid 417DF7A30201
     */
    public void onThrowable(java.lang.Object l_arg0, java.lang.Throwable l_arg1)
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
