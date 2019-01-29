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
filename	WEB3AdminEquityProductCondSettingHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者ログイン停止再開変更ハンドラクラス(WEB3AdminEquityProductCondSettingHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.util.WEB3LogUtility;

/**
 * （管理者株式銘柄条件設定ハンドラ）<BR>
 * <BR>
 * 管理者株式銘柄条件設定ハンドラクラス<BR>
 * <BR>
 *  WEB3AdminEquityProductCondSettingHandler class<BR>
 * <BR>
 * @@author Anupama
 * @@version 1.0
  */
public class WEB3AdminEquityProductCondSettingHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondSettingHandler.class);
    /**
       * @@roseuid 41FA29D40204
       */
    public WEB3AdminEquityProductCondSettingHandler()
    {

    }
    /**
        * （get条件設定入力画面）<BR>
        * <BR>
        * 株式銘柄条件設定入力画面表示処理を行う。<BR>
        * <BR>
        * 管理者株式銘柄条件設定サービスImplを<BR>
        * 取得し、execute()メソッドをコールする。<BR>
        * <BR>
        * ----<English>--------------------<BR>
        * <BR>
        * getConditionSettingInputScreen<BR>
        * <BR>
        * Execute WEB3AdminEquityProductCondSettingService input screen process<BR>
        * <BR>
        * Acquire WEB3AdminEquityProductCondSettingServiceImpl, and call execute()
        * method.<BR>
        * <BR>
        * @@param l_request - （リクエストデータ）<BR>
        * <BR>
        * 管理者・株式銘柄条件設定入力リクエストオブジェクト<BR>
        * <BR>
        * ----<English>--------------------<BR>
        * <BR>
        * l_request<BR>
        * <BR>
        * WEB3AdminPMProductCondConfInputRequest object<BR>
        * <BR>
        * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse
        * @@roseuid 417F8F80036D
        */
    public WEB3AdminPMProductCondConfInputResponse getConditionSettingInputScreen(
        WEB3AdminPMProductCondConfInputRequest l_request)
    {

        final String STR_METHOD_NAME =
            "getConditionSettingInputScreen(WEB3AdminPMProductCondConfInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMProductCondConfInputResponse l_response = null;
        WEB3AdminEquityProductCondSettingService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityProductCondSettingService) Services.getService(
                    WEB3AdminEquityProductCondSettingService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondConfInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getConditionSettingInputScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminPMProductCondConfInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response = (WEB3AdminPMProductCondConfInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getConditionSettingInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （confirm条件設定）<BR>
     * <BR>
     * 株式銘柄条件設定確認処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件設定サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * confirmConditionSetting<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService confirm process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondSettingServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件設定確認リクエストオブジェクト<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondConfConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse
     * @@roseuid 417F902C0290
     */
    public WEB3AdminPMProductCondConfConfirmResponse confirmConditionSetting(
        WEB3AdminPMProductCondConfConfirmRequest l_request)
    {

        final String STR_METHOD_NAME =
            "confirmConditionSetting(WEB3AdminPMProductCondConfConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMProductCondConfConfirmResponse l_response = null;
        WEB3AdminEquityProductCondSettingService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityProductCondSettingService) Services.getService(
                    WEB3AdminEquityProductCondSettingService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondConfConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while confirmConditionSetting request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminPMProductCondConfConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "confirmConditionSetting Request failed", l_exp);
            l_response = (WEB3AdminPMProductCondConfConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （complete条件設定）<BR>
     * <BR>
     * 株式銘柄条件設定完了処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件設定サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * completeConditionSetting<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondSettingService complete process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondSettingServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件設定完了リクエストオブジェクト<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondConfCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse
     * @@roseuid 417F907200AB
     */
    public WEB3AdminPMProductCondConfCompleteResponse completeConditionSetting(
        WEB3AdminPMProductCondConfCompleteRequest l_request)
    {

        final String STR_METHOD_NAME =
            "completeConditionSetting(WEB3AdminPMProductCondConfCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMProductCondConfCompleteResponse l_response = null;
        WEB3AdminEquityProductCondSettingService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityProductCondSettingService) Services.getService(
                    WEB3AdminEquityProductCondSettingService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondConfCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while completeConditionSetting Request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminPMProductCondConfCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, " completeConditionSetting  Request failed", l_exp);
            l_response = (WEB3AdminPMProductCondConfCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
