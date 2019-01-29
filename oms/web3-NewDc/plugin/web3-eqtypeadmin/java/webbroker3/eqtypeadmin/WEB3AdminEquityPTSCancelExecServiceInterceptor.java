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
filename	WEB3AdminEquityPTSCancelExecServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来取消サービスインタセプタ(WEB3AdminEquityPTSCancelExecServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 于瀟(中訊) 新規作成モデル178
*/

package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・株式（PTS）出来取消サービスインタセプタ)<BR>
 * 管理者・株式（PTS）出来取消サービスインタセプタクラス
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecServiceInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminEquityPTSCancelExecServiceInterceptor.class);

    /**
     * @@roseuid 4795B08501F4
     */
    public WEB3AdminEquityPTSCancelExecServiceInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * (未実装)<BR>
     * nullを返却する。<BR>
     * @@param l_method - (メソッド)
     * @@param l_serviceParams - (サービス引数)
     * @@return Object
     * @@roseuid 4773155E0220
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG<BR>
     * PTS取引時間管理.OFFSET_TAG<BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     * @@roseuid 4773156703D5
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        //PTS取引時間管理取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //PTS取引時間管理取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG,
            null);

        //PTS取引時間管理取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * 取引カレンダコンテキストクリア処理。<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。<BR>
     * <BR>
     * PTS取引時間管理.TIMESTAMP_TAG<BR>
     * PTS取引時間管理.OFFSET_TAG<BR>
     * PTS取引時間管理.TRADING_CAL_CONTEXT_PATH<BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     * @@roseuid 4773157003E4
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);

        //ThreadLocalSystemAttributesRegistryの以下の内容をクリアする。
        //PTS取引時間管理取引時間管理.TIMESTAMP_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
            null);

        //PTS取引時間管理取引時間管理.OFFSET_TAG
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.OFFSET_TAG,
            null);

        //PTS取引時間管理取引時間管理.TRADING_CAL_CONTEXT_PATH
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            null);

        log.exiting(STR_METHOD_NAME);
    }
}
@
