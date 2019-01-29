head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.29.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorLoginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータログインハンドラ(WEB3CCOperatorLoginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/03/05 菊地(SRA)
 */

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3CCOperatorLoginRequest;
import webbroker3.login.message.WEB3CCOperatorLoginResponse;
import webbroker3.login.service.delegate.WEB3CCOperatorLoginService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (CCオペレータログインハンドラ)<BR>
 * CCオペレータログインハンドラ<BR>
 *<BR> 
 * @@author      菊地(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler

 */
public class WEB3CCOperatorLoginHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3CCOperatorLoginHandler.class);

    /**
     * @@roseuid 4045C30102DA
     */
    public WEB3CCOperatorLoginHandler()
    {

    }

    /**
     * (CCオペレータログインリクエスト)
     * @@param l_request
     * @@return Response
     * @@roseuid 4044307C0351
     */
    public Response ccOperatorLoginRequest(WEB3CCOperatorLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "ccOperatorLoginRequest(WEB3CCOperatorLoginRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3CCOperatorLoginResponse l_response = null;
        WEB3CCOperatorLoginService l_service = null;

        try
        {
            l_service =
                (WEB3CCOperatorLoginService) Services.getService(
                    WEB3CCOperatorLoginService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3CCOperatorLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3CCOperatorLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3CCOperatorLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3CCOperatorLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3CCOperatorLoginService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
