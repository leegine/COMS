head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultOfferDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminIpoLotResultOfferDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/23 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultOfferDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗクラス
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultOfferDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotResultOfferDownloadHandler.class);
    
    /**
     * @@roseuid 4112EE5602D4
     */
    public WEB3AdminIpoLotResultOfferDownloadHandler() 
    {
     
    }
    
    /**
     * (抽選結果購入申込状況ダウンロード画面表示)<BR>
     * IPO管理者IPO抽選結果購入申込状況画面表示処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return WEB3AdminIPOLotResultOfferDownloadResponse
     * @@roseuid 40E142890037
     */
    public WEB3AdminIPOLotResultOfferDownloadResponse lotResultOfferStateDownloadScreenIndication(WEB3AdminIPOLotResultOfferDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " lotResultOfferStateDownloadScreenIndication(WEB3AdminIPOLotResultOfferDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultOfferDownloadResponse l_response = null;
        WEB3AdminIpoLotResultOfferDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoLotResultOfferDownloadService)Services.getService(WEB3AdminIpoLotResultOfferDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotResultOfferDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOLotResultOfferDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOLotResultOfferDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (抽選結果購入申込状況ダウンロード)<BR>
     * IPO管理者・抽選結果購入申込状況ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse
     * @@roseuid 40E142890056
     */
    public WEB3AdminIPOLotResultOfferFileDownloadResponse lotResultOfferStateDownload(WEB3AdminIPOLotResultOfferFileDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " lotResultOfferStateDownload(WEB3AdminIPOLotResultOfferFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultOfferFileDownloadResponse l_response = null;
        WEB3AdminIpoLotResultOfferDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoLotResultOfferDownloadService)Services.getService(WEB3AdminIpoLotResultOfferDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotResultOfferFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOLotResultOfferFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOLotResultOfferFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (抽選結果購入申込状況表示)<BR>
     * 抽選結果購入申込状況表示処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse
     * @@roseuid 40E142890066
     */
    public WEB3AdminIPOLotResultOfferResponse lotResultOfferStateIndication(WEB3AdminIPOLotResultOfferRequest l_request) 
    {
        final String STR_METHOD_NAME = " lotResultOfferStateIndication(WEB3AdminIPOLotResultOfferRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultOfferResponse l_response = null;
        WEB3AdminIpoLotResultOfferDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoLotResultOfferDownloadService)Services.getService(WEB3AdminIpoLotResultOfferDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotResultOfferResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOLotResultOfferResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOLotResultOfferResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (抽選結果購入申込状況一覧表示)<BR>
     * 抽選結果購入申込状況一覧表示処理を行う。<BR>
     * <BR>
     * 管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse
     * @@roseuid 40EE06280233
     */
    public WEB3AdminIPOLotResultOfferListResponse lotResultOfferStateListIndication(WEB3AdminIPOLotResultOfferListRequest l_request) 
    {
        final String STR_METHOD_NAME = " lotResultOfferStateListIndication(WEB3AdminIPOLotResultOfferListRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotResultOfferListResponse l_response = null;
        WEB3AdminIpoLotResultOfferDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoLotResultOfferDownloadService)Services.getService(WEB3AdminIpoLotResultOfferDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotResultOfferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOLotResultOfferListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOLotResultOfferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
