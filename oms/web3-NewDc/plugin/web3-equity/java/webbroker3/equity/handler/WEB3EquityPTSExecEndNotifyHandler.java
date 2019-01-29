head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)株式出来終了通知ハンドラ(WEB3EquityPTSExecEndNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 趙林鵬(中訊) 新規作成 モデルNo.1285
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequest;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)株式出来終了通知ハンドラ)<BR>
 * (PTS)株式出来終了通知ハンドラ<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyHandler implements MessageHandler
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyHandler.class);

    /**
     * @@roseuid 40B28FD9022A
     */
    public WEB3EquityPTSExecEndNotifyHandler()
    {

    }

    /**
     * (complete通知)<BR>
     * (PTS)株式出来終了通知処理を行う。<BR>
     * <BR>
     * (PTS)株式出来終了通知サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * (PTS)株式出来終了通知リクエストデータオブジェクト<BR>
     * @@return WEB3EquityPTSExecEndNotifyResponse
     * @@roseuid 4056E82202D6
     */
    public WEB3EquityPTSExecEndNotifyResponse completeNotify(WEB3EquityPTSExecEndNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "completeNotify(WEB3EquityPTSExecEndNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityPTSExecEndNotifyResponse l_response = null;
        WEB3EquityPTSExecEndNotifyService l_service = null;

        try
        {
            l_service = (WEB3EquityPTSExecEndNotifyService)Services.getService(
                WEB3EquityPTSExecEndNotifyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(PTS)株式出来終了通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(PTS)株式出来終了通知処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3EquityPTSExecEndNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "(PTS)株式出来終了通知処理が失敗しました。",
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
