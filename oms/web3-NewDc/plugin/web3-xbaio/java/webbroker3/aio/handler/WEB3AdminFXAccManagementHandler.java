head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXAccManagementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者FX口座管理ハンドラクラス(WEB3AdminFXAccManagementHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 王蘭芬(中訊)新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeConfirmResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoChangeInputResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchConditionResponse;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFXAccInfoSearchResponse;
import webbroker3.aio.service.delegate.WEB3AdminFXAccManagementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者FX口座管理ハンドラ) <BR>
 * 管理者FX口座管理ハンドラクラス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AdminFXAccManagementHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXAccManagementHandler.class);

    /**
     * @@roseuid 41E7934A01D4
     */
    public WEB3AdminFXAccManagementHandler()
    {
    }

    /**
     * (get条件入力画面) <BR>
     * (管理者FX口座管理)口座情報検索条件入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX口座管理サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。
     * 
     * @@param l_request - 管理者・FX口座情報検索条件入力リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoSearchConditionResponse
     * @@roseuid 41BD352201F0
     */
    public WEB3AdminFXAccInfoSearchConditionResponse getCondInputScreen(
        WEB3AdminFXAccInfoSearchConditionRequest l_request)
    {
        final String STR_METHOD_NAME = "getCondInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座管理サービスを取得し
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoSearchConditionResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccInfoSearchConditionResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchConditionResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座管理)口座情報検索条件入力画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get検索結果) <BR> 
     * (管理者FX口座管理)口座情報検索処理を行う。 <BR>
     * <BR>
     * 管理者FX口座管理サービスImplを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座情報検索リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoSearchResponse
     * @@roseuid 41BD3607020F
     */
    public WEB3AdminFXAccInfoSearchResponse getQueryResult(
        WEB3AdminFXAccInfoSearchRequest l_request)
    {
        final String STR_METHOD_NAME = "getQueryResult()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座管理サービスを取得し
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoSearchResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccInfoSearchResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座管理)口座情報検索処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get変更入力画面) <BR>
     * (管理者FX口座管理)口座情報変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者FX口座管理サービスImplを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座情報変更入力リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeInputResponse
     * @@roseuid 41BD363E0329
     */
    public WEB3AdminFXAccInfoChangeInputResponse getUpdInputScreen(
        WEB3AdminFXAccInfoChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getUpdInputScreen()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座管理サービスを取得し
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeInputResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccInfoChangeInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座管理)口座情報変更入力画面表示処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm変更) <BR>
     * (管理者FX口座管理)口座情報変更確認処理を行う。 <BR>
     * <BR>
     * 管理者FX口座管理サービスImplを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座情報変更確認リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeConfirmResponse
     * @@roseuid 41BD36830357
     */
    public WEB3AdminFXAccInfoChangeConfirmResponse confirmChanger(
        WEB3AdminFXAccInfoChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChanger()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座管理サービスを取得し
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeConfirmResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccInfoChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座管理)口座情報変更確認処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete変更) <BR>
     * (管理者FX口座管理)口座情報変更完了処理を行う。 <BR>
     * <BR>
     * 管理者FX口座管理サービスImplを取得し、execute()メソッドをコールする。<BR>
     * 
     * @@param l_request - 管理者・FX口座情報変更完了リクエストオブジェクト
     * @@return WEB3AdminFXAccInfoChangeCompleteResponse
     * @@roseuid 41BD36AA003B
     */
    public WEB3AdminFXAccInfoChangeCompleteResponse completeChange(
        WEB3AdminFXAccInfoChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChange()";
        log.entering(STR_METHOD_NAME);
        
        //管理者FX口座管理サービスを取得し
        WEB3AdminFXAccManagementService l_service = null;
        WEB3AdminFXAccInfoChangeCompleteResponse l_response = null;

        try
        {
            l_service = (WEB3AdminFXAccManagementService) 
                Services.getService(WEB3AdminFXAccManagementService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者FX口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminFXAccInfoChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFXAccInfoChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "(管理者FX口座管理)口座情報変更完了処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
