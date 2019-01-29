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
filename	WEB3RuitoBuyOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資買付注文入力ハンドラ(WEB3RuitoBuyOrderInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/07 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.xbruito.message.WEB3RuitoBuyInputRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;


/**
 * 累積投資買付注文入力ハンドラ<BR>
 */
public class WEB3RuitoBuyOrderInputHandler implements MessageHandler
{

    /**
         * ログ出力ユーティリティ。
         */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoBuyOrderInputHandler.class);

    /**
     * 累積投資の買付注文入力画面表示処理を行う。<BR>
     * <BR>
     * 累投買付注文入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param リクエストデータ
     * @@param l_request
     * @@roseuid 4069307001B5
     */
    public WEB3RuitoBuyInputResponse ruitoBuyInputRequest(
            WEB3RuitoBuyInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "WEB3RuitoBuyOrderInputRequest(WEB3RuitoBuyInputRequest)";

        log.entering(STR_METHOD_NAME);
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");            
        }
        WEB3RuitoBuyInputResponse l_response = null;
        WEB3RuitoBuyOrderInputService l_service = null;

        // 累投買付注文入力サービスオブジェクトを取得する
        // 累投買付注文入力サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3RuitoBuyOrderInputService) Services.getService(
                    WEB3RuitoBuyOrderInputService.class);
            l_response = 
                (WEB3RuitoBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3RuitoBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "累投買付注文に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
