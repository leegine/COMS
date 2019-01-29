head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�N�n���h��(WEB3AioCashoutTradingPowerHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckRequest;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���]�̓`�F�b�N�n���h��)<BR>
 * �o���]�̓`�F�b�N�n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutTradingPowerHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutTradingPowerHandler.class);
    
    /**
     * (�o���]�̓`�F�b�N���N�G�X�g)<BR>
     * �o���]�̓`�F�b�N�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutTradingPowerCheckResponse
     * @@roseuid 41294DCB0256
     */
    public WEB3AioCashoutTradingPowerCheckResponse 
        handleCashoutRemainingPowerCheckRequest(
            WEB3AioCashoutTradingPowerCheckRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashoutRemainingPowerCheckRequest(" +
            "WEB3AioCashoutTradingPowerCheckRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o���]�̓`�F�b�N�T�[�r�X
        WEB3AioCashoutTradingPowerService l_service = null;          
         
        //�o���]�̓`�F�b�N���X�|���X
        WEB3AioCashoutTradingPowerCheckResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutTradingPowerService) Services.getService(
                    WEB3AioCashoutTradingPowerService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���]�̓`�F�b�N���X�|���X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o���]�͂Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
