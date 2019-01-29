head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfoUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������X�V�n���h��(WEB3AdminFeqProductInfoUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 ��O��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������������X�V�n���h��)<BR>
 * �O�������������X�V�n���h���N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfoUpdateHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfoUpdateHandler.class);
    
    /**
     * @@roseuid 42D0DA180119
     */
    public WEB3AdminFeqProductInfoUpdateHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �O�������������X�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse
     * @@roseuid 42BF94240055
     */
    public WEB3AdminFeqProductInformationUpdateInputResponse getInputScreen(
        WEB3AdminFeqProductInformationUpdateInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqProductInformationUpdateInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqProductInformationUpdateInputResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get�O�������������X�V�T�[�r�X
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O�������������X�V�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse)
                l_request.createResponse();
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
                "get���͉�ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse) 
                l_request.createResponse();
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
                "get���͉�ʂɎ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���ד��͉��)<BR>
     * �O�������������X�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse
     * @@roseuid 421AB50E03C2
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse getDetailInputScreen(
        WEB3AdminFeqProductInformationUpdateDetailsInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getDetailInputScreen(WEB3AdminFeqProductInformationUpdateDetailsInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqProductInformationUpdateDetailsInputResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get�O�������������X�V�T�[�r�X
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O�������������X�V�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)
                l_request.createResponse();
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
                "get���ד��͉�ʂɎ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse) 
                l_request.createResponse();
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
                "get���ד��͉�ʂɎ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate�X�V)<BR>
     * �O�������������X�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse
     * @@roseuid 421AB52902A9
     */
    public WEB3AdminFeqProductInformationUpdateConfirmResponse validateUpdate(
        WEB3AdminFeqProductInformationUpdateConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqProductInformationUpdateConfirmResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get�O�������������X�V�T�[�r�X
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O�������������X�V�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse)
                l_request.createResponse();
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
                "validate�X�V�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse) 
                l_request.createResponse();
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
                "validate�X�V�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�X�V)<BR>
     * �O�������������X�V�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse
     * @@roseuid 421AB5510103
     */
    public WEB3AdminFeqProductInformationUpdateCompleteResponse submitUpdate(
        WEB3AdminFeqProductInformationUpdateCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " submitUpdate(WEB3AdminFeqProductInformationUpdateCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqProductInformationUpdateCompleteResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get�O�������������X�V�T�[�r�X
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�O�������������X�V�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse)
                l_request.createResponse();
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
                "submit�X�V�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse) 
                l_request.createResponse();
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
                "submit�X�V�Ɏ��s���܂����B",
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
