head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.31.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金下り処理RACコンテキストインタセプタ(WEB3AioDescendRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/29 李志強（日本中訊） 新規作成
*/

package webbroker3.aio;

import java.lang.reflect.*;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.aio.data.*;
import webbroker3.gentrade.*;
import webbroker3.system.tune.affinity.*;
import webbroker3.util.*;

/**
* （入出金下り処理RACコンテキストインタセプタ）。
* @@version 1.0
*/
public class WEB3AioDescendRacCtxInterceptor
   implements Interceptor
{
   /**
    * ログ出力ユーティリティ。
    */
   private static WEB3LogUtility log = WEB3LogUtility.getInstance(
       WEB3AioDescendRacCtxInterceptor.class);

   private static long VID = -1;

   /**
    * コンストラクタ<BR>
    */
   public WEB3AioDescendRacCtxInterceptor()
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
                l_serviceParams[0] instanceof HostMrgsecTransNotifyParams)
       {
            HostMrgsecTransNotifyParams l_hostMrgsecTransNotifyParams =
               (HostMrgsecTransNotifyParams) l_serviceParams[0];
           String l_strInstitutionCode = l_hostMrgsecTransNotifyParams.
               getInstitutionCode();
           String l_strBranchCode = l_hostMrgsecTransNotifyParams.getBranchCode();
           String l_strAccountCode = l_hostMrgsecTransNotifyParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                      l_strAccountCode);

       }
       else if (l_serviceParams.length > 0 &&
                l_serviceParams[0] instanceof HostSpsecTransNotifyParams)
       {
           HostSpsecTransNotifyParams l_hostSpsecTransNotifyParams =
               (HostSpsecTransNotifyParams) l_serviceParams[0];
           String l_strInstitutionCode = l_hostSpsecTransNotifyParams.
               getInstitutionCode();
           String l_strBranchCode = l_hostSpsecTransNotifyParams.getBranchCode();
           String l_strAccountCode = l_hostSpsecTransNotifyParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                      l_strAccountCode);
       }
       else if (l_serviceParams.length > 0 &&
                l_serviceParams[0] instanceof HostTransferReceiptParams)
       {
           HostTransferReceiptParams l_hostTransferReceiptParams =
               (HostTransferReceiptParams) l_serviceParams[0];
           String l_strInstitutionCode = l_hostTransferReceiptParams.
               getInstitutionCode();
           String l_strBranchCode = l_hostTransferReceiptParams.getBranchCode();
           String l_strAccountCode = l_hostTransferReceiptParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                      l_strAccountCode);
       }
       else if (l_serviceParams.length > 0 &&
                l_serviceParams[0] instanceof HostCashTransferParams)
       {
           HostCashTransferParams l_hostCashTransferParams =
               (HostCashTransferParams) l_serviceParams[0];
           String l_strInstitutionCode =
               l_hostCashTransferParams.getInstitutionCode();
           String l_strBranchCode = l_hostCashTransferParams.getBranchCode();
           String l_strAccountCode = l_hostCashTransferParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                      l_strAccountCode);
       }
       else if (l_serviceParams.length > 0 &&
                l_serviceParams[0] instanceof HostTransferPaymentParams)
       {
           HostTransferPaymentParams l_hostTransferPaymentParams =
               (HostTransferPaymentParams) l_serviceParams[0];
           String l_strInstitutionCode =
               l_hostTransferPaymentParams.getInstitutionCode();
           String l_strBranchCode = l_hostTransferPaymentParams.getBranchCode();
           String l_strAccountCode = l_hostTransferPaymentParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                     l_strAccountCode);
       }
       else if (l_serviceParams.length > 0 &&
                l_serviceParams[0] instanceof HostSecDeliveryTransferParams)
       {
           HostSecDeliveryTransferParams l_hostSecDeliveryTransferParams =
               (HostSecDeliveryTransferParams) l_serviceParams[0];
           String l_strInstitutionCode =
               l_hostSecDeliveryTransferParams.getInstitutionCode();
           String l_strBranchCode = l_hostSecDeliveryTransferParams.getBranchCode();
           String l_strAccountCode = l_hostSecDeliveryTransferParams.getAccountCode();
           l_accountId = getAccountId(l_strInstitutionCode, l_strBranchCode,
                                      l_strAccountCode);
       }

       if (log.ison())
       {
           log.debug("WEB3AioDescendRacCtxInterceptor get account_id=" + l_accountId);
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
