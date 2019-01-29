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
filename	WEB3RuitoMRFCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF取消受付ハンドラクラス(WEB3RuitoMRFCancelAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * 累積投資MRF取消受付ハンドラクラス<BR>
 */
public class WEB3RuitoMRFCancelAcceptHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptHandler.class);

    /**
     * 累積投資MRF取消受付処理を行う。<BR>
     * <BR>
     * 累積投資MRF取消受付サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資MRF取消受付リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse
     * @@roseuid 405812600235
     */
    public WEB3RuitoMRFCancelAcceptResponse mrfCancelAcceptRequest(
            WEB3RuitoMRFCancelAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "mrfCancelAcceptRequest(" +
            "WEB3RuitoMRFCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        /**
         * ログ出力ユーティリティ。<BR>
         */

        final WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptHandler.class);

        // 累積投資MRF取消受付サービスオブジェクトを取得する

        WEB3RuitoMRFCancelAcceptResponse l_response = null;
        WEB3RuitoMRFCancelAcceptService l_service = null;

        l_service =
                (WEB3RuitoMRFCancelAcceptService) Services.getService(
                    WEB3RuitoMRFCancelAcceptService.class);

        try
        {
            l_response = (WEB3RuitoMRFCancelAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoMRFCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "累積投資MRF取消受付サービスの取得に失敗しました。", l_ex);
            return l_response;           
        }
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する     
        return l_response;
    }
}
@
