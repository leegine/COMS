head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株式注文ハンドラ(WEB3ToSuccEquityOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/14 鄭海良(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）株式注文ハンドラ)<BR>
 * （連続）株式注文サービスハンドラクラス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderHandler.class);
    
    /**
     * @@roseuid 4348ECB602DE
     */
    public WEB3ToSuccEquityOrderHandler() 
    {
     
    }
    
    /**
     * (confirm買付注文)<BR>
     * （連続）現物株式買付注文確認処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3SuccEquityBuyConfirmResponse
     * @@roseuid 431D3CC4029F
     */
    public WEB3SuccEquityBuyConfirmResponse confirmBuyOrder(WEB3SuccEquityBuyConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =" confirmBuyOrder(WEB3SuccEquityBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquityBuyConfirmResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get（連続）株式注文サービス
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccEquityBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquityBuyConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）現物株式買付注文確認処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityBuyConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();   
            log.error(
                l_request, 
                "（連続）現物株式買付注文確認処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete買付注文)<BR>
     * （連続）現物株式買付注文完了処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3SuccEquityBuyCompleteResponse
     * @@roseuid 431D3DB3007C
     */
    public WEB3SuccEquityBuyCompleteResponse completeBuyOrder(WEB3SuccEquityBuyCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =" completeBuyOrder(WEB3SuccEquityBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquityBuyCompleteResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get（連続）株式注文サービス
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccEquityBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquityBuyCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquityBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）現物株式買付注文完了処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquityBuyCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(
                l_request, 
                "（連続）現物株式買付注文完了処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm売付注文)<BR>
     * （連続）現物株式売付注文確認処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3SuccEquitySellConfirmResponse
     * @@roseuid 431D3DDA006C
     */
    public WEB3SuccEquitySellConfirmResponse confirmSellOrder(WEB3SuccEquitySellConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =" confirmSellOrder(WEB3SuccEquitySellConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellConfirmResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get（連続）株式注文サービス
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccEquitySellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquitySellConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquitySellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）現物株式売付注文確認処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquitySellConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）現物株式売付注文確認処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete売付注文)<BR>
     * （連続）現物株式売付注文完了処理を行う。<BR>
     * <BR>
     * 「（連続）株式注文サービス」を取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * @@return WEB3SuccEquitySellCompleteResponse
     * @@roseuid 431D3DDA007C
     */
    public WEB3SuccEquitySellCompleteResponse completeSellOrder(WEB3SuccEquitySellCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =" completeSellOrder(WEB3SuccEquitySellCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellCompleteResponse l_response = null;
        WEB3ToSuccEquityOrderService l_service = null;

        try
        {            
            //get（連続）株式注文サービス
            l_service = (WEB3ToSuccEquityOrderService)
                Services.getService(WEB3ToSuccEquityOrderService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3SuccEquitySellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）株式注文サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccEquitySellCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccEquitySellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）現物株式売付注文完了処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccEquitySellCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）現物株式売付注文完了処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
