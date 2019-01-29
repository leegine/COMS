head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminEquityExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҋ����������Ɖ�n���h��(WEB3AdminEquityExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOREquityOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminEquityExecuteReferenceService;

/**
 * (�Ǘ��Ҋ����������Ɖ�n���h��)<BR>
 * <BR>
 * �Ǘ��Ҋ����������Ɖ�n���h���N���X<BR>
 * <BR>
 * WEB3AdminEquityExecuteReferenceHandler class<BR>
 * <BR>
 *<BR>
 * @@author Wanishree
 * @@version 1.0
 */
public class WEB3AdminEquityExecuteReferenceHandler implements MessageHandler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityExecuteReferenceHandler.class);

    /**
    * @@roseuid 4212FAAF0392
    */
    public WEB3AdminEquityExecuteReferenceHandler()
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
     * Execute WEB3AdminEquityExecuteReferenceService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminEquityExecuteReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�����������Ɖ���̓��N�G�X�g�N���X<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOREquityOrderExecutionRefInputRequest<BR>
     * <BR>
     * @@return WEB3AdminOREquityOrderExecutionRefInputResponse
     * @@roseuid 41A585830139
     */
    public WEB3AdminOREquityOrderExecutionRefInputResponse getInputScreen
    (WEB3AdminOREquityOrderExecutionRefInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminOREquityOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityExecuteReferenceService l_service = null;
        WEB3AdminOREquityOrderExecutionRefInputResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminEquityExecuteReferenceService) Services.getService(
                    WEB3AdminEquityExecuteReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminOREquityOrderExecutionRefInputResponse) l_request.createResponse();
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
                (WEB3AdminOREquityOrderExecutionRefInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminOREquityOrderExecutionRefInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�Ɖ���)<BR>
     * <BR>
     * �����������Ɖ�����s���B<BR>
     * <BR>
     * �Ǘ��Ҋ����������Ɖ�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminEquityExecuteReferenceService process<BR>
     * <BR>
     * Acquire WEB3AdminEquityExecuteReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE�����������Ɖ�N�G�X�g�N���X<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminOREquityOrderExecutionRefReferenceRequest<BR>
     * <BR>
     * @@return WEB3AdminOREquityOrderExecutionRefReferenceResponse
     * @@roseuid 41A5BFD5021D
     */
    public WEB3AdminOREquityOrderExecutionRefReferenceResponse getReferenceScreen
    (WEB3AdminOREquityOrderExecutionRefReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminOREquityOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityExecuteReferenceService l_service = null;
        WEB3AdminOREquityOrderExecutionRefReferenceResponse l_response = null;

        try
        {
            l_service =
                (WEB3AdminEquityExecuteReferenceService) Services.getService(
                    WEB3AdminEquityExecuteReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminOREquityOrderExecutionRefReferenceResponse) l_request.createResponse();
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
                (WEB3AdminOREquityOrderExecutionRefReferenceResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminOREquityOrderExecutionRefReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getReferenceScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
