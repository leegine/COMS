head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.28.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3CCOperatorAccountListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧ハンドラ(WEB3CCOperatorAccountListHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成 モデルNo.039
*/

package webbroker3.login.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import com.fitechlabs.xtrade.kernel.message.Response;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (CCオペレータ対象顧客一覧ハンドラ)<BR>
 * CCオペレータ対象顧客一覧ハンドラ<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListHandler implements MessageHandler
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListHandler.class);

    /**
     * (CCオペレータ対象顧客一覧ハンドラ)<BR>
     * コンストラクタ<BR>
     * @@roseuid 46A45A21004E
     */
    public WEB3CCOperatorAccountListHandler()
    {

    }

    /**
     * (CCオペレータ対象顧客一覧リクエスト)<BR>
     * CCオペレータ対象顧客一覧リクエスト<BR>
     * @@param l_request - (リクエスト)
     * リクエスト
     * @@return Response
     * @@roseuid 46949C3C0207
     */
    public Response ccOperatorAccountListRequest(WEB3CCOperatorAccountListRequest l_request)
    {

        final String STR_METHOD_NAME =
            " ccOperatorAccountListRequest(WEB3CCOperatorAccountListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3CCOperatorAccountListResponse l_response = null;
        WEB3CCOperatorAccountListService l_service = null;

        try
        {
            l_service =
                (WEB3CCOperatorAccountListService)Services.getService(
                    WEB3CCOperatorAccountListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "CCオペレータ対象顧客一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3CCOperatorAccountListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
