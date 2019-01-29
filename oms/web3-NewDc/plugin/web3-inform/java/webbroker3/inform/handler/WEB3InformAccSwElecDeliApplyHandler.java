head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAccSwElecDeliApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ؑցE�d�q��t�\���n���h��(WEB3InformAccSwElecDeliApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 �h�C(���u) �V�K�쐬 �d�l�ύX���f��098
*/
package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����ؑցE�d�q��t�\���n���h��)<BR>
 * �����ؑցE�d�q��t�\���n���h��<BR>
 * <BR>
 * @@author �h�C(���u)
 * @@version 1.0
 */
public class WEB3InformAccSwElecDeliApplyHandler implements MessageHandler
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3InformAccSwElecDeliApplyHandler.class);

    /**
     * @@roseuid 4663A9D502AF
     */
    public WEB3InformAccSwElecDeliApplyHandler()
    {

    }

    /**
     * (���͉�ʕ\��)<BR>
     * �����ؑցE�d�q��t�\�����͉�ʕ\���������s���B  <BR>
     * <BR>
     * �����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3InformAccSwElecDeliApplyInpResponse
     */
    public WEB3InformAccSwElecDeliApplyInpResponse displayInputScreen(
        WEB3InformAccSwElecDeliApplyInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3InformAccSwElecDeliApplyInpRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyInpResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //�����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�������͂����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�\���m�F)<BR>
     * �����ؑցE�d�q��t�\���m�F�������s���B  <BR>
     * <BR>
     * �����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B  <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3InformAccSwElecDeliApplyConfResponse
     */
    public WEB3InformAccSwElecDeliApplyConfResponse applyConfirm(
        WEB3InformAccSwElecDeliApplyConfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "applyConfirm(WEB3InformAccSwElecDeliApplyConfRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyConfResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //�����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�\���m�F�����s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }

    /**
     * (�\������)<BR>
     * �����ؑցE�d�q��t�\�������������s���B  <BR>
     * <BR>
     * �����ؑցE�d�q��t�\���T�[�r�X���擾���Aexecute()���\�b�h���R�[������B  <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3InformAccSwElecDeliApplyCmpResponse
     */
    public WEB3InformAccSwElecDeliApplyCmpResponse applyComplete(
        WEB3InformAccSwElecDeliApplyCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "applyComplete(WEB3InformAccSwElecDeliApplyCmpRequest)";
        log.exiting(STR_METHOD_NAME);

        WEB3InformAccSwElecDeliApplyCmpResponse l_response = null;
        WEB3InformAccSwElecDeliApplyService l_service = null;

        //�����ؑցE�d�q��t�\���T�[�r�X�I�u�W�F�N�g���擾
        try
        {
            l_service = (WEB3InformAccSwElecDeliApplyService)Services.getService(
                WEB3InformAccSwElecDeliApplyService.class);
        }
        catch (Exception l_ex)
        {
            //�T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
            l_response = (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�����ؑցE�d�q��t�\���T�[�r�X���擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�\�����������s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        //���X�|���X�I�u�W�F�N�g��ԋp����B
        return l_response;
    }
}
@
