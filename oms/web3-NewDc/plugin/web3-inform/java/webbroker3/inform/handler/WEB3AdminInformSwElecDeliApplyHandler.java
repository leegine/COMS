head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformSwElecDeliApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ����ؑցE�d�q��t�\���n���h���N���X�iWEB3AdminInformSwElecDeliApplyHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 �����i���u�j�V�K�쐬 ���f��No.099
Revision History : 2007/09/20 �g�E�N�|�i���u�j���f��No.112
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ����ؑցE�d�q��t�\���n���h��)<BR>
 * �Ǘ��Ҍ����ؑցE�d�q��t�\���n���h���N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminInformSwElecDeliApplyHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformSwElecDeliApplyHandler.class);

    public WEB3AdminInformSwElecDeliApplyHandler()
    {

    }

    /**
     *�i������ʕ\���j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\��������ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplySrcResponse
     */
    public WEB3AdminInformAccSwElecDeliApplySrcResponse searchScreenDisplay(
        WEB3AdminInformAccSwElecDeliApplySrcRequest l_request)
    {
        final String STR_METHOD_NAME = "searchScreenDisplay(WEB3AdminInformAccSwElecDeliApplySrcRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplySrcResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplySrcResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Ҍ����ؑցE�d�q��t�\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i���͉�ʕ\���j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyInpResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyInpResponse displayInputScreen(
        WEB3AdminInformAccSwElecDeliApplyInpRequest l_request)
    {
        final String STR_METHOD_NAME = "displayInputScreen(WEB3AdminInformAccSwElecDeliApplyInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Ҍ����ؑցE�d�q��t�\�����͉�ʂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i�\���m�F�j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���m�F���N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyConfResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyConfResponse applyConfirm(
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request)
    {
        final String STR_METHOD_NAME = "applyConfirm(WEB3AdminInformAccSwElecDeliApplyConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Ҍ����ؑցE�d�q��t�\���\���m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i�\�������j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\�������������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g
     * @@return WEB3AdminInformAccSwElecDeliApplyCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyCmpResponse applyComplete(
        WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "applyComplete(WEB3AdminInformAccSwElecDeliApplyCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��Ҍ����ؑցE�d�q��t�\���\�������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i�Ɖ��ʕ\���j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���Ɖ��ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyRefResponse
     */
    public WEB3AdminInformAccSwElecDeliApplyRefResponse displayReferenceScreen(
        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request)
    {
        final String STR_METHOD_NAME = "displayReferenceScreen(WEB3AdminInformAccSwElecDeliApplyRefRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliApplyRefResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���Q�ƂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliApplyRefResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���Q�ƂɎ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i�ύX�m�F�j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���ύX�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliApplyChangeConfResponse
     */
    public WEB3AdminInformAccSwElecDeliChangeConfResponse changeConfirm(
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request)
    {
        final String STR_METHOD_NAME = "changeConfirm(WEB3AdminInformAccSwElecDeliChangeConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i�ύX�����j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���ύX�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliChangeCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse changeComplete(
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "changeComplete(WEB3AdminInformAccSwElecDeliChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�����Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     *�i����m�F�j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\������m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteConfResponse
     */
    public WEB3AdminInformAccSwElecDeliDeleteConfResponse deleteConfirm(
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteConfirm(WEB3AdminInformAccSwElecDeliDeleteConfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\������m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\������m�F�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
    /**
     *�i��������j<BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\����������������s���B<BR>
     * <BR>
     * �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminInformAccSwElecDeliDeleteCmpResponse
     */
    public WEB3AdminInformAccSwElecDeliDeleteCmpResponse deleteComplete(
        WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteComplete(WEB3AdminInformAccSwElecDeliDeleteCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformAccSwElecDeliDeleteCmpResponse l_response = null;
        WEB3AdminInformSwElecDeliApplyService l_service = null;

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾
        try
        {
            l_service = (WEB3AdminInformSwElecDeliApplyService)Services.getService(
                WEB3AdminInformSwElecDeliApplyService.class);
        }
        //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g.execute�i�j���R�[�����A
        //�������ʂł��郌�X�|���X�I�u�W�F�N�g���擾����B
        try
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\����������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformAccSwElecDeliDeleteCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "�Ǘ��ҁE�����ؑցE�d�q��t�\����������Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
