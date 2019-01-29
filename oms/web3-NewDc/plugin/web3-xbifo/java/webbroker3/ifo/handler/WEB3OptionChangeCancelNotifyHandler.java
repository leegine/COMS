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
filename	WEB3OptionChangeCancelNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正取消通知ハンドラクラス(WEB3OptionChangeCancelNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyRequest;
import webbroker3.ifo.message.WEB3OptionsChangeCancelNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;

/**
 * (OP訂正取消通知ハンドラ)<BR>
 * 株価指数オプション訂正取消通知ハンドラクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeCancelNotifyHandler.class);    
    /**
     * @@roseuid 40C075500203
     */
    public WEB3OptionChangeCancelNotifyHandler() 
    {
     
    }
    
    /**
     * (オプション訂正取消通知リクエスト)<BR>
     * 株式指数オプション訂正取消通知処理を実施する。<BR>
     * <BR>
     * 株式指数オプション訂正取消通知サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株式指数オプション訂正取消通知リクエストオブジェクト<BR>
     * @@return WEB3OptionsChangeCancelNotifyResponse
     * @@roseuid 4057D2FE00AE
     */
    public WEB3OptionsChangeCancelNotifyResponse optionChangeCancelNotifyRequest(WEB3OptionsChangeCancelNotifyRequest l_request) throws WEB3BaseException 
    {

        final String STR_METHOD_NAME = "optionChangeCancelNotifyRequest(WEB3OptionsChangeCancelNotifyRequest l_request)";
        WEB3OptionsChangeCancelNotifyResponse l_response = null;
        WEB3OptionChangeCancelNotifyService l_changeCancelNotify = null;
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
            log.debug("Enter the try path and get the WEB3OptionChangeCancelNotifyServiceImpl object.");
            l_changeCancelNotify = 
                (WEB3OptionChangeCancelNotifyService)Services.getService(WEB3OptionChangeCancelNotifyService.class);
            log.debug("Exit the try path and succeed to get the WEB3OptionChangeCancelNotifyServiceImpl object.");
        }
        catch(Exception l_ex)
        {
            log.debug("Enter the catch path and create the response about exception."); 
            l_response = (WEB3OptionsChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002; 
            log.error(l_request,
                "株価指数オプション訂正取消通知サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex); 
            log.debug("Exit the catch path and create the response about exception."); 
            return l_response;  
        }
        try
        {
            log.debug("Enter the try path and get the WEB3OptionsChangeCancelNotifyResponse object.");
            l_response = 
                (WEB3OptionsChangeCancelNotifyResponse)l_changeCancelNotify.execute(l_request);
            log.debug("Exit the try path and get the WEB3OptionsChangeCancelNotifyResponse object.");

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug("Enter the catch path and create the response to deal with the exception."); 
            l_response = (WEB3OptionsChangeCancelNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo(); 
            log.error(l_request,
                "株価指数オプション訂正取消通知に失敗しました",
                l_ex); 
            log.debug("Exit the catch path and create the response to deal with the exception."); 
            return l_response;          
        }
        log.exiting(STR_METHOD_NAME);
        log.debug("End to test the method :OptionChangeCancelNotifyRequest");
        return l_response;
        }

    }

@
