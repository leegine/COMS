head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o����t�n���h��(WEB3AioForeignCashTransAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioForeignCashTransAcceptRequest;
import webbroker3.aio.message.WEB3AioForeignCashTransAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioForeignCashTransAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�ݓ��o����t�n���h��)<BR>
 * �O�ݓ��o����t�n���h���N���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptHandler.class);
    
    /**
     * (�O�ݓ��o����t���N�G�X�g)<BR>
     * �O�ݓ��o����t�������s���B<BR>
     * <BR>
     * �O�ݓ��o����t�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioForeignCashTransAcceptResponse
     * @@throws WEB3BaseException
     */
    public WEB3AioForeignCashTransAcceptResponse foreignCashTransAcceptRequest(
        WEB3AioForeignCashTransAcceptRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "foreignCashTransAcceptRequest(WEB3AioForeignCashTransAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioForeignCashTransAcceptService l_service = null;
        WEB3AioForeignCashTransAcceptResponse l_response = null;
        try
        {
            l_service =
                (WEB3AioForeignCashTransAcceptService)Services.getService(
                    WEB3AioForeignCashTransAcceptService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�O�ݓ�����t�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        } 
        
        try
        {
            l_response =
                (WEB3AioForeignCashTransAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�O�ݓ��o����t�����Ɏ��s���܂���", l_ex.getErrorInfo(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}

@
