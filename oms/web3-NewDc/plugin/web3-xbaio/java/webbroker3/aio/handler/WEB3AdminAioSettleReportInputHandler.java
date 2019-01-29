head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート入力ハンドラクラス(WEB3AdminAioSettleReportInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioSettleReportInputRequest;
import webbroker3.aio.message.WEB3AdminAioSettleReportInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済連携レポート入力ハンドラ)<BR>
 * 決済連携レポート入力ハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioSettleReportInputHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportInputHandler.class); 
        
    /**
     * (レポート入力リクエスト)<BR>
     * 決済連携レポート入力サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminAioSettleReportInputResponse
     * @@roseuid 4100FF6B0213
     */
    public WEB3AdminAioSettleReportInputResponse handleReportInputRequest(WEB3AdminAioSettleReportInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "handleReportInputRequest(WEB3AdminAioSettleReportInputRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        
        //決済連携レポート入力サービスを取得し
        WEB3AdminAioSettleReportInputService l_service = null;
        WEB3AdminAioSettleReportInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSettleReportInputService) 
                Services.getService(WEB3AdminAioSettleReportInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "決済連携レポート入力サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioSettleReportInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "決済連携レポート入力処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
