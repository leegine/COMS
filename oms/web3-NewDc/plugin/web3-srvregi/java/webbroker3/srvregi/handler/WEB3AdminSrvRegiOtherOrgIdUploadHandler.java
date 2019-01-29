head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminSrvRegiOtherOrgIdUploadHandler.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/03/10 柴双紅(中訊) 新規作成 モデルNo.337
*/

package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdUploadHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class);

    /**
     * @@roseuid 47D111370225
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadHandler()
    {

    }

    /**
     * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面表示)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力リクエスト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadInputResponse
     * @@roseuid 47B940CA021F
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadInputResponse otherOrgIdUploadScreenDisplay(
        WEB3AdminSrvRegiOtherOrgIdUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadScreenDisplay(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadInputResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ画面表示処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞファ@イル確認)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞファ@イル確認処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞファ@イル確認サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞファ@イル確認リクエスト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse
     * @@roseuid 47B940CA0223
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse otherOrgIdUploadConfirm(
        WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadConfirm(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }

            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞサービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞリクエスト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse
     * @@roseuid 47B940CA022F
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse otherOrgIdUpload(
        WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUpload(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            if (!WEB3StringTypeUtility.isEmpty(l_ex.getErrorMessage()))
            {
                if (!(l_ex instanceof WEB3SystemLayerException || l_ex instanceof WEB3BusinessLayerException))
                {
                    l_response.errorMessage = l_ex.getErrorMessage();
                }
            }

            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止処理を行う。<BR>
     * <BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止サービスを取得し、<BR>
     * execute( )メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止リクエスト<BR>
     * @@return WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse
     * @@roseuid 47B940CA0221
     */
    public WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse otherOrgIdUploadCancel(
        WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME =
            "otherOrgIdUploadComplete(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse l_response = null;
        WEB3AdminSrvRegiOtherOrgIdUploadService l_service = null;

        try
        {
            l_service =
                (WEB3AdminSrvRegiOtherOrgIdUploadService)Services.getService(
                    WEB3AdminSrvRegiOtherOrgIdUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止処理にエラーが発生しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response =
                (WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止処理にエラーが発生しました。",
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
