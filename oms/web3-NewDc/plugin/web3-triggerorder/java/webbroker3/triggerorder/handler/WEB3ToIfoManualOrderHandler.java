head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�蓮�����n���h��(WEB3ToIfoManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�蓮�����n���h��)<BR>
 * �敨OP�蓮�����n���h���N���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderHandler.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToIfoManualOrderHandler() 
    {
     
    }
    
    /**
     * (confirm�蓮����)<BR>
     * �敨OP�蓮�����m�F�������s���B<BR>
     * <BR>
     * �敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�敨OP�蓮�����m�F���N�G�X�g)<BR>
     * �敨OP�蓮�����m�F���N�G�X�g<BR>
     * @@return webbroker.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3FuturesOptionsManualConfirmResponse confirmManual(WEB3FuturesOptionsManualConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManual(WEB3FuturesOptionsManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOptionsManualConfirmResponse l_response = null;
        WEB3ToIfoManualOrderService l_service = null;
        
        try
        {
            //get�敨OP�蓮�����T�[�r�X
            l_service = (WEB3ToIfoManualOrderService) Services.getService(WEB3ToIfoManualOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�蓮�����m�F�����̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�蓮�����m�F�����̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�蓮����)<BR>
     * �敨OP�蓮���������������s���B<BR>
     * <BR>
     * �敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�敨OP�蓮�����������N�G�X�g)<BR>
     * �敨OP�蓮�����������N�G�X�g<BR>
     * @@return webbroker.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3FuturesOptionsManualCompleteResponse completeManual(WEB3FuturesOptionsManualCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeManual(WEB3FuturesOptionsManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOptionsManualCompleteResponse l_response = null;
        WEB3ToIfoManualOrderService l_service = null;
        
        try
        {
            //get�敨OP�蓮�����T�[�r�X
            l_service = (WEB3ToIfoManualOrderService) Services.getService(WEB3ToIfoManualOrderService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�蓮�������������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�敨OP�蓮�������������̎��{�Ɏ��s���܂����B", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
