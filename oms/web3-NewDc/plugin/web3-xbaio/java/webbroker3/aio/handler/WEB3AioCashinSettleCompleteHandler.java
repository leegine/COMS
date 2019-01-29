head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCompleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済完了ハンドラクラス(WEB3AioCashinSettleCompleteHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 屈陽 (中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSettleCompleteRequest;
import webbroker3.aio.message.WEB3AioCashinSettleCompleteResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCompleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済完了ハンドラ)<BR>
 * 決済完了ハンドラクラス
 *
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCompleteHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCompleteHandler.class);
    
    /**
     * (決済完了リクエスト)<BR>
     * 決済完了サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B0A09031F
     */
    public WEB3AioCashinSettleCompleteResponse handleCashinSettleCompleteRequest(
        WEB3AioCashinSettleCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "handleCashinSettleCompleteRequest(WEB3AioCashinSettleCompleteRequest l_request)";
        log.entering(l_strMethodName);
 
        WEB3AioCashinSettleCompleteService l_service;
        WEB3AioCashinSettleCompleteResponse l_response;
        
        try
        {
            //決済完了サービスを取得し
            l_service = 
                (WEB3AioCashinSettleCompleteService)Services.getService(
                WEB3AioCashinSettleCompleteService.class);
        }
        ////サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSettleCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "決済完了サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinSettleCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettleCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "決済完了処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
