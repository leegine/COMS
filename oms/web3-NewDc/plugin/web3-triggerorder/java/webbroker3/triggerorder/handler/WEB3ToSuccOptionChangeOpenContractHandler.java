head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�����V�K���n���h���iWEB3ToSuccOptionChangeOpenContractHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/11 �g�E�N�| (���u) �V�K�쐬 ���f��282
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�����V�K���n���h��)<BR>
 * �i�A���j�I�v�V���������V�K���n���h���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeOpenContractHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeOpenContractHandler.class);

    /**
     * @@roseuid 47FDBE3D031D
     */
    public WEB3ToSuccOptionChangeOpenContractHandler()
    {

    }

    /**
     * (confirm�����V�K��)<BR>
     * �i�A���j�I�v�V���������V�K�������̔����R�����s���B<BR>
     * <BR>
     * �i�A���j�I�v�V���������V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsOpenChangeConfirmResponse
     * @@roseuid 47A920160208
     */
    public WEB3SuccOptionsOpenChangeConfirmResponse confirmChangeOpenContract(
        WEB3SuccOptionsOpenChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChangeOpenContract(WEB3SuccOptionsOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenChangeConfirmResponse l_response = null;
        WEB3ToSuccOptionChangeOpenContractService l_service = null;
        try
        {
            // �i�A���j�I�v�V���������V�K���T�[�r�X���擾
            l_service = (WEB3ToSuccOptionChangeOpenContractService)Services.getService(
                WEB3ToSuccOptionChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccOptionsOpenChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K�������̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K�������̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�����V�K��)<BR>
     * �i�A���j�I�v�V���������V�K�������̍X�V���s���B<BR>
     * <BR>
     * �i�A���j�I�v�V���������V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsOpenChangeCompleteResponse
     * @@roseuid 47A9203101E9
     */
    public WEB3SuccOptionsOpenChangeCompleteResponse completeChangeOpenContract(
        WEB3SuccOptionsOpenChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChangeOpenContract(WEB3SuccOptionsOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenChangeCompleteResponse l_response = null;
        WEB3ToSuccOptionChangeOpenContractService l_service = null;
        try
        {
            // �i�A���j�I�v�V���������V�K���T�[�r�X���擾
            l_service = (WEB3ToSuccOptionChangeOpenContractService)Services.getService(
                WEB3ToSuccOptionChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccOptionsOpenChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K�������̍X�V�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V���������V�K�������̍X�V�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
