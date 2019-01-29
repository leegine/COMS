head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoPasswordChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報暗証番号変更ハンドラ(WEB3AccInfoPasswordChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報暗証番号変更ハンドラ)<BR>
 * お客様情報暗証番号変更ハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoPasswordChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoPasswordChangeHandler.class);        
    
    /**
     * @@roseuid 418F3A0B0186
     */
    public WEB3AccInfoPasswordChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 暗証番号変更入力画面表示処理を行う。<BR>
     * <BR>
     * お客様情報暗証番号変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報暗証番号変更入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeInputResponse
     * @@roseuid 416BBA6E03C4
     */
    public WEB3AccInfoPasswordChangeInputResponse inputScreenDisplay(WEB3AccInfoPasswordChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccInfoPasswordChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoPasswordChangeInputResponse l_response = null;
        WEB3AccInfoPasswordChangeService l_service = null;

        try
        {
            l_service = (WEB3AccInfoPasswordChangeService)Services.getService(WEB3AccInfoPasswordChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報暗証番号変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "お客様情報暗証番号変更の入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (暗証番号変更)<BR>
     * 暗証番号変更処理を行う。<BR>
     * <BR>
     * お客様情報暗証番号変更サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報暗証番号変更リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoPasswordChangeCompleteResponse
     * @@roseuid 416BBA6E03E3
     */
    public WEB3AccInfoPasswordChangeCompleteResponse changePassword(WEB3AccInfoPasswordChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " changePassword(WEB3AccInfoPasswordChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoPasswordChangeCompleteResponse l_response = null;
        WEB3AccInfoPasswordChangeService l_service = null;

        try
        {
            l_service = (WEB3AccInfoPasswordChangeService)Services.getService(WEB3AccInfoPasswordChangeService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報暗証番号変更サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoPasswordChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "お客様情報暗証番号変更の暗証番号変更に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
