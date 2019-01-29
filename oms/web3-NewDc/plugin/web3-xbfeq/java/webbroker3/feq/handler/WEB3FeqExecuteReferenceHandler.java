head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定照会ハンドラ(WEB3FeqExecuteReferenceHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成       
                 : 2005/08/03 鄭海良(中訊) レビュー       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqExecuteDetailsRequest;
import webbroker3.feq.message.WEB3FeqExecuteDetailsResponse;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceResponse;
import webbroker3.feq.message.WEB3FeqOrderHistoryRequest;
import webbroker3.feq.message.WEB3FeqOrderHistoryResponse;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文約定照会ハンドラ)<BR>
 * 外国株式注文約定照会ハンドラ
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqExecuteReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceHandler.class);
    
    /**
     * @@roseuid 42D0DA190251
     */
    public WEB3FeqExecuteReferenceHandler() 
    {
     
    }
    
    /**
     * (get注文約定照会)<BR>
     * 外国株式注文約定照会処理を行う <BR>
     * <BR>
     * 外国株式注文約定照会サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定照会リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqExecuteReferenceResponse
     * @@roseuid 429EA81801B1
     */
    public WEB3FeqExecuteReferenceResponse getOrderExecuteReference(
            WEB3FeqExecuteReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteReference(WEB3FeqExecuteReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式注文約定照会サービスインターフェイス
        WEB3FeqExecuteReferenceService l_service = null;
         
        //外国株式注文約定照会レスポンス
        WEB3FeqExecuteReferenceResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqExecuteReferenceResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式注文約定照会処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定詳細)<BR>
     * 外国株式注文約定詳細照会処理を行う <BR>
     * <BR>
     * 外国株式注文約定照会サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定詳細リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqExecuteDetailsResponse
     * @@roseuid 429EA90E03D4
     */
    public WEB3FeqExecuteDetailsResponse getOrderExecuteDetails(
            WEB3FeqExecuteDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteDetails(WEB3FeqExecuteDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式注文約定照会サービスインターフェイス
        WEB3FeqExecuteReferenceService l_service = null;
         
        //外国株式注文約定詳細レスポンス
        WEB3FeqExecuteDetailsResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqExecuteDetailsResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式注文約定詳細照会処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get注文約定履歴)<BR>
     * 外国株式注文約定履歴照会処理を行う <BR>
     * <BR>
     * 外国株式注文約定照会サービスを取得し、<BR>
     * execute()メソッドをコールする。 <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 外国株式注文約定履歴リクエストオブジェクト
     * @@return webbroker3.feq.message.WEB3FeqOrderHistoryResponse
     * @@roseuid 429EA8B9020F
     */
    public WEB3FeqOrderHistoryResponse getOrderExecuteAction(
            WEB3FeqOrderHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getOrderExecuteAction(WEB3FeqOrderHistoryRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //外国株式注文約定照会サービスインターフェイス
        WEB3FeqExecuteReferenceService l_service = null;
         
        //外国株式注文約定履歴レスポンス
        WEB3FeqOrderHistoryResponse l_response = null;
         
        try
        {
            l_service = 
                (WEB3FeqExecuteReferenceService) Services.getService(
                    WEB3FeqExecuteReferenceService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式注文約定照会サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3FeqOrderHistoryResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderHistoryResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式注文約定履歴照会処理に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
