head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class);
    
    /**
     * @@roseuid 418F3A0C0213
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * ハイパーボックス手数料変更ダウンロード入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 41665BD10049
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse inputScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " inputScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (ダウンロード画面表示)<BR>
     * ハイパーボックス手数料変更ダウンロード画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A6AE0050
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse downloadScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " downloadScreenDisplay(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (ハイパーボックス手数料変更ダウンロード)<BR>
     * ハイパーボックス手数料変更ダウンロードファ@イルデータ取得処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse
     * @@throws WEB3BaseException
     * @@roseuid 4146A6AE0070
     */
    public WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse hyperBoxCommissionChangeDownload(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            " hyperBoxCommissionChangeDownload(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse l_response = null;
        WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService l_service = null;
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService)Services.getService
                (WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
