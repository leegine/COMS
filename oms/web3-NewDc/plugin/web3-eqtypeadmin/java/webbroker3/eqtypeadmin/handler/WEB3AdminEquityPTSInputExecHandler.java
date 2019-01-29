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
filename	WEB3AdminEquityPTSInputExecHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o�����̓n���h���iWEB3AdminEquityPTSInputExecHandler.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 ���� (���u) �V�K�쐬���f��177
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����iPTS�j�o�����̓n���h��)<BR>
 * �Ǘ��ҁE�����iPTS�j�o�����̓n���h���N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecHandler.class);

    /**
     * @@roseuid 4795A0F703AD
     */
    public WEB3AdminEquityPTSInputExecHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * �����iPTS�j�o�����͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminEquityPTSInputExecInputResponse
     * @@roseuid 477219EC02BA
     */
    public WEB3AdminEquityPTSInputExecInputResponse getInputScreen(
        WEB3AdminEquityPTSInputExecInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecInputResponse l_response = null;
        try
        {
            // �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�I�u�W�F�N�g���擾
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        // execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͉�ʕ\�������̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }

    /**
     * (validate�o������)<BR>
     * �����iPTS�j�o�����͊m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminEquityPTSInputExecConfirmResponse
     * @@roseuid 477219F3028C
     */
    public WEB3AdminEquityPTSInputExecConfirmResponse validateInputExec(
        WEB3AdminEquityPTSInputExecConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecConfirmResponse l_response = null;
        try
        {
            // �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�I�u�W�F�N�g���擾
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        // execute()���\�b�h���R�[������
        try
        {
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊m�F�����̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊m�F�����̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }

    /**
     * (submit�o������)<BR>
     * �����iPTS�j�o�����͊����������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3AdminEquityPTSInputExecCompleteResponse
     * @@roseuid 477219FD0098
     */
    public WEB3AdminEquityPTSInputExecCompleteResponse submitInputExec(
        WEB3AdminEquityPTSInputExecCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecCompleteResponse l_response = null;
        try
        {
            // �Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�I�u�W�F�N�g���擾
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            // execute()���\�b�h���R�[������
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊��������̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X�̊����iPTS�j�o�����͊��������̎��{�Ɏ��s���܂����B", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }
}
@
