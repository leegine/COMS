head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込ハンドラ(WEB3AccOpenRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設申込ハンドラ)<BR>
 * 口座開設申込ハンドラ<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenRegistHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenRegistHandler.class);
    
    /**
     * @@roseuid 41B45E750157
     */
    public WEB3AccOpenRegistHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 口座開設申込入力画面表示処理を行う。 <BR>
     * <BR>
     * 口座開設申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設申込入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 419C90400293
     */
    public WEB3AccOpenApplyInputResponse inputScreenDisplay(WEB3AccOpenApplyInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccOpenApplyInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyInputResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座開設申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AccOpenApplyInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "口座開設申込入力画面表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (申込確認)<BR>
     * 口座開設申込確認処理を行う。<BR>
     * <BR>
     * 口座開設申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設申込確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse
     * @@roseuid 419C904002A3
     */
    public WEB3AccOpenApplyConfirmResponse registConfirm(WEB3AccOpenApplyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registConfirm(WEB3AccOpenApplyConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyConfirmResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座開設申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AccOpenApplyConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "口座開設申込確認処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (申込完了)<BR>
     * 口座開設申込完了処理を行う。<BR>
     * <BR>
     * 口座開設申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設申込完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse
     * @@roseuid 419C904002A5
     */
    public WEB3AccOpenApplyCompleteResponse registComplete(WEB3AccOpenApplyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registComplete(WEB3AccOpenApplyCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3AccOpenApplyCompleteResponse l_response = null;
        WEB3AccOpenRegistService l_service = null;      
        try
        {
            //get service
            l_service = 
                (WEB3AccOpenRegistService)Services.getService(
                    WEB3AccOpenRegistService.class);
        }
        catch (Exception l_ex)
        {
            
            l_response = (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "口座開設申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AccOpenApplyCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "口座開設申込完了処理に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
