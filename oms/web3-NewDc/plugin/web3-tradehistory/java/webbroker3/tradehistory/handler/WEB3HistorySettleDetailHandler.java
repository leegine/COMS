head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistorySettleDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済明細ハンドラ(WEB3HistorySettleDetailHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/27 賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済明細ハンドラ)<BR>
 * 決済明細ハンドラクラス<BR>
 * 
 * @@author 賈元春(中訊)
 * @@version 1.00
 */
public class WEB3HistorySettleDetailHandler implements MessageHandler 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistorySettleDetailHandler.class);
    
    /**
     * @@roseuid 41789C4C02AF
     */
    public WEB3HistorySettleDetailHandler() 
    {
     
    }
    
    /**
     * (get決済明細画面)<BR>
     * 決済明細表示処理を行う。<BR>
     * <BR>
     * 決済明細サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 決済明細リクエストオブジェクト<BR>
     * @@return webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse
     * @@roseuid 413EBA0B00A5
     */
    public WEB3HistorySettleDetailResponse getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".getSettleDetailScreen(WEB3HistorySettleDetailRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3HistorySettleDetailResponse l_response = null;
        WEB3HistorySettleDetailService l_service = null;     
        try
        {
            l_service= (WEB3HistorySettleDetailService)Services.getService(WEB3HistorySettleDetailService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistorySettleDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "決済明細サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3HistorySettleDetailResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistorySettleDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "決済明細表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
