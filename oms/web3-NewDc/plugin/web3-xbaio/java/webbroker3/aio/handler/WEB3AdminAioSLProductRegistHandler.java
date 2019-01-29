head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録ハンドラ(WEB3AdminAioSLProductRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孟亞南 (中訊) 新規作成 モデルNo.760
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保銘柄登録ハンドラ)<BR>
 * 担保銘柄登録ハンドラクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAioSLProductRegistHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistHandler.class);

    /**
     * (get担保銘柄入力)<BR>
     * 担保銘柄登録入力画面表示処理を行う。<BR>
     * <BR>
     * 担保銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLProductRegistInputResponse
     */
    public WEB3AdminSLProductRegistInputResponse getSLProductInput(
        WEB3AdminSLProductRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLProductInput(WEB3AdminSLProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //担保銘柄登録サービスを取得し
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSLProductRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate担保銘柄)<BR>
     * 担保銘柄登録入力確認画面表示処理を行う。<BR>
     * <BR>
     * 担保銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLProductRegistConfirmResponse
     */
    public WEB3AdminSLProductRegistConfirmResponse validateSLProductRegist(
        WEB3AdminSLProductRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateSLProductRegist(WEB3AdminSLProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //担保銘柄登録サービスを取得し
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSLProductRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力確認画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力確認画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit担保銘柄)<BR>
     * 担保銘柄登録入力完了画面表示処理を行う。<BR>
     * <BR>
     * 担保銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLProductRegistCompleteResponse
     */
    public WEB3AdminSLProductRegistCompleteResponse submitSLProductRegist(
        WEB3AdminSLProductRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitSLProductRegist(WEB3AdminSLProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //担保銘柄登録サービスを取得し
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄登録サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSLProductRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力完了画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "担保銘柄登録入力完了画面表示処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
