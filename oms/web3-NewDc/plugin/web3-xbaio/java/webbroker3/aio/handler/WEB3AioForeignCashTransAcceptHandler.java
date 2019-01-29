head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外貨入出金受付ハンドラ(WEB3AioForeignCashTransAcceptHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioForeignCashTransAcceptRequest;
import webbroker3.aio.message.WEB3AioForeignCashTransAcceptResponse;
import webbroker3.aio.service.delegate.WEB3AioForeignCashTransAcceptService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (外貨入出金受付ハンドラ)<BR>
 * 外貨入出金受付ハンドラクラス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptHandler implements MessageHandler 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioForeignCashTransAcceptHandler.class);
    
    /**
     * (外貨入出金受付リクエスト)<BR>
     * 外貨入出金受付処理を行う。<BR>
     * <BR>
     * 外貨入出金受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3AioForeignCashTransAcceptResponse
     * @@throws WEB3BaseException
     */
    public WEB3AioForeignCashTransAcceptResponse foreignCashTransAcceptRequest(
        WEB3AioForeignCashTransAcceptRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "foreignCashTransAcceptRequest(WEB3AioForeignCashTransAcceptRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioForeignCashTransAcceptService l_service = null;
        WEB3AioForeignCashTransAcceptResponse l_response = null;
        try
        {
            l_service =
                (WEB3AioForeignCashTransAcceptService)Services.getService(
                    WEB3AioForeignCashTransAcceptService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外貨入金受付サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        } 
        
        try
        {
            l_response =
                (WEB3AioForeignCashTransAcceptResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AioForeignCashTransAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "外貨入出金受付処理に失敗しました", l_ex.getErrorInfo(), l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}

@
