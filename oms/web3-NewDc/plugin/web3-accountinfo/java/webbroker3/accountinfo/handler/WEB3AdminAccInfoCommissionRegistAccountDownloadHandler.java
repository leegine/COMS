head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�������(WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�������)<BR>
 * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�������<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class);
    
    /**
     * @@roseuid 418F3A0F033C
     */
    public WEB3AdminAccInfoCommissionRegistAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h�⍇�����͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�ޖ⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5E4701E3
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAC830192
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�萔���ύX�\���ڋq�_�E�����[�h)<BR>
     * �萔���ύX�\���ڋq�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���萔���ύX�\���ڋq̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAC830194
     */
    public WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse commissionRegistAccountDownload(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "commissionRegistAccountDownload(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���萔���ύX�\���ڋq�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
