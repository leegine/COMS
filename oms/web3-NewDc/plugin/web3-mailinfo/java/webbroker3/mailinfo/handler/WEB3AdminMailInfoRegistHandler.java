head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報登録ハンドラ(WEB3AdminMailInfoRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (メール情報登録ハンドラ)<BR>
 * メール情報登録ハンドラクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoRegistHandler implements MessageHandler 
{    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoRegistHandler.class);    
    
    /**
     * @@roseuid 416F1DD203D8
     */
    public WEB3AdminMailInfoRegistHandler() 
    {
     
    }
    
    /**
     * (confirmメール情報登録)<BR>
     * メール情報登録審査処理を行う。<BR>
     * <BR>
     * メール情報登録サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param  l_request - （リクエストデータ）<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistConfirmResponse
     * @@roseuid 413C2BBE0232
     */
    public WEB3AdminMailInfoRegistConfirmResponse confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoRegistConfirmResponse l_response = null;
        WEB3AdminMailInfoRegistService l_service = null;
        try
        {
            l_service = (WEB3AdminMailInfoRegistService)Services.getService(WEB3AdminMailInfoRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報登録ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;        
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "メール情報登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (completeメール情報登録)<BR>
     * メール情報登録処理を行う。<BR>
     * <BR>
     * メール情報登録サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param  l_request - (リクエストデータ)<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoRegistCompleteResponse
     * @@roseuid 413C2C3A0203
     */
    public WEB3AdminMailInfoRegistCompleteResponse completeMailInfoRegist(WEB3AdminMailInfoRegistCompleteRequest  l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoRegist(WEB3AdminMailInfoRegistConfirmRequest  l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoRegistCompleteResponse l_response = null;
        WEB3AdminMailInfoRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoRegistService)Services.getService(WEB3AdminMailInfoRegistService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報登録ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;        
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "メール情報登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
}
@
