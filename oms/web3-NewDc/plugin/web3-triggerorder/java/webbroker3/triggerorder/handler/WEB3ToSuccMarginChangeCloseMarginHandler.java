head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeCloseMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済ハンドラ(WEB3ToSuccMarginChangeCloseMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17 譚漢江(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引訂正返済ハンドラ)<BR>
 * （連続）信用取引訂正返済ハンドラクラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeCloseMarginHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeCloseMarginHandler.class);

    /**
     * @@roseuid 4369ED33029F
     */
    public WEB3ToSuccMarginChangeCloseMarginHandler() 
    {
     
    }
    
    /**
     * (confirm訂正返済)<BR>
     * 信用取引の訂正返済発注審査を行う。<BR>
     * <BR>
     * （連続）信用取引訂正返済サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文訂正返済確認リクエストオブジェクト。<BR>
     * @@return WEB3SuccMarginCloseChangeConfirmResponse
     * @@roseuid 433CFF7A01FD
     */
    public WEB3SuccMarginCloseChangeConfirmResponse confirmCloseMarginChange(
        WEB3SuccMarginCloseChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmCloseMarginChange(WEB3SuccMarginCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccMarginChangeCloseMarginService l_service = null;
        
        try
        {            
            //get（連続）信用取引訂正返済サービス
            l_service = (WEB3ToSuccMarginChangeCloseMarginService)
                Services.getService(WEB3ToSuccMarginChangeCloseMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）信用取引訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "信用取引の訂正返済発注審査の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "信用取引の訂正返済発注審査の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete訂正返済)<BR>
     * 信用取引の訂正返済注文を登録する。<BR>
     * <BR>
     * （連続）信用取引訂正返済サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文訂正返済完了リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginCloseChangeCompleteResponse
     * @@roseuid 433CFF7A021C
     */
    public WEB3SuccMarginCloseChangeCompleteResponse completeCloseMarginChange(
        WEB3SuccMarginCloseChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeCloseMarginChange(WEB3SuccMarginCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccMarginChangeCloseMarginService l_service = null;
        
        try
        {            
            //get（連続）信用取引訂正返済サービス
            l_service = (WEB3ToSuccMarginChangeCloseMarginService)
                Services.getService(WEB3ToSuccMarginChangeCloseMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(l_request,
                "（連続）信用取引訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "信用取引の訂正返済注文の登録に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "信用取引の訂正返済注文の登録に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
