head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 株式下り処理RACコンテキストインタセプタ(WEB3EquityDescendRacCtxInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/11 劉(FLT) 新規作成
 */
package webbroker3.equity;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.equity.data.*;
import webbroker3.equity.service.delegate.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;

/**
 * （株式下り処理RACコンテキストインタセプタ）。
 * @@version 1.0
 */
public class WEB3EquityDescendRacCtxInterceptor
    implements Interceptor
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityDescendRacCtxInterceptor.class);

    private static long VID = -1;

    /**
     * コンストラクタ<BR>
     */
    public WEB3EquityDescendRacCtxInterceptor()
    {
    }

    public Object onCall(Method l_method, Object[] l_serviceParams)
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        long l_accountId = VID;
        //出来通知(現物出来通知一件サービス、信用出来通知一件サービス、注文繰越一件サービス)
        if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof OrderUnit)
        {
            OrderUnit l_orderUnit = (OrderUnit) l_serviceParams[0];
            l_accountId = l_orderUnit.getAccountId();

        }
        //株式注文受付一件サービス（訂正取消受付一件サービス含）
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeOrderAcceptParams)
        {
            //サービスの引数[0]を株式注文受付キューParamsオブジェクトにキャストする。
            HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams =
                (HostEqtypeOrderAcceptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_hostEqtypeOrderAcceptParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostEqtypeOrderAcceptParams.getBranchCode();
            String l_strAccountCode = l_hostEqtypeOrderAcceptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }
        //株式失効通知一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeCloseOrderNotifyParams)
        {
            //サービスの引数[0]を株式失効通知キューParamsオブジェクトにキャストする。
            HostEqtypeCloseOrderNotifyParams l_hostEqtypeCloseOrderNotifyParams =
                (HostEqtypeCloseOrderNotifyParams) l_serviceParams[0];

            String l_strInstitutionCode = l_hostEqtypeCloseOrderNotifyParams.
                getInstitutionCode();
            String l_strBranchCode = l_hostEqtypeCloseOrderNotifyParams.getBranchCode();
            String l_strAccountCode = l_hostEqtypeCloseOrderNotifyParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);

        }
        //信用取引現引現渡注文通知一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3MarginSwapOrderNotifyDataAdapter)
        {
            WEB3MarginSwapOrderNotifyDataAdapter l_adapter
                = (WEB3MarginSwapOrderNotifyDataAdapter) l_serviceParams[0];
            HostEqtypeSwapReceiptParams l_swapReceiptParams = l_adapter.
                getDataSourseObject();
            String l_strInstitutionCode = l_swapReceiptParams.
                getInstitutionCode();
            String l_strBranchCode = l_swapReceiptParams.getBranchCode();
            String l_strAccountCode = l_swapReceiptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //信用取引現引現渡受付一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeSwapAcceptRow)
        {
            HostEqtypeSwapAcceptRow l_eqtypeSwapAcceptRow = (HostEqtypeSwapAcceptRow)
                l_serviceParams[0];
            String l_strInstitutionCode = l_eqtypeSwapAcceptRow.
                getInstitutionCode();
            String l_strBranchCode = l_eqtypeSwapAcceptRow.getBranchCode();
            String l_strAccountCode = l_eqtypeSwapAcceptRow.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //信用取引訂正取消通知訂正(訂正、取消含)一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof HostEqtypeOrderClmdReceiptParams)
        {
            HostEqtypeOrderClmdReceiptParams l_params = (
                HostEqtypeOrderClmdReceiptParams) l_serviceParams[0];
            String l_strInstitutionCode = l_params.getInstitutionCode();
            String l_strBranchCode = l_params.getBranchCode();
            String l_strAccountCode = l_params.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //株式注文通知一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3EquityOrderInputNotifyAdapter)
        {
            WEB3EquityOrderInputNotifyAdapter l_adapter = (
                WEB3EquityOrderInputNotifyAdapter) l_serviceParams[0];
            HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.
                getHostEqtypeOrderReceipt();
            String l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
            String l_strBranchCode = l_orderReceiptParams.getBranchCode();
            String l_strAccountCode = l_orderReceiptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }
        //信用取引注文通知一件サービス
        else if (l_serviceParams.length > 0 &&
                 l_serviceParams[0] instanceof WEB3MarginOrderNotifyDataAdapter)
        {
            WEB3MarginOrderNotifyDataAdapter l_adapter = (
                WEB3MarginOrderNotifyDataAdapter) l_serviceParams[0];
            HostEqtypeOrderReceiptParams l_orderReceiptParams = l_adapter.
                getDataSourseObject();
            String l_strInstitutionCode = l_orderReceiptParams.getInstitutionCode();
            String l_strBranchCode = l_orderReceiptParams.getBranchCode();
            String l_strAccountCode = l_orderReceiptParams.getAccountCode();
            l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                       l_strAccountCode);
        }

        if (log.ison())
        {
            log.debug("WEB3EquityDescendRacCtxInterceptor get account_id=" + l_accountId);
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
