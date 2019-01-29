head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡ハンドラ(WEB3ToSuccMarginSwapMarginHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 沈迪(中訊) 新規作成
*/

package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引現引現渡ハンドラ)<BR>
 * （連続）信用取引現引現渡ハンドラクラス。<BR>
 * 
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginHandler.class);
    
    /**
     * @@roseuid 4369ED2F0290
     */
    public WEB3ToSuccMarginSwapMarginHandler() 
    {
     
    }
    
    /**
     * (confirm注文)<BR>
     * （連続）信用取引現引現渡発注審査を行う。<BR>
     * <BR>
     * （連続）信用取引現引現渡サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引現引現渡注文確認リクエストオブジェクト。<BR>
     * @@return WEB3SuccMarginSwapConfirmResponse
     * @@roseuid 43436A0E0292
     */
    public WEB3SuccMarginSwapConfirmResponse confirmOrder(WEB3SuccMarginSwapConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmOrder(WEB3SuccMarginSwapConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginSwapConfirmResponse l_response = null;
        WEB3ToSuccMarginSwapMarginService l_service = null;

        try
        {            
            //（連続）信用取引現引現渡サービスImplを取得し
            l_service =
                (WEB3ToSuccMarginSwapMarginService) Services.getService(WEB3ToSuccMarginSwapMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引現引現渡サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginSwapConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡発注審査の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete注文)<BR>
     * （連続）信用取引現引現渡注文登録を行う。<BR>
     * <BR>
     * （連続）信用取引現引現渡サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引現引現渡注文完了リクエストオブジェクト。<BR>
     * @@return WEB3SuccMarginSwapCompleteResponse
     * @@roseuid 43436A0E02A2
     */
    public WEB3SuccMarginSwapCompleteResponse completeOrder(WEB3SuccMarginSwapCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " completeOrder(WEB3SuccMarginSwapCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginSwapCompleteResponse l_response = null;
        WEB3ToSuccMarginSwapMarginService l_service = null;

        try
        {            
            //（連続）信用取引現引現渡サービスImplを取得し
            l_service =
                (WEB3ToSuccMarginSwapMarginService) Services.getService(WEB3ToSuccMarginSwapMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_e)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request,
                "（連続）信用取引現引現渡サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_e);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccMarginSwapCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "（連続）信用取引現引現渡注文登録の実施に失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
