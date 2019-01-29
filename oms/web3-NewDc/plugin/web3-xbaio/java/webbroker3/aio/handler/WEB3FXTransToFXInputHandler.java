head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替入力ハンドラ(WEB3FXTransToFXInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXへの振替入力ハンドラ) <BR>
 * FXへの振替入力ハンドラクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXInputHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputHandler.class);
    
    /**
     * @@roseuid 41E780B100BB
     */
    public WEB3FXTransToFXInputHandler()
    {
    }

    /**
     * (入力画面表示) <BR>
     * FXへの振替入力サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXInputResponse
     * @@roseuid 41BCF37E0279
     */
    public WEB3FXTransToFXInputResponse displayInputScreen(
        WEB3FXTransToFXInputRequest l_request)
    {
        String l_strMethodName = "displayInputScreen(WEB3FXTransToFXInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXInputService l_service;
        WEB3FXTransToFXInputResponse l_response;
        
        try
        {
            //FXへの振替入力サービスを取得し
            l_service = 
                (WEB3FXTransToFXInputService)Services.getService(
                    WEB3FXTransToFXInputService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FXへの振替入力サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTransToFXInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FXへの振替入力サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}@
