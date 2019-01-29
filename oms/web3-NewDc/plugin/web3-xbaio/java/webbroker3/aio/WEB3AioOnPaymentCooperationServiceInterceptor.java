head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.33.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携サービスインタセプタ (WEB3AioOnPaymentCooperationServiceInterceptor)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金連携サービスインタセプタ) <BR>
 * 出金連携サービスインタセプタ クラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationServiceInterceptor implements Interceptor
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationServiceInterceptor.class);
    
    /**
     * @@roseuid 41E77F4C0203
     */
    public WEB3AioOnPaymentCooperationServiceInterceptor()
    {
    }

    /**
     * サービスメソッド開始時にコールされる。  <BR> 
     * <BR> 
     * 取引カレンダが利用するコンテキストを生成する。  <BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>  
     * <BR> 
     * １）　@取引カレンダコンテキストに内容をセットする。  <BR> 
     * 　@－サービスの引数[0]の配列の最初の要素を注文単位オブジェクトにキャストする。<BR>  
     * 　@－注文単位の内容より取引時間コンテキストのプロパティをセットする。  <BR> 
     * <BR> 
     * 　@取引カレンダコンテキスト.証券会社コード = リクエスト.証券会社コード <BR> 
     * 　@取引カレンダコンテキスト.部店コード = null <BR> 
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”  <BR> 
     * 　@取引カレンダコンテキスト.受付時間区分 = ”16：出金”  <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT”  <BR> 
     * 　@取引カレンダコンテキスト.注文受付商品 = null  <BR> 
     * 　@取引カレンダコンテキスト.注文受付トランザクション = null <BR> 
     * <BR> 
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて<BR> 
     * 取引時間コンテキストをセットする。  <BR> 
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  <BR> 
     * <BR> 
     * @@param l_method - サービスメソッド
     * @@param l_serviceParam - サービスメソッド引数
     * @@return Object
     * @@roseuid 41BCF2EB0056
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
        
        //１）取引カレンダコンテキストに内容をセットする。  
        //　@－サービスの引数[0]の配列の最初の要素を注文単位オブジェクトにキャストする。  
        //　@－注文単位の内容より取引時間コンテキストのプロパティをセットする。 
        if (l_serviceParams == null || l_serviceParams.length == 0)
        {
            log.debug("array is null OR the array's length is 0");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);            
        }
        
        WEB3AioOnPaymentCooperationRequest l_request = null;
        if(l_serviceParams[0] instanceof WEB3AioOnPaymentCooperationRequest)
        {
            l_request = (WEB3AioOnPaymentCooperationRequest) l_serviceParams[0];
        }
        else
        {
            log.debug(" パラメータError ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }               
       
        WEB3GentradeTradingClendarContext l_context = 
            new WEB3GentradeTradingClendarContext();       
                
        //取引カレンダコンテキスト.証券会社コード = リクエスト.証券会社コード 
        String l_strInstitutionCode = l_request.institutionCode;            
        l_context.setInstitutionCode(l_strInstitutionCode);
        
        //取引カレンダコンテキスト.部店コード = null   
        l_context.setBranchCode(null);
        
        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT”
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.受付時間区分 = ”16：出金” 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.PAYMENT);
        
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.注文受付商品 = null   
        l_context.setOrderAcceptProduct(null);
        
        //取引カレンダコンテキスト.注文受付トランザクション = null 
        l_context.setOrderAcceptTransaction(null);

        //－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。  
        //設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);
   
        log.exiting(STR_METHOD_NAME);        
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_context - onCallリターン値
     * @@param l_returnValue - サービスメソッドリターン値
     * @@roseuid 41BCF2EB0076
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        String l_strMethodName = "onReturn(Object l_context, Object l_returnValue)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * 
     * @@param l_obj - onCallリターン値
     * @@param l_throwable - 例外オブジェクト
     * @@roseuid 41BCF2EB0095
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        String l_strMethodName = "onThrowable(Object l_obj, Throwable l_throwable)";
        log.entering(l_strMethodName);
        
        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG, null);
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, null);
        
        log.exiting(l_strMethodName);
    }
}@
