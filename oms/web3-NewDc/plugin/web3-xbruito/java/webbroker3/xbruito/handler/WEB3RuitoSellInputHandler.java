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
filename	WEB3RuitoSellInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投解約入力ハンドラ(WEB3RuitoSellInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/09 李志強 (中訊) 新規作成
*/

package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;


/**
 * 累投解約入力ハンドラ<BR>
 */
public class WEB3RuitoSellInputHandler implements MessageHandler
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoSellInputHandler.class);

    /**
     * 累積投資の解約入力画面表示処理を行う。<BR>
     * 累投解約入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 累投解約入力リクエストオブジェクト<BR>
     * @@return webbroker3.xbruito.message.WEB3RuitoSellInputResponse
     * @@roseuid 406A907C01C2
     */
    public WEB3RuitoSellInputResponse sellInputRequest(
            WEB3RuitoSellInputRequest l_request)
    {
		final String STR_METHOD_NAME = 
            "sellInputRequest(WEB3RuitoSellInputRequest l_request)";
		log.entering(STR_METHOD_NAME);
		
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }

		WEB3RuitoSellInputService l_service = null;
        WEB3RuitoSellInputResponse l_response = null;

        l_service =
            (WEB3RuitoSellInputService) Services.getService(WEB3RuitoSellInputService.class);

        try
        {
            l_response = (WEB3RuitoSellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoSellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "累積投資の解約入力画面表示処理が失敗しました。", l_ex.getErrorInfo(), l_ex);
        }
		log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
