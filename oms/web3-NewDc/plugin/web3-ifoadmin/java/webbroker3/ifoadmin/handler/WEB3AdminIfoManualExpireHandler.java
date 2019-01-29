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
filename	WEB3AdminIfoManualExpireHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�蓮�����n���h��(WEB3AdminIfoManualExpireHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
*/

package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�敨OP�蓮�����n���h��)<BR>
 * �Ǘ��ҁE�敨OP�蓮�����n���h���N���X<BR>
 * <BR>
 * @@author �Ӑ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireHandler.class);
    
    /**
     * @@roseuid 447AC0CF01F4
     */
    public WEB3AdminIfoManualExpireHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �敨OP�蓮�������͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮�������̓��N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse
     * @@roseuid 44692FC10116
     */
    public WEB3AdminIfoManualLapseInputResponse getInputScreen(WEB3AdminIfoManualLapseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminIfoManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseInputResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾��
            l_service = (WEB3AdminIfoManualExpireService)Services.getService(WEB3AdminIfoManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�������͉�ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
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
     * �敨OP�蓮�����m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮�����m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse
     * @@roseuid 446930230264
     */
    public WEB3AdminIfoManualLapseConfirmResponse confirmManualExpire(WEB3AdminIfoManualLapseConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManualExpire(WEB3AdminIfoManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseConfirmResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾��
            l_service = (WEB3AdminIfoManualExpireService)Services.getService(WEB3AdminIfoManualExpireService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
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
     * �敨OP�蓮�����N�����s���B<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮���������N�����N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse
     * @@roseuid 4469305701C2
     */
    public WEB3AdminIfoManualLapseRunResponse runManualExpire(WEB3AdminIfoManualLapseRunRequest l_request) 
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminIfoManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseRunResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾��
            l_service = 
                (WEB3AdminIfoManualExpireService)Services.getService(
                    WEB3AdminIfoManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҁE�敨OP�蓮�����N���̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
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
     * �敨OP�蓮�����̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�敨OP�蓮���������X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse
     * @@roseuid 446930AA03B9
     */
    public WEB3AdminIfoManualLapseStatusResponse confirmStatus(
        WEB3AdminIfoManualLapseStatusRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmStatus(WEB3AdminIfoManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseStatusResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //�Ǘ��ҁE�敨OP�蓮�����T�[�r�XImpl���擾��
            l_service = 
                (WEB3AdminIfoManualExpireService)Services.getService(
                    WEB3AdminIfoManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�敨OP�蓮�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�敨OP�蓮�����̏����X�e�[�^�X�̎��{�Ɏ��s���܂����B",
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
