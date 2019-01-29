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
filename	WEB3AdminOffFloorChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売銘柄更新ハンドラクラス(WEB3AdminOffFloorChangeHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorChangeService;

/**
 * (管理者立会外分売銘柄更新ハンドラ)<BR>
 * <BR>
 * 管理者立会外分売銘柄更新ハンドラクラス<BR>
 * <BR>
 * WEB3AdminOffFloorChangeHandler<BR>
 * @@author Wanishree
 * @@version 1.0
 */
public class WEB3AdminOffFloorChangeHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorChangeHandler.class);

    /**
     * @@roseuid 421AE4DF014B
     */
    public WEB3AdminOffFloorChangeHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新画面表示処理を行う。<BR>
     * <BR>
     * 管理者立会外分売銘柄更新サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>-----------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService screen process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorChangeService, and call execute() method.<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新入力リクエストオブジェクト<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorChangeInputRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse
     * @@roseuid 41BD89BC03BD
     */
    public WEB3AdminOffFloorChangeInputResponse getInputScreen(WEB3AdminOffFloorChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminOffFloorChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminOffFloorChangeInputResponse l_response = null;
        WEB3AdminOffFloorChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorChangeService) Services.getService(
                    WEB3AdminOffFloorChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorChangeInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response = (WEB3AdminOffFloorChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate更新)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新確認処理を行う。<BR>
     * <BR>
     * 管理者立会外分売銘柄更新サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * validateChange<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService validate process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorChangeService, and call execute() method.<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新確認リクエストオブジェクト<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorChangeConfirmRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse
     * @@roseuid 41BD89BC03BF
     */
    public WEB3AdminOffFloorChangeConfirmResponse validateChange(WEB3AdminOffFloorChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminOffFloorChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminOffFloorChangeConfirmResponse l_response = null;
        WEB3AdminOffFloorChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorChangeService) Services.getService(
                    WEB3AdminOffFloorChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring " + "WEB3AdminOffFloorChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorChangeConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateChange()", l_exp);
            l_response = (WEB3AdminOffFloorChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit更新)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新完了処理を行う。<BR>
     * <BR>
     * 管理者立会外分売銘柄更新サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>------------------------<BR>
     * <BR>
     * submitChange<BR>
     * <BR>
     * Execute WEB3AdminOffFloorChangeService submit process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorChangeService, and call execute() method.<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者立会外分売銘柄更新完了リクエストオブジェクト<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorChangeCompleteRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse
     * @@roseuid 41BD89BC03C1
     */
    public WEB3AdminOffFloorChangeCompleteResponse
        submitChange(WEB3AdminOffFloorChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminOffFloorChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminOffFloorChangeCompleteResponse l_response = null;
        WEB3AdminOffFloorChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorChangeService) Services.getService(
                    WEB3AdminOffFloorChangeService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorChangeCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitChange()", l_exp);
            l_response = (WEB3AdminOffFloorChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
