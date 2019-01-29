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
filename	WEB3RuitoOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資注文照会ハンドラクラス(WEB3RuitoOrderReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;

/**
 * 累積投資注文照会ハンドラクラス
 */
public class WEB3RuitoOrderReferenceHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderReferenceHandler.class);

    /**
     * 累積投資の注文照会を行う。<BR>
     * <BR>
     * 累投注文照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資注文照会リクエストオブジェクト
     * @@return webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse
     * @@roseuid 405A4C21001E
     */
    public WEB3RuitoOrderReferenceResponse ruitoOrderReferenceRequest(
            WEB3RuitoOrderReferenceRequest l_request)
    {        
        final String STR_METHOD_NAME =
         "ruitoOrderReferenceRequest(WEB3RuitoOrderReferenceRequest)";

        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }
        WEB3RuitoOrderReferenceService l_service = null;
        WEB3RuitoOrderReferenceResponse l_response = null;

        try
        {
            l_service =
                (WEB3RuitoOrderReferenceService) Services.getService(
                    WEB3RuitoOrderReferenceService.class);
            //WEB3BaseException
            l_response = (WEB3RuitoOrderReferenceResponse) 
                    l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = (WEB3RuitoOrderReferenceResponse) 
                    l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "累積投資の注文照会処理が失敗しました。",
                     e.getErrorInfo(), e);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
