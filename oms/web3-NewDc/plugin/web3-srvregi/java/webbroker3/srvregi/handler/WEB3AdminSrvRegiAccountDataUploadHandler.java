head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�n���h��(WEB3AdminSrvRegiAccountDataUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�n���h��)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�n���h���@@�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataUploadHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountDataUploadHandler.class);
    
    /**
     * @@roseuid 416F415C00AB
     */
    public WEB3AdminSrvRegiAccountDataUploadHandler() 
    {
     
    }
    
    /**
     * (�ڋq�A�b�v���[�h��ʕ\��)<BR>
     * �ڋq�f�[�^�A�b�v���[�h��ʏ������s���B<BR>
     * <BR>
     * �ڋq�f�[�^�A�b�v���[�h��ʃT�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���̓��N�G�X�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse
     * @@roseuid 412071840182
     */
    public WEB3AdminSrvRegiUploadInputResponse accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadScreenIndication(WEB3AdminSrvRegiUploadInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadInputResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ڋq�A�b�v���[�h��ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�ڋq�A�b�v���[�h���~)<BR>
     * �ڋq�f�[�^�A�b�v���[�h���~�������s���B<BR>
     * <BR>
     * �ڋq�f�[�^�A�b�v���[�h���~�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���~���N�G�X�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse
     * @@roseuid 412072CF0163
     */
    public WEB3AdminSrvRegiUploadCancelResponse accountUploadDiscontinuation(WEB3AdminSrvRegiUploadCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadDiscontinuation(WEB3AdminSrvRegiUploadCancelRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCancelResponse l_response = null;        
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;

        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ڋq�A�b�v���[�h���~�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�ڋq�A�b�v���[�h�t�@@�C���m�F)<BR>
     * �ڋq�f�[�^�A�b�v���[�h�t�@@�C���m�F�������s���B<BR>
     * <BR>
     * �ڋq�f�[�^�A�b�v���[�h�t�@@�C���m�F�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�t�@@�C���m�F���N�G�X�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse
     * @@roseuid 4120732101A2
     */
    public WEB3AdminSrvRegiUploadConfirmResponse accountUploadConfirm(WEB3AdminSrvRegiUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUploadConfirm(WEB3AdminSrvRegiUploadConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadConfirmResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;       
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ڋq�A�b�v���[�h�t�@@�C���m�F�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�ڋq�A�b�v���[�h)<BR>
     * �ڋq�f�[�^�A�b�v���[�h�������s���B<BR>
     * <BR>
     * �ڋq�f�[�^�A�b�v���[�h�T�[�r�X���擾���Aexecute( )���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h���N�G�X�g<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse
     * @@roseuid 412073790338
     */
    public WEB3AdminSrvRegiUploadCompleteResponse accountUpload(WEB3AdminSrvRegiUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountUpload(WEB3AdminSrvRegiUploadCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiUploadCompleteResponse l_response = null;
        WEB3AdminSrvRegiAccountDataUploadService l_service = null;       
        try
        {
            //get service
            l_service = 
                (WEB3AdminSrvRegiAccountDataUploadService)Services.getService(
                    WEB3AdminSrvRegiAccountDataUploadService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�T�[�r�X���p�Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�ڋq�A�b�v���[�h�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
