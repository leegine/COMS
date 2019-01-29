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
filename	WEB3FeqBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式買付ハンドラ(WEB3FeqBuyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBuyCompleteRequest;
import webbroker3.feq.message.WEB3FeqBuyCompleteResponse;
import webbroker3.feq.message.WEB3FeqBuyConfirmRequest;
import webbroker3.feq.message.WEB3FeqBuyConfirmResponse;
import webbroker3.feq.message.WEB3FeqBuyInputRequest;
import webbroker3.feq.message.WEB3FeqBuyInputResponse;
import webbroker3.feq.message.WEB3FeqBuyProductSelectRequest;
import webbroker3.feq.message.WEB3FeqBuyProductSelectResponse;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式買付ハンドラ)<BR>
 * 外国株式買付ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBuyHandler implements MessageHandler 
{
   
   /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBuyHandler.class); 
    
    /**
     * @@roseuid 42D0DA1903B9
     */
    public WEB3FeqBuyHandler() 
    {
     
    }
           
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 外国株式買付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqBuyInputResponse
     * @@roseuid 428AF36E0170
     */
    public WEB3FeqBuyInputResponse getInputScreen(WEB3FeqBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBuyInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //外国株式買付サービスインターフェイス
        WEB3FeqBuyService l_service = null;
         
        //外国株式買付入力レスポンス
        WEB3FeqBuyInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式買付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式買付入力に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * 買付注文の確認処理を行う。<BR>
     * <BR>
     * 外国株式買付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqBuyConfirmResponse
     * @@roseuid 428AF45F021B
     */
    public WEB3FeqBuyConfirmResponse validateOrder(WEB3FeqBuyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "validateOrder(WEB3FeqBuyConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式買付サービスインターフェイス
        WEB3FeqBuyService l_service = null;
         
        //外国株式買付入力レスポンス
        WEB3FeqBuyConfirmResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式買付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式買付確認に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * 買付注文の登録処理を行う。
     * <BR>
     * 外国株式買付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.feq.message.WEB3FeqBuyCompleteResponse
     * @@roseuid 428AF4910008
     */
    public WEB3FeqBuyCompleteResponse submitOrder(WEB3FeqBuyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitOrder(WEB3FeqBuyCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式買付サービスインターフェイス
        WEB3FeqBuyService l_service = null;
         
        //外国株式買付入力レスポンス
        WEB3FeqBuyCompleteResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBuyService) Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式買付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式買付完了に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get銘柄選択画面)<BR>
     * 銘柄選択画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 外国株式買付サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3FeqBuyProductSelectResponse
     */
    public WEB3FeqBuyProductSelectResponse getProductSelectScreen(
        WEB3FeqBuyProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getProductSelectScreen(WEB3FeqBuyProductSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqBuyService l_service = null;
        
        WEB3FeqBuyProductSelectResponse l_response = null;
        
        try
        {
            l_service = 
                (WEB3FeqBuyService)Services.getService(
                    WEB3FeqBuyService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
            l_response.errorInfo =
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式買付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBuyProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "外国株式買付銘柄選択に失敗しました。",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
