head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionOpenContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���jOP�V�K�������n���h��(WEB3ToSuccOptionOpenContractOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���n(���u) �V�K�쐬���f��281
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���jOP�V�K�������n���h��)<BR>
 * �i�A���j�I�v�V�����V�K�������n���h���N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccOptionOpenContractOrderHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionOpenContractOrderHandler.class);

    /**
     * @@roseuid 47FDBE3D0251
     */
    public WEB3ToSuccOptionOpenContractOrderHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * �i�A���j�I�v�V�����V�K�������R�����s���B<BR>
     * <BR>
     * �i�A���jOP�V�K���T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsOpenConfirmResponse
     * @@roseuid 47A918480349
     */
    public WEB3SuccOptionsOpenConfirmResponse confirmOrder(WEB3SuccOptionsOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3SuccOptionsOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenConfirmResponse l_response = null;
        WEB3ToSuccOptionOpenContractOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionOpenContractOrderService)Services.getService(
                WEB3ToSuccOptionOpenContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�V�K���T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccOptionsOpenConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V�����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�i�A���j�I�v�V�����V�K�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete����)<BR>
     * �i�A���j�I�v�V�����V�K�������o�^���s���B<BR>
     * <BR>
     * �i�A���jOP�V�K���T�[�r�XImpl���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccOptionsOpenCompleteResponse
     * @@roseuid 47A91870020E
     */
    public WEB3SuccOptionsOpenCompleteResponse completeOrder(WEB3SuccOptionsOpenCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3SuccOptionsOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsOpenCompleteResponse l_response = null;
        WEB3ToSuccOptionOpenContractOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccOptionOpenContractOrderService)Services.getService(
                WEB3ToSuccOptionOpenContractOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���jOP�V�K���T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccOptionsOpenCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�I�v�V�����V�K�������o�^���s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccOptionsOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�i�A���j�I�v�V�����V�K�������o�^���s���܂����B",
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
