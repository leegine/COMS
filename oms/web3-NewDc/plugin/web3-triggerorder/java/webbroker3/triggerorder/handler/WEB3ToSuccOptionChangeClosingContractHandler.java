head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���jOP�����ԍσn���h���iWEB3ToSuccOptionChangeClosingContractHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 �И���(���u) �V�K�쐬 ���f��No.307
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�����ԍσn���h��)<BR>
 * �i�A���jOP�����ԍσT�[�r�X�n���h���N���X<BR>
 *
 * @@author �И���(���u)
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeClosingContractHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeClosingContractHandler.class);

    /**
     * @@roseuid 47FDBE3D03D8
     */
    public WEB3ToSuccOptionChangeClosingContractHandler()
    {

    }

    /**
     * (confirm�����ԍ�)<BR>
     * �i�A���jOP�����ԍς̔����R�����s���B<BR>
     * <BR>
     * �i�A���jOP�����ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccOptionsCloseChangeConfirmResponse
     * @@roseuid 47A92AE000FF
     */
    public WEB3SuccOptionsCloseChangeConfirmResponse confirmChangeClosingContract(
        WEB3SuccOptionsCloseChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3SuccOptionsCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractService l_service = null;
        try
        {
            //�i�A���jOP�����ԍϒ����T�[�r�X�����擾
            l_service = (WEB3ToSuccOptionChangeClosingContractService)Services.getService(
                WEB3ToSuccOptionChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�����ԍϒ����T�[�r�X�����擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����ԍς̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����ԍς̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�����ԍ�)<BR>
     * �i�A���jOP�����ԍς̍X�V���s���B<BR>
     * <BR>
     * �i�A���jOP�����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SuccOptionsCloseChangeCompleteResponse
     * @@roseuid 47A92B05015D
     */
    public WEB3SuccOptionsCloseChangeCompleteResponse completeChangeClosingContract(
        WEB3SuccOptionsCloseChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3SuccOptionsCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractService l_service = null;
        try
        {
            //�i�A���jOP�����ԍσT�[�r�X���擾
            l_service = (WEB3ToSuccOptionChangeClosingContractService)Services.getService(
                WEB3ToSuccOptionChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�����ԍσT�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����ԍς̍X�V�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�����ԍς̍X�V�����s���܂����B",
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
