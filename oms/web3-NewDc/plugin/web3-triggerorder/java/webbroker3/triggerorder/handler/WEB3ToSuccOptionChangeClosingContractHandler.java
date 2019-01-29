head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.58.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :（連続）OP訂正返済ハンドラ（WEB3ToSuccOptionChangeClosingContractHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 孟亞南(中訊) 新規作成 モデルNo.307
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）OP訂正返済ハンドラ)<BR>
 * （連続）OP訂正返済サービスハンドラクラス<BR>
 *
 * @@author 孟亞南(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccOptionChangeClosingContractHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccOptionChangeClosingContractHandler.class);

    /**
     * @@roseuid 47FDBE3D03D8
     */
    public WEB3ToSuccOptionChangeClosingContractHandler()
    {

    }

    /**
     * (confirm訂正返済)<BR>
     * （連続）OP訂正返済の発注審査を行う。<BR>
     * <BR>
     * （連続）OP訂正返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsCloseChangeConfirmResponse
     * @@roseuid 47A92AE000FF
     */
    public WEB3SuccOptionsCloseChangeConfirmResponse confirmChangeClosingContract(
        WEB3SuccOptionsCloseChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3SuccOptionsCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractService l_service = null;
        try
        {
            //（連続）OP訂正返済注文サービスをを取得
            l_service = (WEB3ToSuccOptionChangeClosingContractService)Services.getService(
                WEB3ToSuccOptionChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP訂正返済注文サービスをを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正返済の発注審査が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正返済の発注審査が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete訂正返済)<BR>
     * （連続）OP訂正返済の更新を行う。<BR>
     * <BR>
     * （連続）OP訂正返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccOptionsCloseChangeCompleteResponse
     * @@roseuid 47A92B05015D
     */
    public WEB3SuccOptionsCloseChangeCompleteResponse completeChangeClosingContract(
        WEB3SuccOptionsCloseChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3SuccOptionsCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccOptionsCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccOptionChangeClosingContractService l_service = null;
        try
        {
            //（連続）OP訂正返済サービスを取得
            l_service = (WEB3ToSuccOptionChangeClosingContractService)Services.getService(
                WEB3ToSuccOptionChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）OP訂正返済サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正返済の更新が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccOptionsCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）OP訂正返済の更新が失敗しました。",
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
