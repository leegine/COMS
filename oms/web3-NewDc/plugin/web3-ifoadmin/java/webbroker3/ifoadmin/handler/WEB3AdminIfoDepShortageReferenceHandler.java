head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会ハンドラ(WEB3AdminIfoDepShortageReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.005
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・証拠金不足状況照会ハンドラ)<BR>
 * 管理者・証拠金不足状況照会ハンドラクラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceHandler.class);

    /**
     * @@roseuid 49A74856037A
     */
    public WEB3AdminIfoDepShortageReferenceHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 管理者証拠金不足状況照会入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者・証拠金不足状況照会サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminIfoDepShortageRefInputResponse
     * @@roseuid 498FD72102E3
     */
    public WEB3AdminIfoDepShortageRefInputResponse getInputScreen(
        WEB3AdminIfoDepShortageRefInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminIfoDepShortageRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoDepShortageRefInputResponse l_response = null;
        WEB3AdminIfoDepShortageReferenceService l_service = null;

        try
        {
            //管理者・証拠金不足状況照会サービスImplを取得し
            l_service = (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                WEB3AdminIfoDepShortageReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・証拠金不足状況照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者証拠金不足状況照会入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageRefInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者証拠金不足状況照会入力画面表示処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * 管理者証拠金不足状況照会処理を行う <BR>
     * <BR>
     * 管理者・証拠金不足状況照会サービスImplを取得し、 <BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminIfoDepShortageReferenceResponse
     * @@roseuid 498FD73102C3
     */
    public WEB3AdminIfoDepShortageReferenceResponse getReferenceScreen(
        WEB3AdminIfoDepShortageReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminIfoDepShortageReferenceResponse l_response = null;
        WEB3AdminIfoDepShortageReferenceService l_service = null;

        try
        {
            //管理者注意情報照会サービスを取得し
            l_service = (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                    WEB3AdminIfoDepShortageReferenceService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・証拠金不足状況照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする。
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者証拠金不足状況照会処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoDepShortageReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者証拠金不足状況照会処理が失敗しました。",
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
