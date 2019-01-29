head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������ꗗ�n���h��(WEB3AioCashoutCancelListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 ���� (���u) �V�K�쐬
                   2004/10/22 ���E(���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCancelListRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelListResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o������ꗗ�n���h��)<BR>
 * �o������ꗗ�n���h���N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelListHandler implements MessageHandler 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelListHandler.class);
    
    /**
     * (�o������ꗗ���N�G�X�g)<BR>
     * �o������ꗗ�\���������s���B<BR>
     * <BR>
     * �o������ꗗ�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3AioCashoutCancelListResponse
     * @@roseuid 40F635E203A0
     */
    public WEB3AioCashoutCancelListResponse handleAioCashoutCancelListRequest(
        WEB3AioCashoutCancelListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleAioCashoutCancelListRequest(" +
            "WEB3AioCashoutCancelListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //�o������ꗗ�T�[�r�X
        WEB3AioCashoutCancelListService l_service = null;          
         
        //�o������ꗗ���X�|���X
        WEB3AioCashoutCancelListResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelListService) Services.getService(
                    WEB3AioCashoutCancelListService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3AioCashoutCancelListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�o������ꗗ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "�o������ꗗ�Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
