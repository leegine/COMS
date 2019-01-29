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
filename	WEB3AdminFPTDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧削除ハンドラ(WEB3AdminFPTDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/11/06 武波 (中訊) 新規作成 仕様変更・モデル No.010
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTDeleteConfirmResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧削除ハンドラ)<BR>
 * 管理者金商法@交付閲覧削除ハンドラクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTDeleteHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTDeleteHandler.class);

    /**
     * @@roseuid 472FC5B60363
     */
    public WEB3AdminFPTDeleteHandler()
    {

    }

    /**
     * (get金商法@交付閲覧削除確認)<BR>
     * 金商法@交付閲覧削除確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧削除サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDeleteConfirmResponse
     * @@roseuid 4726CF3A0216
     */
    public WEB3AdminFPTDeleteConfirmResponse getDeleteConfirm(
        WEB3AdminFPTDeleteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " getDeleteConfirm(WEB3AdminFPTDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDeleteConfirmResponse l_response;
        WEB3AdminFPTDeleteService l_service;

        //管理者金商法@交付閲覧削除サービスImplを取得し
        try
        {
            l_service = (WEB3AdminFPTDeleteService)Services.getService(
                WEB3AdminFPTDeleteService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者金商法@交付閲覧削除サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧削除確認処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get金商法@交付閲覧削除完了)<BR>
     * 金商法@交付閲覧削除完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧削除サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTDeleteCompleteResponse
     * @@roseuid 4726CF3C0169
     */
    public WEB3AdminFPTDeleteCompleteResponse getDeleteComplete(
        WEB3AdminFPTDeleteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " getDeleteComplete(WEB3AdminFPTDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTDeleteCompleteResponse l_response;
        WEB3AdminFPTDeleteService l_service;

        //管理者金商法@交付閲覧削除サービスImplを取得し
        try
        {
            l_service = (WEB3AdminFPTDeleteService)Services.getService(
                WEB3AdminFPTDeleteService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者金商法@交付閲覧削除サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧削除完了処理にエラーが発生しました。",
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
