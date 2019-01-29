head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.24.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioMultiBankTelegramProcessServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク電文処理サービスインタセプタ(WEB3AioMultiBankTelegramProcessServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 王蘭芬(中訊) 新規作成
                   2004/10/25 黄建 (中訊) レビュー   
                   2005/1/11 周勇 (中訊) 残対応  
*/

package webbroker3.aio;

import java.lang.reflect.Method;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (マルチバンク電文処理サービスインタセプタ)<BR>
 * マルチバンク電文処理サービスインタセプタクラス
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioMultiBankTelegramProcessServiceInterceptor implements Interceptor 
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AioMultiBankTelegramProcessServiceInterceptor.class);

    /**
     * @@roseuid 415A78190319
     */
    public WEB3AioMultiBankTelegramProcessServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR> 
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。<BR> 
     * （xTradeカーネルよりサービス実行前に呼び出される）<BR> 
     * <BR>
     * １）　@取引カレンダコンテキストに内容をセットする。<BR> 
     * <BR>
     * 　@取引カレンダコンテキスト.市場コード = ”0：DEFAULT”<BR> 
     * 　@取引カレンダコンテキスト.受付時間区分 = ”14：入金” <BR>
     * 　@取引カレンダコンテキスト.商品コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = ”14：入出金” <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = ”10：入金”<BR> 
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて取引時間コンテキストをセットする。<BR> 
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 41255D1401F8
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = 
            "onCall(Method l_method, Object[] l_serviceParams)";
        log.entering(STR_METHOD_NAME);
               
        //======remain zhou-yong NO.1 障害票U00621 begin =========
        
        //サービスメソッド開始時にコールされる。
        //取引カレンダが利用するコンテキストを生成する。 
        //（xTradeカーネルよりサービス実行前に呼び出される） 
        WEB3GentradeTradingClendarContext l_context =
            new WEB3GentradeTradingClendarContext();  

        //１）　@取引カレンダコンテキストに内容をセットする。 

        //取引カレンダコンテキスト.市場コード = ”0：DEFAULT” 
        l_context.setMarketCode(WEB3MarketCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.受付時間区分 = ”14：入金” 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.RECIEPT);
        
        //取引カレンダコンテキスト.商品コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //取引カレンダコンテキスト.注文受付商品 = ”14：入出金”  
        l_context.setOrderAcceptProduct(
            WEB3OrderAccProductDef.PAYMENT);
        
        //取引カレンダコンテキスト.注文受付トランザクション = ”10：入金” 
        l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.CASH_IN);

        //======remian zhou-yong NO.1 障害票U00621 end =========
        
        //-ThreadLocalSystemAttributesRegistry.setAttribute()にて取引時間コンテキストをセット
        ThreadLocalSystemAttributesRegistry.setAttribute(
              WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
              l_context);

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
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 41255D1401FB
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = 
            "onReturn(Object l_context, Object l_returnValue)";
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
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * <BR>
     * @@param l_obj - (onCallリターン値)
     * @@param l_throwable - (例外オブジェクト)
     * @@roseuid 41255D1401FE
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = 
            "onThrowable(Object l_obj, Throwable l_throwable)";
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
