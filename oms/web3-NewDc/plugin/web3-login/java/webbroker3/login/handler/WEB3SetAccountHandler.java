head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.29.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3SetAccountHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客成りすましハンドラ(WEB3SetAccountHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/08 菊地(SRA)
 */

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginRequest;
import webbroker3.login.message.WEB3OpeAcceptAutoLoginResponse;
import webbroker3.login.message.WEB3OpeAcceptSlingshotRequest;
import webbroker3.login.message.WEB3OpeAcceptSlingshotResponse;
import webbroker3.login.message.WEB3SetAccountRequest;
import webbroker3.login.message.WEB3SetAccountResponse;
import webbroker3.login.service.delegate.WEB3OpeAcceptAutoLoginService;
import webbroker3.login.service.delegate.WEB3OpeAcceptSlingshotService;
import webbroker3.login.service.delegate.WEB3SetAccountService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (顧客成りすましハンドラ)
 * 顧客成りすましハンドラ<BR>
 * <BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3SetAccountHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3SetAccountHandler.class);

    /**
     * @@roseuid 4045CB59023D
     */
    public WEB3SetAccountHandler()
    {

    }

    /**
     * (顧客成りすましリクエスト)
     * @@param l_request
     * @@return Response
     * @@roseuid 404457C80044
     */
    public Response setAccountRequest(WEB3SetAccountRequest l_request)
    {
        final String STR_METHOD_NAME =
            "setAccountRequest(WEB3SetAccountRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3SetAccountResponse l_response = null;
        WEB3SetAccountService l_service = null;

        try
        {
            l_service =
                (WEB3SetAccountService) Services.getService(
                    WEB3SetAccountService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3SetAccountResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3SetAccountService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3SetAccountResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3SetAccountResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME + " .... WEB3SetAccountService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （顧客成りすましSS遷移リクエスト）
     * @@param l_request
     * @@return com.fitechlabs.xtrade.kernel.message.Response
     * @@roseuid 406D253002FD
     */
    public Response opeAcceptSlingshotRequest(WEB3OpeAcceptSlingshotRequest l_request)
    {
        final String STR_METHOD_NAME =
            "opeAcceptSlingshotRequest(WEB3OpeAcceptSlingshotRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3OpeAcceptSlingshotResponse l_response = null;
        WEB3OpeAcceptSlingshotService l_service = null;

        try
        {
            l_service =
                (WEB3OpeAcceptSlingshotService) Services.getService(
                    WEB3OpeAcceptSlingshotService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3OpeAcceptSlingshotResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3OpeAcceptSlingshotResponse.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OpeAcceptSlingshotResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OpeAcceptSlingshotResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3OpeAcceptSlingshotService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * （顧客成りすまし自動ログインリクエスト）
     * @@param l_request
     * @@return com.fitechlabs.xtrade.kernel.message.Response
     * @@roseuid 406D258300DA
     */
    public Response opeAcceptAutoLoginRequest(WEB3OpeAcceptAutoLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "opeAcceptAutoLoginRequest(WEB3OpeAcceptAutoLoginRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3OpeAcceptAutoLoginResponse l_response = null;
        WEB3OpeAcceptAutoLoginService l_service = null;

        try
        {
            l_service =
                (WEB3OpeAcceptAutoLoginService) Services.getService(
                    WEB3OpeAcceptAutoLoginService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3OpeAcceptAutoLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3OpeAcceptAutoLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OpeAcceptAutoLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OpeAcceptAutoLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3OpeAcceptAutoLoginService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
