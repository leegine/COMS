head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PTS�����J�ݐ\���n���h��(WEB3InformPTSAccOpenApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 �Ӑ� (���u) �V�K�쐬 ���f��No.124
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (PTS�����J�ݐ\���n���h��)<BR>
 * PTS�����J�ݐ\���n���h��
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformPTSAccOpenApplyHandler.class);

    /**
     * @@roseuid 47B9271A02FD
     */
    public WEB3InformPTSAccOpenApplyHandler()
    {

    }

    /**
     * (���͉�ʕ\��)<BR>
     * PTS�����J�ݐ\�����͉�ʕ\���������s���B <BR>
     * <BR>
     * PTS�����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyInpResponse
     * @@roseuid 47A0221A0044
     */
    public WEB3InformPTSAccOpenApplyInpResponse displayInputScreen(
        WEB3InformPTSAccOpenApplyInpRequest l_request)
    {
        final String STR_METHOD_NAME = "displayInputScreen(WEB3InformPTSAccOpenApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyInpResponse l_response = null;
        WEB3InformPTSAccOpenApplyService l_service = null;

        try
        {
            l_service =
                (WEB3InformPTSAccOpenApplyService)Services.getService(
                    WEB3InformPTSAccOpenApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "PTS�����J�ݐ\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3InformPTSAccOpenApplyInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "PTS�����J�ݐ\�����͏����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�\���m�F)<BR>
     * PTS�����J�ݐ\���m�F�������s���B <BR>
     * <BR>
     * PTS�����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyCnfResponse
     * @@roseuid 47A0221C0228
     */
    public WEB3InformPTSAccOpenApplyCnfResponse applyConfirm(
        WEB3InformPTSAccOpenApplyCnfRequest l_request)
    {
        final String STR_METHOD_NAME = "applyConfirm(WEB3InformPTSAccOpenApplyCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCnfResponse l_response = null;
        WEB3InformPTSAccOpenApplyService l_service = null;

        try
        {
            l_service =
                (WEB3InformPTSAccOpenApplyService)Services.getService(
                    WEB3InformPTSAccOpenApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "PTS�����J�ݐ\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3InformPTSAccOpenApplyCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "PTS�����J�ݐ\���m�F�����ɃG���[���������܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (�\������)<BR>
     * PTS�����J�ݐ\�������������s���B <BR>
     * <BR>
     * PTS�����J�ݐ\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3InformPTSAccOpenApplyCmpResponse
     * @@roseuid 47A0221F0218
     */
    public WEB3InformPTSAccOpenApplyCmpResponse applyComplete(
        WEB3InformPTSAccOpenApplyCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "applyComplete(WEB3InformPTSAccOpenApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3InformPTSAccOpenApplyCmpResponse l_response = null;
        WEB3InformPTSAccOpenApplyService l_service = null;

        try
        {
            l_service =
                (WEB3InformPTSAccOpenApplyService)Services.getService(
                    WEB3InformPTSAccOpenApplyService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "PTS�����J�ݐ\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3InformPTSAccOpenApplyCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "execute()���\�b�h���R�[�����邱�Ƃ����s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3InformPTSAccOpenApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "PTS�����J�ݐ\�����������ɃG���[���������܂����B",
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
