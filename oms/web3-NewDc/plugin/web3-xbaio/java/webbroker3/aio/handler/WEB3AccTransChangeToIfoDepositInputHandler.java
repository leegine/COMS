head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替入力ハンドラ(WEB3AccTransChangeToIfoDepositInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositInputRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositInputResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金への振替入力ハンドラ)<BR>
 * 証拠金への振替入力ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositInputHandler
    implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeToIfoDepositInputHandler.class);

    /**
     * (証拠金への振替入力リクエスト)<BR>
     * 証拠金への振替入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AccTransChangeToIfoDepositInputResponse
     * @@roseuid 4135A8440110
     */
    public WEB3AccTransChangeToIfoDepositInputResponse handleAccTransChangeToIfoDepositInputRequest(
        WEB3AccTransChangeToIfoDepositInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleAccTransChangeToIfoDepositInputRequest(" +
                "WEB3AccTransChangeToIfoDepositInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositInputResponse l_response = null;
        WEB3AccTransChangeToIfoDepositInputService l_mutualCancelAcceptService =
            null;

        try
        {
            l_mutualCancelAcceptService =
                (WEB3AccTransChangeToIfoDepositInputService) Services.getService(
            WEB3AccTransChangeToIfoDepositInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金への振替入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //証拠金への振替入力サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3AccTransChangeToIfoDepositInputResponse) l_mutualCancelAcceptService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "証拠金への振替入力リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
