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
filename	WEB3EquityExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式注文約定照会ハンドラ(WEB3EquityExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 孟 東 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityExecuteDetailsResponse;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryResponse;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * （現物株式注文約定照会ハンドラ）。<BR>
 * <BR>
 * 現物株式注文約定照会ハンドラクラス
 * @@version 1.0
 */
public class WEB3EquityExecuteReferenceHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceHandler.class);

    /**
     * @@roseuid 40A287FA038A
     */
    public WEB3EquityExecuteReferenceHandler()
    {

    }

    /**
     * (search注文約定照会)<BR>
     * 現物株式注文約定照会、現物株式訂正取消一覧に表示するデータの<BR>
     * 取得を行う。<BR>
     * <BR>
     * 現物株式注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定照会リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteReferenceResponse
     * @@roseuid 4060DF2201C9
     */
    public WEB3EquityExecuteReferenceResponse searchExecuteReference(WEB3EquityExecuteReferenceRequest l_request)
    {
        // 株式注文約定照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME = 
            "equityExecuteReferenceRequest(WEB3EquityExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityExecuteReferenceResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // 株式注文約定照会サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株式注文約定照会表示に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式注文約定照会表示に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (search注文約定詳細)<BR>
     * 現物株式注文約定詳細画面に表示するデータを取得する。<BR>
     * <BR>
     * 現物株式注文約定照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityExecuteDetailsResponse
     * @@roseuid 406A8F750137
     */
    public WEB3EquityExecuteDetailsResponse searchExecuteDetails(WEB3EquityExecuteDetailsRequest l_request)
    {
        //株式注文約定詳細サービスオブジェクトを取得する。
        final String STR_METHOD_NAME = 
            "equityExecuteDetailsRequest(WEB3EquityExecuteDetailsResponse)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityExecuteDetailsResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // 株式注文約定照会サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株式注文注文約定詳細表示に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式注文注文約定詳細表示に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する       
        return l_response;
    }

    /**
     * (search注文約定履歴)<BR>
     * 現物株式注文約定履歴画面に表示するデータを取得する。<BR>
     * <BR>
     * 現物株式注文約定照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 現物株式注文約定履歴リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderHistoryResponse
     * @@roseuid 4076537500C2
     */
    public WEB3EquityOrderHistoryResponse searchOrderHistory(WEB3EquityOrderHistoryRequest l_request)
    {
        //株式注文訂正サービスオブジェクトを取得する。
        final String STR_METHOD_NAME =
            "equityOrderHistoryRequest(WEB3EquityOrderHistoryResponse)";

        log.entering(STR_METHOD_NAME);

        WEB3EquityOrderHistoryResponse l_response = null;
        WEB3EquityExecuteReferenceService l_service = null;

        // 株式注文約定照会サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityExecuteReferenceService) Services.getService(
                    WEB3EquityExecuteReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            //会社部店取扱可能市場Paramsが取得できませんでした
            log.error(
                l_request,
                "株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株式注文約定履歴表示に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "株式注文約定履歴表示に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する       
        return l_response;
    }
}
@
