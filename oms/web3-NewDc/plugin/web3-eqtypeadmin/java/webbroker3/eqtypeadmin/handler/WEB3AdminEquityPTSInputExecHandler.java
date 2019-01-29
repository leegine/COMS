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
filename	WEB3AdminEquityPTSInputExecHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式（PTS）出来入力ハンドラ（WEB3AdminEquityPTSInputExecHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル177
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式（PTS）出来入力ハンドラ)<BR>
 * 管理者・株式（PTS）出来入力ハンドラクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecHandler.class);

    /**
     * @@roseuid 4795A0F703AD
     */
    public WEB3AdminEquityPTSInputExecHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 株式（PTS）出来入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminEquityPTSInputExecInputResponse
     * @@roseuid 477219EC02BA
     */
    public WEB3AdminEquityPTSInputExecInputResponse getInputScreen(
        WEB3AdminEquityPTSInputExecInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecInputResponse l_response = null;
        try
        {
            // 管理者・株式（PTS）出来入力サービスオブジェクトを取得
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来入力サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        // execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力画面表示処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力画面表示処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }

    /**
     * (validate出来入力)<BR>
     * 株式（PTS）出来入力確認処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminEquityPTSInputExecConfirmResponse
     * @@roseuid 477219F3028C
     */
    public WEB3AdminEquityPTSInputExecConfirmResponse validateInputExec(
        WEB3AdminEquityPTSInputExecConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecConfirmResponse l_response = null;
        try
        {
            // 管理者・株式（PTS）出来入力サービスオブジェクトを取得
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来入力サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        // execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力確認処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力確認処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }

    /**
     * (submit出来入力)<BR>
     * 株式（PTS）出来入力完了処理を行う。<BR>
     * <BR>
     * 管理者・株式（PTS）出来入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminEquityPTSInputExecCompleteResponse
     * @@roseuid 477219FD0098
     */
    public WEB3AdminEquityPTSInputExecCompleteResponse submitInputExec(
        WEB3AdminEquityPTSInputExecCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminEquityPTSInputExecInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSInputExecService l_service = null;
        WEB3AdminEquityPTSInputExecCompleteResponse l_response = null;
        try
        {
            // 管理者・株式（PTS）出来入力サービスオブジェクトを取得
            l_service =
                (WEB3AdminEquityPTSInputExecService)Services.getService(
                    WEB3AdminEquityPTSInputExecService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式（PTS）出来入力サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            // execute()メソッドをコールする
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力完了処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_request,
                "管理者・株式（PTS）出来入力サービスの株式（PTS）出来入力完了処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityPTSInputExecCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        return l_response;
    }
}
@
