head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携ハンドラ(WEB3AioCashinCooperationServiceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/1/11 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinCooperationNotifyRequest;
import webbroker3.aio.message.WEB3AioCashinCooperationNotifyResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金連携ハンドラ) <BR>
 * 入金連携ハンドラクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCooperationServiceHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCooperationServiceHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3AioCashinCooperationServiceHandler()
    {
    }

    /**
     * (入金連携リクエスト) <BR>
     * 入金連携処理を行う。  <BR>
     * <BR>
     * 入金連携サービスを取得し、  <BR>
     * execute()メソッドをコールする。 <BR>
     * <BR>
     * サービスで例外が発生した場合は、 <BR>
     * レスポンスメッセージを生成し <BR>
     * エラー情報を設定し返却する。  <BR>
     * 
     * @@param l_request - 入金連携通知リクエスト 
     * @@return WEB3AioCashinCooperationNotifyResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3AioCashinCooperationNotifyResponse cashinCooperationRequest(
        WEB3AioCashinCooperationNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "cashinCooperationRequest(WEB3AioCashinCooperationNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioCashinCooperationService l_service;
        WEB3AioCashinCooperationNotifyResponse l_response;
        
        try
        {
            //入金連携サービスを取得し
            l_service = 
                (WEB3AioCashinCooperationService)Services.getService(
                    WEB3AioCashinCooperationService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinCooperationNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__入金連携サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinCooperationNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinCooperationNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 入金連携サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}@
