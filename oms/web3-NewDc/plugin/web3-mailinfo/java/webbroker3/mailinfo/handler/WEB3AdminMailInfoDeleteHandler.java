head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報削除ハンドラ(WEB3AdminMailInfoDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDeleteService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( メール情報削除ハンドラ )<BR>
 * <BR>
 * メール情報削除ハンドラクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteHandler implements MessageHandler 
{    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static  WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDeleteHandler.class);    
   
    /**
     * @@roseuid 416F1DD1035B
     */
    public WEB3AdminMailInfoDeleteHandler() 
    {
     
    }
    
    /**
     * ( confirmメール情報削除 )<BR>
     * <BR>
     * メール情報削除審査処理を行う。<BR>
     * <BR>
     * メール情報削除サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - ( リクエストデータ )<BR>
     * <BR>
     * メール情報削除確認リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteConfirmResponse
     * @@roseuid 413C3DA70271
     */
    public WEB3AdminMailInfoDeleteConfirmResponse confirmMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmMailInfoDelete(WEB3AdminMailInfoDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoDeleteConfirmResponse l_response = null;
        WEB3AdminMailInfoDeleteService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoDeleteService)Services.getService(WEB3AdminMailInfoDeleteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報削除ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch  (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "メール情報削除に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }     
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * ( completeメール情報削除 )<BR>
     * <BR>
     * メール情報削除処理を行う。<BR>
     * <BR>
     * メール情報削除サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - ( リクエストデータ )<BR>
     * <BR>
     * メール情報削除完了リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDeleteCompleteResponse
     * @@roseuid 413C3DB302BF
     */
    public WEB3AdminMailInfoDeleteCompleteResponse completeMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeMailInfoDelete(WEB3AdminMailInfoDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMailInfoDeleteCompleteResponse l_response = null;
        WEB3AdminMailInfoDeleteService l_service = null;
        try
        {
            l_service = (WEB3AdminMailInfoDeleteService)Services.getService(WEB3AdminMailInfoDeleteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報削除ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch  (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(l_request, "メール情報削除に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }     
        log.exiting(STR_METHOD_NAME);
        return l_response;               
    }
}
@
