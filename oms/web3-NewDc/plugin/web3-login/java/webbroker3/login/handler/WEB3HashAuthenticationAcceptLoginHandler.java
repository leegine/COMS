head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �n�b�V���F�؃��O�C���n���h��(WEB3HashAuthenticationAcceptLoginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 �h�C(���u) �V�K�쐬
*/

package webbroker3.login.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginRequest;
import webbroker3.login.message.WEB3HashAuthenticationAcceptLoginResponse;
import webbroker3.login.service.delegate.WEB3HashAuthenticationAcceptLoginService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * �n�b�V���F�؃��O�C���n���h���B<BR>
 * @@author      �h�C(���u)
 * @@version     1.00
 */
public final class WEB3HashAuthenticationAcceptLoginHandler implements MessageHandler
{
    /**
     * ���O�o��
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3HashAuthenticationAcceptLoginHandler.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 403EF3C9020A
     */
    public WEB3HashAuthenticationAcceptLoginHandler()
    {

    }

    /**
     * (�n�b�V���F�؃��O�C�����N�G�X�g)<BR>
     * @@param l_request
     * @@return Response
     * @@roseuid 403DBE5E0222
     */
    public Response hashAuthenticationAcceptLoginRequest(
    	WEB3HashAuthenticationAcceptLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "hashAuthenticationAcceptLoginRequest(WEB3HashAuthenticationAcceptLoginRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3HashAuthenticationAcceptLoginResponse l_response = null;
        WEB3HashAuthenticationAcceptLoginService l_service = null;

        try
        {
            l_service =
                (WEB3HashAuthenticationAcceptLoginService) Services.getService(
                		WEB3HashAuthenticationAcceptLoginService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3HashAuthenticationAcceptLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3HashAuthenticationAcceptLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3HashAuthenticationAcceptLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3HashAuthenticationAcceptLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3HashAuthenticationAcceptLoginService.execute error.",
                e);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
