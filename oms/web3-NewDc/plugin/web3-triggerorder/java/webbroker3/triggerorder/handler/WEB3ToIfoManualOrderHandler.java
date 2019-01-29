head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToIfoManualOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP手動発注ハンドラ(WEB3ToIfoManualOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17　@余新敏(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmRequest;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP手動発注ハンドラ)<BR>
 * 先物OP手動発注ハンドラクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3ToIfoManualOrderHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToIfoManualOrderHandler.class);

    /**
     * コンストラクタ<BR>
     * @@roseuid 43F491C700DA
     */
    public WEB3ToIfoManualOrderHandler() 
    {
     
    }
    
    /**
     * (confirm手動発注)<BR>
     * 先物OP手動発注確認処理を行う。<BR>
     * <BR>
     * 先物OP手動発注サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (先物OP手動発注確認リクエスト)<BR>
     * 先物OP手動発注確認リクエスト<BR>
     * @@return webbroker.triggerorder.message.WEB3FuturesOptionsManualConfirmResponse
     * @@roseuid 43E9A93203AF
     */
    public WEB3FuturesOptionsManualConfirmResponse confirmManual(WEB3FuturesOptionsManualConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManual(WEB3FuturesOptionsManualConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOptionsManualConfirmResponse l_response = null;
        WEB3ToIfoManualOrderService l_service = null;
        
        try
        {
            //get先物OP手動発注サービス
            l_service = (WEB3ToIfoManualOrderService) Services.getService(WEB3ToIfoManualOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物OP手動発注サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP手動発注確認処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP手動発注確認処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete手動発注)<BR>
     * 先物OP手動発注完了処理を行う。<BR>
     * <BR>
     * 先物OP手動発注サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (先物OP手動発注完了リクエスト)<BR>
     * 先物OP手動発注完了リクエスト<BR>
     * @@return webbroker.triggerorder.message.WEB3FuturesOptionsManualCompleteResponse
     * @@roseuid 43E9AB730019
     */
    public WEB3FuturesOptionsManualCompleteResponse completeManual(WEB3FuturesOptionsManualCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeManual(WEB3FuturesOptionsManualCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOptionsManualCompleteResponse l_response = null;
        WEB3ToIfoManualOrderService l_service = null;
        
        try
        {
            //get先物OP手動発注サービス
            l_service = (WEB3ToIfoManualOrderService) Services.getService(WEB3ToIfoManualOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "先物OP手動発注サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP手動発注完了処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3FuturesOptionsManualCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "先物OP手動発注完了処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
