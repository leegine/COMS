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
filename	WEB3FuturesSettleContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済注文ハンドラ(WEB3FuturesSettleContractOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;

/**
 * (株価指数先物返済注文ハンドラ)<BR>
 * 株価指数先物返済注文ハンドラクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderHandler implements MessageHandler
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderHandler.class);

    /**
     * @@roseuid 40F7B0720196
     */
    public WEB3FuturesSettleContractOrderHandler()
    {

    }

    /**
     * (confirm返済)<BR>
     * 株価指数先物の返済発注審査を行う。<BR>
     * <BR>
     * 先物返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse
     * @@roseuid 40A8C17C03D5
     */
    public WEB3FuturesCloseMarginConfirmResponse confirmCloseMargin(WEB3FuturesCloseMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmCloseMargin(WEB3FuturesCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginConfirmResponse l_response = null;
        WEB3FuturesSettleContractOrderService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractOrderService) Services.getService(WEB3FuturesSettleContractOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "先物返済注文サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().error_debug_info;
            log.error(l_request, "株価指数先物の返済発注審査に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().error_debug_info;
            log.error(l_request, "株価指数先物の返済発注審査に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete返済)<BR>
     * 株価指数先物の返済注文を登録する。<BR>
     * <BR>
     * 先物返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse
     * @@roseuid 40A8C17D000D
     */
    public WEB3FuturesCloseMarginCompleteResponse completeCloseMargin(WEB3FuturesCloseMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeCloseMargin(WEB3FuturesCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginCompleteResponse l_response = null;
        WEB3FuturesSettleContractOrderService l_service = null;

        try
        {
            l_service = (WEB3FuturesSettleContractOrderService) Services.getService(WEB3FuturesSettleContractOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "先物返済注文サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().error_debug_info;
            log.error(l_request, "株価指数先物の返済注文に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().error_debug_info;
            log.error(l_request, "株価指数先物の返済注文に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
}
@
