head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesSettleContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���j�敨�ԍϒ����n���h���iWEB3ToSuccFuturesSettleContractOrderHandler.java�j
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/18 �И���(���u) �V�K�쐬���f��No.259 No.291
 */
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨�ԍϒ����n���h��)<BR>
 * �i�A���j�敨�ԍϒ����n���h���N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3ToSuccFuturesSettleContractOrderHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesSettleContractOrderHandler.class);

    /**
     * @@roseuid 47D6593600EA
     */
    public WEB3ToSuccFuturesSettleContractOrderHandler()
    {

    }

    /**
     * (confirm�ԍ�)<BR>
     * �i�A���j�敨�ԍς̔����R�����s���B<BR>
     * <BR>
     * �i�A���j�敨�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseConfirmResponse
     * @@roseuid 47A94D2A0032
     */
    public WEB3SuccFuturesCloseConfirmResponse confirmSettleContract(WEB3SuccFuturesCloseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmSettleContract(WEB3SuccFuturesCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseConfirmResponse l_response = null;
        WEB3ToSuccFuturesSettleContractOrderService l_service = null;

        //�i�A���j�敨�ԍϒ����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractOrderService)Services.getService(
                    WEB3ToSuccFuturesSettleContractOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�ԍϒ����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���j�敨�ԍϒ����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccFuturesCloseConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�ԍς̔����R�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�ԍς̔����R�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (complete�ԍ�)<BR>
     * �i�A���j�敨�ԍς̒�����o�^����B<BR>
     * <BR>
     * �i�A���j�敨�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCloseCompleteResponse
     * @@roseuid 47A94D390238
     */
    public WEB3SuccFuturesCloseCompleteResponse completeSettleContract(
        WEB3SuccFuturesCloseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeSettleContract(WEB3SuccFuturesCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseCompleteResponse l_response = null;
        WEB3ToSuccFuturesSettleContractOrderService l_service = null;

        //�i�A���j�敨�ԍϒ����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccFuturesSettleContractOrderService)Services.getService(
                    WEB3ToSuccFuturesSettleContractOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�敨�ԍϒ����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���j�敨�ԍϒ����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccFuturesCloseCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�ԍς̒�����o�^���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�敨�ԍς̒�����o�^���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
