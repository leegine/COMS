head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金への振替ハンドラ(WEB3AccTransChangeToIfoDepositHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/26 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositCompleteResponse;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmRequest;
import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金への振替ハンドラ)<BR>
 * 証拠金への振替ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositHandler implements MessageHandler
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeToIfoDepositHandler.class);

    /**
     * (confirm注文)<BR>
     * 振替処理の発注審査を行う。 <BR>
     * <BR>
     * 証拠金への振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AccTransChangeToIfoDepositConfirmResponse
     * @@roseuid 4135AE7F02A6
     */
    public WEB3AccTransChangeToIfoDepositConfirmResponse confirmOrder(
        WEB3AccTransChangeToIfoDepositConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmOrder(" +
                "WEB3AccTransChangeToIfoDepositConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositService l_service = null;
        WEB3AccTransChangeToIfoDepositConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeToIfoDepositService) Services.getService(
                    WEB3AccTransChangeToIfoDepositService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金への振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "振替処理の発注審査が失敗しました。",
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
     * 証拠金への振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AccTransChangeToIfoDepositCompleteResponse
     * @@roseuid 4135AE7F02A8
     */
    public WEB3AccTransChangeToIfoDepositCompleteResponse completeOrder(
        WEB3AccTransChangeToIfoDepositCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeOrder(" +
                "WEB3AccTransChangeToIfoDepositCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccTransChangeToIfoDepositService l_service = null;
        WEB3AccTransChangeToIfoDepositCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AccTransChangeToIfoDepositService) Services.getService(
                    WEB3AccTransChangeToIfoDepositService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証拠金への振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AccTransChangeToIfoDepositCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "振替処理の登録が失敗しました。", l_ex.getErrorInfo(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
