head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginRejectIPManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ログイン拒否IP管理ハンドラ(WEB3AdminTMLoginRejectIPManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 安陽(中訊) 新規作成 モデルNo.004
*/

package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者ログイン拒否IP管理ハンドラ)<BR>
 * 管理者ログイン拒否IP管理ハンドラクラス<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3AdminTMLoginRejectIPManagementHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginRejectIPManagementHandler.class);

    /**
     * @@roseuid 48D75CD9000E
     */
    public WEB3AdminTMLoginRejectIPManagementHandler()
    {

    }

    /**
     * (get一覧画面)<BR>
     * ログイン拒否IP一覧画面表示処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP一覧リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlListResponse
     * @@roseuid 48C7770D0387
     */
    public WEB3AdminTraderAdminIPControlListResponse getListScreen(
        WEB3AdminTraderAdminIPControlListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getListScreen(WEB3AdminTraderAdminIPControlListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlListResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP一覧画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP一覧画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get登録画面)<BR>
     * ログイン拒否IP登録画面表示処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistInputResponse
     * @@roseuid 48C7770D0389
     */
    public WEB3AdminTraderAdminIPControlRegistInputResponse getRegistScreen(
        WEB3AdminTraderAdminIPControlRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getRegistScreen(WEB3AdminTraderAdminIPControlRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlRegistInputResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate登録)<BR>
     * ログイン拒否IP登録確認処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistConfirmResponse
     * @@roseuid 48C7770D038B
     */
    public WEB3AdminTraderAdminIPControlRegistConfirmResponse validateRegist(
        WEB3AdminTraderAdminIPControlRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateRegist(WEB3AdminTraderAdminIPControlRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlRegistConfirmResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit登録)<BR>
     * ログイン拒否IP登録完了処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlRegistCompleteResponse
     * @@roseuid 48C7770D038D
     */
    public WEB3AdminTraderAdminIPControlRegistCompleteResponse submitRegist(
        WEB3AdminTraderAdminIPControlRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitRegist(WEB3AdminTraderAdminIPControlRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlRegistCompleteResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get変更画面)<BR>
     * ログイン拒否IP登録情報変更画面表示処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateInputResponse
     * @@roseuid 48C7770D038F
     */
    public WEB3AdminTraderAdminIPControlUpdateInputResponse getChangedScreen(
        WEB3AdminTraderAdminIPControlUpdateInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getChangedScreen(WEB3AdminTraderAdminIPControlUpdateInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlUpdateInputResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate変更)<BR>
     * ログイン拒否IP登録情報変更確認処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateConfirmResponse
     * @@roseuid 48C7770D0391
     */
    public WEB3AdminTraderAdminIPControlUpdateConfirmResponse validateChange(
        WEB3AdminTraderAdminIPControlUpdateConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateChange(WEB3AdminTraderAdminIPControlUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlUpdateConfirmResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit変更)<BR>
     * ログイン拒否IP登録情報変更完了処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報変更完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlUpdateCompleteResponse
     * @@roseuid 48C7770D0397
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteResponse submitChange(
        WEB3AdminTraderAdminIPControlUpdateCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitChange(WEB3AdminTraderAdminIPControlUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlUpdateCompleteResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報変更完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate削除)<BR>
     * ログイン拒否IP登録情報削除確認処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報削除確認リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteConfirmResponse
     * @@roseuid 48C7770D0399
     */
    public WEB3AdminTraderAdminIPControlDeleteConfirmResponse validateDelete(
        WEB3AdminTraderAdminIPControlDeleteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateDelete(WEB3AdminTraderAdminIPControlDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlDeleteConfirmResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報削除確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報削除確認処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit削除)<BR>
     * ログイン拒否IP登録情報削除完了処理を行う。<BR>
     * <BR>
     * 管理者ログイン拒否IP管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・ログイン拒否IP登録情報削除完了リクエストオブジェクト<BR>
     * @@return WEB3AdminTraderAdminIPControlDeleteCompleteResponse
     * @@roseuid 48C7770D039B
     */
    public WEB3AdminTraderAdminIPControlDeleteCompleteResponse submitDelete(
        WEB3AdminTraderAdminIPControlDeleteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitDelete(WEB3AdminTraderAdminIPControlDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMLoginRejectIPManagementService l_service = null;
        WEB3AdminTraderAdminIPControlDeleteCompleteResponse l_response = null;

        try
        {
            //管理者ログイン拒否IP管理サービスImplを取得
            l_service =
                (WEB3AdminTMLoginRejectIPManagementService)Services.getService(
                    WEB3AdminTMLoginRejectIPManagementService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "管理者ログイン拒否IP管理サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報削除完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminIPControlDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "ログイン拒否IP登録情報削除完了処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
