head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込入力ハンドラ(WEB3AioSLRepayApplyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 柴双紅 (中訊) 新規作成 仕様変更 モデルNo.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済申込入力ハンドラ)<BR>
 * 証券担保ローン返済申込入力ハンドラクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputHandler.class);

    /**
     * @@roseuid 46E89086025E
     */
    public WEB3AioSLRepayApplyInputHandler()
    {

    }

    /**
     * (証券担保ローン返済申込入力リクエスト)<BR>
     * 証券担保ローン返済申込入力表示処理を行う。<BR>
     * <BR>
     * 証券担保ローン返済申込入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3SLRepayApplyInputResponse
     * @@roseuid 46CBF7E103B9
     */
    public WEB3SLRepayApplyInputResponse slRepayInputRequest(WEB3SLRepayApplyInputRequest l_request)
    {
        final String STR_METHOD_NAME = " slRepayInputRequest(WEB3SLRepayApplyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyInputService l_service = null;
        WEB3SLRepayApplyInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyInputService)Services.getService(
                    WEB3AioSLRepayApplyInputService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証券担保ローン返済申込入力サービスの取得に失敗しました",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込入力サービス処理が失敗しました。",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_excRuntime)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_excRuntime.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済申込入力サービス処理が失敗しました。",
                l_excRuntime);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
