head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 株価指数先物新規建入力ハンドラクラス(WEB3FuturesOpenMarginInputHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/21 呉艶飛 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesProductSelectRequest;
import webbroker3.ifo.message.WEB3FuturesProductSelectResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物新規建入力ハンドラ)<BR>
 * 株価指数先物新規建入力ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3FuturesOpenContractInputHandler implements MessageHandler
{
    /**
      * Logger
      */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractInputHandler.class);

    /**
     * @@roseuid 40F7B07101E4
     */
    public WEB3FuturesOpenContractInputHandler()
    {

    }

    /**
     * (新規建入力リクエスト)<BR>
     * 株価指数先物新規建入力表示処理を行う。<BR>
     * <BR>
     * 株価指数先物新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FuturesOpenMarginInputResponse
     * @@roseuid 40A8520B015A
     */
    public WEB3FuturesOpenMarginInputResponse openMarginInputRequest(WEB3FuturesOpenMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "openMarginInputRequest(WEB3FuturesOpenMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOpenMarginInputResponse l_response = null;
        WEB3FuturesOpenContractInputService l_service = null;

        //株価指数先物新規建入力
        try
        {
            l_service = (WEB3FuturesOpenContractInputService) Services.getService(WEB3FuturesOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOpenMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物新規建入力サービス取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }
        try
        {
            //株価指数先物新規建入力サービスを取得し、execute()メソッドをコールする。
            l_response = (WEB3FuturesOpenMarginInputResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOpenMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物新規建入力の表示処理に失敗しました。", l_ex);

            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (新規建銘柄選択リクエスト)<BR>
     * 株価指数先物新規建銘柄選択表示処理を行う。<BR>
     * <BR>
     * 株価指数先物新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FuturesProductSelectResponse
     * @@roseuid 40A853560244
     */
    public WEB3FuturesProductSelectResponse openMarginProductSelectRequest(WEB3FuturesProductSelectRequest l_request)
    {
        final String STR_METHOD_NAME = ".openMarginProductSelectRequest(WEB3FuturesProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesProductSelectResponse l_response = null;
        WEB3FuturesOpenContractInputService l_service = null;

        //株価指数先物新規建銘柄選択表示処理を行う
        try
        {
            l_service = (WEB3FuturesOpenContractInputService) Services.getService(WEB3FuturesOpenContractInputService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物新規建入力サービス取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }
        //株価指数先物新規建入力サービスを取得し、 execute()メソッドをコールする。
        try
        {
            l_response = (WEB3FuturesProductSelectResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物新規建入力の銘柄選択表示処理に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
