head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建入力ハンドラ(WEB3ToSuccFuturesOpenContractInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 于瀟(中訊) 新規作成モデル256
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物新規建入力ハンドラ)<BR>
 * （連続）株価指数先物新規建入力ハンドラクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ToSuccFuturesOpenContractInputHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractInputHandler.class);

    /**
     * @@roseuid 47D6593502CF
     */
    public WEB3ToSuccFuturesOpenContractInputHandler()
    {

    }

    /**
     * (新規建入力リクエスト)<BR>
     * （連続）株価指数先物新規建入力画面表示処理を行う。 <BR>
     * <BR>
     * （連続）株価指数先物新規建入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesOpenInputResponse
     * @@roseuid 47A939C401AB
     */
    public WEB3SuccFuturesOpenInputResponse openContractInputRequest(WEB3SuccFuturesOpenInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openContractInputRequest(WEB3SuccFuturesOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenInputResponse l_response = null;
        WEB3ToSuccFuturesOpenContractInputService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesOpenContractInputService)Services.getService(
                WEB3ToSuccFuturesOpenContractInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数先物新規建入力サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物新規建入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesOpenInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物新規建入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
