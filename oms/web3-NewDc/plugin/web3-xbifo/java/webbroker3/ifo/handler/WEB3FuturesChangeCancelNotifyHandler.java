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
filename	WEB3FuturesChangeCancelNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物訂正取消通知ハンドラ(WEB3FuturesChangeCancelNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/21 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3FuturesChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyService;

/**
 * (先物訂正取消通知ハンドラ)<BR>
 * 先物訂正取消通知ハンドラクラス<BR>
 */
public class WEB3FuturesChangeCancelNotifyHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeCancelNotifyHandler.class);        
    /**
     * @@roseuid 40F7B072034B
     */
    public WEB3FuturesChangeCancelNotifyHandler() 
    {
     
    }
    
    /**
     * (先物訂正取消通知リクエスト)<BR>
     * 株式指数先物訂正取消通知処理を実施する。<BR>
     * <BR>
     * 株式指数先物訂正取消通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数先物訂正取消通知リクエストオブジェクト<BR>
     * @@return WEB3FuturesChangeCancelNotifyResponse
     * @@roseuid 40A89D710113
     */
    public WEB3FuturesChangeCancelNotifyResponse WEB3FuturesChangeCancelNotifyRequest(WEB3FuturesChangeCancelNotifyRequest l_request) throws WEB3SystemLayerException 
    {
        log.debug("Start to test the method :FuturesChangeCancelNotifyRequest");
        final String STR_METHOD_NAME = "FuturesChangeCancelNotifyRequest(WEB3FuturessChangeCancelNotifyRequest l_request)";
        WEB3FuturesChangeCancelNotifyResponse l_response = null;
        WEB3FuturesChangeCancelNotifyService l_changeCancelNotify = null;
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            log.debug("Enter the if path that the l_request parameter is null.");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);            
        }
        try
        {
            l_changeCancelNotify = 
                (WEB3FuturesChangeCancelNotifyService)Services.getService(WEB3FuturesChangeCancelNotifyService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3FuturesChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "株式指数先物訂正取消通知サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex); 
            return l_response;  
        }
        try
        {
            l_response = 
                (WEB3FuturesChangeCancelNotifyResponse)l_changeCancelNotify.execute(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "株式指数先物訂正取消通知に失敗しました。",
                l_ex); 
            return l_response;          
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }
}
@
