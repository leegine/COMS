head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管ハンドラ(WEB3AdminAccOpenDataTransferHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 181
*/
package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設データ移管ハンドラ)<BR>
 * 管理者口座開設データ移管ハンドラクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferHandler.class);

    /**
     * @@roseuid 4A8A083401D4
     */
    public WEB3AdminAccOpenDataTransferHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 入力画面の表示を行う。 <BR>
     * <BR>
     * 管理者口座開設データ移管サービスを取得し、execute()メソッドをコールする。 <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminAccOpenDataTransferInputResponse
     * @@roseuid 4A821F5A03D8
     */
    public WEB3AdminAccOpenDataTransferInputResponse getInputScreen(
        WEB3AdminAccOpenDataTransferInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminAccOpenDataTransferInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenDataTransferInputResponse l_response = null;
        WEB3AdminAccOpenDataTransferService l_service = null;

        try
        {
            l_service = (WEB3AdminAccOpenDataTransferService)Services.getService(
                WEB3AdminAccOpenDataTransferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座開設データ移管サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者口座開設データ移管入力画面の表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "管理者口座開設データ移管入力画面の表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submitデータ移管)<BR>
     * データ移管の処理を行う。 <BR>
     * <BR>
     * 管理者口座開設データ移管サービスを取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminAccOpenDataTransferCompleteResponse
     * @@roseuid 4A821F5C0399
     */
    public WEB3AdminAccOpenDataTransferCompleteResponse submitDataTransfer(
        WEB3AdminAccOpenDataTransferCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitDataTransfer(WEB3AdminAccOpenDataTransferCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenDataTransferCompleteResponse l_response = null;
        WEB3AdminAccOpenDataTransferService l_service = null;

        try
        {
            l_service = (WEB3AdminAccOpenDataTransferService)Services.getService(
                WEB3AdminAccOpenDataTransferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者口座開設データ移管サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者口座開設データ移管処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenDataTransferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "管理者口座開設データ移管処理が失敗しました。",
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
