head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleOrderApproveHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�������ω��������F�^�񏳔F�n���h��(WEB3AdminEquityForcedSettleOrderApproveHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����F (���u) �V�K�쐬 �d�l�ύX���f��No.129
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�������ω��������F�^�񏳔F�n���h��)<BR>
 * �Ǘ��ҁE�������ω��������F�^�񏳔F�n���h���N���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveHandler.class);

    /**
     * @@roseuid 462CA4170285
     */
    public WEB3AdminEquityForcedSettleOrderApproveHandler()
    {

    }

    /**
     * (confirm���F�^�񏳔F)<BR>
     * �������ω��������F�^�񏳔F�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse
     * @@roseuid 460323A4002E
     */
    public WEB3AdminForcedSettleApproveConfirmResponse confirmApprove(
        WEB3AdminForcedSettleApproveConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmApprove(WEB3AdminForcedSettleApproveConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveConfirmResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "�������ω��������F�^�񏳔F�m�F�����̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (run���F�^�񏳔F)<BR>
     * �������ω��������F�^�񏳔F�����N�����s���B<BR>
     * <BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveLapseRunResponse
     * @@roseuid 460323A4004E
     */
    public WEB3AdminForcedSettleApproveRunResponse runApprove(
        WEB3AdminForcedSettleApproveRunRequest l_request)
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveRunRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveRunResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "�������ω��������F�^�񏳔F�����̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�����X�e�[�^�X)<BR>
     * �������ω��������F�^�񏳔F�̏����X�e�[�^�X���m�F����B<BR>
     * <BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�XImpl��<BR>
     * �擾���Aexecute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveLapseStatusResponse
     * @@roseuid 460323A400CB
     */
    public WEB3AdminForcedSettleApproveStatusResponse confirmStatus(
        WEB3AdminForcedSettleApproveStatusRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmStatus(WEB3AdminForcedSettleApproveStatusRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveStatusResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "�������ω��������F�^�񏳔F�̏����̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
