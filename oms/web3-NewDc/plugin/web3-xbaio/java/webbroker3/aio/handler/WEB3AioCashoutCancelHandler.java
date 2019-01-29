head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������n���h��(WEB3AioCashoutCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 ���� (���u) �V�K�쐬
                   2004/10/25 ���z (���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o������n���h��)<BR>
 * (�o������n���h���N���X)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelHandler.class);
    
    /**
     * (confirm���)<BR>
     * �o�������̎�������R�����s���B<BR>
     * <BR>
     * �o������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3AioCashoutCancelConfirmResponse
     * @@roseuid 40F73D390272
     */
    public WEB3AioCashoutCancelConfirmResponse handleConfirmCancelRequest(
        WEB3AioCashoutCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleConfirmCancelRequest(" +
            "WEB3AioCashoutCancelConfirmRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o������T�[�r�X�C���^�[�t�F�C�X
        WEB3AioCashoutCancelService l_service = null;          
         
        //�o������m�F���X�|���X
        WEB3AioCashoutCancelConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelService) Services.getService(
                    WEB3AioCashoutCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o������m�F�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o������m�F�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete���)<BR>
     * �o�������̎���̓o�^���s���B<BR>
     * <BR>
     * �o������T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioCashoutCancelCompleteResponse
     * @@roseuid 40F73D8101E6
     */
    public WEB3AioCashoutCancelCompleteResponse handleCompleteCancelRequest(
        WEB3AioCashoutCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCompleteCancelRequest(WEB3AioCashoutCancelCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o������T�[�r�X�C���^�[�t�F�C�X
        WEB3AioCashoutCancelService l_service = null;          
         
        //�o������������X�|���X
        WEB3AioCashoutCancelCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelService) Services.getService(
                    WEB3AioCashoutCancelService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o����������T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o����������Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
