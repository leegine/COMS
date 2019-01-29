head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXAccOpenHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX口座開設ハンドラ(WEB3FXAccOpenHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/25 周勇 (中訊) 新規作成
Revesion History : 2008/04/08 馮海濤 (中訊) 仕様変更・モデル833　@モデル840
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3FXAccOpenAskingResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXAccOpenConfirmRequest;
import webbroker3.aio.message.WEB3FXAccOpenConfirmResponse;
import webbroker3.aio.service.delegate.WEB3FXAccOpenService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX口座開設ハンドラ) <BR>
 * FX口座開設ハンドラ <BR>
 * @@author 周勇(中訊)
 * @@version 1.0 
 */
public class WEB3FXAccOpenHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenHandler.class);
    
    /**
     * @@roseuid 41E78297029F
     */
    public WEB3FXAccOpenHandler()
    {
    }

    /**
     * (口座開設確認) <BR>
     * 口座開設処理の発注審査を行う。 <BR>
     * <BR>
     * FX口座開設サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenConfirmResponse
     * @@roseuid 41C78D7103BC
     */
    public WEB3FXAccOpenConfirmResponse accountOpenConfirm(
        WEB3FXAccOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "accountOpenConfirm(WEB3FXAccOpenConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenService l_service = null;
        WEB3FXAccOpenConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenService) Services.getService(
                    WEB3FXAccOpenService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXAccOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX口座開設サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX口座開設確認レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (口座開設依頼) <BR>
     * 口座開設処理の依頼を行う。 <BR>
     * <BR>
     * FX口座開設サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenAskingResponse
     * @@roseuid 41C78D7103DC
     */
    public WEB3FXAccOpenAskingResponse accountOpenAsking(
        WEB3FXAccOpenAskingRequest l_request)
    {
        final String STR_METHOD_NAME =
            "accountOpenAsking(WEB3FXAccOpenAskingRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenService l_service = null;
        WEB3FXAccOpenAskingResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenService) Services.getService(
                    WEB3FXAccOpenService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXAccOpenAskingResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX口座開設サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenAskingResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenAskingResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX口座開設依頼レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }

    /**
     * (口座開設完了) <BR>
     * 口座開設処理の登録を行う。 <BR>
     * <BR>
     * FX口座開設サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenCompleteResponse
     * @@roseuid 41C78D720003
     */
    public WEB3FXAccOpenCompleteResponse accountOpenComplete(
        WEB3FXAccOpenCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "accountOpenComplete(WEB3FXAccOpenCompleteRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXAccOpenService l_service = null;
        WEB3FXAccOpenCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenService) Services.getService(
                    WEB3FXAccOpenService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXAccOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX口座開設サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "FX口座開設完了レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (口座開設完了（SOAP接続）)<BR>
     * 口座開設の完了処理を行う。<BR>
     * ※SOAP接続にて行う。<BR>
     * <BR>
     * <BR>
     * FX口座開設サービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3FXAccOpenCompleteSoapResponse
     * @@roseuid 41C78D720003
     */
    public WEB3FXAccOpenCompleteSoapResponse accountOpenCompleteSoap(
        WEB3FXAccOpenCompleteSoapRequest l_request)
    {
        final String STR_METHOD_NAME = "accountOpenCompleteSoap(WEB3FXAccOpenCompleteSoapRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FXAccOpenService l_service = null;
        WEB3FXAccOpenCompleteSoapResponse l_response = null;
        try
        {
            l_service =
                (WEB3FXAccOpenService)Services.getService(
                    WEB3FXAccOpenService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "FX口座開設サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FXAccOpenCompleteSoapResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FX口座開設完了レスポンス（SOAP接続）の処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3FXAccOpenCompleteSoapResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "FX口座開設完了レスポンス（SOAP接続）の処理に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
