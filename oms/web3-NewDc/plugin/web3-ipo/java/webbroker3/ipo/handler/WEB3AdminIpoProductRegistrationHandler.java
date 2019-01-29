head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoProductRegistrationHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄登録ハンドラ(WEB3AdminIpoProductRegistrationHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 李頴淵 新規作成
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO銘柄登録ハンドラ)<BR>
 * 管理者IPO銘柄登録ハンドラクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIpoProductRegistrationHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoProductRegistrationHandler.class);
    
    /**
     * @@roseuid 4112EE570299
     */
    public WEB3AdminIpoProductRegistrationHandler() 
    {
     
    }
    
    /**
     * (銘柄登録確認)<BR>
     * 管理者IPO銘柄新規登録確認処理<BR>
     * <BR>
     * 管理者IPO銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO銘柄新規登録確認リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse
     * @@roseuid 40C56C660117
     */
    public WEB3AdminIPOProductRegistrationConfirmResponse productRegistrationConfirm(WEB3AdminIPOProductRegistrationConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " ProductRegistrationConfirm(WEB3AdminIPOProductRegistrationConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationConfirmResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //管理者IPO銘柄登録サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
                WEB3AdminIpoProductRegistrationService.class);
                
            log.debug("管理者IPO銘柄登録サービスを取得");    
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄登録サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_service.execute(
                    l_request);
            log.debug("管理者IPO銘柄登録サービス.execute()メソッドをコールする");        
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationConfirmResponse)l_request.createResponse();
            // 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA START
            l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA END
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (銘柄登録完了)<BR>
     * 管理者IPO銘柄新規登録完了処理<BR>
     * <BR>
     * 管理者IPO銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO銘柄新規登録完了リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse
     * @@roseuid 40C56D0C001D
     */
    public WEB3AdminIPOProductRegistrationCompleteResponse productRegistrationComplete(WEB3AdminIPOProductRegistrationCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " productRegistrationComplete(WEB3AdminIPOProductRegistrationCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationCompleteResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //管理者IPO銘柄登録サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
            WEB3AdminIpoProductRegistrationService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄登録サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationCompleteResponse)l_request.createResponse();
			// 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA START
			l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA END
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (入力画面表示)<BR>
     * 管理者IPO銘柄新規登録入力画面表示処理<BR>
     * <BR>
     * 管理者IPO銘柄登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO銘柄新規登録入力リクエスト
     * @@return webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse
     * @@roseuid 40C56D1603E6
     */
    public WEB3AdminIPOProductRegistrationInputResponse inputScreenIndication(WEB3AdminIPOProductRegistrationInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenIndication(WEB3AdminIPOProductRegistrationInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductRegistrationInputResponse l_response = null;
        WEB3AdminIpoProductRegistrationService l_service = null;
        
        //管理者IPO銘柄登録サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoProductRegistrationService)Services.getService(
                    WEB3AdminIpoProductRegistrationService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄登録サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductRegistrationInputResponse)l_request.createResponse();
			// 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA START
			l_response.errorInfo = l_ex.getErrorInfo();
//			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			// 2004/11/09 U00378 ビジネスエラーをレスポンスにセットするように修正  坂上@@SRA END
            log.error(
                l_request,
                "管理者IPO銘柄登録サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
}
@
