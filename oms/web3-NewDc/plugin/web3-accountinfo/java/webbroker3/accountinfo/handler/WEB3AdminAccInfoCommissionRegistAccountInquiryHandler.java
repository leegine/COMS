head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�������)(WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�������)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�������<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountInquiryHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class);
    
    /**
     * @@roseuid 418F3A1201A5
     */
    public WEB3AdminAccInfoCommissionRegistAccountInquiryHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �萔���ύX�\���ڋq�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇������ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B61AD03E3
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountInquiryService l_service = null;
        
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�ύX�\���ꗗ�\��)<BR>
     * �萔���ύX�\���ڋq�⍇���\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse
     * @@roseuid 4151417D001C
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryResponse registListDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registListDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountInquiryService l_service = null;
        
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�⍇�����޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
