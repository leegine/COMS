head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderExecNotifyMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインハンドラ(WEB3EquityOrderExecNotifyMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 中尾寿彦(SRA) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.equity.message.WEB3EquityExecNotifyMainResponse;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来通知メインハンドラ）。
 * @@author 中尾寿彦
 * @@version 1.0
 */
public class WEB3EquityOrderExecNotifyMainHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyMainHandler.class);

    /**
     * デフォルトコンストラクタ。<BR>
     */
    public WEB3EquityOrderExecNotifyMainHandler()
    {
    }

    /**
     * (出来通知メインリクエスト)<BR>
     * <BR>
     * 現物・信用取引の出来通知サービス振り分け処理を実施する。<BR>
     * <BR>
     * 株式出来通知メインサービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_execNotifyMainRequest - リクエストデータ<BR>
     * @@return WEB3EquityExecNotifyMainResponse<BR>
     */
    public WEB3EquityExecNotifyMainResponse equityOrderExecNotifyMainRequest(WEB3EquityExecNotifyMainRequest l_execNotifyMainRequest)
    {
        final String STR_METHOD_NAME =
            "equityOrderExecNotifyMainRequest(WEB3EquityExecNotifyMainRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityExecNotifyMainResponse l_response = null;
        WEB3EquityOrderExecNotifyMainService l_service = null;

        try
        {
            l_service =
                (WEB3EquityOrderExecNotifyMainService) Services.getService(
                    WEB3EquityOrderExecNotifyMainService.class);
        }
        catch (Exception l_exp)
        {
            l_response =
                (WEB3EquityExecNotifyMainResponse) l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_execNotifyMainRequest,
                "株式出来通知メインサービス取得に失敗しました。",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_service.execute(
                    l_execNotifyMainRequest);
        }
        catch (WEB3BaseException l_exp)
        {
            log.error(l_execNotifyMainRequest, "株式出来通知メイン処理に失敗しました。", l_exp);
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            log.error(l_execNotifyMainRequest, "株式出来通知メイン処理に失敗しました。", l_bre);
            l_response =
                (WEB3EquityExecNotifyMainResponse)l_execNotifyMainRequest.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
