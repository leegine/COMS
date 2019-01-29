head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データアップロードハンドラ(WEB3AdminSrvRegiAccountDataUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者顧客データアップロードハンドラ)<BR>
 * サービス利用管理者顧客データアップロードハンドラ　@クラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUploadHandler.class);
    
    /**
     * @@roseuid 416F415C00AB
     */
    public WEB3AdminSrvRegiAccountDataUploadHandler() 
    {
     
    }
    
    /**
     * (顧客アップロード画面表示)<BR>
     * 顧客データアップロード画面処理を行う。<BR>
     * <BR>
     * 顧客データアップロード画面サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客データアップロード入力リクエスト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse
     * @@roseuid 412071840182
     */
    public WEB3AdminSrvRegiUploadInputResponse accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadInputResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データアップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "顧客アップロード画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (顧客アップロード中止)<BR>
     * 顧客データアップロード中止処理を行う。<BR>
     * <BR>
     * 顧客データアップロード中止サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客データアップロード中止リクエスト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse
     * @@roseuid 412072CF0163
     */
    public WEB3AdminSrvRegiUploadCancelResponse accountUploadDiscontinuation(WEB3AdminSrvRegiUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadDiscontinuation(WEB3AdminSrvRegiUploadCancelRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCancelResponse l_response = null;        
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;

        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データアップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "顧客アップロード中止に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (顧客アップロードファ@イル確認)<BR>
     * 顧客データアップロードファ@イル確認処理を行う。<BR>
     * <BR>
     * 顧客データアップロードファ@イル確認サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客データアップロードファ@イル確認リクエスト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse
     * @@roseuid 4120732101A2
     */
    public WEB3AdminSrvRegiUploadConfirmResponse accountUploadConfirm(WEB3AdminSrvRegiUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadConfirm(WEB3AdminSrvRegiUploadConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadConfirmResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;       
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データアップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "顧客アップロードファ@イル確認に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (顧客アップロード)<BR>
     * 顧客データアップロード処理を行う。<BR>
     * <BR>
     * 顧客データアップロードサービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客データアップロードリクエスト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse
     * @@roseuid 412073790338
     */
    public WEB3AdminSrvRegiUploadCompleteResponse accountUpload(WEB3AdminSrvRegiUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUpload(WEB3AdminSrvRegiUploadCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCompleteResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;       
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者顧客データアップロードサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "顧客アップロードに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
