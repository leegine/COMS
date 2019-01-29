head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポートハンドラクラス(WEB3AdminAioSettleReportHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioSettleReportListRequest;
import webbroker3.aio.message.WEB3AdminAioSettleReportListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済連携レポートハンドラ)<BR>
 * 決済連携レポートハンドラクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AdminAioSettleReportHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportHandler.class);
       
    /**
     * (レポート表示リクエスト)<BR>
     * 決済連携レポートサービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminAioSettleReportListResponse
     * @@throws WEB3BaseException
     * @@roseuid 410101AC00BB
     */
    public WEB3AdminAioSettleReportListResponse handleReportIndicationRequest(
        WEB3AdminAioSettleReportListRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "handleReportIndicationRequest(WEB3AdminAioSettleReportListRequest l_request)";
        log.entering(STR_METHOD_NAME);       
        
        //決済連携レポートサービスを取得し
        WEB3AdminAioSettleReportService l_service = null;
        WEB3AdminAioSettleReportListResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminAioSettleReportService) 
                Services.getService(WEB3AdminAioSettleReportService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "決済連携レポートサービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminAioSettleReportListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioSettleReportListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "決済連携レポート処理が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }    
}
@
