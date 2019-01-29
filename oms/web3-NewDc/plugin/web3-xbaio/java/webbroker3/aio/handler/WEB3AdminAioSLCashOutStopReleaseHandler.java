head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLCashOutStopReleaseHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��S�ۃ��[���o����~�����n���h��(WEB3AdminAioSLCashOutStopReleaseHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �����i���u�j�V�K�쐬 ���f��No.764
Revision History : 2007/09/14 �����i���u�j���f��No.777
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���o����~�����n���h��)<BR>
 * �،��S�ۃ��[���o����~�����n���h���N���X<BR>
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3AdminAioSLCashOutStopReleaseHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLCashOutStopReleaseHandler.class);

    public WEB3AdminAioSLCashOutStopReleaseHandler()
    {

    }

    /**
     * (validate�o����~�����m�F���)<BR>
     * �،��S�ۃ��[���o����~�����m�F��ʕ\���������s���B<BR>
     * <BR>
     * �،��S�ۃ��[���o����~�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLCashOutStopReleaseConfirmResponse
     */
    public WEB3AdminSLCashOutStopReleaseConfirmResponse validateCashOutStopReleaseConfirmScreen(
        WEB3AdminSLCashOutStopReleaseConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateCashOutStopReleaseConfirmScreen(WEB3AdminSLCashOutStopReleaseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLCashOutStopReleaseConfirmResponse l_response = null;

        // �،��S�ۃ��[���o����~�����T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�،��S�ۃ��[���o����~�����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o����~�����m�F��ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o����~�����m�F��ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o����~�����������)<BR>
     * �،��S�ۃ��[���o����~����������ʕ\���������s���B<BR>
     * <BR>
     * �،��S�ۃ��[���o����~�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     *
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLCashOutStopReleaseCompleteResponse
     */
    public WEB3AdminSLCashOutStopReleaseCompleteResponse submitCashOutStopReleaseCompleteScreen(
        WEB3AdminSLCashOutStopReleaseCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitCashOutStopReleaseCompleteScreen(WEB3AdminSLCashOutStopReleaseCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLCashOutStopReleaseCompleteResponse l_response = null;

        // �،��S�ۃ��[���o����~�����T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�،��S�ۃ��[���o����~�����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o����~����������ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLCashOutStopReleaseCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o����~����������ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * get�S�ۃ��[���o���S�����ꗗ���<BR>
     * �،��S�ۃ��[���o���S�����ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �،��S�ۃ��[���o����~�����T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLRestraintMoneyListResponse
     */
    public WEB3AdminSLRestraintMoneyListResponse getSLRestraintMoneyListScreen(
        WEB3AdminSLRestraintMoneyListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLRestraintMoneyListScreen(WEB3AdminSLRestraintMoneyListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminAioSLCashOutStopReleaseService l_service = null;
        WEB3AdminSLRestraintMoneyListResponse l_response = null;

        // �،��S�ۃ��[���o����~�����T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3AdminAioSLCashOutStopReleaseService)Services.getService(
                WEB3AdminAioSLCashOutStopReleaseService.class);
        }
        catch (Exception l_ex)
        {
            // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "�،��S�ۃ��[���o����~�����T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o���S�����ꗗ��ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLRestraintMoneyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request,  "�،��S�ۃ��[���o���S�����ꗗ��ʕ\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
