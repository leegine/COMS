head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引新規建入力ハンドラ(WEB3MarginOpenMarginInputHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 王暁傑 (Sinocom) 新規作成 
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.equity.message.WEB3MarginOpenMarginInputRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginInputResponse;
import webbroker3.equity.message.WEB3MarginProductSelectRequest;
import webbroker3.equity.message.WEB3MarginProductSelectResponse;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;

/**
 * （信用取引新規建入力ハンドラ）。<BR>
 * <BR>
 * 信用取引新規建入力ハンドラクラス
 * @@author 王暁傑
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputHandler implements MessageHandler 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputHandler.class);
           
    /**
     * @@roseuid 414184C50060
     */
    public WEB3MarginOpenMarginInputHandler() 
    {
     
    }
    
    /**
     * (get銘柄選択画面)<BR>
     * 信用取引の新規建銘柄選択画面表示処理を行う。<BR>
     * <BR>
     * 信用取引新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginProductSelectResponse
     * @@roseuid 40F64C01036C
     */
    public WEB3MarginProductSelectResponse getProductSelectScreen(WEB3MarginProductSelectRequest l_request) 
    {
        final String STR_METHOD_NAME = "getProductSelectScreen(WEB3MarginProductSelectRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginProductSelectResponse l_response = null;
        WEB3MarginOpenMarginInputService l_service = null;

        //信用取引の新規建銘柄選択画面表示処理を行う。
        try
        {
            l_service = (WEB3MarginOpenMarginInputService)Services.getService(WEB3MarginOpenMarginInputService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引新規建入力サービス取得に失敗しました。。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //信用取引新規建入力サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3MarginProductSelectResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引銘柄選択に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginProductSelectResponse)l_request.createResponse();

            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引銘柄選択に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get新規建入力画面)<BR>
     * 信用取引の新規建入力表示処理を行う。<BR>
     * <BR>
     * 信用取引新規建入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3MarginOpenMarginInputResponse
     * @@roseuid 407BBC6903B8
     */
    public WEB3MarginOpenMarginInputResponse getOpenMarginInputScreen(WEB3MarginOpenMarginInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getOpenMarginInputScreen(WEB3OptionsOpenMarginInputResponse)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginOpenMarginInputResponse l_response = null;
        WEB3MarginOpenMarginInputService l_service = null;

        //信用取引の新規建入力表示処理を行う
        try
        {
            l_service = (WEB3MarginOpenMarginInputService)Services.getService(WEB3MarginOpenMarginInputService.class);

            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {

            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "信用取引新規建入力サービス取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //信用取引新規建入力サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3MarginOpenMarginInputResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "信用取引新規建入力に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_bre)
        {
            l_response = (WEB3MarginOpenMarginInputResponse)l_request.createResponse();

            l_response.errorInfo = l_bre.getErrorInfo();
            log.error(l_request, "信用取引新規建入力に失敗しました。", l_bre);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
