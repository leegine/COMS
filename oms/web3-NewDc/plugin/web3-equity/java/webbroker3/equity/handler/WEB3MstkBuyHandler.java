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
filename	WEB3MstkBuyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資買付注文ハンドラ(WEB3MstkBuyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 劉江涛 (中訊) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkBuyCompleteRequest;
import webbroker3.equity.message.WEB3MstkBuyCompleteResponse;
import webbroker3.equity.message.WEB3MstkBuyConfirmRequest;
import webbroker3.equity.message.WEB3MstkBuyConfirmResponse;
import webbroker3.equity.message.WEB3MstkBuyInputRequest;
import webbroker3.equity.message.WEB3MstkBuyInputResponse;
import webbroker3.equity.service.delegate.WEB3MstkBuyService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資買付注文ハンドラ）。<BR>
 * <BR>
 * 株式ミニ投資買付注文ハンドラクラス
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3MstkBuyHandler implements MessageHandler 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyHandler.class);

    /**
     * 
     */
    public WEB3MstkBuyHandler() 
    {
     
    }
    
    /**
     * （get買付入力画面）。<BR>
     * <BR>
     * ミニ株買付注文入力画面表示を行う。 <BR>
     * <BR>
     * 株式ミニ投資買付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資買付注文入力リクエストデータオブジェクト
     * @@return WEB3MstkBuyInputResponse
     */
    public WEB3MstkBuyInputResponse handleGetBuyInputScreen(WEB3MstkBuyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleGetBuyInputScreen(WEB3MstkBuyInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyInputResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //株式ミニ投資買付注文サービスを取得
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資買付注文サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資買付注文サービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3MstkBuyInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request,
                "株式ミニ投資買付注文サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyInputResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request,
				"株式ミニ投資買付注文サービスに失敗しました。",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
     
    }
    
    /**
     * （confirm注文）。<BR>
     * <BR>
     * ミニ株買付注文発注審査を行う。 <BR>
     * <BR>
     * 株式ミニ投資買付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資買付注文確認リクエストデータオブジェクト
     * @@return WEB3MstkBuyConfirmResponse
     */
    public WEB3MstkBuyConfirmResponse handleConfirmOrder(WEB3MstkBuyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleConfirmOrder(WEB3MstkBuyConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyConfirmResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //株式ミニ投資買付注文サービスを取得
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資買付注文サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資買付注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3MstkBuyConfirmResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式ミニ投資買付注文サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
        catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"株式ミニ投資買付注文サービスに失敗しました。",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * （complete注文）。<BR>
     * <BR>
     * ミニ株買付注文登録を行う。 <BR>
     * <BR>
     * 株式ミニ投資買付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資買付注文完了リクエストデータオブジェクト
     * @@return WEB3MstkBuyCompleteResponse
     */
    public WEB3MstkBuyCompleteResponse handleCompleteOrder(WEB3MstkBuyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " handleCompleteOrder(WEB3MstkBuyCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBuyCompleteResponse l_response = null;
        WEB3MstkBuyService l_service = null;
        
        //株式ミニ投資買付注文サービスを取得
        try
        {
            l_service = (WEB3MstkBuyService)Services.getService(WEB3MstkBuyService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3MstkBuyCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資買付注文サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資買付注文サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3MstkBuyCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkBuyCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式ミニ投資買付注文サービスに失敗しました。",
                l_ex);
            return l_response; 

        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkBuyCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(
				l_request,
				"株式ミニ投資買付注文サービスに失敗しました。",
				l_ex);
			return l_response; 

		}
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
