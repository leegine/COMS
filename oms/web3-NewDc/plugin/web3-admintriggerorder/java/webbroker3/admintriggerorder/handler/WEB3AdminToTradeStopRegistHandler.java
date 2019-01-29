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
filename	WEB3AdminToTradeStopRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止登録ハンドラ(WEB3AdminToTradeStopRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@鄭徳懇(中訊) 新規作成
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopRegConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止登録ハンドラ)<BR>
 * トリガー注文管理者・取扱停止登録ハンドラ<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopRegistHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopRegistHandler.class);
   
    /**
     * @@roseuid 4430DC7702EE
     */
    public WEB3AdminToTradeStopRegistHandler() 
    {
     
    }
    
    /**
     * (confirm登録)<BR>
     * 取扱停止登録確認処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止登録確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopRegConfirmResponse
     * @@roseuid 4410D57B01F2
     */
    public WEB3AdminToTradeStopRegConfirmResponse confirmRegist(WEB3AdminToTradeStopRegConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmRegist(WEB3AdminToTradeStopRegConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopRegConfirmResponse l_response = null;
        WEB3AdminToTradeStopRegistService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止登録サービス
            l_service = (WEB3AdminToTradeStopRegistService) Services.getService(WEB3AdminToTradeStopRegistService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止登録)取扱停止登録確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止登録)取扱停止登録確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete登録)<BR>
     * 取扱停止登録完了処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止登録サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止登録完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopRegCompleteResponse
     * @@roseuid 4410D6E103E6
     */
    public WEB3AdminToTradeStopRegCompleteResponse completeRegist(WEB3AdminToTradeStopRegCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeRegist(WEB3AdminToTradeStopRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopRegCompleteResponse l_response = null;
        WEB3AdminToTradeStopRegistService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止登録サービス
            l_service = (WEB3AdminToTradeStopRegistService) Services.getService(WEB3AdminToTradeStopRegistService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止登録)取扱停止登録完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopRegCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止登録)取扱停止登録完了処理の実施に失敗しました。",
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
