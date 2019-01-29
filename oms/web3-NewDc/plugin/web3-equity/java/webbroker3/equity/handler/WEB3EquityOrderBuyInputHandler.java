head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderBuyInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 現物株式買付注文入力ハンドラ (WEB3EquityOrderBuyInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/10 欒学峰 (中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3EquityBuyInputResponse;
import webbroker3.equity.message.WEB3EquityBuyInputRequest;
import webbroker3.equity.message.WEB3EquityProductSelectRequest;
import webbroker3.equity.message.WEB3EquityProductSelectResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * （現物株式買付注文入力ハンドラクラス）。 
 * @@version 1.0
 */
public class WEB3EquityOrderBuyInputHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderBuyInputHandler.class);
           
    /**
     * @@roseuid 409B36EA0157
     */
    public WEB3EquityOrderBuyInputHandler() 
    {
    
    }
   
    /**
     * (買付注文入力リクエスト) <BR>
     * <BR>
     * 現物株式買付注文入力表示処理を行う。<BR>
     * <BR>
     * 現物株式買付注文入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) <BR>
     * <BR>
     * 現物株式買付注文入力リクエストオブジェクト <BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     * @@roseuid 406285F70087
     */
    public WEB3EquityBuyInputResponse equityOrderBuyInputRequest(WEB3EquityBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "equityOrderBuyInputRequest(WEB3EquityBuyInputRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3EquityBuyInputResponse l_response = null;
        WEB3EquityOrderBuyInputService l_service = null;
       
        // 現物株式買付注文入力サービスオブジェクトを取得する
        // 現物株式買付注文入力サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityOrderBuyInputService) Services.getService(WEB3EquityOrderBuyInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "現物株式買付注文入力サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "買付注文入力に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityBuyInputResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "買付注文入力に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (買付注文銘柄選択リクエスト) <BR>
     * <BR>
     * 現物株式買付注文銘柄選択画面表示処理を行う。<BR>
     * <BR>
     * 現物株式買付注文入力サービスを取得し、
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ) <BR>
     * <BR>
     * 買付注文銘柄選択リクエストオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3EquityOrderBuyInputResponse
     */
    public WEB3EquityProductSelectResponse equityProductSelectRequest(WEB3EquityProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = "equityProductSelectRequest(WEB3EquityProductSelectRequest)";
        log.entering(STR_METHOD_NAME);
       
        WEB3EquityProductSelectResponse l_response = null;
        WEB3EquityOrderBuyInputService l_service = null;
       
        // 現物株式買付注文入力サービスオブジェクトを取得する
        // 現物株式買付注文入力サービスオブジェクト.execute（）をコールし、
        // 処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_service =
                (WEB3EquityOrderBuyInputService) Services.getService(WEB3EquityOrderBuyInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception e)
        {
            l_response =(WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "現物株式買付注文入力サービスの取得に失敗しました。",
                l_response.errorInfo,e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
            log.error(l_request, "買付注文銘柄選択に失敗しました。", e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3EquityProductSelectResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "買付注文銘柄選択に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        // レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
