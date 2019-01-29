head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理ハンドラ(WEB3AdminBondDomesticRecruitLimitManageHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.214,モデルNo.242
*/
package webbroker3.bd.handler;

import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (管理者国内債券部店別応募枠管理ハンドラ)<BR>
 * 管理者国内債券部店別応募枠管理ハンドラクラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageHandler.class);

    /**
     * @@roseuid 46A473FD0203
     */
    public WEB3AdminBondDomesticRecruitLimitManageHandler()
    {

    }

    /**
     * (input応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理入力処理を行う。<BR>
     * <BR>
     * <BR>
     * 管理者国内債券部店別応募枠管理サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageInputResponse
     * @@roseuid 468D97CD037E
     */
    public WEB3AdminBondDomesticRecruitLimitManageInputResponse inputRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            " inputRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticRecruitLimitManageService l_service = null;
        WEB3AdminBondDomesticRecruitLimitManageInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticRecruitLimitManageService)Services.getService(
                    WEB3AdminBondDomesticRecruitLimitManageService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者国内債券部店別応募枠管理サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "管理者国内債券部店別応募枠管理入力処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理確認処理を行う。<BR>
     * <BR>
     * <BR>
     * 管理者国内債券部店別応募枠管理サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageConfirmResponse
     * @@roseuid 468D993F0201
     */
    public WEB3AdminBondDomesticRecruitLimitManageConfirmResponse validateRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            " validateRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticRecruitLimitManageService l_service = null;
        WEB3AdminBondDomesticRecruitLimitManageConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticRecruitLimitManageService)Services.getService(
                    WEB3AdminBondDomesticRecruitLimitManageService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者国内債券部店別応募枠管理サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            String l_strErrorCode = WEB3ErrorCatalog.BUSINESS_ERROR_02885.error_code;
            if (l_strErrorCode.equals(l_ex.getErrorInfo().error_code))
            {
                if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }

            log.error(l_request,
                "管理者国内債券部店別応募枠管理確認処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit応募枠管理)<BR>
     * 管理者国内債券部店別応募枠管理完了処理を行う。<BR>
     * <BR>
     * <BR>
     * 管理者国内債券部店別応募枠管理サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticRecruitLimitManageCompleteResponse
     * @@roseuid 468D990101CE
     */
    public WEB3AdminBondDomesticRecruitLimitManageCompleteResponse submitRecruitLimitManage(
        WEB3AdminBondDomesticRecruitLimitManageCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            " submitRecruitLimitManage(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticRecruitLimitManageService l_service = null;
        WEB3AdminBondDomesticRecruitLimitManageCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminBondDomesticRecruitLimitManageService)Services.getService(
                    WEB3AdminBondDomesticRecruitLimitManageService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者国内債券部店別応募枠管理サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticRecruitLimitManageCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            String l_strErrorCode = WEB3ErrorCatalog.BUSINESS_ERROR_02885.error_code;
            if (l_strErrorCode.equals(l_ex.getErrorInfo().error_code))
            {
                if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }
            log.error(l_request,
                "管理者国内債券部店別応募枠管理完了処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
