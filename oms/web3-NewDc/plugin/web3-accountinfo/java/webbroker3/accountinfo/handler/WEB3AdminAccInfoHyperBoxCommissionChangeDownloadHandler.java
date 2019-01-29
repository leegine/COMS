head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�������(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �d��(���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�������)<BR>
 * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�������<BR>
 * @@author �d��
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class);
    
    /**
     * @@roseuid 418F3A0C0213
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�ޖ⍇��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 41665BD10049
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse inputScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " inputScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�_�E�����[�h��ʕ\��)<BR>
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް��޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A6AE0050
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " downloadScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (�n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h)<BR>
     * �n�C�p�[�{�b�N�X�萔���ύX�_�E�����[�h�t�@@�C���f�[�^�擾�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�ް�̧���޳�۰��ظ��ăf�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A6AE0070
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse hyperBoxCommissionChangeDownload(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " hyperBoxCommissionChangeDownload(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // �Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l���ʲ�߰�ޯ���萔���ύX�޳�۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
