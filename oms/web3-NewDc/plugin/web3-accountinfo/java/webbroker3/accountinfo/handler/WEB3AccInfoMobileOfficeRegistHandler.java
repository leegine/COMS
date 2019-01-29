head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報携帯番号・勤務先情報変更申込ハンドラ(WEB3AccInfoMobileOfficeRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報携帯番号・勤務先情報変更申込ハンドラ)<BR>
 * お客様情報携帯番号・勤務先情報変更申込ハンドラ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AccInfoMobileOfficeRegistHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistHandler.class);

    
    /**
     * @@roseuid 418F3A12000F
     */
    public WEB3AccInfoMobileOfficeRegistHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 携帯番号・勤務先情報変更申込入力画面表示処理を行う。 <BR>
     * <BR>
     * お客様情報携帯番号・勤務先情報変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 413FEF72036E
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse inputScreenDisplay(WEB3AccInfoMobileOfficeRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistInputResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更申込確認)<BR>
     * 携帯番号・勤務先情報変更申込確認処理を行う。 <BR>
     * <BR>
     * お客様情報携帯番号・勤務先情報変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistConfirmResponse
     * @@roseuid 413FEF72038D
     */
    public WEB3AccInfoMobileOfficeRegistConfirmResponse registConfirm(WEB3AccInfoMobileOfficeRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "registConfirm(WEB3AccinfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistConfirmResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスを取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更申込完了)<BR>
     * 携帯番号・勤務先情報変更申込完了処理を行う。 <BR>
     * <BR>
     * お客様情報携帯番号・勤務先情報変更申込サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報携帯番号・勤務先情報変更申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistCompleteResponse
     * @@roseuid 413FEF72039D
     */
    public WEB3AccInfoMobileOfficeRegistCompleteResponse registComplete(WEB3AccInfoMobileOfficeRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "registComplete(WEB3AccinfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistCompleteResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // お客様情報携帯番号・勤務先情報変更申込サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "お客様情報携帯番号・勤務先情報変更申込サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
