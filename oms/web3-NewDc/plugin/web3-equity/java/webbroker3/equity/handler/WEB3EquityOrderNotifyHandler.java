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
filename	WEB3EquityOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文通知ハンドラ(WEB3EquityOrderNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 欒学峰 (中訊) 新規作成
                   2004/12/15 岡村和明(SAR) 残案件対応 Ｎｏ.０７５
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3EquityOrderNotifyRequest;
import webbroker3.equity.message.WEB3EquityOrderNotifyResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式注文通知ハンドラ）。<br>
 * <br>
 * 注文通知ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyHandler.class);

    /**
     * @@roseuid 40B40A7C03C8
     */
    public WEB3EquityOrderNotifyHandler()
    {

    }

    /**
     * （現物株式注文通知リクエスト）。<br>
     * <br>
     * MAXASよりリクエストを受け、現物株式注文通知処理を実行する。 <BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照） <BR>
     * <BR>
     * １）　@株式注文通知サービスオブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@株式注文通知サービスオブジェクト.execute（）をコールし、 <BR>
     * 処理結果である現物株式注文通知レスポンスオブジェクトを取得する。 <BR>
     * <BR>
     * ３）　@サービスで例外が発生した場合は、 <BR>
     * エラー情報をレスポンスメッセージに設定する。 <BR>
     * <BR>
     * ４）　@現物株式注文通知レスポンスオブジェクトを返却する。 <BR>
     * <BR>
     * @@param l_request - (入力データ) <BR>
     * MAXASからのリクエストメッセージを指定する。 <BR>
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 4021F72601DF
     */
    public WEB3BackResponse WEB3EquityOrderNotifyRequest(WEB3EquityOrderNotifyRequest l_request)
    {
        // 株式注文訂正サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "WEB3EquityOrderNotifyRequest(WEB3EquityOrderNotifyRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderNotifyResponse l_response = null;
        WEB3EquityOrderNotifyService l_service = null;

        // 株式注文通知サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityOrderNotifyService) Services.getService(
                    WEB3EquityOrderNotifyService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_e)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            // 会社部店取扱可能市場Paramsが取得できませんでした
            log.error(
                l_request,
                "株式注文通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株式注文通知に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式注文通知に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
