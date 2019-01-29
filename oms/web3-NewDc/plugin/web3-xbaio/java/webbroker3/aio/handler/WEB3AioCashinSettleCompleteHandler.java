head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ϊ����n���h���N���X(WEB3AioCashinSettleCompleteHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���z (���u) �V�K�쐬
                   2004/10/22 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCompleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���ϊ����n���h��)<BR>
 * ���ϊ����n���h���N���X
 *
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteHandler implements MessageHandler 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCompleteHandler.class);
    
    /**
     * (���ϊ������N�G�X�g)<BR>
     * ���ϊ����T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B0A09031F
     */
    public WEB3AioCashinSettleCompleteResponse handleCashinSettleCompleteRequest(
        WEB3AioCashinSettleCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "handleCashinSettleCompleteRequest(WEB3AioCashinSettleCompleteRequest l_request)";
        log.entering(l_strMethodName);
 
        WEB3AioCashinSettleCompleteService l_service;
        WEB3AioCashinSettleCompleteResponse l_response;
        
        try
        {
            //���ϊ����T�[�r�X���擾��
            l_service = 
                (WEB3AioCashinSettleCompleteService)Services.getService(
                WEB3AioCashinSettleCompleteService.class);
        }
        ////�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSettleCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "���ϊ����T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AioCashinSettleCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettleCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "���ϊ��������Ɏ��s���܂���",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
