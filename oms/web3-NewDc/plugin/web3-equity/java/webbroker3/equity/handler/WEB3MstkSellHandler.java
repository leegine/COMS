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
filename	WEB3MstkSellHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付ハンドラクラス(WEB3MstkSellHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 李海波 (中訊) 新規作成
                   2004/12/29 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.message.WEB3MstkSellCompleteResponse;
import webbroker3.equity.message.WEB3MstkSellConfirmRequest;
import webbroker3.equity.message.WEB3MstkSellConfirmResponse;
import webbroker3.equity.message.WEB3MstkSellInputRequest;
import webbroker3.equity.message.WEB3MstkSellInputResponse;
import webbroker3.equity.message.WEB3MstkSellListRequest;
import webbroker3.equity.message.WEB3MstkSellListResponse;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資売付注文ハンドラ）。
 * <BR>
 * 株式ミニ投資売付ハンドラクラス
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellHandler implements MessageHandler 
{
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkSellHandler.class);
    
    /**
     * 
     */
    public WEB3MstkSellHandler() 
    {
     
    }
    
    /**
     * （get売付一覧）。<BR>
     * <BR>
     * 株式ミニ投資売付一覧画面表示処理を実施する。 <BR>
     * <BR>
     * 株式ミニ投資売付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資売付一覧リクエストデータオブジェクト
     * @@return WEB3MstkSellListResponse
     */
    public WEB3MstkSellListResponse handleGetSellList(WEB3MstkSellListRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleGetSellList(WEB3MstkSellListRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellListResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資売付注文サービスサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資売付注文サービスサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3MstkSellListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellListResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
    
    /**
     * （get売付入力画面）。<BR>
     * <BR>
     * ミニ株売付注文入力画面表示を行う。<BR>
     * <BR>
     * 株式ミニ投資売付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資売付注文入力リクエストデータオブジェクト
     * @@return WEB3MstkSellInputResponse
     */
    public WEB3MstkSellInputResponse handleGetSellInputScreen(WEB3MstkSellInputRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleGetSellInputScreen(WEB3MstkSellInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellInputResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資売付注文サービスサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資売付注文サービスサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3MstkSellInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellInputResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
    
    /**
     * （confirm注文）。<BR>
     * <BR>
     * ミニ株売付注文発注審査を行う。 <BR>
     * <BR>
     * 株式ミニ投資売付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資売付注文確認リクエストデータオブジェクト
     * @@return WEB3MstkSellConfirmResponse
     */
    public WEB3MstkSellConfirmResponse handleConfirmOrder(WEB3MstkSellConfirmRequest l_request) 
    {
        
        final String STR_METHOD_NAME = " handleConfirmOrder(WEB3MstkSellConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellConfirmResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資売付注文サービスサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資売付注文サービスサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3MstkSellConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellConfirmResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;
        
    }
    
    /**
     * （complete注文）。<BR>
     * <BR>
     * ミニ株売付注文登録を行う。 <BR>
     * <BR>
     * 株式ミニ投資売付注文サービスを取得し、execute()メソッドをコールする。
     * @@param l_request (リクエストデータ)<BR>
     * 　@　@　@株式ミニ投資売付注文完了リクエストデータオブジェクト
     * @@return WEB3MstkSellCompleteResponse
     */
    public WEB3MstkSellCompleteResponse handleCompleteOrder(WEB3MstkSellCompleteRequest l_request) 
    {

        final String STR_METHOD_NAME = " handleCompleteOrder(WEB3MstkSellCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellCompleteResponse l_response = null;
        WEB3MstkSellService l_service = null;
        
        try
        {
            l_service = (WEB3MstkSellService)Services.getService(WEB3MstkSellService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株式ミニ投資売付注文サービスサービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //株式ミニ投資売付注文サービスサービス.execute()メソッドをコールする
        try
        {
            l_response = (WEB3MstkSellCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
            return l_response;
        }
		catch (WEB3BaseRuntimeException l_ex)
		{
			l_response = (WEB3MstkSellCompleteResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, "株式ミニ投資売付注文サービスに失敗しました。", l_ex);
			return l_response;
		}
        
        log.exiting(STR_METHOD_NAME);
        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
