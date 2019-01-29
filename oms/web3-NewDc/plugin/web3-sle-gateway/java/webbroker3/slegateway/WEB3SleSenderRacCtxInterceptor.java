head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSenderRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 *Copyright        : (株)大和総研 証券ソリューションシステム第二部
 *File Name        : WEB3SleSenderRacCtxInterceptorクラス
 *Author Name      : Daiwa Institute of Research
 *Revision History : 2006/11/7 李(FLJ) 新規作成　@DBの安定性を改善するため
 */
package webbroker3.slegateway;

import java.lang.reflect.*;
import webbroker3.util.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import webbroker3.system.tune.affinity.*;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;

/**
 * （SLE送信処理RACコンテキストインタセプタ）。
 * @@version 1.0
 */
public class WEB3SleSenderRacCtxInterceptor   
	implements Interceptor{
		
		
		/**
		 * ログ出力ユーティリティ。
		 */
		private static WEB3LogUtility m_log = WEB3LogUtility.getInstance(
			WEB3SleSenderRacCtxInterceptor.class);

		private static long VID = -1;

		/**
		 * コンストラクタ<BR>
		 */
		public WEB3SleSenderRacCtxInterceptor()
		{
		}

		public Object onCall(Method l_method, Object[] l_serviceParams)
		{
			final String STR_METHOD_NAME = "onCall(Method, Object[])";
			m_log.entering(STR_METHOD_NAME);
			long l_accountId = VID;
			//一件send_q送信サービス(WEB3SleSendqProcessorServiceサービス)
			if (l_serviceParams.length > 0 && l_serviceParams[0] instanceof SleSendQParams)
			{
				SleSendQParams l_sleSendQ = (SleSendQParams) l_serviceParams[0];
				l_accountId = l_sleSendQ.getAccountId();

			}        
			//全件send_q送信管理サービス（WEB3SleSendqProcessorManagerServiceサービス）
			else if (l_serviceParams.length > 0 &&
					 l_serviceParams[0] instanceof WEB3ProcessSleSendqRequest)
			{
				WEB3ProcessSleSendqRequest l_sleSendQProcessReq =
					(WEB3ProcessSleSendqRequest) l_serviceParams[0];
			
				l_accountId = l_sleSendQProcessReq.fromAccountId;
			}
		
			if (m_log.ison())
			{
				m_log.debug("WEB3SleSenderRacCtxInterceptor get account_id=" + l_accountId);
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
			m_log.exiting(STR_METHOD_NAME);
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
}
@
