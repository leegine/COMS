head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginChangeOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正新規建ハンドラ(WEB3ToSuccMarginChangeOpenMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/17　@呉　@鈞(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引訂正新規建ハンドラ)<BR>
 * （連続）信用取引訂正新規建ハンドラクラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginChangeOpenMarginHandler implements MessageHandler 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginChangeOpenMarginHandler.class);
    
    /**
     * @@roseuid 4369ED320203
     */
    public WEB3ToSuccMarginChangeOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm訂正新規建)<BR>
     * （連続）信用取引の訂正新規建発注審査を行う。<BR>
     * <BR>
     * （連続）信用取引訂正新規建サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引注文訂正新規建確認リクエスト<BR>
     * @@return WEB3SuccMarginOpenChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCD6903DE
     */
    public WEB3SuccMarginOpenChangeConfirmResponse confirmOpenMarginChange(
        WEB3SuccMarginOpenChangeConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " confirmOpenMarginChange(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
     
        WEB3SuccMarginOpenChangeConfirmResponse l_response = null;
        WEB3ToSuccMarginChangeOpenMarginService l_service = null;

        try
        {            
            //（連続）信用取引訂正新規建サービスを取得し
            l_service =
                (WEB3ToSuccMarginChangeOpenMarginService) Services.getService(
                    WEB3ToSuccMarginChangeOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引の訂正新規建発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引の訂正新規建発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete訂正新規建)<BR>
     * （連続）信用取引の訂正新規建注文を登録する。<BR>
     * <BR>
     * （連続）信用取引訂正新規建サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - （連続）信用取引注文訂正新規建完了リクエスト
     * @@return WEB3SuccMarginOpenChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433BCD6A0016
     */
    public WEB3SuccMarginOpenChangeCompleteResponse completeOpenMarginChange(
        WEB3SuccMarginOpenChangeCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " confirmOpenMarginChange(WEB3SuccMarginOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenChangeCompleteResponse l_response = null;
        WEB3ToSuccMarginChangeOpenMarginService l_service = null;

        try
        {            
            //（連続）信用取引訂正新規建サービスを取得し
            l_service =
                (WEB3ToSuccMarginChangeOpenMarginService) Services.getService(
                    WEB3ToSuccMarginChangeOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引の訂正新規建注文の登録に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引の訂正新規建注文の登録に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
