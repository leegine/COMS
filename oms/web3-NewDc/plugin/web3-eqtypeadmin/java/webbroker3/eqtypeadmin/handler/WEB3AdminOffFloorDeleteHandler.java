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
filename	WEB3AdminOffFloorDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��җ���O���������폜�n���h���N���X)
                        (WEB3AdminOffFloorDeleteHandler.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorDeleteService;

/**
 * (�Ǘ��җ���O���������폜�n���h��)<BR>
 * <BR>
 * �Ǘ��җ���O���������폜�n���h���N���X<BR>
 * <BR>
  * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminOffFloorDeleteHandler class<BR>
 * <BR>
 * @@author Shruti
 * @@version 1.0
 */
public class WEB3AdminOffFloorDeleteHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOffFloorDeleteHandler.class);

    /**
     * @@roseuid 421AE53000CE
     */
    public WEB3AdminOffFloorDeleteHandler()
    {

    }

    /**
     * (validate�폜)<BR>
     * �Ǘ��җ���O���������폜�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��җ���O���������폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * ------<English>--------------------------------<BR>
     * <BR>
     * validateDelete<BR>
     * <BR>
     * Execute WEB3AdminOffFloorDeleteService validate process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorDeleteService, and call execute() method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������폜�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * -----<English>----------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorDeleteConfirmRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse
     * @@roseuid 41BD8E940089
     */
    public WEB3AdminOffFloorDeleteConfirmResponse validateDelete(WEB3AdminOffFloorDeleteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateDelete(WEB3AdminOffFloorDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOffFloorDeleteConfirmResponse l_response = null;
        WEB3AdminOffFloorDeleteService l_service = null;

        try
        {
            l_service =
                (WEB3AdminOffFloorDeleteService) Services.getService(
                    WEB3AdminOffFloorDeleteService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorDeleteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorDeleteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorDeleteConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access confirmDelete()", l_exp);
            l_response = (WEB3AdminOffFloorDeleteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�폜)<BR>
     * <BR>
     * �Ǘ��җ���O���������폜�����������s���B<BR>
     * <BR>
     * �Ǘ��җ���O���������폜�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * -----<English>----------------------<BR>
     * <BR>
     * submitDelete<BR>
     * <BR>
     * Execute WEB3AdminOffFloorDeleteService submit process<BR>
     * <BR>
     * Acquire WEB3AdminOffFloorDeleteService, and call execute() method.<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��җ���O���������X�V�������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOffFloorChangeCompleteRequest object<BR>
     * <BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse
     * @@roseuid 41BD8E94008B
     */
    public WEB3AdminOffFloorDeleteCompleteResponse
        submitDelete(WEB3AdminOffFloorDeleteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitDelete(WEB3AdminOffFloorDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminOffFloorDeleteCompleteResponse l_response = null;
        WEB3AdminOffFloorDeleteService l_service = null;

        try
        {
            l_service =
                (WEB3AdminOffFloorDeleteService) Services.getService(
                    WEB3AdminOffFloorDeleteService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminOffFloorDeleteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOffFloorDeleteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminOffFloorDeleteCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitDelete()", l_exp);
            l_response = (WEB3AdminOffFloorDeleteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
