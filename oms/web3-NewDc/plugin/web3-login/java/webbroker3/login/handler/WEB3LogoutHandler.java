head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3LogoutHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ���O�A�E�g�n���h��(WEB3LogoutHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/02/26 �e�n(SRA)
 */

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3LogoutRequest;
import webbroker3.login.message.WEB3LogoutResponse;
import webbroker3.login.service.delegate.WEB3LogoutService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (���O�A�E�g�n���h��)<BR>
 * ���O�A�E�g�n���h��<BR>
 *<BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3LogoutHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3LogoutHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 403EEF5D03DE
     */
    public WEB3LogoutHandler()
    {

    }

    /**
     * (���O�A�E�g���N�G�X�g)<BR>
     * @@param l_request
     * @@return Response
     * @@roseuid 403DA84300BA
     */
    public Response logoutRequest(WEB3LogoutRequest l_request)
    {
        final String STR_METHOD_NAME = "logoutRequest(WEB3LogoutRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3LogoutResponse l_response = null;
        WEB3LogoutService l_service = null;

        try
        {
            l_service =
                (WEB3LogoutService) Services.getService(
                    WEB3LogoutService.class);
        }
        catch (Exception e)
        {
            l_response = (WEB3LogoutResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3LogoutService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3LogoutResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3LogoutResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME + " .... WEB3LogoutService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
