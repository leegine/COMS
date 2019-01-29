head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消一覧ハンドラ(WEB3AioCashoutCancelListHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/22 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashoutCancelListRequest;
import webbroker3.aio.message.WEB3AioCashoutCancelListResponse;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金取消一覧ハンドラ)<BR>
 * 出金取消一覧ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutCancelListHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashoutCancelListHandler.class);
    
    /**
     * (出金取消一覧リクエスト)<BR>
     * 出金取消一覧表示処理を行う。<BR>
     * <BR>
     * 出金取消一覧サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioCashoutCancelListResponse
     * @@roseuid 40F635E203A0
     */
    public WEB3AioCashoutCancelListResponse handleAioCashoutCancelListRequest(
        WEB3AioCashoutCancelListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleAioCashoutCancelListRequest(" +
            "WEB3AioCashoutCancelListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //出金取消一覧サービス
        WEB3AioCashoutCancelListService l_service = null;          
         
        //出金取消一覧レスポンス
        WEB3AioCashoutCancelListResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AioCashoutCancelListService) Services.getService(
                    WEB3AioCashoutCancelListService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AioCashoutCancelListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "出金取消一覧サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AioCashoutCancelListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashoutCancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "出金取消一覧に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
