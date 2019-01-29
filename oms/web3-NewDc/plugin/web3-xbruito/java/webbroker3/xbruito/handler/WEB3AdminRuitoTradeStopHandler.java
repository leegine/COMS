head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者累投銘柄別売買停止ハンドラ (WEB3AdminRuitoTradeStopHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/06 韋念瓊 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;

/**
 * (管理者累投銘柄別売買停止ハンドラ)<BR>
 * 管理者累投銘柄別売買停止ハンドラクラス
 */
public class WEB3AdminRuitoTradeStopHandler implements MessageHandler
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminRuitoTradeStopHandler.class);
    
    /**
     * (get入力画面)<BR>
     * 累投銘柄別売買停止入力画面取得処理を実施する。<BR>
     * 管理者累投銘柄別売買停止サービスを取得して、execute()メソッドをコールする。<BR>  
     * @@param l_request - 累投銘柄別売買停止入力画面リクエスト
     * @@return webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopInputResponse handleGetInputScreen(
            WEB3AdminRuitoTradeStopInputRequest l_request)
    {
        String STR_METHOD_NAME = "handleGetInputScreen(" +
            "WEB3AdminRuitoTradeStopInputRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        if (l_request == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
    
        //管理者累投銘柄別売買停止サービスを取得し
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopInputResponse l_response = null;
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者累投銘柄別売買停止サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminRuitoTradeStopInputResponse) 
                l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                      "管理者累投銘柄別売買停止入力画面取得処理が失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm銘柄別売買停止)<BR>
     * 累投銘柄別売買停止確認処理を実施する。<BR>
     * 管理者累投銘柄別売買停止サービスを取得して、execute()メソッドをコールする。<BR>
     * @@param l_request - 累投銘柄別売買停止確認リクエスト
     * @@return webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopConfirmResponse handleComfirmTradeStop(
            WEB3AdminRuitoTradeStopConfirmRequest l_request)
    {
        String STR_METHOD_NAME = "handleComfirmTradeStop(" +
            "WEB3AdminRuitoTradeStopConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);       
    
        //管理者累投銘柄別売買停止サービスを取得し
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者累投銘柄別売買停止サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminRuitoTradeStopConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者累投銘柄別売買停止確認処理が失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete銘柄別売買停止)<BR>
     * 累投銘柄別売買停止完了処理を実施する。<BR>
     * 管理者累投銘柄別売買停止サービスを取得して、execute()メソッドをコールする。<BR>
     * @@param l_request - 累投銘柄別売買停止完了リクエスト
     * @@return webbroker3.WEB3AdminRuitoTradeStopCompleteResponse
     * @@roseuid 4058292B039C
     */
    public WEB3AdminRuitoTradeStopCompleteResponse handleCompleteTradeStop(
            WEB3AdminRuitoTradeStopCompleteRequest l_request)
    {
        String STR_METHOD_NAME = "handleCompleteTradeStop(" +
        "WEB3AdminRuitoTradeStopCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
    
        //管理者累投銘柄別売買停止サービスを取得
        WEB3AdminRuitoTradeStopService l_service = null;
        WEB3AdminRuitoTradeStopCompleteResponse l_response = null;
    
        try
        {
            l_service = (WEB3AdminRuitoTradeStopService) 
                Services.getService(WEB3AdminRuitoTradeStopService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者累投銘柄別売買停止サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
    
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminRuitoTradeStopCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminRuitoTradeStopCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "累投銘柄別売買停止登録が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
