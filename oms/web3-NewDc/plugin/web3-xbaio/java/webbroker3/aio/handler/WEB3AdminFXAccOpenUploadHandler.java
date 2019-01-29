head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccOpenUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���FX�����J�݃A�b�v���[�h�n���h��(WEB3AdminFXAccOpenUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 �A����(���u) �V�K�쐬
                   2006/02/28 ���(SRA) ����e�X�g��QU02769�Ή�
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCancelResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccOpenUploadInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ���FX�����J�݃A�b�v���[�h�n���h��)<BR>
 * �Ǘ���FX�����J�݃A�b�v���[�h�n���h���N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXAccOpenUploadHandler implements MessageHandler 
{
   
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminFXAccOpenUploadHandler.class);
    
    /**
     * @@roseuid 43F49A6D0138
     */
    public WEB3AdminFXAccOpenUploadHandler() 
    {
     
    }
    
    /**
     * (get�A�b�v���[�h���)<BR>
     * �A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadInputResponse
     * @@roseuid 43E041A90210
     */
    public WEB3AdminFXAccOpenUploadInputResponse getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest l_request)
        
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFXAccOpenUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadInputResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h��ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h��ʕ\�������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C���m�F�������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadConfirmResponse
     * @@roseuid 43E041C403A7
     */
    public WEB3AdminFXAccOpenUploadConfirmResponse validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateUploadFile(WEB3AdminFXAccOpenUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadConfirmResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h�t�@@�C���m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h�t�@@�C���m�F�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�A�b�v���[�h)<BR>
     * �A�b�v���[�h�����������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadCompleteResponse
     * @@roseuid 43E041D100E8
     */
    public WEB3AdminFXAccOpenUploadCompleteResponse submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadCompleteResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B", 
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h���������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h���������̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undo�A�b�v���[�h)<BR>
     * �A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �Ǘ���FX�����J�݃A�b�v���[�hރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminFXAccOpenUploadCancelResponse
     * @@roseuid 43E041E3027E
     */
    public WEB3AdminFXAccOpenUploadCancelResponse undoUpload(WEB3AdminFXAccOpenUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFXAccOpenUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminFXAccOpenUploadCancelResponse l_response = null;
        WEB3AdminFXAccOpenUploadService l_service = null;
        
        try
        {            
            //get�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X
            l_service = (WEB3AdminFXAccOpenUploadService) Services.getService(WEB3AdminFXAccOpenUploadService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "�Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h���~�����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminFXAccOpenUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(�Ǘ���FX�����J�݃A�b�v���[�h)�A�b�v���[�h���~�����̎��{�Ɏ��s���܂����B",
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
