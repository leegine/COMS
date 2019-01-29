head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class);   
    
    /**
     * @@roseuid 418F3A0D0138
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * パスワード変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5B51032B
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄの入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * パスワード変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147BBDB02E8
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄのダウンロード画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (パスワード変更顧客ダウンロード)<BR>
     * パスワード変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147BBDB02F8
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse loginPasswordChangeAccountDownload(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " loginPasswordChangeAccountDownload(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄのパスワード変更顧客ダウンロードに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
