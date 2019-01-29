head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.21.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者入出金一覧ハンドラクラス(WEB3AdminAioListHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/05 何文敏 (中訊) 新規作成　@仕様変更モデル NO.694
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者入出金一覧ハンドラ)<BR>
 * 管理者入出金一覧ハンドラクラス<BR>
 * <BR>
 * @@author 何文敏(中訊)
 * @@version 1.0 
 */
public class WEB3AdminAioListHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminAioListHandler.class);

    /**
     * @@roseuid 45C4096500C0
     */
    public WEB3AdminAioListHandler() 
    {
     
    }

    /**
     * 入出金一覧入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者入出金一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param リクエストデータ - リクエストデータ
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListInputResponse
     * @@roseuid 45B73592003C
     */
    public WEB3AdminAioCashTransferListInputResponse getInputScreen(
        WEB3AdminAioCashTransferListInputRequest l_request) 
    {
        String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminAioCashTransferListInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListInputResponse l_response;

        try
        {
            //入出金一覧入力画面表示処理を行う
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__管理者入出金一覧サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminAioCashTransferListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 管理者入出金一覧サービス.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * 入出金一覧結果画面表示処理を行う。<BR>
     * <BR>
     * 管理者入出金一覧サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * <BR>
     * @@param リクエストデータ - リクエストデータ
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListResponse
     * @@roseuid 45B737F200B9
     */
    public WEB3AdminAioCashTransferListResponse getListScreen(WEB3AdminAioCashTransferListRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getListScreen(WEB3AdminAioCashTransferListRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListResponse l_response;

        try
        {
            //その他件数照会サービスを取得し
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__管理者入出金一覧サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminAioCashTransferListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 管理者入出金一覧サービス.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 入出金一覧getダウンロードファ@イル処理を行う。<BR>
     * <BR>
     * 管理者入出金一覧サービスImplを取得し、<BR>
     * <BR>
     * execute()メソッドをコールする。
     * @@param リクエストデータ - リクエストデータ
     * @@return webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadResponse
     * @@roseuid 45B737F3003C
     */
    public WEB3AdminAioCashTransferListDownloadResponse getDownLoadFile(WEB3AdminAioCashTransferListDownloadRequest l_request) 
    {
        String STR_METHOD_NAME = 
            "getDownLoadFile(WEB3AdminAioCashTransferListDownloadRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3AdminAioListService l_service;
        WEB3AdminAioCashTransferListDownloadResponse l_response;

        try
        {
            //その他件数照会サービスを取得し
            l_service = 
                (WEB3AdminAioListService)Services.getService(WEB3AdminAioListService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__管理者入出金一覧サービスの取得に失敗しました__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }

        try
        {
            //execute()メソッドをコールする
            l_response = 
                (WEB3AdminAioCashTransferListDownloadResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminAioCashTransferListDownloadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__error in call 管理者入出金一覧サービス.execute__",
                l_ex);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
