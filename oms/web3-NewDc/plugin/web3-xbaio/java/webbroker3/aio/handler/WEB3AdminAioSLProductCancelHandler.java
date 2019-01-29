head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSLProductCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄取消ハンドラクラス(WEB3AdminAioSLProductCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 孫洪江 (中訊) 新規作成 仕様変更モデル760
Revision History : 2007/09/28 金傑 (中訊) 仕様変更モデル797
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteResponse;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保銘柄取消ハンドラ)<BR>
 * 担保銘柄取消ハンドラクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminAioSLProductCancelHandler implements MessageHandler
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductCancelHandler.class);

    /**
     * @@roseuid 46E8EE2A0375
     */
    public WEB3AdminAioSLProductCancelHandler()
    {

    }

    /**
     *（validate担保銘柄取消）<BR>
     * 担保銘柄取消確認画面表示処理を行う。<BR>
     * <BR>
     * 担保銘柄取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLProductCancelConfirmResponse
     */
    public WEB3AdminSLProductCancelConfirmResponse validateSLProductCancel(
        WEB3AdminSLProductCancelConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateSLProductCancel(WEB3AdminSLProductCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductCancelConfirmResponse l_response = null;
        WEB3AdminAioSLProductCancelService l_service = null;

        // 担保銘柄取消サービスを取得
        try
        {
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                WEB3AdminAioSLProductCancelService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "担保銘柄取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //担保銘柄取消サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request, "担保銘柄取消確認画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "担保銘柄取消確認画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     *（submit担保銘柄取消）<BR>
     * 担保銘柄取消完了画面表示処理を行う。<BR>
     * <BR>
     * 担保銘柄取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3AdminSLProductCancelCompleteResponse
     */
    public WEB3AdminSLProductCancelCompleteResponse submitSLProductCancel(
        WEB3AdminSLProductCancelCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitSLProductCancel(WEB3AdminSLProductCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSLProductCancelCompleteResponse l_response = null;
        WEB3AdminAioSLProductCancelService l_service = null;

        // 担保銘柄取消サービスを取得
        try
        {
            l_service = (WEB3AdminAioSLProductCancelService)Services.getService(
                WEB3AdminAioSLProductCancelService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "担保銘柄取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //担保銘柄取消サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();

            log.error(l_request, "担保銘柄取消完了画面表示処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminSLProductCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
               "担保銘柄取消完了画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
