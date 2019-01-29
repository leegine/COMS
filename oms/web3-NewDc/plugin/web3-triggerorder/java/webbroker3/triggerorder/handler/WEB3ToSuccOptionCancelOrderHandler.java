head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP取消注文ハンドラ（WEB3ToSuccOptionCancelOrderHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 金傑 (中訊) 新規作成 モデルNo.280
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP取消注文ハンドラ)<BR>
 *（連続）OP取消注文ハンドラクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3ToSuccOptionCancelOrderHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderHandler.class);

    /**
     * @@roseuid 47FDBE3D01E4
     */
    public WEB3ToSuccOptionCancelOrderHandler()
    {

    }

    /**
     * (confirm取消)<BR>
     * （連続）株価指数OPの取消発注審査を行う。<BR>
     * <BR>
     * （連続）株価指数OP取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccOptionsCancelConfirmResponse
     * @@roseuid 47A90A9D0376
     */
    public WEB3SuccOptionsCancelConfirmResponse confirmCancel(WEB3SuccOptionsCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmCancel(WEB3SuccOptionsCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCancelConfirmResponse l_response = null;
        WEB3ToSuccOptionCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionCancelOrderService)Services.getService(
                WEB3ToSuccOptionCancelOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数OP取消注文サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccOptionsCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OPの取消発注審査の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OPの取消発注審査の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete取消)<BR>
     * （連続）株価指数OPの取消注文を登録する。<BR>
     * <BR>
     * （連続）株価指数OP取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccOptionsCancelCompleteResponse
     * @@roseuid 47A90AB103E4
     */
    public WEB3SuccOptionsCancelCompleteResponse completeCancel(WEB3SuccOptionsCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeCancel(WEB3SuccOptionsCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCancelCompleteResponse l_response = null;
        WEB3ToSuccOptionCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionCancelOrderService)Services.getService(
                WEB3ToSuccOptionCancelOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数OP取消注文サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccOptionsCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OPの取消注文を登録失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccOptionsCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OPの取消注文を登録失敗しました。",
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
