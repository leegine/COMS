head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求通知ハンドラ(WEB3AccTransChangeRequestNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyRequest;
import webbroker3.aio.message.WEB3AccTransChangeRequestNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替請求通知ハンドラ)<BR>
 * 振替請求通知ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestNotifyHandler.class);

    /**
     * (振替請求通知リクエスト)<BR>
     * 振替請求通知サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AccTransChangeRequestNotifyResponse
     * @@roseuid 413C2DF301A7
     */
    public WEB3AccTransChangeRequestNotifyResponse handleAccTransChangeRequestNotifyRequest(
        WEB3AccTransChangeRequestNotifyRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleAccTransChangeRequestNotifyRequest(" +
                "WEB3AccTransChangeRequestNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeRequestNotifyResponse l_response = null;
        WEB3AccTransChangeRequestNotifyService l_accTransChangeRequestNotifyService =
            null;

        try
        {
            l_accTransChangeRequestNotifyService =
                (WEB3AccTransChangeRequestNotifyService) Services.getService(
                    WEB3AccTransChangeRequestNotifyService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "振替請求通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //振替請求通知サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_accTransChangeRequestNotifyService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "振替請求通知リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
