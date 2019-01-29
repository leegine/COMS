head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginErrorResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�n���h��(WEB3AdminAccInfoLoginErrorResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginErrorResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�n���h��)<BR>
 * �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�n���h��<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginErrorResetHandler implements MessageHandler 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginErrorResetHandler.class);   
    
    /**
     * @@roseuid 418F3A0D02CE
     */
    public WEB3AdminAccInfoLoginErrorResetHandler() 
    {
     
    }
    
    /**
     * (���͉�ʕ\��)<BR>
     * ���O�C���G���[���Z�b�g���͉�ʕ\�����������{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���̓��N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLoginErrorResetInputResponse inputScreenDisplay(WEB3AdminAccInfoLoginErrorResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginErrorResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginErrorResetInputResponse l_response = null;
        WEB3AdminAccInfoLoginErrorResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginErrorResetService)Services.getService(WEB3AdminAccInfoLoginErrorResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�̓��͉�ʕ\���Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (���O�C���G���[���Z�b�g)<BR>
     * ���O�C���G���[���Z�b�g�����{����B<BR>
     * <BR>
     * �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g���N�G�X�g�f�[�^�I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse
     * @@roseuid 4159354C0155
     */
    public WEB3AdminAccInfoLoginErrorResetResponse loginErrorReset(WEB3AdminAccInfoLoginErrorResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " loginErrorReset(WEB3AdminAccInfoLoginErrorResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginErrorResetResponse l_response = null;
        WEB3AdminAccInfoLoginErrorResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginErrorResetService)Services.getService(WEB3AdminAccInfoLoginErrorResetService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " �Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��҂��q�l��񃍃O�C���G���[���Z�b�g�̃��O�C���G���[���Z�b�g�Ɏ��s���܂����B", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
