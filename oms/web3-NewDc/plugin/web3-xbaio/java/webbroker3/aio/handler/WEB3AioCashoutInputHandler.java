head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込入力ハンドラ(WEB3AioCashoutInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutInputRequest;
import webbroker3.aio.message.WEB3AioCashoutInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金申込入力ハンドラ)<BR>
 * (出金申込入力ハンドラクラス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutInputHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutInputHandler.class);
    
    /**
     * (出金申込入力リクエスト)<BR>
     * 出金申込入力表示処理を行う。 <BR>
     * 出金申込入力サービスを取得し、 execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashoutInputResponse
     * @@roseuid 40EBDE4A0281
     */
    public WEB3AioCashoutInputResponse handleCashoutInputHandler(WEB3AioCashoutInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashoutInputHandler(WEB3AioCashoutInputRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金申込入力サービス
        WEB3AioCashoutInputService l_service = null;          
         
        //出金申込入力レスポンス
        WEB3AioCashoutInputResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutInputService) Services.getService(
                    WEB3AioCashoutInputService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金申込入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金申込入力に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
