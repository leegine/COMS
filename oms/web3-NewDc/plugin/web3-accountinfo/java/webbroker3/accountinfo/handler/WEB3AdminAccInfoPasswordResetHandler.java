head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�n���h��(WEB3AdminAccInfoPasswordResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�n���h��)<BR>
 * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordResetHandler.class);   
    
    /**
     * @@roseuid 418F3A0F0109
     */
    public WEB3AdminAccInfoPasswordResetHandler() 
    {
     
    }
    
    /**
     * (get���͉��)<BR>
     * �Ïؔԍ����Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B78730366
     */
    public WEB3AdminAccInfoPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordResetInputResponse l_response = null;
        WEB3AdminAccInfoPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordResetService)Services.getService(WEB3AdminAccInfoPasswordResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�Ïؔԍ����Z�b�g)<BR>
     * �Ïؔԍ����Z�b�g�����{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���Ïؔԍ����Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse
     * @@roseuid 41649DB400F6
     */
    public WEB3AdminAccInfoPasswordResetResponse passwordReset(WEB3AdminAccInfoPasswordResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " passwordReset(WEB3AdminAccInfoPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordResetResponse l_response = null;
        WEB3AdminAccInfoPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordResetService)Services.getService(WEB3AdminAccInfoPasswordResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���Ïؔԍ����Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l���Ïؔԍ����Z�b�g�̈Ïؔԍ����Z�b�g�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
