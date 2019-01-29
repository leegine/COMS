head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替ハンドラクラス(WEB3FEqConTransferHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 韋念瓊 (中訊) 新規作成       
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FEqConTransferCompleteRequest;
import webbroker3.aio.message.WEB3FEqConTransferCompleteResponse;
import webbroker3.aio.message.WEB3FEqConTransferConfirmRequest;
import webbroker3.aio.message.WEB3FEqConTransferConfirmResponse;
import webbroker3.aio.message.WEB3FEqConTransferInputRequest;
import webbroker3.aio.message.WEB3FEqConTransferInputResponse;
import webbroker3.aio.service.delegate.WEB3FEqConTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株口座への振替ハンドラ)<BR>
 * 外株口座への振替ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferHandler.class);
    
    /**
     * @@roseuid 423559DA0000
     */
    public WEB3FEqConTransferHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 入力画面表示を行う。<BR>
     * <BR>
     * 外株口座への振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferInputResponse
     * @@roseuid 41E38F5E029A
     */
    public WEB3FEqConTransferInputResponse displayInputScreen(WEB3FEqConTransferInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayInputScreen(WEB3FEqConTransferInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替サービスインターフェイス
        WEB3FEqConTransferService l_service = null;          
         
        //外株口座への振替入力レスポンス
        WEB3FEqConTransferInputResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "入力画面表示に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (振替確認)<BR>
     * 振替の確認処理を行う。<BR>
     * <BR>
     * 外株口座への振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferConfirmResponse
     * @@roseuid 41E38F5E02AA
     */
    public WEB3FEqConTransferConfirmResponse transferConfirm(WEB3FEqConTransferConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "transferConfirm(WEB3FEqConTransferConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替サービスインターフェイス
        WEB3FEqConTransferService l_service = null;          
         
        //外株口座への振替確認レスポンス
        WEB3FEqConTransferConfirmResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "振替の確認に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (振替完了)<BR>
     * 振替の完了処理を行う。<BR>
     * <BR>
     * 外株口座への振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3FEqConTransferCompleteResponse
     * @@roseuid 41E38F5E02C9
     */
    public WEB3FEqConTransferCompleteResponse transferComplete(WEB3FEqConTransferCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "transferComplete(WEB3FEqConTransferCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座への振替サービスインターフェイス
        WEB3FEqConTransferService l_service = null;          
         
        //外株口座への振替完了レスポンス
        WEB3FEqConTransferCompleteResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3FEqConTransferService) Services.getService(
                        WEB3FEqConTransferService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座への振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FEqConTransferCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "振替の完了に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
