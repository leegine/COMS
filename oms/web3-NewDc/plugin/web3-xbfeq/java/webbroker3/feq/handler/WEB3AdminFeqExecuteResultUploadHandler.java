head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteResultUploadHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞハンドラ(WEB3AdminFeqExecuteResultUploadHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 鄭海良(中訊) 新規作成
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecuteResultUploadService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞハンドラ)<BR>
 * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞハンドラ<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteResultUploadHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqExecuteResultUploadHandler.class);
    
    /**
     * @@roseuid 42D0DA180251
     */
    public WEB3AdminFeqExecuteResultUploadHandler() 
    {
     
    }
    
    /**
     * (getアップロード画面)<BR>
     * アップロード画面表示処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード入力リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadInputResponse
     * @@roseuid 429AE2450007
     */
    public WEB3AdminFeqExecuteResultUploadInputResponse getUploadScreen(
        WEB3AdminFeqExecuteResultUploadInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getUploadScreen(WEB3AdminFeqExecuteResultUploadInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecuteResultUploadInputResponse l_response = null;
        WEB3AdminFeqExecuteResultUploadService l_service = null;

        try
        {            
            //get管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
            l_service = (WEB3AdminFeqExecuteResultUploadService)
                Services.getService(WEB3AdminFeqExecuteResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecuteResultUploadInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "getアップロード画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadInputResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "getアップロード画面に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validateアップロード)<BR>
     * アップロードファ@イル確認処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * ※エラーレスポンスに（WEB3BaseException.errorMessage）をセットすること。<BR>
     *    l_response.errorInfo = l_ex.getErrorInfo();<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード確認リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse
     * @@roseuid 429AE2450009
     */
    public WEB3AdminFeqExecuteResultUploadConfirmResponse validateUpload(
        WEB3AdminFeqExecuteResultUploadConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateUpload(WEB3AdminFeqExecuteResultUploadConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecuteResultUploadConfirmResponse l_response = null;
        WEB3AdminFeqExecuteResultUploadService l_service = null;

        try
        {            
            //get管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
            l_service = (WEB3AdminFeqExecuteResultUploadService)
                Services.getService(WEB3AdminFeqExecuteResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecuteResultUploadConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
			l_response.errorMessage = l_ex.getErrorMessage();
            log.error(
                l_request, 
                "validateアップロードに失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadConfirmResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "validateアップロードに失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submitアップロード)<BR>
     * アップロード完了処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード完了リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadCompleteResponse
     * @@roseuid 429AE245000B
     */
    public WEB3AdminFeqExecuteResultUploadCompleteResponse submitUpload(
        WEB3AdminFeqExecuteResultUploadCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitUpload(WEB3AdminFeqExecuteResultUploadCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecuteResultUploadCompleteResponse l_response = null;
        WEB3AdminFeqExecuteResultUploadService l_service = null;

        try
        {            
            //get管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
            l_service = (WEB3AdminFeqExecuteResultUploadService)
                Services.getService(WEB3AdminFeqExecuteResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecuteResultUploadCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "submitアップロードに失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadCompleteResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "submitアップロードに失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (undoアップロード)<BR>
     * アップロード中止処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者外国株式約定結果アップロード中止リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadStopResponse
     * @@roseuid 429AE2450017
     */
    public WEB3AdminFeqExecuteResultUploadStopResponse undoUpload(
        WEB3AdminFeqExecuteResultUploadStopRequest l_request) 
    {
        final String STR_METHOD_NAME = " undoUpload(WEB3AdminFeqExecuteResultUploadStopRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecuteResultUploadStopResponse l_response = null;
        WEB3AdminFeqExecuteResultUploadService l_service = null;

        try
        {            
            //get管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
            l_service = (WEB3AdminFeqExecuteResultUploadService)
                Services.getService(WEB3AdminFeqExecuteResultUploadService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecuteResultUploadStopResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadStopResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "undoアップロードに失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecuteResultUploadStopResponse) l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;    
            log.error(
                l_request, 
                "undoアップロードに失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
