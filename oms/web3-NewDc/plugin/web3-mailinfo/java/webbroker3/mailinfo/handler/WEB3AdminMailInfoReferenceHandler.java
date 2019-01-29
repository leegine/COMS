head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報一覧ハンドラ(WEB3AdminMailInfoReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (メール情報一覧ハンドラ)<BR>
 * メール情報一覧ハンドラクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoReferenceHandler.class);
    
    /**
     * @@roseuid 416F1DD10148
     */
    public WEB3AdminMailInfoReferenceHandler() 
    {
     
    }
    
    /**
     * (メール情報一覧リクエスト)<BR>
     * メール情報一覧照会処理を行う。<BR>
     * <BR>
     * メール情報一覧サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * メール情報一覧リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoReferenceResponse
     * @@roseuid 413C46410242
     */
    public WEB3AdminMailInfoReferenceResponse mailInfoReferenceRequest(WEB3AdminMailInfoReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " mailInfoReferenceRequest(WEB3AdminMailInfoReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMailInfoReferenceResponse l_response = null; 
        WEB3AdminMailInfoReferenceService l_service = null;
          
        try
        {
            l_service = (WEB3AdminMailInfoReferenceService)Services.getService(WEB3AdminMailInfoReferenceService.class); 
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request, "メール情報一覧ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "メール情報一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
