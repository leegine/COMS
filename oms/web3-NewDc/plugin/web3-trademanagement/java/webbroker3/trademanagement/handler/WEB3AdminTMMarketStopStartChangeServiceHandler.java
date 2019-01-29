head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMMarketStopStartChangeServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者市場別取引停止再開変更ハンドラ(WEB3AdminTMMarketStopStartChangeServiceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMMarketStopStartChangeService;

/**
 * (管理者市場別取引停止再開変更ハンドラ)<BR>
 * <BR>
 * 管理者市場別取引停止再開変更ハンドラクラス<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeServiceHandler<BR>
 * <BR>
 * WEB3AdminTMMarketStopStartChangeServiceHandler class<BR>
 * <BR>
 * @@author Saravanan
 * @@version 1.0
 */
public class WEB3AdminTMMarketStopStartChangeServiceHandler implements MessageHandler
{

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMMarketStopStartChangeServiceHandler.class);

    /**
     * @@roseuid 41DD3E320214
     */
    public WEB3AdminTMMarketStopStartChangeServiceHandler()
    {

    }

    /**
     * (get変更入力画面)<BR>
     * <BR>
     * 市場別取引停止再開変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者市場別取引停止再開変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * getChangeInputScreen<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChange input screen process<BR>
     * <BR>
     * Acquire WEB3AdminTMMarketStopStartChangeServiceImpl, and call execute() method.
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・市場別取引停止再開変更入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgInputRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgInputResponse
     * @@roseuid 41770B4501AA
     */
    public WEB3AdminTMMStopStartChgInputResponse getChangeInputScreen(
        WEB3AdminTMMStopStartChgInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getChangeInputScreen(WEB3AdminTMMStopStartChgInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgInputResponse l_response = null;
        WEB3AdminTMMarketStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMMarketStopStartChangeService) Services.getService(
                    WEB3AdminTMMarketStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMMStopStartChgInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMMarketStopStartChangeServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminTMMStopStartChgInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response = (WEB3AdminTMMStopStartChgInputResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling getChangeInputScreen()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm変更)<BR>
     * <BR>
     * 市場別取引停止再開変更確認処理を行う。<BR>
     * <BR>
     * 管理者市場別取引停止再開変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------<BR>
     * <BR>
     * confirmChange<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService confirm process.<BR>
     * <BR>
     * Acquire WEB3AdminTMMarketStopStartChangeServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・市場別取引停止再開変更確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgConfirmRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgConfirmResponse
     * @@roseuid 41770B4501CA
     */
    public WEB3AdminTMMStopStartChgConfirmResponse confirmChange(
        WEB3AdminTMMStopStartChgConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChange(WEB3AdminTMMStopStartChgConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgConfirmResponse l_response = null;
        WEB3AdminTMMarketStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMMarketStopStartChangeService) Services.getService(
                    WEB3AdminTMMarketStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMMStopStartChgConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMMarketStopStartChangeServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMMStopStartChgConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response = (WEB3AdminTMMStopStartChgConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling confirmChange()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete変更)<BR>
     * <BR>
     * 市場別取引停止再開変更完了処理を行う。<BR>
     * <BR>
     * 管理者市場別取引停止再開変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * completeChange<BR>
     * <BR>
     * Execute WEB3AdminTMMarketStopStartChangeService complete process. <BR>
     * <BR>
     * Acquire WEB3AdminTMMarketStopStartChangeServiceImpl, and call execute()
     * method.<BR>
     *  <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・市場別取引停止再開変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMMStopStartChgCompleteRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMMStopStartChgCompleteResponse
     * @@roseuid 41770B4501E9
     */
    public WEB3AdminTMMStopStartChgCompleteResponse completeChange(
        WEB3AdminTMMStopStartChgCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChange(WEB3AdminTMMStopStartChgCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMMStopStartChgCompleteResponse l_response = null;
        WEB3AdminTMMarketStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMMarketStopStartChangeService) Services.getService(
                    WEB3AdminTMMarketStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMMStopStartChgCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMMarketStopStartChangeServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMMStopStartChgCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response = (WEB3AdminTMMStopStartChgCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling completeChange()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
