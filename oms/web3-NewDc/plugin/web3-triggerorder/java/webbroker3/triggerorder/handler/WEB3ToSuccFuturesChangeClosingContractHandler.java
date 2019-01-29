head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨�����ԍσn���h���iWEB3ToSuccFuturesChangeClosingContractHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 �g�E�N�| (���u) �V�K�쐬 ���f��No.270
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�����ԍσn���h��)<BR>
 * �i�A���j�敨�����ԍσT�[�r�X�n���h���N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractHandler.class);

    /**
     * @@roseuid 47D65936001F
     */
    public WEB3ToSuccFuturesChangeClosingContractHandler()
    {

    }

    /**
     * (confirm�����ԍ�)<BR>
     * �i�A���j�敨�����ԍς̔����R�����s���B<BR>
     * <BR>
     * �i�A���j�敨�����ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseChangeConfirmResponse
     * @@roseuid 47A9511E01B8
     */
    public WEB3SuccFuturesCloseChangeConfirmResponse confirmChangeClosingContract(
        WEB3SuccFuturesCloseChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3SuccFuturesCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            //�i�A���j�敨�����ԍσT�[�r�X���擾
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(�i�A���j�敨�����ԍσT�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����ԍς̔����R�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����ԍς̔����R�������s���܂����B",
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
     * �i�A���j�敨�����ԍς̍X�V���s���B<BR>
     * <BR>
     * �i�A���j�敨�����ԍσT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseChangeCompleteResponse
     * @@roseuid 47A9513702A6
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse completeChangeClosingContract(
        WEB3SuccFuturesCloseChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3SuccFuturesCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            //�i�A���j�敨�����ԍσT�[�r�X���擾
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(�i�A���j�敨�����ԍσT�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()���\�b�h���R�[������
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����ԍς̍X�V�����s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�����ԍς̍X�V�����s���܂����B",
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
