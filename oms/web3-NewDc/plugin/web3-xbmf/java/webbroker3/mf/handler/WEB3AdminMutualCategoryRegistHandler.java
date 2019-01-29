head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualCategoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者カテゴリー登録ハンドラ(WEB3AdminMutualCategoryRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信管理者カテゴリー登録ハンドラ)<BR>
 * 投資信託管理者カテゴリー登録ハンドラクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminMutualCategoryRegistHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualCategoryRegistHandler.class);
    
    /**
     * (getカテゴリー登録入力画面)<BR>
     * 投資信託管理者カテゴリー登録入力画面取得処理を行う。<BR>
     * <BR>
     * 投信管理者カテゴリー登録サービスを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminMutualCategoryRegistInputResponse
     * @@roseuid 4153E9BB016F
     */
    public WEB3AdminMutualCategoryRegistInputResponse getCategoryRegistrInput(
        WEB3AdminMutualCategoryRegistInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getCategoryRegistrInput(WEB3AdminMutualCategoryRegistInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        //管理者カテゴリー登録サービス
        WEB3AdminMutualCategoryRegistService l_service = null;
        //投信管理者カテゴリー登録入力画面レスポンス
        WEB3AdminMutualCategoryRegistInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者カテゴリー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者カテゴリー登録入力画面レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (getカテゴリー変更入力画面)<BR>
     * 投資信託管理者カテゴリー変更入力画面取得処理を行う。<BR>
     * <BR>
     * 投信管理者カテゴリー登録サービスを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminMutualCategoryRegistChangeResponse
     * @@roseuid 4153ED7B01BD
     */
    public WEB3AdminMutualCategoryRegistChangeResponse getCategoryRegistChangeInput(
        WEB3AdminMutualCategoryRegistChangeRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getCategoryRegistChangeInput(WEB3AdminMutualCategoryRegistChangeRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //管理者カテゴリー登録サービス
        WEB3AdminMutualCategoryRegistService l_service = null;
        //投信管理者カテゴリー変更入力画面レスポンス
        WEB3AdminMutualCategoryRegistChangeResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者カテゴリー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistChangeResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者カテゴリー変更入力画面レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (confirmカテゴリー登録)<BR>
     * 投資信託管理者カテゴリー登録確認処理を行う。<BR>
     * <BR>
     * 投信管理者カテゴリー登録サービスを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminMutualCategoryRegistConfirmResponse
     * @@roseuid 4153ECDC0334
     */
    public WEB3AdminMutualCategoryRegistConfirmResponse confirmCategoryRegistRequest(
        WEB3AdminMutualCategoryRegistConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmCategoryRegistRequest(WEB3AdminMutualCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //管理者カテゴリー登録サービス
        WEB3AdminMutualCategoryRegistService l_service = null;
        //投信管理者カテゴリー登録確認レスポンス
        WEB3AdminMutualCategoryRegistConfirmResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者カテゴリー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者カテゴリー登録確認レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
   
    /**
     * (completeカテゴリー登録)<BR>
     * 投資信託管理者カテゴリー登録処理を行う。<BR>
     * <BR>
     * 投信管理者カテゴリー登録サービスを取得し、<BR>
     * execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AdminMutualCategoryRegistCompleteResponse
     * @@roseuid 4153ED4003A2
     */
    public WEB3AdminMutualCategoryRegistCompleteResponse completeCategoryRegistRequest(
        WEB3AdminMutualCategoryRegistCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeCategoryRegistRequest(WEB3AdminMutualCategoryRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //管理者カテゴリー登録サービス
        WEB3AdminMutualCategoryRegistService l_service = null;
        //投信管理者カテゴリー登録完了レスポンス
        WEB3AdminMutualCategoryRegistCompleteResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualCategoryRegistService) Services.getService(
                    WEB3AdminMutualCategoryRegistService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者カテゴリー登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualCategoryRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投信管理者カテゴリー登録完了レスポンスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
