head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : 取引明細ハンドラ(WEB3HistoryTradeDetailHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 王敏 (中訊) 新規作成
*/

package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;

/**
 * (取引明細ハンドラ)<BR>
 * 取引明細ハンドラクラス<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3HistoryTradeDetailHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3HistoryTradeDetailHandler.class);
    
    /**
     * @@roseuid 41789C4C03A9
     */
    public WEB3HistoryTradeDetailHandler() 
    {
     
    }
    
    /**
     * (get取引明細画面)<BR>
     * 取引明細画面表示処理を行う。<BR>
     * <BR>
     * 取引明細サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引明細リクエストオブジェクト<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse
     * @@roseuid 413D90560113
     */
    public WEB3HistoryTradeDetailResponse getTradeDetailScreen(WEB3HistoryTradeDetailRequest l_request) 
    {
        final String STR_METHOD_NAME = " getTradeDetailScreen(WEB3HistoryTradeDetailRequest)";    
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeDetailResponse  l_response = null;
        WEB3HistoryTradeDetailService  l_service = null;

        try
        {
            l_service = (WEB3HistoryTradeDetailService) Services.getService(
                                 WEB3HistoryTradeDetailService.class);
        }
        catch (Exception e)
        {
            l_response =
                (WEB3HistoryTradeDetailResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "取引明細サービスに失敗しました。",
                l_response.errorInfo,
                e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3HistoryTradeDetailResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.error(l_request, "取引明細画面表示失敗しました。", e);
            l_response = (WEB3HistoryTradeDetailResponse) l_request.createResponse();
            l_response.errorInfo = e.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
