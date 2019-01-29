head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMProductStopStartChangeServiceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者商品別取扱停止再開照会サービスHandler(WEB3AdminTMProductStopStartChangeServiceHandler.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trademanagement.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartChangeService;

/**
 * (管理者商品別取扱停止再開変更ハンドラ)<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeServiceHandler<BR>
 * <BR>
 * (管理者商品別取扱停止再開変更ハンドラクラス)<BR>
 * <BR>
 * WEB3AdminTMProductStopStartChangeServiceHandler class<BR>
 *  @@author Rajesh Sharma
 *  @@version 1.0
 */
public class WEB3AdminTMProductStopStartChangeServiceHandler implements MessageHandler
{
    /**
      * @@log WEB3LogUtility
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMProductStopStartChangeServiceHandler.class);

    /**
     * @@roseuid 41DD400D00BD
     */
    public WEB3AdminTMProductStopStartChangeServiceHandler()
    {
    }

    /**
     * (confirm変更)<BR>
     * <BR>
     * 商品別取扱停止再開変更確認処理を行う。<BR>
     * <BR>
     * 管理者商品別取扱停止再開変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>---------------<BR>
     * <BR>
     * confirmChange<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartChangeService confirm process.<BR>
     * <BR>
     * Acquire WEB3AdminTMProductStopStartChangeServiceImpl  and call the execute()
     * method. <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・商品別取扱停止再開変更確認リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartConfirmRequest object<BR>
     * <BR>
     * <BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMPStopStartConfirmResponse
     * @@roseuid 417479AE017A
     */
    public WEB3AdminTMPStopStartConfirmResponse
         confirmChange(WEB3AdminTMPStopStartConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChange()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMPStopStartConfirmResponse l_response = null;
        WEB3AdminTMProductStopStartChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminTMProductStopStartChangeService) Services.getService(
                    WEB3AdminTMProductStopStartChangeService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMPStopStartConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMProductStopStartChangeServiceImpl ",
                l_response.errorInfo,
                l_exp);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMPStopStartConfirmResponse) l_service.execute(l_request);

        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access validateChange()", l_exp);
            l_response = (WEB3AdminTMPStopStartConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete変更)<BR>
     * <BR>
     * 商品別取扱停止再開変更完了処理を行う。<BR>
     * <BR>
     * 管理者商品別取扱停止再開変更サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * ----<English>---------<BR>
     * <BR>
     * completeChange<BR>
     * <BR>
     * Execute WEB3AdminTMProductStopStartChangeService complete process.<BR>
     * <BR>
     * Acquire WEB3AdminTMProductStopStartChangeServiceImpl, and call the execute()
     * method. <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 管理者・商品別取扱停止再開変更完了リクエストオブジェクト<BR>
     * <BR>
     * l_request<BR>
     * <BR>
     * WEB3AdminTMPStopStartCompleteRequest object<BR>
     * <BR>
     * @@return webbroker3.trademnagement.message.WEB3AdminTMPStopStartCompleteResponse
     * @@roseuid 417479E50283
     */
    public WEB3AdminTMPStopStartCompleteResponse
             completeChange(WEB3AdminTMPStopStartCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChange()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminTMPStopStartCompleteResponse l_response = null;
        WEB3AdminTMProductStopStartChangeService l_service = null;

        try
        {
            l_service =
                (WEB3AdminTMProductStopStartChangeService) Services.getService(
                    WEB3AdminTMProductStopStartChangeService.class);

        } catch (Exception l_exp)
        {
            l_response = (WEB3AdminTMPStopStartCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;

            log.error(
                l_request,
                "Failed while acquiring WEB3AdminTMProductStopStartChangeServiceImpl",
                l_response.errorInfo,
                l_exp);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        try
        {
            l_response = (WEB3AdminTMPStopStartCompleteResponse) l_service.execute(l_request);

        } catch (WEB3BaseException l_exp)
        {
            log.error(l_request, "Failed to access submitChange()", l_exp);

            l_response = (WEB3AdminTMPStopStartCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
