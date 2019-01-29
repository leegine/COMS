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
filename	WEB3FuturesSettleContractInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済入力ハンドラ(WEB3FuturesSettleContractInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/23 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse;

/**
 * (株価指数先物返済入力ハンドラ)<BR>
 * 株価指数先物返済入力ハンドラクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesSettleContractInputHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractInputHandler.class);

    /**
     * @@roseuid 40F7B07302CE
     */
    public WEB3FuturesSettleContractInputHandler()
    {

    }

    /**
     * (get返済一覧)<BR>
     * 株価指数先物返済一覧表示処理を行う。<BR>
     * <BR>
     * 先物返済入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginListResponse
     * @@roseuid 40A967C602C0
     */
    public WEB3FuturesCloseMarginListResponse getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)
    {
        final String STR_METHOD_NAME = "getCloseMarginList(WEB3FuturesCloseMarginListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginListResponse l_response = null;
        WEB3FuturesSettleContractInputService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractInputService) Services.getService(WEB3FuturesSettleContractInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物返済入力サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物返済一覧表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (get返済入力画面)<BR>
     * 株価指数先物返済注文入力表示処理を行う。<BR>
     * <BR>
     * 先物返済入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse
     * @@roseuid 40A967C602DF
     */
    public WEB3FuturesCloseMarginInputResponse getCloseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getCloseMarginInput(WEB3FuturesCloseMarginInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginInputResponse l_response = null;
        WEB3FuturesSettleContractInputService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractInputService) Services.getService(WEB3FuturesSettleContractInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物返済入力サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物返済注文入力表示処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
