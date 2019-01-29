head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontSwitchOrderRouteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3AdminFrontSwitchOrderRouteHandler
                        (WEB3AdminFrontSwitchOrderRouteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.115
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�Ǘ��Ҕ�����ؑփn���h���N���XHandler�j<BR>
 * <BR>
 * �Ǘ��Ҕ�����ؑփn���h���N���X<BR>
 * <BR>
 * WEB3AdminFrontSwitchOrderRouteHandler class<BR>
 * <BR>
 * @@author SCS Sato
 * @@version 1.0
 */
public class WEB3AdminFrontSwitchOrderRouteHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFrontSwitchOrderRouteHandler.class);

    /**
     * @@roseuid 41FD965303D8
     */
    public WEB3AdminFrontSwitchOrderRouteHandler()
    {

    }

    /**
     * �iget���͉�ʁj<BR>
     * �Ǘ��Ҕ�����֑ؑI����ʕ\���������s���B<BR>
     * <BR>
     * �Ǘ��Ҕ�����ؑփT�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * (getSelectScreen)<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE������֑ؑI�����N�G�X�g ���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminFrontSwitchOrderRouteSelectRequest object<BR>
     * <BR>
     * @@return dirsec.message.WEB3AdminFrontSwitchOrderRouteSelectResponse
     * @@roseuid 419968CF00F9
     */
    public WEB3AdminSwitchOrderRouteSelectResponse getSelectScreen(
	WEB3AdminSwitchOrderRouteSelectRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSelectScreen(WEB3AdminSwitchOrderRouteSelectRequest)";
        log.entering(STR_METHOD_NAME);

		WEB3AdminSwitchOrderRouteSelectResponse l_response = null;
		WEB3AdminSwitchOrderRouteService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSwitchOrderRouteService) Services.getService(
			WEB3AdminSwitchOrderRouteService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminSwitchOrderRouteSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminSwitchOrderRouteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminSwitchOrderRouteSelectResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response =
                (WEB3AdminSwitchOrderRouteSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �Ǘ��Ҕ�����ؑ֊m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҕ�����ؑփT�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE������ؑ֊m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminSwitchOrderRouteComfirmRequest object<BR>
     * <BR>
     * @@return dirsec.message.WEB3AdminSwitchOrderRouteComfirmResponse
     * @@roseuid 4199693D0195
     */
    public WEB3AdminSwitchOrderRouteConfirmResponse validateOrderRouteChange(
				WEB3AdminSwitchOrderRouteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateOrderRouteChange(WEB3AdminSwitchOrderRouteComfirmRequest)";
        log.entering(STR_METHOD_NAME);

		WEB3AdminSwitchOrderRouteConfirmResponse l_response = null;
		WEB3AdminSwitchOrderRouteService l_service = null;
        try
        {
            l_service =
                (WEB3AdminSwitchOrderRouteService) Services.getService(
			WEB3AdminSwitchOrderRouteService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminSwitchOrderRouteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminSwitchOrderRouteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminSwitchOrderRouteConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateOrderRouteChange()", l_exp);
            l_response =
                (WEB3AdminSwitchOrderRouteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �Ǘ��Ҕ�����ؑ֊����������s���B<BR>
     * <BR>
     * �Ǘ��Ҕ�����ؑփT�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE������ؑ֊������N�G�X�g�I�u�W�F�N�g<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminSwitchOrderRouteCompleteRequest object<BR>
     * <BR>
     * @@return dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse
     * @@roseuid 419969600109
     */
    public WEB3AdminSwitchOrderRouteCompleteResponse submitOrderRouteChange(
        WEB3AdminSwitchOrderRouteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitOrderRouteChange(WEB3AdminSwitchOrderRouteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSwitchOrderRouteCompleteResponse l_response = null;
		WEB3AdminSwitchOrderRouteService l_service = null;
        try
        {
			l_service =
				(WEB3AdminSwitchOrderRouteService) Services.getService(
			WEB3AdminSwitchOrderRouteService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminSwitchOrderRouteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminSwitchOrderRouteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminSwitchOrderRouteCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitOrderRouteChange()", l_exp);
            l_response =
                (WEB3AdminSwitchOrderRouteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
