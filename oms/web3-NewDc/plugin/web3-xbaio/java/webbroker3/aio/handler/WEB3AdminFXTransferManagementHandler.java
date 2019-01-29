head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替管理ハンドラクラス(WEB3AdminFXTransferManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 于美麗 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListConditionResponse;
import webbroker3.aio.message.WEB3AdminFXTransferListRequest;
import webbroker3.aio.message.WEB3AdminFXTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替管理ハンドラ) <BR>
 * FX振替管理ハンドラクラス
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXTransferManagementHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferManagementHandler.class);

    /**
     * (条件入力画面表示) <BR>
     * 振替条件入力画面表示処理を行う。 <BR>
     * <BR>
     * FX振替管理サービスを取得し、execute()メソッドをコールする。
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminFXTransferListConditionResponse
     * @@roseuid 41C0FA3303B7
     */
    public WEB3AdminFXTransferListConditionResponse condInputScreenDisplay(
        WEB3AdminFXTransferListConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "condInputScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        //FX振替管理サービスを取得し
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferListConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXTransferListConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "振替管理の条件入力画面表示データの取得処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (一覧画面表示) <BR>
     * 振替一覧画面表示処理を行う。 <BR>
     * <BR>
     * FX振替管理サービスを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminFXTransferListResponse
     * @@roseuid 41C0FA3E0118
     */
    public WEB3AdminFXTransferListResponse listScreenDisplay(
        WEB3AdminFXTransferListRequest l_request)
    {
        final String STR_METHOD_NAME = "listScreenDisplay()";
        log.entering(STR_METHOD_NAME);
        
        //FX振替管理サービスを取得し
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferListResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXTransferListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "振替管理の一覧画面表示データの取得処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (取消確認) <BR>
     * 振替取消確認処理を行う。 <BR>
     * <BR>
     * FX振替管理サービスを取得し、execute()メソッドをコールする。
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminFXTransferCancelConfirmResponse
     * @@roseuid 41C0FA440379
     */
    public WEB3AdminFXTransferCancelConfirmResponse cancelConfirm(
        WEB3AdminFXTransferCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "cancelConfirm()";
        log.entering(STR_METHOD_NAME);

        //FX振替管理サービスを取得し
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferCancelConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXTransferCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "振替管理の取消注文の確認が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (取消完了) <BR>
     * 振替取消完了処理を行う。 <BR>
     * <BR>
     * FX振替管理サービスを取得し、execute()メソッドをコールする。
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminFXTransferCancelCompleteResponse
     * @@roseuid 41C0FA480231
     */
    public WEB3AdminFXTransferCancelCompleteResponse cancelComplete(
        WEB3AdminFXTransferCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "cancelComplete()";
        log.entering(STR_METHOD_NAME);

        //FX振替管理サービスを取得し
        WEB3AdminFXTransferManagementService l_service = null;
        WEB3AdminFXTransferCancelCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXTransferManagementService) 
                Services.getService(WEB3AdminFXTransferManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXTransferCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXTransferCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "振替管理の注文取消の登録が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
