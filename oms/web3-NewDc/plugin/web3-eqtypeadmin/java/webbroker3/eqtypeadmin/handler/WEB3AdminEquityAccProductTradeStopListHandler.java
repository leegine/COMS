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
filename	WEB3AdminEquityAccProductTradeStopListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�Ǘ��Ҍڋq�����ʎ����~�ꗗ�n���h���N���X)
                        (WEB3AdminEquityAccProductTradeStopListHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;
/**
 * �i�Ǘ��Ҍڋq�����ʎ����~�ꗗ�n���h���N���X�j<BR>
 * <BR>
 * �Ǘ��Ҍڋq�����ʎ����~�ꗗ�n���h���N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminEquityAccProductTradeStopListHandler class<BR>
 * <BR>
 * @@author Vijay Kumar
 * @@version 1.0
 */
public class WEB3AdminEquityAccProductTradeStopListHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAccProductTradeStopListHandler.class);

    /**
     * @@roseuid 41FD9445006D
     */
    public WEB3AdminEquityAccProductTradeStopListHandler()
    {

    }

   /**
    * �iget�ꗗ��ʁj<BR>
    * <BR>
    * �ڋq�����ʎ����~�ꗗ�\���������s���B<BR>
    * <BR>
    * �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�XImpl���擾���A<BR>
    * execute()���\�b�h���R�[������B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * (getListScreen)<BR>
    * <BR>
    * Execute WEB3AdminEquityAccProductTradeStopListService screen process<BR>
    * <BR>
    * Acquire WEB3AdminEquityAccProductTradeStopListServiceImpl, and call execute()
    * method.<BR>
    * <BR>
    * @@param l_request - �i���N�G�X�g�f�[�^�j<BR>
    * <BR>
    * �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
    * <BR>
    * WEB3AdminPMAccProductTradeStopListRequest object<BR>
    * <BR>
    * @@return eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse
    * @@roseuid 41987E36014F
    */
    public WEB3AdminPMAccProductTradeStopListResponse
                 getListScreen(WEB3AdminPMAccProductTradeStopListRequest l_request)
    {
        final String STR_METHOD_NAME =
           "getListScreen(WEB3AdminPMAccProductTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminPMAccProductTradeStopListResponse l_response = null;
        WEB3AdminEquityAccProductTradeStopListService l_service = null;

        try
        {
            l_service =
                (WEB3AdminEquityAccProductTradeStopListService) Services.getService(
                    WEB3AdminEquityAccProductTradeStopListService.class);

        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminEquityAccProductTradeStopListServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response =
                (WEB3AdminPMAccProductTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
   }
}
@
