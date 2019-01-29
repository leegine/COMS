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
filename	WEB3AdminEquityForcedSettleOrderApproveHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認ハンドラ(WEB3AdminEquityForcedSettleOrderApproveHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認ハンドラ)<BR>
 * 管理者・強制決済仮注文承認／非承認ハンドラクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveHandler.class);

    /**
     * @@roseuid 462CA4170285
     */
    public WEB3AdminEquityForcedSettleOrderApproveHandler()
    {

    }

    /**
     * (confirm承認／非承認)<BR>
     * 強制決済仮注文承認／非承認確認処理を行う。<BR>
     * <BR>
     * 管理者・強制決済仮注文承認／非承認サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認確認リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse
     * @@roseuid 460323A4002E
     */
    public WEB3AdminForcedSettleApproveConfirmResponse confirmApprove(
        WEB3AdminForcedSettleApproveConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmApprove(WEB3AdminForcedSettleApproveConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveConfirmResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済仮注文承認／非承認サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "強制決済仮注文承認／非承認確認処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (run承認／非承認)<BR>
     * 強制決済仮注文承認／非承認処理起動を行う。<BR>
     * <BR>
     * 管理者・強制決済仮注文承認／非承認サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認処理起動リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveLapseRunResponse
     * @@roseuid 460323A4004E
     */
    public WEB3AdminForcedSettleApproveRunResponse runApprove(
        WEB3AdminForcedSettleApproveRunRequest l_request)
    {
        final String STR_METHOD_NAME = "runApprove(WEB3AdminForcedSettleApproveRunRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveRunResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済仮注文承認／非承認サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "強制決済仮注文承認／非承認処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (confirm処理ステータス)<BR>
     * 強制決済仮注文承認／非承認の処理ステータスを確認する。<BR>
     * <BR>
     * 管理者・強制決済仮注文承認／非承認サービスImplを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認処理ステータス確認リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveLapseStatusResponse
     * @@roseuid 460323A400CB
     */
    public WEB3AdminForcedSettleApproveStatusResponse confirmStatus(
        WEB3AdminForcedSettleApproveStatusRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmStatus(WEB3AdminForcedSettleApproveStatusRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleApproveStatusResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済仮注文承認／非承認サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "強制決済仮注文承認／非承認の処理の実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminForcedSettleApproveStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
