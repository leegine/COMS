head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecTriggerIssueHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー発行処理ハンドラ(WEB3AdminDirSecTriggerIssueHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 許丹(中訊) 新規作成 モデルNo.116、No.118、No.119
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー発行処理ハンドラ)<BR>
 * トリガー発行情報ハンドラクラス。<BR>
 * <BR>
 * @@author 許丹
 * @@version 1.0
 */
public class WEB3AdminDirSecTriggerIssueHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecTriggerIssueHandler.class);

    /**
     * @@roseuid 4806E05401B7
     */
    public WEB3AdminDirSecTriggerIssueHandler()
    {

    }

    /**
     * (getトリガー発行処理一覧画面表示)<BR>
     * トリガー発行処理一覧画面表示を行う。<BR>
     * <BR>
     * トリガー発行処理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理一覧リクエストクラス<BR>
     * @@return WEB3AdminDirSecTriggerIssueListResponse
     * @@roseuid 47C27C2D004F
     */
    public WEB3AdminDirSecTriggerIssueListResponse getTriggerIssueListScreenDisplay(
        WEB3AdminDirSecTriggerIssueListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueListScreenDisplay(WEB3AdminDirSecTriggerIssueListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminDirSecTriggerIssueListResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "トリガー発行処理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理一覧画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理一覧画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getトリガー発行処理入力画面表示)<BR>
     * トリガー発行処理入力画面表示を行う。<BR>
     * <BR>
     * トリガー発行処理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理入力リクエストクラス<BR>
     * @@return WEB3AdminDirSecTriggerIssueInputResponse
     * @@roseuid 47C27C2D005F
     */
    public WEB3AdminDirSecTriggerIssueInputResponse getTriggerIssueInputScreenDisplay(
        WEB3AdminDirSecTriggerIssueInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getTriggerIssueInputScreenDisplay(WEB3AdminDirSecTriggerIssueInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueInputResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "トリガー発行処理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validateトリガー発行処理確認画面表示)<BR>
     * トリガー発行処理確認画面表示を行う。<BR>
     * <BR>
     * トリガー発行処理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理確認リクエストクラス<BR>
     * @@return WEB3AdminDirSecTriggerIssueConfirmResponse
     * @@roseuid 47C27C2D0060
     */
    public WEB3AdminDirSecTriggerIssueConfirmResponse validateTriggerIssueConfirmScreenDisplay(
        WEB3AdminDirSecTriggerIssueConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateTriggerIssueConfirmScreenDisplay(WEB3AdminDirSecTriggerIssueConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueConfirmResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "トリガー発行処理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理確認画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理確認画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitトリガー発行処理完了画面表示)<BR>
     * トリガー発行処理完了画面表示処理を行う。<BR>
     * <BR>
     * トリガー発行処理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・トリガー発行処理完了リクエストクラス<BR>
     * @@return WEB3AdminDirSecTriggerIssueCompleteResponse
     * @@roseuid 47C27C2D006F
     */
    public WEB3AdminDirSecTriggerIssueCompleteResponse submitTriggerIssueCompleteScreenDisplay(
        WEB3AdminDirSecTriggerIssueCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitTriggerIssueCompleteScreenDisplay(WEB3AdminDirSecTriggerIssueCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminDirSecTriggerIssueCompleteResponse l_response = null;
        WEB3AdminDirSecTriggerIssueService l_service = null;

        try
        {
            l_service = (WEB3AdminDirSecTriggerIssueService)Services.getService(
                WEB3AdminDirSecTriggerIssueService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "トリガー発行処理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理完了画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminDirSecTriggerIssueCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "トリガー発行処理完了画面に失敗しました。",
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
