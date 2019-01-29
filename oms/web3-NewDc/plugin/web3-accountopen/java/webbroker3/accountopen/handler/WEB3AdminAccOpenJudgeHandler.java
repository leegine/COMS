head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenJudgeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査ハンドラ (WEB3AdminAccOpenJudgeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 周捷(中訊) 新規作成
*/

package webbroker3.accountopen.handler;

import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (管理者口座開設審査ハンドラ)<BR>
 * 管理者口座開設審査ハンドラ
 *   
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAccOpenJudgeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenJudgeHandler.class);
    
    /**
     * @@roseuid 44912BFD02BF
     */
    public WEB3AdminAccOpenJudgeHandler() 
    {
     
    }
    
    /**
     * (審査対象一覧検索画面表示)<BR>
     * 審査対象一覧検索画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査対象一覧検索リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectListSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473A7A102DE
     */
    public WEB3AdminAccOpenInspectListSearchResponse inspectObjectListSearchScreen(
        WEB3AdminAccOpenInspectListSearchRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inspectObjectListSearchScreen(WEB3AdminAccOpenInspectListSearchResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectListSearchResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;  
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査対象一覧検索画面表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (一覧画面表示)<BR>
     * 一覧画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査一覧リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473A8DD005E
     */
    public WEB3AdminAccOpenInspectListResponse screenList(
        WEB3AdminAccOpenInspectListRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " screenList(WEB3AdminAccOpenInspectListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectListResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査一覧画面表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (承認確認)<BR>
     * 承認確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査承認確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectConsentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AA1F034F
     */
    public WEB3AdminAccOpenInspectConsentConfirmResponse consentConfirm(
        WEB3AdminAccOpenInspectConsentConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " consentConfirm(WEB3AdminAccOpenInspectConsentConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectConsentConfirmResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査承認確認処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (承認完了)<BR>
     * 承認完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査承認完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectConsentCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AA8C01DD
     */
    public WEB3AdminAccOpenInspectConsentCompleteResponse consentComplete(
        WEB3AdminAccOpenInspectConsentCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " consentComplete(WEB3AdminAccOpenInspectConsentCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectConsentCompleteResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectConsentCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査承認完了処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (否認確認)<BR>
     * 否認確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査否認確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectDenyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AB130090
     */
    public WEB3AdminAccOpenInspectDenyConfirmResponse denyConfirm(
        WEB3AdminAccOpenInspectDenyConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " denyConfirm(WEB3AdminAccOpenInspectDenyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectDenyConfirmResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査否認確認処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (否認完了)<BR>
     * 否認完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設審査サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設審査承認完了リクエスト データオブジェクト<BR>
     * @@return WEB3AdminAccOpenInspectDenyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4473AB2A031A
     */
    public WEB3AdminAccOpenInspectDenyCompleteResponse denyComplete(
        WEB3AdminAccOpenInspectDenyCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " denyComplete(WEB3AdminAccOpenInspectDenyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenInspectDenyCompleteResponse l_response = null;
        WEB3AdminAccOpenJudgeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenJudgeService)Services.getService(WEB3AdminAccOpenJudgeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設審査サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設審査サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenInspectDenyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 管理者口座開設審査否認完了処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
