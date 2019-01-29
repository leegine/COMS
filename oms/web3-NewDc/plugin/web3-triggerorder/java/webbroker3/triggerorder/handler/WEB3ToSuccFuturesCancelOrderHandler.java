head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物取消注文ハンドラ(WEB3ToSuccFuturesCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 于瀟(中訊) 新規作成モデル268
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesCancelOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物取消注文ハンドラ)<BR>
 * （連続）先物取消注文ハンドラクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccFuturesCancelOrderHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesCancelOrderHandler.class);

    /**
     * @@roseuid 47D6593501F4
     */
    public WEB3ToSuccFuturesCancelOrderHandler()
    {

    }

    /**
     * (confirm取消)<BR>
     * （連続）株価指数先物の取消発注審査を行う。<BR>
     * <BR>
     * （連続）株価指数先物取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCancelConfirmResponse
     * @@roseuid 47A953CC035A
     */
    public WEB3SuccFuturesCancelConfirmResponse confirmCancel(WEB3SuccFuturesCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmCancel(WEB3SuccFuturesCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmResponse l_response = null;
        WEB3ToSuccFuturesCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesCancelOrderService)Services.getService(
                WEB3ToSuccFuturesCancelOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数先物取消注文サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物の取消発注審査の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物の取消発注審査の実施に失敗しました。",
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
     * （連続）株価指数先物の取消注文を登録する。<BR>
     * <BR>
     * （連続）株価指数先物取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCancelCompleteResponse
     * @@roseuid 47A953DC0030
     */
    public WEB3SuccFuturesCancelCompleteResponse completeCancel(WEB3SuccFuturesCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeCancel(WEB3SuccFuturesCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelCompleteResponse l_response = null;
        WEB3ToSuccFuturesCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesCancelOrderService)Services.getService(
                    WEB3ToSuccFuturesCancelOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数先物取消注文サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物の取消注文を登録失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "（連続）株価指数先物の取消注文を登録失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
