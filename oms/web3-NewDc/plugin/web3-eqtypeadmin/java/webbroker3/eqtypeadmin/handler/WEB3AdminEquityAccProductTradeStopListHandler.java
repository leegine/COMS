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
filename	WEB3AdminEquityAccProductTradeStopListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者顧客銘柄別取引停止一覧ハンドラクラス)
                        (WEB3AdminEquityAccProductTradeStopListHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;
/**
 * （管理者顧客銘柄別取引停止一覧ハンドラクラス）<BR>
 * <BR>
 * 管理者顧客銘柄別取引停止一覧ハンドラクラス<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopListHandler class<BR>
 * <BR>
 * @@author Vijay Kumar
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopListHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopListHandler.class);

    /**
     * @@roseuid 41FD9445006D
     */
    public WEB3AdminEquityAccProductTradeStopListHandler()
    {

    }

   /**
    * （get一覧画面）<BR>
    * <BR>
    * 顧客銘柄別取引停止一覧表示処理を行う。<BR>
    * <BR>
    * 管理者顧客銘柄別取引停止一覧サービスImplを取得し、<BR>
    * execute()メソッドをコールする。<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (getListScreen)<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * Acquire WEB3AdminEquityAccProductTradeStopListServiceImpl, and call execute()
    * method.<BR>
    * <BR>
    * @@param l_request - （リクエストデータ）<BR>
    * <BR>
    * 管理者・顧客銘柄別取引停止一覧リクエストオブジェクト<BR>
    * <BR>
    * WEB3AdminPMAccProductTradeStopListRequest object<BR>
    * <BR>
    * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse
    * @@roseuid 41987E36014F
    */
    public WEB3AdminPMAccProductTradeStopListResponse
                 getListScreen(WEB3AdminPMAccProductTradeStopListRequest l_request)
    {
        final String STR_METHOD_NAME =
           "getListScreen(WEB3AdminPMAccProductTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopListResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopListService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopListService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopListServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
   }
}
@
