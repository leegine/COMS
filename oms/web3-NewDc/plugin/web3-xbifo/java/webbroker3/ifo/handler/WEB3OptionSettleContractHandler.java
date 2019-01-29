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
filename	WEB3OptionSettleContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP返済注文ハンドラ(WEB3OptionSettleContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;


/**
 * (OP返済注文ハンドラ)<BR>
 * 株価指数オプション返済注文ハンドラクラス<BR>
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3OptionSettleContractHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractHandler.class);

    /**
     * (confirm返済)<BR>
     * <BR>
     * 株価指数オプションの返済発注審査を行う。<BR>
     * <BR>
     * オプション返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse
     * @@roseuid 405511FF0128
     */
    public WEB3OptionsCloseMarginConfirmResponse confirmSettleContract(WEB3OptionsCloseMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmSettleContract(WEB3OptionsCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginConfirmResponse l_response = null;
        WEB3OptionSettleContractOrderService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionSettleContractOrderService)Services.getService(
                    WEB3OptionSettleContractOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "オプション返済注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプションの返済発注審査に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプションの返済発注審査に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (complete返済)<BR>
     * <BR>
     * 株価指数オプションの返済注文を登録する。<BR>
     * <BR>
     * オプション返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse
     * @@roseuid 4055120C027F
     */
    public WEB3OptionsCloseMarginCompleteResponse completeSettleContract(WEB3OptionsCloseMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeSettleContract(WEB3OptionsCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3OptionsCloseMarginCompleteResponse l_response = null;
        WEB3OptionSettleContractOrderService l_service = null;

        try
        {
            l_service =
                 (WEB3OptionSettleContractOrderService)Services.getService(
                    WEB3OptionSettleContractOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "オプション返済注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプションの返済注文に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプションの返済注文に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;

    }
}
@
