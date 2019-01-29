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
filename	WEB3AdminFeqOrderAndExecutionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�����������̓n���h��(WEB3AdminFeqOrderAndExecutionHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ��O��(���u) ���r���[   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO�����������̓n���h��)<BR>
 * �Ǘ��ҊO�����������̓n���h��<BR>
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionHandler.class);        
    
    /**
     * @@roseuid 42D0DA18032C
     */
    public WEB3AdminFeqOrderAndExecutionHandler() 
    {
     
    }
    
    /**
     * (get�������)<BR>
     * ������ʕ\�������B<BR>
     * <BR>
     * �Ǘ��ҊO�����������̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse
     * @@roseuid 42B914BF02DA
     */
    public WEB3AdminFeqOrderAndExecutionSearchResponse getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAndExecutionSearchResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get�Ǘ��ҊO�����������̓T�[�r�X
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�����������̓T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse)l_request.createResponse();
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
                "get������ʂ����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse) l_request.createResponse();
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
                "get������ʂ����{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �Ǘ��ҊO�����������̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse
     * @@roseuid 429FFB2B0252
     */
    public WEB3AdminFeqOrderAndExecutionInputResponse getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAndExecutionInputResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get�Ǘ��ҊO�����������̓T�[�r�X
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�����������̓T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
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
                "get���͉�ʂ����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse) l_request.createResponse();
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
                "get���͉�ʂ����{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate���)<BR>
     * �����͊m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҊO�����������̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse
     * @@roseuid 429FFB2B0261
     */
    public WEB3AdminFeqOrderAndExecutionConfirmResponse validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAndExecutionConfirmResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get�Ǘ��ҊO�����������̓T�[�r�X
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�����������̓T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_request.createResponse();
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
                "validate�������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse) l_request.createResponse();
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
                "validate�������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit���)<BR>
     * �����͊����������s���B<BR>
     * <BR>
     * �Ǘ��ҊO�����������̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse
     * @@roseuid 429FFB2B0271
     */
    public WEB3AdminFeqOrderAndExecutionCompleteResponse submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAndExecutionCompleteResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get�Ǘ��ҊO�����������̓T�[�r�X
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�����������̓T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse)l_request.createResponse();
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
                "submit�������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse) l_request.createResponse();
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
                "submit�������{�Ɏ��s���܂����B",
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
