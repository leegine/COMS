head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）OP返済注文ハンドラ(WEB3ToSuccOptionSettleContractHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 楊夫志(中訊) 新規作成 モデル283
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP返済注文ハンドラ)<BR>
 * （連続）オプション返済注文ハンドラクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractHandler implements MessageHandler
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractHandler.class);

    /**
     * @@roseuid 47FDBE3E00BB
     */
    public WEB3ToSuccOptionSettleContractHandler()
    {

    }

    /**
     * (confirm返済)<BR>
     * （連続）OP返済の発注審査を行う。<BR>
     * <BR>
     * （連続）OP返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccOptionsCloseConfirmResponse
     * @@roseuid 47A922D303E0
     */
    public WEB3SuccOptionsCloseConfirmResponse confirmSettleContract(
        WEB3SuccOptionsCloseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmSettleContract(WEB3SuccOptionsCloseConfirmResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseConfirmResponse l_response = null;
        WEB3ToSuccOptionSettleContractOrderService l_service = null;

        //（連続）OP返済注文サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP返済注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）OP返済注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccOptionsCloseConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP返済の発注審査処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP返済の発注審査処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (complete返済)<BR>
     * （連続）OP返済の注文を登録する。<BR>
     * <BR>
     * （連続）OP返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccOptionsCloseCompleteResponse
     * @@roseuid 47A922E103A0
     */
    public WEB3SuccOptionsCloseCompleteResponse completeSettleContract(
        WEB3SuccOptionsCloseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeSettleContract(WEB3SuccOptionsCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseCompleteResponse l_response = null;
        WEB3ToSuccOptionSettleContractOrderService l_service = null;

        //（連続）OP返済注文サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP返済注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）OP返済注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccOptionsCloseCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP返済の注文を登録処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP返済の注文を登録処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
