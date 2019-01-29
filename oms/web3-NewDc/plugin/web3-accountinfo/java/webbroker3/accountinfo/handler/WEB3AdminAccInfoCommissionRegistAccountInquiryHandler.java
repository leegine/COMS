head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.12.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客問合せﾊﾝﾄﾞﾗ)(WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報手数料変更申込顧客問合せﾊﾝﾄﾞﾗ)<BR>
 * 管理者お客様情報手数料変更申込顧客問合せﾊﾝﾄﾞﾗ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionRegistAccountInquiryHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class);
    
    /**
     * @@roseuid 418F3A1201A5
     */
    public WEB3AdminAccInfoCommissionRegistAccountInquiryHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 手数料変更申込顧客問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客問合せ入力ﾘｸｴｽﾄデータオブジェクト
     * 
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 416B61AD03E3
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest l_request) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = 
            "inputScreenDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountInquiryService l_service = null;
        
        //管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountInquiryService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
    
    /**
     * (変更申込一覧表示)<BR>
     * 手数料変更申込顧客問合せ表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - 管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoCommissionChangeAccountInquiryResponse
     * @@roseuid 4151417D001C
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryResponse registListDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest l_request) 
    {
        
        final String STR_METHOD_NAME = 
            "registListDisplay(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoCommissionChangeAccountInquiryResponse l_response = null;
        WEB3AdminAccInfoCommissionRegistAccountInquiryService l_service = null;
        
        //管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得
        try
        {  
                      
            l_service=(WEB3AdminAccInfoCommissionRegistAccountInquiryService)Services.getService
                (WEB3AdminAccInfoCommissionRegistAccountInquiryService.class);            
            
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch(Exception l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response; 
            
        }
        
        // 管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽを取得し、execute()メソッドをコールする 
        try
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_service.execute(l_request);
                    
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3AdminAccInfoCommissionChangeAccountInquiryResponse)
                l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽに失敗しました。",
                l_ex);
            return l_response;                    
            
        }
        log.exiting(STR_METHOD_NAME);
        return l_response; 
        
    }
}
@
