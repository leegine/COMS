head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 累積投資下り処理RACコンテキストインタセプタ(WEB3RuitoDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/25 李志強（日本中訊） 新規作成
 */

package webbroker3.xbruito;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.xbruito.data.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
 * （累積投資下りRACコンテキストインタセプタ）。
 * @@version 1.0
 */
public class WEB3RuitoDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3RuitoDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * コンストラクタ<BR>
     */
    public WEB3RuitoDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;


        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof OrderUnit)
        {
            OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
            l_accountId = l_orderUnit.getAccountId();

        }
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostRuitoOrderNotifyParams)
        {
            HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams =
                (HostRuitoOrderNotifyParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostRuitoOrderNotifyParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostRuitoOrderNotifyParams.getBranchCode();
            String l_strAccountCode = l_hostRuitoOrderNotifyParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }

        if (log.ison())
        {
            log.debug("WEB3RuitoDescendRacCtxInterceptor get account_id=" + l_accountId);
        }
        if (l_accountId > 0)
        {
            WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
                getService(WEB3DescendRacCtxService.class);
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
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    public void onThrowable(Object l_obj, Throwable l_throwable)
    {
        //RACコンテキストをクリアする。
        WEB3DescendRacCtxService l_ctx_serv = (WEB3DescendRacCtxService) Services.
            getService(WEB3DescendRacCtxService.class);
        if (l_ctx_serv != null)
        {
            l_ctx_serv.clearAccountIdCtx();
        }

    }

    private long getAccountId(String l_strInstitutionCode,
                              String l_strBranchCode,
                              String l_strAccountCode)

    {
        final String STR_METHOD_NAME =
            "getAccountId(String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(
                l_strInstitutionCode);

            long l_lngInstitutionId = l_institution.getInstitutionId();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            log.exiting(STR_METHOD_NAME);
            return l_mainAccount.getAccountId();

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            return VID;
        }
    }
}
@
