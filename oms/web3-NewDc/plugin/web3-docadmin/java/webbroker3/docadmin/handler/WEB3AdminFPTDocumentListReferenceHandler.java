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
filename	WEB3AdminFPTDocumentListReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付書面照会ハンドラ(WEB3AdminFPTDocumentListReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 馮海濤 (中訊) 新規作成 モデルNo.040
*/
package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDocumentListSearchInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDocumentListReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付書面照会ハンドラ)<BR>
 * 管理者金商法@交付書面照会ハンドラ<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3AdminFPTDocumentListReferenceHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDocumentListReferenceHandler.class);

    /**
     * @@roseuid 47CBC5AD02AF
     */
    public WEB3AdminFPTDocumentListReferenceHandler()
    {

    }

    /**
     * (get金商法@交付書面照会検索入力)<BR>
     * 金商法@交付書面照会検索入力画面表示処理を行う。  <BR>
     * <BR>
     * 管理者金商法@交付書面照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDocumentListSearchInputResponse
     * @@roseuid 47C26217003F
     */
    public WEB3AdminFPTDocumentListSearchInputResponse getDocumentReferenceSearchInput(
        WEB3AdminFPTDocumentListSearchInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getDocumentReferenceSearchInput(WEB3AdminFPTDocumentListSearchInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListSearchInputResponse l_response;
        WEB3AdminFPTDocumentListReferenceService l_service;

        //管理者金商法@交付書面照会サービスを取得し
        try
        {
            l_service =
                (WEB3AdminFPTDocumentListReferenceService)Services.getService(
                    WEB3AdminFPTDocumentListReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付書面照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminFPTDocumentListSearchInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面照会検索入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListSearchInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面照会検索入力画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付書面照会一覧)<BR>
     * 金商法@交付書面照会一覧画面表示処理を行う。  <BR>
     * <BR>
     * 管理者金商法@交付書面照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDocumentListReferenceResponse
     * @@roseuid 47C2621900CF
     */
    public WEB3AdminFPTDocumentListReferenceResponse getDocumentReferenceList(
        WEB3AdminFPTDocumentListReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getDocumentReferenceList(WEB3AdminFPTDocumentListReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDocumentListReferenceResponse l_response;
        WEB3AdminFPTDocumentListReferenceService l_service;

        //管理者金商法@交付書面照会サービスを取得し
        try
        {
            l_service =
                (WEB3AdminFPTDocumentListReferenceService)Services.getService(
                        WEB3AdminFPTDocumentListReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付書面照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminFPTDocumentListReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面照会一覧画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminFPTDocumentListReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "金商法@交付書面照会一覧画面表示処理にエラーが発生しました。",
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
