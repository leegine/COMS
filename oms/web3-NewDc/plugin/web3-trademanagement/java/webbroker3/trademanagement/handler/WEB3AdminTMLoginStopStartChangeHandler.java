head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginStopStartChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃��O�C����~�ĊJ�ύX�n���h���N���X(WEB3AdminTMLoginStopStartChangeHandler.java)
Author Name      : Daiwa Institute of Research
*/

package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginStopStartChangeService;

/**
 * (�Ǘ��҃��O�C����~�ĊJ�ύX�n���h��)<BR>
 * <BR>
 * (�Ǘ��҃��O�C����~�ĊJ�ύX�n���h���N���X)<BR>
 * <BR>
 * WEB3AdminTMLoginStopStartChangeHandler<BR>
 * <BR>
 * WEB3AdminTMLoginStopStartChangeHandler class<BR>
 * <BR>
 * @@author Umadevi
 * @@version 1.0
 */
public class WEB3AdminTMLoginStopStartChangeHandler implements MessageHandler
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginStopStartChangeHandler.class);
    /**
     * @@roseuid 41DD3D80036C
     */
    public WEB3AdminTMLoginStopStartChangeHandler()
    {

    }

    /**
     * (get�ύX���͉��)<BR>
     * <BR>
     * ���O�C����~�ĊJ�ύX���͉�ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * ----<English>-------------<BR>
     * <BR>
     * getChangeInputScreen<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminTMLoginStopStartChangeServiceImpl, and call execute()<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���O�C����~�ĊJ�ύX���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgInputRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgInputResponse
     * @@roseuid 41774472013D
     */
    public WEB3AdminTMLStopStartChgInputResponse
        getChangeInputScreen(WEB3AdminTMLStopStartChgInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getChangeInputScreen(WEB3AdminTMLStopStartChgInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTMLStopStartChgInputResponse l_response = null;
        WEB3AdminTMLoginStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginStopStartChangeService) Services.getService(
                    WEB3AdminTMLoginStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMLStopStartChgInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getChangeInputScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMLStopStartChgInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response = (WEB3AdminTMLStopStartChgInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getChangeInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm�ύX)<BR>
     * <BR>
     * ���O�C����~�ĊJ�ύX�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * confirmChange<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService confirm process<BR>
     * <BR>
     * Acquire WEB3AdminTMLoginStopStartChangeServiceImpl, and call execute()<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgConfirmRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgConfirmResponse
     * @@roseuid 41774472015C
     */
    public WEB3AdminTMLStopStartChgConfirmResponse
        confirmChange(WEB3AdminTMLStopStartChgConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChange(WEB3AdminTMLStopStartChgConfirmRequest";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTMLStopStartChgConfirmResponse l_response = null;
        WEB3AdminTMLoginStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginStopStartChangeService) Services.getService(
                    WEB3AdminTMLoginStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMLStopStartChgConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while confirmChange request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMLStopStartChgConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "confirmChange Request failed", l_exp);
            l_response = (WEB3AdminTMLStopStartChgConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete�ύX)<BR>
     * <BR>
     * ���O�C����~�ĊJ�ύX�����������s���B<BR>
     * <BR>
     * �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * -----<English>--------------<BR>
     * <BR>
     * completeChange<BR>
     * <BR>
     * Execute WEB3AdminTMLoginStopStartChangeService complete process<BR>
     * <BR>
     * Acquire WEB3AdminTMLoginStopStartChangeServiceImpl, and call execute() <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * <BR>
     * �Ǘ��ҁE���O�C����~�ĊJ�ύX�������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMLStopStartChgCompleteRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.trademnagement.message.WEB3AdminTMLStopStartChgCompleteResponse
     * @@roseuid 41774472016C
     */
    public WEB3AdminTMLStopStartChgCompleteResponse
        completeChange(WEB3AdminTMLStopStartChgCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChange(WEB3AdminTMLStopStartChgCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTMLStopStartChgCompleteResponse l_response = null;
        WEB3AdminTMLoginStopStartChangeService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginStopStartChangeService) Services.getService(
                    WEB3AdminTMLoginStopStartChangeService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMLStopStartChgCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while completeChange request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMLStopStartChgCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "completeChange Request failed", l_exp);
            l_response = (WEB3AdminTMLStopStartChgCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
