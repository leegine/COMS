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
filename	WEB3RuitoBuyOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文ハンドラクラス(WEB3RuitoBuyOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
/**
 * 累積投資買付注文ハンドラクラス<BR>
 */
public class WEB3RuitoBuyOrderHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderHandler.class);
    /**
     * 累積投資買付注文審査を行う。<BR>
     * <BR>
     * 累積投資買付注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資買付注文確認リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse
     * @@roseuid 4058292B039C
     */
    public WEB3RuitoBuyConfirmResponse confirmBuyOrder(WEB3RuitoBuyConfirmRequest request)
    {
        final String STR_METHOD_NAME =
            "confirmBuyOrder(WEB3RuitoBuyConfirmRequest request)";
        log.entering(STR_METHOD_NAME);

        if (request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        WEB3RuitoBuyOrderService l_service = null;
        WEB3RuitoBuyConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3RuitoBuyOrderService) Services.getService(
                    WEB3RuitoBuyOrderService.class);
        }
        catch (Exception l_exp)
        {
            l_response = (WEB3RuitoBuyConfirmResponse) request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                request,
                "累積投資注文買付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoBuyConfirmResponse) l_service.execute(request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoBuyConfirmResponse) request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(request, "累積投資累投買付注文に失敗しました", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    /**
     * 累積投資買付注文登録を行う。<BR>
     * <BR>
     * 累積投資買付注文サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資買付注文完了リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse
     * @@roseuid 40582931035D
     */
    public WEB3RuitoBuyCompleteResponse submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "submitBuyOrder(WEB3RuitoBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        WEB3RuitoBuyOrderService l_service = null;
        WEB3RuitoBuyCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3RuitoBuyOrderService) Services.getService(
                    WEB3RuitoBuyOrderService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "累積投資買付注文サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3RuitoBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "累積投資買付注文に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
