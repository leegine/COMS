head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックハンドラ(WEB3AioCashoutTradingPowerHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckRequest;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金余力チェックハンドラ)<BR>
 * 出金余力チェックハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutTradingPowerHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutTradingPowerHandler.class);
    
    /**
     * (出金余力チェックリクエスト)<BR>
     * 出金余力チェックサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioCashoutTradingPowerCheckResponse
     * @@roseuid 41294DCB0256
     */
    public WEB3AioCashoutTradingPowerCheckResponse 
        handleCashoutRemainingPowerCheckRequest(
            WEB3AioCashoutTradingPowerCheckRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashoutRemainingPowerCheckRequest(" +
            "WEB3AioCashoutTradingPowerCheckRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金余力チェックサービス
        WEB3AioCashoutTradingPowerService l_service = null;          
         
        //出金余力チェックレスポンス
        WEB3AioCashoutTradingPowerCheckResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutTradingPowerService) Services.getService(
                    WEB3AioCashoutTradingPowerService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金余力チェックレスポンスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutTradingPowerCheckResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金余力に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
