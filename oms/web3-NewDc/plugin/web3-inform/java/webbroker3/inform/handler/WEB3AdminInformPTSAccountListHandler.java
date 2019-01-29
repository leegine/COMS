head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.57.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS申込客一覧問合せハンドラ(WEB3AdminInformPTSAccountListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.130
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者PTS申込客一覧問合せハンドラ)<BR>
 * 管理者PTS申込客一覧問合せハンドラ<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListHandler.class);

    /**
     * @@roseuid 47C6665102C9
     */
    public WEB3AdminInformPTSAccountListHandler()
    {

    }

    /**
     * (検索画面表示)<BR>
     * 管理者PTS申込客一覧問合せ検索画面表示処理を行う。 <BR>
     * <BR>
     * 管理者PTS申込客一覧問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccountListInquiryResponse
     * @@roseuid 47B538B101B9
     */
    public WEB3AdminInformPTSAccountListInquiryResponse displaySearchScreen(
        WEB3AdminInformPTSAccountListInquiryRequest l_request)
    {
        final String STR_METHOD_NAME = "displaySearchScreen(WEB3AdminInformPTSAccountListInquiryRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListInquiryResponse l_response = null;
        WEB3AdminInformPTSAccountListService l_service = null;

        //管理者PTS申込客一覧問合せサービスを取得
        try
        {
            l_service = (WEB3AdminInformPTSAccountListService)Services.getService(
                WEB3AdminInformPTSAccountListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者PTS申込客一覧問合せサービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //管理者PTS申込客一覧問合せサービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS申込客一覧問合せ検索画面の取得に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS申込客一覧問合せ検索画面の取得にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (検索結果表示)<BR>
     * 管理者PTS申込客一覧問合せ検索結果画面表示処理を行う。 <BR>
     * <BR>
     * 管理者PTS申込客一覧問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccountListResultResponse
     * @@roseuid 47B539350381
     */
    public WEB3AdminInformPTSAccountListResultResponse displaySearchResultScreen(
        WEB3AdminInformPTSAccountListResultRequest l_request)
    {
        final String STR_METHOD_NAME = "displaySearchResultScreen(WEB3AdminInformPTSAccountListResultRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccountListResultResponse l_response = null;
        WEB3AdminInformPTSAccountListService l_service = null;

        //管理者PTS申込客一覧問合せサービスを取得
        try
        {
            l_service = (WEB3AdminInformPTSAccountListService)Services.getService(
                WEB3AdminInformPTSAccountListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者PTS申込客一覧問合せサービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //管理者PTS申込客一覧問合せサービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS申込客一覧問合せ検索結果画面の取得に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccountListResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS申込客一覧問合せ検索結果画面の取得にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
