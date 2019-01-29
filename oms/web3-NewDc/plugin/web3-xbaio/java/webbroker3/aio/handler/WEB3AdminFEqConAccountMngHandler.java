head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountMngHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 外株口座管理ハンドラクラス(WEB3AdminFEqConAccountMngHandler)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/03/21 黄建(中訊) 新規作成
 */

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountInfoSearchResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeCompleteResponse;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmRequest;
import webbroker3.aio.message.WEB3AdminFEqConAccountStateChangeConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株口座管理ハンドラ)<BR>
 * 外株口座管理ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFEqConAccountMngHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountMngHandler.class);
    
    /**
     * (入力画面表示)<BR>
     * 入力画面表示を行う。<BR>
     * <BR>
     * 外株口座管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchInputResponse
     * @@roseuid 41E3B8BC0194
     */
    public WEB3AdminFEqConAccountInfoSearchInputResponse displayInputScreen(
        WEB3AdminFEqConAccountInfoSearchInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "displayInputScreen(WEB3AdminFEqConAccountInfoSearchInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座管理サービス
        WEB3AdminFEqConAccountMngService l_service = null;
        //外株口座情報検索条件入力レスポンス
        WEB3AdminFEqConAccountInfoSearchInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株口座情報検索条件入力レスポンスの処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (検索画面表示)<BR>
     * 検索画面表示を行う。<BR>
     * <BR>
     * 外株口座管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountInfoSearchResponse
     * @@roseuid 41E3B8BC01B3
     */
    public WEB3AdminFEqConAccountInfoSearchResponse displaySearchScreen(
        WEB3AdminFEqConAccountInfoSearchRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "displaySearchScreen(WEB3AdminFEqConAccountInfoSearchRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座管理サービス
        WEB3AdminFEqConAccountMngService l_service = null;
        //外株口座情報検索レスポンス
        WEB3AdminFEqConAccountInfoSearchResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountInfoSearchResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株口座情報検索レスポンスの処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (口座開設状況変更確認)<BR>
     * 口座開設状況変更の確認処理を行う。<BR>
     * <BR>
     * 外株口座管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountStateChangeConfirmResponse
     * @@roseuid 41E3B8BC01E2
     */
    public WEB3AdminFEqConAccountStateChangeConfirmResponse accountStateChangeConfirm(
        WEB3AdminFEqConAccountStateChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "accountStateChangeConfirm(WEB3AdminFEqConAccountStateChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座管理サービス
        WEB3AdminFEqConAccountMngService l_service = null;
        //外株口座開設状況変更確認レスポンス
        WEB3AdminFEqConAccountStateChangeConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株口座開設状況変更確認レスポンスの処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (口座開設状況変更完了)<BR>
     * 口座開設状況変更の完了処理を行う。<BR>
     * <BR>
     * 外株口座管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConAccountStateChangeCompleteResponse
     * @@roseuid 41E3B8BC0201
     */
    public WEB3AdminFEqConAccountStateChangeCompleteResponse accountStateChangeComplete(
        WEB3AdminFEqConAccountStateChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "accountStateChangeComplete(WEB3AdminFEqConAccountStateChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外株口座管理サービス
        WEB3AdminFEqConAccountMngService l_service = null;
        //外株口座開設状況変更完了レスポンス
        WEB3AdminFEqConAccountStateChangeCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminFEqConAccountMngService) Services.getService(
                    WEB3AdminFEqConAccountMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株口座管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConAccountStateChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株口座開設状況変更完了レスポンスの処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
