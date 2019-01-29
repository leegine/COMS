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
filename	WEB3OptionOrderExecutedInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP注文約定照会ハンドラ(WEB3OptionOrderExecutedInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 馬振田 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequest;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse;
import webbroker3.ifo.message.WEB3OptionsExecuteDetailsRequest;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequest;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;

/**
 * OP注文約定照会ハンドラ<BR>
 * 株価指数オプション注文約定照会ハンドラクラス<BR>
 * @@author 馬振田
 * @@version 1.0
 */
public class WEB3OptionOrderExecutedInquiryHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderExecutedInquiryHandler.class);
    
    /**
     * @@roseuid 40C075600261
     */
    public WEB3OptionOrderExecutedInquiryHandler() 
    {
     
    }
    
    /**
     * get注文約定照会<BR>
     * 株価指数オプション注文約定照会、<BR>
     * 株価オプション訂正一覧画面に表示するデータの取得処理を行う。<BR>
     * <BR>
     * OP注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数オプション注文約定照会リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteReferenceResponse
     * @@roseuid 4057FBE3010C
     */
    public WEB3OptionsExecuteReferenceResponse getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest l_request) 
    {
        //OP注文約定照会サービスオブジェクトを取得する
        final String l_strMethodName =
            "getOrderExecutedInquiry(WEB3OptionsExecuteReferenceRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsExecuteReferenceResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP注文約定照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP注文約定照会に失敗しました。", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * get注文約定詳細<BR>
     * 株価指数オプション当日注文約定詳細画面に表示するデータの取得処理を行<BR>う。<BR>
     * <BR>
     * OP注文約定詳細サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数オプション当日注文約定詳細リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsExecuteDetailsResponse
     * @@roseuid 406A8AB9007C
     */
    public WEB3OptionsExecuteDetailsResponse getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest l_request) 
    {
        //OP注文約定詳細サービスオブジェクトを取得する
        final String l_strMethodName =
            "getOrderExecutedDetail(WEB3OptionsExecuteDetailsRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsExecuteDetailsResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP注文約定詳細サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP注文約定詳細サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP注文約定詳細に失敗しました。", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * get注文履歴照会<BR>
     * 株価指数オプション注文履歴照会画面に表示するデータを取得する。<BR>
     * <BR>
     * OP注文履歴照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション注文履歴照会リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3OptionsOrderHistoryResponse
     * @@roseuid 409EDD0701B8
     */
    public WEB3OptionsOrderHistoryResponse getOrderActionInquiry(WEB3OptionsOrderHistoryRequest l_request) 
    {
        // OP注文履歴照会サービスオブジェクトを取得する
        final String l_strMethodName =
            "getOrderActionInquiry(WEB3OptionsOrderHistoryRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsOrderHistoryResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP注文履歴照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP注文履歴照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP注文履歴照会に失敗しました。", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * get返済建玉一覧<BR>
     * 株価指数オプション返済建玉一覧画面に表示するデータを取得する。<BR>
     * OP返済建玉一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * 株価指数オプション返済建玉一覧リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginContractListResponse
     * @@roseuid 409EDDFC01C8
     */
    public WEB3OptionsCloseMarginContractListResponse getSettleContractList(WEB3OptionsCloseMarginContractListRequest l_request) 
    {
        //OP返済建玉一覧サービスオブジェクトを取得する
        final String l_strMethodName = 
            "getSettleContractList(WEB3OptionsCloseMarginContractListRequest)";

        log.entering(l_strMethodName);

        WEB3OptionsCloseMarginContractListResponse l_response = null;
        WEB3OptionOrderExecutedInquiryService l_service = null;

        //OP返済建玉一覧サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3OptionOrderExecutedInquiryService)Services.getService(
                    WEB3OptionOrderExecutedInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response = 
                (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "OP返済建玉一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }

        try
        {
            l_response = (WEB3OptionsCloseMarginContractListResponse)
                l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response = 
                (WEB3OptionsCloseMarginContractListResponse)l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "OP返済建玉一覧に失敗しました。", e);
            return l_response;
        }

        log.exiting(l_strMethodName);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
