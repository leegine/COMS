head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAttentionInfoNotifyUnitServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知一件サービスインタセプタ(WEB3AdminEquityAttentionInfoNotifyUnitServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 張少傑(中訊) モデルNo.219
*/

package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
/**
 * (注意情報通知一件サービスインタセプタ)<BR>
 * 注意情報通知一件サービスインタセプタ<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyUnitServiceInterceptor implements Interceptor
{
    /**
     * 何もせず処理を終了する。<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     * @@roseuid 4947162200BC
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        return null;
    }

    /**
     * 何もせず処理を終了する。<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 49471624007D
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {

    }

    /**
     * 何もせず処理を終了する。<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 494716250242
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {

    }
}
@
