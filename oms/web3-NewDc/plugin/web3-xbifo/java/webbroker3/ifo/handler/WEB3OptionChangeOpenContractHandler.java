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
filename	WEB3OptionChangeOpenContractHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP訂正新規建ハンドラ(WEB3OptionChangeOpenContractHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 李頴淵 (中訊) 新規作成
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;


/**
 * (OP訂正新規建ハンドラ)<BR>
 * 株価指数オプション訂正新規建ハンドラクラス<BR>
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeOpenContractHandler.class);
    
    /**
     * @@roseuid 40C0755E0177
     */
    public WEB3OptionChangeOpenContractHandler() 
    {
     
    }
    
    /**
     * (confirm訂正新規建)<BR>
     * <BR>
     * 株価指数オプションの訂正新規建発注審査を行う。<BR>
     * <BR>
     * 株価指数オプション訂正新規建サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * <BR>
     * 株価指数オプション訂正新規建確認リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmResponse
     * @@roseuid 4056BFB70145
     */
    public WEB3OptionsOpenMarginChangeConfirmResponse confirmChangeOpenContract(WEB3OptionsOpenMarginChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".confirmChangeOpenContract(WEB3OptionsOpenMarginChangeConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeConfirmResponse l_response = null;
        WEB3OptionChangeOpenContractService l_service = null;
        
        //株価指数オプション訂正新規建サービスを取得する
        try
        {
            l_service =
                (WEB3OptionChangeOpenContractService)Services.getService(
            WEB3OptionChangeOpenContractService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正新規建に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正新規建に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
                
    }
    
    /**
     * 株価指数オプションの訂正新規建注文を登録する。<BR>
     * <BR>
     * 株価指数オプション訂正新規建サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション訂正新規建完了リクエスト
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteResponse
     * @@roseuid 4056BFCE02BC
     */
    public WEB3OptionsOpenMarginChangeCompleteResponse completeChangeOpenContract(WEB3OptionsOpenMarginChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".completeChangeOpenContract(WEB3OptionsOpenMarginChangeCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsOpenMarginChangeCompleteResponse l_response = null;
        WEB3OptionChangeOpenContractService l_service = null;
        
        //株価指数オプション訂正新規建サービスを取得する
        try
        {
            l_service =
                (WEB3OptionChangeOpenContractService)Services.getService(
            WEB3OptionChangeOpenContractService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション訂正新規建サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正新規建に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response =
                (WEB3OptionsOpenMarginChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            log.error(l_request, "株価指数オプション訂正新規建に失敗しました。", l_rex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
}
@
