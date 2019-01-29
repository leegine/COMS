head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMobileOfficeRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���g�єԍ��E�Ζ�����ύX�\���n���h��(WEB3AccInfoMobileOfficeRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���g�єԍ��E�Ζ�����ύX�\���n���h��)<BR>
 * ���q�l���g�єԍ��E�Ζ�����ύX�\���n���h��<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AccInfoMobileOfficeRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMobileOfficeRegistHandler.class);

    
    /**
     * @@roseuid 418F3A12000F
     */
    public WEB3AccInfoMobileOfficeRegistHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMobileOfficeRegistInputResponse
     * @@roseuid 413FEF72036E
     */
    public WEB3AccInfoMobileOfficeRegistInputResponse inputScreenDisplay(WEB3AccInfoMobileOfficeRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistInputResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ύX�\���m�F)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���m�F�������s���B <BR>
     * <BR>
     * ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistConfirmResponse
     * @@roseuid 413FEF72038D
     */
    public WEB3AccInfoMobileOfficeRegistConfirmResponse registConfirm(WEB3AccInfoMobileOfficeRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "registConfirm(WEB3AccinfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistConfirmResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ύX�\������)<BR>
     * �g�єԍ��E�Ζ�����ύX�\�������������s���B <BR>
     * <BR>
     * ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l���g�єԍ��E�Ζ�����ύX�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccinfoMobileOfficeRegistCompleteResponse
     * @@roseuid 413FEF72039D
     */
    public WEB3AccInfoMobileOfficeRegistCompleteResponse registComplete(WEB3AccInfoMobileOfficeRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "registComplete(WEB3AccinfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoMobileOfficeRegistCompleteResponse l_response = null;
        WEB3AccInfoMobileOfficeRegistService l_service = null;
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AccInfoMobileOfficeRegistService)Services.getService(WEB3AccInfoMobileOfficeRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l���g�єԍ��E�Ζ�����ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
