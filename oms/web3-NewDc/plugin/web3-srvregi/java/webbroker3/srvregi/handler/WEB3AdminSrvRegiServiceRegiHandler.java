head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiServiceRegiHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者サービス登録ハンドラ(WEB3AdminSrvRegiServiceRegiHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 郭英 (中訊) 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceRegiService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用管理者サービス登録ハンドラ)<BR>
 * サービス利用管理者サービス登録ハンドラクラス<BR>
 * 
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminSrvRegiServiceRegiHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiServiceRegiHandler.class);
    
    /**
     * @@roseuid 416F415B0271
     */
    public WEB3AdminSrvRegiServiceRegiHandler() 
    {
     
    }
    
    /**
     * (confirmサービス登録)<BR>
     * サービス利用管理者サービス登録審査処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス登録サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス登録確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse
     * @@roseuid 40F50F980363
     */
    public WEB3AdminSrvRegiServiceRegistConfirmResponse confirmServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceRegistConfirmResponse l_response = null;
        WEB3AdminSrvRegiServiceRegiService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceRegiService)Services.getService(WEB3AdminSrvRegiServiceRegiService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceRegistConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
                log.error(
                l_request, 
                " サービス利用管理者サービス登録審査に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス登録サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceRegistConfirmResponse) l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス登録審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (completeサービス登録)<BR>
     * サービス利用管理者サービス登録処理を行う。<BR>
     * <BR>
     * サービス利用管理者サービス登録サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者サービス登録確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse
     * @@roseuid 40F50FE302D7
     */
    public WEB3AdminSrvRegiServiceRegistCompleteResponse completeServiceRegi(WEB3AdminSrvRegiServiceRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeServiceRegi(WEB3AdminSrvRegiServiceRegistConfirmRequest) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminSrvRegiServiceRegistCompleteResponse l_response = null;
        WEB3AdminSrvRegiServiceRegiService l_service = null;
        
        try
        {
            l_service = (WEB3AdminSrvRegiServiceRegiService)Services.getService(WEB3AdminSrvRegiServiceRegiService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceRegistCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
                log.error(
                l_request, 
                " サービス利用管理者サービス登録に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //サービス利用管理者サービス登録サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminSrvRegiServiceRegistCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminSrvRegiServiceRegistCompleteResponse)l_request.createResponse();//WEB3BaseException
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " サービス利用管理者サービス登録に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
}
@
