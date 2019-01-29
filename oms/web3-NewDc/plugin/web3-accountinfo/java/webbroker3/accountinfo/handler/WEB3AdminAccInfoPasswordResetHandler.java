head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.14.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号リセットハンドラ(WEB3AdminAccInfoPasswordResetHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報暗証番号リセットハンドラ)<BR>
 * 管理者お客様情報暗証番号リセットハンドラ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordResetHandler.class);   
    
    /**
     * @@roseuid 418F3A0F0109
     */
    public WEB3AdminAccInfoPasswordResetHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 暗証番号リセット入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者お客様情報暗証番号リセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号リセット入力リクエストデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B78730366
     */
    public WEB3AdminAccInfoPasswordResetInputResponse getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordResetInputResponse l_response = null;
        WEB3AdminAccInfoPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordResetService)Services.getService(WEB3AdminAccInfoPasswordResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報暗証番号リセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報暗証番号リセットの入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (暗証番号リセット)<BR>
     * 暗証番号リセットを実施する。<BR>
     * <BR>
     * 管理者お客様情報暗証番号リセットサービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号リセットリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetResponse
     * @@roseuid 41649DB400F6
     */
    public WEB3AdminAccInfoPasswordResetResponse passwordReset(WEB3AdminAccInfoPasswordResetRequest l_request) 
    {
        final String STR_METHOD_NAME = " passwordReset(WEB3AdminAccInfoPasswordResetRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordResetResponse l_response = null;
        WEB3AdminAccInfoPasswordResetService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordResetService)Services.getService(WEB3AdminAccInfoPasswordResetService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報暗証番号リセットサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordResetResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報暗証番号リセットの暗証番号リセットに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
