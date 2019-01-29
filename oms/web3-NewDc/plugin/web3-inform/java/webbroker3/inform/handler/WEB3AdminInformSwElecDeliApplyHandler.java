head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座切替・電子交付申込ハンドラクラス（WEB3AdminInformSwElecDeliApplyHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 金傑（中訊）新規作成 モデルNo.099
Revision History : 2007/09/20 トウ鋒鋼（中訊）モデルNo.112
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座切替・電子交付申込ハンドラ)<BR>
 * 管理者口座切替・電子交付申込ハンドラクラス<BR>
 * <BR>
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyHandler.class);

    public WEB3AdminInformSwElecDeliApplyHandler()
    {

    }

    /**
     *（検索画面表示）<BR>
     * 管理者口座切替・電子交付申込検索画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込検索リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplySrcResponse
     */
    public WEB3AdminInformAccSwElecDeliApplySrcResponse searchScreenDisplay(
        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request)
    {
        final String STR_METHOD_NAME = "searchScreenDisplay(WEB3AdminInformAccSwElecDeliApplySrcRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者口座切替・電子交付申込検索に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（入力画面表示）<BR>
     * 管理者口座切替・電子交付申込入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込入力リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyInpResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyInpResponse displayInputScreen(
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request)
    {
        final String STR_METHOD_NAME = "displayInputScreen(WEB3AdminInformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者口座切替・電子交付申込入力画面に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（申込確認）<BR>
     * 管理者口座切替・電子交付申込確認処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込確認リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyConfResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyConfResponse applyConfirm(
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request)
    {
        final String STR_METHOD_NAME = "applyConfirm(WEB3AdminInformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者口座切替・電子交付申込申込確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（申込完了）<BR>
     * 管理者口座切替・電子交付申込完了処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・口座切替・電子交付申込完了リクエスト
     * @@return WEB3AdminInformAccSwElecDeliApplyCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyCmpResponse applyComplete(
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "applyComplete(WEB3AdminInformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者口座切替・電子交付申込申込完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（照会画面表示）<BR>
     * 管理者口座切替・電子交付申込照会画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyRefResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyRefResponse displayReferenceScreen(
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request)
    {
        final String STR_METHOD_NAME = "displayReferenceScreen(WEB3AdminInformAccSwElecDeliApplyRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込参照に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込参照に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（変更確認）<BR>
     * 管理者口座切替・電子交付申込変更確認処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyChangeConfResponse
     */
    public WEB3AdminInformAccSwElecDeliChangeConfResponse changeConfirm(
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request)
    {
        final String STR_METHOD_NAME = "changeConfirm(WEB3AdminInformAccSwElecDeliChangeConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込変更確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込変更確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（変更完了）<BR>
     * 管理者口座切替・電子交付申込変更完了処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse changeComplete(
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "changeComplete(WEB3AdminInformAccSwElecDeliChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込変更完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込変更完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（取消確認）<BR>
     * 管理者口座切替・電子交付申込取消確認処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteConfResponse
     */
    public WEB3AdminInformAccSwElecDeliDeleteConfResponse deleteConfirm(
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteConfirm(WEB3AdminInformAccSwElecDeliDeleteConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込取消確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込取消確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    /**
     *（取消完了）<BR>
     * 管理者口座切替・電子交付申込取消完了処理を行う。<BR>
     * <BR>
     * 管理者口座切替・電子交付申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliDeleteCmpResponse deleteComplete(
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteComplete(WEB3AdminInformAccSwElecDeliDeleteCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // 管理者口座切替・電子交付申込サービスを取得
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座切替・電子交付申込サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // 管理者口座切替・電子交付申込サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込取消完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者・口座切替・電子交付申込取消完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
