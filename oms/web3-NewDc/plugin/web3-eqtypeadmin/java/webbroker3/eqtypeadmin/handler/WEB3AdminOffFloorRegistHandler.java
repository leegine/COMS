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
filename	WEB3AdminOffFloorRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��җ���O���������V�K�o�^�n���h�� (WEB3AdminOffFloorRegistHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorRegistService;

/**
 * �i�Ǘ��җ���O���������V�K�o�^�n���h���j<BR>
 * <BR>
 * �Ǘ��җ���O���������V�K�o�^�n���h���N���X<BR>
 * <BR>
 * WEB3AdminOffFloorRegistHandler<BR>
 * <BR>
 * @@author Saravanan
 * @@version 1.0
 */
public class WEB3AdminOffFloorRegistHandler implements MessageHandler
{

    /**
    * @@log WEB3LogUtility
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorRegistHandler.class);
    /**
     * @@roseuid 421AE54C0320
     */
    public WEB3AdminOffFloorRegistHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR
     * >
     * <BR>
     * ----<English>-----------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService screen process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorRegistService, and call execute() method.<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorRegistInputRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse
     * @@roseuid 41BD540102EA
     */
    public WEB3AdminOffFloorRegistInputResponse
        getInputScreen(WEB3AdminOffFloorRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminOffFloorRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOffFloorRegistInputResponse l_response = null;
        WEB3AdminOffFloorRegistService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorRegistService) Services.getService(
                    WEB3AdminOffFloorRegistService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorRegistServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorRegistInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response =
				(WEB3AdminOffFloorRegistInputResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling getInputScreen()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�V�K�o�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR
     * >
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * validateRegist<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService validate process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorRegistService, and call execute() method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorRegistConfirmRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse
     * @@roseuid 41BD54A10367
     */
    public WEB3AdminOffFloorRegistConfirmResponse
     validateRegist(WEB3AdminOffFloorRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateRegist(WEB3AdminOffFloorRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOffFloorRegistConfirmResponse l_response = null;
        WEB3AdminOffFloorRegistService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorRegistService) Services.getService(
                    WEB3AdminOffFloorRegistService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorRegistServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorRegistConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response =
				(WEB3AdminOffFloorRegistConfirmResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
             log.error(l_request, "Error while calling validateRegist()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�V�K�o�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�����������s���B<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR
     * >
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * submitRegist<BR>
     * <BR>
     * Execute WEB3AdminOffFloorRegistService submit process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorRegistService, and call execute() method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������V�K�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorRegistCompleteRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse
     * @@roseuid 41BD54D50154
     */
    public WEB3AdminOffFloorRegistCompleteResponse
     submitRegist(WEB3AdminOffFloorRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AdminOffFloorRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOffFloorRegistCompleteResponse l_response = null;
        WEB3AdminOffFloorRegistService l_service = null;
        try
        {
            l_service =
                (WEB3AdminOffFloorRegistService) Services.getService(
                    WEB3AdminOffFloorRegistService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorRegistServiceImpl",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorRegistCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
			l_response =
				(WEB3AdminOffFloorRegistCompleteResponse) l_request.createResponse();
			l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Error while calling submitRegist()", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
