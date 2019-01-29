head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoBookbuildingStateDownloadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ(WEB3AdminIpoBookbuildingStateDownloadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 斉麟 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ)
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoBookbuildingStateDownloadHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoBookbuildingStateDownloadHandler.class);
    
    /**
     * @@roseuid 4112EE560189
     */
    public WEB3AdminIpoBookbuildingStateDownloadHandler() 
    {
     
    }
    
    /**
     * (ブックビルディング状況ダウンロード画面表示)<BR>
     * IPOブックビルディング状況取得処理を行う。<BR>
     * <BR>
     * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return WEB3AdminIPOBookBuildingStateDownloadResponse
     * @@roseuid 40E139C50131
     */
    public WEB3AdminIPOBookBuildingStateDownloadResponse bookbuildingStateDownloadScreenIndication(WEB3AdminIPOBookBuildingStateDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingStateDownloadScreenIndication(WEB3AdminIPOBookBuildingStateDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingStateDownloadResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingStateDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingStateDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (申告分布図画面表示)<BR>
     * IPOブックビルディング申告分布図画面表示処理を行う。<BR>
     * <BR>
     * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse
     * @@roseuid 40E139C50150
     */
    public WEB3AdminIPOBookBuildingDemandMapResponse orderDistributionChartScreenIndication(WEB3AdminIPOBookBuildingDemandMapRequest l_request) 
    {
        return null;
    }
    
    /**
     * (ブックビルディング状況ダウンロード)<BR>
     * IPOブックビルディング状況ダウンロードファ@イルデータ取得処理を行う。<BR>
     * <BR>
     * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄデータオブジェクト
     * @@return WEB3AdminIPOBookBuildingStateFileDownloadResponse
     * @@roseuid 40E139C50160
     */
    public WEB3AdminIPOBookBuildingStateFileDownloadResponse bookbuildingStateDownload(WEB3AdminIPOBookBuildingStateFileDownloadRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingStateDownload(WEB3AdminIPOBookBuildingStateFileDownloadRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingStateFileDownloadResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingStateFileDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (ブックビルディング申告履歴表示)<BR>
     * IPOブックビルディング申告履歴一覧取得処理を行う。<BR>
     * <BR>
     * 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse
     * @@roseuid 40E139C50170
     */
    public WEB3AdminIPOBookBuildingHistoryResponse bookbuildingOrderActionIndication(WEB3AdminIPOBookBuildingHistoryRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingOrderActionIndication(WEB3AdminIPOBookBuildingHistoryRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOBookBuildingHistoryResponse l_response = null;
        WEB3AdminIpoBookbuildingStateDownloadService l_service = null;
        
        try
        {
            l_service = (WEB3AdminIpoBookbuildingStateDownloadService)Services.getService(WEB3AdminIpoBookbuildingStateDownloadService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response; 
        }
        
        try
        {
            l_response = (WEB3AdminIPOBookBuildingHistoryResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =  (WEB3AdminIPOBookBuildingHistoryResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            
            log.error(l_request, "管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞサービスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
