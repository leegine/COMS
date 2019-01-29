head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会ハンドラ(WEB3AdminEquityForcedSettleReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.129
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済注文照会ハンドラ)<BR>
 * 管理者・強制決済注文照会ハンドラクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleReferenceHandler.class);

    /**
     * @@roseuid 462CA416039F
     */
    public WEB3AdminEquityForcedSettleReferenceHandler()
    {

    }

    /**
     * (get入力画面)<BR>
     * 強制決済注文照会入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者・強制決済注文照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済注文照会入力リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse
     * @@roseuid 4601EA71006C
     */
    public WEB3AdminForcedSettleRefInputResponse getInputScreen(WEB3AdminForcedSettleRefInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminForcedSettleRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleRefInputResponse l_response = null;
        WEB3AdminEquityForcedSettleReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleReferenceService)Services.getService(
                    WEB3AdminEquityForcedSettleReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済注文照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleRefInputResponse)l_request.createResponse();
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

    /**
     * (get照会画面)<BR>
     * 強制決済注文照会処理を行う。<BR>
     * <BR>
     * 管理者・強制決済注文照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済注文照会リクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse
     * @@roseuid 4601EA71008B
     */
    public WEB3AdminForcedSettleReferenceResponse getReferenceScreen(WEB3AdminForcedSettleReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "getReferenceScreen(WEB3AdminForcedSettleReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminForcedSettleReferenceResponse l_response = null;
        WEB3AdminEquityForcedSettleReferenceService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleReferenceService)Services.getService(
                    WEB3AdminEquityForcedSettleReferenceService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済注文照会サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminForcedSettleReferenceResponse)l_request.createResponse();
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
