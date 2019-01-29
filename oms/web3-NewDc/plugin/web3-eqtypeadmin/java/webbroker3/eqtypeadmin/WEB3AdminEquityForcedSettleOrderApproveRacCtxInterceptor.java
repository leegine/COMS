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
filename	WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認RACコンテキストインタセプタ(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 張騰宇(中訊) 新規作成 仕様変更モデルNo.170
*/
package webbroker3.eqtypeadmin;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認RACコンテキストインタセプタ)<BR>
 * 管理者・強制決済仮注文承認／非承認RACコンテキストインタセプタ <BR>
 * <BR>
 * ※管理者・強制決済仮注文承認／非承認一件サービスにaddInterceptorする。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor implements Interceptor
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor()
    {

    }

    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * １）　@RACコンテキストに値をセットする。<BR>
     * 　@１－１）　@サービスメソッド引数[0]を強制決済注文Rowにキャストする。<BR>
     * <BR>
     * 　@１－２）　@RACコンテキストに口座IDをセットする。<BR>
     * 　@　@WEB3DescendRacCtxService.setAccountIdCtx()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数] <BR>
     * 　@　@　@　@強制決済注文Row.口座ID<BR>
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッドオブジェクト<BR>
     * @@param l_serviceParams - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object
     */
    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        long l_accountId = -1;

        //１－１）　@サービスメソッド引数[0]を強制決済注文Rowにキャストする。
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof AdminEqForcedSettleOrderRow)
        {
            AdminEqForcedSettleOrderRow l_orderRow = (AdminEqForcedSettleOrderRow)l_serviceParams[0];
            l_accountId = l_orderRow.getAccountId();
        }

        if (l_accountId > 0)
        {
            //１－２）　@RACコンテキストに口座IDをセットする。
            WEB3DescendRacCtxService l_descendRacCtxService =
                (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
            if (l_descendRacCtxService != null)
            {
                l_descendRacCtxService.setAccountIdCtx(l_accountId);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。  <BR>
     * <BR>
     * １）　@RACコンテキストをクリアする。<BR>
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue)
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        //１）　@RACコンテキストをクリアする。
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.clearAccountIdCtx();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * サービスメソッドが例外をスローした場合にコールされる。  <BR>
     * <BR>
     * １）　@RACコンテキストをクリアする。  <BR>
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (例外オブジェクト)<BR>
     * 例外オブジェクト<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        //１）　@RACコンテキストをクリアする。
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_descendRacCtxService != null)
        {
            l_descendRacCtxService.clearAccountIdCtx();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
