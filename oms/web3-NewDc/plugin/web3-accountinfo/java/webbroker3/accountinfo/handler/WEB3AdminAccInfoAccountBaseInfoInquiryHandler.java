head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.13.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccountBaseInfoInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報顧客基本情報問合せハンドラ(WEB3AdminAccInfoAccountBaseInfoInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoInquiryResponse;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccountBaseInfoInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報顧客基本情報問合せハンドラ)<BR>
 * 管理者お客様情報顧客基本情報問合せハンドラ<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoAccountBaseInfoInquiryHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class);
    /**
     * @@roseuid 418F3A0F0251
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryHandler() 
    {
     
    }
    
    /**
     * (顧客基本情報問合せ)<BR>
     * 顧客基本情報問合せ処理を行う。<BR>
     * <BR>
     * 管理者お客様情報顧客基本情報問合せサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報顧客基本情報問合せリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 4163B2620264
     */
    public WEB3AdminAccInfoAccountBaseInfoResultResponse accountBaseInfoInquiry(
        WEB3AdminAccInfoAccountBaseInfoResultRequest l_request) 
    {
        final String STR_METHOD_NAME = " accountBaseInfoInquiry(WEB3AdminAccInfoAccountBaseInfoResultRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoAccountBaseInfoResultResponse l_response = null;
        WEB3AdminAccInfoAccountBaseInfoInquiryService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoAccountBaseInfoInquiryService)Services.getService(
                    WEB3AdminAccInfoAccountBaseInfoInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報顧客基本情報問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報顧客基本情報問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoResultResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報顧客基本情報問合せサービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (入力画面表示)<BR>
     * 顧客基本情報問合せ入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者お客様情報顧客基本情報問合せサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者お客様情報顧客基本情報問合せリクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoAccountBaseInfoResultResponse
     * @@roseuid 416CBD5502F9
     */
    public WEB3AdminAccInfoAccountBaseInfoInquiryResponse inputScreenDisplay(
        WEB3AdminAccInfoAccountBaseInfoInquiryRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccInfoAccountBaseInfoInquiryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccInfoAccountBaseInfoInquiryResponse l_response = null;
        WEB3AdminAccInfoAccountBaseInfoInquiryService l_service = null;
        
        try
        {
            l_service = 
                (WEB3AdminAccInfoAccountBaseInfoInquiryService)Services.getService(
                    WEB3AdminAccInfoAccountBaseInfoInquiryService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者お客様情報顧客基本情報問合せサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者お客様情報顧客基本情報問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccInfoAccountBaseInfoInquiryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "管理者お客様情報顧客基本情報問合せサービスに失敗しました。", l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
