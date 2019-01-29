head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l����p�U�����������۰�������(WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �J�N���V (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l����p�U�����������۰�������)<BR>
 * �Ǘ��҂��q�l����p�U�����������۰�������<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class);

    
    /**
     * @@roseuid 418F3A130222
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadHandler() 
    {
     
    }
    
    /**
     * (�A�b�v���[�h��ʕ\��)<BR>
     * ��p�U��������A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ޓ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse
     * @@roseuid 415BC4640005
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse uploadScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�A�b�v���[�h�t�@@�C���m�F)<BR>
     * ��p�U��������A�b�v���[�h�m�F�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ފm�Fظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse
     * @@roseuid 415BC4640024
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse uploadFileConfirm(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadFileConfirm(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (��p�U��������A�b�v���[�h)<BR>
     * ��p�U��������A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ފ���ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse
     * @@roseuid 415BC4640034
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse exclusiveTransferAccountUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "exclusiveTransferAccountUpload(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (�A�b�v���[�h���~)<BR>
     * ��p�U��������A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l����p�U�����������۰�ޒ��~ظ��ăf�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse
     * @@roseuid 415BC4640072
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse uploadCancel(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = "uploadCancel(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountUploadService l_service = null;
        
        // �Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountUploadService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountUploadService.class);            
            
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //�Ǘ��҂��q�l����p�U�����������۰�޻��޽���擾���Aexecute()���\�b�h���R�[������ 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��҂��q�l����p�U�����������۰�޻��޽�Ɏ��s���܂����B",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
