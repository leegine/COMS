head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報パスワードリセットハンドラ(WEB3AdminAccInfoLoginPasswordResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報パスワードリセットハンドラ)<BR>
 * 管理者お客様情報パスワードリセットハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordResetHandler.class);       
    
    /**
     * @@roseuid 418F3A0C03A9
     */
    public WEB3AdminAccInfoLoginPasswordResetHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * パスワードリセット入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者お客様情報パスワードリセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報パスワードリセット入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B72DE027C
     */
    public WEB3AdminAccInfoLoginPasswordResetInputResponse inputScreenDisplay(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordResetInputResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordResetService)Services.getService(WEB3AdminAccInfoLoginPasswordResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報パスワードリセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " 管理者お客様情報パスワードリセットの入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (パスワードリセット)<BR>
     * パスワードリセットを実施する。<BR>
     * <BR>
     * 管理者お客様情報パスワードリセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報パスワードリセットリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetResponse
     * @@roseuid 4158E8920124
     */
    public WEB3AdminAccInfoLoginPasswordResetResponse loginPasswordReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " loginPasswordReset(WEB3AdminAccInfoLoginPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordResetResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordResetService)Services.getService(WEB3AdminAccInfoLoginPasswordResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報パスワードリセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " 管理者お客様情報パスワードリセットのパスワードリセットに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
