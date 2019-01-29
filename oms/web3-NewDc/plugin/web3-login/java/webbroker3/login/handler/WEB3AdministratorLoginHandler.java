head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.29.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AdministratorLoginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �ڋq���O�C���n���h��(WEB3AdministratorLoginHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/04/28 �e�n(SRA)
 */

package webbroker3.login.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3AdministratorLoginRequest;
import webbroker3.login.message.WEB3AdministratorLoginResponse;
import webbroker3.login.service.delegate.WEB3AdministratorLoginService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Response;

/**
 * �i�Ǘ��҃��O�C���n���h���j
 * �Ǘ��҃��O�C���n���h��<BR>
 * <BR> 
 * @@author      �e�n(SRA)
 * @@version     1.00
 * @@see com.fitechlabs.xtrade.kernel.handler.MessageHandler
 */
public class WEB3AdministratorLoginHandler implements MessageHandler
{
    private static WEB3LogUtility l_log =
        WEB3LogUtility.getInstance(WEB3AdministratorLoginHandler.class);

    /**
     * @@roseuid 408F4D410164
     */
    public WEB3AdministratorLoginHandler()
    {

    }

    /**
     * �i�Ǘ��҃��O�C�����N�G�X�g�j
     * @@param l_request
     * @@return Response
     * @@roseuid 4084F6A20048
     */
    public Response administratorLoginRequest(WEB3AdministratorLoginRequest l_request)
    {
        final String STR_METHOD_NAME =
            "administratorLoginRequest(WEB3AdministratorLoginRequest)";
        l_log.entering(STR_METHOD_NAME);

        WEB3AdministratorLoginResponse l_response = null;
        WEB3AdministratorLoginService l_service = null;

        try
        {
            l_service =
                (WEB3AdministratorLoginService) Services.getService(
                    WEB3AdministratorLoginService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3AdministratorLoginResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... Services.getService(WEB3AdministratorLoginService.class) error.",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdministratorLoginResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3AdministratorLoginResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            l_log.error(
                l_request,
                STR_METHOD_NAME
                    + " .... WEB3AdministratorLoginService.execute error.",
                e);
            return l_response;
        }

        l_log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
