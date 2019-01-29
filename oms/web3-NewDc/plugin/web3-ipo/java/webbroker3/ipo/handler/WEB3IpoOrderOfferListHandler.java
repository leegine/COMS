head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderOfferListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :IPO申告・購入申込一覧ハンドラクラス(WEB3IpoOrderOfferListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 劉江涛(中訊) 新規作成
*/
package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO申告・購入申込一覧ハンドラクラス
 * @@author 劉江涛(中訊)
 * @@version 1.0
 */
public class WEB3IpoOrderOfferListHandler implements MessageHandler 
{
    
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoOrderOfferListHandler.class);
    
    /**
     * @@roseuid 4112EEA800C9
     */
    public WEB3IpoOrderOfferListHandler() 
    {
     
    }
    
    /**
     * (申告購入申込一覧表示)<BR>
     * IPO申告購入申込一覧表示処理<BR>
     * <BR>
     * IPO申告購入申込一覧サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * IPO申告購入申込リクエストデータオブジェクト
     * @@return WEB3IpoOrderOfferResponse
     * @@roseuid 40DB93470395
     */
    public WEB3IPODemandOfferResponse orderOfferList(WEB3IPODemandOfferRequest l_request) 
    {
        final String STR_METHOD_NAME =
            " orderOfferList(WEB3IpoOrderOfferRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPODemandOfferResponse l_response = null;
        WEB3IpoOrderOfferListService l_service = null;
        
        //IPO申告・購入申込一覧サービスを取得する
        try
        {
            l_service =
                (WEB3IpoOrderOfferListService)Services.getService(
            WEB3IpoOrderOfferListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO申告・購入申込一覧サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO申告・購入申込一覧サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPODemandOfferResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO申告・購入申込一覧に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (ブックビルディング申告履歴表示)<BR>
     * IPOブックビルディング申告履歴表示処理<BR>
     * <BR>
     * IPO申告購入申込一覧サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - (リクエストデータ)<BR>
     * IPOブックビルディング申告履歴リクエストデータオブジェクト
     * @@return WEB3IpoBookbuildingOrderActionResponse
     * @@roseuid 40DB934703A5
     */
    public WEB3IPOBookBuildingHistoryResponse bookbuildingAction(WEB3IPOBookBuildingHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 "bookbuildingAction(WEB3IpoBookbuildingOrderActionRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingHistoryResponse l_response = null;
        WEB3IpoOrderOfferListService l_service = null;
        
        //IPO申告・購入申込一覧サービスを取得する
        try
        {
            l_service =
                (WEB3IpoOrderOfferListService)Services.getService(
            WEB3IpoOrderOfferListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPO申告・購入申込一覧サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //IPO申告・購入申込一覧サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3IPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "IPO申告・購入申込一覧に失敗しました。", l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;

    }
}
@
