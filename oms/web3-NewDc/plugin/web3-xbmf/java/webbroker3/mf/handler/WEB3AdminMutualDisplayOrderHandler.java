head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録ハンドラ(WEB3AdminMutualDisplayOrderHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信管理者銘柄表示順序登録ハンドラ)<BR>
 * 投信管理者銘柄表示順序登録ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminMutualDisplayOrderHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualDisplayOrderHandler.class);
    
    /**
     * (get銘柄表示順序登録入力画面)<BR>
     * 投資信託管理者銘柄表示順序登録入力画面取得処理を行う。<BR>
     * <BR>
     * 投信管理者銘柄表示順序登録サービスを取得し、<BR>
     * execute( )メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者銘柄表示順序登録入力画面リクエストオブジェクト
     * @@return WEB3AdminMutualDisplayOrderInputResponse
     * @@roseuid 4153F74A02F6
     */
    public WEB3AdminMutualDisplayOrderInputResponse getProductDisplayOrderInput(
        WEB3AdminMutualDisplayOrderInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getProductDisplayOrderInput(WEB3AdminMutualDisplayOrderInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //投信管理者銘柄表示順序登録サービス
        WEB3AdminMutualDisplayOrderService l_service = null;
        //投信管理者銘柄表示順序登録入力画面レスポンス
        WEB3AdminMutualDisplayOrderInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualDisplayOrderService) Services.getService(
                    WEB3AdminMutualDisplayOrderService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者銘柄表示順序登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualDisplayOrderInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者銘柄表示順序登録入力画面レスポンスの処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete管理者銘柄表示順序登録)<BR>
     * 投資信託管理者銘柄表示順序登録処理を行う。<BR>
     * <BR>
     * 投信管理者銘柄表示順序登録サービスを取得し、<BR>
     * execute( )メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 投信管理者銘柄表示順序登録完了リクエストオブジェクト
     * @@return WEB3AdminMutualDisplayOrderCompleteResponse
     * @@roseuid 4153F7C701EC
     */
    public WEB3AdminMutualDisplayOrderCompleteResponse completeAdminMutualDisplayOrderRegistr(
        WEB3AdminMutualDisplayOrderCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeAdminMutualDisplayOrderRegistr(WEB3AdminMutualDisplayOrderCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //投信管理者銘柄表示順序登録サービス
        WEB3AdminMutualDisplayOrderService l_service = null;
        //投信管理者銘柄表示順序登録完了レスポンス
        WEB3AdminMutualDisplayOrderCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualDisplayOrderService) Services.getService(
                    WEB3AdminMutualDisplayOrderService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者銘柄表示順序登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualDisplayOrderCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者銘柄表示順序登録完了レスポンスの処理に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
