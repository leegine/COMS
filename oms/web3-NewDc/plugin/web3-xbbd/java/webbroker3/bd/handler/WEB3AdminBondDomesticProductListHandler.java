head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄一覧ハンドラ(WEB3AdminBondDomesticProductListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 何文敏(中訊) 新規作成 仕様変更・モデルNo.193,No.209
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者国内債券銘柄一覧ハンドラ)<BR>
 * 管理者国内債券銘柄一覧ハンドラ<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductListHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductListHandler.class);

    /**
     * @@roseuid 4691D3AE0298
     */
    public WEB3AdminBondDomesticProductListHandler()
    {

    }

    /**
     * (get検索画面表示)<BR>
     * 管理者国内債券銘柄一覧サービスを取得し、execute()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductListSearchDisplayResponse
     * @@roseuid 4664E4600109
     */
    public WEB3AdminBondDomesticProductListSearchDisplayResponse getSearchScreenDisplay(
        WEB3AdminBondDomesticProductListSearchDisplayRequest l_request)
    {
        final String STR_METHOD_NAME =
            " getSearchScreenDisplay(WEB3AdminBondDomesticProductListSearchDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticProductListSearchDisplayResponse l_response = null;
        WEB3AdminBondDomesticProductListService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondDomesticProductListService)Services.getService(
                    WEB3AdminBondDomesticProductListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者国内債券銘柄一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListSearchDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get銘柄一覧画面表示)<BR>
     * 管理者国内債券銘柄一覧サービスを取得し、execute()をコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminBondDomesticProductListDisplayResponse
     * @@roseuid 4663A0EE03C8
     */
    public WEB3AdminBondDomesticProductListDisplayResponse getProductListScreenDisplay(
        WEB3AdminBondDomesticProductListDisplayRequest l_request)
    {
        final String STR_METHOD_NAME =
            " getProductListScreenDisplay(WEB3AdminBondDomesticProductListDisplayRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminBondDomesticProductListDisplayResponse l_response = null;
        WEB3AdminBondDomesticProductListService l_service = null;
        try
        {
            l_service =
                (WEB3AdminBondDomesticProductListService)Services.getService(
                    WEB3AdminBondDomesticProductListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者国内債券銘柄一覧サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminBondDomesticProductListDisplayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminBondDomesticProductListDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "execute()メソッドをコールすることが失敗しました。",
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
