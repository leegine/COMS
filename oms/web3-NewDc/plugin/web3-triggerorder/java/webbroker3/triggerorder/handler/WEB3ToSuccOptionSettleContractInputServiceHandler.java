head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractInputServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :（連続）OP返済入力ハンドラ(WEB3ToSuccOptionSettleContractInputServiceHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 楊夫志(中訊) 新規作成 モデル297
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP返済入力ハンドラ)<BR>
 * （連続）OP返済入力ハンドラクラス<BR>
 * <BR>
 * @@author 楊夫志
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractInputServiceHandler implements MessageHandler
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractInputServiceHandler.class);

    /**
     * @@roseuid 47FDBE3E0119
     */
    public WEB3ToSuccOptionSettleContractInputServiceHandler()
    {

    }

    /**
     * (get返済入力画面)<BR>
     * （連続）株価指数オプション返済入力画面表示処理を行う。<BR>
     * <BR>
     * （連続）OP返済入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccOptionsCloseInputResponse
     * @@roseuid 47A9279C02C4
     */
    public WEB3SuccOptionsCloseInputResponse getSettleContractInputScreen(
        WEB3SuccOptionsCloseInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSettleContractInputScreen(WEB3SuccOptionsCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseInputResponse l_response = null;
        WEB3ToSuccOptionSettleContractInputService l_service = null;

        //（連続）OP返済入力サービスを取得する
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractInputService)Services.getService(
                    WEB3ToSuccOptionSettleContractInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP返済入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //（連続）OP返済入力サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SuccOptionsCloseInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OP返済入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）株価指数OP返済入力画面表示処理が失敗しました。",
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
