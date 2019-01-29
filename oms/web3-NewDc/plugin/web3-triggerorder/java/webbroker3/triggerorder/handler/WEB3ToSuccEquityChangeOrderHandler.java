head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j�������������n���h��(WEB3ToSuccEquityChangeOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 �A�C��(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���j�������������n���h��)<BR>
 * �i�A���j�������������n���h���N���X�B<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderHandler implements MessageHandler 
{
    
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderHandler.class);
    
    /**
     * @@roseuid 4369ED2E000F
     */
    public WEB3ToSuccEquityChangeOrderHandler() 
    {
     
    }
    
    /**
     * (confirm��������)<BR>
     * �i�A���j�����������������m�F�������s���B<BR>
     * <BR>
     * �u�i�A���j�������������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@roseuid 4338D8890058
     */
    public WEB3SuccEquityChangeConfirmResponse confirmChangeOrder(WEB3SuccEquityChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmChangeOrder(WEB3SuccEquityChangeConfirmRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeConfirmResponse l_response = null;
        WEB3ToSuccEquityChangeOrderService l_service = null;       

        // �i�A���j�������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderService) Services.getService(WEB3ToSuccEquityChangeOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "confirm���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete��������)<BR>
     * �i�A���j���������������������������s���B<BR>
     * <BR>
     * �u�i�A���j�������������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3EquityChangeCompleteResponse
     * @@roseuid 4338D9C10327
     */
    public WEB3SuccEquityChangeCompleteResponse completeChangeOrder(WEB3SuccEquityChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeChangeOrder(WEB3SuccEquityChangeCompleteRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeCompleteResponse l_response = null;
        WEB3ToSuccEquityChangeOrderService l_service = null;       

        // �i�A���j�������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderService) Services.getService(WEB3ToSuccEquityChangeOrderService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "�i�A���j�������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "complete���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete���������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
