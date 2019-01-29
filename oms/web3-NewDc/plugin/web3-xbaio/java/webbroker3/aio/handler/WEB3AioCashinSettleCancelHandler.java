head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����v�����~�n���h���N���X(WEB3AioCashinSettleCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���z (���u) �V�K�쐬
                   2004/10/22 ���� (���u) ���r���[    
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSettleCancelRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCancelResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����v�����~�n���h��)<BR>
 * �����v�����~�n���h���N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCancelHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCancelHandler.class);
    
    /**
     * (�����v�����~���N�G�X�g)<BR>
     * �����v�����~�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4119A83500FB
     */
    public WEB3AioCashinSettleCancelResponse handleCashinSettleCancelRequest(
        WEB3AioCashinSettleCancelRequest l_request) 
    {
        String l_strMethodName = 
            "handleCashinSettleCancelRequest(WEB3AioCashinSettleCancelRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSettleCancelService l_service;
        WEB3AioCashinSettleCancelResponse l_response;
        
        try
        {
            //�����v�����~�T�[�r�X���擾��
            l_service = 
                (WEB3AioCashinSettleCancelService)Services.getService(
                WEB3AioCashinSettleCancelService.class);
        }
        ////�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSettleCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "�����v�����~�T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioCashinSettleCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettleCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�����v�����~�����Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
