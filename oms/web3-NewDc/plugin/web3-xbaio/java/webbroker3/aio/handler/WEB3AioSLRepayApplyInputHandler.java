head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\�����̓n���h��(WEB3AioSLRepayApplyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �đo�g (���u) �V�K�쐬 �d�l�ύX ���f��No.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayApplyInputRequest;
import webbroker3.aio.message.WEB3SLRepayApplyInputResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍϐ\�����̓n���h��)<BR>
 * �،��S�ۃ��[���ԍϐ\�����̓n���h���N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyInputHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyInputHandler.class);

    /**
     * @@roseuid 46E89086025E
     */
    public WEB3AioSLRepayApplyInputHandler()
    {

    }

    /**
     * (�،��S�ۃ��[���ԍϐ\�����̓��N�G�X�g)<BR>
     * �،��S�ۃ��[���ԍϐ\�����͕\���������s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍϐ\�����̓T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3SLRepayApplyInputResponse
     * @@roseuid 46CBF7E103B9
     */
    public WEB3SLRepayApplyInputResponse slRepayInputRequest(WEB3SLRepayApplyInputRequest l_request)
    {
        final String STR_METHOD_NAME = " slRepayInputRequest(WEB3SLRepayApplyInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyInputService l_service = null;
        WEB3SLRepayApplyInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyInputService)Services.getService(
                    WEB3AioSLRepayApplyInputService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X���������s���܂����B",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_excRuntime)
        {
            l_response = (WEB3SLRepayApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_excRuntime.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X���������s���܂����B",
                l_excRuntime);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
