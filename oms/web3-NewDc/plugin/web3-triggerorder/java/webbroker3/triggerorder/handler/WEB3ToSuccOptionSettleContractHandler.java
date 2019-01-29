head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionSettleContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�i�A���jOP�ԍϒ����n���h��(WEB3ToSuccOptionSettleContractHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/04/09 �k�v�u(���u) �V�K�쐬 ���f��283
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�ԍϒ����n���h��)<BR>
 * �i�A���j�I�v�V�����ԍϒ����n���h���N���X<BR>
 * <BR>
 * @@author �k�v�u
 * @@version 1.0
 */
public class WEB3ToSuccOptionSettleContractHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractHandler.class);

    /**
     * @@roseuid 47FDBE3E00BB
     */
    public WEB3ToSuccOptionSettleContractHandler()
    {

    }

    /**
     * (confirm�ԍ�)<BR>
     * �i�A���jOP�ԍς̔����R�����s���B<BR>
     * <BR>
     * �i�A���jOP�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsCloseConfirmResponse
     * @@roseuid 47A922D303E0
     */
    public WEB3SuccOptionsCloseConfirmResponse confirmSettleContract(
        WEB3SuccOptionsCloseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmSettleContract(WEB3SuccOptionsCloseConfirmResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseConfirmResponse l_response = null;
        WEB3ToSuccOptionSettleContractOrderService l_service = null;

        //�i�A���jOP�ԍϒ����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�ԍϒ����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���jOP�ԍϒ����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccOptionsCloseConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�ԍς̔����R�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�ԍς̔����R�����������s���܂����B",
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
     * �i�A���jOP�ԍς̒�����o�^����B<BR>
     * <BR>
     * �i�A���jOP�ԍϒ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsCloseCompleteResponse
     * @@roseuid 47A922E103A0
     */
    public WEB3SuccOptionsCloseCompleteResponse completeSettleContract(
        WEB3SuccOptionsCloseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeSettleContract(WEB3SuccOptionsCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseCompleteResponse l_response = null;
        WEB3ToSuccOptionSettleContractOrderService l_service = null;

        //�i�A���jOP�ԍϒ����T�[�r�X���擾����
        try
        {
            l_service =
                (WEB3ToSuccOptionSettleContractOrderService)Services.getService(
                    WEB3ToSuccOptionSettleContractOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�ԍϒ����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //�i�A���jOP�ԍϒ����T�[�r�X.execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3SuccOptionsCloseCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�ԍς̒�����o�^���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���jOP�ԍς̒�����o�^���������s���܂����B",
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
