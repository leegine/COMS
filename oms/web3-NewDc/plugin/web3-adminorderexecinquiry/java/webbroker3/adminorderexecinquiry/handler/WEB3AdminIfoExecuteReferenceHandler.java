head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminIfoExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ����������Ɖ�n���h��(WEB3AdminIfoExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFutOpOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminIfoExecuteReferenceService;

/**
 * (�Ǘ��Ҋ����������Ɖ�n���h��)<BR>
 * <BR>
 * �Ǘ��Ҋ����������Ɖ�n���h���N���X<BR>
 * <BR>
 * WEB3AdminIfoExecuteReferenceHandler class<BR>
 * <BR>
 * @@author Anupama
 * @@version 1.0
 */
public class WEB3AdminIfoExecuteReferenceHandler implements MessageHandler
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoExecuteReferenceHandler.class);

    /**
    * @@roseuid 41FA325E0262
    */
    public WEB3AdminIfoExecuteReferenceHandler()
    {
    }

    /**
     * (get���͉��)<BR>
     * <BR>
     * �����������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҋ����������Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     *Execute WEB3AdminIfoExecuteReferenceService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminIfoExecuteReferenceServiceImpl, and call execute() method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�����������Ɖ���̓��N�G�X�g�N���X<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORFutOpOrderExecutionRefInputRequest<BR>
     * <BR>
     * @@return WEB3AdminORFutOpOrderExecutionRefInputResponse
     * @@roseuid 41A585830139
     */
    public WEB3AdminORFutOpOrderExecutionRefInputResponse getInputScreen(
        WEB3AdminORFutOpOrderExecutionRefInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminORFutOpOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoExecuteReferenceService l_service = null;
        WEB3AdminORFutOpOrderExecutionRefInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminIfoExecuteReferenceService) Services.getService(
                    WEB3AdminIfoExecuteReferenceService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getInputScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefInputResponse) l_service.execute(l_request);

        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getInputScreen Request failed", l_exp);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
    * (get���͉��)<BR>
    * <BR>
    * �����������Ɖ���͉�ʕ\���������s���B<BR>
    * <BR>
    * �Ǘ��Ҋ����������Ɖ�T�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B<BR>
    * <BR>
    * -----<English>---------------<BR>
    * <BR>
    * getReferenceScreen<BR>
    * <BR>
    * Execute WEB3AdminIfoExecuteReferenceService process<BR>
    * <BR>
    * Acquire WEB3AdminIfoExecuteReferenceServiceImpl, and call execute() method<BR>
    * <BR>
    * @@param l_request - (���N�G�X�g�f�[�^)<BR>
    * <BR>
    * �Ǘ��ҁE�����������Ɖ���̓��N�G�X�g�N���X<BR>
    * <BR>
    * -----<English>---------------<BR>
    * <BR>
    * l_request<BR>
    * <BR>
    * WEB3AdminORFutOpOrderExecutionRefReferenceRequest<BR>
    * <BR>
    * @@return WEB3AdminORFutOpOrderExecutionRefReferenceResponse
    * @@roseuid 41A585830139
    */

    public WEB3AdminORFutOpOrderExecutionRefReferenceResponse getReferenceScreen(
        WEB3AdminORFutOpOrderExecutionRefReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORFutOpOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoExecuteReferenceService l_service = null;
        WEB3AdminORFutOpOrderExecutionRefReferenceResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminIfoExecuteReferenceService) Services.getService(
                    WEB3AdminIfoExecuteReferenceService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getReferenceScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefReferenceResponse) l_service.execute(l_request);

        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminORFutOpOrderExecutionRefReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getReferenceScreen Request failed", l_exp);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
