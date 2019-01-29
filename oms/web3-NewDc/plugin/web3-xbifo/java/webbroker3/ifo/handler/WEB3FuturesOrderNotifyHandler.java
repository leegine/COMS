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
filename	WEB3FuturesOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文通知ハンドラ(WEB3FuturesOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyService;
import webbroker3.ifo.message.WEB3FuturesOrderNotifyResponse;
import webbroker3.ifo.message.WEB3FuturesOrderNotifyRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物注文通知ハンドラ)<BR>
 * 株価指数先物注文通知ハンドラクラス<BR>
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyHandler.class);
   
    /**
     * @@roseuid 41AD6546029F
     */
    public WEB3FuturesOrderNotifyHandler() 
    {
    
    }
   
    /**
     * (先物注文通知リクエスト)<BR>
     * 株価指数先物注文通知処理を行う。<BR>
     * <BR>
     * 株価指数先物注文通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物注文通知リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOrderNotifyResponse
     * @@roseuid 417501300079
     */
    public WEB3FuturesOrderNotifyResponse futuresOrderNotifyRequest(WEB3FuturesOrderNotifyRequest l_request) 
    {
        final String METHOD_NAME = "futuresOrderNotifyRequest()";
        log.entering(METHOD_NAME);

        WEB3FuturesOrderNotifyResponse l_response = null;
        WEB3FuturesOrderNotifyService l_service = null;
        
        //先物注文通知サービスを取得
        try
        {
            l_service =
                (WEB3FuturesOrderNotifyService)Services.getService(
                    WEB3FuturesOrderNotifyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物注文通知サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //先物注文通知サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物注文通知に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
