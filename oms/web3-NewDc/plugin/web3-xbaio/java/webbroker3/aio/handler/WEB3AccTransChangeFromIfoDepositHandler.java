head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金から振替ハンドラ(WEB3AccTransChangeFromIfoDepositHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeFromIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金から振替ハンドラ)<BR>
 * 証拠金から振替ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeFromIfoDepositHandler.class);

    /**
     * (confirm注文)<BR>
     * 振替処理の発注審査を行う。 <BR>
     * <BR>
     * 証拠金から振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return WEB3AccTransChangeFromIfoDepositConfirmResponse
     * @@roseuid 4135317001B7
     */
    public WEB3AccTransChangeFromIfoDepositConfirmResponse handleConfirmOrder(
        WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleConfirmOrder(" 
            + "WEB3AccTransChangeFromIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeFromIfoDepositService l_service = null;
        WEB3AccTransChangeFromIfoDepositConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeFromIfoDepositService) Services.getService(
                    WEB3AccTransChangeFromIfoDepositService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金から振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "振替処理の発注審査処理が失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete注文)<BR>
     * 振替処理の登録を行う。 <BR>
     * <BR>
     * 証拠金から振替サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AccTransChangeFromIfoDepositCompleteResponse
     * @@roseuid 4135317001D6
     */
    public WEB3AccTransChangeFromIfoDepositCompleteResponse handleCompleteOrder(
        WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "handleCompleteOrder(" +
                "WEB3AccTransChangeFromIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeFromIfoDepositService l_service = null;
        WEB3AccTransChangeFromIfoDepositCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeFromIfoDepositService) Services.getService(
                    WEB3AccTransChangeFromIfoDepositService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金から振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeFromIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "振替処理の登録サービスが失敗しました。",
                l_ex.getErrorInfo(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
