head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物返済注文ハンドラ（WEB3ToSuccFuturesSettleContractOrderHandler.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 孟亞南(中訊) 新規作成モデルNo.259 No.291
 */
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物返済注文ハンドラ)<BR>
 * （連続）先物返済注文ハンドラクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractOrderHandler implements MessageHandler
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderHandler.class);

    /**
     * @@roseuid 47D6593600EA
     */
    public WEB3ToSuccFuturesSettleContractOrderHandler()
    {

    }

    /**
     * (confirm返済)<BR>
     * （連続）先物返済の発注審査を行う。<BR>
     * <BR>
     * （連続）先物返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCloseConfirmResponse
     * @@roseuid 47A94D2A0032
     */
    public WEB3SuccFuturesCloseConfirmResponse confirmSettleContract(WEB3SuccFuturesCloseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmSettleContract(WEB3SuccFuturesCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseConfirmResponse l_response = null;
        WEB3ToSuccFuturesSettleContractOrderService l_service = null;

        //（連続）先物返済注文サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractOrderService)Services.getService(
                    WEB3ToSuccFuturesSettleContractOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物返済注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）先物返済注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccFuturesCloseConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物返済の発注審査処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物返済の発注審査処理が失敗しました。",
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
     * （連続）先物返済の注文を登録する。<BR>
     * <BR>
     * （連続）先物返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCloseCompleteResponse
     * @@roseuid 47A94D390238
     */
    public WEB3SuccFuturesCloseCompleteResponse completeSettleContract(
        WEB3SuccFuturesCloseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeSettleContract(WEB3SuccFuturesCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseCompleteResponse l_response = null;
        WEB3ToSuccFuturesSettleContractOrderService l_service = null;

        //（連続）先物返済注文サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractOrderService)Services.getService(
                    WEB3ToSuccFuturesSettleContractOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物返済注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）先物返済注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccFuturesCloseCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物返済の注文を登録処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物返済の注文を登録処理が失敗しました。",
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
