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
filename	WEB3AdminEquityProductCondScheduleHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者株式銘柄条件予定一覧ハンドラ(WEB3AdminEquityProductCondScheduleHandler.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondScheduleService;

/**
 * （管理者株式銘柄条件予定一覧ハンドラ）<BR>
 * <BR>
 * 管理者株式銘柄条件予定一覧ハンドラクラス<BR>
 * <BR>
 * WEB3AdminEquityProductCondScheduleHandler class<BR>
 * <BR>
 * @@author Manjula Honnappa
 * @@version 1.0
 */
public class WEB3AdminEquityProductCondScheduleHandler implements MessageHandler
{

    /**
    * ログ出力ユーティリティ。
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondScheduleHandler.class);

    /**
    * @@roseuid 41FA325E0262
    */
    public WEB3AdminEquityProductCondScheduleHandler()
    {

    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 株式銘柄条件予定一覧入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件予定一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondScheduleService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondScheduleServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件予定一覧入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondListInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondListInputResponse
     * @@roseuid 41944BFA01F5
     */
    public WEB3AdminPMProductCondListInputResponse getInputScreen(
        WEB3AdminPMProductCondListInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminPMProductCondListInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityProductCondScheduleService l_service = null;
        WEB3AdminPMProductCondListInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminEquityProductCondScheduleService) Services.getService(
                    WEB3AdminEquityProductCondScheduleService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondListInputResponse) l_request.createResponse();

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getInputScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        try
        {
            l_response = (WEB3AdminPMProductCondListInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response = (WEB3AdminPMProductCondListInputResponse) l_request.createResponse();

            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get予定一覧）<BR>
     * <BR>
     * 株式銘柄条件予定一覧処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件予定一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * getSchedule<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondScheduleService process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondScheduleServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件予定一覧入力リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     *  WEB3AdminPMProductCondListInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminPMProductCondListResponse
     * @@roseuid 4191BFED02DA
     */
    public WEB3AdminPMProductCondListResponse getSchedule(
        WEB3AdminPMProductCondListRequest l_request)
    {
        final String STR_METHOD_NAME = "getSchedule(WEB3AdminPMProductCondListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityProductCondScheduleService l_service = null;
        WEB3AdminPMProductCondListResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminEquityProductCondScheduleService) Services.getService(
                    WEB3AdminEquityProductCondScheduleService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondListResponse) l_request.createResponse();

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getSchedule request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        try
        {
            l_response = (WEB3AdminPMProductCondListResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response = (WEB3AdminPMProductCondListResponse) l_request.createResponse();

            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getSchedule Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
