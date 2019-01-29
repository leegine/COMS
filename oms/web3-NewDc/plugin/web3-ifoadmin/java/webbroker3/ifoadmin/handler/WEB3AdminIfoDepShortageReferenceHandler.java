head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�؋����s���󋵏Ɖ�n���h��(WEB3AdminIfoDepShortageReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 ���ʗ�(���u) �V�K�쐬 ���f��No.005
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҁE�؋����s���󋵏Ɖ�n���h��)<BR>
 * �Ǘ��ҁE�؋����s���󋵏Ɖ�n���h���N���X<BR>
 * <BR>
 * @@author ���ʗ�(���u)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceHandler.class);

    /**
     * @@roseuid 49A74856037A
     */
    public WEB3AdminIfoDepShortageReferenceHandler()
    {

    }

    /**
     * (get���͉��)<BR>
     * �Ǘ��ҏ؋����s���󋵏Ɖ���͉�ʕ\���������s���B <BR>
     * <BR>
     * �Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageRefInputResponse
     * @@roseuid 498FD72102E3
     */
    public WEB3AdminIfoDepShortageRefInputResponse getInputScreen(
        WEB3AdminIfoDepShortageRefInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminIfoDepShortageRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoDepShortageRefInputResponse l_response = null;
        WEB3AdminIfoDepShortageReferenceService l_service = null;

        try
        {
            //�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�XImpl���擾��
            l_service = (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                WEB3AdminIfoDepShortageReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҏ؋����s���󋵏Ɖ���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҏ؋����s���󋵏Ɖ���͉�ʕ\�����������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * �Ǘ��ҏ؋����s���󋵏Ɖ�����s�� <BR>
     * <BR>
     * �Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�XImpl���擾���A <BR>
     * execute()���\�b�h���R�[������B <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminIfoDepShortageReferenceResponse
     * @@roseuid 498FD73102C3
     */
    public WEB3AdminIfoDepShortageReferenceResponse getReferenceScreen(
        WEB3AdminIfoDepShortageReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoDepShortageReferenceResponse l_response = null;
        WEB3AdminIfoDepShortageReferenceService l_service = null;

        try
        {
            //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X���擾��
            l_service = (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                    WEB3AdminIfoDepShortageReferenceService.class);
        }
        // �T�[�r�X�ŗ�O�����������ꍇ�́A�G���[�������X�|���X���b�Z�[�W�ɐݒ肷��
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�Ǘ��ҁE�؋����s���󋵏Ɖ�T�[�r�X�̎擾�Ɏ��s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()���\�b�h���R�[������B
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҏ؋����s���󋵏Ɖ�������s���܂����B",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "�Ǘ��ҏ؋����s���󋵏Ɖ�������s���܂����B",
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
