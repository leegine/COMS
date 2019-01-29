head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座開設アップロードハンドラ(WEB3AdminFXAccOpenUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 鄭徳懇(中訊) 新規作成
                   2006/02/28 情野(SRA) 受入テスト障害U02769対応
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座開設アップロードハンドラ)<BR>
 * 管理者FX口座開設アップロードハンドラクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadHandler implements MessageHandler 
{
   
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadHandler.class);
    
    /**
     * @@roseuid 43F49A6D0138
     */
    public WEB3AdminFXAccOpenUploadHandler() 
    {
     
    }
    
    /**
     * (getアップロード画面)<BR>
     * アップロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadInputResponse
     * @@roseuid 43E041A90210
     */
    public WEB3AdminFXAccOpenUploadInputResponse getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest l_request)
        
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadInputResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get管理者FX口座開設アップロードサービス
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "管理者FX口座開設アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロードファ@イル)<BR>
     * アップロードファ@イル確認処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設アップロードサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadConfirmResponse
     * @@roseuid 43E041C403A7
     */
    public WEB3AdminFXAccOpenUploadConfirmResponse validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadConfirmResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get管理者FX口座開設アップロードサービス
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "管理者FX口座開設アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロードファ@イル確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロードファ@イル確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロード)<BR>
     * アップロード完了処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設アップロードサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadCompleteResponse
     * @@roseuid 43E041D100E8
     */
    public WEB3AdminFXAccOpenUploadCompleteResponse submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadCompleteResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get管理者FX口座開設アップロードサービス
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "管理者FX口座開設アップロードサービスの取得に失敗しました。", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * アップロード中止処理を行う。 <BR>
     * <BR>
     * 管理者FX口座開設アップロードﾞサービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminFXAccOpenUploadCancelResponse
     * @@roseuid 43E041E3027E
     */
    public WEB3AdminFXAccOpenUploadCancelResponse undoUpload(WEB3AdminFXAccOpenUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadCancelResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get管理者FX口座開設アップロードサービス
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "管理者FX口座開設アップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード中止処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(管理者FX口座開設アップロード)アップロード中止処理の実施に失敗しました。",
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
