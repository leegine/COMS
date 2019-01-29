head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����t�w�l�����蓮�����n���h��(WEB3ToStopEquityManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@���r(���u) �V�K�쐬
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3EquityManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3EquityManualConfirmResponse;
import webbroker3.triggerorder.message.WEB3EquityStopManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3EquityStopManualConfirmRequest;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderMainService;
import webbroker3.util.WEB3LogUtility;



/**
 * (�����t�w�l�����蓮�����n���h��)<BR>
 * �����t�w�l�����蓮�����n���h���N���X<BR>
 * 
 * @@author ���r
 * @@version 1.0
 */
public class WEB3ToStopEquityManualOrderHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityManualOrderHandler.class);

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToStopEquityManualOrderHandler() 
    {
     
    }
    
    /**
     * (confirm�蓮����)<BR>
     * �����t�w�l�����蓮�����m�F�������s���B<BR>
     * �P�j�@@�����t�w�l�����蓮�������C���T�[�r�XImpl���擾���A<BR>  
     *�@@�@@�@@execute()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@request�F�@@�p�����[�^.�����t�w�l�����蓮�����m�F���N�G�X�g <BR> 
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �����t�w�l�����蓮�����m�F���N�G�X�g 
     * @@return WEB3EquityManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3EquityManualConfirmResponse confirmManualOrder(
        WEB3EquityStopManualConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "confirmManualOrder(WEB3EquityManualConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualConfirmResponse l_response = null;
        WEB3ToStopEquityManualOrderMainService l_service = null;
        
        try
        {
            //get�����t�w�l�����蓮�������C���T�[�r�X
            l_service = (WEB3ToStopEquityManualOrderMainService) 
                Services.getService(
                WEB3ToStopEquityManualOrderMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����t�w�l�����蓮�������C���T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3EquityManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����t�w�l�����蓮�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����t�w�l�����蓮�����m�F�����̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�蓮����)<BR>
     * �����t�w�l�����蓮���������������s���B<BR>
     * �P�j�@@�����t�w�l�����蓮�������C���T�[�r�XImpl���擾���A<BR>  
     * �@@�@@�@@execute()���\�b�h���R�[������B<BR> 
     * <BR>
     * �@@�@@�@@�@@[execute()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�@@request�F�@@�p�����[�^.�����t�w�l�����蓮�����������N�G�X�g<BR> 
     * <BR>
     * �Q�j�@@execute()�̖߂�l��Ԃ��B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * �����t�w�l�����蓮�����������N�G�X�g<BR>
     * @@return WEB3EquityManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3EquityManualCompleteResponse completeManualOrder(
        WEB3EquityStopManualCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "completeManualOrder(WEB3EquityManualCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualCompleteResponse l_response = null;
        WEB3ToStopEquityManualOrderMainService l_service = null;
        
        try
        {
            //get�����t�w�l�����蓮�������C���T�[�r�X
            l_service = (WEB3ToStopEquityManualOrderMainService) 
                Services.getService(
                WEB3ToStopEquityManualOrderMainService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����t�w�l�����蓮�������C���T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3EquityManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����t�w�l�����蓮�������������̎��{�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����t�w�l�����蓮�������������̎��{�Ɏ��s���܂����B", 
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
