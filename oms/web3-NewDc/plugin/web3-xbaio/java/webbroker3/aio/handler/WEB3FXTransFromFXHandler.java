head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FXから振替ハンドラ(WEB3FXTransFromFXHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/21 黄建(中訊) 新規作成
 Revesion History : 2008/04/08 王志葵(中訊) モデルNo.832
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXから振替ハンドラ) <BR>
 * FXから振替ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FXTransFromFXHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXHandler.class);
   
    /**
     * (注文確認) <BR>
     * 振替注文の発注審査を行う。 <BR>
     * <BR>
     * FXから振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXConfirmResponse
     * @@roseuid 41BCF8760141
     */
    public WEB3FXTransFromFXConfirmResponse orderConfirm(
        WEB3FXTransFromFXConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderConfirm(WEB3FXTransFromFXConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FXから振替サービス
        WEB3FXTransFromFXService l_service = null;
        //FXから振替確認レスポンス
        WEB3FXTransFromFXConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FXから振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FXから振替確認レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (注文依頼) <BR>
     * 振替注文の依頼処理を行う。 <BR>
     * <BR>
     * FXから振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXAskingResponse
     * @@roseuid 41BCF8760143
     */
    public WEB3FXTransFromFXAskingResponse orderAsking(
        WEB3FXTransFromFXAskingRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderAsking(WEB3FXTransFromFXAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //FXから振替サービス
        WEB3FXTransFromFXService l_service = null;
        //FXから振替依頼レスポンスクラス
        WEB3FXTransFromFXAskingResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FXから振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXAskingResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FXから振替依頼レスポンスクラスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (注文完了) <BR>
     * 振替注文の登録処理を行う。 <BR>
     * <BR>
     * FXから振替サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXTransFromFXCompleteResponse
     * @@roseuid 41BCF8760145
     */
    public WEB3FXTransFromFXCompleteResponse orderComplete(
        WEB3FXTransFromFXCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderComplete(WEB3FXTransFromFXCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //FXから振替サービス
        WEB3FXTransFromFXService l_service = null;
        //FXから振替完了レスポンスクラス
        WEB3FXTransFromFXCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService) Services.getService(
            WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FXから振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FXから振替完了レスポンスクラスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (注文完了（SOAP接続）)<BR>
     * 振替注文の完了処理を行う。<BR>
     * ※SOAP接続にて行う。<BR>
     * <BR>
     * FXからの振替サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request リクエストデータ
     * @@return WEB3FXTransFromFXCompleteSoapResponse
     */
    public WEB3FXTransFromFXCompleteSoapResponse orderCompleteSoap(
        WEB3FXTransFromFXCompleteSoapRequest l_request)
    {
        final String STR_METHOD_NAME =
            "orderCompleteSoap(WEB3FXTransFromFXCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        //FXから振替サービス
        WEB3FXTransFromFXService l_service = null;
        //FXから振替完了レスポンスクラス
        WEB3FXTransFromFXCompleteSoapResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXTransFromFXService)Services.getService(
                    WEB3FXTransFromFXService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FXから振替サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FXから振替完了レスポンス（SOAP接続）クラスの処理に失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FXTransFromFXCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FXから振替完了レスポンス（SOAP接続）クラスの処理に失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
