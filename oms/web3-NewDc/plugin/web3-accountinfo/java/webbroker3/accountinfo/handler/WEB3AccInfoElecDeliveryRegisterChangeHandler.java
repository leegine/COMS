head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付サービス登録・変更ハンドラ(WEB3AccInfoElecDeliveryRegisterChangeHandler.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.278
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (電子交付サービス登録・変更ハンドラ)<BR>
 * 電子交付サービス登録・変更ハンドラ<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeHandler.class);

    /**
     * (入力画面表示)<BR>
     * 電子交付サービス登録・変更入力画面表示処理を行う。<BR>
     * <BR>
     * 電子交付サービス登録・変更サービスを取得し、execute()メソッドをコールする。<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeInputResponse inputScreenDisplay(
        WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request)
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AccInfoElecDeliveryRegisterChangeInputRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_response = null;
        WEB3AccInfoElecDeliveryRegisterChangeService l_service = null;

        //電子交付サービス登録・変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoElecDeliveryRegisterChangeService)Services.getService(
                WEB3AccInfoElecDeliveryRegisterChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子交付サービス登録・変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更入力画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更入力画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (電子交付サービス登録・変更)<BR>
     * 電子交付サービス登録・変更処理を行う。<BR>
     * <BR>
     * 電子交付サービス登録・変更サービスを取得し、execute()メソッドをコールする。<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse elecDeliveryRegisterChange(
        WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "elecDeliveryRegisterChange(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse l_response = null;
        WEB3AccInfoElecDeliveryRegisterChangeService l_service = null;

        //電子交付サービス登録・変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoElecDeliveryRegisterChangeService)Services.getService(
                WEB3AccInfoElecDeliveryRegisterChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子交付サービス登録・変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (電子交付情報一覧)<BR>
     * 電子交付情報一覧表示処理を行う。<BR>
     * <BR>
     * 電子交付サービス登録・変更サービスを取得し、execute()メソッドをコールする。<BR>
     */
    public WEB3AccInfoElecDeliveryApyReferenceResponse elecDeliveryApyReference(
        WEB3AccInfoElecDeliveryApyReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = "elecDeliveryApyReference(WEB3AccInfoElecDeliveryApyReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AccInfoElecDeliveryApyReferenceResponse l_response = null;
        WEB3AccInfoElecDeliveryRegisterChangeService l_service = null;

        //電子交付サービス登録・変更サービスを取得
        try
        {
            l_service = (WEB3AccInfoElecDeliveryRegisterChangeService)Services.getService(
                WEB3AccInfoElecDeliveryRegisterChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "電子交付サービス登録・変更サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AccInfoElecDeliveryApyReferenceResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccInfoElecDeliveryApyReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "電子交付サービス登録・変更処理に失敗しました。",
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
