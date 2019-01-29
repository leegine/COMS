head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.46.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除ハンドラ(WEB3AdminAccOpenApplyDataDelHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.159
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設資料請求データ削除ハンドラ)<BR>
 * 管理者口座開設資料請求データ削除ハンドラ<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminAccOpenApplyDataDelHandler.class);

    /**
     * @@roseuid 4940F22B0290
     */
    public WEB3AdminAccOpenApplyDataDelHandler()
    {

    }

    /**
     * (検索画面表示)<BR>
     * 口座開設資料請求データ削除検索画面表示処理を行う。<BR>
     * <BR>
     * 管理者口座開設資料請求データ削除サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除検索リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelSearchResponse
     * @@throws WEB3BaseException
     * @@roseuid 493388650351
     */
    public WEB3AdminAccOpenApplyDataDelSearchResponse displaySearchScreen(
        WEB3AdminAccOpenApplyDataDelSearchRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "displaySearchScreen(WEB3AdminAccOpenApplyDataDelSearchRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelSearchResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者口座開設資料請求データ削除サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除検索画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelSearchResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除検索画面表示処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (削除確認)<BR>
     * 口座開設資料請求データ削除確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設資料請求データ削除サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除確認リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCnfResponse
     * @@throws WEB3BaseException
     * @@roseuid 493389DA03E4
     */
    public WEB3AdminAccOpenApplyDataDelCnfResponse deleteConfirm(
        WEB3AdminAccOpenApplyDataDelCnfRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteConfirm(WEB3AdminAccOpenApplyDataDelCnfRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelCnfResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者口座開設資料請求データ削除サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除確認処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCnfResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除確認処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (削除完了)<BR>
     * 口座開設資料請求データ削除完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設資料請求データ削除サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者口座開設資料請求データ削除完了リクエストデータオブジェクト<BR>
     * @@return WEB3AdminAccOpenApplyDataDelCmpResponse
     * @@roseuid 493389EB027E
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse deleteComplete(
        WEB3AdminAccOpenApplyDataDelCmpRequest l_request)
    {
        final String STR_METHOD_NAME = "deleteComplete(WEB3AdminAccOpenApplyDataDelCmpRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccOpenApplyDataDelService l_service;
        WEB3AdminAccOpenApplyDataDelCmpResponse l_response;

        try
        {
            l_service = (WEB3AdminAccOpenApplyDataDelService)Services.getService(
                WEB3AdminAccOpenApplyDataDelService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者口座開設資料請求データ削除サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除完了処理に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyDataDelCmpResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "口座開設資料請求データ削除完了処理に失敗しました。",
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
