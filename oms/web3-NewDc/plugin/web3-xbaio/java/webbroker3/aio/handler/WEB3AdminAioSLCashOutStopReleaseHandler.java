head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLCashOutStopReleaseHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン出金停止解除ハンドラ(WEB3AdminAioSLCashOutStopReleaseHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 金傑（中訊）新規作成 モデルNo.764
Revision History : 2007/09/14 金傑（中訊）モデルNo.777
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン出金停止解除ハンドラ)<BR>
 * 証券担保ローン出金停止解除ハンドラクラス<BR>
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioSLCashOutStopReleaseHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLCashOutStopReleaseHandler.class);

    public WEB3AdminAioSLCashOutStopReleaseHandler()
    {

    }

    /**
     * (validate出金停止解除確認画面)<BR>
     * 証券担保ローン出金停止解除確認画面表示処理を行う。<BR>
     * <BR>
     * 証券担保ローン出金停止解除サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLCashOutStopReleaseConfirmResponse
     */
    public WEB3AdminSLCashOutStopReleaseConfirmResponse validateCashOutStopReleaseConfirmScreen(
        WEB3AdminSLCashOutStopReleaseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateCashOutStopReleaseConfirmScreen(WEB3AdminSLCashOutStopReleaseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLCashOutStopReleaseConfirmResponse l_response = null;

        // 証券担保ローン出金停止解除サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "証券担保ローン出金停止解除サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金停止解除確認画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金停止解除確認画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出金停止解除完了画面)<BR>
     * 証券担保ローン出金停止解除完了画面表示処理を行う。<BR>
     * <BR>
     * 証券担保ローン出金停止解除サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     *
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLCashOutStopReleaseCompleteResponse
     */
    public WEB3AdminSLCashOutStopReleaseCompleteResponse submitCashOutStopReleaseCompleteScreen(
        WEB3AdminSLCashOutStopReleaseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitCashOutStopReleaseCompleteScreen(WEB3AdminSLCashOutStopReleaseCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLCashOutStopReleaseCompleteResponse l_response = null;

        // 証券担保ローン出金停止解除サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "証券担保ローン出金停止解除サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金停止解除完了画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金停止解除完了画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * get担保ローン出金拘束金一覧画面<BR>
     * 証券担保ローン出金拘束金一覧画面表示処理を行う。<BR>
     * <BR>
     * 証券担保ローン出金停止解除サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLRestraintMoneyListResponse
     */
    public WEB3AdminSLRestraintMoneyListResponse getSLRestraintMoneyListScreen(
        WEB3AdminSLRestraintMoneyListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLRestraintMoneyListScreen(WEB3AdminSLRestraintMoneyListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLRestraintMoneyListResponse l_response = null;

        // 証券担保ローン出金停止解除サービスオブジェクトを取得
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "証券担保ローン出金停止解除サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金拘束金一覧画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "証券担保ローン出金拘束金一覧画面表示処理が失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
