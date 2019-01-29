head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l���p�X���[�h���Z�b�g�n���h��(WEB3AdminAccInfoLoginPasswordResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l���p�X���[�h���Z�b�g�n���h��)<BR>
 * �Ǘ��҂��q�l���p�X���[�h���Z�b�g�n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordResetHandler.class);       
    
    /**
     * @@roseuid 418F3A0C03A9
     */
    public WEB3AdminAccInfoLoginPasswordResetHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * �p�X���[�h���Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���p�X���[�h���Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B72DE027C
     */
    public WEB3AdminAccInfoLoginPasswordResetInputResponse inputScreenDisplay(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordResetInputResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordResetService)Services.getService(WEB3AdminAccInfoLoginPasswordResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �Ǘ��҂��q�l���p�X���[�h���Z�b�g�̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (�p�X���[�h���Z�b�g)<BR>
     * �p�X���[�h���Z�b�g�����{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l���p�X���[�h���Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse
     * @@roseuid 4158E8920124
     */
    public WEB3AdminAccInfoLoginPasswordResetResponse loginPasswordReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " loginPasswordReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordResetResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordResetService)Services.getService(WEB3AdminAccInfoLoginPasswordResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l���p�X���[�h���Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " �Ǘ��҂��q�l���p�X���[�h���Z�b�g�̃p�X���[�h���Z�b�g�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
