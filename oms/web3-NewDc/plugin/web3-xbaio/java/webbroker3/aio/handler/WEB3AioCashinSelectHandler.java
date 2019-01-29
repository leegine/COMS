head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSelectHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金選択ハンドラクラス(WEB3AioCashinSelectHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSelectRequest;
import webbroker3.aio.message.WEB3AioCashinSelectResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSelectService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (オンライン入金選択ハンドラ)<BR>
 * オンライン入金選択ハンドラクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSelectHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSelectHandler.class);
        
    /**
     * (オンライン入金選択リクエスト)<BR>
     * オンライン入金選択画面表示処理を行う。<BR> 
     * <BR>
     * オンライン入金選択サービスを取得し、 <BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinSelectResponse
     * @@roseuid 40F1F23F0050
     */
    public WEB3AioCashinSelectResponse handleCashinSelectRequest(
        WEB3AioCashinSelectRequest l_request) 
    {
        final String l_strMethodName = 
            "handleCashinSelectRequest(WEB3AioCashinSelectRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSelectService l_service;
        WEB3AioCashinSelectResponse l_response;        
        try
        {
            //オンライン入金選択サービスを取得し
            l_service = 
                (WEB3AioCashinSelectService)Services.getService(
                WEB3AioCashinSelectService.class);            
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "オンライン入金選択サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinSelectResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSelectResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "オンライン入金選択画面表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);      
        
        return l_response; 
    }
}
@
