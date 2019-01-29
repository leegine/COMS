head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報変更ハンドラ(WEB3AdminMailInfoChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
Revesion History : 2004/10/19  奚靖(中訊) 作成
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoChangeInputRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( メール情報変更ハンドラ )<BR>
 * <BR>
 * メール情報変更ハンドラクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeHandler implements MessageHandler 
{    
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoChangeHandler.class);
    
    /**
     * @@roseuid 416F1DD302DE
     */
    public WEB3AdminMailInfoChangeHandler() 
    {
     
    }
    
    /**
     * ( メール情報変更入力画面リクエスト )<BR>
     * <BR>
     * メール情報入力画面表示処理を行う。<BR>
     * <BR>
     * メール情報変更サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - ( リクエストデータ )<BR>
     * <BR>
     * メール情報変更入力画面リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoCommonResponse
     * @@roseuid 413D55C203AA
     */
    public WEB3AdminMailInfoCommonResponse mailInfoChangeInputScreenRequest(WEB3AdminMailInfoChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailInfoChangeInputScreenRequest(WEB3AdminMailInfoChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoCommonResponse l_response = null; 
        WEB3AdminMailInfoChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoChangeService)Services.getService(WEB3AdminMailInfoChangeService.class); 
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoCommonResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報変更ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3AdminMailInfoCommonResponse)l_service.execute(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoCommonResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "メール情報変更に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
         
         log.exiting(STR_METHOD_NAME);
         return l_response;
    }
    
    /**
     * ( confirmメール情報変更 )<BR>
     * <BR>
     * メール情報変更審査処理を行う。<BR>
     * <BR>
     * メール情報変更サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoChangeConfirmResponse
     * @@roseuid 413C345A0177
     */
    public WEB3AdminMailInfoChangeConfirmResponse confirmMailInfoChange(WEB3AdminMailInfoChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmMailInfoChange(WEB3AdminMailInfoChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoChangeConfirmResponse l_response = null; 
        WEB3AdminMailInfoChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoChangeService)Services.getService(WEB3AdminMailInfoChangeService.class); 
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80018 ;
            log.error(l_request, "メール情報変更ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
         
         try
         {
             l_response = (WEB3AdminMailInfoChangeConfirmResponse)l_service.execute(l_request);
            
         }
         catch(WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminMailInfoChangeConfirmResponse)l_request.createResponse();
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(l_request, "メール情報変更ハンドラサービスに失敗しました。", l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         
         log.exiting(STR_METHOD_NAME);
         return l_response;
    }
    
    /**
     * ( completeメール情報変更 )<BR>
     * <BR>
     * メール情報変更処理を行う。<BR>
     * <BR>
     * メール情報変更サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - （リクエストデータ）<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoChangeCompleteResponse
     * @@roseuid 413C349700DA
     */
    public WEB3AdminMailInfoChangeCompleteResponse completeMailInfoChange(WEB3AdminMailInfoChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeMailInfoChange(WEB3AdminMailInfoChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoChangeCompleteResponse l_response = null; 
        WEB3AdminMailInfoChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoChangeService)Services.getService(WEB3AdminMailInfoChangeService.class); 
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80018 ;
            log.error(l_request, "メール情報変更ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
         
         try
         {
             l_response = (WEB3AdminMailInfoChangeCompleteResponse)l_service.execute(l_request);
            
         }
         catch(WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminMailInfoChangeCompleteResponse)l_request.createResponse();
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(l_request, "メール情報変更ハンドラサービスに失敗しました。", l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         
         log.exiting(STR_METHOD_NAME);
         return l_response;        
    }
}
@
