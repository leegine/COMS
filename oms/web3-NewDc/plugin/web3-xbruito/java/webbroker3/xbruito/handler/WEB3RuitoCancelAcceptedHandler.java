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
filename	WEB3RuitoCancelAcceptedHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消受付ハンドラ クラス (WEB3RuitoCancelAcceptedHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15  王艶芳 (中訊) 新規作成
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;


/**
 * 累積投資取消受付ハンドラクラス<BR>
 */
public class WEB3RuitoCancelAcceptedHandler implements MessageHandler
{
	private static WEB3LogUtility log =
			WEB3LogUtility.getInstance(
			WEB3RuitoCancelAcceptResponse.class);
    /**
     * 累積投資取消受付を行う。<BR>
     * <BR>
     * 累積投資取消受付サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 累積投資取消受付リクエストオブジェクト <BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse
     * @@throws WEB3BaseException
     * @@roseuid 40582120014A
     */
    public WEB3RuitoCancelAcceptResponse cancelAcceptedRequest
        (WEB3RuitoCancelAcceptRequest l_request)
    {
        String STR_METHOD_NAME = "cancelAcceptedRequest(" +
            "WEB3RuitoCancelAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);   
        
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }
        
        WEB3RuitoCancelAcceptResponse l_response = null;
        WEB3RuitoCancelAcceptedService l_ruitoCancelAcceptService =
            (WEB3RuitoCancelAcceptedService)Services.getService
        (WEB3RuitoCancelAcceptedService.class);     

        try
        {
            l_response = (WEB3RuitoCancelAcceptResponse)l_ruitoCancelAcceptService.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "累積投資取消受付サービスの取得に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
