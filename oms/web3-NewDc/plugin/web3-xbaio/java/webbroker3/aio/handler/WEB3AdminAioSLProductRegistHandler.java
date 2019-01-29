head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����o�^�n���h��(WEB3AdminAioSLProductRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 �И��� (���u) �V�K�쐬 ���f��No.760
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistConfirmResponse;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputRequest;
import webbroker3.aio.message.WEB3AdminSLProductRegistInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (�S�ۖ����o�^�n���h��)<BR>
 * �S�ۖ����o�^�n���h���N���X<BR>
 *
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminAioSLProductRegistHandler implements MessageHandler
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistHandler.class);

    /**
     * (get�S�ۖ�������)<BR>
     * �S�ۖ����o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �S�ۖ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLProductRegistInputResponse
     */
    public WEB3AdminSLProductRegistInputResponse getSLProductInput(
        WEB3AdminSLProductRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSLProductInput(WEB3AdminSLProductRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        //�S�ۖ����o�^�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSLProductRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͉�ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͉�ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�S�ۖ���)<BR>
     * �S�ۖ����o�^���͊m�F��ʕ\���������s���B<BR>
     * <BR>
     * �S�ۖ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLProductRegistConfirmResponse
     */
    public WEB3AdminSLProductRegistConfirmResponse validateSLProductRegist(
        WEB3AdminSLProductRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateSLProductRegist(WEB3AdminSLProductRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //�S�ۖ����o�^�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSLProductRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͊m�F��ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͊m�F��ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�S�ۖ���)<BR>
     * �S�ۖ����o�^���͊�����ʕ\���������s���B<BR>
     * <BR>
     * �S�ۖ����o�^�T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminSLProductRegistCompleteResponse
     */
    public WEB3AdminSLProductRegistCompleteResponse submitSLProductRegist(
        WEB3AdminSLProductRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitSLProductRegist(WEB3AdminSLProductRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //�S�ۖ����o�^�T�[�r�X���擾��
        WEB3AdminAioSLProductRegistService l_service = null;
        WEB3AdminSLProductRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSLProductRegistService)
                Services.getService(WEB3AdminAioSLProductRegistService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�S�ۖ����o�^�T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������
            l_response = (WEB3AdminSLProductRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͊�����ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSLProductRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�S�ۖ����o�^���͊�����ʕ\�����������s���܂����B",
                l_ex.getErrorInfo(),
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
