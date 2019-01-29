head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物出来通知ハンドラクラス(WEB3FuturesOrderExecNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 艾興 (中訊) 新規作成
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物出来通知ハンドラ)<BR>
 * 株価指数先物出来通知ハンドラクラス<BR>
 */
public class WEB3FuturesOrderExecNotifyHandler implements MessageHandler
{
    /**
         * ログ出力ユーティリティ。<BR>
         */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyHandler.class);

    /**
     * @@roseuid 40F7B071003E
     */
    public WEB3FuturesOrderExecNotifyHandler()
    {

    }

    /**
     * (先物出来通知リクエスト)<BR>
     * 株価指数先物出来通知処理を行う。<BR>
     * <BR>
     * 株価指数先物出来通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数先物出来通知リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOrderExecNotifyResponse
     * @@roseuid 40A8411403DA
     */
    public WEB3FuturesOrderExecNotifyResponse futuresOrderExecNotifyRequest(WEB3FuturesOrderExecNotifyRequest l_request)
    {

        final String STR_METHOD_NAME =
            getClass().getName()
                + ".futuresOrderExecNotifyRequest(WEB3FuturesOrderExecNotifyRequest l_request)";

        log.debug(STR_METHOD_NAME);

        WEB3FuturesOrderExecNotifyResponse l_response = null;
        WEB3FuturesOrderExecNotifyService l_service = null;

        try
        {
            l_service =
                (WEB3FuturesOrderExecNotifyService)Services.getService(
                    WEB3FuturesOrderExecNotifyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物出来通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物出来通知に失敗しました。", l_ex);
            return l_response;
        }

        log.debug(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
