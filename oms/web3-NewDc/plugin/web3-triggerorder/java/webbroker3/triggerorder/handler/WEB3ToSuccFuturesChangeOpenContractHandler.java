head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�����V�K���n���h���iWEB3ToSuccFuturesChangeOpenContractHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 �g�E�N�| (���u) �V�K�쐬 ���f��No.269
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�����V�K���n���h��)<BR>
 * �i�A���j�敨�����V�K���n���h���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeOpenContractHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractHandler.class);

    /**
     * @@roseuid 47D65935033C
     */
    public WEB3ToSuccFuturesChangeOpenContractHandler()
    {

    }

    /**
     * (confirm�����V�K��)<BR>
     * �i�A���j�敨�����V�K�������̔����R�����s���B<BR>
     * <BR>
     * �i�A���j�敨�����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenChangeConfirmResponse
     * @@roseuid 47A944C703C9
     */
    public WEB3SuccFuturesOpenChangeConfirmResponse confirmChangeOpenContract(
        WEB3SuccFuturesOpenChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChangeOpenContract(WEB3SuccFuturesOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeConfirmResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            // �i�A���j�敨�����V�K���T�[�r�X���擾
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�����V�K���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����V�K�������̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����V�K�������̔����R�������s���܂����B",
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
     * �i�A���j�敨�����V�K�������̍X�V���s���B<BR>
     * <BR>
     * �i�A���j�敨�����V�K���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesOpenChangeCompleteResponse
     * @@roseuid 47A944D600CE
     */
    public WEB3SuccFuturesOpenChangeCompleteResponse completeChangeOpenContract(
        WEB3SuccFuturesOpenChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChangeOpenContract(WEB3SuccFuturesOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeCompleteResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            // �i�A���j�敨�����V�K���T�[�r�X���擾
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�����V�K���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����V�K�������̍X�V�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����V�K�������̍X�V�����s���܂����B",
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
