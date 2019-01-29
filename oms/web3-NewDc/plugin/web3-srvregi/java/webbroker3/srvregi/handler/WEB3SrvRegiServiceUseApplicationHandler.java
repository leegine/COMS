head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiServiceUseApplicationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用申込ハンドラ(WEB3SrvRegiServiceUseApplicationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 李頴淵 新規作成
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.util.WEB3LogUtility;

/**
 * (サービス利用申込ハンドラ)<BR>
 * サービス利用申込ハンドラクラス<BR>
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3SrvRegiServiceUseApplicationHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiServiceUseApplicationHandler.class);
    
    /**
     * @@roseuid 416F415E004E
     */
    public WEB3SrvRegiServiceUseApplicationHandler() 
    {
     
    }
    
    /**
     * (confirm利用申込)<BR>
     * サービス利用申込審査処理を行う。<BR>
     * <BR>
     * サービス利用申込サービスを取得し、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用申込確認リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse
     * @@roseuid 40F5EFA901A7
     */
    public WEB3SrvRegiApplyConfirmResponse confirmUseAppli(WEB3SrvRegiApplyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmUseAppli(WEB3SrvRegiApplyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplyConfirmResponse l_response = null;
        WEB3SrvRegiServiceUseApplicationService l_service = null;
        
        //サービス利用申込サービスを取得
        try
        {
            l_service =
                (WEB3SrvRegiServiceUseApplicationService)Services.getService(
                WEB3SrvRegiServiceUseApplicationService.class);  //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //サービス利用申込サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_service.execute(
                    l_request);         //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用申込サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
     
    }
    
    /**
     * (complete利用申込)<BR>
     * サービス利用申込登録処理を行う。<BR>
     * <BR>
     * サービス利用申込サービスを取得し、、execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用申込完了リクエスト　@オブジェクト<BR>
     * @@return webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse
     * @@roseuid 40F5EFB000DC
     */
    public WEB3SrvRegiApplyCompleteResponse completeUseAppli(WEB3SrvRegiApplyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeUseAppli(WEB3SrvRegiApplyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SrvRegiApplyCompleteResponse l_response = null;
        WEB3SrvRegiServiceUseApplicationService l_service = null;
        
        //サービス利用申込サービスを取得
        try
        {
            l_service =
                (WEB3SrvRegiServiceUseApplicationService)Services.getService(
                WEB3SrvRegiServiceUseApplicationService.class);   //Exception
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;      
        }
        
        //サービス利用申込サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_service.execute(
                    l_request);  //WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SrvRegiApplyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            if (l_ex.getErrorMessage() != null && !"".equals(l_ex.getErrorMessage())
                && !WEB3ErrorCatalog.BUSINESS_ERROR_03027.equals(l_ex.getErrorInfo())
                && !WEB3ErrorCatalog.BUSINESS_ERROR_03019.equals(l_ex.getErrorInfo())
                && !WEB3ErrorCatalog.SYSTEM_ERROR_80006.equals(l_ex.getErrorInfo()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request,
                "サービス利用申込サービスに失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
