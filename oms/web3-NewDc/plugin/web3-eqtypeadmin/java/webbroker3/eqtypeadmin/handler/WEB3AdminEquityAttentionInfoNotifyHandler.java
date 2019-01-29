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
filename	WEB3AdminEquityAttentionInfoNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報通知ハンドラ(WEB3AdminEquityAttentionInfoNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/01/04 張少傑(中訊) 新規作成 モデルNo.219
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (注意情報通知ハンドラ)<BR>
 * 注意情報通知ハンドラ<BR>
 * <BR>
 * @@author 張少傑
 * @@version 1.0
 */
public class WEB3AdminEquityAttentionInfoNotifyHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityAttentionInfoNotifyHandler.class);

    /**
     * @@roseuid 49588AEC00AB
     */
    public WEB3AdminEquityAttentionInfoNotifyHandler()
    {

    }

    /**
     * (注意情報通知リクエスト)<BR>
     * 注意情報通知処理を実施する。<BR>
     * <BR>
     * 注意情報通知サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3AdminEquityAttentionInfoNotifyResponse
     * @@roseuid 493DF4EB032D
     */
    public WEB3AdminEquityAttentionInfoNotifyResponse attentionInfoNotifyRequest(
        WEB3AdminEquityAttentionInfoNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = "attentionInfoNotifyRequest(WEB3AdminEquityAttentionInfoNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityAttentionInfoNotifyService l_service = null;
        WEB3AdminEquityAttentionInfoNotifyResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminEquityAttentionInfoNotifyService)Services.getService(
                    WEB3AdminEquityAttentionInfoNotifyService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "注意情報通知サービス の取得に失敗しました。 ",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "注意情報通知処理の実施に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminEquityAttentionInfoNotifyResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "注意情報通知処理の実施に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
