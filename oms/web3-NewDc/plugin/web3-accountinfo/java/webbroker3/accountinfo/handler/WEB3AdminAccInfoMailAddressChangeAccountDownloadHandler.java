head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class);
    /**
     * @@roseuid 418F3A0E005D
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * メールアドレス変更顧客ダウンロード問合せ画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5778000E
     */
    public WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * メールアドレス変更顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F3F300A7
     */
    public WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " downloadScreenDisplay(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (メールアドレス変更顧客ダウンロード)<BR>
     * メールアドレス変更顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報メールアドレス変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4147F3F300A9
     */
    public WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse mailAddressChangeAccountDownload(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " mailAddressChangeAccountDownload(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoMailAddressChangeAccountDownloadService l_service = null;
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMailAddressChangeAccountDownloadService)Services.getService(WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
