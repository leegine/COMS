head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ψ˗��n���h���N���X(WEB3AioCashinSettlementHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 ���z (���u) �V�K�쐬
                   2004/10/23 ������ (���u) ���r���[ 
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinConfirmResponse;
import webbroker3.aio.message.WEB3AioCashinSettlementRequest;
import webbroker3.aio.message.WEB3AioCashinSettlementResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettlementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ψ˗��n���h��)<BR>
 * ���ψ˗��n���h���N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementHandler.class);
    
    /**
     * (confirm����)<BR>
     * ���ψ˗��̔����R�����s���B<BR>
     * <BR>
     * ���ψ˗��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AioCashinConfirmResponse
     * @@roseuid 40F25E7502EF
     */
    public WEB3AioCashinConfirmResponse handleConfirmSettlement(
        WEB3AioCashinConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "handleConfirmSettlement(WEB3AioCashinConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSettlementService l_service;
        WEB3AioCashinConfirmResponse l_response;
        
        try
        {
            //���ψ˗��T�[�r�X���擾��
            l_service = 
                (WEB3AioCashinSettlementService)Services.getService(
                WEB3AioCashinSettlementService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__confirm����__���ψ˗��T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            log.debug("Before the execute ---------------------------------------------");
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioCashinConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__confirm����__���ψ˗��̔����R�������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (start����)<BR>
     * ���ψ˗��������s���B<BR>
     * <BR>
     * ���ψ˗��T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * 
     * @@return WEB3AioCashinSettlementResponse
     * @@roseuid 40F25E80036C
     */
    public WEB3AioCashinSettlementResponse handleStartSettlement(WEB3AioCashinSettlementRequest l_request) 
    {
        String l_strMethodName = "handleStartSettlement(WEB3AioCashinSettlementRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSettlementService l_service;
        WEB3AioCashinSettlementResponse l_response;
        
        try
        {
            l_service = 
                (WEB3AioCashinSettlementService)Services.getService(WEB3AioCashinSettlementService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinSettlementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__start����__���ψ˗��T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AioCashinSettlementResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettlementResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__start����__���ψ˗����������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
