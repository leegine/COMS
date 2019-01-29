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
filename	WEB3OptionCancelOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP取消注文ハンドラ(WEB3OptionCancelOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/19 李強 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsCancelConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCancelCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * (OP取消注文ハンドラ)<BR>
 * 株価指数オプション取消注文ハンドラクラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3OptionCancelOrderHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionCancelOrderHandler.class);    
        
    /**
     * @@roseuid 40C0754F004E
     */
    public WEB3OptionCancelOrderHandler() 
    {
     
    }
    
    /**
     * (confirm取消)<BR>
     * 株価指数オプションの取消発注審査を行う。<BR>
     * <BR>
     * 株価指数オプション取消注文サービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション取消注文確認リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelConfirmResponse
     * @@roseuid 4051431F004D
     */
    public WEB3OptionsCancelConfirmResponse confirmCancel(WEB3OptionsCancelConfirmRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3OptionsCancelConfirmRequest)";
    
        log.entering(METHOD_NAME);

        WEB3OptionsCancelConfirmResponse l_response = null;
        WEB3OptionCancelOrderService l_service      = null;
        
        //株価指数オプション取消注文サービスを取得
        try
        {
            l_service =
                (WEB3OptionCancelOrderService)Services.getService(
                    WEB3OptionCancelOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション取消注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //株価指数オプション取消注文サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株価指数オプションの取消発注審査に失敗しました。", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCancelConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプションの取消発注審査に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete取消)<BR>
     * 株価指数オプションの取消注文を登録する。<BR>
     * <BR>
     * 株価指数オプション取消注文サービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション取消注文完了リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCancelCompleteResponse
     * @@roseuid 40514347027F
     */
    public WEB3OptionsCancelCompleteResponse completeCancel(WEB3OptionsCancelCompleteRequest l_request) 
    {
        final String METHOD_NAME =
                "confirmCancel(WEB3OptionsCancelCompleteRequest)";
    
        log.entering(METHOD_NAME);

        WEB3OptionsCancelCompleteResponse l_response = null;
        WEB3OptionCancelOrderService l_service      = null;
        
        //株価指数オプション取消注文サービスを取得
        try
        {
            l_service =
                (WEB3OptionCancelOrderService)Services.getService(
                    WEB3OptionCancelOrderService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション取消注文サービスを取得に失敗しました。",
                l_response.errorInfo,
                e);
            return l_response;
        }
        
        //株価指数オプション取消注文サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "株価指数オプションの取消注文を登録に失敗しました。", e);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsCancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプションの取消注文を登録に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
