head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 余力下り処理RACコンテキストインタセプタ(WEB3TPDescendRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/31 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * （余力下り処理RACコンテキストインタセプタ）。
 * @@version 1.0
 */
public class WEB3TPDescendRacCtxInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * コンストラクタ<BR>
     */
    public WEB3TPDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;

        //サービスの引数[0]を補助口座オブジェクトにキャストする。
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_serviceParams[0];
        l_accountId = l_subAccount.getAccountId();

        if (log.ison())
        {
            log.debug("WEB3TPDescendRacCtxInterceptor get account_id=" + l_accountId);
        }
        if (l_accountId > 0)
        {
            WEB3DescendRacCtxService l_ctx_serv =
                (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
            if (l_ctx_serv != null)
            {
                l_ctx_serv.setAccountIdCtx(l_accountId);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    public void onReturn(Object l_returnValue, Object l_context)
    {
        //RACコンテキストをクリアする。
        WEB3DescendRacCtxService l_ctx_serv =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        //RACコンテキストをクリアする。
        WEB3DescendRacCtxService l_ctx_serv =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }
    }
}
@
