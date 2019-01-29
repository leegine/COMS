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
filename	WEB3AdminFPTDocumentUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面更新ハンドラ(WEB3AdminFPTDocumentUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.039
*/
package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentUpdateInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付書面更新ハンドラ)<BR>
 * 管理者金商法@交付書面更新ハンドラ<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentUpdateHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentUpdateHandler.class);

    /**
     * @@roseuid 47CBC5AD0213
     */
    public WEB3AdminFPTDocumentUpdateHandler()
    {

    }

    /**
     * (get金商法@交付書面更新入力)<BR>
     * 金商法@交付書面更新入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付書面更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDocumentUpdateInputResponse
     * @@roseuid 47C25CED028F
     */
    public WEB3AdminFPTDocumentUpdateInputResponse getDocumentUpdateInput(
        WEB3AdminFPTDocumentUpdateInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateInput(WEB3AdminFPTDocumentUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateInputResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //管理者金商法@交付書面更新サービスを取得し
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付書面更新サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付書面更新確認)<BR>
     * 金商法@交付書面更新確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付書面更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDocumentUpdateConfirmResponse
     * @@roseuid 47C25E320156
     */
    public WEB3AdminFPTDocumentUpdateConfirmResponse getDocumentUpdateConfirm(
        WEB3AdminFPTDocumentUpdateConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateConfirm(WEB3AdminFPTDocumentUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateConfirmResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //管理者金商法@交付書面更新サービスを取得し
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付書面更新サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新確認画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新確認画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付書面更新完了)<BR>
     * 金商法@交付書面更新完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付書面更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDocumentUpdateCompleteResponse
     * @@roseuid 47C25E32037B
     */
    public WEB3AdminFPTDocumentUpdateCompleteResponse getDocumentUpdateComplete(
        WEB3AdminFPTDocumentUpdateCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "getDocumentUpdateComplete(WEB3AdminFPTDocumentUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentUpdateCompleteResponse l_response;
        WEB3AdminFPTDocumentUpdateService l_service;

        //管理者金商法@交付書面更新サービスを取得し
        try
        {
            l_service =
                (WEB3AdminFPTDocumentUpdateService)Services.getService(
                        WEB3AdminFPTDocumentUpdateService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付書面更新サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新完了画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面更新完了画面表示処理にエラーが発生しました。",
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
