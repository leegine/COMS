head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderRouteChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者発注経路切替ハンドラクラス(WEB3AdminFrontOrderRouteChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者発注経路切替ハンドラ)<BR>
 * <BR>
 * 管理者発注経路切替ハンドラクラス<BR>
 * <BR>
 * WEB3AdminFrontOrderRouteChangeHandler<BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public class WEB3AdminFrontOrderRouteChangeHandler implements MessageHandler {
    
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontOrderRouteChangeHandler.class);
        
    /**
     * @@roseuid 43001A6C0255
     */
    public WEB3AdminFrontOrderRouteChangeHandler()
    {

    }
    
    /**
    * 管理者発注経路切替選択画面表示処理を行う。<BR>
    * <BR>
    * 管理者発注経路切替サービスImplを取得し、<BR>
    * execute()メソッドをコールする。<BR>
    * @@param リクエストデータ - 管理者・発注経路切替選択リクエストオブジェクト
    * @@return 管理者・発注経路切替選択レスポンス
    * @@roseuid 42D232520330
    */
    public WEB3AdminFrontRouteChangeSelectResponse getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest l_request)
    {
        final String STR_METHOD_NAME = "getSelectScreen(WEB3AdminFrontRouteChangeSelectRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFrontRouteChangeSelectResponse l_response = null;
        WEB3AdminFrontOrderRouteChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminFrontOrderRouteChangeService) Services.getService(
                   WEB3AdminFrontOrderRouteChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminFrontRouteChangeSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFrontOrderRouteChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminFrontRouteChangeSelectResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getSelectScreen()", l_exp);
            l_response = (WEB3AdminFrontRouteChangeSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 管理者発注経路切替確認処理を行う。<BR>
     * <BR>
     * 管理者発注経路切替サービスImplを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param リクエストデータ - 管理者・発注経路切替確認リクエストオブジェクト
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteComfirmResponse
     * @@roseuid 42D232AE03DC
     */
    public WEB3AdminFrontRouteChangeConfirmResponse validateChange(WEB3AdminFrontRouteChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminFrontRouteChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFrontRouteChangeConfirmResponse l_response = null;
        WEB3AdminFrontOrderRouteChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminFrontOrderRouteChangeService) Services.getService(
                     WEB3AdminFrontOrderRouteChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminFrontRouteChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring " + "WEB3AdminFrontOrderRouteChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminFrontRouteChangeConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateChange()", l_exp);
            l_response = (WEB3AdminFrontRouteChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 管理者発注経路切替完了処理を行う。<BR>
     * <BR>
     * 管理者発注経路切替サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param リクエストデータ - 管理者・発注経路切替完了リクエストオブジェクト
     * @@return webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse
     * @@roseuid 42D232DD00FE
     */
    public WEB3AdminFrontRouteChangeCompleteResponse
        submitChange(WEB3AdminFrontRouteChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminFrontRouteChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminFrontRouteChangeCompleteResponse l_response = null;
        WEB3AdminFrontOrderRouteChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminFrontOrderRouteChangeService) Services.getService(
            WEB3AdminFrontOrderRouteChangeService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminFrontRouteChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFrontOrderRouteChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminFrontRouteChangeCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitChange()", l_exp);
            l_response = (WEB3AdminFrontRouteChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
