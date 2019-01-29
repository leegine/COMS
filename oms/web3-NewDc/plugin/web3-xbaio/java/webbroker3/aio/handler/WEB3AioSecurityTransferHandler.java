head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.23.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替ハンドラ(WEB3AioSecurityTransferHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioSecurityTransferCompleteRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferCompleteResponse;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmRequest;
import webbroker3.aio.message.WEB3AioSecurityTransferConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券振替ハンドラ)<BR>
 * 証券振替ハンドラクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferHandler.class);
    
    /**
     * (confirm注文)<BR>
     * 証券振替の発注審査を行う。 <BR>
     * <BR>
     * 証券振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSecurityTransferConfirmResponse
     * @@roseuid 41577AF0002E
     */
    public WEB3AioSecurityTransferConfirmResponse handleSecurityTransferConfirmRequest(
            WEB3AioSecurityTransferConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferConfirmRequest(WEB3AioSecurityTransferConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferService l_service;
        WEB3AioSecurityTransferConfirmResponse l_response;
        
        try
        {
            //証券振替入力サービスを取得し
            l_service = 
                (WEB3AioSecurityTransferService)Services.getService(
                    WEB3AioSecurityTransferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__証券振替サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioSecurityTransferConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 証券振替サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (complete注文)<BR>
     * 証券振替の登録を行う。<BR> 
     * <BR>
     * 証券振替サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * @@return WEB3AioSecurityTransferCompleteResponse
     * @@roseuid 41577AF00030
     */
    public WEB3AioSecurityTransferCompleteResponse handleSecurityTransferCompleteRequest(
            WEB3AioSecurityTransferCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "handleSecurityTransferCompleteRequest(WEB3AioSecurityTransferCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioSecurityTransferService l_service;
        WEB3AioSecurityTransferCompleteResponse l_response;
        
        try
        {
            //証券振替入力サービスを取得し
            l_service = 
                (WEB3AioSecurityTransferService)Services.getService(
                    WEB3AioSecurityTransferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__証券振替サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AioSecurityTransferCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioSecurityTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 証券振替サービス.execute__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
