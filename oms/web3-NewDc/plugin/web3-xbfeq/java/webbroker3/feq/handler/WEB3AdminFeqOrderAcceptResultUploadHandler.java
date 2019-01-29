head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptResultUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t���ʱ���۰�ރn���h��(WEB3AdminFeqOrderAcceptResultUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptResultUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO������������t���ʱ���۰�ރn���h��)<BR>
 * �Ǘ��ҊO������������t���ʱ���۰�ރn���h��<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptResultUploadHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptResultUploadHandler.class);
    
    /**
     * @@roseuid 42D0DA1700CB
     */
    public WEB3AdminFeqOrderAcceptResultUploadHandler() 
    {
     
    }
    
    /**
     * (get�A�b�v���[�h���)<BR>
     * �A�b�v���[�h��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h���̓��N�G�X�g���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadInputResponse
     * @@roseuid 429FD75B0167
     */
    public WEB3AdminFeqOrderAcceptResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqOrderAcceptResultUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFeqOrderAcceptResultUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptResultUploadInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptResultUploadService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptResultUploadService)
                Services.getService(WEB3AdminFeqOrderAcceptResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptResultUploadInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "get�A�b�v���[�h��ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "get�A�b�v���[�h��ʂɎ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�A�b�v���[�h)<BR>
     * �A�b�v���[�h�t�@@�C���m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * ���G���[���X�|���X�ɁiWEB3BaseException.errorMessage�j���Z�b�g���邱�ƁB<BR>
     *    l_response.errorInfo = l_ex.getErrorInfo();<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h�m�F���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse
     * @@roseuid 429FD75B0177
     */
    public WEB3AdminFeqOrderAcceptResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqOrderAcceptResultUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateUpload(WEB3AdminFeqOrderAcceptResultUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptResultUploadConfirmResponse l_response = null;
        WEB3AdminFeqOrderAcceptResultUploadService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptResultUploadService)
                Services.getService(WEB3AdminFeqOrderAcceptResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptResultUploadConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request, 
                "validate�A�b�v���[�h�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadConfirmResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "validate�A�b�v���[�h�Ɏ��s���܂����B",
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
     * �A�b�v���[�h�����������s���B<BR>
     * <BR>
     * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h�������N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadCompleteResponse
     * @@roseuid 429FD75B0187
     */
    public WEB3AdminFeqOrderAcceptResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqOrderAcceptResultUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptResultUploadCompleteResponse l_response = null;
        WEB3AdminFeqOrderAcceptResultUploadService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptResultUploadService)
                Services.getService(WEB3AdminFeqOrderAcceptResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptResultUploadCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "submit�A�b�v���[�h�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadCompleteResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "submit�A�b�v���[�h�Ɏ��s���܂����B",
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
     * �A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO������������t���ʃA�b�v���[�h���~���N�G�X�g�f�[�^�I�u�W�F�N�g<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadStopResponse
     * @@roseuid 429FD75B0196
     */
    public WEB3AdminFeqOrderAcceptResultUploadStopResponse undoUpload(
        WEB3AdminFeqOrderAcceptResultUploadStopRequest l_request) 
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminFeqOrderAcceptResultUploadStopRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptResultUploadStopResponse l_response = null;
        WEB3AdminFeqOrderAcceptResultUploadService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptResultUploadService)
                Services.getService(WEB3AdminFeqOrderAcceptResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t���ʱ���۰�ރT�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptResultUploadStopResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadStopResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "undo�A�b�v���[�h�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptResultUploadStopResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "undo�A�b�v���[�h�Ɏ��s���܂����B",
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
