head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正新規建入力ハンドラ（WEB3ToSuccFuturesChangeOpenContractInputHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 トウ鋒鋼 (中訊) 新規作成 モデルNo.261
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物訂正新規建入力ハンドラ)<BR>
 * （連続）先物訂正新規建入力ハンドラクラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeOpenContractInputHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractInputHandler.class);

    /**
     * @@roseuid 47D6593503A9
     */
    public WEB3ToSuccFuturesChangeOpenContractInputHandler()
    {

    }

    /**
     * (訂正新規建入力リクエスト)<BR>
     * （連続）株価指数先物訂正新規建入力表示処理を行う。 <BR>
     * <BR>
     * （連続）株価指数先物訂正新規建入力サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccFuturesOpenChangeInputResponse
     * @@roseuid 47A942CF0210
     */
    public WEB3SuccFuturesOpenChangeInputResponse changeOpenContractInputRequest(
        WEB3SuccFuturesOpenChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeOpenContractInputRequest(WEB3SuccFuturesOpenChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeInputResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractInputService l_service = null;
        try
        {
            // （連続）株価指数先物訂正新規建入力サービスを取得
            l_service = (WEB3ToSuccFuturesChangeOpenContractInputService)Services.getService(
                    WEB3ToSuccFuturesChangeOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数先物訂正新規建入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物訂正新規建入力表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物訂正新規建入力表示処理が失敗しました。",
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
