head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOnPaymentCooperationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携ハンドラ(WEB3AioOnPaymentCooperationHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioOnPaymentCooperationRequest;
import webbroker3.aio.message.WEB3AioOnPaymentCooperationResponse;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金連携ハンドラ) <BR>
 * 出金連携ハンドラクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOnPaymentCooperationHandler implements MessageHandler
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOnPaymentCooperationHandler.class);
    
    /**
     * @@roseuid 41E77F4A009C
     */
    public WEB3AioOnPaymentCooperationHandler()
    {
    }

    /**
     * (出金連携リクエスト) <BR>
     * 出金連携サービスを取得し、execute()メソッドをコールする。 <BR>
     * 
     * @@param l_request - リクエストデータ
     * @@return WEB3AioOnPaymentCooperationResponse
     * @@roseuid 41BCF40E01DD
     */
    public WEB3AioOnPaymentCooperationResponse onPaymentCooperationRequest(
        WEB3AioOnPaymentCooperationRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "onPaymentCooperationRequest(WEB3AioOnPaymentCooperationRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AioOnPaymentCooperationService l_service;
        WEB3AioOnPaymentCooperationResponse l_response;
        
        try
        {
            //FXへの振替サービスを取得し
            l_service = 
                (WEB3AioOnPaymentCooperationService)Services.getService(
                    WEB3AioOnPaymentCooperationService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__出金連携サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioOnPaymentCooperationResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioOnPaymentCooperationResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 出金連携サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}@
