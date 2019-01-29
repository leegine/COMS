head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinConfirmInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡確認入力ハンドラクラス(WEB3AdminAioCashinConfirmInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/22 屈陽 (中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashinConfirmInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinConfirmInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連絡確認入力ハンドラ)<BR>
 * 入金連絡確認入力ハンドラクラス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioCashinConfirmInputHandler implements MessageHandler 
{    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashinConfirmInputHandler.class);    
    
    /**
     * (入金連絡確認入力リクエスト)<BR>
     * 入金連絡確認入力サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AdminAioCashinConfirmInputResponse
     * @@roseuid 410749E301B3
     */
    public WEB3AdminAioCashinConfirmInputResponse handleCashinConfirmInputRequest(
        WEB3AdminAioCashinConfirmInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleCashinConfirmInputRequest(WEB3AdminAioCashinConfirmInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //入金連絡確認入力サービスを取得し
        WEB3AdminAioCashinConfirmInputService l_service = null;
        WEB3AdminAioCashinConfirmInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioCashinConfirmInputService) 
                Services.getService(WEB3AdminAioCashinConfirmInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "入金連絡確認入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioCashinConfirmInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashinConfirmInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "入金連絡確認入力処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
