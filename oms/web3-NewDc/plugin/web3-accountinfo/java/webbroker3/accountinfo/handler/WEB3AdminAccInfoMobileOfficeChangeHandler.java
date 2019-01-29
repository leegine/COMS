head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更ハンドラ(WEB3AdminAccInfoMobileOfficeChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更ハンドラ)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更ハンドラ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeChangeHandler.class);

    
    /**
     * @@roseuid 418F3A100261
     */
    public WEB3AdminAccInfoMobileOfficeChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 携帯番号・勤務先情報変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse
     * @@roseuid 4148FCE4010D
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistInputResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更確認)<BR>
     * 携帯番号・勤務先情報変更確認処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更確認リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse
     * @@roseuid 4148FCE4011D
     */
    public WEB3AdminAccInfoMobileOfficeRegistConfirmResponse changeConfirm(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeConfirm(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistConfirmResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更完了)<BR>
     * 携帯番号・勤務先情報変更完了処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更完了リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse
     * @@roseuid 4148FCE4013C
     */
    public WEB3AdminAccInfoMobileOfficeRegistCompleteResponse changeComplete(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeComplete(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistCompleteResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報携帯番号・勤務先情報変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
