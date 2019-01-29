head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.27.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2144d885d4c6839;
filename	WEB3RlsCondOrderNotifyMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : ルールエンジン通知ハンドラクラス(WEB3RlsCondOrderNotifyMainHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/01 劉 (FLJ)新規作成
 */
package webbroker3.omsadaptor.handler;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.handler.*;
import webbroker3.common.*;
import webbroker3.omsadaptor.message.*;
import webbroker3.omsadaptor.service.delegate.*;
import webbroker3.util.*;

/**
 * （ルールエンジン通知メインハンドラ）。
 * @@author  劉
 * @@version 1.0
 */
public class WEB3RlsCondOrderNotifyMainHandler
    implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsCondOrderNotifyMainHandler.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3RlsCondOrderNotifyMainHandler()
    {
    }

    /**
     * (ルールエンジン通知メインリクエスト)<BR>
     * ルールエンジン通知メインサービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - リクエストデータ<BR>
     * @@return WEB3RlsCondOrderNotifyMainResponse<BR>
     */
    public WEB3RlsCondOrderNotifyMainResponse handleRlsCondOrderNotifyMainRequest(
        WEB3RlsCondOrderNotifyMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "handleRlsCondOrderNotifyMainRequest(WEB3RlsCondOrderNotifyMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3RlsCondOrderNotifyMainResponse l_response = new
            WEB3RlsCondOrderNotifyMainResponse();
        WEB33RlsCondOrderNotifyMainService l_service = null;

        try
        {
            l_service =
                (WEB33RlsCondOrderNotifyMainService) Services.getService(
                WEB33RlsCondOrderNotifyMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response = new WEB3RlsCondOrderNotifyMainResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "ルールエンジン通知メインサービス取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_service.execute(
                l_execNotifyMainRequest);

        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "ルールエンジン通知メイン処理に失敗しました。", l_exp);
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "ルールエンジン通知メイン処理に失敗しました。", l_bre);
            l_response =
                (WEB3RlsCondOrderNotifyMainResponse) l_execNotifyMainRequest.
                createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
