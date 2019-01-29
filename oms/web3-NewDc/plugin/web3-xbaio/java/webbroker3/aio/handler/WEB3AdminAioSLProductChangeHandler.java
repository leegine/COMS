head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����ύX�n���h��(WEB3AdminAioSLProductChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �g�E�N�|(���u) �V�K�쐬 �d�l�ύX���f��760
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (�S�ۖ����ύX�n���h��)<BR>
 * �S�ۖ����ύX�n���h���N���X<BR>
 * <BR>
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3AdminAioSLProductChangeHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductChangeHandler.class);

    /**
     * @@roseuid 46E8EE2A0375
     */
    public WEB3AdminAioSLProductChangeHandler()
    {

    }

    /**
     * (get�S�ۖ����ύX����)<BR>
     * �S�ۖ����ύX���͉�ʕ\���������s���B <BR>
     * <BR>
     * �S�ۖ����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeInputResponse
     * @@roseuid 46DCC07F0046
     */
    public WEB3AdminSLProductChangeInputResponse getSLProductChangeInput(
        WEB3AdminSLProductChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSLProductChangeInput(WEB3AdminSLProductChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeInputResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //�S�ۖ����ύX�T�[�r�X���擾��
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get�S�ۖ����ύX���͏��������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "get�S�ۖ����ύX���͏��������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�S�ۖ����ύX)<BR>
     * �S�ۖ����ύX�m�F��ʕ\���������s���B <BR>
     * <BR>
     * �S�ۖ����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeConfirmResponse
     * @@roseuid 46DCC1360201
     */
    public WEB3AdminSLProductChangeConfirmResponse validateSLProductChange(
        WEB3AdminSLProductChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateSLProductChange(WEB3AdminSLProductChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeConfirmResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //�S�ۖ����ύX�T�[�r�X���擾��
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "validate�S�ۖ����ύX���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "validate�S�ۖ����ύX���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�S�ۖ����ύX)<BR>
     * �S�ۖ����ύX������ʕ\���������s���B <BR>
     * <BR>
     * �S�ۖ����ύX�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminSLProductChangeCompleteResponse
     * @@roseuid 46DCC194003F
     */
    public WEB3AdminSLProductChangeCompleteResponse submitSLProductChange(
        WEB3AdminSLProductChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitSLProductChange(WEB3AdminSLProductChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductChangeCompleteResponse l_response = null;
        WEB3AdminAioSLProductChangeService l_service = null;

        try
        {
            //�S�ۖ����ύX�T�[�r�X���擾��
            l_service = (WEB3AdminAioSLProductChangeService)Services.getService(
                WEB3AdminAioSLProductChangeService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����ύX�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "submit�S�ۖ����ύX���������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "submit�S�ۖ����ύX���������s���܂����B",
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
