head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株価指数先物新規建注文ハンドラクラス(WEB3FuturesOpenMarginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物新規建注文ハンドラ)<BR>
 * 株価指数先物新規建注文ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOpenContractHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractHandler.class);

    /**
     * @@roseuid 40F7B07100FA
     */
    public WEB3FuturesOpenContractHandler()
    {

    }

    /**
     * (confirm注文)<BR>
     * 株価指数先物の新規建発注審査を行う。<BR>
     * <BR>
     * 株価指数先物新規建注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物新規建注文確認リクエストデータオブジェクト
     * @@return WEB3FuturesOpenMarginConfirmResponse
     * @@roseuid 40A84A4701E6
     */
    public WEB3FuturesOpenMarginConfirmResponse confirmOrder(WEB3FuturesOpenMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3FuturesOpenMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginConfirmResponse l_response = null;
        WEB3FuturesOpenContractService l_service = null;

        //株価指数先物の新規建発注審査を行う
        try
        {
            l_service = (WEB3FuturesOpenContractService) Services.getService(WEB3FuturesOpenContractService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物新規建注文サービスに失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }
        //株価指数先物新規建注文サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数先物新規建注文の発注審査処理に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesOpenMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数先物新規建注文の発注審査処理に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (complete注文)<BR>
     * 株価指数先物の新規建注文を登録する。<BR>
     * <BR>
     * 株価指数先物新規建注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物新規建注文完了リクエストデータオブジェクト<BR>
     * @@return WEB3FuturesOpenMarginCompleteResponse
     * @@roseuid 40A84A470206
     */
    public WEB3FuturesOpenMarginCompleteResponse completeOrder(WEB3FuturesOpenMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3FuturesOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginCompleteResponse l_response = null;
        WEB3FuturesOpenContractService l_service = null;

        //株価指数先物の新規建注文を登録する
        try
        {
            l_service = (WEB3FuturesOpenContractService) Services.getService(WEB3FuturesOpenContractService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物新規建注文サービスに失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }
        //株価指数先物の新規建注文を登録するサービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数先物新規建注文の登録処理に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesOpenMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数先物新規建注文の登録処理に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
