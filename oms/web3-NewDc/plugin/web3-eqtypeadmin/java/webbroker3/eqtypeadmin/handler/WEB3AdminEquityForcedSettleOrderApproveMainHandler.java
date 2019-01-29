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
filename	WEB3AdminEquityForcedSettleOrderApproveMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認メインハンドラ(WEB3AdminEquityForcedSettleOrderApproveMainHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデルNo.129
Revision History : 2007/04/28 張騰宇 モデルNo.148
*/
package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認メインハンドラ)<BR>
 * 管理者・強制決済仮注文承認／非承認メインハンドラクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */

public class WEB3AdminEquityForcedSettleOrderApproveMainHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveMainHandler.class);

    /**
     * @@roseuid 462CA41801AB
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainHandler()
    {

    }

    /**
     * (承認／非承認リクエスト)<BR>
     * （非同期）強制決済仮注文承認／非承認処理を起動する。<BR>
     * <BR>
     * 管理者・強制決済仮注文承認／非承認メインサービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・強制決済仮注文承認／非承認メインリクエストオブジェクト<BR>
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse
     * @@roseuid 460325730213
     */
    public WEB3AdminEquityForcedSettleOrderApproveMainResponse approveRequest(
        WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request)
    {
        final String STR_METHOD_NAME = "approveRequest(WEB3AdminEquityForcedSettleOrderApproveMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveMainResponse l_response = null;
        WEB3AdminEquityForcedSettleOrderApproveMainService l_service = null;
        try
        {
            l_service =
                (WEB3AdminEquityForcedSettleOrderApproveMainService)Services.getService(
                    WEB3AdminEquityForcedSettleOrderApproveMainService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・強制決済仮注文承認／非承認メインサービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_request, "（非同期）強制決済仮注文承認／非承認処理の処理ステータスの実施に失敗しました。", l_ex);
            l_response =
                (WEB3AdminEquityForcedSettleOrderApproveMainResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
