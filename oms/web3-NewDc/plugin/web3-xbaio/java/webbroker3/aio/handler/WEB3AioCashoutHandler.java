head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込ハンドラ(WEB3AioCashoutHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金申込ハンドラ)<BR>
 * 出金申込ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutHandler.class);
            
    /**
     * (confirm注文)<BR>
     * 出金申込の発注審査を行う。 <BR>
     * <BR>
     * 出金申込サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutConfirmResponse
     * @@roseuid 40EBEC7701E5
     */
    public WEB3AioCashoutConfirmResponse handleConfirmOrderRequest(WEB3AioCashoutConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleConfirmOrderRequest(WEB3AioCashoutConfirmRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金申込サービス
        WEB3AioCashoutService l_service = null;          
         
        //出金申込確認レスポンス
        WEB3AioCashoutConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutService) Services.getService(
                    WEB3AioCashoutService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金申込確認に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete注文)<BR>
     * 出金申込の登録を行う。 <BR>
     * <BR>
     * 出金申込サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutCompleteResponse
     * @@roseuid 40EBEC7D02B0
     */
    public WEB3AioCashoutCompleteResponse handleCompleteOrderRequest(WEB3AioCashoutCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCompleteOrderRequest(WEB3AioCashoutCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金申込サービス
        WEB3AioCashoutService l_service = null;          
         
        //出金申込完了レスポンス
        WEB3AioCashoutCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutService) Services.getService(
                    WEB3AioCashoutService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金申込完了に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
