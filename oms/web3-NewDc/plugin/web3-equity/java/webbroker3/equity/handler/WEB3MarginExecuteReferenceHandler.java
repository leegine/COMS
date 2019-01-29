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
filename	WEB3MarginExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文約定照会ハンドラクラス(WEB3MarginExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/21 艾興 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginContractListResponse;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3MarginOrderHistoryRequest;
import webbroker3.equity.message.WEB3MarginOrderHistoryResponse;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文約定照会ハンドラ）。<BR>
 * <BR>
 * 信用取引注文約定照会ハンドラクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceHandler.class);
    /**
     * @@roseuid 414184C501D3
     */
    public WEB3MarginExecuteReferenceHandler()
    {

    }

    /**
     * (search注文約定照会)<BR>
     * 信用取引注文約定照会処理を行う<BR>
     * <BR>
     * 信用取引注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * 信用取引注文約定照会リクエストデータオブジェクト<BR>
     * <BR>
     * @@return WEB3MarginExecuteReferenceResponse
     * @@roseuid 40582D750126
     */
    public WEB3MarginExecuteReferenceResponse searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest l_request)
    {
        //信用取引注文約定照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "searchOrderExecuteReference(WEB3MarginExecuteReferenceRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteReferenceResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //信用取引注文約定照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引注文約定照会に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginExecuteReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引注文約定照会に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (search注文約定詳細)<BR>
     * 信用取引注文約定詳細処理を行う<BR>
     * <BR>
     * 信用取引注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - リクエストデータ<BR>
     * 信用取引注文約定詳細リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3MarginExecuteDetailsResponse
     * @@roseuid 40FC9F2B03B6
     */
    public WEB3MarginExecuteDetailsResponse searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest l_request)
    {

        //信用取引注文約定詳細サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest) ";

        log.entering(STR_METHOD_NAME);

        WEB3MarginExecuteDetailsResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //信用取引注文約定詳細サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引注文約定詳細サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引注文約定詳細に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginExecuteDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引注文約定詳細に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (search注文履歴照会)<BR>
     * 信用取引注文履歴照会処理を行う<BR>
     * <BR>
     * 信用取引注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request  - リクエストデータ<BR>
     * 信用取引注文履歴照会リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3MarginOrderHistoryResponse
     * @@roseuid 40FC9F430200
     */
    public WEB3MarginOrderHistoryResponse searchOrderHistoryInquiry(WEB3MarginOrderHistoryRequest l_request)
    {
        // 信用取引注文履歴照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "searchOrderHistoryInquiry(WEB3MarginOrderHistoryRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginOrderHistoryResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //信用取引注文履歴照会サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引注文履歴照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引注文履歴照会に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOrderHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引注文履歴照会に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (search決済建株一覧)<BR>
     * 信用取引決済建株一覧表示処理を行う<BR>
     * <BR>
     * 信用取引注文約定照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request  - リクエストデータ<BR>
     * 信用取引決済建株一覧リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3MarginCloseMarginContractListResponse
     * @@roseuid 40FC9F5301A2
     */
    public WEB3MarginCloseMarginContractListResponse searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest l_request)
    {
        //信用取引注文約定照会サービスオブジェクトを取得する
        final String STR_METHOD_NAME =
            "searchCloseMarginContractList(WEB3MarginCloseMarginContractListRequest)";

        log.entering(STR_METHOD_NAME);

        WEB3MarginCloseMarginContractListResponse l_response = null;
        WEB3MarginExecuteReferenceService l_service = null;

        //信用取引決済建株一覧サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3MarginExecuteReferenceService)Services.getService(
                    WEB3MarginExecuteReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "信用取引決済建株一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引決済建株一覧に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginCloseMarginContractListResponse)l_request
                    .createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引決済建株一覧に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
