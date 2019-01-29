head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP訂正返済入力ハンドラ（WEB3ToSuccOptionChangeClosingContractInputHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 孟亞南(中訊) 新規作成 モデルNo.306
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP訂正返済入力ハンドラ)<BR>
 * （連続）株価指数オプション訂正返済入力ハンドラクラス<BR>
 *
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeClosingContractInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeClosingContractInputHandler.class);

    /**
     * @@roseuid 47FDBE3E005D
     */
    public WEB3ToSuccOptionChangeClosingContractInputHandler()
    {

    }

    /**
     * (訂正返済入力リクエスト)<BR>
     * （連続）株価指数オプション訂正返済入力表示処理を行う。 <BR>
     * <BR>
     * （連続）株価指数オプション訂正返済入力サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccOptionsCloseChangeInputResponse
     * @@roseuid 47A92BCD017C
     */
    public WEB3SuccOptionsCloseChangeInputResponse changeCloseInputRequest(
        WEB3SuccOptionsCloseChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "changeCloseInputRequest(WEB3SuccOptionsCloseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeInputResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractInputService l_service = null;

        //（連続）株価指数オプション訂正返済入力サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccOptionChangeClosingContractInputService)Services.getService(
                        WEB3ToSuccOptionChangeClosingContractInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）株価指数オプション訂正返済入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）株価指数オプション訂正返済入力サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccOptionsCloseChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数オプション訂正返済入力表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数オプション訂正返済入力表示処理が失敗しました。",
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
