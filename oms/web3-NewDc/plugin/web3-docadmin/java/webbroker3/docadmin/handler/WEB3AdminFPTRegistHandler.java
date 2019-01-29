head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧登録ハンドラ(WEB3AdminFPTRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 柴双紅 (中訊) 新規作成
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTRegistInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧登録ハンドラ)<BR>
 * 管理者金商法@交付閲覧登録ハンドラ<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminFPTRegistHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTRegistHandler.class);

    /**
     * @@roseuid 46FDDD3E009C
     */
    public WEB3AdminFPTRegistHandler()
    {

    }

    /**
     * (get金商法@交付閲覧登録入力)<BR>
     * 金商法@交付閲覧登録入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTRegistInputResponse
     */
    public WEB3AdminFPTRegistInputResponse getRegistInput(WEB3AdminFPTRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getRegistInput(WEB3AdminFPTRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTRegistInputResponse l_response;
        WEB3AdminFPTRegistService l_service;
        try
        {
            l_service =
                (WEB3AdminFPTRegistService)Services.getService(
                    WEB3AdminFPTRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminFPTRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付閲覧登録確認)<BR>
     * 金商法@交付閲覧登録確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTRegistConfirmResponse
     */
    public WEB3AdminFPTRegistConfirmResponse getRegistConfirm(WEB3AdminFPTRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " getRegistConfirm(WEB3AdminFPTRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTRegistConfirmResponse l_response;
        WEB3AdminFPTRegistService l_service;
        try
        {
            l_service =
                (WEB3AdminFPTRegistService)Services.getService(
                    WEB3AdminFPTRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminFPTRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録確認画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録確認画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付閲覧登録完了)<BR>
     * 金商法@交付閲覧登録完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧登録サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTRegistCompleteResponse
     */
    public WEB3AdminFPTRegistCompleteResponse getRegistComplete(WEB3AdminFPTRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " getRegistComplete(WEB3AdminFPTRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTRegistCompleteResponse l_response;
        WEB3AdminFPTRegistService l_service;
        try
        {
            l_service =
                (WEB3AdminFPTRegistService)Services.getService(
                    WEB3AdminFPTRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminFPTRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録完了画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付閲覧登録完了画面表示処理にエラーが発生しました。",
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
