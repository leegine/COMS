head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecuredLoanOfferStateListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保ローン申込状況一覧ハンドラ(WEB3AioSecuredLoanOfferStateListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.756,No.790
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListResponse;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保ローン申込状況一覧ハンドラ)<BR>
 * 担保ローン申込状況一覧ハンドラクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AioSecuredLoanOfferStateListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanOfferStateListHandler.class);

    /**
     * @@roseuid 46E0BE4702AF
     */
    public WEB3AioSecuredLoanOfferStateListHandler()
    {

    }

    /**
     * (get一覧画面)<BR>
     * 担保ローン申込状況一覧画面表示処理を行う。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminSLAccountOpenApplyListResponse
     * @@roseuid 46D287BB0366
     */
    public WEB3AdminSLAccountOpenApplyListResponse getListScreen(
        WEB3AdminSLAccountOpenApplyListRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getListScreen(WEB3AdminSLAccountOpenApplyListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AioSecuredLoanOfferStateListService l_service;
        WEB3AdminSLAccountOpenApplyListResponse l_response;

        try
        {
            //担保ローン申込状況一覧サービス
            l_service =
                (WEB3AioSecuredLoanOfferStateListService)Services.getService(
                    WEB3AioSecuredLoanOfferStateListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "担保ローン申込状況一覧サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminSLAccountOpenApplyListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "担保ローン申込状況一覧サービス.execute()",
                l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_excRuntime)
        {
            l_response =
                (WEB3AdminSLAccountOpenApplyListResponse)l_request.createResponse();
            l_response.errorInfo = l_excRuntime.getErrorInfo();
            log.error(
                l_request,
                "担保ローン申込状況一覧サービス.execute()",
                l_excRuntime);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
