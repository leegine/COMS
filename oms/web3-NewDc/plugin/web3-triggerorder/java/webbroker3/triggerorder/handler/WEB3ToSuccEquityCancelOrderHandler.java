head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文取消ハンドラ(WEB3ToSuccEquityCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）株式注文取消ハンドラ)<BR>
 * （連続）株式注文取消ハンドラクラス。<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3ToSuccEquityCancelOrderHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityCancelOrderHandler.class);

    /**
     * @@roseuid 4369ED2C0251
     */
    public WEB3ToSuccEquityCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm注文取消)<BR>
     * （連続）現物株式注文取消確認処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文取消サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccEquityCancelConfirmResponse
     * @@roseuid 433A07100193
     */
    public WEB3SuccEquityCancelConfirmResponse confirmCancelOrder(WEB3SuccEquityCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCancelOrder(WEB3SuccEquityCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityCancelConfirmResponse l_response = null;
        WEB3ToSuccEquityCancelOrderService l_service = null;
        
        // （連続）株式注文取消サービスを取得し、execute()メソッドをコールする
        try
        {
            l_service = (WEB3ToSuccEquityCancelOrderService)Services.getService(WEB3ToSuccEquityCancelOrderService.class); 
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_service.execute(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm注文取消確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
        catch(WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm注文取消確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
         
        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete注文取消)<BR>
     * （連続）現物株式注文取消完了処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文取消サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccEquityCancelCompleteResponse
     * @@roseuid 433A07100195
     */
    public WEB3SuccEquityCancelCompleteResponse completeCancelOrder(WEB3SuccEquityCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCancelOrder(WEB3SuccEquityCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityCancelCompleteResponse l_response = null;
        WEB3ToSuccEquityCancelOrderService l_service = null;
        
        // （連続）株式注文取消サービスを取得し、execute()メソッドをコールする
        try
        {
            l_service = (WEB3ToSuccEquityCancelOrderService)Services.getService(WEB3ToSuccEquityCancelOrderService.class); 
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002 ;
            log.error(l_request,
                "（連続）株式注文取消サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        try
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_service.execute(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete注文取消完了に失敗しました。", l_ex);  
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }
        catch(WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete注文取消完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
         }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
