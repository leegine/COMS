head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.00.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建ハンドラ(WEB3ToSuccMarginOpenMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08　@呉　@鈞(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引新規建ハンドラ)<BR>
 * （連続）信用取引新規建ハンドラクラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginHandler.class);

    /**
     * @@roseuid 4369ED3101A5
     */
    public WEB3ToSuccMarginOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm注文)<BR>
     * （連続）信用取引新規建発注審査を行う。<BR>
     * <BR>
     * （連続）信用取引新規建サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引新規建注文確認リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginOpenConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77550152
     */
    public WEB3SuccMarginOpenConfirmResponse confirmOrder(
        WEB3SuccMarginOpenConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SuccMarginOpenConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenConfirmResponse l_response = null;
        WEB3ToSuccMarginOpenMarginService l_service = null;

        try
        {            
            //（連続）信用取引新規建サービスImplを取得し
            l_service =
                (WEB3ToSuccMarginOpenMarginService) Services.getService(WEB3ToSuccMarginOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引新規建サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引新規建発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引新規建発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete注文)<BR>
     * （連続）信用取引新規建注文登録を行う。<BR>
     * <BR>
     * （連続）信用取引新規建サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引新規建注文完了リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginOpenCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 432F77AD01B0
     */
    public WEB3SuccMarginOpenCompleteResponse completeOrder(
        WEB3SuccMarginOpenCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SuccMarginOpenCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccMarginOpenCompleteResponse l_response = null;
        WEB3ToSuccMarginOpenMarginService l_service = null;

        try
        {            
            //（連続）信用取引新規建サービスImplを取得し
            l_service =
                (WEB3ToSuccMarginOpenMarginService) Services.getService(WEB3ToSuccMarginOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引新規建サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                l_response.errorMessage = l_ex.getErrorMessage();
            }
            log.error(
                l_request, 
                "（連続）信用取引新規建注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginOpenCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引新規建注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
