head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSCancelExecHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来取消ハンドラ(WEB3AdminEquityPTSCancelExecHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 于瀟(中訊) 新規作成モデル178
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式（PTS）出来取消ハンドラ)<BR>
 * 管理者・株式（PTS）出来取消ハンドラクラス<BR>
 * <BR>
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3AdminEquityPTSCancelExecHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecHandler.class);

    /**
     * @@roseuid 4795B086004E
     */
    public WEB3AdminEquityPTSCancelExecHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 株式（PTS）出来取消入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来取消サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminEquityPTSCancelExecInputResponse
     * @@roseuid 4769BFC3035B
     */
    public WEB3AdminEquityPTSCancelExecInputResponse getInputScreen(
        WEB3AdminEquityPTSCancelExecInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSCancelExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecInputResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service = (WEB3AdminEquityPTSCancelExecService)Services.getService(
                WEB3AdminEquityPTSCancelExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来取消サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate出来取消)<BR>
     * 株式（PTS）出来取消確認処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来取消サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminEquityPTSCancelExecConfirmResponse
     * @@roseuid 4769C09501C8
     */
    public WEB3AdminEquityPTSCancelExecConfirmResponse validateCancelExec(
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateCancelExec(WEB3AdminEquityPTSCancelExecConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecConfirmResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来取消サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit出来取消)<BR>
     * 株式（PTS）出来取消完了処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来取消サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * @@return WEB3AdminEquityPTSCancelExecCompleteResponse
     * @@roseuid 4769C0A900FD
     */
    public WEB3AdminEquityPTSCancelExecCompleteResponse submitCancelExec(
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitCancelExec(WEB3AdminEquityPTSCancelExecCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecCompleteResponse l_response = null;
        WEB3AdminEquityPTSCancelExecService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityPTSCancelExecService)Services.getService(
                    WEB3AdminEquityPTSCancelExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来取消サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSCancelExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "管理者・株式（PTS）出来取消の処理の実施に失敗しました。",
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
