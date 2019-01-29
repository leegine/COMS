head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正新規建ハンドラ(WEB3MarginChangeOpenMarginHandler.java)
Author Name      : 2004/10/8 盧法@旭(中訊) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginService;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正新規建ハンドラ）。<BR>
 * <BR>
 * 信用取引訂正新規建ハンドラクラス
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginChangeOpenMarginHandler.class);
    /**
     * @@roseuid 414184C5034F
     */
    public WEB3MarginChangeOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm訂正新規建)<BR>
     * 信用取引の訂正新規建発注審査を行う。<BR>
     * <BR>
     * 信用取引訂正新規建サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引訂正新規建確認リクエスト
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeConfirmResponse
     * @@roseuid 405818D2027E
     */
    public WEB3MarginOpenMarginChangeConfirmResponse confirmOpenMarginChange(WEB3MarginOpenMarginChangeConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME = "confirmOpenMarginChange(WEB3MarginOpenMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
                 throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3MarginChangeOpenMarginService l_service = null;
        WEB3MarginOpenMarginChangeConfirmResponse l_response = null;
        try
        {
            l_service = (WEB3MarginChangeOpenMarginService) Services.getService(WEB3MarginChangeOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginOpenMarginChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引訂正新規建サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginOpenMarginChangeConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引訂正新規建発注審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginOpenMarginChangeConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引訂正新規建発注審査に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (complete訂正新規建)<BR>
     * 信用取引の訂正新規建注文を登録する。<BR>
     * <BR>
     * 信用取引訂正新規建サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引訂正新規建完了リクエスト
     * @@return webbroker3.margin.message.WEB3MarginOpenMarginChangeCompleteResponse
     * @@roseuid 405818D2028E
     */
    public WEB3MarginOpenMarginChangeCompleteResponse completeOpenMarginChange(WEB3MarginOpenMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeOpenMarginChange(WEB3MarginOpenMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
                 throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3MarginChangeOpenMarginService l_service = null;
        WEB3MarginOpenMarginChangeCompleteResponse l_response = null;
        try
        {
            l_service = (WEB3MarginChangeOpenMarginService) Services.getService(WEB3MarginChangeOpenMarginService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MarginOpenMarginChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引訂正新規建サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3MarginOpenMarginChangeCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引訂正新規建注文に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginOpenMarginChangeCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引訂正新規建注文に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
