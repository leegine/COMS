head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済ハンドラ(WEB3ToSuccMarginCloseMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/18　@呉　@鈞(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;
    
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （（連続）信用取引返済ハンドラ)<BR>
 * （連続）信用取引返済ハンドラクラス
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginHandler.class);
    
    /**
     * @@roseuid 4369ED34034B
     */
    public WEB3ToSuccMarginCloseMarginHandler() 
    {
     
    }
    
    /**
     * (confirm返済)<BR>
     * （連続）信用取引返済発注審査を行う。<BR>
     * <BR>
     * （連続）信用取引返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433244CD00E4
     */
    public WEB3SuccMarginCloseConfirmResponse confirmCloseMargin(
        WEB3SuccMarginCloseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " confirmCloseMargin(WEB3SuccMarginCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginCloseConfirmResponse l_response = null;
        WEB3ToSuccMarginCloseMarginService l_service = null;

        try
        {            
            //（連続）信用取引返済サービスを取得し
            l_service =
                (WEB3ToSuccMarginCloseMarginService) Services.getService(
                    WEB3ToSuccMarginCloseMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginCloseConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引返済発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引返済発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete返済)<BR>
     * （連続）信用取引返済注文登録を行う。<BR>
     * <BR>
     * （連続）信用取引返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433244CD01DE
     */
    public WEB3SuccMarginCloseCompleteResponse completeCloseMargin(
        WEB3SuccMarginCloseCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " completeCloseMargin(WEB3SuccMarginCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginCloseCompleteResponse l_response = null;
        WEB3ToSuccMarginCloseMarginService l_service = null;

        try
        {            
            //（連続）信用取引返済サービスを取得し
            l_service =
                (WEB3ToSuccMarginCloseMarginService) Services.getService(
                    WEB3ToSuccMarginCloseMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginCloseCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引返済サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginCloseCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginCloseCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引返済注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginCloseCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引返済注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
