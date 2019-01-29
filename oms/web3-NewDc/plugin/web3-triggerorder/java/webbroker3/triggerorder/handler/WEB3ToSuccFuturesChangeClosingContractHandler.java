head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正返済ハンドラ（WEB3ToSuccFuturesChangeClosingContractHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 トウ鋒鋼 (中訊) 新規作成 モデルNo.270
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物訂正返済ハンドラ)<BR>
 * （連続）先物訂正返済サービスハンドラクラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeClosingContractHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeClosingContractHandler.class);

    /**
     * @@roseuid 47D65936001F
     */
    public WEB3ToSuccFuturesChangeClosingContractHandler()
    {

    }

    /**
     * (confirm訂正返済)<BR>
     * （連続）先物訂正返済の発注審査を行う。<BR>
     * <BR>
     * （連続）先物訂正返済注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCloseChangeConfirmResponse
     * @@roseuid 47A9511E01B8
     */
    public WEB3SuccFuturesCloseChangeConfirmResponse confirmChangeClosingContract(
        WEB3SuccFuturesCloseChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3SuccFuturesCloseChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseChangeConfirmResponse l_response = null;
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            //（連続）先物訂正返済サービスを取得
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(（連続）先物訂正返済サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正返済の発注審査が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正返済の発注審査が失敗しました。",
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
     * （連続）先物訂正返済の更新を行う。<BR>
     * <BR>
     * （連続）先物訂正返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesCloseChangeCompleteResponse
     * @@roseuid 47A9513702A6
     */
    public WEB3SuccFuturesCloseChangeCompleteResponse completeChangeClosingContract(
        WEB3SuccFuturesCloseChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3SuccFuturesCloseChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseChangeCompleteResponse l_response = null;
        WEB3ToSuccFuturesChangeClosingContractService l_service = null;
        try
        {
            //（連続）先物訂正返済サービスを取得
            l_service = (WEB3ToSuccFuturesChangeClosingContractService)Services.getService(
                    WEB3ToSuccFuturesChangeClosingContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "(（連続）先物訂正返済サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正返済の更新が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesCloseChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正返済の更新が失敗しました。",
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
