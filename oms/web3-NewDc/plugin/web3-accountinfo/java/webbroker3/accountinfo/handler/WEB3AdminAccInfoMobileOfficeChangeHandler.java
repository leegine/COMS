head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�n���h��(WEB3AdminAccInfoMobileOfficeChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�n���h��)<BR>
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�n���h��<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeChangeHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeChangeHandler.class);

    
    /**
     * @@roseuid 418F3A100261
     */
    public WEB3AdminAccInfoMobileOfficeChangeHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �g�єԍ��E�Ζ�����ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistInputResponse
     * @@roseuid 4148FCE4010D
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistInputResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ύX�m�F)<BR>
     * �g�єԍ��E�Ζ�����ύX�m�F�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistConfirmResponse
     * @@roseuid 4148FCE4011D
     */
    public WEB3AdminAccInfoMobileOfficeRegistConfirmResponse changeConfirm(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeConfirm(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistConfirmResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ύX����)<BR>
     * �g�єԍ��E�Ζ�����ύX�����������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistCompleteResponse
     * @@roseuid 4148FCE4013C
     */
    public WEB3AdminAccInfoMobileOfficeRegistCompleteResponse changeComplete(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeComplete(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistCompleteResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeChangeService l_service = null;
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeChangeService)Services.getService(WEB3AdminAccInfoMobileOfficeChangeService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
