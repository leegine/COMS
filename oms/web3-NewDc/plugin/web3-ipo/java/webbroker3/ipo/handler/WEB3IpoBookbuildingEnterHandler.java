head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.48.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBookbuildingEnterHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOブックビルディング参加ハンドラクラス(WEB3IpoBookbuildingEnterHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 鄭海良(中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterResponse;
import webbroker3.ipo.message.WEB3IPOProductInfoRequest;
import webbroker3.ipo.message.WEB3IPOProductInfoResponse;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingEnterService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPOブックビルディング参加ハンドラクラス
 * 
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3IpoBookbuildingEnterHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IpoBookbuildingEnterHandler.class);
            
    /**
     * @@roseuid 4112EE5801FA
     */
    public WEB3IpoBookbuildingEnterHandler() 
    {
     
    }
    
    /**
     * ブックビルディング参加処理<BR>
     * <BR>
     * IPOブックビルディング参加サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPOブックビルディング参加リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoBookbuildingEnterResponse
     * @@roseuid 40D2815E0343
     */
    public WEB3IPOBookBuildingEnterResponse bookbuildingEnter(
        WEB3IPOBookBuildingEnterRequest l_request) 
    {
        final String STR_METHOD_NAME = " bookbuildingEnter(WEB3IpoBookbuildingEnterRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOBookBuildingEnterResponse l_response = null;
        WEB3IpoBookbuildingEnterService l_service = null;
        
        try
        {
            //IPOブックビルディング参加サービスを取得し
            l_service =
                (WEB3IpoBookbuildingEnterService)Services.getService(
                    WEB3IpoBookbuildingEnterService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOBookBuildingEnterResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング参加サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }
        
        try
        {
            //IPOブックビルディング参加サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOBookBuildingEnterResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOBookBuildingEnterResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "ブックビルディング参加処理に失敗しました。", l_we);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * 個別銘柄情報表示処理<BR>
     * <BR>
     * IPOブックビルディング参加サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - IPO個別銘柄情報リクエストデータオブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoProductInfoResponse
     * @@roseuid 40D2815E0381
     */
    public WEB3IPOProductInfoResponse individualProductInfoIndication(
        WEB3IPOProductInfoRequest l_request)
    {
        final String STR_METHOD_NAME = " individualProductInfoIndication(WEB3IpoProductInfoRequest)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOProductInfoResponse l_response = null;
        WEB3IpoBookbuildingEnterService l_service = null;
        
        try
        {
            //IPOブックビルディング参加サービスを取得し
            l_service =
                (WEB3IpoBookbuildingEnterService)Services.getService(
                    WEB3IpoBookbuildingEnterService.class);
        }
        catch(Exception l_e)
        {
            l_response =
                   (WEB3IPOProductInfoResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "IPOブックビルディング参加サービスを取得に失敗しました。",
                l_response.errorInfo, l_e);
        }

            
        try
        {
            //IPOブックビルディング参加サービス.execute()メソッドをコールする
            l_response =
                (WEB3IPOProductInfoResponse)l_service.execute(
                    l_request);//WEB3BaseException
        }
        catch(WEB3BaseException l_we)
        {
            l_response =
                (WEB3IPOProductInfoResponse)l_request.createResponse();
            l_response.errorInfo = l_we.getErrorInfo();
            log.error(l_request, "個別銘柄情報表示処理に失敗しました。", l_we);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
