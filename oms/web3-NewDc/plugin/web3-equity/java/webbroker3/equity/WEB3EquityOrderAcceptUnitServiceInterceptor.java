head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付一件サービスインタセプタ(WEB3EquityOrderAcceptUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 鄒政 (中訊) 新規作成
Revesion History : 2004/10/22 法@旭 修正    
Revesion History : 2004/12/15 森川 (SRA)  残案件対応
Revesion History : 2005/01/05 岡村和明 (SRA) JavaDoc修正
*/
package webbroker3.equity;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文受付一件サービスインタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptUnitServiceInterceptor implements Interceptor
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderAcceptUnitServiceInterceptor.class);

    /**
     * @@roseuid 4142B67C0206
     */
    public WEB3EquityOrderAcceptUnitServiceInterceptor()
    {

    }

    /**
     * (onCall)。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルより、サービスメソッド開始時にコールされる） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@?サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストする。 <BR>
     * 　@?株式注文受付キューParamsオブジェクトの内容より、 <BR>
     * 　@　@　@取引時間コンテキストのプロパティをセットする。 <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = 株式注文受付キューParamsの同項目 <BR>
     * 　@取引カレンダコンテキスト.部店コード = 株式注文受付キューParamsの同項目 <BR>
     * 　@取引カレンダコンテキスト.市場コード = null <BR>
     * 　@取引時間コンテキスト.受付時間区分 = ”株式・信用” <BR>
     * 　@取引時間コンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = null <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null <BR>
     * <BR>
     * 　@?ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR>
     * 　@取引時間コンテキストをセットする。 <BR>
     * 　@設定キー：取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR>
     * <BR>
     * ２）　@受付日時、日付ロールをセットする。 <BR>
     * 　@?取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)<BR>
     * 　@　@　@をコールする。 <BR>
     * 　@　@　@※引数は株式注文受付キューParamsより編集。 <BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - サービスメソッド引数<BR>
     * @@return Object<BR>
     * @@roseuid 41083B040364
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method , Object[])";
        log.entering(STR_METHOD_NAME);
        if(l_serviceParams[0] == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }        
        //１）　@取引カレンダコンテキストに内容をセットする       
        
        //サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストする。
        HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams = 
            (HostEqtypeOrderAcceptParams)l_serviceParams[0];
        
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //取引カレンダコンテキスト.証券会社コード =  株式注文受付キューParamsの同項目
        l_context.setInstitutionCode(l_hostEqtypeOrderAcceptParams.getInstitutionCode());
        
        //取引カレンダコンテキスト.部店コード = 株式注文受付キューParamsの同項目
        l_context.setBranchCode(l_hostEqtypeOrderAcceptParams.getBranchCode());
        
        //取引カレンダコンテキスト.市場コード = null
        l_context.setMarketCode(null);
        
        //取引時間コンテキスト.受付時間区分 = ”株式・信用”
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        
        //取引時間コンテキスト.銘柄コード = ”0：DEFAULT”
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.注文受付商品 = null
        l_context.setOrderAcceptProduct(null);
        
        //取引カレンダコンテキスト.注文受付トランザクション = null
        l_context.setOrderAcceptTransaction(null);
        
        //取引時間コンテキストをセットする
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
        
        try
        {
            //２）　@受付日時、日付ロールをセットする
            WEB3GentradeTradingTimeManagement.setTimestamp();
            
            //３）　@口座をロックする
            l_accountManager.lockAccount(
                l_hostEqtypeOrderAcceptParams.institution_code,
                l_hostEqtypeOrderAcceptParams.getBranchCode(),
                l_hostEqtypeOrderAcceptParams.getAccountCode());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);                  
        }

        
        log.exiting(STR_METHOD_NAME);
        return null;
        
    }

    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * 注文受付サービス終了時にコールされる。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_context - onCallリターン値<BR>
     * @@param l_returnValue - サービスメソッドリターン値<BR>
     * @@roseuid 41083B040367
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "OnReturn";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 取引カレンダコンテキストクリア処理。<BR>
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - onCallリターン値<BR>
     * @@param l_throwable - 例外オブジェクト<BR>
     * @@roseuid 41083B04036A<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable";
        log.entering(STR_METHOD_NAME);
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
        log.exiting(STR_METHOD_NAME);
    }
}
@
