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
filename	WEB3AdminToTradeStopListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止一覧ハンドラ(WEB3AdminToTradeStopListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04　@鄭徳懇(中訊) 新規作成
*/
package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・取扱停止一覧ハンドラ)<BR>
 * トリガー注文管理者・取扱停止一覧ハンドラクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopListHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopListHandler.class);
   
    /**
     * @@roseuid 4430D9A0000F
     */
    public WEB3AdminToTradeStopListHandler() 
    {
     
    }
    
    /**
     * (get一覧画面)<BR>
     * 取扱停止一覧処理を行う。<BR>
     * <BR>
     * トリガー注文管理者・取扱停止一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止一覧リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopListResponse
     * @@roseuid 44101227004F
     */
    public WEB3AdminToTradeStopListResponse getListScreen(WEB3AdminToTradeStopListRequest l_request) 
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminToTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToTradeStopListResponse l_response = null;
        WEB3AdminToTradeStopListService l_service = null;
        
        try
        {            
            //getトリガー注文管理者・取扱停止一覧サービス
            l_service = (WEB3AdminToTradeStopListService) Services.getService(WEB3AdminToTradeStopListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・取扱停止一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToTradeStopListResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止一覧)取扱停止一覧処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(トリガー注文管理者・取扱停止一覧)取扱停止一覧処理の実施に失敗しました。",
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
