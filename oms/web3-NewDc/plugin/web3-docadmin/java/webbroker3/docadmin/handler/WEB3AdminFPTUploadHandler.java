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
filename	WEB3AdminFPTUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧アップロードハンドラ(WEB3AdminFPTUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/07 武波 (中訊) 新規作成 モデル No.012,No.014
*/

package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCancelResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadConfirmResponse;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTUploadInputResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者金商法@交付閲覧アップロードハンドラ)<BR>
 * 管理者金商法@交付閲覧アップロードハンドラ<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTUploadHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTUploadHandler.class);

    /**
     * @@roseuid 4758B2790128
     */
    public WEB3AdminFPTUploadHandler()
    {

    }

    /**
     * (アップロード画面表示)<BR>
     * 金商法@交付閲覧アップロード入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧アップロードサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadInputResponse
     * @@roseuid 4737F458021C
     */
    public WEB3AdminFPTUploadInputResponse uploadScreenDisplay(WEB3AdminFPTUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadScreenDisplay(WEB3AdminAccOpenApplyUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadInputResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3AdminFPTUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロード入力処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (アップロードファ@イル確認)<BR>
     * 金商法@交付閲覧アップロードj確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧アップロードサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadConfirmResponse
     * @@roseuid 4737F46B0112
     */
    public WEB3AdminFPTUploadConfirmResponse uploadFileConfirm(WEB3AdminFPTUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadFileConfirm(WEB3AdminFPTUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadConfirmResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3AdminFPTUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロード確認処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (金商法@交付閲覧アップロード)<BR>
     * 金商法@交付閲覧アップロード完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧アップロードサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadCompleteResponse
     * @@roseuid 4737F47E0343
     */
    public WEB3AdminFPTUploadCompleteResponse adminFPTUpload(WEB3AdminFPTUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "adminFPTUpload(WEB3AdminFPTUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadCompleteResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3AdminFPTUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロード完了処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (アップロード中止)<BR>
     * 金商法@交付閲覧アップロード中止画面表示処理を行う。<BR>
     * <BR>
     * 管理者金商法@交付閲覧アップロードサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFPTUploadCancelResponse
     * @@roseuid 4737F49200B2
     */
    public WEB3AdminFPTUploadCancelResponse uploadCancel(WEB3AdminFPTUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME = "uploadCancel(WEB3AdminFPTUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTUploadCancelResponse l_response = null;
        WEB3AdminFPTUploadService l_service = null;
        try
        {
            //get service
            l_service =
                (WEB3AdminFPTUploadService)Services.getService(
                    WEB3AdminFPTUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response =
                (WEB3AdminFPTUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()メソッドをコールすることが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFPTUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者金商法@交付閲覧アップロード中止処理にエラーが発生しました。",
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
