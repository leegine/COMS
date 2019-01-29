head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.11.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDetailsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報詳細ハンドラ(WEB3AdminMailInfoDetailsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
Revesion History : 2004/10/19  奚靖(中訊) 作成
*/
package webbroker3.mailinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsRequest;
import webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse;
import webbroker3.mailinfo.service.delegate.WEB3AdminMailInfoDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (メール情報詳細ハンドラ)<BR>
 * メール情報詳細ハンドラクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDetailsHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMailInfoDetailsHandler.class);

    /**
     * @@roseuid 416F1DD201B5
     */
    public WEB3AdminMailInfoDetailsHandler()
    {

    }

    /**
     * (メール情報詳細リクエスト)<BR>
     * メール情報詳細照会処理を行う。<BR>
     * <BR>
     * メール情報詳細サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param  l_request - (リクエストデータ)<BR>
     * メール情報詳細リクエストオブジェクト<BR>
     * @@return webbroker3.mailinfo.message.WEB3AdminMailInfoDetailsResponse
     * @@roseuid 413C4EB803BE
     */
    public WEB3AdminMailInfoDetailsResponse mailInfoDetailsRequest(WEB3AdminMailInfoDetailsRequest l_request)
    {
        final String STR_METHOD_NAME = " mailInfoDetailsRequest(WEB3AdminMailInfoDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMailInfoDetailsResponse l_response = null;
        WEB3AdminMailInfoDetailsService l_service = null;
        
        try
        {
            l_service = (WEB3AdminMailInfoDetailsService) Services.getService(WEB3AdminMailInfoDetailsService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "メール情報詳細ハンドラサービスに失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminMailInfoDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "メール情報詳細に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
