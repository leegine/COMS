head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式注文受付取消認証サービスインタセプタ(WEB3AdminFeqOrderAcceptServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 郭英 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者外国株式注文受付取消認証サービスインタセプタ)<BR>
 * 管理者外国株式注文受付取消認証サービスインタセプタ<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptServiceInterceptor.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3AdminFeqOrderAcceptServiceInterceptor() 
    {
     
    }
    
    /**
     * （xTradeカーネルよりサービス実行前に呼び出される） <BR>
     * <BR>
     * １）　@業務日時ロールをセットする。<BR>
     *　@－取引時間管理.setBusinessTimestamp()をコールする。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * 
     * @@param l_serviceParams - (サービスの引数)<BR>
     * サービスの引数配列<BR>
     * @@return Object
     * @@roseuid 429FED39003F
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);        
         
        //１）　@業務日時ロールをセットする。 
        //－取引時間管理.setBusinessTimestamp()をコールする。
        WEB3GentradeTradingTimeManagement.setBusinessTimestamp();
        
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
     * @@param l_context - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_returnValue - (サービスメソッド返却値)<BR>
     * サービスメソッド返却値<BR>
     * @@roseuid 429FED390042
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
        
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);
            
        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。 <BR>
     * 取引カレンダコンテキストクリア処理。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * 取引時間管理.OFFSET_TAG <BR>
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH <BR>
     * @@param l_obj - (onCall返却値)<BR>
     * onCall返却値<BR>
     * @@param l_throwable - (例外)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 429FED39004E
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            null);
            
        //取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG,
            null);

        //取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
