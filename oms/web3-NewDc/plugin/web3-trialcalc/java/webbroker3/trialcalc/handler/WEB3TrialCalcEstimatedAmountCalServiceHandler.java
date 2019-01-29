head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcEstimatedAmountCalServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス受渡代金計算ハンドラ(WEB3TrialCalcEstimatedAmountCalServiceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcEstimatedAmountCalService;

/**
 * （計算サービス受渡代金計算ハンドラ）<BR>
 * <BR>
 * 受渡代金計算ハンドラクラス。<BR>
 * <BR>
 * WEB3TrialCalcEstimatedAmountCalServiceHandler<BR>
 * @@author sudhindra kinnal
 * @@version 1.0
 */
public class WEB3TrialCalcEstimatedAmountCalServiceHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcEstimatedAmountCalServiceHandler.class);

    /**
     * @@roseuid 41C8139100CC
     */
    public WEB3TrialCalcEstimatedAmountCalServiceHandler()
    {
    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 受渡代金計算入力画面表示を行う。<BR>
     * <BR>
     * 計算サービス受渡代金計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getInputDisplay<BR>
     * Acquire the calculation service receiving contact price calculation service,
     * and call the "execute( )" method. <BR>
     * <BR>
     * @@param l_request リクエストデータ - 受渡代金計算（入力画面表示）のリクエストデータ。
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcInputResponse
     * @@roseuid 4192E74E0234
     */
    public WEB3TrialCalcDeliveryPriceCalcInputResponse getInputDisplay(
        WEB3TrialCalcDeliveryPriceCalcInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputDisplay(WEB3TrialCalcDeliveryPriceCalcInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcDeliveryPriceCalcInputResponse l_response = null;
        WEB3TrialCalcEstimatedAmountCalService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcEstimatedAmountCalService) Services.getService(
                    WEB3TrialCalcEstimatedAmountCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcDeliveryPriceCalcInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the Estimated Amount",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcDeliveryPriceCalcInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of Estimated calulated amount failed", l_exp);
            l_response = (WEB3TrialCalcDeliveryPriceCalcInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （calc受渡代金）<BR>
     * <BR>
     * 受渡代金計算を行う。<BR>
     * <BR>
     * 受渡代金計算サービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * calcEstimatedAmount<BR>
     * Acquire the receiving contact price calculation service, and call the "execute(
     * )" method. <BR>
     * @@param l_request リクエストデータ - 受渡代金計算のリクエストデータ。
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcDeliveryPriceCalcResponse
     * @@roseuid 4190A6A1001E
     */
    public WEB3TrialCalcDeliveryPriceCalcResponse calcEstimatedAmount(
        WEB3TrialCalcDeliveryPriceCalcRequest l_request)
    {
        final String STR_METHOD_NAME = "calcEstimatedAmount(WEB3TrialCalcDeliveryPriceCalcRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcDeliveryPriceCalcResponse l_response = null;
        WEB3TrialCalcEstimatedAmountCalService l_service = null;
        try
        {
            l_service =
                (WEB3TrialCalcEstimatedAmountCalService) Services.getService(
                    WEB3TrialCalcEstimatedAmountCalService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcDeliveryPriceCalcResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while calculating the Estimated amount",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcDeliveryPriceCalcResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Calculation of Estimated amount failed", l_exp);
            l_response = (WEB3TrialCalcDeliveryPriceCalcResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}@
