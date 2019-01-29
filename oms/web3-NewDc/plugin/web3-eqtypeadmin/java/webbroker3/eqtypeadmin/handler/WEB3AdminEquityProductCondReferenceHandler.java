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
filename	WEB3AdminEquityProductCondReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者株式銘柄条件照会ハンドラクラス(WEB3AdminPMProductCondRefReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondReferenceService;

/**
 * （管理者株式銘柄条件照会ハンドラクラス）<BR>
 * <BR>
 * 管理者株式銘柄条件照会ハンドラクラス
 * <BR>
 * WEB3AdminPMProductCondRefReferenceHandler<BR>
 * <BR>
 * @@author Saravanan Krishnamoorthy
 * @@version 1.0
 */
public class WEB3AdminEquityProductCondReferenceHandler implements MessageHandler
{

    /**
    * @@log WEB3LogUtility
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityProductCondReferenceHandler.class);

    /**
     * @@roseuid 41FA28FA035C
     */
    public WEB3AdminEquityProductCondReferenceHandler()
    {

    }

    /**
     * （get銘柄入力画面）<BR>
     * 株式銘柄条件照会銘柄入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getProductInputScreen)<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondReferenceService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondReferenceServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）
     * <BR>
     * 管理者・株式銘柄条件照会銘柄入力リクエストオブジェクト<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondRefInputRequest object<BR>
     * <BR>
     *
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse
     * @@roseuid 4190C6CB00E3
     */
    public WEB3AdminPMProductCondRefInputResponse
     getProductInputScreen(WEB3AdminPMProductCondRefInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getProductInputScreen(WEB3AdminPMProductCondRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMProductCondRefInputResponse l_response = null;
        WEB3AdminEquityProductCondReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityProductCondReferenceService) Services.getService(
                    WEB3AdminEquityProductCondReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondRefInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityProductCondReferenceServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminPMProductCondRefInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response =
				(WEB3AdminPMProductCondRefInputResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling getProductInputScreen()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （get照会画面）<BR>
     * <BR>
     * 株式銘柄条件照会処理を行う。<BR>
     * <BR>
     * 管理者株式銘柄条件照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (getReferenceScreen)<BR>
     * <BR>
     * Execute WEB3AdminEquityProductCondReferenceService process<BR>
     * <BR>
     * Acquire WEB3AdminEquityProductCondReferenceServiceImpl, and call the execute()
     * method.<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・株式銘柄条件照会リクエストオブジェクト<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMProductCondRefReferenceRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse
     * @@roseuid 4190C91F0149
     */
    public WEB3AdminPMProductCondRefReferenceResponse
     getReferenceScreen(WEB3AdminPMProductCondRefReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminPMProductCondRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMProductCondRefReferenceResponse l_response = null;
        WEB3AdminEquityProductCondReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityProductCondReferenceService) Services.getService(
                    WEB3AdminEquityProductCondReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminPMProductCondRefReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityProductCondReferenceServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminPMProductCondRefReferenceResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response =
				(WEB3AdminPMProductCondRefReferenceResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling getReferenceScreen()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
