head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍϐ\���n���h��(WEB3AioSLRepayApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �đo�g (���u) �V�K�쐬 �d�l�ύX ���f��No.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayApplyCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayApplyCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayApplyConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍϐ\���n���h��)<BR>
 * �،��S�ۃ��[���ԍϐ\���n���h���N���X<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3AioSLRepayApplyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AioSLRepayApplyHandler.class);

    /**
     * @@roseuid 46E8908602BB
     */
    public WEB3AioSLRepayApplyHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * �،��S�ۃ��[���ԍϐ\���̔����R�����s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍϐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayApplyConfirmResponse
     */
    public WEB3SLRepayApplyConfirmResponse confirmOrder(WEB3SLRepayApplyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SLRepayApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyService l_service = null;
        WEB3SLRepayApplyConfirmResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyService)Services.getService(
                    WEB3AioSLRepayApplyService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���̔����R�����������s���܂����B",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���T�[�r�X���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete����)<BR>
     * �،��S�ۃ��[���ԍϐ\���̓o�^���s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍϐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayApplyCompleteResponse
     */
    public WEB3SLRepayApplyCompleteResponse completeOrder(WEB3SLRepayApplyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SLRepayApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSLRepayApplyService l_service = null;
        WEB3SLRepayApplyCompleteResponse l_response = null;

        try
        {
            l_service =
                (WEB3AioSLRepayApplyService)Services.getService(
                    WEB3AioSLRepayApplyService.class);
        }
        catch (Exception l_exc)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���T�[�r�X�̎擾�Ɏ��s���܂���",
                l_response.errorInfo,
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_exc)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_exc.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���̓o�^���������s���܂����B",
                l_exc);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�،��S�ۃ��[���ԍϐ\���T�[�r�X���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
