head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossBreakEvenPointCalHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益分岐点計算ハンドラ(WEB3TrialCalcProfitLossBreakEvenPointCalHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossBreakEvenPointCalService;

/**
 * WEB3TrialCalcProfitLossBreakEvenPointCalHandler implements MessageHandler
 * （計算サービス損益分岐点計算ハンドラ）<BR>
 * <BR>
 * 損益分岐点計算ハンドラクラス。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * Break-even point calculation handler class<BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossBreakEvenPointCalHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3TrialCalcProfitLossBreakEvenPointCalHandler.class);

    /**
     * @@roseuid 41C81551031D
     */
    public WEB3TrialCalcProfitLossBreakEvenPointCalHandler()
    {

    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 損益分岐点計算入力画面表示を行う。<BR>
     * <BR>
     * 計算サービス損益分岐点計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Display the break-even point calculation input screen.  Acquire the calculation
     * service break-even point calculation service, and call the "execute( )" method.
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 損益分岐点計算（入力画面表示）のリクエストデータ。<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointInputResponse
     * @@see getInputDisplay
     * @@roseuid 4192D2BA0021
     */
    public WEB3TrialCalcBreakEvenPointInputResponse
                    getInputDisplay(WEB3TrialCalcBreakEvenPointInputRequest l_request)
    {
        final String STR_METHOD_NAME =
                   "getInputDisplay(WEB3TrialCalcBreakEvenPointInputRequest)";
               log.entering(STR_METHOD_NAME);

        WEB3TrialCalcBreakEvenPointInputResponse l_response = null;
        WEB3TrialCalcProfitLossBreakEvenPointCalService l_service = null;
        try
        {
            l_service =
            (WEB3TrialCalcProfitLossBreakEvenPointCalService) Services.getService(
                WEB3TrialCalcProfitLossBreakEvenPointCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcBreakEvenPointInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
            l_request,
            "Failed while displaying the Break Even Point",
            l_response.errorInfo,
            l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
            (WEB3TrialCalcBreakEvenPointInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of Break Even Point failed", l_exp);
            l_response =
            (WEB3TrialCalcBreakEvenPointInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * （calc損益分岐点）<BR>
     * <BR>
     * 損益分岐点計算を行う。<BR>
     * <BR>
     * 損益分岐点計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Calculate the break-even point.  Acquire the break-even point calculation
     * service, and call the execute( ) method. <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 損益分岐点計算のリクエストデータ。<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcBreakEvenPointResponse
     * @@see calcProfitLossBreakPoint
     * @@roseuid 419085EC0081
     */
    public WEB3TrialCalcBreakEvenPointResponse
                calcProfitLossBreakPoint(WEB3TrialCalcBreakEvenPointRequest l_request)
    {
        final String STR_METHOD_NAME =
                   "calcProfitLossBreakPoint(WEB3TrialCalcBreakEvenPointInputRequest)";
               log.entering(STR_METHOD_NAME);

        WEB3TrialCalcBreakEvenPointResponse l_response = null;
        WEB3TrialCalcProfitLossBreakEvenPointCalService l_service = null;
        try
        {
            l_service =
            (WEB3TrialCalcProfitLossBreakEvenPointCalService) Services.getService(
            WEB3TrialCalcProfitLossBreakEvenPointCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcBreakEvenPointResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
            l_request,
            "Failed while displaying the Break Even Point",
            l_response.errorInfo,
            l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
            (WEB3TrialCalcBreakEvenPointResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of Break Even Point failed", l_exp);
            l_response =
            (WEB3TrialCalcBreakEvenPointResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;


    }
}
@
