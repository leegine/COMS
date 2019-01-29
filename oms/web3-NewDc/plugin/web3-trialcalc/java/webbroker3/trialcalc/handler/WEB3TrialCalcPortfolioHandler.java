head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービスポートフォリオハンドラ(WEB3TrialCalcPortfolioHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditRequest;
import webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse;
import webbroker3.trialcalc.service.delegate.WEB3TrialCalcPortfolioService;

/**
 * （計算サービスポートフォリオハンドラ）<BR>
 * <BR>
 * ポートフォリオハンドラクラス。<BR>
 * <BR>
 * WEB3TrialCalcPortfolioHandler<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioHandler implements MessageHandler
{
    /**
      * ログ出力ユーティリティ。
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TrialCalcPortfolioHandler.class);

    /**
     * @@roseuid 41C7FFFD0229
     */
    public WEB3TrialCalcPortfolioHandler()
    {

    }

    /**
     * （getポートフォリオ表示）<BR>
     * <BR>
     * ポートフォリオ表示サービスを実施する。<BR>
     * <BR>
     * 計算サービスポートフォリオサービスを取得し、execute( )メソッドをコールする。<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getPortfolioDisplay<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request リクエストデータ - ポートフォリオ（一覧画面表示）のリクエストデータ。
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioDisplayResponse
     * @@roseuid 4193391D011B
     */
    public WEB3TrialCalcPortfolioDisplayResponse
        getPortfolioDisplay(WEB3TrialCalcPortfolioDisplayRequest l_request)
    {
        final String STR_METHOD_NAME = "getPortfolioDisplay(WEB3TrialCalcPortfolioDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioDisplayResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the Portfolio",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Display of portfolio failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioDisplayResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （updateポートフォリオ編集）<BR>
     * <BR>
     * ポートフォリオ編集サービスを実施する。<BR>
     * <BR>
     * ポートフォリオサービスを取得し、execute( )メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * updatePortfolioEdit<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request リクエストデータ - ポートフォリオ編集結果更新のリクエストデータ。
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditResponse
     * @@roseuid 4193391D011D
     */
    public WEB3TrialCalcPortfolioEditResponse
        updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest l_request)
    {
        final String STR_METHOD_NAME = "updatePortfolioEdit(WEB3TrialCalcPortfolioEditRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioEditResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while updateing the Portfolio",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Updateing the portfolio failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioEditResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （getポートフォリオ編集入力）<BR>
     * <BR>
     * ポートフォリオ編集入力表示サービスを実施する。<BR>
     * <BR>
     * 計算サービスポートフォリオサービスを取得し、execute( )メソッドをコールする。<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getPortfolioEditInput<BR>
     * Aquire WEB3TrialCalcPortfolioService and call method 'execute()'<BR>
     * @@param l_request
     * ポートフォリオ（ポートフォリオ編集入力画面表示）のリクエストデータ。
     * @@return webbroker3.trialcalc.message.WEB3TrialCalcPortfolioEditInputResponse
     * @@roseuid 4198ABE60259
     */
    public WEB3TrialCalcPortfolioEditInputResponse
        getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPortfolioEditInput(WEB3TrialCalcPortfolioEditInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3TrialCalcPortfolioEditInputResponse l_response = null;
        WEB3TrialCalcPortfolioService l_service = null;

        try
        {
            l_service =
                (WEB3TrialCalcPortfolioService) Services.getService(
                    WEB3TrialCalcPortfolioService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while displaying the PortfolioEditInput",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "PortfolioEditInput failed", l_exp);
            l_response = (WEB3TrialCalcPortfolioEditInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
