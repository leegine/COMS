head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携ハンドラ(WEB3AioBondOnPaymentCooperationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationRequest;
import webbroker3.aio.message.WEB3AioBondOnPaymentCooperationResponse;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券出金連携ハンドラ)<BR>
 * 債券出金連携ハンドラクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioBondOnPaymentCooperationHandler.class);

    /**
     * (債券出金連携リクエスト)<BR>
     * 債券出金連携処理を行う。<BR>
     * <BR>
     * 債券出金連携サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AioBondOnPaymentCooperationResponse
     */
    public WEB3AioBondOnPaymentCooperationResponse bondOnPaymentCooperationRequest(
            WEB3AioBondOnPaymentCooperationRequest l_request)
    {
        final String STR_METHOD_NAME =
            "bondOnPaymentCooperationRequest(WEB3AioBondOnPaymentCooperationRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //債券出金連携サービスインターフェイス
        WEB3AioBondOnPaymentCooperationService l_service = null;

        //債券出金連携
        WEB3AioBondOnPaymentCooperationResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioBondOnPaymentCooperationService)Services.getService(
                    WEB3AioBondOnPaymentCooperationService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioBondOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo =
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券出金連携サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioBondOnPaymentCooperationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioBondOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "債券出金連携処理に失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
