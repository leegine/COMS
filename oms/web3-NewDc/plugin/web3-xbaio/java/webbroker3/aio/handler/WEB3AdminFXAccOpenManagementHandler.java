head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設管理ハンドラクラス(WEB3AdminFXAccOpenManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
                 : 2006/02/08 譚漢江 (中訊) 仕様変更・モデル466
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyDownloadResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenApplyListResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenStatusUpdInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座開設管理ハンドラ) <BR>
 * 管理者FX口座開設管理ハンドラクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenManagementHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccOpenManagementHandler.class);

    /**
     * (get条件入力画面) <BR>
     * (管理者FX口座開設管理)口座開設申込検索条件入力 <BR>
     * 画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設管理サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。
     * 
     * @@param l_request - 管理者・FX口座開設申込一覧条件入力リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenApplyListConditionResponse
     * @@roseuid 41BD52F20183
     */
    public WEB3AdminFXAccOpenApplyListConditionResponse getCondInputScreen(
        WEB3AdminFXAccOpenApplyListConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座開設管理サービスを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyListConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccOpenApplyListConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座開設管理)入力画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get一覧画面) <BR>
     * (管理者FX口座開設管理)口座開設申込検索結果 <BR>
     * 一覧画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設管理サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。
     * @@param l_request - 管理者・FX口座開設申込一覧リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenApplyListResponse
     * @@roseuid 41BD534F025D
     */
    public WEB3AdminFXAccOpenApplyListResponse getListScreen(
        WEB3AdminFXAccOpenApplyListRequest l_request)
    {
        final String STR_METHOD_NAME = "getListScreen()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座開設管理サービスを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyListResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccOpenApplyListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座開設管理)口座開設申込検索結果一覧画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (getステータス更新入力画面) <BR>
     * (管理者FX口座開設管理)口座開設ステータス更新入力画面表示処理を行う。 <BR>
     * 管理者FX口座開設管理サービスImplを取得し 、 execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座開設ステータス更新入力リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdInputResponse
     * @@roseuid 41BD53B00106
     */
    public WEB3AdminFXAccOpenStatusUpdInputResponse getStatusUpdInputScreen(
        WEB3AdminFXAccOpenStatusUpdInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getStatusUpdInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座開設管理サービスを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdInputResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccOpenStatusUpdInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座開設管理)更新入力画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirmステータス更新) <BR>
     * (管理者FX口座開設管理)口座開設ステータス更新確認処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設管理サービスImplを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座開設ステータス更新確認リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdConfirmResponse
     * @@roseuid 41BD53E902EA
     */
    public WEB3AdminFXAccOpenStatusUpdConfirmResponse confirmStatusUpd(
        WEB3AdminFXAccOpenStatusUpdConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座開設管理サービスを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccOpenStatusUpdConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座開設管理)口座開設ステータス更新確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (completeステータス更新) <BR>
     * (管理者FX口座開設管理)口座開設ステータス更新完了処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設管理サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。
     * 
     * @@param l_request - 管理者・FX口座開設ステータス更新完了リクエストオブジェクト
     * @@return WEB3AdminFXAccOpenStatusUpdCompleteResponse
     * @@roseuid 41BD543F023E
     */
    public WEB3AdminFXAccOpenStatusUpdCompleteResponse completeStatusUpd(
        WEB3AdminFXAccOpenStatusUpdCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeStatusUpd()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座開設管理サービスを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenStatusUpdCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccOpenManagementService) 
                Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccOpenStatusUpdCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccOpenStatusUpdCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座開設管理)口座開設ステータス更新完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getダウンロードファ@イル)<BR>
     * 管理者FX口座開設申込ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * 管理者FX口座開設管理サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param  l_request - 管理者・FX口座開設申込ダウンロードリクエスト<BR>
     * @@return WEB3AdminFXAccOpenApplyDownloadResponse
     */
    public WEB3AdminFXAccOpenApplyDownloadResponse getDownloadFile(
        WEB3AdminFXAccOpenApplyDownloadRequest l_request)
    {
        final String STR_METHOD_NAME = "getDownloadFile(WEB3AdminFXAccOpenApplyDownloadRequest)";
        log.entering(STR_METHOD_NAME);

        //管理者FX口座開設管理サービスImplを取得し
        WEB3AdminFXAccOpenManagementService l_service = null;
        WEB3AdminFXAccOpenApplyDownloadResponse l_response = null;
        
        try
        {
            l_service = 
                (WEB3AdminFXAccOpenManagementService) Services.getService(WEB3AdminFXAccOpenManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座開設管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者FX口座開設申込ダウンロードファ@イルデータ取得処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenApplyDownloadResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者FX口座開設申込ダウンロードファ@イルデータ取得処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
