head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityChangeCancelAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消受付ハンドラ(WEB3EquityChangeCancelAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 周玲玲(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequest;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptResponse;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式訂正取消受付ハンドラ）。<BR>
 * <BR>
 * 株式訂正取消受付ハンドラクラス。
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityChangeCancelAcceptHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeCancelAcceptHandler.class);

    /**
     * @@roseuid 40A0A7FD00C5
     */
    public WEB3EquityChangeCancelAcceptHandler()
    {
    }

    /**
     * （株式訂正取消受付リクエスト）<BR>
     * <BR>
     * リクエストを受け、株式訂正取消受付処理を実行する。<BR>
     * （システム実装方針ガイド 4.5.ハンドラ　@参照）<BR>
     * <BR>
     * １）　@株式訂正取消受付サービスオブジェクトを取得する。<BR>
     * <BR>
     * ２）　@株式訂正取消受付サービスオブジェクト.execute（）をコールし、処理結果である
     * 株式訂正取消受付レスポンスオブジェクトを取得する。<BR>
     * <BR>
     * ３）　@サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する。<BR>
     * <BR>
     * ４）　@株式訂正取消受付レスポンスオブジェクトを返却する。<BR>
     *
     * @@param l_request - 株式訂正取消受付リクエスト
     * @@return webbroker3.equity.message.WEB3EquityChangeCancelAcceptResponse
     */
    public WEB3EquityChangeCancelAcceptResponse equityChangeCancelAcceptRequest(WEB3EquityChangeCancelAcceptRequest l_request)
    {
        // 株式訂正取消受付サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "equityChangeCancelAcceptRequest(WEB3EquityChangeCancelAcceptRequest)";       
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
             throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3EquityChangeCancelAcceptResponse l_response = null;
        WEB3EquityChangeCancelAcceptService l_service = null;

        // 株式訂正取消受付サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityChangeCancelAcceptService) Services.getService(
                    WEB3EquityChangeCancelAcceptService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式訂正取消受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株式訂正取消受付に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityChangeCancelAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式訂正取消受付に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
