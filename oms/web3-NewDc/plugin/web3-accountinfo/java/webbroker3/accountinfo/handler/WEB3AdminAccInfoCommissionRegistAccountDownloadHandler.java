head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class);
    
    /**
     * @@roseuid 418F3A0F033C
     */
    public WEB3AdminAccInfoCommissionRegistAccountDownloadHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 手数料変更申込顧客ダウンロード問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5E4701E3
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * 手数料変更申込顧客ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAC830192
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "downloadScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountDownloadResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (手数料変更申込顧客ダウンロード)<BR>
     * 手数料変更申込顧客ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 414FAC830194
     */
    public WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse commissionRegistAccountDownload(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "commissionRegistAccountDownload(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountDownloadService l_service = null;
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountDownloadService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
