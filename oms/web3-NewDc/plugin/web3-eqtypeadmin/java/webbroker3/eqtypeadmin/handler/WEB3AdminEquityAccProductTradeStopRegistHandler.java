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
filename	WEB3AdminEquityAccProductTradeStopRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍڋq�����ʎ����~�o�^�n���h���N���X(WEB3AdminEquityAccProductTradeStopRegistHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopRegistService;

/**
 * �i�Ǘ��Ҍڋq�����ʎ����~�o�^�n���h���N���X�j<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopRegistHandler<BR>
 * <BR>
 * @@author Wanishree
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopRegistHandler implements MessageHandler
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopRegistHandler.class);
    /**
    * @@roseuid 41FD95F20128
    */
    public WEB3AdminEquityAccProductTradeStopRegistHandler()
    {

    }

    /**
     * �iget���͉�ʁj<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^���͉�ʕ\���������s���B<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �igetInputScreen�j<BR>
     * <BR>
     * Execute WEB3AdminPMAccProductTradeStopRegistService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopRegistServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistInputRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse
     * @@roseuid 419814560288
     */
    public WEB3AdminPMAccProductTradeStopRegistInputResponse getInputScreen
    (WEB3AdminPMAccProductTradeStopRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminPMAccProductTradeStopRegistInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccProductTradeStopRegistInputResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopRegistService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopRegistService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistInputResponse) l_request.createResponse();
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
                (WEB3AdminPMAccProductTradeStopRegistInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �iconfirm�o�^�j<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^�m�F�������s���B<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �iconfirmRegist�j<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService confirm process<BR>
     * <BR>
     * Acquire  WEB3AdminEquityAccProductTradeStopRegistServiceImpl, call execute()
     * method<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�ڋq�����ʎ����~�o�^�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistConfirmRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse
     * @@roseuid 419814C30334
     */
    public WEB3AdminPMAccProductTradeStopRegistConfirmResponse confirmRegist
    (WEB3AdminPMAccProductTradeStopRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmRegist(WEB3AdminPMAccProductTradeStopRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccProductTradeStopRegistConfirmResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopRegistService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopRegistService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while confirmRegist request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "confirmRegist Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �icomplete�o�^�j<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^�����������s���B<BR>
     * <BR>
     * �ڋq�����ʎ����~�o�^�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * �icompleteRegist�j<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopRegistService complete process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopRegistServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�ڋq�����ʎ����~�o�^�������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopRegistCompleteRequest object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse
     * @@roseuid 419814F702F5
     */
    public WEB3AdminPMAccProductTradeStopRegistCompleteResponse completeRegist
    (WEB3AdminPMAccProductTradeStopRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeRegist(WEB3AdminPMAccProductTradeStopRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccProductTradeStopRegistCompleteResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopRegistService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopRegistService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopRegistService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while completeRegist request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "completeRegist Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
}
@
