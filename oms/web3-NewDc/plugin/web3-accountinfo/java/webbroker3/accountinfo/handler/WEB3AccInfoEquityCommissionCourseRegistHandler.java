head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込ハンドラ(WEB3AccInfoEquityCommissionCourseRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報株式委託手数料コース変更申込ハンドラ)<BR>
 * お客様情報株式委託手数料コース変更申込ハンドラ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseRegistHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseRegistHandler.class);
    
    /**
     * @@roseuid 418F3A0C000F
     */
    public WEB3AccInfoEquityCommissionCourseRegistHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 株式委託手数料コース変更申込入力画面表示処理を行う。 <BR>
     * <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 413D5CB500D4
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse inputScreenDisplay(WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "inputScreenDisplay(WEB3AccInfoEquityCommissionCourseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // お客様情報株式委託手数料コース変更申込サービスを取得
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報株式委託手数料コース変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (変更申込確認)<BR>
     * 株式委託手数料コース変更申込確認処理を行う。 <BR>
     * <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse
     * @@roseuid 413D5CB500E4
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse registConfirm(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registConfirm(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeConfirmResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // お客様情報株式委託手数料コース変更申込サービスを取得
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報株式委託手数料コース変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (変更申込完了)<BR>
     * 株式委託手数料コース変更申込完了処理を行う。 <BR>
     * <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse
     * @@roseuid 413D5CB50103
     */
    public WEB3AccInfoEquityCommissionCourseChangeCompleteResponse registComplete(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registComplete(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCompleteResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // お客様情報株式委託手数料コース変更申込サービスを取得
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報株式委託手数料コース変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (申込取消確認) <BR>
     * 株式委託手数料コース変更申込取消確認処理を行う。 <BR>
     *  <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、 <BR>
     * execute()メソッドをコールする。  <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込取消確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse
     * @@roseuid 413D5CB50113
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse registCancelConfirm(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registCancelConfirm(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // お客様情報株式委託手数料コース変更申込サービスを取得
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報株式委託手数料コース変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (申込取消完了)<BR>
     * 株式委託手数料コース変更申込取消完了処理を行う。 <BR>
     * <BR>
     * お客様情報株式委託手数料コース変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報株式委託手数料コース変更申込取消完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse
     * @@roseuid 413D5CB50122
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse registConcelComplete(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registConcelComplete(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // お客様情報株式委託手数料コース変更申込サービスを取得
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報株式委託手数料コース変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報株式委託手数料コース変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
