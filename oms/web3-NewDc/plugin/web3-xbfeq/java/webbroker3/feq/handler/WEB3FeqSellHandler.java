head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式売付ハンドラ(WEB3FeqSellHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqSellCompleteRequest;
import webbroker3.feq.message.WEB3FeqSellCompleteResponse;
import webbroker3.feq.message.WEB3FeqSellConfirmRequest;
import webbroker3.feq.message.WEB3FeqSellConfirmResponse;
import webbroker3.feq.message.WEB3FeqSellInputRequest;
import webbroker3.feq.message.WEB3FeqSellInputResponse;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式売付ハンドラ)<BR>
 * 外国株式売付ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqSellHandler implements MessageHandler 
{
   /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqSellHandler.class); 
    
    /**
     * @@roseuid 42D0DA1A008C
     */
    public WEB3FeqSellHandler() 
    {
     
    }
     
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 外国株式売付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqSellInputResponse
     * @@roseuid 428AFD8E01BE
     */
    public WEB3FeqSellInputResponse getInputScreen(WEB3FeqSellInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqSellInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式売付サービスインターフェイス
        WEB3FeqSellService l_service = null;
         
        //外国株式売付入力レスポンス
        WEB3FeqSellInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqSellInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式売付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqSellInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式売付入力処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 売付注文の確認処理を行う。<BR>
     * <BR>
     * 外国株式売付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqSellConfirmResponse
     * @@roseuid 428AFD8E01CD
     */
    public WEB3FeqSellConfirmResponse validateOrder(WEB3FeqSellConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqSellConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式売付サービスインターフェイス
        WEB3FeqSellService l_service = null;
         
        //外国株式売付確認レスポンス
        WEB3FeqSellConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqSellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式売付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqSellConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式売付確認処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 売付注文の登録処理を行う。<BR>
     * <BR>
     * 外国株式売付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * 
     * @@return webbroker3.feq.message.WEB3FeqSellCompleteResponse
     * @@roseuid 428AFD8E01ED
     */
    public WEB3FeqSellCompleteResponse submitOrder(WEB3FeqSellCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqSellCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式売付サービスインターフェイス
        WEB3FeqSellService l_service = null;
         
        //外国株式売付完了レスポンス
        WEB3FeqSellCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqSellService) Services.getService(
                    WEB3FeqSellService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqSellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式売付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqSellCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqSellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式売付完了処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
