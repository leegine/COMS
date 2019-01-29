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
filename	WEB3RuitoMRFOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資MRF注文受付ハンドラクラス(WEB3RuitoMRFOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptServiceImpl;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * 累積投資MRF注文受付ハンドラクラス<BR>
 */
public class WEB3RuitoMRFOrderAcceptHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptHandler.class);
    /**
     * 累積投資MRF注文受付処理を行う。<BR>
     * <BR>
     * 累積投資MRF注文受付サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資MRF注文受付リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest
     * @@roseuid 4058152B0031
     */
    public WEB3RuitoMRFOrderAcceptResponse mrfOrderAcceptRequest(
                WEB3RuitoMRFOrderAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "mrfOrderAcceptRequest(" +
                "WEB3RuitoMRFOrderAcceptRequest l_request)";
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
            WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptServiceImpl.class);

        // 累積投資MRF取消受付サービスオブジェクトを取得する
        WEB3RuitoMRFOrderAcceptResponse l_response = null;
        WEB3RuitoMRFOrderAcceptService l_service = null;

        try
        {
            l_service =
                (WEB3RuitoMRFOrderAcceptService) Services.getService(
                    WEB3RuitoMRFOrderAcceptService.class);
        }
        // サービスで例外が発生した場合は、
        // エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) 
                            l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            // 会社部店取扱可能市場Paramsが取得できませんでした
            log.error(l_request, "累積投資MRF取消受付サービスの取得に失敗しました。", 
            l_response.errorInfo, e);
            return l_response;
        }
        try
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) 
                            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoMRFOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, 
                    "累積投資MRF取消受付サービスの取得に失敗しました。", e);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する     
        return l_response;
    }
}
@
