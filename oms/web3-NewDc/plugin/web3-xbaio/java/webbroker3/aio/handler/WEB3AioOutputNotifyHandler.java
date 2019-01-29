head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知ハンドラ(WEB3AioOutputNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioOutputNotifyRequest;
import webbroker3.aio.message.WEB3AioOutputNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出庫通知ハンドラ)<BR>
 * 出庫通知ハンドラクラス 
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyHandler.class);
    
    /**
     * (出庫通知リクエスト)<BR>
     * 出庫通知サービスを取得し、execute()メソッドをコールする。 
     * @@param l_request - リクエストデータ
     * @@return WEB3AioOutputNotifyResponse
     * @@roseuid 4157981D0040
     */
    public WEB3AioOutputNotifyResponse outputNotifyRequest(WEB3AioOutputNotifyRequest l_request) 
    {
    		
        String STR_METHOD_NAME = 
            "outputNotifyRequest(WEB3AioOutputNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioOutputNotifyService l_service;
        WEB3AioOutputNotifyResponse l_response;
        
        try
        {
            //出庫通知サービスを取得し
            l_service = 
                (WEB3AioOutputNotifyService)Services.getService(
                    WEB3AioOutputNotifyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioOutputNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__(出庫通知サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioOutputNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioOutputNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call (出庫通知サービス.execute()__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;        
    }
}
@
