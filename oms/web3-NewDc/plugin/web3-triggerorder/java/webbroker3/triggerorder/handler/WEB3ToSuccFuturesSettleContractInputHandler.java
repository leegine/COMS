head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）先物返済入力ハンドラ（WEB3ToSuccFuturesSettleContractInputHandler.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/17 孟亞南(中訊) 新規作成モデルNo.255 No.298
 */
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物返済入力ハンドラ)<BR>
 * （連続）先物返済入力ハンドラクラス<BR>
 *
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractInputHandler.class);

    /**
     * @@roseuid 47D659360158
     */
    public WEB3ToSuccFuturesSettleContractInputHandler()
    {

    }

    /**
     * (get返済入力画面)<BR>
     * （連続）株価指数先物返済入力画面表示処理を行う。<BR>
     * <BR>
     * （連続）先物返済入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCloseInputResponse
     * @@roseuid 47A94B3001E8
     */
    public WEB3SuccFuturesCloseInputResponse getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccFuturesCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseInputResponse l_response = null;
        WEB3ToSuccFuturesSettleContractInputService l_service = null;

        //（連続）先物返済注文サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractInputService)Services.getService(
                    WEB3ToSuccFuturesSettleContractInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物返済入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）先物返済入力サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccFuturesCloseInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物返済入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数先物返済入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
