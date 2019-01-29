head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索ハンドラ(WEB3AdminTPPaymentRequisitionCustomerSearchHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 安陽(中訊) 新規作成 モデルNo.027
*/

package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * (入金請求顧客検索ハンドラ)<BR>
 * <BR>
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class);

    /**
     * @@roseuid 48E9E2530055
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchHandler()
    {

    }

    /**
     * (get入金請求顧客検索入力)<BR>
     * @@roseuid 48CA07E40070
     * @@return WEB3AdminTPPaymentRequisitionInputResponse
     */
    public WEB3AdminTPPaymentRequisitionInputResponse getPaymentRequisitionCustomerSearchInput(
        WEB3AdminTPPaymentRequisitionInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchInput(WEB3AdminTPPaymentRequisitionInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionInputResponse l_response = null;

        try
        {
            //入金請求顧客検索サービスImplを取得
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "入金請求顧客検索サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索入力表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索入力表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索一覧)<BR>
     * @@roseuid 48CA080B02A2
     * @@return WEB3AdminTPPaymentRequisitionListResponse
     */
    public WEB3AdminTPPaymentRequisitionListResponse getPaymentRequisitionCustomerSearchList(
        WEB3AdminTPPaymentRequisitionListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchList(WEB3AdminTPPaymentRequisitionListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionListResponse l_response = null;

        try
        {
            //入金請求顧客検索サービスImplを取得
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "入金請求顧客検索サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索一覧表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索一覧表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索詳細)<BR>
     * @@roseuid 48CA080403AB
     * @@return WEB3AdminTPPaymentRequisitionDetailResponse
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse getPaymentRequisitionCustomerSearchDetail(
        WEB3AdminTPPaymentRequisitionDetailRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDetail(WEB3AdminTPPaymentRequisitionDetailRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionDetailResponse l_response = null;

        try
        {
            //入金請求顧客検索サービスImplを取得
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "入金請求顧客検索サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索詳細表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索詳細表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get入金請求顧客検索ダウンロード)<BR>
     * @@roseuid 48CA081A01B7
     * @@return WEB3AdminTPPaymentRequisitionDownLoadResponse
     */
    public WEB3AdminTPPaymentRequisitionDownLoadResponse getPaymentRequisitionCustomerSearchDownLoad(
        WEB3AdminTPPaymentRequisitionDownLoadRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getPaymentRequisitionCustomerSearchDownLoad(WEB3AdminTPPaymentRequisitionDownLoadRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTPPaymentRequisitionCustomerSearchService l_service = null;
        WEB3AdminTPPaymentRequisitionDownLoadResponse l_response = null;

        try
        {
            //入金請求顧客検索サービスImplを取得
            l_service =
                (WEB3AdminTPPaymentRequisitionCustomerSearchService)Services.getService(
                    WEB3AdminTPPaymentRequisitionCustomerSearchService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "入金請求顧客検索サービスImplの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索ダウンロード表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTPPaymentRequisitionDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "入金請求顧客検索ダウンロード表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
