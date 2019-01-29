head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t�n���h���N���X(WEB3AioCashinAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���E (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinAcceptRequest;
import webbroker3.aio.message.WEB3AioCashinAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (������t�n���h��)<BR>
 * ������t�n���h���N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashinAcceptHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinAcceptHandler.class);
    
    /**
     * (������t���N�G�X�g)<BR>
     * ������t�������s���B<BR>
     * <BR>
     * ������t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashinAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 40F7C46C00AB
     */
    public WEB3AioCashinAcceptResponse handleAioCashinAcceptRequest(WEB3AioCashinAcceptRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleAioCashinAcceptRequest(WEB3AioCashinAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("WEB3AioCashinAcceptRequest is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashinAcceptService l_service = null;
        WEB3AioCashinAcceptResponse l_response = null;
        try
        {
            l_service =
                (WEB3AioCashinAcceptService) Services.getService(
                    WEB3AioCashinAcceptService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "������t�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        } 
        
        try
        {
            l_response =
                (WEB3AioCashinAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashinAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "������t���X�|���X�̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
