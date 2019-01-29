head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�������(WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountListInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�������)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�������<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.class);
    
    /**
     * @@roseuid 418F3A12030D
     */
    public WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �萔���ύX�\���ڋq�ꗗ�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇������ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse
     * @@roseuid 41510F3403A7
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountListInquiryService l_service = null;
        
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountListInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�萔���ύX�\���ڋq�ꗗ�\��)<BR>
     * �萔���ύX�\���ڋq�ꗗ�\���������s���B<BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse
     * @@roseuid 41510F3403B6
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse commissionRegistAccountListDisplay(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "commissionRegistAccountListDisplay(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountListInquiryService l_service = null;
        
        //�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountListInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�ꗗ�⍇�����޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
