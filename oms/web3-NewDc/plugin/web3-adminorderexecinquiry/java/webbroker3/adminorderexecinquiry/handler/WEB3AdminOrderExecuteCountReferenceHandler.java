head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.42.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOrderExecuteCountReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文約定件数照会ハンドラ(WEB3AdminOrderExecuteCountReferenceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;

import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORExecutionNumberReferenceResponse;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminOrderExecuteCountReferenceService;

/**
 * (管理者注文約定件数照会ハンドラ)<BR>
 * <BR>
 * 管理者注文約定件数照会ハンドラクラス<BR>
 * <BR>
 * WEB3AdminOrderExecuteCountReferenceHandler<BR>
 * <BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public class WEB3AdminOrderExecuteCountReferenceHandler implements MessageHandler
{
    /**
      * Log Variable
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOrderExecuteCountReferenceHandler.class);

    /**
     * @@roseuid 4212FCD20094
     */
    public WEB3AdminOrderExecuteCountReferenceHandler()
    {
    }

    /**
     * (get入力画面)<BR>
     * <BR>
     * 注文約定件数照会入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者注文約定件数照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getInputScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService input screen process<BR>
     * <BR>
     * Acquire WEB3AdminOrderExecuteCountReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者注文約定件数照会入力リクエストオブジェクト<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberInputRequest object<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberInputResponse
     * @@roseuid 419ACF350271
     */
    public WEB3AdminORExecutionNumberInputResponse getInputScreen(
        WEB3AdminORExecutionNumberInputRequest l_request)
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminORExecutionNumberInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminORExecutionNumberInputResponse l_response = null;
        WEB3AdminOrderExecuteCountReferenceService l_service = null;

        try
        {
            l_service =
                (WEB3AdminOrderExecuteCountReferenceService) Services.getService(
                    WEB3AdminOrderExecuteCountReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminORExecutionNumberInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOrderExecuteCountReferenceServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminORExecutionNumberInputResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getInputScreen()", l_exp);
            l_response = (WEB3AdminORExecutionNumberInputResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get照会画面)<BR>
     * <BR>
     * 注文約定件数照会処理を行う。<BR>
     * <BR>
     * 管理者注文約定件数照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * getReferenceScreen<BR>
     * <BR>
     * Execute WEB3AdminOrderExecuteCountReferenceService process<BR>
     * <BR>
     * Acquire WEB3AdminOrderExecuteCountReferenceServiceImpl, and call execute()
     * method<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者注文約定件数照会リクエストオブジェクト<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminORExecutionNumberReferenceRequest<BR>
     * <BR>
     * @@return WEB3AdminORExecutionNumberReferenceResponse
     * @@roseuid 419ACEDB00AC
     */
    public WEB3AdminORExecutionNumberReferenceResponse getReferenceScreen(
        WEB3AdminORExecutionNumberReferenceRequest l_request)
    {
        final String STR_METHOD_NAME =
             "getReferenceScreen(WEB3AdminORExecutionNumberReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminORExecutionNumberReferenceResponse l_response = null;
        WEB3AdminOrderExecuteCountReferenceService l_service = null;

        try
        {
            l_service =
                (WEB3AdminOrderExecuteCountReferenceService) Services.getService(
                    WEB3AdminOrderExecuteCountReferenceService.class);
        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminORExecutionNumberReferenceResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminOrderExecuteCountReferenceServiceImpl ",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminORExecutionNumberReferenceResponse) l_service.execute(l_request);
        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access getReferenceScreen()", l_exp);
            l_response = (WEB3AdminORExecutionNumberReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
