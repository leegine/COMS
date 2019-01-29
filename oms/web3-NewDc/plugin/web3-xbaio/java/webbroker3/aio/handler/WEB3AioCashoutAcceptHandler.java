head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o����t�n���h���N���X(WEB3AioCashoutAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���E (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3AioCashoutAcceptRequest;
import webbroker3.aio.message.WEB3AioCashoutAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o����t�n���h��)<BR>
 * �o����t�n���h���N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioCashoutAcceptHandler implements MessageHandler 
{   
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutAcceptHandler.class);
    
    /**
     * (�o����t���N�G�X�g)<BR>
     * �o����t�������s���B<BR>
     * <BR>
     * �o����t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 40FC7D1702CC
     */
    public WEB3AioCashoutAcceptResponse handleCashoutAcceptRequest(WEB3AioCashoutAcceptRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleCashoutAcceptRequest(WEB3AioCashoutAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AioCashoutAcceptService l_service = null;
        WEB3AioCashoutAcceptResponse l_response = null; 
        try
        {
            l_service =
                (WEB3AioCashoutAcceptService) Services.getService(
                    WEB3AioCashoutAcceptService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashoutAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o����t�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        } 
        
        try
        {
            l_response =
                (WEB3AioCashoutAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashoutAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�o����t���X�|���X�̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
