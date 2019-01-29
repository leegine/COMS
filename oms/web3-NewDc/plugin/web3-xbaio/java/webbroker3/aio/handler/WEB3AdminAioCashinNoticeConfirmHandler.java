head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeConfirmHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A���m�F�n���h���N���X(WEB3AdminAioCashinNoticeConfirmHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashinConfirmListRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����A���m�F�n���h��)<BR>
 * �����A���m�F�n���h���N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */

public class WEB3AdminAioCashinNoticeConfirmHandler implements MessageHandler 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinNoticeConfirmHandler.class);    
            
    
    /**
     * (�����A���m�F�ꗗ���N�G�X�g)<BR>
     * �����A���m�F�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3AdminAioCashinConfirmListResponse
     * @@roseuid 4108777A037B
     */
    public WEB3AdminAioCashinConfirmListResponse handleCashinConfirmListRequest(
        WEB3AdminAioCashinConfirmListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashinConfirmListRequest(WEB3AdminAioCashinConfirmListRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        //�����A���m�F�T�[�r�X���擾��
        WEB3AdminAioCashinNoticeConfirmService l_service = null;
        WEB3AdminAioCashinConfirmListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinNoticeConfirmService) 
                Services.getService(WEB3AdminAioCashinNoticeConfirmService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����A���m�F�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminAioCashinConfirmListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "�����A���m�F���������s���܂����B", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
