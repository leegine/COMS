head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済取消ハンドラ(WEB3AioSLRepayCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayCancelCompleteRequest;
import webbroker3.aio.message.WEB3SLRepayCancelCompleteResponse;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmRequest;
import webbroker3.aio.message.WEB3SLRepayCancelConfirmResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済取消ハンドラ)<BR>
 * 証券担保ローン返済取消ハンドラ<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayCancelHandler.class);

    /**
     * @@roseuid 46E890860319
     */
    public WEB3AioSLRepayCancelHandler()
    {

    }

    /**
     * (confirm注文)<BR>
     * 証券担保ローン返済取消の発注審査を行う。<BR>
     * <BR>
     * 証券担保ローン返済取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_slRepayCancelConfirmRequest - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayCancelConfirmResponse
     * @@roseuid 46DE4C0701D1
     */
    public WEB3SLRepayCancelConfirmResponse confirmOrder(
        WEB3SLRepayCancelConfirmRequest l_slRepayCancelConfirmRequest)
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3SLRepayCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelConfirmResponse l_response = null;
        WEB3AioSLRepayCancelService l_service = null;

        try
        {
            l_service = (WEB3AioSLRepayCancelService)Services.getService(WEB3AioSLRepayCancelService.class);
        }
        catch (Exception l_ex)
        {
            // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_slRepayCancelConfirmRequest,
                "証券担保ローン返済取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // 証券担保ローン返済取消サービスを取得し、execute()メソッドをコールする
            l_response = (WEB3SLRepayCancelConfirmResponse)l_service.execute(l_slRepayCancelConfirmRequest);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelConfirmRequest,
                "証券担保ローン返済取消確認リクエストが失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelConfirmResponse)l_slRepayCancelConfirmRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelConfirmRequest,
                "証券担保ローン返済取消確認サービス処理が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete注文)<BR>
     * 証券担保ローン返済取消の登録を行う。<BR>
     * <BR>
     * 証券担保ローン返済取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_slRepayCancelCompleteRequest - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayCancelCompleteResponse
     * @@roseuid 46DE4C0B0393
     */
    public WEB3SLRepayCancelCompleteResponse completeOrder(
        WEB3SLRepayCancelCompleteRequest l_slRepayCancelCompleteRequest)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3SLRepayCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelCompleteResponse l_response = null;
        WEB3AioSLRepayCancelService l_slRepayCancelService = null;

        try
        {
            l_slRepayCancelService =
                (WEB3AioSLRepayCancelService) Services.getService(
                    WEB3AioSLRepayCancelService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_slRepayCancelCompleteRequest,
                "証券担保ローン返済取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            //証券担保ローン返済取消サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3SLRepayCancelCompleteResponse)l_slRepayCancelService.execute(
                    l_slRepayCancelCompleteRequest);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelCompleteRequest,
                "証券担保ローン返済取消完了リクエストが失敗しました。",
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelCompleteResponse) l_slRepayCancelCompleteRequest.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_slRepayCancelCompleteRequest,
                "証券担保ローン返済申込サービス処理が失敗しました。",
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
