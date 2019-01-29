head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建ハンドラクラス(WEB3MarginOpenMarginHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/27 王暁傑 (Sinocom) 新規作成
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginOpenMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginCompleteResponse;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginConfirmResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * （信用取引新規建ハンドラ）。<BR>
 * <BR>
 * 信用取引新規建ハンドラクラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginHandler implements MessageHandler 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3MarginOpenMarginHandler.class);
            
    /**
     * @@roseuid 414184C4038A
     */
    public WEB3MarginOpenMarginHandler() 
    {
     
    }
    
    /**
     * (confirm注文)<BR>
     * 信用取引新規建発注審査を行う。<BR>
     * <BR>
     * 信用取引新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引新規建注文確認リクエストデータオブジェクト
     * 
     * @@return WEB3MarginOpenMarginConfirmResponse
     * @@roseuid 40554D9A01CE
     */
    public WEB3MarginOpenMarginConfirmResponse confirmOrder(WEB3MarginOpenMarginConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmOrder(WEB3MarginOpenMarginConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginConfirmResponse l_response = null;
        WEB3MarginOpenMarginService l_service = null;

        //信用取引新規建発注審査を行う。
        try
        {
            l_service = (WEB3MarginOpenMarginService)Services.getService(WEB3MarginOpenMarginService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引新規建サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //信用取引新規建サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3MarginOpenMarginConfirmResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引新規建発注審査に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOpenMarginConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引新規建発注審査に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (complete注文)<BR>
     * 信用取引新規建注文登録を行う。<BR>
     * <BR>
     * 信用取引新規建サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引新規建注文完了リクエストデータオブジェクト
     * @@return WEB3MarginOpenMarginCompleteResponse
     * @@roseuid 40554DAE02D8
     */
    public WEB3MarginOpenMarginCompleteResponse completeOrder(WEB3MarginOpenMarginCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeOrder(WEB3MarginOpenMarginCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginCompleteResponse l_response = null;
        WEB3MarginOpenMarginService l_service = null;

        //信用取引新規建注文登録を行う。
        try
        {
            l_service = (WEB3MarginOpenMarginService)Services.getService(WEB3MarginOpenMarginService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引新規建注文サービスの取得に失敗しました。", l_response.errorInfo, l_ex);

            return l_response;
        }

        //信用取引新規建サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3MarginOpenMarginCompleteResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            l_response.errorMessage = l_ex.getErrorMessage();
            log.error(l_request, "信用取引新規建注文に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response =
                (WEB3MarginOpenMarginCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引新規建注文に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
