head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ�n���h���N���X(WEB3AioCashTransferListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���E (���u) �V�K�쐬
                   2004/10/25 ���z (���u) ���r���[
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashTransferListRequest;
import webbroker3.aio.message.WEB3AioCashTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AioCashTransferListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���o���ꗗ�n���h��)<BR>
 * ���o���ꗗ�n���h���N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0 
 */
public class WEB3AioCashTransferListHandler implements MessageHandler 
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferListHandler.class);
    
    
    /**
     * ���o���ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashTransferListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4100F1B802CE
     */
    public WEB3AioCashTransferListResponse handleCashTransferListRequest(WEB3AioCashTransferListRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "handleCashTransferListRequest(WEB3AioCashTransferListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashTransferListResponse l_response = null;
        WEB3AioCashTransferListService l_service = null;
        try
        {
            l_service =
                (WEB3AioCashTransferListService) Services.getService(
                    WEB3AioCashTransferListService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashTransferListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "���o���ꗗ�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        } 
        
        try
        {
            l_response =
                (WEB3AioCashTransferListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioCashTransferListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�����A�����͂̎擾�Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
