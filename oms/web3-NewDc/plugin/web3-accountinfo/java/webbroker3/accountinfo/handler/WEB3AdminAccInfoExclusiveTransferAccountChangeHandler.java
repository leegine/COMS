head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座変更ハンドラ(WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報専用振込先口座変更ハンドラ)<BR>
 * 管理者お客様情報専用振込先口座変更ハンドラ<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class);
    
    /**
     * @@roseuid 418F3A13008C
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 専用振込先口座変更入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更入力ﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B5CF30163
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse inputScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountChangeService l_service = null;
        
        // 管理者お客様情報専用振込先口座変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountChangeService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報専用振込先口座変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更確認)<BR>
     * 専用振込先口座変更確認処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更確認ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse
     * @@roseuid 415CC65E006A
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse changeConfirm(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeConfirm(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountChangeService l_service = null;
        
        // 管理者お客様情報専用振込先口座変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountChangeService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報専用振込先口座変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更完了)<BR>
     * 専用振込先口座変更完了処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報専用振込先口座変更サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse
     * @@roseuid 415CC65E008A
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse changeComplete(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "changeComplete(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse l_response = null;
        WEB3AdminAccInfoExclusiveTransferAccountChangeService l_service = null;
        
        // 管理者お客様情報専用振込先口座変更サービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoExclusiveTransferAccountChangeService)Services.getService(WEB3AdminAccInfoExclusiveTransferAccountChangeService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報専用振込先口座変更サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報専用振込先口座変更サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
