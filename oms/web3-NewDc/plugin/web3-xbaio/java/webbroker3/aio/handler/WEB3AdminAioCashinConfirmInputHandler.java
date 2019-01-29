head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F���̓n���h���N���X(WEB3AdminAioCashinConfirmInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinConfirmInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A���m�F���̓n���h��)<BR>
 * �����A���m�F���̓n���h���N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashinConfirmInputHandler implements MessageHandler 
{    
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinConfirmInputHandler.class);    
    
    /**
     * (�����A���m�F���̓��N�G�X�g)<BR>
     * �����A���m�F���̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminAioCashinConfirmInputResponse
     * @@roseuid 410749E301B3
     */
    public WEB3AdminAioCashinConfirmInputResponse handleCashinConfirmInputRequest(
        WEB3AdminAioCashinConfirmInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashinConfirmInputRequest(WEB3AdminAioCashinConfirmInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //�����A���m�F���̓T�[�r�X���擾��
        WEB3AdminAioCashinConfirmInputService l_service = null;
        WEB3AdminAioCashinConfirmInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinConfirmInputService) 
                Services.getService(WEB3AdminAioCashinConfirmInputService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A���m�F���̓T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinConfirmInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����A���m�F���͏��������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
