head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文有効期限取得ハンドラ(WEB3ExpirationDateListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 于瀟(中訊) 新規作成モデル319
*/

package webbroker3.gentrade.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.gentrade.message.WEB3ExpirationDateListResponse;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (注文有効期限取得ハンドラ)<BR>
 * 注文有効期限取得ハンドラクラス<BR>
 *
 * @@author 于瀟
 * @@version 1.0
 */
public class WEB3ExpirationDateListHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListHandler.class);

    /**
     * @@roseuid 47B3E132006D
     */
    public WEB3ExpirationDateListHandler()
    {

    }

    /**
     * (注文有効期限取得リクエスト)<BR>
     * 注文有効期限取得処理を行う。<BR>
     * <BR>
     * 注文有効期限取得サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * 注文有効期限取得リクエストオブジェクト<BR>
     * @@return WEB3ExpirationDateListResponse
     * @@roseuid 47A2DF0301BC
     */
    public WEB3ExpirationDateListResponse expirationDateListRequest(WEB3ExpirationDateListRequest l_request)
    {
        final String STR_METHOD_NAME = "expirationDateListRequest(WEB3ExpirationDateListRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3ExpirationDateListResponse l_response = null;
        WEB3ExpirationDateListService l_service = null;
        try
        {
            l_service = (WEB3ExpirationDateListService)Services.getService(
                WEB3ExpirationDateListService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3ExpirationDateListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "注文有効期限取得サービスの取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3ExpirationDateListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3ExpirationDateListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "注文有効期限取得の処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3ExpirationDateListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, "注文有効期限取得の処理の実施に失敗しました。",
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
