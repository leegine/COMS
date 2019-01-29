head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知ハンドラクラス(WEB3AioSecurityTransferNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSecurityTransferNotifyRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替通知ハンドラ)<BR>
 * 証券振替通知ハンドラクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferNotifyHandler implements MessageHandler 
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSecurityTransferNotifyHandler.class);    
    
    /**
     * (証券振替通知リクエスト)<BR>
     * 証券振替通知サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSecurityTransferNotifyResponse
     * @@roseuid 415782AE031C
     */
    public WEB3AioSecurityTransferNotifyResponse handleSecurityTransferNotifyRequest(
            WEB3AioSecurityTransferNotifyRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "handleSecurityTransferNotify(" +
                "WEB3AioSecurityTransferNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSecurityTransferNotifyResponse l_response = null;
        WEB3AioSecurityTransferNotifyService l_securityTransferNotifyService = null;

        try
        {
            l_securityTransferNotifyService =
                (WEB3AioSecurityTransferNotifyService) Services.getService(
                    WEB3AioSecurityTransferNotifyService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証券振替通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //証券振替通知サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3AioSecurityTransferNotifyResponse) l_securityTransferNotifyService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "証券振替通知リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
