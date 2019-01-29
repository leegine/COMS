head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioFinanceAmountRepayHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 融資額返済ハンドラ(WEB3AioFinanceAmountRepayHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 唐性峰 (中訊) 新規作成                                     
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioFinanceAmountRepayRequest;
import webbroker3.aio.message.WEB3AioFinanceAmountRepayResponse;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (融資額返済ハンドラ)<BR>
 * 融資額返済ハンドラクラス<BR>
 * 
 * @@author 唐性峰(中訊)
 * @@version 1.0
 */
public class WEB3AioFinanceAmountRepayHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioFinanceAmountRepayHandler.class); 
    
    /**
     * (融資額返済リクエスト)<BR>
     * 融資額返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AioFinanceAmountRepayResponse
     * @@roseuid 44F529B60190
     */
    public WEB3AioFinanceAmountRepayResponse financeAmountRepayRequest(
        WEB3AioFinanceAmountRepayRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " secFinanceLoanRepayRequest(WEB3AioSecFinanceLoanRepayRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioFinanceAmountRepayResponse l_response = null;
        WEB3AioFinanceAmountRepayService l_service = null;
        
        try
        {
            //債券応募/買付サービスの取得
            l_service = (WEB3AioFinanceAmountRepayService)
                Services.getService(WEB3AioFinanceAmountRepayService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioFinanceAmountRepayResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "融資額返済サービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AioFinanceAmountRepayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioFinanceAmountRepayResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "融資額返済リクエスト処理の実施に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
}
@
