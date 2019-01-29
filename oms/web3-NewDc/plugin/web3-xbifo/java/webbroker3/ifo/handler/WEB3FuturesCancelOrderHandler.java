head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物取消注文ハンドラクラス(WEB3FuturesCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;

/**
 * (株価指数先物取消注文ハンドラ)<BR>
 * 株価指数先物取消注文ハンドラクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesCancelOrderHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCancelOrderHandler.class);    
    /**
     * @@roseuid 40F7B070032C
     */
    public WEB3FuturesCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm取消)<BR>
     * 株価指数先物の取消発注審査を行う。<BR>
     * <BR>
     * 株価指数先物取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション取消注文確認リクエスト<BR>
     * @@return WEB3FuturesCancelConfirmResponse
     * @@roseuid 40A819C50254
     */
    public WEB3FuturesCancelConfirmResponse confirmCancel(WEB3FuturesCancelConfirmRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3FuturesCancelConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3FuturesCancelConfirmResponse l_response = null;
        WEB3FuturesCancelOrderService l_service = null;
        
        //株価指数オプション取消注文サービスを取得
        try
        {
            l_service =
                (WEB3FuturesCancelOrderService)Services.getService(
                    WEB3FuturesCancelOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物取消注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //株価指数先物取消注文サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        
        try
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株価指数先物取消注文の発注審査に失敗しました。", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物取消注文の発注審査に失敗しました。", l_rex);
            return l_response;
        }
        
        return l_response;
    }
    
    /**
     * (complete取消)<BR>
     * 株価指数先物の取消注文を登録する。<BR>
     * <BR>
     * 株価指数先物取消注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション取消注文完了リクエスト<BR>
     * @@return WEB3FuturesCancelCompleteResponse
     * @@roseuid 40A819C50263
     */
    public WEB3FuturesCancelCompleteResponse completeCancel(WEB3FuturesCancelCompleteRequest l_request) 
    {
        final String METHOD_NAME =
                "completeCancel(WEB3FuturesCancelCompleteRequest)";    
        log.entering(METHOD_NAME);

        WEB3FuturesCancelCompleteResponse l_response = null;
        WEB3FuturesCancelOrderService l_service      = null;
        
        //株価指数先物取消注文サービスを取得
        try
        {
            l_service =
                (WEB3FuturesCancelOrderService)Services.getService(
                    WEB3FuturesCancelOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物取消注文サービスの取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //株価指数先物取消注文サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株価指数先物取消注文の登録に失敗しました。", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物取消注文の登録に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
