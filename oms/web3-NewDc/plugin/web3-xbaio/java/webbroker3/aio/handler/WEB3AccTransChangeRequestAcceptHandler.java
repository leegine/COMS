head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付ハンドラ(WEB3AccTransChangeRequestAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/25 王蘭芬(中訊) レビュー                                       
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeRequestAcceptRequest;
import webbroker3.aio.message.WEB3AccTransChangeRequestAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替請求受付ハンドラ)<BR>
 * 振替請求受付ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestAcceptHandler.class);

    /**
     * (振替請求受付リクエスト)<BR>
     * 振替請求受付サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AccTransChangeRequestAcceptResponse
     * @@roseuid 413C1C3C0187
     */
    public WEB3AccTransChangeRequestAcceptResponse handleAccTransChangeRequestAcceptRequest(
        WEB3AccTransChangeRequestAcceptRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleAccTransChangeRequestAcceptRequest("
                + "WEB3AccTransChangeRequestAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeRequestAcceptResponse l_response = null;
        WEB3AccTransChangeRequestAcceptService l_mutualCancelAcceptService =
            null;

        try
        {
            l_mutualCancelAcceptService =
                (WEB3AccTransChangeRequestAcceptService) Services.getService(
                    WEB3AccTransChangeRequestAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "振替請求受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //振替請求受付サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3AccTransChangeRequestAcceptResponse) l_mutualCancelAcceptService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeRequestAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "振替請求受付リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
