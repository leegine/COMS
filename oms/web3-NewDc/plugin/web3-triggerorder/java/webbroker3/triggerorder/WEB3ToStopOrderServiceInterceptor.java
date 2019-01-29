head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 逆指値注文発注サービスインタセプタ(WEB3ToStopOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 呉艶飛(中訊) 新規作成
*/
package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.util.WEB3LogUtility;


/**
 * (逆指値注文発注サービスインタセプタ)<BR>
 * 逆指値注文発注サービスインタセプタ<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3ToStopOrderServiceInterceptor implements Interceptor 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 436ACB98034B
     */
    public WEB3ToStopOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 434C7BCA0026
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME =
            "onCall(Method l_method, Object[] l_serviceParam) ";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 434C7BCA0093
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
     
    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 434C7BCA00D2
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
     
    }
}
@
