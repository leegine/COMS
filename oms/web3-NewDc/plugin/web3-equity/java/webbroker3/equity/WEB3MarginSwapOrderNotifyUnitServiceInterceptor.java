head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文通知一件サービスインタセプタ(WEB3MarginSwapOrderNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 森川 (SRA) 新規作成
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
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyDataAdapter;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡注文通知一件サービスインタセプタ）。<BR>
 * <BR>
 * 信用取引現引現渡注文通知一件サービスインタセプタクラス
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyUnitServiceInterceptor implements Interceptor 
{ 
    /**
    * (ログユーティリティ)。<BR>
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyUnitServiceInterceptor.class);


    /**
     * (コンストラクタ)。
     */
    public WEB3MarginSwapOrderNotifyUnitServiceInterceptor() 
    {
    }


    /**
     * （onCall）。<BR>
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * （xTradeカーネルより、[信用取引現引現渡注文通知一件サービス]実行前に呼び出される） <BR>
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。 <BR>
     * 　@－サービスの引数[0]を信用取引現引現渡入力通知データアダプタにキャストする。 <BR>
     * <BR>
     * 　@－信用取引現引現渡入力通知データアダプタ.現引現渡入力通知キューParamsの内容より <BR>
     * 　@　@取引カレンダコンテキストのプロパティをセットする。 <BR>
     * 　@　@　@　@　@　@　@証券会社コード＝　@現引現渡入力通知キューParams.証券会社コード <BR>
     * 　@　@　@　@　@　@　@部店コード　@　@＝　@現引現渡入力通知キューParams.部店コード <BR>
     * 　@　@　@　@　@　@　@市場コード　@　@＝　@信用取引現引現渡入力通知データアダプタ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@.get市場().getMarketCode() <BR>
     * 　@　@　@　@　@　@　@受付時間区分  ＝　@"19：現引・現渡" <BR>
     * 　@　@　@　@　@　@　@銘柄コード　@　@＝　@"0：DEFAULT" <BR>
     * 　@　@　@　@　@　@　@注文受付商品　@＝　@"03：信用取引" <BR>
     * 　@　@　@　@　@　@　@注文受付トランザクション　@＝　@"04：現引・現渡" <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて <BR>
     * 　@　@　@取引カレンダコンテキストをセットする。 <BR>
     * 　@　@　@設定キー：　@取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * ２）　@受付日時をセットする。 <BR>
     * <BR>
     * ２－１）　@取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * ３）　@口座をロックする。 <BR>
     * <BR>
     * 　@　@　@拡張アカウントマネージャ.lock口座(証券会社コード, 部店コード, 口座コード)をコールする。 <BR>
     * 　@　@　@※引数は現引現渡入力通知キューParamsより編集。
     * @@param l_method （サービスメソッド）<BR>
     * サービスメソッドオブジェクト
     * @@param l_serviceParams サービスメソッド引数
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {  
        final String STR_METHOD_NAME = getClass().getName() + ".onCall(Method,Object[])";
        log.entering(STR_METHOD_NAME);

        //--------------------
        //サービスメソッド引数を現引現渡入力通知データアダプタにキャストする。
        //--------------------
        if (!(l_serviceParams[0] instanceof WEB3MarginSwapOrderNotifyDataAdapter))
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3MarginSwapOrderNotifyDataAdapter l_adapter
            = (WEB3MarginSwapOrderNotifyDataAdapter) l_serviceParams[0];
        HostEqtypeSwapReceiptParams l_swapReceiptParams = l_adapter.getDataSourseObject();


        //--------------------
        //現引現渡入力通知データアダプタから値を取得する。
        //--------------------
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        String      l_strInstitutionCode = null;    // 証券会社コード
        String      l_strBranchCode = null;         // 部店コード
        String      l_strMarketCode = null;         // 市場コード
        String      l_strAccountCode = null;        // 口座コード

        l_strInstitutionCode = l_swapReceiptParams.institution_code;
        l_strBranchCode = l_swapReceiptParams.branch_code;
        try
        {
            l_strMarketCode = l_adapter.getMarket().getMarketCode();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }
        l_strAccountCode = l_swapReceiptParams.getAccountCode();
        
        
        //--------------------
        //取引カレンダコンテキストに内容をセットする。
        //--------------------
        WEB3GentradeTradingClendarContext l_context
            = new WEB3GentradeTradingClendarContext();
            
        l_context.setInstitutionCode(l_strInstitutionCode);
        l_context.setBranchCode(l_strBranchCode);
        l_context.setMarketCode(l_strMarketCode);
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);


        //--------------------
        //受付日時をセットする。
        //--------------------
        try
        {
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (WEB3SystemLayerException l_wse)
        {
            throw new WEB3BaseRuntimeException(
                l_wse.getErrorInfo(),                
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_wse.getMessage(), l_wse);                  
        }


        //--------------------
        //口座をロックする
        //--------------------
        try
        {
            l_accountManager.lockAccount(
                l_strInstitutionCode, l_strBranchCode, l_strAccountCode);
        }
        catch (WEB3BaseException l_be)
        {
            log.error(STR_METHOD_NAME, l_be);
            throw new WEB3BaseRuntimeException(
                l_be.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_be.getMessage(), l_be);
        }

        log.exiting(STR_METHOD_NAME);
        return l_context;
    }


    /**
     * （onReturn）。<BR>
     * <BR>
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * @@param l_context onCallリターン値<BR>
     * @@param l_returnValue サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {        
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （onThrowable）。<BR>
     * <BR>
     * サービスメソッドが例外をスローした場合にコールされる <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj onCallリターン値<BR>
     * @@param l_throwable 例外オブジェクト<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
             WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
