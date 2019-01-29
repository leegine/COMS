head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.16.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885aca6009;
filename	WEB3RichPushMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : リッチクライアントプッシュハンドラクラス(WEB3RichPushMainHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉 (FLJ)新規作成
 */
package webbroker3.rcp.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.common.*;
import webbroker3.rcp.message.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * （リッチクライアントプッシュメインハンドラ）。
 * @@author  劉
 * @@version 1.0
 */
public class WEB3RichPushMainHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushMainHandler.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3RichPushMainHandler()
    {
    }

    /**
     * (リッチクライアントプッシュメインリクエスト)<BR>
     * リッチクライアントプッシュメインサービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - リクエストデータ<BR>
     * @@return WEB3RichPushMainResponse<BR>
     */
    public WEB3RichPushMainResponse handleRichPushMainRequest(
        WEB3RichPushMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "handleRichPushMainRequest(WEB3RichPushMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RichPushMainResponse l_response = new
            WEB3RichPushMainResponse();
        WEB3RichPushMainService l_service = null;

        try
        {
            l_service =
                (WEB3RichPushMainService) Services.getService(
                WEB3RichPushMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response = new WEB3RichPushMainResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "リッチクライアントプッシュメインサービス取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3RichPushMainResponse) l_service.execute(
                l_execNotifyMainRequest);

        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "リッチクライアントプッシュメイン処理に失敗しました。", l_exp);
            l_response =
                (WEB3RichPushMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "リッチクライアントプッシュメイン処理に失敗しました。", l_bre);
            l_response =
                (WEB3RichPushMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
