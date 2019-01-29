head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客変更ハンドラ(WEB3AdminSrvRegiAccountChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張学剛 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客変更ハンドラ)<BR>
 * サービス利用管理者顧客変更ハンドラクラス<BR> 
 *                                                               
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminSrvRegiAccountChangeHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiAccountChangeHandler.class);
    
    /**
     * @@roseuid 416F415C03C8
     */
    public WEB3AdminSrvRegiAccountChangeHandler() 
    {
     
    }
    
    /**
     * (confirm顧客変更)<BR>
     * サービス利用管理者顧客変更審査処理を行う。<BR>
     * <BR>
     * サービス利用管理者顧客変更サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客変更確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse
     * @@roseuid 40F5E29000DC
     */
    public WEB3AdminSrvRegiCustomerChangeConfirmResponse confirmAccountChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmAccountChange(WEB3AdminSrvRegiCustomerChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiCustomerChangeConfirmResponse l_response = null;
        WEB3AdminSrvRegiAccountChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountChangeService)
                Services.getService(WEB3AdminSrvRegiAccountChangeService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者顧客変更サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者顧客変更確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (complete顧客変更)<BR>
     * サービス利用管理者顧客変更処理を行う。<BR>
     * <BR>
     * サービス利用管理者顧客変更サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者顧客変更完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse
     * @@roseuid 40F5E29C011A
     */
    public WEB3AdminSrvRegiCustomerChangeCompleteResponse completeAccountChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeAccountChange(WEB3AdminSrvRegiCustomerChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiCustomerChangeCompleteResponse l_response = null;
        WEB3AdminSrvRegiAccountChangeService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiAccountChangeService)Services.getService(WEB3AdminSrvRegiAccountChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " サービス利用管理者顧客変更サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiCustomerChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者顧客変更完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
             
        return l_response;
    }
}
@
