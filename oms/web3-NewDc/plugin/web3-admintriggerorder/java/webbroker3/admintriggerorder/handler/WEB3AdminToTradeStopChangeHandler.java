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
filename	WEB3AdminToTradeStopChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止変更ハンドラ(WEB3AdminToTradeStopChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdConfirmResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopUpdInputResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止変更ハンドラ)<BR>
 * トリガー注文管理者・取扱停止変更ハンドラクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToTradeStopChangeHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopChangeHandler.class);
    
    /**
     * @@roseuid 4430DDF403A9
     */
    public WEB3AdminToTradeStopChangeHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 取扱停止変更入力画面表示処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更入力リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdInputResponse
     * @@roseuid 4410D9A00211
     */
    public WEB3AdminToTradeStopUpdInputResponse getInputScreen(WEB3AdminToTradeStopUpdInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToTradeStopUpdInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopUpdInputResponse l_response = null;
        WEB3AdminToTradeStopChangeService l_service = null;
        
        try
        {
            //getトリガー注文管理者・取扱停止変更サービス
            l_service = 
                (WEB3AdminToTradeStopChangeService) Services.getService(WEB3AdminToTradeStopChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopUpdInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm変更)<BR>
     * 取扱停止変更確認処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdConfirmResponse
     * @@roseuid 4410D9BA033A
     */
    public WEB3AdminToTradeStopUpdConfirmResponse confirmChange(WEB3AdminToTradeStopUpdConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmChange(WEB3AdminToTradeStopUpdConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopUpdConfirmResponse l_response = null;
        WEB3AdminToTradeStopChangeService l_service = null;
        
        try
        {
            //getトリガー注文管理者・取扱停止変更サービス
            l_service = 
                (WEB3AdminToTradeStopChangeService) Services.getService(WEB3AdminToTradeStopChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopUpdConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete変更)<BR>
     * 取扱停止変更完了処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止変更完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopUpdCompleteResponse
     * @@roseuid 4410FAF7002D
     */
    public WEB3AdminToTradeStopUpdCompleteResponse completeChange(WEB3AdminToTradeStopUpdCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeChange(WEB3AdminToTradeStopUpdCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopUpdCompleteResponse l_response = null;
        WEB3AdminToTradeStopChangeService l_service = null;
        
        try
        {
            //getトリガー注文管理者・取扱停止変更サービス
            l_service = 
                (WEB3AdminToTradeStopChangeService) Services.getService(WEB3AdminToTradeStopChangeService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopUpdCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopUpdCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者)取扱停止変更完了処理の実施に失敗しました。",
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
