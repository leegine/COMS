head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMutualRuitoExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者投信累投注文約定照会ハンドラ(WEB3AdminMutualRuitoExecuteReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminMutualRuitoExecuteReferenceService;

/**
 * (管理者投信累投注文約定照会ハンドラ)<BR>
 * <BR>
 * 管理者投信累投注文約定照会ハンドラクラス<BR>
 * <BR>
 * WEB3AdminMutualRuitoExecuteReferenceHandler<BR>
 * <BR>
 * @@author Shruti
 * @@version 1.0
 */
public class WEB3AdminMutualRuitoExecuteReferenceHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualRuitoExecuteReferenceHandler.class);

    /**
     * @@roseuid 4212FBD4023A
     */
    public WEB3AdminMutualRuitoExecuteReferenceHandler()
    {
    }

    /**
     * （get入力画面）<BR>
     * <BR>
     * 投信累投注文約定照会入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者投信累投注文約定照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminOrderExecuteCountReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - （リクエストデータ）<BR>
     * <BR>
     * 管理者・投信累投注文約定照会入力リクエストオブジェクト<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefInputRequest object<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * InputResponse
     * @@roseuid 41AE9CFA02F8
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefInputResponse
        getInputScreen(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getInputScreen(WEB3AdminORMutualRuitoOrderExecutionRefInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualRuitoExecuteReferenceService l_service = null;
        WEB3AdminORMutualRuitoOrderExecutionRefInputResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualRuitoExecuteReferenceService) Services.getService(
                    WEB3AdminMutualRuitoExecuteReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getInputScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getInputScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * <BR>
     * 投信累投注文約定照会処理を行う。<BR>
     * <BR>
     * 管理者投信累投注文約定照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminMutualRuitoExecuteReferenceService process<BR>
     * <BR>
     * Acquire WEB3AdminMutualRuitoExecuteReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・投信累投注文約定照会リクエストオブジェクト<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest obejct<BR>
     * <BR>
     * @@return
     * webbroker3.adminorderexecinquiry.message.WEB3AdminORMutualRuitoOrderExecutionRef
     * ReferenceResponse
     * @@roseuid 41AE9CFA0318
     */
    public WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse
        getReferenceScreen(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
            "getReferenceScreen(WEB3AdminORMutualRuitoOrderExecutionRefReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminMutualRuitoExecuteReferenceService l_service = null;
        WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse l_response = null;
        try
        {
            l_service =
                (WEB3AdminMutualRuitoExecuteReferenceService) Services.getService(
                    WEB3AdminMutualRuitoExecuteReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while getReferenceScreen request is called",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse) l_service.execute(
                    l_request);
        } catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminORMutualRuitoOrderExecutionRefReferenceResponse) l_request
                    .createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "getReferenceScreen Request failed", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}@
