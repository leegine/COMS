head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文受付ハンドラクラス(WEB3MutualOrderAcceptServiceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建  (中訊) 新規作成
                   2004/08/24 王蘭芬 (中訊) レビュー
*/
package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託注文受付ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3MutualOrderAcceptServiceHandler implements MessageHandler 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualOrderAcceptServiceHandler.class);
    
    /**
     * 投資信託注文受付処理を行う。<BR>
     * <BR>
     * 投資信託注文受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return webbroker3.mf.message.WEB3MutualOrderAcceptResponse
     * @@roseuid 4056658B03BB
     */
    public WEB3MutualOrderAcceptResponse mutualOrderAcceptRequest(
        WEB3MutualOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME = "mutualOrderAcceptRequest(" +
            "WEB3MutualOrderAcceptRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //投資信託注文受付サービスインターフェイス
        WEB3MutualOrderAcceptService l_service = null;          
        
        //投資信託注文受付レスポンスクラス
        WEB3MutualOrderAcceptResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3MutualOrderAcceptService) Services.getService(
                    WEB3MutualOrderAcceptService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
             //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投資信託注文受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3MutualOrderAcceptResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MutualOrderAcceptResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投資信託注文受付サービスの処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
