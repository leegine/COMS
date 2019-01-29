head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング取消ハンドラ(WEB3IpoBookbuildingCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOブックビルディング取消ハンドラ
 *                                                                
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IpoBookbuildingCancelHandler implements MessageHandler 
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingCancelHandler.class);
        
    /**
     * @@roseuid 4112EE5802CD
     */
    public WEB3IpoBookbuildingCancelHandler() 
    {
     
    }
    
    /**
     * IPOブックビルディング取消確認処理<BR>
     * <BR>
     * IPOブックビルディング取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング取消確認リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelConfirmResponse
     * @@roseuid 40D935570362
     */
    public WEB3IPOBookBuildingCancelConfirmResponse bookbuildingCancelConfirm(
        WEB3IPOBookBuildingCancelConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " bookbuildingCancelConfirm(WEB3IPOBookBuildingCancelConfirmRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingCancelConfirmResponse l_response = null;
        WEB3IpoBookbuildingCancelService l_service = null;

        try
        {        
            //IPOブックビルディング取消サービスを取得し
            l_service =
                (WEB3IpoBookbuildingCancelService)Services.getService(
            WEB3IpoBookbuildingCancelService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング取消サービスを取得に失敗しました。",
                l_response.errorInfo,l_e);
        }

            
        try
        {        
            //IPOブックビルディング取消サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingCancelConfirmResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPOブックビルディング取消確認処理に失敗しました。", l_we);
        }
    
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * IPOブックビルディング取消完了処理<BR>
     * <BR>
     * IPOブックビルディング取消サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング取消完了リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingCancelCompleteResponse
     * @@roseuid 40D935570372
     */
    public WEB3IPOBookBuildingCancelCompleteResponse bookbuildingCancelComplete(
        WEB3IPOBookBuildingCancelCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " bookbuildingCancelComplete(WEB3IPOBookBuildingCancelCompleteRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingCancelCompleteResponse l_response = null;
        WEB3IpoBookbuildingCancelService l_service = null;
        try
        {   
            //IPOブックビルディング取消サービスを取得し
            l_service =
                (WEB3IpoBookbuildingCancelService)Services.getService(
            WEB3IpoBookbuildingCancelService.class);

        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング取消サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }
            
        try
        {   
            //IPOブックビルディング取消サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingCancelCompleteResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "IPOブックビルディング取消完了処理に失敗しました。", l_we);
        }
        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
