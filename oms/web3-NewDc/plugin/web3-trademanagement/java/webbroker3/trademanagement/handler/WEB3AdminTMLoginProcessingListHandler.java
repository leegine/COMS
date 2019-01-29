head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginProcessingListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン処理一覧ハンドラ (WEB3AdminTMLoginProcessingListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 張少傑 (中訊) 新規作成 モデルNo.005
*/

package webbroker3.trademanagement.handler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (ログイン処理一覧ハンドラ)<BR>
 * 管理者ログイン処理一覧ハンドラクラス。<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public class WEB3AdminTMLoginProcessingListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListHandler.class);

    /**
     * @@roseuid 48D7511802BA
     */
    public WEB3AdminTMLoginProcessingListHandler()
    {

    }

    /**
     * (get検索入力画面)<BR>
     * ログイン処理一覧の検索入力画面の表示を行う。 <BR>
     * <BR>
     * ログイン処理一覧サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・ログイン処理一覧表示入力リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryInputResponse
     * @@roseuid 48BDF76C0311
     */
    public WEB3AdminTraderAdminLoginHistoryInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchInputScreen(WEB3AdminTraderAdminLoginHistoryInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response = null;
        WEB3AdminTMLoginProcessingListService l_service = null;
        try
        {
            //ログイン処理一覧サービスを取得する
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                    WEB3AdminTMLoginProcessingListService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・ログイン処理一覧サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTraderAdminLoginHistoryInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "ログイン処理一覧の検索入力画面の表示に失敗しました。",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "ログイン処理一覧の検索入力画面の表示に失敗しました。",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;  
    }
    
    /**
     * (get検索結果画面)<BR>
     * ログイン処理一覧の検索結果画面の表示を行う。 <BR>
     * <BR>
     * ログイン処理一覧サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・ログイン処理一覧表示確認リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginHistoryListResponse
     * @@roseuid 48BE0D30001E
     */
    public WEB3AdminTraderAdminLoginHistoryListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginHistoryListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getSearchResultScreen(WEB3AdminTraderAdminLoginHistoryListRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        WEB3AdminTMLoginProcessingListService l_service = null;
        try
        {
            l_service =
                (WEB3AdminTMLoginProcessingListService)Services.getService(
                    WEB3AdminTMLoginProcessingListService.class);
        }
        catch(Exception l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・ログイン処理一覧サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTraderAdminLoginHistoryListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "ログイン処理一覧の検索結果画面の表示に失敗しました。",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "ログイン処理一覧の検索結果画面の表示に失敗しました。",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
