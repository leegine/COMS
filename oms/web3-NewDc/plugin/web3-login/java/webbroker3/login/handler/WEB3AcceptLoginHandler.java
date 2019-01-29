head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.29.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptLoginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログインハンドラ(WEB3AcceptLoginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 菊地(SRA)
 */

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3AcceptAutoLoginRequest;
import webbroker3.login.message.WEB3AcceptAutoLoginResponse;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3AcceptLoginResponse;
import webbroker3.login.message.WEB3AcceptSlingshotRequest;
import webbroker3.login.message.WEB3AcceptSlingshotResponse;
import webbroker3.login.service.delegate.WEB3AcceptAutoLoginService;
import webbroker3.login.service.delegate.WEB3AcceptLoginService;
import webbroker3.login.service.delegate.WEB3AcceptSlingshotService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (顧客ログインハンドラ)<BR>
 * 顧客ログインハンドラ<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3AcceptLoginHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginHandler.class);

    /**
     * @@roseuid 403EE06B0054
     */
    public WEB3AcceptLoginHandler()
    {

    }

    /**
     * (顧客ログインリクエスト)<BR>
     * @@param l_request
     * @@return Response
     * @@roseuid 4016559003CE
     */
    public Response acceptLoginRequest(WEB3AcceptLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "acceptLoginRequest(WEB3AcceptLoginRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3AcceptLoginResponse l_response = null;
        WEB3AcceptLoginService l_service = null;

        try
        {
            l_service =
                (WEB3AcceptLoginService) Services.getService(
                    WEB3AcceptLoginService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3AcceptLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3AcceptLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3AcceptLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3AcceptLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME + " .... WEB3AcceptLoginService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （顧客SS遷移リクエスト）
     * @@param l_request
     * @@return com.fitechlabs.xtrade.kernel.message.Response
     * @@roseuid 40640ABC03B8
     */
    public Response acceptSlingshotRequest(WEB3AcceptSlingshotRequest l_request)
    {
        final String STR_METHOD_NAME =
            "acceptSlingshotRequest(WEB3AcceptSlingshotRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3AcceptSlingshotResponse l_response = null;
        WEB3AcceptSlingshotService l_service = null;

        try
        {
            l_service =
                (WEB3AcceptSlingshotService) Services.getService(
                    WEB3AcceptSlingshotService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3AcceptSlingshotResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3AcceptSlingshotService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AcceptSlingshotResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3AcceptSlingshotResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3AcceptSlingshotService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （顧客自動ログインリクエスト）
     * @@param l_request
     * @@return com.fitechlabs.xtrade.kernel.message.Response
     * @@roseuid 40640AD40203
     */
    public Response acceptAutoLoginRequest(WEB3AcceptAutoLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "acceptAutoLoginRequest(WEB3AcceptAutoLoginRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3AcceptAutoLoginResponse l_response = null;
        WEB3AcceptAutoLoginService l_service = null;

        try
        {
            l_service =
                (WEB3AcceptAutoLoginService) Services.getService(
                    WEB3AcceptAutoLoginService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3AcceptAutoLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3AcceptAutoLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AcceptAutoLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3AcceptAutoLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3AcceptAutoLoginService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
