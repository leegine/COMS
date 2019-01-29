head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金入力ハンドラクラス(WEB3AioCashinInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinInputRequest;
import webbroker3.aio.message.WEB3AioCashinInputResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * ( オンライン入金入力ハンドラ)<BR>
 * オンライン入金入力ハンドラクラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinInputHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinInputHandler.class);
        
    /**
     * (オンライン入金入力リクエスト)<BR>
     * オンライン入金入力画面表示処理を行う。 <BR>
     * <BR>
     * オンライン入金入力サービスを取得し、<BR> 
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinInputRequest
     * @@roseuid 40F23147013A
     */
    public WEB3AioCashinInputResponse handleCashinInputRequest(
        WEB3AioCashinInputRequest l_request) 
    {
        String l_strMethodName = 
            "handleCashinInputRequest(WEB3AioCashinInputResponse l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinInputService l_service;
        WEB3AioCashinInputResponse l_response;
                
        try
        {
            //オンライン入金入力サービスを取得し
            l_service = 
                (WEB3AioCashinInputService)Services.getService(WEB3AioCashinInputService.class);            
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "オンライン入金入力サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "オンライン入金入力画面表示処理に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
