head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資注文受付ハンドラクラス (WEB3RuitoOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
/**
 * 累積投資注文受付ハンドラクラス<BR>
 */
public class WEB3RuitoOrderAcceptHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptHandler.class);
    /**
     * 累積投資注文受付処理を行う。<BR>
     * <BR>
     * 累積投資注文受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資注文受付リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse
     * @@roseuid 405A4A08027F
     */
    public WEB3RuitoOrderAcceptResponse orderAcceptRequest(
            WEB3RuitoOrderAcceptRequest request)        
    {
        final String STR_METHOD_NAME =
            "orderAcceptRequest(WEB3RuitoOrderAcceptRequest request)";
        log.entering(STR_METHOD_NAME);
        WEB3RuitoOrderAcceptResponse l_response = null;
        WEB3RuitoOrderAcceptService l_service = null;
        log.debug("entry if (request == null)");
        if (request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        log.debug("end if (request == null)");
        try
        {
            l_service =
                (WEB3RuitoOrderAcceptService) Services.getService(
                    WEB3RuitoOrderAcceptService.class);
            log.debug("l_service = " + l_service);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) request.createResponse();
            log.debug("l_ruitoOrderAcceptResponse =" + l_response);
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "累積投資注文受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) l_service.execute(request);
            log.debug("l_response = " + l_response);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3RuitoOrderAcceptResponse) request.createResponse();
            log.debug("l_ruitoOrderAcceptResponse =" + l_response);
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(request, "累積投資注文受付に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
