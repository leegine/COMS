head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用取消ハンドラ(WEB3SrvRegiCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 李頴淵 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用取消ハンドラ)<BR>
 * サービス利用取消ハンドラクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiCancelHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiCancelHandler.class);
    
    /**
     * @@roseuid 416F415D0232
     */
    public WEB3SrvRegiCancelHandler() 
    {
     
    }
    
    /**
     * (confirm取消)<BR>
     * サービス利用取消確認審査処理を行う。<BR>
     * <BR>
     * サービス利用取消サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用取消確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse
     * @@roseuid 40F5ED4702B0
     */
    public WEB3SrvRegiCancelConfirmResponse confirmCancel(WEB3SrvRegiCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCancel(WEB3SrvRegiCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCancelConfirmResponse l_response = null;
        WEB3SrvRegiCancelService l_service = null;
        
        //サービス利用取消サービスを取得
        try
        {
            l_service =
                (WEB3SrvRegiCancelService)Services.getService(
                WEB3SrvRegiCancelService.class);       //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用取消サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);    
            return l_response;      
        }
        
        //サービス利用取消サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_service.execute(
                l_request);                    //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(
                l_request,
                "サービス利用取消サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;      
    }
    
    /**
     * (complete取消)<BR>
     * サービス利用取消処理を行う。<BR>
     * <BR>
     * サービス利用取消サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用取消完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse
     * @@roseuid 40F5ED8B00CC
     */
    public WEB3SrvRegiCancelCompleteResponse completeCancel(WEB3SrvRegiCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCancel(WEB3SrvRegiCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiCancelCompleteResponse l_response = null;
        WEB3SrvRegiCancelService l_service = null;
        
        //サービス利用取消サービスを取得
        try
        {
            l_service =
                (WEB3SrvRegiCancelService)Services.getService(
                WEB3SrvRegiCancelService.class);     //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用取消サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //サービス利用取消サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_service.execute(
                l_request);               //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用取消サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;     
    }
}
@
