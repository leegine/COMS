head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�敨��������n���h��(WEB3ToSuccFuturesCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/12 ���n(���u) �V�K�쐬���f��268
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesCancelOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j�敨��������n���h��)<BR>
 * �i�A���j�敨��������n���h���N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ToSuccFuturesCancelOrderHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccFuturesCancelOrderHandler.class);

    /**
     * @@roseuid 47D6593501F4
     */
    public WEB3ToSuccFuturesCancelOrderHandler()
    {

    }

    /**
     * (confirm���)<BR>
     * �i�A���j�����w���敨�̎�������R�����s���B<BR>
     * <BR>
     * �i�A���j�����w���敨��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCancelConfirmResponse
     * @@roseuid 47A953CC035A
     */
    public WEB3SuccFuturesCancelConfirmResponse confirmCancel(WEB3SuccFuturesCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmCancel(WEB3SuccFuturesCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmResponse l_response = null;
        WEB3ToSuccFuturesCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesCancelOrderService)Services.getService(
                WEB3ToSuccFuturesCancelOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���敨��������T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�̎�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�̎�������R���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete���)<BR>
     * �i�A���j�����w���敨�̎��������o�^����B<BR>
     * <BR>
     * �i�A���j�����w���敨��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3SuccFuturesCancelCompleteResponse
     * @@roseuid 47A953DC0030
     */
    public WEB3SuccFuturesCancelCompleteResponse completeCancel(WEB3SuccFuturesCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeCancel(WEB3SuccFuturesCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelCompleteResponse l_response = null;
        WEB3ToSuccFuturesCancelOrderService l_service = null;
        try
        {
            l_service = (WEB3ToSuccFuturesCancelOrderService)Services.getService(
                    WEB3ToSuccFuturesCancelOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�i�A���j�����w���敨��������T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�i�A���j�����w���敨�̎��������o�^���s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccFuturesCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�i�A���j�����w���敨�̎��������o�^���s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
