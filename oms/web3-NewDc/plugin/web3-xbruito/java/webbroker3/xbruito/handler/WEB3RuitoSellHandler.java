head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資解約ハンドラ(WEB3RuitoSellHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellService;

/**
 * 累積投資解約ハンドラクラス<BR>
 */
public class WEB3RuitoSellHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellHandler.class);

    /**
     * 累積投資解約審査を行う。<BR>
     * <BR>
     * 累積投資解約サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資解約確認リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse
     * @@roseuid 4058185800DD
     */
    public WEB3RuitoSellConfirmResponse confirmSell(
            WEB3RuitoSellConfirmRequest l_request)
    {
		final String STR_METHOD_NAME = "confirmSell(WEB3RuitoSellConfirmRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }

        WEB3RuitoSellService l_service = null;
        WEB3RuitoSellConfirmResponse l_response = null;

        l_service =
            (WEB3RuitoSellService) Services.getService(WEB3RuitoSellService.class);

        try
        {
            l_response = (WEB3RuitoSellConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoSellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "累積投資解約審査処理が失敗しました。", e.getErrorInfo(), e);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 累積投資解約登録を行う。<BR>
     * <BR>
     * 累積投資解約サービスを取得し、execute()メソッドをコールする。<BR>
     *
     * @@param l_request - 累積投資解約完了リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse
     * @@roseuid 4058185D009E
     */
    public WEB3RuitoSellCompleteResponse completeSell(
            WEB3RuitoSellCompleteRequest l_request)
    {
		final String STR_METHOD_NAME = "completeSell(WEB3RuitoSellCompleteRequest l_request)";
		log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }

        WEB3RuitoSellService l_service = null;
        WEB3RuitoSellCompleteResponse l_response = null;

        l_service =
            (WEB3RuitoSellService) Services.getService(WEB3RuitoSellService.class);

        try
        {
            l_response = (WEB3RuitoSellCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoSellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "累積投資解約登録処理が失敗しました。", e.getErrorInfo(), e);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
