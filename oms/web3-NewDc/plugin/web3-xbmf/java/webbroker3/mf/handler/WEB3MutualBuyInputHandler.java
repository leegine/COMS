head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信買付注文入力ハンドラクラス(WEB3MutualBuyInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 周勇 (中訊) 新規作成
                   2004/08/23 黄建 (中訊) レビュー
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信買付注文入力ハンドラクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyInputHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyInputHandler.class);
    /**
     * (買付注文入力リクエスト)<BR>
     * 投資信託の買付注文入力画面表示処理を行う。<BR>
     * <BR>
     * 投信買付注文入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 投信買付注文入力リクエストオブジェクト<BR>
     * @@return webbroker3.mf.message.WEB3MutualBuyInputResponse
     * @@roseuid 40B14D9502F3
     */
    public WEB3MutualBuyInputResponse buyInputRequest(WEB3MutualBuyInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "buyInputRequest(WEB3MutualBuyInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug("the parametor method in null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MutualBuyInputResponse l_response = null;
        WEB3MutualBuyInputService l_service = null;
        try
        {
            l_service =
                (WEB3MutualBuyInputService) Services.getService(
                    WEB3MutualBuyInputService.class);

        }
        catch (Exception l_ex)
        {
            l_response = (WEB3MutualBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信買付注文入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MutualBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "投信買付注文入力の取得に失敗しました", l_ex.getErrorInfo(), l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
