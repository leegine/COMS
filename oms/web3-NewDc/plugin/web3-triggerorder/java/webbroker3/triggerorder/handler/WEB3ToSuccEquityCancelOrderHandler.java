head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�A���j������������n���h��(WEB3ToSuccEquityCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  鰊](���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i�A���j������������n���h��)<BR>
 * �i�A���j������������n���h���N���X�B<BR>
 * 
 * @@ author 鰊] <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3ToSuccEquityCancelOrderHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderHandler.class);

    /**
     * @@roseuid 4369ED2C0251
     */
    public WEB3ToSuccEquityCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm�������)<BR>
     * �i�A���j����������������m�F�������s���B<BR>
     * <BR>
     * �u�i�A���j������������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccEquityCancelConfirmResponse
     * @@roseuid 433A07100193
     */
    public WEB3SuccEquityCancelConfirmResponse confirmCancelOrder(WEB3SuccEquityCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCancelOrder(WEB3SuccEquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityCancelConfirmResponse l_response = null;
        WEB3ToSuccEquityCancelOrderService l_service = null;
        
        // �i�A���j������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service = (WEB3ToSuccEquityCancelOrderService)Services.getService(WEB3ToSuccEquityCancelOrderService.class); 
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_service.execute(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm��������m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
        catch(WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm��������m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
         
        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    
    /**
     * (complete�������)<BR>
     * �i�A���j��������������������������s���B<BR>
     * <BR>
     * �u�i�A���j������������T�[�r�X�v���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3SuccEquityCancelCompleteResponse
     * @@roseuid 433A07100195
     */
    public WEB3SuccEquityCancelCompleteResponse completeCancelOrder(WEB3SuccEquityCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCancelOrder(WEB3SuccEquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityCancelCompleteResponse l_response = null;
        WEB3ToSuccEquityCancelOrderService l_service = null;
        
        // �i�A���j������������T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_service = (WEB3ToSuccEquityCancelOrderService)Services.getService(WEB3ToSuccEquityCancelOrderService.class); 
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request,
                "�i�A���j������������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_service.execute(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete������������Ɏ��s���܂����B", l_ex);  
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
        catch(WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete������������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }

        log.exiting(STR_METHOD_NAME);

        // ���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
