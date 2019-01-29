head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginErrorResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ログインエラーリセットハンドラ(WEB3AdminAccInfoLoginErrorResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginErrorResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ログインエラーリセットハンドラ)<BR>
 * 管理者お客様情報ログインエラーリセットハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginErrorResetHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginErrorResetHandler.class);   
    
    /**
     * @@roseuid 418F3A0D02CE
     */
    public WEB3AdminAccInfoLoginErrorResetHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * ログインエラーリセット入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者お客様情報ログインエラーリセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ログインエラーリセット入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B745B027C
     */
    public WEB3AdminAccInfoLoginErrorResetInputResponse inputScreenDisplay(WEB3AdminAccInfoLoginErrorResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginErrorResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginErrorResetInputResponse l_response = null;
        WEB3AdminAccInfoLoginErrorResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginErrorResetService)Services.getService(WEB3AdminAccInfoLoginErrorResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報ログインエラーリセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報ログインエラーリセットの入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (ログインエラーリセット)<BR>
     * ログインエラーリセットを実施する。<BR>
     * <BR>
     * 管理者お客様情報ログインエラーリセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ログインエラーリセットリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginErrorResetResponse
     * @@roseuid 4159354C0155
     */
    public WEB3AdminAccInfoLoginErrorResetResponse loginErrorReset(WEB3AdminAccInfoLoginErrorResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " loginErrorReset(WEB3AdminAccInfoLoginErrorResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginErrorResetResponse l_response = null;
        WEB3AdminAccInfoLoginErrorResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginErrorResetService)Services.getService(WEB3AdminAccInfoLoginErrorResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報ログインエラーリセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginErrorResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報ログインエラーリセットのログインエラーリセットに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
