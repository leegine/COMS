head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���n���h��(WEB3AccInfoEquityCommissionCourseRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���n���h��)<BR>
 * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���n���h��<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AccInfoEquityCommissionCourseRegistHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseRegistHandler.class);
    
    /**
     * @@roseuid 418F3A0C000F
     */
    public WEB3AccInfoEquityCommissionCourseRegistHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �����ϑ��萔���R�[�X�ύX�\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\�����̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeInputResponse
     * @@roseuid 413D5CB500D4
     */
    public WEB3AccInfoEquityCommissionCourseChangeInputResponse inputScreenDisplay(WEB3AccInfoEquityCommissionCourseChangeInputRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "inputScreenDisplay(WEB3AccInfoEquityCommissionCourseChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeInputResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�ύX�\���m�F)<BR>
     * �����ϑ��萔���R�[�X�ύX�\���m�F�������s���B <BR>
     * <BR>
     * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeConfirmResponse
     * @@roseuid 413D5CB500E4
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse registConfirm(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registConfirm(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeConfirmResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�ύX�\������)<BR>
     * �����ϑ��萔���R�[�X�ύX�\�������������s���B <BR>
     * <BR>
     * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCompleteResponse
     * @@roseuid 413D5CB50103
     */
    public WEB3AccInfoEquityCommissionCourseChangeCompleteResponse registComplete(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registComplete(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCompleteResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�\������m�F) <BR>
     * �����ϑ��萔���R�[�X�ύX�\������m�F�������s���B <BR>
     *  <BR>
     * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B  <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������m�F���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse
     * @@roseuid 413D5CB50113
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse registCancelConfirm(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registCancelConfirm(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�\���������)<BR>
     * �����ϑ��萔���R�[�X�ύX�\����������������s���B <BR>
     * <BR>
     * ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\������������N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse
     * @@roseuid 413D5CB50122
     */
    public WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse registConcelComplete(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registConcelComplete(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse l_response = null;
        WEB3AccInfoEquityCommissionCourseRegistService l_service = null;
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾
        try
        {  
                      
            l_service=(WEB3AccInfoEquityCommissionCourseRegistService)Services.getService
                (WEB3AccInfoEquityCommissionCourseRegistService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // ���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���q�l��񊔎��ϑ��萔���R�[�X�ύX�\���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
