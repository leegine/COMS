head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報共通入力ハンドラ(WEB3AccInfoCommonInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoCommonInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報共通入力ハンドラ)<BR>
 * お客様情報共通入力ハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommonInputHandler.class);
            
    /**
     * @@roseuid 418F3A0A031C
     */
    public WEB3AccInfoCommonInputHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 入力画面表示共通処理を実施する。<BR>
     * <BR>
     * お客様情報共通入力サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - お客様情報共通入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse
     * @@roseuid 41456E630366
     */
    public WEB3AccInfoCommonInputResponse inputScreenDisplay(WEB3AccInfoCommonInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AccInfoCommonInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AccInfoCommonInputResponse l_response = null;
        WEB3AccInfoCommonInputService l_service = null;

        try
        {
            l_service = (WEB3AccInfoCommonInputService)Services.getService(WEB3AccInfoCommonInputService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "お客様情報共通入力サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "お客様情報共通入力の入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
