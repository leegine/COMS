head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者PTS口座開設状況変更ハンドラ(WEB3AdminInformPTSAccOpenStateChangeHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/27 柴双紅(中訊) 新規作成 モデルNo.129
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者PTS口座開設状況変更ハンドラ<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformPTSAccOpenStateChangeHandler.class);

    /**
     * @@roseuid 47B9272A02ED
     */
    public WEB3AdminInformPTSAccOpenStateChangeHandler()
    {

    }

    /**
     * (検索画面表示)<BR>
     * 管理者PTS口座開設状況変更検索画面表示処理を行う。<BR>
     * <BR>
     * 管理者PTS口座開設状況変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeSrcResponse
     * @@roseuid 47A0221A34E
     */
    public WEB3AdminInformPTSAccOpenStateChangeSrcResponse displaySearchScrean(
        WEB3AdminInformPTSAccOpenStateChangeSrcRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displaySearchScrean(WEB3AdminInformPTSAccOpenStateChangeSrcRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccOpenStateChangeSrcResponse l_response = null;
        WEB3AdminInformPTSAccOpenStateChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminInformPTSAccOpenStateChangeService)Services.getService(
                    WEB3AdminInformPTSAccOpenStateChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者PTS口座開設状況変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS口座開設状況変更検索画面の取得にエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeSrcResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS口座開設状況変更検索画面の取得にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (入力画面表示)<BR>
     * 管理者PTS口座開設状況変更入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者PTS口座開設状況変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeInpResponse
     * @@roseuid 47A0221C02F8
     */
    public WEB3AdminInformPTSAccOpenStateChangeInpResponse displayInputScrean(
        WEB3AdminInformPTSAccOpenStateChangeInpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "displayInputScrean(WEB3AdminInformPTSAccOpenStateChangeInpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccOpenStateChangeInpResponse l_response = null;
        WEB3AdminInformPTSAccOpenStateChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminInformPTSAccOpenStateChangeService)Services.getService(
                    WEB3AdminInformPTSAccOpenStateChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者PTS口座開設状況変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS口座開設状況変更入力画面の取得にエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeInpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS口座開設状況変更入力画面の取得にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (変更確認)<BR>
     * 管理者PTS口座開設状況変更確認処理を行う。<BR>
     * <BR>
     * 管理者PTS口座開設状況変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCnfResponse
     * @@roseuid 47A0221F0A58
     */
    public WEB3AdminInformPTSAccOpenStateChangeCnfResponse changeConfirm(
        WEB3AdminInformPTSAccOpenStateChangeCnfRequest l_request)
    {
        final String STR_METHOD_NAME =
            "changeConfirm(WEB3AdminInformPTSAccOpenStateChangeCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccOpenStateChangeCnfResponse l_response = null;
        WEB3AdminInformPTSAccOpenStateChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminInformPTSAccOpenStateChangeService)Services.getService(
                    WEB3AdminInformPTSAccOpenStateChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者PTS口座開設状況変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS口座開設状況変更確認処理にエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS口座開設状況変更確認処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (変更完了)<BR>
     * 管理者PTS口座開設状況変更完了処理を行う。<BR>
     * <BR>
     * 管理者PTS口座開設状況変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminInformPTSAccOpenStateChangeCmpResponse
     * @@roseuid 47A5C31302E4
     */
    public WEB3AdminInformPTSAccOpenStateChangeCmpResponse changeComplete(
        WEB3AdminInformPTSAccOpenStateChangeCmpRequest l_request)
    {
        final String STR_METHOD_NAME =
            "changeComplete(WEB3AdminInformPTSAccOpenStateChangeCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminInformPTSAccOpenStateChangeCmpResponse l_response = null;
        WEB3AdminInformPTSAccOpenStateChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminInformPTSAccOpenStateChangeService)Services.getService(
                    WEB3AdminInformPTSAccOpenStateChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者PTS口座開設状況変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者PTS口座開設状況変更完了処理にエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminInformPTSAccOpenStateChangeCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者PTS口座開設状況変更完了処理にエラーが発生しました。",
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
