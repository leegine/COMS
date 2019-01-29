head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������UL�n���h��(WEB3AdminAioVirtualAccCashinULHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 �юu�� (���u) �V�K�쐬     
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCancelResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULCompleteResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmResponse;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputRequest;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioVirtualAccCashinULService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o�[�`������������UL�n���h��)<BR>
 * �o�[�`������������UL�n���h��
 * 
 * @@author �юu��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioVirtualAccCashinULHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportInputHandler.class);
    
    /**
     * @@roseuid 44603B3301F4
     */
    public WEB3AdminAioVirtualAccCashinULHandler() 
    {
     
    }
    
    /**
     * (�A�b�v���[�h��ʕ\��)<BR>
     * �o�[�`�������������A�b�v���[�h��ʕ\���������s���B <BR>
     * <BR>
     * �o�[�`�����������౯��۰�޻��޽���擾���A<BR> 
     * execute()���\�b�h���R�[������<BR>
     * @@param l_request - (�o�[�`������������UL���N�G�X�g)<BR>
     * �o�[�`������������UL���̓��N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULInputResponse
     * @@roseuid 4455BC8E0008
     */
    public WEB3AdminAioVirtualAccCashinULInputResponse uploadScreenDisplay(
        WEB3AdminAioVirtualAccCashinULInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadScreenDisplay(WEB3AdminAioVirtualAccCashinULInputRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //�o�[�`�����������౯��۰�޻��޽���擾��
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULInputResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "�o�[�`�����������౯��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioVirtualAccCashinULInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o�[�`�������������A�b�v���[�h��ʕ\�����������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�A�b�v���[�h�t�@@�C���m�F)<BR>
     * �o�[�`�������������A�b�v���[�h�m�F�������s���B<BR>
     * <BR>
     * �o�[�`�������������A�b�v���[�h����۰�޻��޽���擾���A<BR> 
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�o�[�`������������UL�m�F���N�G�X�g)<BR>
     * �o�[�`������������UL�m�F���N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULConfirmResponse
     * @@roseuid 4455BD4E03E0
     */
    public WEB3AdminAioVirtualAccCashinULConfirmResponse uploadFileConfirm(
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadFileConfirm(WEB3AdminAioVirtualAccCashinULConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //�o�[�`�����������౯��۰�޻��޽���擾��
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULConfirmResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "�o�[�`�����������౯��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "�o�[�`�������������A�b�v���[�h�m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�o�[�`�������������A�b�v���[�h)<BR>
     * �o�[�`�������������A�b�v���[�h�����������s���B<BR> 
     * <BR>
     * �o�[�`�����������౯��۰�޻��޽���擾���A<BR> 
     * execute()���\�b�h���R�[������<BR>
     * @@param l_request - (�o�[�`�������������������N�G�X�g)<BR>
     * �o�[�`������������UL�������N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULCompleteResponse
     * @@roseuid 4455BEE80218
     */
    public WEB3AdminAioVirtualAccCashinULCompleteResponse virtualAccCashinUpload(
        WEB3AdminAioVirtualAccCashinULCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "virtualAccCashinUpload(WEB3AdminAioVirtualAccCashinULCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //�o�[�`�����������౯��۰�޻��޽���擾��
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULCompleteResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "�o�[�`�����������౯��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request, 
                "�o�[�`�������������A�b�v���[�h�������������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (�A�b�v���[���~)<BR>
     * �o�[�`�������������A�b�v���[�h���~�������s���B <BR>
     * <BR>
     * �o�[�`�����������౯��۰�޻��޽���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (�o�[�`������������UL���~���N�G�X�g)<BR>
     * �o�[�`������������UL���~���N�G�X�g
     * @@return WEB3AdminAioVirtualAccCashinULCancelResponse
     * @@roseuid 4455BF9A0313
     */
    public WEB3AdminAioVirtualAccCashinULCancelResponse uploadCancel(
        WEB3AdminAioVirtualAccCashinULCancelRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "uploadCancel(WEB3AdminAioVirtualAccCashinULCancelRequest l_request)";
        log.entering(STR_METHOD_NAME); 
        
        //�o�[�`�����������౯��۰�޻��޽���擾��
        WEB3AdminAioVirtualAccCashinULService l_service = null;
        WEB3AdminAioVirtualAccCashinULCancelResponse l_response = null;
        try
        {
            l_service = 
                (WEB3AdminAioVirtualAccCashinULService)
                    Services.getService(WEB3AdminAioVirtualAccCashinULService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;           
            log.error(
                l_request,
                "�o�[�`�����������౯��۰�޻��޽���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try 
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioVirtualAccCashinULCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioVirtualAccCashinULCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�o�[�`�������������A�b�v���[�h���~���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex); 
        }       
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
