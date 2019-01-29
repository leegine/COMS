head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒm�n���h���N���X(WEB3IfoExecuteEndNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/22 䈋� (���u) �V�K�쐬
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyService;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyResponse;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�o���I���ʒm�n���h��)<BR>
 * �敨OP�o���I���ʒm�n���h���N���X<BR>
 * @@author  : 䈋�
 * @@version : 1.0
 */
public class WEB3IfoExecuteEndNotifyHandler implements MessageHandler
{
    /**
     * @@roseuid 40C0755600DA
     */
    public WEB3IfoExecuteEndNotifyHandler()
    {

    }
    /**
     * Logger
     */
    public static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecuteEndNotifyHandler.class);

    /**
     * �����w���I�v�V�����o���I���ʒm���������{����<BR>
     * <BR>
     * �����w���I�v�V�����o���I���ʒm�T�[�r�X���擾���A<BR>
     * execute()���\�b�h���R�[������B<BR>
     * @@param l_request - �����w���I�v�V�����o���I���ʒm���N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3IfoExecEndNotifyResponse
     * @@roseuid 4057BB120179
     */
    public WEB3IfoExecEndNotifyResponse executeEndNotifyRequest(WEB3IfoExecEndNotifyRequest l_request)
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + "executeEndNotifyRequest(WEB3IfoExecEndNotifyRequest l_request) ";
        log.entering(STR_METHOD_NAME);

        WEB3IfoExecuteEndNotifyService l_endNotifyService = null;
        WEB3IfoExecEndNotifyResponse l_response = null;
        try
        {
            log.debug(
                ">>Services.getService(WEB3IfoExecuteEndNotifyService.class);  >>start");
            l_endNotifyService =
                (WEB3IfoExecuteEndNotifyService)Services.getService(
                    WEB3IfoExecuteEndNotifyService.class);
            log.debug(
                ">>Services.getService(WEB3IfoExecuteEndNotifyService.class);  >>"
                    + l_endNotifyService.toString());
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3IfoExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "�敨OP�o���I���ʒm�n���h���N���X�B",
                l_response.errorInfo,
                l_exp);
            return l_response;
        }
        try
        {
            log.debug("create response>>start");
            l_response =
                (WEB3IfoExecEndNotifyResponse)l_endNotifyService.execute(
                    l_request);
            log.debug("create response>>" + l_response.toString());
        }
        catch (WEB3BaseException l_wbe)
        {
            l_response =
                (WEB3IfoExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_wbe.getErrorInfo();
            log.error(l_request, "�敨OP�o���I���ʒm�n���h���N���X�B", l_wbe);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
