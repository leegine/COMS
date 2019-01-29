head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOfferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  IPO購入申込ハンドラ(WEB3IpoOfferHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 杜珉 新規作成
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmResponse;
import webbroker3.ipo.message.WEB3IPOOfferInputRequest;
import webbroker3.ipo.message.WEB3IPOOfferInputResponse;
import webbroker3.ipo.message.WEB3IPOOfferCompleteResponse;
import webbroker3.ipo.service.delegate.WEB3IpoOfferService;
import webbroker3.util.WEB3LogUtility;

/**
 * ( IPO購入申込ハンドラ)<BR>
 * 
 * @@author 杜珉
 * @@version 1.0
 */
public class WEB3IpoOfferHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOfferHandler.class);
        
            
    /**
     * @@roseuid 4112EEA702C6
     */
    public WEB3IpoOfferHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * IPO購入申込入力画面表示処理<BR>
     * <BR>
     * IPO購入申込サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferInputResponse
     * @@roseuid 40DB91EA03B4
     */
    public WEB3IPOOfferInputResponse offerInput(WEB3IPOOfferInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
                " offerInput(WEB3IPOOfferInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferInputResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPO購入申込サービスを取得する
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO購入申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO購入申込サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO購入申込に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
}
    
    /**
     * (購入申込確認)<BR>
     * IPO購入申込確認処理<BR>
     * <BR>
     * IPO購入申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferConfirmResponse
     * @@roseuid 40DB91EA03C4
     */
    public WEB3IPOOfferConfirmResponse offerConfirm(WEB3IPOOfferConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 " offerConfirm(WEB3IPOOfferConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferConfirmResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPO購入申込サービスを取得する
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO購入申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO購入申込サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO購入申込に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (購入申込完了)<BR>
     * IPO購入申込完了処理<BR>
     * <BR>
     * IPO購入申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * IPO購入申込完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOfferCompleteResponse
     * @@roseuid 40DB91EA03D4
     */
    public WEB3IPOOfferCompleteResponse offerComplete(WEB3IPOOfferCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                " offerComplete(WEB3IPOOfferCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOOfferCompleteResponse l_response = null;
        WEB3IpoOfferService l_service = null;
        
        //IPO購入申込サービスを取得する
        try
        {
            l_service =
                (WEB3IpoOfferService)Services.getService(
            WEB3IpoOfferService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO購入申込サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO購入申込サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOOfferCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO購入申込に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
