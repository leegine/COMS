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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知ハンドラクラス(WEB3IfoExecuteEndNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/22 艾興 (中訊) 新規作成
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
 * (先物OP出来終了通知ハンドラ)<BR>
 * 先物OP出来終了通知ハンドラクラス<BR>
 * @@author  : 艾興
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
     * 株式指数オプション出来終了通知処理を実施する<BR>
     * <BR>
     * 株式指数オプション出来終了通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 株式指数オプション出来終了通知リクエストオブジェクト
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
                "先物OP出来終了通知ハンドラクラス。",
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
            log.error(l_request, "先物OP出来終了通知ハンドラクラス。", l_wbe);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
