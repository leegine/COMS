head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�������蓮�����n���h��(WEB3ToSuccEquityManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@���g(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquitySuccManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquitySuccManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A�������蓮�����n���h��)<BR>
 * �����A�������蓮�����n���h���N���X<BR>
 * 
 * @@author ���g
 * @@version 1.0
 */
public class WEB3ToSuccEquityManualOrderHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityManualOrderHandler.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToSuccEquityManualOrderHandler() 
    {
     
    }
  
    /**
     * (confirm�蓮����)<BR>
     * �����A�������蓮�����m�F�������s���B<BR>
     * �P�j�@@�����A�������蓮�������C���T�[�r�XImpl���擾���A <BR> 
     * �@@�@@�@@execute()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@request�F�@@�p�����[�^�D�����A�������蓮�����m�F���N�G�X�g <BR>
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * �����A�������蓮�����m�F���N�G�X�g<BR>
     * @@return WEB3EquityManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquitySuccManualConfirmRequest l_request) 
    {        
        final String STR_METHOD_NAME = "confirmManualOrder(" +
                "WEB3EquityManualConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToSuccEquityManualOrderMainService l_service = null;
        
        try
        {
            l_service = 
                (WEB3ToSuccEquityManualOrderMainService) Services.getService(
                    WEB3ToSuccEquityManualOrderMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A�������蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //�P�j�@@�����A�������蓮�������C���T�[�r�XImpl���擾���A  
            //   execute()���\�b�h���R�[������B 
            //
            //     [execute()�Ɏw�肷�����] 
            //     request�F�@@�p�����[�^�D�����蓮�����m�F���N�G�X�g 
            l_response = (WEB3EquityManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A�������蓮�����m�F�����̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A�������蓮�����m�F�����̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Q�j�@@execute()�̖߂�l��Ԃ�
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�蓮����)<BR>
     * �����A�������蓮�����m�F�������s���B<BR>
     * �P�j�@@�����A�������蓮�������C���T�[�r�XImpl���擾���A<BR>  
     * �@@�@@�@@execute()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@request�F�@@�p�����[�^�D�����A�������蓮�����������N�G�X�g <BR>
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * �����A�������蓮�����������N�G�X�g<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquitySuccManualCompleteRequest l_request) 
    {   
        final String STR_METHOD_NAME = "completeManualOrder(" +
                "WEB3EquityManualCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToSuccEquityManualOrderMainService l_service = null;
        
        try
        {
            l_service = 
                (WEB3ToSuccEquityManualOrderMainService) Services.getService(
                    WEB3ToSuccEquityManualOrderMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A�������蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //�P�j�@@�����A�������蓮�������C���T�[�r�XImpl���擾���A  
            //     execute()���\�b�h���R�[������B 
            //
            //       [execute()�Ɏw�肷�����] 
            //       request�F�@@�p�����[�^�D�����蓮�����������N�G�X�g
            l_response = (WEB3EquityManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A�������蓮�����������̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A�������蓮�������������̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //�Q�j�@@execute()�̖߂�l��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
