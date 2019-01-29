head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX����U�֓��̓n���h��(WEB3FXTransFromFXInputHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 ����(���u) �V�K�쐬
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX����U�֓��̓n���h��) <BR>
 * FX����U�֓��̓n���h���N���X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FXTransFromFXInputHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXInputHandler.class);

    /**
     * (���͉�ʕ\��) <BR>
     * FX����U�֓��̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3FXTransFromFXInputResponse
     * @@roseuid 41BCF7FE0344
     */
    public WEB3FXTransFromFXInputResponse displayInputScreen(
        WEB3FXTransFromFXInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3FXTransFromFXCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FX����U�֓��̓T�[�r�X
        WEB3FXTransFromFXInputService l_service = null;
        //FX����U�֓��̓��X�|���X�N���X
        WEB3FXTransFromFXInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXInputService) Services.getService(
                    WEB3FXTransFromFXInputService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A
            //�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response =
                (WEB3FXTransFromFXInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX����U�֓��̓T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX����U�֓��̓��X�|���X�N���X�̏����Ɏ��s���܂����B", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
