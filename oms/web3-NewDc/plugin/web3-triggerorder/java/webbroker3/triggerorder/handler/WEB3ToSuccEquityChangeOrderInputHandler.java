head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正入力ハンドラ(WEB3ToSuccEquityChangeOrderInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）現物株式注文訂正入力ハンドラ)<BR>
 * （連続）現物株式注文訂正入力ハンドラクラス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderInputHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderInputHandler.class);
    
    /**
     * @@roseuid 4369ED2E03A9
     */
    public WEB3ToSuccEquityChangeOrderInputHandler() 
    {
     
    }
    
    /**
     * (get訂正入力画面)<BR>
     * （連続）現物株式注文訂正入力画面表示処理を行う。<BR>
     * <BR>
     * （連続）現物株式注文訂正入力サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param  l_request - リクエストデータ。<BR>
     * @@return WEB3SuccEquityChangeInputResponse
     * @@roseuid 433915D503C3
     */
    public WEB3SuccEquityChangeInputResponse getChangeInputScreen(WEB3SuccEquityChangeInputRequest  l_request) 
    {
        final String STR_METHOD_NAME = " getChangeInputScreen(WEB3SuccEquityChangeInputRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeInputResponse l_response = null;
        WEB3ToSuccEquityChangeOrderInputService l_service = null;       

        // （連続）現物株式注文訂正入力サービスインタフェースを取得し、execute()メソッドをコールする
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderInputService) Services.getService(WEB3ToSuccEquityChangeOrderInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）現物株式注文訂正入力サービスインタフェースの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "get訂正入力画面に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "get訂正入力画面に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
