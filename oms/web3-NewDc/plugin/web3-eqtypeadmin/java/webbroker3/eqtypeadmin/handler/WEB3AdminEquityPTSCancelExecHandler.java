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
filename	WEB3AdminEquityPTSCancelExecHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�����iPTS�j�o������n���h��(WEB3AdminEquityPTSCancelExecHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ���n(���u) �V�K�쐬���f��178
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�����iPTS�j�o������n���h��)<BR>
 * �Ǘ��ҁE�����iPTS�j�o������n���h���N���X<BR>
 * <BR>
 * @@author ���n
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecHandler.class);

    /**
     * @@roseuid 4795B086004E
     */
    public WEB3AdminEquityPTSCancelExecHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * �����iPTS�j�o��������͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o������T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminEquityPTSCancelExecInputResponse
     * @@roseuid 4769BFC3035B
     */
    public WEB3AdminEquityPTSCancelExecInputResponse getInputScreen(
        WEB3AdminEquityPTSCancelExecInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSCancelExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecInputResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service = (WEB3AdminEquityPTSCancelExecService)Services.getService(
                WEB3AdminEquityPTSCancelExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o������T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate�o�����)<BR>
     * �����iPTS�j�o������m�F�������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o������T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminEquityPTSCancelExecConfirmResponse
     * @@roseuid 4769C09501C8
     */
    public WEB3AdminEquityPTSCancelExecConfirmResponse validateCancelExec(
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateCancelExec(WEB3AdminEquityPTSCancelExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecConfirmResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o������T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit�o�����)<BR>
     * �����iPTS�j�o����������������s���B<BR>
     * <BR>
     * �Ǘ��ҁE�����iPTS�j�o������T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * @@return WEB3AdminEquityPTSCancelExecCompleteResponse
     * @@roseuid 4769C0A900FD
     */
    public WEB3AdminEquityPTSCancelExecCompleteResponse submitCancelExec(
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitCancelExec(WEB3AdminEquityPTSCancelExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecCompleteResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�����iPTS�j�o������T�[�r�X�̎擾�Ɏ��s���܂����B ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "�Ǘ��ҁE�����iPTS�j�o������̏����̎��{�Ɏ��s���܂����B",
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
