head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録ハンドラ(WEB3AccOpenMailAddressRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 孟亞南 (中訊) 新規作成 モデル No.166
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設メールアドレス登録ハンドラ)<BR>
 * 口座開設メールアドレス登録ハンドラ<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressRegistHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AccOpenMailAddressRegistHandler.class);

    /**
     * (入力画面表示)<BR>
     * 口座開設メールアドレス登録入力画面表示処理を行う。 <BR>
     * <BR>
     * 口座開設メールアドレス登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設メールアドレス登録入力リクエスト
     * @@return 口座開設メールアドレス登録入力レスポンス
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenMailAddrRegInputResponse inputScreenDisplay(WEB3AccOpenMailAddrRegInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AccOpenMailAddrRegInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenMailAddressRegistService l_service;
        WEB3AccOpenMailAddrRegInputResponse l_response;

        try
        {
            l_service = (WEB3AccOpenMailAddressRegistService)Services.getService(
                    WEB3AccOpenMailAddressRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "口座開設メールアドレス登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設メールアドレス登録入力画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設メールアドレス登録入力画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (登録完了)<BR>
     * 口座開設メールアドレス登録完了処理を行う。 <BR>
     * <BR>
     * 口座開設メールアドレス登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 口座開設メールアドレス登録完了リクエスト
     * @@return 口座開設メールアドレス登録完了レスポンス
     * @@throws WEB3BaseException
     */
    public WEB3AccOpenMailAddrRegCompleteResponse registComplete(WEB3AccOpenMailAddrRegCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "registComplete(WEB3AccOpenMailAddrRegCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenMailAddressRegistService l_service;
        WEB3AccOpenMailAddrRegCompleteResponse l_response;

        try
        {
            l_service = (WEB3AccOpenMailAddressRegistService)Services.getService(
                    WEB3AccOpenMailAddressRegistService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "口座開設メールアドレス登録サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設メールアドレス登録完了処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenMailAddrRegCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設メールアドレス登録完了処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
