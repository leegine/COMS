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
filename	WEB3OptionOpenContractOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : OP新規建注文ハンドラ(WEB3OptionOpenContractOrderHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/06/19 呉艶飛 (中訊) 新規作成
                 001: 2004/07/31 王暁傑 (中訊) 対応バッグ WEB3_IFO_UT-000097
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * (OP新規建注文ハンドラ)<BR>
 * 株価指数オプション新規建注文ハンドラクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionOpenContractOrderHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractOrderHandler.class);

    /**
     * @@roseuid 40C0AF3300EA
     */
    public WEB3OptionOpenContractOrderHandler()
    {

    }

    /**
     * (confirm注文)<BR>
     * <BR>
     * 株価指数オプションの新規建発注審査を行う。<BR>
     * <BR>
     * 株価指数オプション新規建注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション新規建注文確認リクエストデータオブジェクト
     * @@return webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmResponse
     * @@roseuid 404EF34403D7
     */
    public WEB3OptionsOpenMarginConfirmResponse confirmOrder(WEB3OptionsOpenMarginConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = ".confirmOrder(WEB3OptionsOpenMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginConfirmResponse l_response = null;
        WEB3OptionOpenContractOrderService l_service = null;

        //株価指数オプションの新規建発注審査を行う。
        try
        {
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション新規建注文サービス取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }
        
        //株価指数オプション新規建注文サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプション新規建注文の発注審査に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプション新規建注文の発注審査に失敗しました。", l_rex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }

    /**
     * (complete注文)<BR>
     * <BR>
     * 株価指数オプションの新規建注文を登録する。<BR>
     * <BR>
     * 株価指数オプション新規建注文サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株価指数オプション新規建注文完了リクエストデータオブジェクト
     * @@return WEB3OptionsOpenMarginCompleteResponse
     * @@roseuid 404EF345000F
     */
    public WEB3OptionsOpenMarginCompleteResponse completeOrder(WEB3OptionsOpenMarginCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3OptionsOpenMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3OptionsOpenMarginCompleteResponse l_response = null;
        WEB3OptionOpenContractOrderService l_service = null;

        //株価指数オプションの新規建注文を登録する
        try
        {
            l_service = (WEB3OptionOpenContractOrderService)Services.getService(WEB3OptionOpenContractOrderService.class);
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション新規建注文サービス取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }
        
        //株価指数オプション新規建注文サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();        
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプション新規建注文の登録に失敗しました。", l_ex);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_rex)
        {
            l_response = (WEB3OptionsOpenMarginCompleteResponse)l_request.createResponse();        
            l_response.errorInfo = l_rex.getErrorInfo();
            l_response.errorMessage = l_rex.getErrorInfo().getErrorDebugInfo();
            log.error(l_request, "株価指数オプション新規建注文の登録に失敗しました。", l_rex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
