head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益計算ハンドラ(WEB3TrialCalcProfitLossCalServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcProfitLossCalService;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * （計算サービス損益計算ハンドラ）<BR>
 * <BR>
 * 損益計算ハンドラクラス。<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalHandler<BR>
 * @@author shruti
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalHandler implements MessageHandler
{
    /**
 	 * @@log WEB3LogUtility
	 */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcProfitLossCalHandler.class);
    /**
     * @@roseuid 41C817800223
     */
    public WEB3TrialCalcProfitLossCalHandler()
    {
    }
    /**
     * （get入力画面）<BR>
     * <BR>
     * 損益計算入力画面表示を行う。<BR>
     * <BR>
     * 計算サービス損益計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Display the profit and loss calculation input screen.<BR>
     * Acquire the calculation service profit and loss calculation service, and call
     * the "execute( ) method".<BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 損益計算（入力画面表示）のリクエストデータ。<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcInputResponse
     * @@roseuid 4192C53902A9
     */
    public WEB3TrialCalcProfitLossCalcInputResponse getInputDisplay(
        WEB3TrialCalcProfitLossCalcInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputDisplay(WEB3TrialCalcProfitLossCalcInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcProfitLossCalcInputResponse l_response = null;
        WEB3TrialCalcProfitLossCalService l_service = null;
        try
        {
            l_service =
                (WEB3TrialCalcProfitLossCalService) Services.getService(
                    WEB3TrialCalcProfitLossCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcProfitLossCalcInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the profit and loss",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcProfitLossCalcInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of profit and loss failed", l_exp);
            l_response = (WEB3TrialCalcProfitLossCalcInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * （calc損益）<BR>
     * <BR>
     * 損益計算を行う。<BR>
     * <BR>
     * 計算サービス損益計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Calculate profit and loss. <BR>
     * Acquire the calculation service, profit and loss calculation service, and call
     * the "execute( )" method. <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 損益計算のリクエストデータ。<BR>
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcProfitLossCalcResponse
     * @@roseuid 41904F3401C9
     */
    public WEB3TrialCalcProfitLossCalcResponse
                            calcProfitLoss(WEB3TrialCalcProfitLossCalcRequest l_request)
    {
        final String STR_METHOD_NAME = "calcProfitLoss(WEB3TrialCalcProfitLossCalcRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcProfitLossCalcResponse l_response = null;
        WEB3TrialCalcProfitLossCalService l_service = null;
        try
        {
            l_service =
                (WEB3TrialCalcProfitLossCalService) Services.getService(
                    WEB3TrialCalcProfitLossCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcProfitLossCalcResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while calculating the profit and loss",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcProfitLossCalcResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Calculation of profit and loss failed", l_exp);
            l_response = (WEB3TrialCalcProfitLossCalcResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
