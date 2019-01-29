head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.47.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せハンドラ(WEB3AdminAccOpenStateInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/21 郭英 (中訊) 新規作成
Revesion History : 2009/08/13 武波 (中訊) 仕様変更 モデルNo.175
*/

package webbroker3.accountopen.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設状況問合せハンドラ)<BR>
 * 管理者口座開設状況問合せハンドラ<BR>
 */
public class WEB3AdminAccOpenStateInquiryHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance
        (WEB3AdminAccOpenStateInquiryHandler.class);

    /**
     * @@roseuid 41B45E7401B5
     */
    public WEB3AdminAccOpenStateInquiryHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 口座開設状況問合せ入力画面表示処理を行う。 <BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設状況問合せ入力リクエストデータオブジェクト
     * 
     * 
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41943C320215
     */
    public WEB3AdminAccOpenStateInquiryInputResponse inputScreenDisplay(WEB3AdminAccOpenStateInquiryInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminAccOpenStateInquiryInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryInputResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;  
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設状況問合せ入力画面表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (口座開設状況一覧表示)<BR>
     * 口座開設状況一覧表示処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設状況問合せ一覧リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse
     * @@roseuid 41943C320217
     */
    public WEB3AdminAccOpenStateInquiryListResponse accOpenStatusListDisplay(WEB3AdminAccOpenStateInquiryListRequest l_request) 
    {
        final String STR_METHOD_NAME = " accOpenStatusListDisplay(WEB3AdminAccOpenStateInquiryListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryListResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設状況一覧表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (口座開設状況詳細表示)<BR>
     * 口座開設状況詳細表示処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設状況問合せ詳細リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse
     * @@roseuid 41943C320225
     */
    public WEB3AdminAccOpenStateInquiryDetailResponse accOpenStatusDetailDisplay(WEB3AdminAccOpenStateInquiryDetailRequest l_request) 
    {
        final String STR_METHOD_NAME = " accOpenStatusDetailDisplay(WEB3AdminAccOpenStateInquiryDetailRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenStateInquiryDetailResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenStateInquiryDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設状況詳細表示処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (申込更新確認)<BR>
     * 口座開設申込更新確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設申込更新確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse
     * @@roseuid 41943C320227
     */
    public WEB3AdminAccOpenApplyUpdateConfirmResponse registUpdatedConfirm(WEB3AdminAccOpenApplyUpdateConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registUpdatedConfirm(WEB3AdminAccOpenApplyUpdateConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUpdateConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設申込更新確認処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (申込更新完了)<BR>
     * 口座開設申込更新完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設申込更新完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse
     * @@roseuid 41943C320229
     */
    public WEB3AdminAccOpenApplyUpdateCompleteResponse registUpdatedComplete(WEB3AdminAccOpenApplyUpdateCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registUpdatedComplete(WEB3AdminAccOpenApplyUpdateCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenApplyUpdateCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenApplyUpdateCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設申込更新完了処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (伝票作成確認)<BR>
     * 口座開設伝票作成確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設伝票作成確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse
     * @@roseuid 41943C32022B
     */
    public WEB3AdminAccOpenVoucherMakeConfirmResponse voucherCreatedConfirm(WEB3AdminAccOpenVoucherMakeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCreatedConfirm(WEB3AdminAccOpenVoucherMakeConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherMakeConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設伝票作成確認処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (伝票作成完了)<BR>
     * 口座開設伝票作成完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設伝票作成完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse
     * @@roseuid 41943C320235
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse voucherCreatedComplete(WEB3AdminAccOpenVoucherMakeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCreatedComplete(WEB3AdminAccOpenVoucherMakeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherMakeCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherMakeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設伝票作成完了処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (伝票取消確認)<BR>
     * 口座開設伝票取消確認処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設伝票取消確認リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse
     * @@roseuid 41943C320237
     */
    public WEB3AdminAccOpenVoucherCancelConfirmResponse voucherCanceledConfirm(WEB3AdminAccOpenVoucherCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCanceledConfirm(WEB3AdminAccOpenVoucherCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherCancelConfirmResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設伝票取消確認処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }
    
    /**
     * (伝票取消完了)<BR>
     * 口座開設伝票取消完了処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者口座開設伝票取消完了リクエストデータオブジェクト
     * @@return webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse
     * @@roseuid 41943C320239
     */
    public WEB3AdminAccOpenVoucherCancelCompleteResponse voucherCanceledComplete(WEB3AdminAccOpenVoucherCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " voucherCanceledComplete(WEB3AdminAccOpenVoucherCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminAccOpenVoucherCancelCompleteResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;
        
        try
        {
            l_service = (WEB3AdminAccOpenStateInquiryService)Services.getService(WEB3AdminAccOpenStateInquiryService.class);//Exception
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                " 管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminAccOpenVoucherCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, " 口座開設伝票取消完了処理に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response; 
    }

    /**
     * (切替)<BR>
     * 口座開設状況問合せ切替処理を行う。<BR>
     * <BR>
     * 管理者口座開設状況問合せサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (管理者口座開設切替リクエスト)<BR>
     * 管理者口座開設切替リクエストデータオブジェクト<BR>
     * @@return WEB3AccOpenChangeResponse
     */
    public WEB3AccOpenChangeResponse change(WEB3AccOpenChangeRequest l_request) 
    {
        final String STR_METHOD_NAME = "change(WEB3AccOpenChangeRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AccOpenChangeResponse l_response = null;
        WEB3AdminAccOpenStateInquiryService l_service = null;

        try
        {
            l_service =
                (WEB3AdminAccOpenStateInquiryService)Services.getService(
                    WEB3AdminAccOpenStateInquiryService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "管理者口座開設状況問合せサービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //管理者口座開設状況問合せサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3AccOpenChangeResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "口座開設状況問合せ切替処理", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AccOpenChangeResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "口座開設状況問合せ切替処理に失敗しました。",
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
