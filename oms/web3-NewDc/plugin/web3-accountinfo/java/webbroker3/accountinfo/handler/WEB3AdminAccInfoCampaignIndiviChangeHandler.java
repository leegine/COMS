head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignIndiviChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 個別顧客指定変更ハンドラ(WEB3AdminAccInfoCampaignIndiviChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/2/1  齊珂(中訊) 新規作成
Revision History : 2007/2/1  モデルNo.168
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCampaignIndiviInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (個別顧客指定変更ハンドラ)<BR>
 * 個別顧客指定変更ハンドラ<BR>
 * <BR>
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignIndiviChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignIndiviChangeHandler.class);
    
    /**
     * @@roseuid 45C08B510138
     */
    public WEB3AdminAccInfoCampaignIndiviChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 手数料キャンペーン顧客別入力画面表示処理を実施する。<BR>
     * <BR>
     * 個別顧客指定変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 個別顧客指定入力リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviInputResponse
     * @@roseuid 459DAF890101
     */
    public WEB3AdminAccInfoCampaignIndiviInputResponse inputScreenDisplay(
        WEB3AdminAccInfoCampaignIndiviInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " inputScreenDisplay(WEB3AdminAccInfoCampaignIndiviInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviInputResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // 個別顧客指定変更サービスを取得
        try
        {          
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "個別顧客指定変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // 個別顧客指定変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_service.execute(l_request);    
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "個別顧客指定変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (個別顧客指定確認)<BR>
     * 個別顧客指定確認処理を行う。 <BR>
     * <BR>
     * 個別顧客指定変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 個別顧客指定確認リクエストデータオブジェクト<BR>
     * 
     * @@return WEB3AdminAccInfoCampaignIndiviConfirmResponse
     * @@roseuid 459DB143016E
     */
    public WEB3AdminAccInfoCampaignIndiviConfirmResponse IndiviChangeConfirm(
        WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " IndiviChangeConfirm(WEB3AdminAccInfoCampaignIndiviConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviConfirmResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // 個別顧客指定変更サービスを取得
        try
        {           
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "個別顧客指定変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // 個別顧客指定変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_service.execute(l_request);     
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviConfirmResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "個別顧客指定変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (個別顧客指定完了)<BR>
     * 個別顧客指定完了処理を行う。 <BR>
     * <BR>
     * 個別顧客指定変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 個別顧客指定完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccInfoCampaignIndiviCompleteResponse
     * @@roseuid 459DB1430170
     */
    public WEB3AdminAccInfoCampaignIndiviCompleteResponse IndiviChangeComplete(
        WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " IndiviChangeComplete(WEB3AdminAccInfoCampaignIndiviCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCampaignIndiviCompleteResponse l_response = null;
        WEB3AdminAccInfoCampaignIndiviChangeService l_service = null;
        
        // 個別顧客指定変更サービスを取得
        try
        {           
            l_service=(WEB3AdminAccInfoCampaignIndiviChangeService)Services.getService
                (WEB3AdminAccInfoCampaignIndiviChangeService.class);            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "個別顧客指定変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
        }
        
        // 個別顧客指定変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_service.execute(l_request);     
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoCampaignIndiviCompleteResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "個別顧客指定変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
