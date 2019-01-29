head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeClosingContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正返済ハンドラクラス(WEB3FuturesChangeClosingContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 李媛 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;

import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物訂正返済ハンドラ)<BR>
 * 株価指数先物訂正返済ハンドラクラス<BR>
 * @@author 李媛
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeClosingContractHandler.class);
            
    /**
     * @@roseuid 40F7B07301D4
     */
    public WEB3FuturesChangeClosingContractHandler() 
    {
     
    }
    
    /**
     * (confirm訂正返済)<BR>
     * 株価指数先物の訂正返済発注審査を行う。<BR>
     * <BR>
     * 株価指数先物訂正返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物訂正返済確認リクエスト
     * @@return WEB3FuturesCloseMarginChangeConfirmResponse
     * @@roseuid 40A8B03003A4
     */
    public WEB3FuturesCloseMarginChangeConfirmResponse confirmChangeClosingContract(WEB3FuturesCloseMarginChangeConfirmRequest l_request) 
    {

        final String STR_METHOD_NAME =
            "confirmChangeClosingContract(WEB3FuturesCloseMarginChangeConfirmRequest l_request)";
                        
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesCloseMarginChangeConfirmResponse l_response = null;
        WEB3FuturesChangeClosingContractService l_service = null;

        //株価指数先物訂正新規建サービスを取得する
        try
        {
            
            l_service =
                (WEB3FuturesChangeClosingContractService)Services.getService(
                WEB3FuturesChangeClosingContractService.class);
                
        }
        
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "株価指数先物の訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
                
            return l_response;
            
        }
        
        //株価指数先物訂正新規建サービス.execute()メソッドをコールする
        try
        {
            
            l_response =
                (WEB3FuturesCloseMarginChangeConfirmResponse)l_service.execute(l_request);
                
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物の訂正返済サービス.execute()を失敗しました。", l_ex);
            return l_response;
            
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物の訂正返済サービス.execute()を失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
    
    /**
     * (complete訂正返済)<BR>
     * 株価指数先物の訂正返済注文を登録する。<BR>
     * <BR>
     * 株価指数先物訂正返済サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物訂正返済完了リクエスト
     * @@return WEB3FuturesCloseMarginChangeCompleteResponse
     * @@roseuid 40A8B03003B3
     */
    public WEB3FuturesCloseMarginChangeCompleteResponse completeChangeClosingContract(WEB3FuturesCloseMarginChangeCompleteRequest l_request) 
    {
        
        final String STR_METHOD_NAME =
            "completeChangeClosingContract(WEB3FuturesCloseMarginChangeCompleteRequest l_request)";
             
                
        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesCloseMarginChangeCompleteResponse l_response = null;
        WEB3FuturesChangeClosingContractService l_service = null;
        
        //株価指数先物訂正新規建サービスを取得する
        try
        {
            
            l_service =
                (WEB3FuturesChangeClosingContractService)Services.getService(
                WEB3FuturesChangeClosingContractService.class);
                
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "株価指数先物の訂正返済サービスの取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
                
            return l_response;
            
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            
            l_response =
                (WEB3FuturesCloseMarginChangeCompleteResponse)l_service.execute(l_request);
                
        }
        catch (WEB3BaseException l_ex)
        {
            
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物の訂正返済サービス.execute()を失敗しました。", l_ex);
            return l_response;
            
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物の訂正返済サービス.execute()を失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
