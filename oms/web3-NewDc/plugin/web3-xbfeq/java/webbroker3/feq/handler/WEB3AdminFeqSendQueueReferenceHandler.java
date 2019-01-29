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
filename	WEB3AdminFeqSendQueueReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[�Ɖ�n���h��(WEB3AdminFeqSendQueueReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬 
*/
package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO������SEND�L���[�Ɖ�n���h�� )<BR>
 * �Ǘ��ҊO������SEND�L���[�Ɖ�n���h���N���X<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueReferenceHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueReferenceHandler.class);
    
	public WEB3AdminFeqSendQueueReferenceHandler()
	{
		
	}
	
    /**
     * (get���͉��)<BR>
     * ���͉�ʕ\�������B 	<BR>
     * <BR>
     * �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceInputResponse
     * @@roseuid 429FECBF005E
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse getInputScreen(
    	WEB3AdminFeqSendQueueReferenceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqSendQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqSendQueueReferenceInputResponse l_response = null;
        WEB3AdminFeqSendQueueReferenceService l_service = null;

        try
        {            
            l_service = (WEB3AdminFeqSendQueueReferenceService)
                Services.getService(WEB3AdminFeqSendQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse) l_request.createResponse();
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
     * (get�ꗗ���)<BR>
     * �ꗗ��ʕ\�������B <BR>
     * <BR>
     * �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceResponse
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqSendQueueReferenceResponse getListScreen(
    	WEB3AdminFeqSendQueueReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqSendQueueReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g��null�ł��B");
        }

        WEB3AdminFeqSendQueueReferenceResponse l_response = null;
        WEB3AdminFeqSendQueueReferenceService l_service = null;

        try
        {            
            l_service = (WEB3AdminFeqSendQueueReferenceService)
                Services.getService(WEB3AdminFeqSendQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X���擾�Ɏ��s���܂����B");
            }
            //execute
            l_response = (WEB3AdminFeqSendQueueReferenceResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceResponse)l_request.createResponse();
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
                "get�ꗗ��ʂ����{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceResponse) l_request.createResponse();
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
                "get�ꗗ��ʂ����{�Ɏ��s���܂����B",
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
