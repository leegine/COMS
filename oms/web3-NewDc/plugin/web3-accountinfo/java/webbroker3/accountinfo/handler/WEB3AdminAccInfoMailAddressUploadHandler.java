head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoMailAddressUploadHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/14 呉艶飛 (中訊) 新規作成
 */

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCancelResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * 
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoMailAddressUploadHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadHandler.class);

    public WEB3AdminAccInfoMailAddressUploadHandler()
    {

    }

    /**
     * (アップロード画面表示)<BR>
     * メールアドレスアップロード画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード入力リクエスト<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadInputResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadInputResponse uploadScreenDisplay(
            WEB3AdminAccInfoMailAddressUploadInputRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadScreenDisplay(WEB3AdminAccInfoMailAddressUploadInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressUploadInputResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ画面表示処理の実施に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;        
        
    }

    /**
     * (アップロードファ@イル確認)<BR>
     * メールアドレスアップロード確認処理を行う。<BR>
     * <BR>
     * 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード確認リクエスト<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadConfirmResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmResponse uploadFileConfirm(
        WEB3AdminAccInfoMailAddressUploadConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadFileConfirm(WEB3AdminAccInfoMailAddressUploadConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadConfirmResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ確認処理の実施に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }

    /**
     * (メールアドレスアップロード)<BR>
     * メールアドレスアップロード完了処理を行う。<BR>
     * <BR>
     * 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする<BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード完了リクエスト<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadCompleteResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadCompleteResponse mailAddressUpload(
        WEB3AdminAccInfoMailAddressUploadCompleteRequest l_request)
    {
        final String STR_METHOD_NAME =
            "mailAddressUpload(WEB3AdminAccInfoMailAddressUploadCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadCompleteResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;
        
        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ完了処理の実施に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
        
    }

    /**
     * (アップロード中止)<BR>
     * メールアドレスアップロード中止処理を行う。<BR>
     * <BR>
     * 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレスアップロード中止リクエスト<BR>
     * <BR>
     * @@return WEB3AdminAccInfoMailAddressUploadCancelResponse<BR>
     */
    public WEB3AdminAccInfoMailAddressUploadCancelResponse uploadCancel(
        WEB3AdminAccInfoMailAddressUploadCancelRequest l_request)
    {
        final String STR_METHOD_NAME = 
            "uploadCancel(WEB3AdminAccInfoMailAddressUploadCancelRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMailAddressUploadCancelResponse l_response = null;
        WEB3AdminAccInfoMailAddressUploadService l_service = null;

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressUploadService) Services.getService(
                WEB3AdminAccInfoMailAddressUploadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request,
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。",
                l_response.errorInfo, l_ex);
            return l_response;
        }

        // 管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする
        try
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽを取得に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = 
                (WEB3AdminAccInfoMailAddressUploadCancelResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, 
                "管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ処理の実施に失敗しました。",
                l_response.errorInfo,l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
            
    }
}
@
