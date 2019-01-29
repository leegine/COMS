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
filename	WEB3AdminEquityAccProductTradeStopDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��Ҍڋq�����ʎ����~�폜�n���h���N���X)
                        (WEB3AdminEquityAccProductTradeStopDeleteHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopDeleteService;
/**
 * �i�Ǘ��Ҍڋq�����ʎ����~�폜�n���h���N���X�j<BR>
 * <BR>
 * �Ǘ��Ҍڋq�����ʎ����~�폜�n���h���N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopDeleteHandler class<BR>
 * <BR>
 * @@author Rajesh Sharma and Shruti
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopDeleteHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopDeleteHandler.class);

    /**
     * @@roseuid 41FD94B201D4
     */
    public WEB3AdminEquityAccProductTradeStopDeleteHandler()
    {

    }

    /**
     * �iconfirm�폜�j<BR>
     * <BR>
     * �ڋq�����ʎ����~�폜�m�F�������s���B<BR>
     * <BR>
     * �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (confirmDelete)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopDeleteService confirm process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopDeleteServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�ڋq�����ʎ����~�폜�m�F���X�|���X�I�u�W�F�N�g<BR>
     * <BR>
     * -------<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopDeleteConfirmResponse object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse
     * @@roseuid 4199852F02A7
     */
    public WEB3AdminPMAccProductTradeStopDeleteConfirmResponse confirmDelete
        (WEB3AdminPMAccProductTradeStopDeleteConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmDelete(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopDeleteConfirmResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopDeleteService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopDeleteService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopDeleteService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopDeleteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteConfirmResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access confirmDelete()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * �icomplete�폜�j<BR>
     * <BR>
     * �ڋq�����ʎ����~�폜�����������s���B<BR>
     * <BR>
     * �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�XImpl���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * (completeDelete)<BR>
     * <BR>
     * Execute WEB3AdminEquityAccProductTradeStopDeleteService complete process<BR>
     * <BR>
     * Acquire WEB3AdminEquityAccProductTradeStopDeleteServiceImpl, and call execute()
     * method.<BR>
     * <BR>
     * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
     * <BR>
     * �Ǘ��ҁE�ڋq�����ʎ����~�폜�������X�|���X�I�u�W�F�N�g<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminPMAccProductTradeStopDeleteCompleteResponse object<BR>
     * <BR>
     * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse
     * @@roseuid 4199858D01EB
     */
    public WEB3AdminPMAccProductTradeStopDeleteCompleteResponse completeDelete
        (WEB3AdminPMAccProductTradeStopDeleteCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeDelete(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopDeleteCompleteResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopDeleteService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopDeleteService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopDeleteService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopDeleteServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteCompleteResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access completeDelete()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopDeleteCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
