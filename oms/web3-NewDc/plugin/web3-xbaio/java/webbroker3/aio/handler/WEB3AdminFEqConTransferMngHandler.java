head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.25.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferMngHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替管理ハンドラ(WEB3AdminFEqConTransferMngHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/22 周勇 (中訊) 新規作成
*/
package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminFEqConTransferListRequest;
import webbroker3.aio.message.WEB3AdminFEqConTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminFEqConTransferMngService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株振替管理ハンドラ)<BR>
 * 外株振替管理ハンドラクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferMngHandler implements MessageHandler 
{
    
    /**
     * @@roseuid 4235651700CB
     */
    public WEB3AdminFEqConTransferMngHandler() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferMngHandler.class);
    
    /**
     * (入力画面表示)<BR>
     * 入力画面表示を行う。<BR>
     * <BR>
     * 外株振替管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConTransferListInputResponse
     * @@roseuid 41E3991703E3
     */
    public WEB3AdminFEqConTransferListInputResponse displayInputScreen(
        WEB3AdminFEqConTransferListInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayInputScreen(" +
            "WEB3AdminFEqConTransferListInputRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //外株振替管理サービス
        WEB3AdminFEqConTransferMngService l_service = null;          
         
        //外株振替一覧条件入力レスポンス
        WEB3AdminFEqConTransferListInputResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AdminFEqConTransferMngService) Services.getService(
                    WEB3AdminFEqConTransferMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConTransferListInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株振替一覧条件入力に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;

    }
    
    /**
     * (一覧画面表示)<BR>
     * 一覧画面表示を行う。<BR>
     * <BR>
     * 外株振替管理サービスを取得し、execute()メソッドをコールする。
     * @@param l_request - リクエストデータ
     * 
     * @@return WEB3AdminFEqConTransferListResponse
     * @@roseuid 41E3995900B6
     */
    public WEB3AdminFEqConTransferListResponse displayListScreen(
        WEB3AdminFEqConTransferListRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "displayListScreen(" +
            "WEB3AdminFEqConTransferListRequest l_request)"; 
        log.entering(STR_METHOD_NAME);
        
        //外株振替管理サービス
        WEB3AdminFEqConTransferMngService l_service = null;          
         
        //外株振替一覧レスポンス
        WEB3AdminFEqConTransferListResponse l_response = null;     
         
        try
        {
            l_service = 
                (WEB3AdminFEqConTransferMngService) Services.getService(
                    WEB3AdminFEqConTransferMngService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外株振替管理サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminFEqConTransferListResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外株振替一覧に失敗しました。", 
                l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
