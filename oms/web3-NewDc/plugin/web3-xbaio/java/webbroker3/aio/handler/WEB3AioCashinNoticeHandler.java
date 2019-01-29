head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���n���h���N���X(WEB3AioCashinNoticeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ���E (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinNoticeCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeCompleteResponse;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinNoticeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A���n���h��)<BR>
 * �����A���n���h���N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioCashinNoticeHandler implements MessageHandler 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeHandler.class);

    /**
     * (confirm�A��)<BR>
     * �����A���̐R�����s���B<BR>
     * <BR>
     * �����A���T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashinNoticeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBFBDC038A
     */
    public WEB3AioCashinNoticeConfirmResponse handleConfirmNoticeRequest(WEB3AioCashinNoticeConfirmRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleConfirmNoticeRequest(WEB3AioCashinNoticeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinNoticeService l_service = null;
        WEB3AioCashinNoticeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AioCashinNoticeService) Services.getService(
                    WEB3AioCashinNoticeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinNoticeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AioCashinNoticeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashinNoticeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A���m�F���X�|���X�̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete�A��)<BR>
     * �����A���̓o�^���s���B<BR>
     * <BR>
     * �����A���T�[�r�X���擾���A <BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashinNoticeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40EBFBE60119
     */
    public WEB3AioCashinNoticeCompleteResponse handleCompleteNoticeRequest(WEB3AioCashinNoticeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleConfirmNoticeRequest(WEB3AioCashinNoticeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinNoticeService l_service = null;
        WEB3AioCashinNoticeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AioCashinNoticeService) Services.getService(
                    WEB3AioCashinNoticeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinNoticeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A�������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AioCashinNoticeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashinNoticeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A���������X�|���X�̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
