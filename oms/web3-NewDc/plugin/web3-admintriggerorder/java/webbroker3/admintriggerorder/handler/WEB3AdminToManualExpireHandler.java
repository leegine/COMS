head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualExpireHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�����n���h��(WEB3AdminToManualExpireHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�A����(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�����n���h��)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�����n���h���N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminToManualExpireHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToManualExpireHandler.class);
    
    /**
     * @@roseuid 4419312A0128
     */
    public WEB3AdminToManualExpireHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �蓮�����������͉�ʕ\���������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�������̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseInputResponse
     * @@roseuid 440549660270
     */
    public WEB3AdminToManualLapseInputResponse getInputScreen(WEB3AdminToManualLapseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseInputResponse l_response = null;
        WEB3AdminToManualExpireService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
            l_service = (WEB3AdminToManualExpireService) Services.getService(WEB3AdminToManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����������͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����������͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm�蓮����)<BR>
     * �蓮�����m�F�������s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseConfirmResponse
     * @@roseuid 44054A390147
     */
    public WEB3AdminToManualLapseConfirmResponse confirmManualExpire(WEB3AdminToManualLapseConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManualExpire(WEB3AdminToManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseConfirmResponse l_response = null;
        WEB3AdminToManualExpireService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
            l_service = (WEB3AdminToManualExpireService) Services.getService(WEB3AdminToManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run�蓮����)<BR>
     * �蓮���������N�����s���B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮���������N�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseRunResponse
     * @@roseuid 44054C61033B
     */
    public WEB3AdminToManualLapseRunResponse runManualExpire(WEB3AdminToManualLapseRunRequest l_request) 
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminToManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseRunResponse l_response = null;
        WEB3AdminToManualExpireService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
            l_service = (WEB3AdminToManualExpireService) Services.getService(WEB3AdminToManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseRunResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseRunResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseRunResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮���������N���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseRunResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮���������N���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm�����X�e�[�^�X)<BR>
     * �蓮�����̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �g���K�[�����Ǘ��ҁE�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�蓮���������X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToManualLapseStatusResponse
     * @@roseuid 440E895E02E6
     */
    public WEB3AdminToManualLapseStatusResponse confirmStatus(WEB3AdminToManualLapseStatusRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmStatus(WEB3AdminToManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseStatusResponse l_response = null;
        WEB3AdminToManualExpireService l_service = null;
        
        try
        {            
            //get�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X
            l_service = (WEB3AdminToManualExpireService) Services.getService(WEB3AdminToManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�g���K�[�����Ǘ��ҁE�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseStatusResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�g���K�[�����Ǘ���)�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
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
