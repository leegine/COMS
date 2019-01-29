head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminIfoExecuteReferenceServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品管理(株式)サービスインタセプタ(WEB3AdminIfoExecuteReferenceServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;

import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;

import webbroker3.adminorderexecinquiry.define.WEB3AdminOrderAcceptProductDef;

/**
 * （管理者先物OP注文約定照会サービスインタセプタ）<BR>
 * <BR>
 * 管理者先物OP注文約定照会サービスインタセプタ <BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminIfoExecuteReferenceServiceInterceptor<BR>
 * <BR>
 *  WEB3AdminIfoExecuteReferenceServiceInterceptor class<BR>
 * <BR>
 * @@author Anupama
 * @@version 1.0
 */
public class WEB3AdminIfoExecuteReferenceServiceInterceptor implements Interceptor
{

   /**
    * WEB3LogUtility log<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoExecuteReferenceServiceInterceptor.class);

    /**
     * @@roseuid 41FD8E5F001F
     */
    public WEB3AdminIfoExecuteReferenceServiceInterceptor()
    {
    }

    /**
     * (onCall)<BR>
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
     * 　@取引カレンダコンテキスト.受付時間区分 = ”11：株価指数先物OP” <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”00：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”07：照会”<BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@　@　@取引時間コンテキストをセットする。<BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。<BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * ---<English>----------------<BR>
     * <BR>
     * When the service method begins, it is called.<BR>
     * <BR>
     * Create WEB3GentradeTradingClendarContext that the trade calender uses.<BR>
     * (It is called from the xTrade kernel before service is executed. )<BR>
     * <BR>
     * 1)Set the content to WEB3GentradeTradingClendarContext. <BR>
     * 　@－Set the properties of WEB3GentradeTradingClendarContext from the content of
     * OpLoginSecurityService. <BR>
     * <BR>
     * 　@WEB3GentradeTradingClendarContext.InstitutionCode = Edit context from
     * OpLoginSecurityService.<BR>
     * 　@WEB3GentradeTradingClendarContext.branchCode = Edit from
     * OpLoginSecurityService.<BR>
     *   WEB3GentradeTradingClendarContext.marketCode = 0: Def.DEFAULT <BR>
     * 　@WEB3GentradeTradingClendarContext.tradingTimeType = 11:
     * Def.INDEX_FUTURE_OP<BR>
     * 　@WEB3GentradeTradingClendarContext.productCode = 0: Def.DEFAULT<BR>
     * 　@WEB3GentradeTradingClendarContext.orderAcceptProduct = 00:Def.DEFAULT<BR>
     * 　@WEB3GentradeTradingClendarContext.orderAcceptTransaction =
     * 07:Def.REFERENCE<BR>
     * <BR>
     * 　@－Set WEB3GentradeTradingClendarContext at
     * ThreadLocalSystemAttributesRegistry.setAttribute( ). <BR>
     * Set key: WEB3GentradeTradingTimeManagement,Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * 2)Set dateRoll and orderDate. <BR>
     * 　@－Call WEB3GentradeTradingTimeManagement.setTimestamp().<BR>
     * <BR>
     * @@param l_method - (arg0)<BR>
     * <BR>
     * l_method<BR>
     * <BR>
     * @@param l_serviceParams - (arg1)<BR>
     * <BR>
     * l_serviceParams<BR>
     * <BR>
     * @@return java.lang.Object
     * @@roseuid 41AE9B4700C6
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;

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
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
        l_context.setProductCode(null);
        l_context.setOrderAcceptProduct(WEB3AdminOrderAcceptProductDef.DEFAULT);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }

    /**
     * (onReturn)<BR>
     * <BR>
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
     * When the service method ends, it is called. <BR>
     * WEB3GentradeTradingClendarContext clear process<BR>
     * <BR>
     * Clear the following contents of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context - (arg0)<BR>
     * <BR>
     * l_context<BR>
     * <BR>
     * @@param l_returnValue - (arg1)<BR>
     * <BR>
     * l_returnValue<BR>
     * <BR>
     * @@roseuid 41AE9B4700E5
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (onThrowable)<BR>
     * <BR>
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
     * Clear the following contents of ThreadLocalSystemAttributesRegistry.<BR>
     * <BR>
     * WEB3GentradeTradingTimeManagement.Def.TIMESTAMP_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.OFFSET_TAG<BR>
     * WEB3GentradeTradingTimeManagement.Def.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_obj - (arg0)<BR>
     * <BR>
     * l_obj<BR>
     * <BR>
     * @@param l_throwable - (arg1)<BR>
     * <BR>
     * l_throwable<BR>
     * <BR>
     * @@roseuid 41AE9B4700F5
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {

        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);

    }

}
@
