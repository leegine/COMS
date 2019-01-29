head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウトハンドラ(WEB3AdminFPTForceLogoutHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutCompleteResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutInputResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceRequest;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutReferenceResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutConfirmResponse;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * 管理者 書面未承諾 強制ログアウトハンドラ
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutHandler implements MessageHandler
{
    /**
     * @@log WEB3LogUtility
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutHandler.class);
    
    /**
     * @@roseuid 47DF467600C3
     */
    public WEB3AdminFPTForceLogoutHandler() 
    {
     
    }
    
    /**
     * get入力画面
     * 
     * 管理者 書面未承諾 強制ログアウト入力画面表示処理を行う。 
     * 
     * 管理者 書面未承諾 強制ログアウトサービスImplを取得し、 
     * execute()メソッドをコールする。
     * @@param 管理者 書面未承諾 強制ログアウト入力リクエスト - 管理者 書面未承諾 
     * 強制ログアウト入力リクエスト
     * @@return 
     * docadmin.書面未承諾強制ログアウトサービスモデル（管理者）.メッセージ.WEB3AdminFP
     * TForceLogoutInputResponse
     * @@roseuid 47D638950042
     */
    public WEB3AdminFPTForceLogoutInputResponse getInputPage(WEB3AdminFPTForceLogoutInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getInputPage(WEB3AdminFPTForceLogoutInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutInputResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputPage()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * validate強制ログアウト
     * 
     * 管理者 書面未承諾 強制ログアウト確認画面表示処理を行う。 
     * 
     * 管理者 書面未承諾 強制ログアウトサービスImplを取得し、 
     * execute()メソッドをコールする。
     * @@param 管理者 書面未承諾 強制ログアウト確認リクエスト - 管理者 書面未承諾 
     * 強制ログアウト確認リクエスト
     * @@return 
     * docadmin.書面未承諾強制ログアウトサービスモデル（管理者）.メッセージ.WEB3AdminFP
     * TForceLogoutconfirmResponse
     * @@roseuid 47D638F601B1
     */
    public WEB3AdminFPTForceLogoutConfirmResponse validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "validateForceLogout(WEB3AdminFPTForceLogoutConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutConfirmResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateForceLogout()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * submit強制ログアウト
     * 
     * 管理者 書面未承諾 強制ログアウト完了画面表示処理を行う。 
     * 
     * 管理者 書面未承諾 強制ログアウトサービスImplを取得し、 
     * execute()メソッドをコールする。
     * @@param 管理者 書面未承諾 強制ログアウト完了リクエスト - 管理者 書面未承諾 
     * 強制ログアウト完了リクエスト
     * @@return 
     * docadmin.書面未承諾強制ログアウトサービスモデル（管理者）.メッセージ.WEB3AdminFP
     * TForceLogoutCompleteResponse
     * @@roseuid 47D6393F0286
     */
    public WEB3AdminFPTForceLogoutCompleteResponse submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "submitForceLogout(WEB3AdminFPTForceLogoutCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutCompleteResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitForceLogout()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * submit強制ログアウト
     * 
     * 管理者 書面未承諾 強制ログアウト完了画面表示処理を行う。 
     * 
     * 管理者 書面未承諾 強制ログアウトサービスImplを取得し、 
     * execute()メソッドをコールする。
     * @@param 管理者 書面未承諾 強制ログアウト結果照会リクエスト - 管理者 書面未承諾 
     * 強制ログアウト結果照会リクエスト
     * @@return 
     * docadmin.書面未承諾強制ログアウトサービスモデル（管理者）.メッセージ.WEB3AdminFP
     * TForceLogoutReferenceResponse
     * @@roseuid 47DB26750220
     */
    public WEB3AdminFPTForceLogoutReferenceResponse getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "getResultRefrence(WEB3AdminFPTForceLogoutReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminFPTForceLogoutReferenceResponse l_response = null;
        WEB3AdminFPTForceLogoutService l_service = null;

        try
        {
            l_service =
                (WEB3AdminFPTForceLogoutService) Services.getService(
                        WEB3AdminFPTForceLogoutService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminFPTForceLogoutServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getResultRefrence()", l_exp);
            l_response =
                (WEB3AdminFPTForceLogoutReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
