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
filename	WEB3FuturesChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建ハンドラクラス(WEB3FuturesOpenMarginChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 李強 (中訊) 新規作成
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株価指数先物訂正新規建ハンドラ)<BR>
 * 株価指数先物訂正新規建ハンドラクラス<BR>
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesChangeOpenContractHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractHandler.class);
        
    /**
     * @@roseuid 40F7B073002E
     */
    public WEB3FuturesChangeOpenContractHandler() 
    {
     
    }
    
    /**
     * (confirm訂正新規建)<BR>
     * 株価指数先物の訂正新規建発注審査を行う。<BR>
     * <BR>
     * 株価指数先物訂正新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物訂正新規建確認リクエスト
     * @@return WEB3FuturesOpenMarginChangeConfirmResponse
     * @@roseuid 40A8A44C001D
     */
    public WEB3FuturesOpenMarginChangeConfirmResponse confirmOpenMarginChange(WEB3FuturesOpenMarginChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "confirmOpenMarginChange(WEB3FuturesOpenMarginChangeConfirmRequest)";

        log.entering(STR_METHOD_NAME);
                
        WEB3FuturesOpenMarginChangeConfirmResponse l_response = null;
        WEB3FuturesChangeOpenContractService l_service = null;
        
        //株価指数先物訂正新規建サービスを取得する
        try
        {
            l_service =
                (WEB3FuturesChangeOpenContractService)Services.getService(
            WEB3FuturesChangeOpenContractService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物訂正新規建に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物訂正新規建に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
    
    /**
     * (complete訂正新規建)<BR>
     * 株価指数先物の訂正新規建注文を登録する。<BR>
     * <BR>
     * 株価指数先物訂正新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数先物訂正新規建完了リクエスト
     * @@return WEB3FuturesOpenMarginChangeCompleteResponse
     * @@roseuid 40A8A44C003C
     */
    public WEB3FuturesOpenMarginChangeCompleteResponse completeOpenMarginChange(WEB3FuturesOpenMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "completeOpenMarginChange(WEB3FuturesOpenMarginChangeCompleteRequest)";

        log.entering(STR_METHOD_NAME);
        
        WEB3FuturesOpenMarginChangeCompleteResponse l_response = null;
        WEB3FuturesChangeOpenContractService l_service = null;
        
        //株価指数先物訂正新規建サービスを取得する
        try
        {
            l_service =
                (WEB3FuturesChangeOpenContractService)Services.getService(
            WEB3FuturesChangeOpenContractService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数先物訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物訂正新規建に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3FuturesOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数先物訂正新規建に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
