head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMLoginFrequencyListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IP別ログイン回数一覧ハンドラ(WEB3AdminTMLoginFrequencyListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 劉剣(中訊) 新規作成 モデル 005,009
*/

package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (IP別ログイン回数一覧ハンドラ)<BR>
 * 管理者IP別ログイン回数一覧ハンドラクラス。<BR>
 *
 * @@author 劉剣
 * @@version 1.0
 */
public class WEB3AdminTMLoginFrequencyListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListHandler.class);

    /**
     * @@roseuid 48D75CD80379
     */
    public WEB3AdminTMLoginFrequencyListHandler()
    {

    }

    /**
     * (get検索入力画面)<BR>
     * IP別ログイン回数一覧の検索入力画面の表示を行う。 <BR>
     * <BR>
     * IP別ログイン回数一覧サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・IP別ログイン回数一覧入力リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginCountInputResponse
     * @@roseuid 48C0E8E9011D
     */
    public WEB3AdminTraderAdminLoginCountInputResponse getSearchInputScreen(
        WEB3AdminTraderAdminLoginCountInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getSearchInputScreen(WEB3AdminTraderAdminLoginCountInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginCountInputResponse l_response = null;
        WEB3AdminTMLoginFrequencyListService l_service = null;

        try
        {
            //IP別ログイン回数一覧サービスを取得し、
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者・IP別ログイン回数一覧サービスの取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }

        try
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IP別ログイン回数一覧の検索入力画面の表示に失敗しました。", l_ex);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountInputResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "IP別ログイン回数一覧の検索入力画面の表示に失敗しました。", l_rex);

            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (get検索結果画面)<BR>
     * IP別ログイン回数一覧の検索結果画面の表示を行う。 <BR>
     * <BR>
     * IP別ログイン回数一覧サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 管理者・IP別ログイン回数一覧確認リクエストクラス。<BR>
     * @@return WEB3AdminTraderAdminLoginCountListResponse
     * @@roseuid 48C0E936015D
     */
    public WEB3AdminTraderAdminLoginCountListResponse getSearchResultScreen(
        WEB3AdminTraderAdminLoginCountListRequest l_request)
    {
        final String STR_METHOD_NAME = "getSearchResultScreen(WEB3AdminTraderAdminLoginCountListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTraderAdminLoginCountListResponse l_response = null;
        WEB3AdminTMLoginFrequencyListService l_service = null;

        try
        {
            //IP別ログイン回数一覧サービスを取得し、
            l_service =
                (WEB3AdminTMLoginFrequencyListService)Services.getService(
                    WEB3AdminTMLoginFrequencyListService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者・IP別ログイン回数一覧サービスの取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }

        try
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IP別ログイン回数一覧の検索結果画面の表示に失敗しました。", l_ex);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3AdminTraderAdminLoginCountListResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "IP別ログイン回数一覧の検索結果画面の表示に失敗しました。", l_rex);

            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}@
