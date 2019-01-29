head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailDistributionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客まさ情報案内メール配信指示ハンドラ(WEB3AdminAccInfoMailDistributionHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客まさ情報案内メール配信指示ハンドラ)<BR>
 * 管理者お客まさ情報案内メール配信指示ハンドラ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */

public class WEB3AdminAccInfoMailDistributionHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailDistributionHandler.class);
        
    public WEB3AdminAccInfoMailDistributionHandler()
    {
        
    }
    /**
     * (配信指示画面表示)<BR>
     * 案内メール配信指示画面表示処理を行う。<BR>
     *<BR>
     *管理者お客様情報案内メール配信指示サービスを取得し、<BR>
     *execute()メソッドをコール<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA301D8
     */
    public WEB3AdminAccInfoMailDistributionResponse getMailDistributionScreen(WEB3AdminAccInfoMailDistributionRequest l_request)
    {
        final String STR_METHOD_NAME = "getMailDistributionScreen(WEB3AccInfoMailDistributionRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // 管理者お客様情報案内メール配信指示サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate配信指示)<BR>
     * 案内メール配信指示確認処理を行う。<BR> 
     *<BR>
     *管理者お客様情報案内メール配信指示サービスを取得し、<BR>
     *execute()メソッドをコールする。<BR>
     * @@param l_request - :管理者お客様情報案内メール配信指示確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA302D8 
     */
    public WEB3AdminAccInfoMailDistributionConfirmResponse validateMailDistribution(WEB3AdminAccInfoMailDistributionConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateMailDistribution(WEB3AccInfoMailDistributionConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionConfirmResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // 管理者お客様情報案内メール配信指示サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // 管理者お客様情報案内メール配信指示サービスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submit配信指示)<BR>
     *案内メール配信指示完了処理を行う。<BR> 
     *<BR>
     *管理者お客様情報案内メール配信指示サービスを取得し、<BR>
     *execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA303D8 
     */
    public WEB3AdminAccInfoMailDistributionCompleteResponse submintMailDistribution(WEB3AdminAccInfoMailDistributionCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submintMailDistribution(WEB3AccInfoMailDistributionCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCompleteResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // 管理者お客様情報案内メール配信指示サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // 管理者お客様情報案内メール配信指示サービスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (validate配信取消)<BR>
     * 案内メール配信指示取消確認処理を行う。<BR> 
     *<BR>
     *管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示取消確認リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA304D8
     */
    public WEB3AdminAccInfoMailDistributionCancelConfirmResponse validateMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateMailDistributionCancle(WEB3AccInfoMailDistributionCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCancelConfirmResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // 管理者お客様情報案内メール配信指示サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // 管理者お客様情報案内メール配信指示サービスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (submit配信取消)<BR>
     * 案内メール配信指示取消完了処理を行う。<BR> 
     *<BR>
     *管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報案内メール配信指示取消完了リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailDistributionCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA305D8
     */
    public WEB3AdminAccInfoMailDistributionCancelCompleteResponse submitMailDistributionCancle(WEB3AdminAccInfoMailDistributionCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitMailDistributionCancle(WEB3AccInfoMailDistributionCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailDistributionCancelCompleteResponse l_response = null;
        WEB3AdminAccInfoMailDistributionService l_service = null;
        
        // 管理者お客様情報案内メール配信指示サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMailDistributionService)Services.getService(WEB3AdminAccInfoMailDistributionService.class);            
            
        }
        // 管理者お客様情報案内メール配信指示サービスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報案内メール配信指示サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMailDistributionCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報案内メール配信指示サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
