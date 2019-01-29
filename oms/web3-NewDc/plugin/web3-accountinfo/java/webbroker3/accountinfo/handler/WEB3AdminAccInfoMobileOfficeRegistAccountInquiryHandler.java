head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せハンドラ(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/12/15 徐大方 (中訊) 仕様変更モデル155
                   2006/12/18 徐大方 (中訊) 仕様変更モデル157
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せハンドラ)
 * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せハンドラ
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class);

    
    /**
     * @@roseuid 418F3A100109
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 携帯番号・勤務先情報変更申込顧客問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを<BR>
     * 取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse
     * @@throws WEB3BaseException
     * @@roseuid 415A5F2E0369
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "inputScreenDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;
        
        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (変更申込顧客一覧表示)<BR>
     * 携帯番号・勤務先情報変更申込顧客一覧表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを<BR>
     * 取得し、execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoMobileOfficeRegistAccountResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegistAccountResponse registAccountListDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountRequest l_request) 
    {
        final String STR_METHOD_NAME = "registAccountListDisplay(WEB3AdminAccInfoMobileOfficeRegistAccountRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoMobileOfficeRegistAccountResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;
        
        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得
        try
        {  
                      
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoMobileOfficeRegistAccountResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }

    /**
     * (一括判定確認画面表示)<BR>
     * 携帯番号・勤務先情報変更申込顧客一括判定確認画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * @@return WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse judgementConfirmScreenDisplay(
        WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest l_request)
    {
        final String STR_METHOD_NAME =
            "judgementConfirmScreenDisplay(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;

        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(
                WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }

        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得し、execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスに失敗しました。",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (一括判定完了)<BR>
     * 携帯番号・勤務先情報変更申込顧客一括判定完了画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを<BR>
     * 取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエスト
     * @@return WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse
     * @@roseuid 414972EA026D
     */
    public WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse judgementComplete(
        WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "judgementComplete(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse l_response = null;
        WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService l_service = null;

        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得
        try
        {
            l_service = (WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService)Services.getService(
                WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }

        // 管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスを取得し、execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービスに失敗しました。",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
