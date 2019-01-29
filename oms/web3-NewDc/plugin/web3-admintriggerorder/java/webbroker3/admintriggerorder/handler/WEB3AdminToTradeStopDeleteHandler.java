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
filename	WEB3AdminToTradeStopDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止削除ハンドラ(WEB3AdminToTradeStopDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@鄭徳懇(中訊) 新規作成
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopDeleteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止削除ハンドラ)<BR>
 * トリガー注文管理者・取扱停止削除ハンドラ<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopDeleteHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopDeleteHandler.class);
    
    /**
     * @@roseuid 4430DB3F006D
     */
    public WEB3AdminToTradeStopDeleteHandler() 
    {
     
    }
    
    /**
     * (confirm削除)<BR>
     * 取扱停止削除確認処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止削除サービスImpl.<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止削除確認リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopDelConfirmResponse
     * @@roseuid 4410D20B025F
     */
    public WEB3AdminToTradeStopDelConfirmResponse confirmDelete(WEB3AdminToTradeStopDelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmDelete(WEB3AdminToTradeStopDelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopDelConfirmResponse l_response = null;
        WEB3AdminToTradeStopDeleteService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止削除サービス
            l_service = (WEB3AdminToTradeStopDeleteService) Services.getService(WEB3AdminToTradeStopDeleteService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止削除サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止削除)取扱停止削除確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止削除)取扱停止削除確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete削除)<BR>
     * 取扱停止削除完了処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止削除サービスImpl.<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止削除完了リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopDelCompleteResponse
     * @@roseuid 4410D29302EC
     */
    public WEB3AdminToTradeStopDelCompleteResponse completeDelete(WEB3AdminToTradeStopDelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeDelete(WEB3AdminToTradeStopDelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopDelCompleteResponse l_response = null;
        WEB3AdminToTradeStopDeleteService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止削除サービス
            l_service = (WEB3AdminToTradeStopDeleteService) Services.getService(WEB3AdminToTradeStopDeleteService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止削除サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止削除)取扱停止削除完了処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopDelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止削除)取扱停止削除完了処理の実施に失敗しました。",
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
