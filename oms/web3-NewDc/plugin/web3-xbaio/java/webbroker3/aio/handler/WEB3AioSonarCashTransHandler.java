head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSonarCashTransHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金ハンドラ(WEB3AioSonarCashTransHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSonarCashTransRequest;
import webbroker3.aio.message.WEB3AioSonarCashTransResponse;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (SONAR入出金ハンドラ)<BR>
 * SONAR入出金ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioSonarCashTransHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSonarCashTransHandler.class);
    
    /**
     * (SONAR入出金リクエスト)<BR>
     * SONAR入出金処理を行う。<BR> 
     * <BR>
     * SONAR入出金サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioSonarCashTransResponse
     * @@roseuid 40FF6D7103D8
     */
    public WEB3AioSonarCashTransResponse handleAioCashTransRequest(WEB3AioSonarCashTransRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleAioCashTransRequest(WEB3AioSonarCashTransRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //SONAR入出金サービスインターフェイス
        WEB3AioSonarCashTransService l_service = null;          
         
        //SONAR入出金レスポンスクラス
        WEB3AioSonarCashTransResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioSonarCashTransService) Services.getService(
                    WEB3AioSonarCashTransService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioSonarCashTransResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "SONAR入出金サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioSonarCashTransResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSonarCashTransResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "SONAR入出金に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}

@
