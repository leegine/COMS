head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替取消ハンドラクラス(WEB3FEqConTransferCancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 韋念瓊 (中訊) 新規作成       
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectRequest;
import webbroker3.aio.message.WEB3FEqConTransferCancelSelectResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株口座への振替取消ハンドラ)<BR>
 * 外株口座への振替取消ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelHandler.class);
    
    /**
     * @@roseuid 42355E700138
     */
    public WEB3FEqConTransferCancelHandler() 
    {
     
    }
    
    /**
     * (選択画面表示)<BR>
     * 選択画面表示を行う。<BR>
     * <BR>
     * 外株口座への振替取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelSelectResponse
     * @@roseuid 41E394CF000A
     */
    public WEB3FEqConTransferCancelSelectResponse displaySelectScreen(WEB3FEqConTransferCancelSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displaySelectScreen(WEB3FEqConTransferCancelSelectRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替取消サービスインターフェイス
        WEB3FEqConTransferCancelService l_service = null;          
         
        //外株口座への振替取消選択レスポンス
        WEB3FEqConTransferCancelSelectResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "選択画面表示に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (取消確認)<BR>
     * 振替の取消確認処理を行う。<BR>
     * <BR>
     * 外株口座への振替取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelConfirmResponse
     * @@roseuid 41E394CF0029
     */
    public WEB3FEqConTransferCancelConfirmResponse cancelConfirm(WEB3FEqConTransferCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cancelConfirm(WEB3FEqConTransferCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替取消サービスインターフェイス
        WEB3FEqConTransferCancelService l_service = null;          
         
        //外株口座への振替取消確認レスポンス
        WEB3FEqConTransferCancelConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "振替の取消確認に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (取消完了)<BR>
     * 振替の取消完了処理を行う。<BR>
     * <BR>
     * 外株口座への振替取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCancelCompleteResponse
     * @@roseuid 41E394CF0049
     */
    public WEB3FEqConTransferCancelCompleteResponse cancelCompelte(WEB3FEqConTransferCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "cancelCompelte(WEB3FEqConTransferCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替取消サービスインターフェイス
        WEB3FEqConTransferCancelService l_service = null;          
         
        //外株口座への振替取消完了レスポンス
        WEB3FEqConTransferCancelCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferCancelService) Services.getService(
                        WEB3FEqConTransferCancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "振替の取消完了に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
