head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文通知ハンドラ(WEB3MarginSwapOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 森川 (SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginSwapOrderNotifyRequest;
import webbroker3.equity.message.WEB3MarginSwapOrderNotifyResponse;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * （信用取引現引現渡注文通知ハンドラ）。<BR>
 * <BR>
 * 信用取引現引現渡注文通知ハンドラ
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyHandler.class);

    /**
     * (コンストラクタ)。<BR>
     */
    public WEB3MarginSwapOrderNotifyHandler()
    {
    }

    /**
     * (信用取引現引現渡注文通知リクエスト)。<BR>
     * <BR>
     * 信用取引現引現渡注文通知処理を行う。 <BR>
     * <BR>
     * 信用取引現引現渡注文通知サービスを取得し、 <BR>
     * execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request 信用取引現引現渡注文通知リクエストオブジェクト <BR>
     * @@return WEB3MarginSwapOrderNotifyResponse<BR>
     */
    public WEB3MarginSwapOrderNotifyResponse swapOrderNotify(
        WEB3MarginSwapOrderNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "swapOrderNotify(WEB3MarginSwapOrderNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginSwapOrderNotifyResponse   l_response = null;
        WEB3MarginSwapOrderNotifyService    l_service = null;

        //--------------------
        //信用取引現引現渡注文通知サービスの取得
        //--------------------
        try
        {
            l_service = (WEB3MarginSwapOrderNotifyService) Services
                .getService(WEB3MarginSwapOrderNotifyService.class);
        }
        catch (Exception l_e)
        {
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, "信用取引現引現渡注文通知サービスの取得に失敗しました。",
                l_response.errorInfo, l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //--------------------
        //信用取引現引現渡注文通知サービスの実行
        //--------------------
        try
        {
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_request, "信用取引現引現渡注文通知に失敗しました。", l_wbe);
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_wbe.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_request, "信用取引現引現渡注文通知に失敗しました。", l_bre);
            l_response = (WEB3MarginSwapOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
