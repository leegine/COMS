head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡入力ハンドラクラス(WEB3AioCashinNoticeInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 周勇 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinNoticeInputRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡入力ハンドラ)<BR>
 * 入金連絡入力ハンドラクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeInputHandler implements MessageHandler 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeInputHandler.class);
    
    /**
     * (入金連絡入力リクエスト)<BR>
     * 入金連絡入力表示処理を行う。 <BR>
     * <BR>
     * 入金連絡入力サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashinNoticeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBFC7E032D
     */
    public WEB3AioCashinNoticeInputResponse handleCashinNoticeInputRequest(
            WEB3AioCashinNoticeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleCashinNoticeInputRequest(WEB3AioCashinNoticeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AioCashinNoticeInputResponse l_response = null;
        WEB3AioCashinNoticeInputService l_service = null;
        try
        {
            l_service =
                (WEB3AioCashinNoticeInputService) Services.getService(
                    WEB3AioCashinNoticeInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinNoticeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "入金連絡入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }        
        
        try
        {
            l_response =
                (WEB3AioCashinNoticeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashinNoticeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金連絡入力レスポンスの取得に失敗しました", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
