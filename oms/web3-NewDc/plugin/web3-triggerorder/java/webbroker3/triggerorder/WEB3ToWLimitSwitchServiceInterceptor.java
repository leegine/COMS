head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitSwitchServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : W指値注文切替サービスインタセプタ(WEB3ToWLimitSwitchServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/24 唐性峰(中訊) 新規作成
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;


/**
 * (W指値注文切替サービスインタセプタ)<BR>
 * W指値注文切替サービスインタセプタ<BR>
 * 
 * @@author 唐性峰
 * @@version 1.0
 */
public class WEB3ToWLimitSwitchServiceInterceptor implements Interceptor
{
    
    /**
     * @@roseuid 44E90CEB01D4
     */
    public WEB3ToWLimitSwitchServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_method
     * @@param l_serviceParam
     * @@return Object
     * @@roseuid 449244EB00AD
     */
    public java.lang.Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        return null;
    }
    
    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 449244EB00CC
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
     * @@roseuid 449244EB00EC
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
     
    }
}
@
