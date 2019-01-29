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
filename	WEB3AdminFeqUploadErrCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO��������������۰�޴װ�����n���h��(WEB3AdminFeqUploadErrCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �s�p (���u) �V�K�쐬
                 : 2005/08/01 ��O��(���u) ���r���[   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO��������������۰�޴װ�����n���h��)<BR>
 * �Ǘ��ҊO��������������۰�޴װ�����n���h��<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelHandler.class);
        
    /**
     * @@roseuid 42D0DA17033C
     */
    public WEB3AdminFeqUploadErrCancelHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��ҊO��������������۰�޴װ�����T�[�r�X.execute()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�������̓��N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelInputResponse
     * @@roseuid 42BBB5B7027F
     */
    public WEB3AdminFeqUploadErrCancelInputResponse getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqUploadErrCancelInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqUploadErrCancelInputResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqUploadErrCancelInputResponse) l_request.createResponse();
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
     * (validate����)<BR>
     * �m�F��ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��ҊO��������������۰�޴װ�����T�[�r�X.execute()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�����m�F���N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelConfirmResponse
     * @@roseuid 42BBB5BD0147
     */
    public WEB3AdminFeqUploadErrCancelConfirmResponse validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateRelease(WEB3AdminFeqUploadErrCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqUploadErrCancelConfirmResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse)l_request.createResponse();
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
                "validate���������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelConfirmResponse) l_request.createResponse();
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
                "validate���������{�Ɏ��s���܂����B",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit����)<BR>
     * ��������۰�މ������������{����B<BR>
     * <BR>
     * �Ǘ��ҊO��������������۰�޴װ�����T�[�r�X.execute()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҊO��������������۰�޴װ�����������N�G�X�g<BR>
     * @@return webbroker3.feq.message.WEB3AdminFeqUploadErrCancelCompleteResponse
     * @@roseuid 42BBB5C2000E
     */
    public WEB3AdminFeqUploadErrCancelCompleteResponse submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitRelease(WEB3AdminFeqUploadErrCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqUploadErrCancelCompleteResponse l_response = null;
        WEB3AdminFeqUploadErrCancelService l_service = null;

        try
        {            
            //get�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X
            l_service = (WEB3AdminFeqUploadErrCancelService)
                Services.getService(WEB3AdminFeqUploadErrCancelService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO��������������۰�޴װ�����T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse)l_request.createResponse();
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
                "submit���������{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqUploadErrCancelCompleteResponse) l_request.createResponse();
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
                "submit���������{�Ɏ��s���܂����B",
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
