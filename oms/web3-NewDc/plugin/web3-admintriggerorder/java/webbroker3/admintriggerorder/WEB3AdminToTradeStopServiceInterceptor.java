head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止サービスインタセプタ(WEB3AdminToTradeStopServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止サービスインタセプタ)<BR>
 * トリガー注文管理者・取扱停止サービスインタセプタ<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToTradeStopServiceInterceptor implements Interceptor 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToTradeStopServiceInterceptor.class);
    
    /**
     * @@roseuid 4430DB9D02BF
     */
    public WEB3AdminToTradeStopServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * ※受付日時のセットのみを行う。<BR>
     * <BR>
     * １）　@ThreadLocalSystemAttributesRegistry.setAttribute()にて<BR>
     * 　@受付日時をセットする。<BR>
     * <BR>
     * 　@[setAttribute()にセットするパラメータ]<BR>
     * 　@　@l_method：　@取引時間管理.TIMESTAMP_TAG<BR>
     * 　@　@l_serviceParam：　@GtlUtils.getSystemTimestamp()<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * @@return Object
     * @@roseuid 44100AC5034E
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = " onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            GtlUtils.getSystemTimestamp());
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * @@param l_context - (onCallリターン値)<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * @@roseuid 44100AC5036E
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = " onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * サービスメソッド例外時にコールされる。 <BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 <BR>
     * <BR>
     * 取引時間管理.TIMESTAMP_TAG <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * @@roseuid 44100AC5037D
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = " onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, null);
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
