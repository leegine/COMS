head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄変更ハンドラ(WEB3AdminAioSLProductChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 トウ鋒鋼(中訊) 新規作成 仕様変更モデル760
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (担保銘柄変更ハンドラ)<BR>
 * 担保銘柄変更ハンドラクラス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminAioSLProductChangeHandler implements MessageHandler
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductChangeHandler.class);

    /**
     * @@roseuid 46E8EE2A0375
     */
    public WEB3AdminAioSLProductChangeHandler()
    {

    }

    /**
     * (get担保銘柄変更入力)<BR>
     * 担保銘柄変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 担保銘柄変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeInputResponse
     * @@roseuid 46DCC07F0046
     */
    public WEB3AdminSLProductChangeInputResponse getSLProductChangeInput(
        WEB3AdminSLProductChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSLProductChangeInput(WEB3AdminSLProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //担保銘柄変更サービスを取得し
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get担保銘柄変更入力処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get担保銘柄変更入力処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate担保銘柄変更)<BR>
     * 担保銘柄変更確認画面表示処理を行う。 <BR>
     * <BR>
     * 担保銘柄変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeConfirmResponse
     * @@roseuid 46DCC1360201
     */
    public WEB3AdminSLProductChangeConfirmResponse validateSLProductChange(
        WEB3AdminSLProductChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateSLProductChange(WEB3AdminSLProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeConfirmResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //担保銘柄変更サービスを取得し
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "validate担保銘柄変更処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "validate担保銘柄変更処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit担保銘柄変更)<BR>
     * 担保銘柄変更完了画面表示処理を行う。 <BR>
     * <BR>
     * 担保銘柄変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLProductChangeCompleteResponse
     * @@roseuid 46DCC194003F
     */
    public WEB3AdminSLProductChangeCompleteResponse submitSLProductChange(
        WEB3AdminSLProductChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitSLProductChange(WEB3AdminSLProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeCompleteResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //担保銘柄変更サービスを取得し
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保銘柄変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "submit担保銘柄変更処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "submit担保銘柄変更処理が失敗しました。",
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
