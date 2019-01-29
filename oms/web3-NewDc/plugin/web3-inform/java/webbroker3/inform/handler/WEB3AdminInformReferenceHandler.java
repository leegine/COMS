head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.56.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索ハンドラクラス(WEB3AdminInformReferenceHandler.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (連絡情報検索ハンドラ)<BR>
 * 連絡情報検索ハンドラクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformReferenceHandler implements MessageHandler 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceHandler.class);
    
    /**
     * @@roseuid 41EE631D038A
     */
    public WEB3AdminInformReferenceHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 連絡情報検索入力画面を表示する。<BR>
     * <BR>
     * 連絡情報検索サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformInputResponse
     * @@roseuid 41BD7F970278
     */
    public WEB3AdminInformInputResponse informInputDisplay(WEB3AdminInformInputRequest l_request) 
    {
        final String METHOD_NAME = "informInputDisplay(WEB3AdminInformInputRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformInputResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //連絡情報検索サービスを取得
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "連絡情報検索サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformInputResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "連絡情報検索に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (一覧画面表示)<BR>
     * 連絡情報検索一覧画面を表示する。<BR>
     * <BR>
     * 連絡情報検索サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformListResponse
     * @@roseuid 41BD817A01FB
     */
    public WEB3AdminInformListResponse informListDisplay(WEB3AdminInformListRequest l_request) 
    {
        final String METHOD_NAME = "informListDisplay(WEB3AdminInformListRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformListResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //連絡情報検索サービスを取得
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "連絡情報検索サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformListResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "連絡情報検索に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (詳細画面表示)<BR>
     * 連絡情報検索詳細画面を表示する。<BR>
     * <BR>
     * 連絡情報検索サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformDetailResponse
     * @@roseuid 41BD817B03C0
     */
    public WEB3AdminInformDetailResponse informDetailDisplay(WEB3AdminInformDetailRequest l_request) 
    {
        final String METHOD_NAME = "informDetailDisplay(WEB3AdminInformDetailRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformDetailResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //連絡情報検索サービスを取得
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "連絡情報検索サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformDetailResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "連絡情報検索に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (各種連絡ダウンロード)<BR>
     * 各種連絡ダウンロードファ@イル取得処理を行う。<BR>
     * <BR>
     * 連絡情報検索サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return webbroker3.inform.message.WEB3AdminInformDownLoadResponse
     * @@roseuid 41BD822C0259
     */
    public WEB3AdminInformDownLoadResponse allInformDownload(WEB3AdminInformDownLoadRequest l_request) 
    {
        final String METHOD_NAME = "allInformDownload(WEB3AdminInformDownLoadRequest)";
        log.entering(METHOD_NAME);

        WEB3AdminInformDownLoadResponse l_response = null;
        WEB3AdminInformReferenceService l_service = null;
        
        //連絡情報検索サービスを取得
        try
        {
            l_service =
                (WEB3AdminInformReferenceService)Services.getService(
                    WEB3AdminInformReferenceService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "連絡情報検索サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //連絡情報検索サービスオブジェクト.execute（）をコールし、
        //処理結果であるレスポンスオブジェクトを取得する。
        try
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminInformDownLoadResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "連絡情報検索に失敗しました。", l_ex);
            return l_response;
        }

        log.exiting(METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
