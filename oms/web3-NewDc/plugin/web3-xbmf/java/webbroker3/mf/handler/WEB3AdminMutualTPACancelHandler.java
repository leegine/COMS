head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者余力調整取消ハンドラクラス(WEB3AdminMutualTPACancelHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 黄建  (中訊) 新規作成
*/

package webbroker3.mf.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * 投信管理者余力調整取消ハンドラクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminMutualTPACancelHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelHandler.class); 
    
    /**
     * (余力調整取消一覧)<BR>
     * 投資信託余力調整取消一覧処理を行う。<BR>
     * <BR>
     * 投信管理者余力調整取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信管理者余力調整取消一覧リクエスト<BR>
     * @@return WEB3AdminMutualTPACancelListResponse
     * @@roseuid 40567E0801DE
     */
    public WEB3AdminMutualTPACancelListResponse tpACancelList(
        WEB3AdminMutualTPACancelListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "tpACancelList(WEB3AdminMutualTPACancelListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //投信管理者余力調整取消サービス
        WEB3AdminMutualTPACancelService l_service = null;          
        
        //投信管理者余力調整取消一覧レスポンス
        WEB3AdminMutualTPACancelListResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3AdminMutualTPACancelService)Services.getService(
                    WEB3AdminMutualTPACancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
             //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualTPACancelListResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者余力調整取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualTPACancelListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualTPACancelListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投資信託余力調整取消一覧処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (余力調整取消完了)<BR>
     * 投資信託余力調整取消完了処理を行う。<BR>
     * <BR>
     * 投信管理者余力調整取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 投信管理者余力調整取消完了リクエスト<BR>
     * 
     * @@return WEB3AdminMutualTPACancelCompleteResponse
     * @@roseuid 40567E0801DE
     */
    public WEB3AdminMutualTPACancelCompleteResponse tpACancelComplete(
        WEB3AdminMutualTPACancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "tpACancelComplete(WEB3AdminMutualTPACancelCompleteRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //投信管理者余力調整取消サービス
        WEB3AdminMutualTPACancelService l_service = null;          
        
        //投資信託管理者余力調整取消完了レスポンスクラス
        WEB3AdminMutualTPACancelCompleteResponse l_response = null;     
        try
        {
            l_service = 
                (WEB3AdminMutualTPACancelService)Services.getService(
                    WEB3AdminMutualTPACancelService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
             //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "投信管理者余力調整取消サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMutualTPACancelCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "投資信託余力調整取消完了処理に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
