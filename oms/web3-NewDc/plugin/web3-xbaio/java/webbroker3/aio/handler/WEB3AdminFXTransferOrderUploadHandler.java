head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferOrderUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX振替注文アップロードハンドラ(WEB3AdminFXTransferOrderUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 鄭徳懇(中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXTransferUploadInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX振替注文アップロードハンドラ)<BR>
 * FX振替注文ULハンドラクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUploadHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadHandler.class);

    /**
     * @@roseuid 43FC1D4B004E
     */
    public WEB3AdminFXTransferOrderUploadHandler() 
    {
     
    }
    
    /**
     * (アップロード画面表示)<BR>
     * FX振替注文アップロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX振替注文アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXTransferUploadInputResponse
     * @@roseuid 43C5F4BE015B
     */
    public WEB3AdminFXTransferUploadInputResponse getUploadScreen(
        WEB3AdminFXTransferUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXTransferUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferUploadInputResponse l_response = null;
        WEB3AdminFXTransferOrderUploadService l_service = null;
        
        try
        {            
            //get管理者FX振替注文アップロードサービス
            l_service = 
                (WEB3AdminFXTransferOrderUploadService) Services.getService(WEB3AdminFXTransferOrderUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FX振替注文アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXTransferUploadInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード画面表示処理の実施に失敗しました。",
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
     * FX振替注文アップロード確認処理を行う。<BR> 
     * <BR>
     * 管理者FX振替注文アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXTransferUploadConfirmResponse
     * @@roseuid 43C5F4DE0300
     */
    public WEB3AdminFXTransferUploadConfirmResponse validateUploadFile(
        WEB3AdminFXTransferUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXTransferUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferUploadConfirmResponse l_response = null;
        WEB3AdminFXTransferOrderUploadService l_service = null;
        
        try
        {            
            //get管理者FX振替注文アップロードサービス
            l_service = 
                (WEB3AdminFXTransferOrderUploadService) Services.getService(WEB3AdminFXTransferOrderUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FX振替注文アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXTransferUploadConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (FX振替注文アップロード)<BR>
     * FX振替注文アップロード処理を行う。<BR> 
     * <BR>
     * 管理者FX振替注文アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXTransferUploadCompleteResponse
     * @@roseuid 43C5F4EE008F
     */
    public WEB3AdminFXTransferUploadCompleteResponse fxTransferOrderUpload(
        WEB3AdminFXTransferUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " fxTransferOrderUpload(WEB3AdminFXTransferUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferUploadCompleteResponse l_response = null;
        WEB3AdminFXTransferOrderUploadService l_service = null;
        
        try
        {            
            //get管理者FX振替注文アップロードサービス
            l_service = 
                (WEB3AdminFXTransferOrderUploadService) Services.getService(WEB3AdminFXTransferOrderUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FX振替注文アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXTransferUploadCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード完了処理の実施に失敗しました。",
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
     * FX振替注文アップロード中止処理を行う。 <BR>
     * <BR>
     * 管理者FX振替注文アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXTransferUploadCancelResponse
     * @@roseuid 43C5F4FC0031
     */
    public WEB3AdminFXTransferUploadCancelResponse undoUploadStop(WEB3AdminFXTransferUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " undoUploadStop(WEB3AdminFXTransferUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXTransferUploadCancelResponse l_response = null;
        WEB3AdminFXTransferOrderUploadService l_service = null;
        
        try
        {            
            //get管理者FX振替注文アップロードサービス
            l_service = 
                (WEB3AdminFXTransferOrderUploadService) Services.getService(WEB3AdminFXTransferOrderUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FX振替注文アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXTransferUploadCancelResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード中止処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXTransferUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX振替注文ＵＬ)アップロード中止処理の実施に失敗しました。",
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
