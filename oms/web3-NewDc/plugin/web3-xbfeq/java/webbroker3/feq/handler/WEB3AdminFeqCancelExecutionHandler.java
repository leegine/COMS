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
filename	WEB3AdminFeqCancelExecutionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o��������n���h��(WEB3AdminFeqCancelExecutionHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �A�C��(���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqCancelExecutionService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO�������o��������n���h��)<BR>
 * �Ǘ��ҊO�������o��������n���h��<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCancelExecutionHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqCancelExecutionHandler.class);
    
    /**
     * @@roseuid 42D0DA160399
     */
    public WEB3AdminFeqCancelExecutionHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��ҊO�������o��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionInputResponse
     * @@roseuid 42942E530008
     */
    public WEB3AdminFeqCancelExecutionInputResponse getInputScreen(
        WEB3AdminFeqCancelExecutionInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqCancelExecutionInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqCancelExecutionInputResponse l_response = null;
        WEB3AdminFeqCancelExecutionService l_service = null;

        try
        {            
            //get�Ǘ��ҊO�������o��������T�[�r�X
            l_service = (WEB3AdminFeqCancelExecutionService)
                Services.getService(WEB3AdminFeqCancelExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������o��������T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqCancelExecutionInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionInputResponse)l_request.createResponse();
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
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionInputResponse) l_request.createResponse();
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
                "���͉�ʕ\�����������{�Ɏ��s���܂����B",
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
     * �o��������m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҊO�������o��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionConfirmResponse
     * @@roseuid 42942E53000A
     */
    public WEB3AdminFeqCancelExecutionConfirmResponse validateCancel(
        WEB3AdminFeqCancelExecutionConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateCancel(WEB3AdminFeqCancelExecutionConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqCancelExecutionConfirmResponse l_response = null;
        WEB3AdminFeqCancelExecutionService l_service = null;

        try
        {
            //get�Ǘ��ҊO�������o��������T�[�r�X
            l_service = (WEB3AdminFeqCancelExecutionService)
                Services.getService(WEB3AdminFeqCancelExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������o��������T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqCancelExecutionConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionConfirmResponse)l_request.createResponse();
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
                "�o��������m�F�������s���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionConfirmResponse) l_request.createResponse();
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
                "�o��������m�F�������s���Ɏ��s���܂����B",
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
     * �o������������������s���B<BR>
     * <BR>
     * �Ǘ��ҊO�������o��������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCancelExecutionCompleteResponse
     * @@roseuid 42942E53000C
     */
    public WEB3AdminFeqCancelExecutionCompleteResponse submitCancel(
        WEB3AdminFeqCancelExecutionCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminFeqCancelExecutionCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqCancelExecutionCompleteResponse l_response = null;
        WEB3AdminFeqCancelExecutionService l_service = null;

        try
        {
            //get�Ǘ��ҊO�������o��������T�[�r�X
            l_service = (WEB3AdminFeqCancelExecutionService)
                Services.getService(WEB3AdminFeqCancelExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO�������o��������T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqCancelExecutionCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionCompleteResponse)l_request.createResponse();
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
                "�o������������������s���Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCancelExecutionCompleteResponse) l_request.createResponse();
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
                "�o������������������s���Ɏ��s���܂����B",
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
