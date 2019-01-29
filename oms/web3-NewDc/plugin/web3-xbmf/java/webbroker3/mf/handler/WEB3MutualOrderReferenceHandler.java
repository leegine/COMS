head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文照会ハンドラクラス(WEB3MutualOrderReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 周勇 (中訊) 新規作成
                   2004/08/20 黄建 (中訊) レビュー  
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託注文照会ハンドラクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceHandler.class);
    /**
     * (search注文)<BR>
     * 投資信託注文照会処理を実施する。<BR>
     * <BR>
     * 投資信託注文照会サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualOrderReferenceResponse
     * @@roseuid 40566A82033E
     */
    public WEB3MutualOrderReferenceResponse searchOrder(WEB3MutualOrderReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "searchOrder(WEB3MutualOrderReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.error(" the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        WEB3MutualOrderReferenceService l_service = null;
        WEB3MutualOrderReferenceResponse l_response = null;
        try
        {
            l_service =
                (WEB3MutualOrderReferenceService) Services.getService(
                    WEB3MutualOrderReferenceService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MutualOrderReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信注文照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualOrderReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualOrderReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投資信託注文照会の取得に失敗しました", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
