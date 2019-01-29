head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知ハンドラクラス(WEB3IfoCloseNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 盧法@旭 (中訊) 新規作成
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.message.WEB3IfoCloseOrderResponse;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;


/**
 * (先物OP失効通知ハンドラ)<BR>
 * 先物OP失効通知ハンドラクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoCloseNotifyHandler.class);        

    /**
     * @@roseuid 40C0755102BF
     */
    public WEB3IfoCloseNotifyHandler() 
    {
     
    }
    
    /**
     * (先物OP失効通知処理リクエスト)<BR>
     * 先物OP失効通知処理を実施する。<BR>
     * <BR>
     * 先物OP失効通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 株式指数オプション失効通知リクエストオブジェクト
     * @@throws WEB3BaseException 
     * @@return webbroker3.ifo.message.WEB3IfoCloseOrderResponse
     * @@roseuid 4057B7C9039C
     */
    public WEB3IfoCloseOrderResponse ifoCloseNotifyRequest(WEB3IfoCloseOrderRequest l_request) throws WEB3BaseException 
    {
        log.debug("Enter the method(ifoCloseNotifyRequest).");
        final String STR_METHOD_NAME = "ifoCloseNotifyRequest(WEB3IfoCloseOrderRequest l_request)";  
        
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoCloseOrderResponse l_response = null;
        WEB3IfoCloseNotifyService l_closeNotify = null;
        try
        {
            log.debug("Get the   先物OP失効通知サービス");
            l_closeNotify = 
                (WEB3IfoCloseNotifyService)Services.getService(WEB3IfoCloseNotifyService.class);
            log.debug("Succeeded to get the  先物OP失効通知サービス");
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the Exception and create the exception response object.");
            l_response = (WEB3IfoCloseOrderResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(
                l_request,
                "先物OP失効通知サービスを取得し失敗",
                l_response.errorInfo,
                l_ex);
            log.debug("Exit the Exception and create the exception response object.");
            return l_response;
        }
        try
        {
            log.debug(" get the WEB3IfoCloseOrderResponse Object");
            l_response = (WEB3IfoCloseOrderResponse)l_closeNotify.execute(l_request);
            log.debug(" Succeeded to get the WEB3IfoCloseOrderResponse Object");
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("Enter the Exception and create the exception response object.");
            l_response = (WEB3IfoCloseOrderResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "先物OP失効通知Response Object取得失敗",
                l_ex); 
            log.debug("Exit the Exception and create the exception response object."); 
            return l_response;                     
        }
        return l_response;
    }
}
@
