head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSLRepayListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済一覧ハンドラ(WEB3AioSLRepayListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済一覧ハンドラ)<BR>
 * 証券担保ローン返済一覧ハンドラクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSLRepayListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayListHandler.class);

    /**
     * @@roseuid 46E890860387
     */
    public WEB3AioSLRepayListHandler()
    {

    }

    /**
     * (証券担保ローン返済一覧リクエスト)<BR>
     * 証券担保ローン返済一覧表示処理を行う。<BR>
     * <BR>
     * 証券担保ローン返済一覧サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SLRepayCancelListResponse
     * @@roseuid 46DE35DA0020
     */
    public WEB3SLRepayCancelListResponse repayCancelListRequest(
        WEB3SLRepayCancelListRequest l_request)
    {
        final String STR_METHOD_NAME = "repayCancelListRequest(WEB3SLRepayCancelListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SLRepayCancelListResponse l_response = null;
        WEB3AioSLRepayListService l_slRepayCancelListService = null;

        try
        {
            l_slRepayCancelListService =
                (WEB3AioSLRepayListService)Services.getService(
                        WEB3AioSLRepayListService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3SLRepayCancelListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "証券担保ローン返済一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //証券担保ローン返済一覧サービスを取得し、execute()メソッドをコールする
            l_response =
                (WEB3SLRepayCancelListResponse)l_slRepayCancelListService.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3SLRepayCancelListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "証券担保ローン返済一覧リクエストが失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SLRepayCancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "証券担保ローン返済一覧サービス処理が失敗しました。",
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
