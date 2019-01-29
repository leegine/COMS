head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPackageAdjustHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�ꊇ�����n���h��(WEB3AdminPointPackageAdjustHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointUploadCancelRequest;
import webbroker3.point.message.WEB3AdminPointUploadCancelResponse;
import webbroker3.point.message.WEB3AdminPointUploadCompleteRequest;
import webbroker3.point.message.WEB3AdminPointUploadCompleteResponse;
import webbroker3.point.message.WEB3AdminPointUploadConfirmRequest;
import webbroker3.point.message.WEB3AdminPointUploadConfirmResponse;
import webbroker3.point.message.WEB3AdminPointUploadInputRequest;
import webbroker3.point.message.WEB3AdminPointUploadInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPackageAdjustService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�|�C���g�ꊇ�����n���h��)<BR>
 * �|�C���g�ꊇ�����n���h���N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointPackageAdjustHandler implements MessageHandler 
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminPointPackageAdjustHandler.class);
 
    /**
     * (���͉�ʕ\��)<BR>
     * ���͉�ʂ̕\�����s���B<BR>
     * <BR>
     * �|�C���g�ꊇ�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadInputResponse
     * @@roseuid 4198445800A8
     */
    public WEB3AdminPointUploadInputResponse inputScreenDisplay(WEB3AdminPointUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminPointUploadInputRequest )";    
        log.entering(STR_METHOD_NAME);        
        
        WEB3AdminPointUploadInputResponse l_response = null;
        WEB3AdminPointPackageAdjustService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminPointPackageAdjustService)Services.getService(
                    WEB3AdminPointPackageAdjustService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response = (WEB3AdminPointUploadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�|�C���g�ꊇ�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "���͉�ʕ\���Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�A�b�v���[�h�m�F)<BR>
     * �A�b�v���[�h�f�[�^�̐R�����s���B<BR>
     * <BR>
     * �|�C���g�ꊇ�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadConfirmResponse
     * @@roseuid 4198445800B7
     */
    public WEB3AdminPointUploadConfirmResponse uploadConfirm(WEB3AdminPointUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " uploadConfirm(WEB3AdminPointUploadConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointUploadConfirmResponse l_response = null;
        WEB3AdminPointPackageAdjustService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminPointPackageAdjustService)Services.getService(
                    WEB3AdminPointPackageAdjustService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�|�C���g�ꊇ�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A�b�v���[�h�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�A�b�v���[�h����)<BR>
     * �A�b�v���[�h�f�[�^�̓o�^���s���B<BR>
     * <BR>
     * �|�C���g�ꊇ�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCompleteResponse
     * @@roseuid 4198445800D7
     */
    public WEB3AdminPointUploadCompleteResponse uploadComplete(WEB3AdminPointUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " uploadComplete(WEB3AdminPointUploadCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointUploadCompleteResponse l_response = null;
        WEB3AdminPointPackageAdjustService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminPointPackageAdjustService)Services.getService(
                    WEB3AdminPointPackageAdjustService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response = (WEB3AdminPointUploadCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�|�C���g�ꊇ�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A�b�v���[�h�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (�A�b�v���[�h���~)<BR>
     * �A�b�v���[�h�̒��~���s���B<BR>
     * <BR>
     * �|�C���g�ꊇ�����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointUploadCancelResponse
     * @@roseuid 419844580088
     */
    public WEB3AdminPointUploadCancelResponse uploadStop(WEB3AdminPointUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " uploadStop(WEB3AdminPointUploadCancelRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointUploadCancelResponse l_response = null;
        WEB3AdminPointPackageAdjustService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminPointPackageAdjustService)Services.getService(
                    WEB3AdminPointPackageAdjustService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadCancelResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�|�C���g�ꊇ�����T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadCancelResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointUploadCancelResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�A�b�v���[�h���~�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
