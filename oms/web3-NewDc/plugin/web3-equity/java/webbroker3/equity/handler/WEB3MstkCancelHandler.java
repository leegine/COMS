head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消ハンドラ(WEB3MstkCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 カク寛新 (中訊) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequest;
import webbroker3.equity.message.WEB3MstkCancelCompleteResponse;
import webbroker3.equity.message.WEB3MstkCancelConfirmRequest;
import webbroker3.equity.message.WEB3MstkCancelConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文取消ハンドラ）。<BR>
 * <BR>
 * 株式ミニ投資注文取消ハンドラクラス
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelHandler implements MessageHandler 
{
    
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelHandler.class);
        
    /**
     * 
     */
    public WEB3MstkCancelHandler() 
    {
     
    }
    
    /**
     * （confirm注文）。<BR>
     * <BR>
     * ミニ株注文取消発注審査を行う。 <BR>
     * <BR>
     * 株式ミニ投資注文取消サービスを取得し、execute()<BR>
     * メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資注文取消確認リクエストデータオブジェクト
     * @@return WEB3MstkCancelConfirmResponse
     */
    public WEB3MstkCancelConfirmResponse handleConfirmOrder(WEB3MstkCancelConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = "handleConfirmOrder(WEB3MstkCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkCancelConfirmResponse l_response = null;
        WEB3MstkCancelService l_service = null;
        
        // 株式ミニ投資注文取消サービスを取得
        try
        {  
                      
            l_service=(WEB3MstkCancelService)Services.getService(WEB3MstkCancelService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資取消注文サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 株式ミニ投資注文取消サービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式ミニ投資取消注文サービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
            
			l_response = (WEB3MstkCancelConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"株式ミニ投資取消注文サービスに失敗しました。",
				l_ex);
			return l_response;                    
            
		}
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * （complete注文）。<BR>
     * <BR>
     * ミニ株注文取消登録を行う。<BR>
     * <BR>
     * 株式ミニ投資注文取消サービスを取得し、execute()<BR>
     * メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資注文取消完了リクエストデータオブジェクト
     * @@return WEB3MstkCancelCompleteResponse
     */
    public WEB3MstkCancelCompleteResponse handleCompleteOrder(WEB3MstkCancelCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME = "handleCompleteOrder(WEB3MstkCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
             
        WEB3MstkCancelCompleteResponse l_response = null;
        WEB3MstkCancelService l_service = null;
        
        // 株式ミニ投資注文取消サービスを取得   
        try
        {
            
            l_service=(WEB3MstkCancelService)Services.getService(WEB3MstkCancelService.class);
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資取消注文サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        //株式ミニ投資注文取消サービスを取得し、execute()メソッドをコールする       
        try
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式ミニ投資取消注文サービスに失敗しました。",
                l_ex);
            return l_response;
                    
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
            
			l_response = (WEB3MstkCancelCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"株式ミニ投資取消注文サービスに失敗しました。",
				l_ex);
			return l_response;
                    
		}
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
