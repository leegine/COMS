head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceBidPriceUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス落札額更新ハンドラ(WEB3AdminSrvRegiServiceBidPriceUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/
package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateService;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者サービス落札額更新ハンドラ)<BR>
 * サービス利用管理者サービス落札額更新ハンドラクラス<BR> 
 *                                                               
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceBidPriceUpdateHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class);
    
    /**
     * @@roseuid 416F415D0119
     */
    public WEB3AdminSrvRegiServiceBidPriceUpdateHandler() 
    {
     
    }
    
    /**
     * (confirm落札額更新)<BR>
     * サービス利用管理者サービス落札額更新審査処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス落札額更新サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス落札額更新リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse
     * @@roseuid 40F5E98A01E5
     */
    public WEB3AdminSrvRegiSuccBidConfirmResponse confirmBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmBidPriceUpdate(WEB3AdminSrvRegiSuccBidConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidConfirmResponse l_response = null;
        WEB3AdminSrvRegiServiceBidPriceUpdateService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceBidPriceUpdateService)Services.getService(WEB3AdminSrvRegiServiceBidPriceUpdateService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス落札額更新サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス落札額更新に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (complete落札額更新)<BR>
     * サービス利用管理者サービス落札額更新処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス落札額更新サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス落札額更新リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse
     * @@roseuid 40F5E9C5039B
     */
    public WEB3AdminSrvRegiSuccBidCompleteResponse completeBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeBidPriceUpdate(WEB3AdminSrvRegiSuccBidCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiSuccBidCompleteResponse l_response = null;
        WEB3AdminSrvRegiServiceBidPriceUpdateService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceBidPriceUpdateService)Services.getService(WEB3AdminSrvRegiServiceBidPriceUpdateService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者サービス落札額更新サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiSuccBidCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス落札額更新サービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
             
        return l_response;
    }
}
@
