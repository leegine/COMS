head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityChangeOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文訂正ハンドラ(WEB3ToSuccEquityChangeOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）株式注文訂正ハンドラ)<BR>
 * （連続）株式注文訂正ハンドラクラス。<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityChangeOrderHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderHandler.class);
    
    /**
     * @@roseuid 4369ED2E000F
     */
    public WEB3ToSuccEquityChangeOrderHandler() 
    {
     
    }
    
    /**
     * (confirm注文訂正)<BR>
     * （連続）現物株式注文訂正確認処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文訂正サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@roseuid 4338D8890058
     */
    public WEB3SuccEquityChangeConfirmResponse confirmChangeOrder(WEB3SuccEquityChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmChangeOrder(WEB3SuccEquityChangeConfirmRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeConfirmResponse l_response = null;
        WEB3ToSuccEquityChangeOrderService l_service = null;       

        // （連続）株式注文訂正サービスを取得し、execute()メソッドをコールする
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderService) Services.getService(WEB3ToSuccEquityChangeOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文訂正サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "confirm注文訂正に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "confirm注文訂正に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete注文訂正)<BR>
     * （連続）現物株式注文訂正完了処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文訂正サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ。<BR>
     * @@return WEB3EquityChangeCompleteResponse
     * @@roseuid 4338D9C10327
     */
    public WEB3SuccEquityChangeCompleteResponse completeChangeOrder(WEB3SuccEquityChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeChangeOrder(WEB3SuccEquityChangeCompleteRequest )";
        log.entering(STR_METHOD_NAME);
       
        WEB3SuccEquityChangeCompleteResponse l_response = null;
        WEB3ToSuccEquityChangeOrderService l_service = null;       

        // （連続）株式注文訂正サービスを取得し、execute()メソッドをコールする
        try
        {
            l_service =
                (WEB3ToSuccEquityChangeOrderService) Services.getService(WEB3ToSuccEquityChangeOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =(WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文訂正サービスの取得に失敗しました。",
                l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(l_request, "complete注文訂正に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3SuccEquityChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "complete注文訂正に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
