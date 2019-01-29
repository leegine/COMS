head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.04.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧ハンドラ(WEB3HistoryTradeHistoryListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/28 賈元春(中訊) 新規作成
*/
package webbroker3.tradehistory.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引履歴一覧ハンドラ)<BR>
 * 取引履歴一覧ハンドラクラス<BR>
 * 
 * @@author 賈元春(中訊)
 * @@version 1.00
 */
public class WEB3HistoryTradeHistoryListHandler implements MessageHandler 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3HistoryTradeHistoryListHandler.class);
    
    /**
     * @@roseuid 41789C4D00AB
     */
    public WEB3HistoryTradeHistoryListHandler() 
    {
     
    }
    
    /**
     * (get取引履歴一覧画面)<BR>
     * 取引履歴一覧画面表示処理を行う。<BR>
     * <BR>
     * 取引履歴一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引履歴一覧リクエスト<BR>
     * @@return WEB3HistoryTradeHistoryListResponse
     * @@roseuid 413C2D7A03BF
     */
    public WEB3HistoryTradeHistoryListResponse getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".getTradeHistoryListScreen(WEB3HistoryTradeHistoryListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeHistoryListResponse l_response = null;
        WEB3HistoryTradeHistoryListService l_service = null;
             
        try
        {
            l_service= (WEB3HistoryTradeHistoryListService)Services.getService(WEB3HistoryTradeHistoryListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "取引履歴一覧サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3HistoryTradeHistoryListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistoryTradeHistoryListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "取引履歴一覧画面表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

    /**
     * (get取引履歴一覧ファ@イルダウンロード)<BR>
     * 取引履歴一覧ファ@イルダウンロード処理を行う。<BR>
     * <BR>
     * 取引履歴一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 取引履歴一覧ファ@イルダウンロードリクエスト<BR>
     * @@return WEB3HistoryTradeHistoryDownloadResponse
     * @@roseuid 413C2D7A03BF
     */
    public WEB3HistoryTradeHistoryDownloadResponse getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " getTradeHistoryListDownload(WEB3HistoryTradeHistoryDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3HistoryTradeHistoryDownloadResponse l_response = null;
        WEB3HistoryTradeHistoryListService l_service = null;
             
        try
        {
            l_service= (WEB3HistoryTradeHistoryListService)Services.getService(WEB3HistoryTradeHistoryListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "取引履歴一覧サービスImplの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3HistoryTradeHistoryDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3HistoryTradeHistoryDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "取引履歴一覧画面表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        return l_response; 
    }
}
@
