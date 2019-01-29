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
filename	WEB3AdminFeqOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�؃n���h��(WEB3AdminFeqOrderAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO������������t����F�؃n���h��)<BR>
 * �Ǘ��ҊO������������t����F�؃n���h��<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptHandler.class);
        
    /**
     * @@roseuid 42D0DA170196
     */
    public WEB3AdminFeqOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (get�������)<BR>
     * ������ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �Ǘ��ҊO������������t����F�؃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptSearchCondInputResponse
     * @@roseuid 429FECBF005E
     */
    public WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse getQueryScreen(WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqOrderAcceptSearchCondInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t����F�؃T�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptService)
                Services.getService(WEB3AdminFeqOrderAcceptService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t����F�؃T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse) l_request.createResponse();
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
     * �Ǘ��ҊO������������t����F�؃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptInputResponse
     * @@roseuid 429FECBF006D
     */
    public WEB3AdminFeqOrderAcceptCancelInputResponse getInputScreen(WEB3AdminFeqOrderAcceptCancelInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAcceptInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptCancelInputResponse l_response = null;
        WEB3AdminFeqOrderAcceptService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t����F�؃T�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptService)
                Services.getService(WEB3AdminFeqOrderAcceptService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t����F�؃T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptCancelInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptCancelInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqOrderAcceptCancelInputResponse) l_request.createResponse();
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
     * (submit��t)<BR>
     * �����i��������j��t���͊����������s���B<BR>
     * <BR>
     * �Ǘ��ҊO������������t����F�؃T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptCompleteResponse
     * @@roseuid 429FECBF008D
     */
    public WEB3AdminFeqOrderAcceptCancelCompleteResponse submitAccept(WEB3AdminFeqOrderAcceptCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitAccept(WEB3AdminFeqOrderAcceptCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqOrderAcceptCancelCompleteResponse l_response = null;
        WEB3AdminFeqOrderAcceptService l_service = null;

        try
        {            
            //get�Ǘ��ҊO������������t����F�؃T�[�r�X
            l_service = (WEB3AdminFeqOrderAcceptService)
                Services.getService(WEB3AdminFeqOrderAcceptService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������������t����F�؃T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAcceptCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptCancelCompleteResponse)l_request.createResponse();
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
                "submit��t�����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAcceptCancelCompleteResponse) l_request.createResponse();
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
                "submit��t�����{�Ɏ��s���܂����B",
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
