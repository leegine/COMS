head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込ハンドラ(WEB3AioSLRepayApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 柴双紅 (中訊) 新規作成 仕様変更 モデルNo.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済申込ハンドラ)<BR>
 * 証券担保ローン返済申込ハンドラクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyHandler.class);

    /**
     * @@roseuid 46E8908602BB
     */
    public WEB3AioSLRepayApplyHandler()
    {

    }

    /**
     * (confirm注文)<BR>
     * 証券担保ローン返済申込の発注審査を行う。<BR>
     * <BR>
     * 証券担保ローン返済申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayApplyConfirmResponse
     */
    public WEB3SLRepayApplyConfirmResponse confirmOrder(WEB3SLRepayApplyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SLRepayApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyService l_service = null;
        WEB3SLRepayApplyConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyService)Services.getService(
                    WEB3AioSLRepayApplyService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証券担保ローン返済申込サービスの取得に失敗しました",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込の発注審査処理が失敗しました。",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込サービス処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete注文)<BR>
     * 証券担保ローン返済申込の登録を行う。<BR>
     * <BR>
     * 証券担保ローン返済申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayApplyCompleteResponse
     */
    public WEB3SLRepayApplyCompleteResponse completeOrder(WEB3SLRepayApplyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SLRepayApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyService l_service = null;
        WEB3SLRepayApplyCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyService)Services.getService(
                    WEB3AioSLRepayApplyService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証券担保ローン返済申込サービスの取得に失敗しました",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込の登録処理が失敗しました。",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込サービス処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
