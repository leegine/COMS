head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消ハンドラ(WEB3AioCashoutCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 黄建 (中訊) 新規作成
                   2004/10/25 屈陽 (中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCancelCompleteRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelCompleteResponse;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金取消ハンドラ)<BR>
 * (出金取消ハンドラクラス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelHandler.class);
    
    /**
     * (confirm取消)<BR>
     * 出金注文の取消発注審査を行う。<BR>
     * <BR>
     * 出金取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutCancelConfirmResponse
     * @@roseuid 40F73D390272
     */
    public WEB3AioCashoutCancelConfirmResponse handleConfirmCancelRequest(
        WEB3AioCashoutCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleConfirmCancelRequest(" +
            "WEB3AioCashoutCancelConfirmRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金取消サービスインターフェイス
        WEB3AioCashoutCancelService l_service = null;          
         
        //出金取消確認レスポンス
        WEB3AioCashoutCancelConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelService) Services.getService(
                    WEB3AioCashoutCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金取消確認サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金取消確認に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete取消)<BR>
     * 出金注文の取消の登録を行う。<BR>
     * <BR>
     * 出金取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioCashoutCancelCompleteResponse
     * @@roseuid 40F73D8101E6
     */
    public WEB3AioCashoutCancelCompleteResponse handleCompleteCancelRequest(
        WEB3AioCashoutCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCompleteCancelRequest(WEB3AioCashoutCancelCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金取消サービスインターフェイス
        WEB3AioCashoutCancelService l_service = null;          
         
        //出金取消完了レスポンス
        WEB3AioCashoutCancelCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelService) Services.getService(
                    WEB3AioCashoutCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金取消完了サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金取消完了に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
