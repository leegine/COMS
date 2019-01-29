head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建注文ハンドラ(WEB3ToSuccFuturesOpenContractHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 于瀟(中訊) 新規作成モデル257
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物新規建注文ハンドラ)<BR>
 * （連続）先物新規建注文ハンドラクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractHandler.class);

    /**
     * @@roseuid 47D659350261
     */
    public WEB3ToSuccFuturesOpenContractHandler()
    {

    }

    /**
     * (confirm注文)<BR>
     * （連続）先物新規建発注審査を行う。 <BR>
     * <BR>
     * （連続）先物新規建サービスImplを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesOpenConfirmResponse
     * @@roseuid 47A93D040228
     */
    public WEB3SuccFuturesOpenConfirmResponse confirmOrder(WEB3SuccFuturesOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3SuccFuturesOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenConfirmResponse l_response = null;
        WEB3ToSuccFuturesOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractService)Services.getService(
                WEB3ToSuccFuturesOpenContractService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物新規建サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物新規建発注審査の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "（連続）先物新規建発注審査の実施に失敗しました。",
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
     * （連続）先物新規建注文登録を行う。 <BR>
     * <BR>
     * （連続）先物新規建サービスImplを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesOpenCompleteResponse
     * @@roseuid 47A93D1500B1
     */
    public WEB3SuccFuturesOpenCompleteResponse completeOrder(WEB3SuccFuturesOpenCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3SuccFuturesOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenCompleteResponse l_response = null;
        WEB3ToSuccFuturesOpenContractService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractService)Services.getService(
                WEB3ToSuccFuturesOpenContractService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物新規建サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物新規建注文登録失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "（連続）先物新規建注文登録失敗しました。",
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
