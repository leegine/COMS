head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���n���h��(WEB3AioCashoutHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���\���n���h��)<BR>
 * �o���\���n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutHandler.class);
            
    /**
     * (confirm����)<BR>
     * �o���\���̔����R�����s���B <BR>
     * <BR>
     * �o���\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutConfirmResponse
     * @@roseuid 40EBEC7701E5
     */
    public WEB3AioCashoutConfirmResponse handleConfirmOrderRequest(WEB3AioCashoutConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleConfirmOrderRequest(WEB3AioCashoutConfirmRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o���\���T�[�r�X
        WEB3AioCashoutService l_service = null;          
         
        //�o���\���m�F���X�|���X
        WEB3AioCashoutConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutService) Services.getService(
                    WEB3AioCashoutService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o���\���m�F�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete����)<BR>
     * �o���\���̓o�^���s���B <BR>
     * <BR>
     * �o���\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutCompleteResponse
     * @@roseuid 40EBEC7D02B0
     */
    public WEB3AioCashoutCompleteResponse handleCompleteOrderRequest(WEB3AioCashoutCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCompleteOrderRequest(WEB3AioCashoutCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o���\���T�[�r�X
        WEB3AioCashoutService l_service = null;          
         
        //�o���\���������X�|���X
        WEB3AioCashoutCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutService) Services.getService(
                    WEB3AioCashoutService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o���\���T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o���\�������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
