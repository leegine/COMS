head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.59.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccFuturesChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物訂正新規建ハンドラ（WEB3ToSuccFuturesChangeOpenContractHandler.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 トウ鋒鋼 (中訊) 新規作成 モデルNo.269
*/
package webbroker3.triggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）先物訂正新規建ハンドラ)<BR>
 * （連続）先物訂正新規建ハンドラクラス<BR>
 *
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3ToSuccFuturesChangeOpenContractHandler implements MessageHandler
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccFuturesChangeOpenContractHandler.class);

    /**
     * @@roseuid 47D65935033C
     */
    public WEB3ToSuccFuturesChangeOpenContractHandler()
    {

    }

    /**
     * (confirm訂正新規建)<BR>
     * （連続）先物訂正新規建注文の発注審査を行う。<BR>
     * <BR>
     * （連続）先物訂正新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesOpenChangeConfirmResponse
     * @@roseuid 47A944C703C9
     */
    public WEB3SuccFuturesOpenChangeConfirmResponse confirmChangeOpenContract(
        WEB3SuccFuturesOpenChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "confirmChangeOpenContract(WEB3SuccFuturesOpenChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeConfirmResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            // （連続）先物訂正新規建サービスを取得
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物訂正新規建サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正新規建注文の発注審査が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正新規建注文の発注審査が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (complete訂正新規建)<BR>
     * （連続）先物訂正新規建注文の更新を行う。<BR>
     * <BR>
     * （連続）先物訂正新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3SuccFuturesOpenChangeCompleteResponse
     * @@roseuid 47A944D600CE
     */
    public WEB3SuccFuturesOpenChangeCompleteResponse completeChangeOpenContract(
        WEB3SuccFuturesOpenChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeChangeOpenContract(WEB3SuccFuturesOpenChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenChangeCompleteResponse l_response = null;
        WEB3ToSuccFuturesChangeOpenContractService l_service = null;
        try
        {
            // （連続）先物訂正新規建サービスを取得
            l_service = (WEB3ToSuccFuturesChangeOpenContractService)Services.getService(
                WEB3ToSuccFuturesChangeOpenContractService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "（連続）先物訂正新規建サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            // execute()メソッドをコールする
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正新規建注文の更新が失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3SuccFuturesOpenChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "（連続）先物訂正新規建注文の更新が失敗しました。",
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
