head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���n���h��(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
                   2006/12/15 ����� (���u) �d�l�ύX���f��155
                   2006/12/18 ����� (���u) �d�l�ύX���f��157
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���n���h��)
 * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���n���h��
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class);

    
    /**
     * @@roseuid 418F3A100109
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5F2E0369
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�ύX�\���ڋq�ꗗ�\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�ꗗ�\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountResponse registAccountListDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountRequest l_request) 
    {
        final String STR_METHOD_NAME = "registAccountListDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }

    /**
     * (�ꊇ����m�F��ʕ\��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�ꊇ����m�F��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse judgementConfirmScreenDisplay(
        WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "judgementConfirmScreenDisplay(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;

        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(
                WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�ꊇ���芮��)<BR>
     * �g�єԍ��E�Ζ�����ύX�\���ڋq�ꊇ���芮����ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ꊇ���芮�����N�G�X�g
     * @@return WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse judgementComplete(
        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "judgementComplete(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;

        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(
                WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response;
        }

        // �Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X���擾���Aexecute()���\�b�h���R�[������
        try
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���g�єԍ��E�Ζ�����ύX�\���ڋq�⍇���T�[�r�X�Ɏ��s���܂����B",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
