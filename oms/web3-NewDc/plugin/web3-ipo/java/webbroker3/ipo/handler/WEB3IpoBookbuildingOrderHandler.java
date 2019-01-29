head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingOrderHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング申告ハンドラクラス(WEB3IpoBookbuildingOrderHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOブックビルディング申告ハンドラクラス
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3IpoBookbuildingOrderHandler implements MessageHandler 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingOrderHandler.class);
    
    /**
     * @@roseuid 4112EE5800F6
     */
    public WEB3IpoBookbuildingOrderHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * IPOブックビルディング申告入力画面表示処理<BR>
     * <BR>
     * IPOブックビルディング申告サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング申告入力リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderInputResponse
     * @@roseuid 40D28A9F019D
     */
    public WEB3IPOBookBuildingDemandInputResponse inputScreenIndication(
        WEB3IPOBookBuildingDemandInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " inputScreenIndication(WEB3IpoBookbuildingOrderInputRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandInputResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;
        
        try
        {
            //IPOブックビルディング申告サービスを取得し
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング申告サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }

        try
        {
            //IPOブックビルディング申告サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingDemandInputResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandInputResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPOブックビルディング申告入力画面表示処理に失敗しました。", l_we);
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * IPOブックビルディング申告確認処理<BR>
     * <BR>
     * IPOブックビルディング申告サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング申告確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderConfirmResponse
     * @@roseuid 40D28A9F0101
     */
    public WEB3IPOBookBuildingDemandConfirmResponse bookbuildingOrderConfirm(
        WEB3IPOBookBuildingDemandConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " bookbuildingOrderConfirm(WEB3IpoBookbuildingOrderConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandConfirmResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;

        try
        {        
            //IPOブックビルディング申告サービスを取得し
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング申告サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }

        try
        {        
            //IPOブックビルディング申告サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingDemandConfirmResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPOブックビルディング申告確認処理に失敗しました。", l_we);
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * IPOブックビルディング申告完了処理<BR>
     * <BR>
     * IPOブックビルディング申告サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング申告完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingOrderCompleteResponse
     * @@roseuid 40D28A9F013F
     */
    public WEB3IPOBookBuildingDemandCompleteResponse bookbuildingOrderComplete(
        WEB3IPOBookBuildingDemandCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " bookbuildingOrderComplete(WEB3IpoBookbuildingOrderCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingDemandCompleteResponse l_response = null;
        WEB3IpoBookbuildingOrderService l_service = null;
        
        try
        {
            //IPOブックビルディング申告サービスを取得し
            l_service =
                (WEB3IpoBookbuildingOrderService)Services.getService(
            WEB3IpoBookbuildingOrderService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング申告サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }

        try
        {
            //IPOブックビルディング申告サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingDemandCompleteResponse)l_service.execute(
                    l_request);
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingDemandCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPOブックビルディング申告完了処理に失敗しました。", l_we);
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
