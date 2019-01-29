head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���ԍώ���n���h��(WEB3AioSLRepayCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����q (���u) �V�K�쐬 �d�l�ύX�E���f��No.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍώ���n���h��)<BR>
 * �،��S�ۃ��[���ԍώ���n���h��<BR>
 *
 * @@author �����q
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelHandler.class);

    /**
     * @@roseuid 46E890860319
     */
    public WEB3AioSLRepayCancelHandler()
    {

    }

    /**
     * (confirm����)<BR>
     * �،��S�ۃ��[���ԍώ���̔����R�����s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍώ���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_slRepayCancelConfirmRequest - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayCancelConfirmResponse
     * @@roseuid 46DE4C0701D1
     */
    public WEB3SLRepayCancelConfirmResponse confirmOrder(
        WEB3SLRepayCancelConfirmRequest l_slRepayCancelConfirmRequest)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3SLRepayCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelConfirmResponse l_response = null;
        WEB3AioSLRepayCancelService l_service = null;

        try
        {
            l_service = (WEB3AioSLRepayCancelService)Services.getService(WEB3AioSLRepayCancelService.class);
        }
        catch (Exception l_ex)
        {
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_slRepayCancelConfirmRequest,
                "�،��S�ۃ��[���ԍώ���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // �،��S�ۃ��[���ԍώ���T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response = (WEB3SLRepayCancelConfirmResponse)l_service.execute(l_slRepayCancelConfirmRequest);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelConfirmRequest,
                "�،��S�ۃ��[���ԍώ���m�F���N�G�X�g�����s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelConfirmRequest,
                "�،��S�ۃ��[���ԍώ���m�F�T�[�r�X���������s���܂����B",
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
     * �،��S�ۃ��[���ԍώ���̓o�^���s���B<BR>
     * <BR>
     * �،��S�ۃ��[���ԍώ���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_slRepayCancelCompleteRequest - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3SLRepayCancelCompleteResponse
     * @@roseuid 46DE4C0B0393
     */
    public WEB3SLRepayCancelCompleteResponse completeOrder(
        WEB3SLRepayCancelCompleteRequest l_slRepayCancelCompleteRequest)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3SLRepayCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelCompleteResponse l_response = null;
        WEB3AioSLRepayCancelService l_slRepayCancelService = null;

        try
        {
            l_slRepayCancelService =
                (WEB3AioSLRepayCancelService) Services.getService(
                    WEB3AioSLRepayCancelService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_slRepayCancelCompleteRequest,
                "�،��S�ۃ��[���ԍώ���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //�،��S�ۃ��[���ԍώ���T�[�r�X���擾���Aexecute()���\�b�h���R�[������
            l_response =
                (WEB3SLRepayCancelCompleteResponse)l_slRepayCancelService.execute(
                    l_slRepayCancelCompleteRequest);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelCompleteRequest,
                "�،��S�ۃ��[���ԍώ���������N�G�X�g�����s���܂����B",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelCompleteRequest,
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
