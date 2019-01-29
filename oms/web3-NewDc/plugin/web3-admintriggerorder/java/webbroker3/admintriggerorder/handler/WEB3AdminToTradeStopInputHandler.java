head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止入力ハンドラ(WEB3AdminToTradeStopInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止入力ハンドラ)<BR>
 * トリガー注文管理者・取扱停止入力ハンドラクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToTradeStopInputHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopInputHandler.class);
    
    /**
     * @@roseuid 4430DD36008C
     */
    public WEB3AdminToTradeStopInputHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 取扱停止入力画面表示処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止入力サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopInputResponse
     * @@roseuid 44192D370261
     */
    public WEB3AdminToTradeStopInputResponse getInputScreen(WEB3AdminToTradeStopInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopInputResponse l_response = null;
        WEB3AdminToTradeStopInputService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止入力サービス
            l_service = (WEB3AdminToTradeStopInputService) Services.getService(WEB3AdminToTradeStopInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopInputResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
