head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FaqInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 問合せ管理お問合せ入力サービス(WEB3FaqInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.faq.message.WEB3FaqCompleteRequest;
import webbroker3.faq.message.WEB3FaqCompleteResponse;
import webbroker3.faq.message.WEB3FaqConfirmRequest;
import webbroker3.faq.message.WEB3FaqConfirmResponse;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.WEB3FaqInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (問合せ管理お問合せ入力サービス)<BR>
 * 問合せ管理お問合せ入力サービス<BR>
 */
public class WEB3FaqInputHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputHandler.class);

    /**
     * @@roseuid 41C25BF300BB
     */
    public WEB3FaqInputHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * 問合せ管理お問合せ入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 問合せ管理お問合せ入力リクエストデータオブジェクト
     * 
     * 
     * @@return WEB3FaqInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41ABFB70011E
     */
    public WEB3FaqInputResponse inputScreenDisplay(WEB3FaqInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3FaqInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqInputResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "問合せ管理お問合せ入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "問合せ管理お問合せ入力の入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (問合せ入力確認)<BR>
     * 問合せ入力確認処理を行う。 <BR>
     * <BR>
     * 問合せ管理お問合せ入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 問合せ管理お問合せ確認リクエストデータオブジェクト
     * @@return WEB3FaqConfirmResponse
     * @@roseuid 41ABFB700276
     */
    public WEB3FaqConfirmResponse faqInputConfirm(WEB3FaqConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqInputConfirm(WEB3FaqConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqConfirmResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "問合せ管理お問合せ入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "問合せ管理お問合せ入力の問合せ入力確認に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (問合せ入力完了)<BR>
     * 問合せ入力完了処理を行う。 <BR>
     * <BR>
     * 問合せ管理お問合せ入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 問合せ管理お問合せ完了リクエストデータオブジェクト
     * @@return WEB3FaqCompleteResponse
     * @@roseuid 41ABFB700332
     */
    public WEB3FaqCompleteResponse faqInputComplete(WEB3FaqCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " faqInputComplete(WEB3FaqCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3FaqCompleteResponse l_response = null;
        WEB3FaqInputService l_service = null;

        try
        {
            l_service = (WEB3FaqInputService)Services.getService(WEB3FaqInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "問合せ管理お問合せ入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3FaqCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FaqCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "問合せ管理お問合せ入力の問合せ入力完了に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
