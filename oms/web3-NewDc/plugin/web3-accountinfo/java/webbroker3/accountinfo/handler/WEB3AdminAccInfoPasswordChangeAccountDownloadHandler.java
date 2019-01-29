head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoPasswordChangeAccountDownloadHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class);   
    
    /**
     * @@roseuid 418F3A11001F
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 暗証番号変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201C1
     */
    public WEB3AdminAccInfoPasswordChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞの入力画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * 暗証番号変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201E0
     */
    public WEB3AdminAccInfoPasswordChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞのダウンロード画面表示に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
    
    /**
     * (暗証番号変更顧客ダウンロード)<BR>
     * 暗証番号変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B8C6201EF
     */
    public WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse passwordChangeAccountDownload(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " passwordChangeAccountDownload(WEB3AdminAccInfoPasswordResetInputRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoPasswordChangeAccountDownloadService l_service = null;

        try
        {
            l_service = (WEB3AdminAccInfoPasswordChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoPasswordChangeAccountDownloadService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                " 管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞの暗証番号変更顧客ダウンロードに失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }
}
@
