head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文約定照会ハンドラ(WEB3FuturesExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李媛 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3FuturesExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryResponse;
import webbroker3.ifo.message.WEB3FuturesOrderHistoryRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginContractListRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物注文約定照会ハンドラ)<BR>
 * 株価指数先物注文約定照会ハンドラクラス<BR>
 */
public class WEB3FuturesExecuteReferenceHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesExecuteReferenceHandler.class);
    
    /**
     * @@roseuid 40C075600261
     */
    public WEB3FuturesExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get注文約定照会)<BR>
     * 株価指数先物注文約定照会、<BR>
     * 株価先物訂正一覧画面に表示するデータの取得処理を行う。<BR>
     * <BR>
     * 先物注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数先物注文約定照会リクエストオブジェクト<BR>
     * @@return WEB3FuturesExecuteReferenceResponse
     * @@roseuid 4057FBE3010C
     */
    public WEB3FuturesExecuteReferenceResponse getOrderExecutedInquiry(WEB3FuturesExecuteReferenceRequest l_request) 
    {
        //先物注文約定照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "getOrderExecutedInquiry(WEB3FuturesExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesExecuteReferenceResponse l_response = null;
        WEB3FuturesExecuteReferenceService  l_service = null;

        //株価指数先物注文約定照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
            WEB3FuturesExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物注文約定照会に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get注文約定詳細)<BR>
     * 株価指数先物当日注文約定詳細画面に表示するデータの取得処理を行<BR>う。<BR>
     * <BR>
     * 先物注文約定詳細サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数先物当日注文約定詳細リクエストオブジェクト<BR>
     * @@return WEB3FuturesExecuteDetailsResponse
     * @@roseuid 406A8AB9007C
     */
    public WEB3FuturesExecuteDetailsResponse getOrderExecutedDetail(WEB3FuturesExecuteDetailsRequest l_request) 
    {
        //先物注文約定詳細サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "getOrderExecutedDetail(WEB3FuturesExecuteDetailsRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesExecuteDetailsResponse  l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //先物注文約定詳細サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物注文約定詳細サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物注文約定詳細に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get注文履歴照会)<BR>
     * 株価指数先物注文履歴照会画面に表示するデータを取得する。<BR>
     * <BR>
     * 先物注文履歴照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物注文履歴照会リクエストオブジェクト
     * @@return WEB3FuturesOrderHistoryResponse
     * @@roseuid 409EDD0701B8
     */
    public WEB3FuturesOrderHistoryResponse getOrderActionInquiry(WEB3FuturesOrderHistoryRequest l_request) 
    {
        // 先物注文履歴照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "getOrderActionInquiry(WEB3FuturesOrderHistoryRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesOrderHistoryResponse    l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //先物注文履歴照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物注文履歴照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物注文履歴照会に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get返済建玉一覧)<BR>
     * 株価指数先物返済建玉一覧画面に表示するデータを取得する。<BR>
     * 先物返済建玉一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数先物返済建玉一覧リクエストオブジェクト<BR>
     * @@return WEB3FuturesCloseMarginContractListResponse
     * @@roseuid 409EDDFC01C8
     */
    public WEB3FuturesCloseMarginContractListResponse getSettleContractList(WEB3FuturesCloseMarginContractListRequest l_request) 
    {
        //先物返済建玉一覧サービスオブジェクトを取得する
        final String STR_METHOD_NAME = 
            "getSettleContractList(WEB3FuturesCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3FuturesCloseMarginContractListResponse l_response = null;
        WEB3FuturesExecuteReferenceService l_service = null;

        //先物返済建玉一覧サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3FuturesExecuteReferenceService)Services.getService(
                WEB3FuturesExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FuturesCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物返済建玉一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FuturesCloseMarginContractListResponse)
                l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3FuturesCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物返済建玉一覧に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
