head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウトインタセプタ(WEB3AdminFPTForceLogoutInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin;

import java.lang.reflect.Method;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;


/**
 * 管理者 書面未承諾 強制ログアウトインタセプタ
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutInterceptor implements Interceptor 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutInterceptor.class);
    
    /**
     * @@roseuid 47DF4FB70132
     */
    public WEB3AdminFPTForceLogoutInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。 
     * ※受付日時のセットのみを行う。 
     * 
     * １）　@ThreadLocalSystemAttributesRegistry.setAttribute()にて 
     * 　@受付日時をセットする。 
     * 
     * 　@[setAttribute()にセットするパラメータ] 
     * 　@　@arg0：　@取引時間管理.TIMESTAMP_TAG 
     * 　@　@arg1：　@GtlUtils.getSystemTimestamp()
     * @@param arg0
     * @@param arg1
     * @@return java.lang.Object
     * @@roseuid 47D6671C0165
     */
    public Object onCall(Method arg0, java.lang.Object[] arg1) 
    {
        final String STR_METHOD_NAME = "onCall()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
            GtlUtils.getSystemTimestamp());
       
        log.exiting(STR_METHOD_NAME);
        return l_context;
    }
    
    /**
     * サービスメソッド終了時にコールされる。 
     * 取引カレンダコンテキストクリア処理。 
     * 
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 
     * 
     * 取引時間管理.TIMESTAMP_TAG 
     * 取引時間管理.OFFSET_TAG 
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param arg0
     * @@param arg1
     * @@roseuid 47D6671C0174
     */
    public void onReturn(java.lang.Object arg0, java.lang.Object arg1) 
    {
        final String STR_METHOD_NAME = "onReturn()";
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
     * サービスメソッド例外時にコールされる。 
     * 取引カレンダコンテキストクリア処理。 
     * 
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。 
     * 
     * 取引時間管理.TIMESTAMP_TAG 
     * 取引時間管理.OFFSET_TAG 
     * 取引時間管理.TRADING_CAL_CONTEXT_PATH
     * @@param arg0
     * @@param arg1
     * @@roseuid 47D6671C0184
     */
    public void onThrowable(java.lang.Object arg0, Throwable arg1) 
    {
        final String STR_METHOD_NAME = "onThrowable()";
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
