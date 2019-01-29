head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityAccProductTradeStopChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : EquityAcc ProductTradeStopChange Handler
                        (WEB3AdminEquityAccProductTradeStopChangeHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopChangeService;

/**
 * （管理者顧客銘柄別取引停止変更ハンドラクラスHandler）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止変更ハンドラクラス<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopChangeHandler class<BR>
 * <BR>
 * @@author Anil
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopChangeHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopChangeHandler.class);

    /**
     * @@roseuid 41FD965303D8
     */
    public WEB3AdminEquityAccProductTradeStopChangeHandler()
    {

    }

    /**
     * （get入力画面）<BR>
     * 顧客銘柄別取引停止変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者顧客銘柄別取引停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getInputScreen)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopChangeServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse
     * @@roseuid 419968CF00F9
     */
    public WEB3AdminPMAccProductTradeStopModifyInputResponse getInputScreen(
        WEB3AdminPMAccProductTradeStopModifyInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminPMAccProductTradeStopModifyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyInputResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopChangeService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopChangeService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （confirm変更）<BR>
     * <BR>
     * 顧客銘柄別取引停止変更確認処理を行う。<BR>
     * <BR>
     * 管理者顧客銘柄別取引停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (confirmChange)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService confirm process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopChangeServiceImpl, and call execute()
     * method. <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse
     * @@roseuid 4199693D0195
     */
    public WEB3AdminPMAccProductTradeStopModifyConfirmResponse confirmChange(
        WEB3AdminPMAccProductTradeStopModifyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmChange(WEB3AdminPMAccProductTradeStopModifyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyConfirmResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopChangeService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopChangeService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring " + "WEB3AdminEquityAccProductTradeStopChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateChange()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （complete変更）<BR>
     * <BR>
     * 顧客銘柄別取引停止変更完了処理を行う。<BR>
     * <BR>
     * 管理者顧客銘柄別取引停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (completeChange)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopChangeService complete process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopChangeServiceImpl, and call execute()
     * method. <BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・顧客銘柄別取引停止変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopModifyCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse
     * @@roseuid 419969600109
     */
    public WEB3AdminPMAccProductTradeStopModifyCompleteResponse completeChange(
        WEB3AdminPMAccProductTradeStopModifyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeChange(WEB3AdminPMAccProductTradeStopModifyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopModifyCompleteResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopChangeService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopChangeService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitChange()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopModifyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
