head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FXから振替入力ハンドラ(WEB3FXTransFromFXInputHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 黄建(中訊) 新規作成
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXから振替入力ハンドラ) <BR>
 * FXから振替入力ハンドラクラス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTransFromFXInputHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FXTransFromFXInputHandler.class);

    /**
     * (入力画面表示) <BR>
     * FXから振替入力サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXInputResponse
     * @@roseuid 41BCF7FE0344
     */
    public WEB3FXTransFromFXInputResponse displayInputScreen(
        WEB3FXTransFromFXInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3FXTransFromFXCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FXから振替入力サービス
        WEB3FXTransFromFXInputService l_service = null;
        //FXから振替入力レスポンスクラス
        WEB3FXTransFromFXInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXInputService) Services.getService(
                    WEB3FXTransFromFXInputService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXTransFromFXInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FXから振替入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FXから振替入力レスポンスクラスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
