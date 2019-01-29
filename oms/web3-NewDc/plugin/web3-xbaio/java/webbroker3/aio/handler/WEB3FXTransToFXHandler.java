head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.22.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FXへの振替ハンドラ(WEB3FXTransToFXHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/1/20 屈陽 (中訊) 新規作成   
                 : 2006/02/24 黄浩澎 (中訊) 仕様変更・モデル460
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransToFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransToFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransToFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXへの振替ハンドラ) <BR>
 * FXへの振替ハンドラクラス <BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3FXTransToFXHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3FXTransToFXHandler()
    {
    }

    /**
     * (注文確認) <BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * FXへの振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXConfirmResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3FXTransToFXConfirmResponse orderConfirm(
        WEB3FXTransToFXConfirmRequest l_request)
    {
        String l_strMethodName = "orderConfirm(WEB3FXTransToFXConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXConfirmResponse l_response;
        
        try
        {
            //FXへの振替サービスを取得し
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FXへの振替サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTransToFXConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FXへの振替サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (注文依頼) <BR>
     * 振替注文の依頼・登録処理を行う。 <BR>
     * <BR>
     * FXへの振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXAskingResponse
     * @@roseuid 41BCF4F900C4
     */
    public WEB3FXTransToFXAskingResponse orderAsking(
        WEB3FXTransToFXAskingRequest l_request)
    {
        String l_strMethodName = "orderAsking(WEB3FXTransToFXAskingRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXAskingResponse l_response;
        
        try
        {
            //FXへの振替サービスを取得し
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FXへの振替サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTransToFXAskingResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXAskingResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FXへの振替サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (注文完了) <BR>
     * 振替注文の完了処理を行う。 <BR>
     * <BR>
     * FXへの振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41BCF5190150
     */
    public WEB3FXTransToFXCompleteResponse orderComplete(
        WEB3FXTransToFXCompleteRequest l_request)
    {
        String l_strMethodName = "orderComplete(WEB3FXTransToFXCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXCompleteResponse l_response;
        
        try
        {
            //FXへの振替サービスを取得し
            l_service = 
                (WEB3FXTransToFXService)Services.getService(
                    WEB3FXTransToFXService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3FXTransToFXCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__FXへの振替サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTransToFXCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransToFXCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call FXへの振替サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }

    /**
     * (注文完了（SOAP接続）) <BR>
     * 振替注文の完了処理を行う。 <BR>
     * ※SOAP接続にて行う。<BR>
     * <BR>
     * FXへの振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3FXTransToFXCompleteResponse
     * @@roseuid 41BCF5190150
     */
    public WEB3FXTransToFXCompleteSoapResponse orderComplete(
        WEB3FXTransToFXCompleteSoapRequest l_request)
    {
        String STR_METHOD_NAME = "orderComplete(WEB3FXTransToFXCompleteSoapRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransToFXService l_service;
        WEB3FXTransToFXCompleteSoapResponse l_response;
        
        try
        {
            //FXへの振替サービスを取得し
            l_service = 
                (WEB3FXTransToFXService) Services.getService(WEB3FXTransToFXService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "FXへの振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request,
                "(FXへの振替)振替注文の完了処理（SOAP接続）の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FXTransToFXCompleteSoapResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "(FXへの振替)振替注文の完了処理（SOAP接続）の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
