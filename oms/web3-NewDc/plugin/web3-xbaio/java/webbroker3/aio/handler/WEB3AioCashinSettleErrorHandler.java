head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleErrorHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済エラーハンドラクラス(WEB3AioCashinSettleErrorHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王蘭芬(中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinSettleErrorRequest;
import webbroker3.aio.message.WEB3AioCashinSettleErrorResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleErrorService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済エラーハンドラ)<BR>
 * 決済エラーハンドラクラス<BR>
 *
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleErrorHandler implements MessageHandler 
{

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleErrorHandler.class);

    
    /**
     * @@roseuid 4158DFFF0312
     */
    public WEB3AioCashinSettleErrorHandler() 
    {
     
    }
    
    /**
     * (決済エラーリクエスト)<BR>
     * 決済エラーサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3GenResponse
     * @@roseuid 411B305F0293
     */
    public WEB3AioCashinSettleErrorResponse handleCashinSettleErrorRequest(WEB3AioCashinSettleErrorRequest l_request) 
    {
        String l_strMethodName = "handleCashinSettleErrorRequest(WEB3AioCashinSettleErrorRequest l_request)";
        log.entering(l_strMethodName);
 
        WEB3AioCashinSettleErrorService l_service;
        WEB3AioCashinSettleErrorResponse l_response;
        
        try
        {
            //決済エラーサービスを取得し
            l_service = 
                (WEB3AioCashinSettleErrorService)Services.getService(WEB3AioCashinSettleErrorService.class);
        }
        ////サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinSettleErrorResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "決済エラーサービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinSettleErrorResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettleErrorResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "決済エラーサービス処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
