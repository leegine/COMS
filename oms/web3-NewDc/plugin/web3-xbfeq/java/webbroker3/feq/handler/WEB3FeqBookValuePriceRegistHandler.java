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
filename	WEB3FeqBookValuePriceRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録ハンドラ(WEB3FeqBookValuePriceRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 黄建 (中訊) 新規作成   
                 : 2005/08/03 鄭海良(中訊) レビュー       
Revesion History : 2008/01/17 柴双紅(中訊) モデルNo.377
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmResponse;
import webbroker3.feq.message.WEB3FeqBookPriceInputRequest;
import webbroker3.feq.message.WEB3FeqBookPriceInputResponse;
import webbroker3.feq.message.WEB3FeqBookPriceRegistRequest;
import webbroker3.feq.message.WEB3FeqBookPriceRegistResponse;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式簿価単価登録ハンドラ)<BR>
 * 外国株式簿価単価登録ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBookValuePriceRegistHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBookValuePriceRegistHandler.class);
    
    /**
     * @@roseuid 42D0DA1A0138
     */
    public WEB3FeqBookValuePriceRegistHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 外国株式簿価単価登録入力画面表示処理を行う。<BR>
     * <BR>
     * 外国株式簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録入力リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBookPriceInputResponse
     * @@roseuid 42A9353502FA
     */
    public WEB3FeqBookPriceInputResponse getInputScreen(WEB3FeqBookPriceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3FeqBookPriceInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式簿価単価登録サービスインターフェイス
        WEB3FeqBookValuePriceRegistService l_service = null;
         
        //外国株式訂正確認レスポンス
        WEB3FeqBookPriceInputResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBookValuePriceRegistService) Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBookPriceInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式簿価単価登録入力処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit簿価単価)<BR>
     * 外国株式簿価単価登録処理を行う。<BR>
     * <BR>
     * 外国株式簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqBookPriceRegistResponse
     * @@roseuid 42A9353B03C5
     */
    public WEB3FeqBookPriceRegistResponse submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "submitBookValuePrice(WEB3FeqBookPriceRegistRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式簿価単価登録サービスインターフェイス
        WEB3FeqBookValuePriceRegistService l_service = null;
         
        //外国株式簿価単価登録レスポンス
        WEB3FeqBookPriceRegistResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqBookValuePriceRegistService) Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceRegistResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式簿価単価登録処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate簿価単価)<BR>
     * 外国株式簿価単価確認処理を行う。<BR>
     * <BR>
     * 外国株式簿価単価登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式簿価単価登録確認リクエストオブジェクト<BR>
     * @@return WEB3FeqBookPriceConfirmResponse
     */
    public WEB3FeqBookPriceConfirmResponse validateBookValuePrice(
        WEB3FeqBookPriceConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "validateBookValuePrice(WEB3FeqBookPriceConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqBookPriceConfirmResponse l_response = null;
        WEB3FeqBookValuePriceRegistService l_service = null;
        try
        {
            l_service =
                (WEB3FeqBookValuePriceRegistService)Services.getService(
                    WEB3FeqBookValuePriceRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式簿価単価登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "外国株式簿価単価確認処理に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FeqBookPriceConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "外国株式簿価単価確認処理に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
