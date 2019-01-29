head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionInputHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式出来入力ハンドラ(WEB3AdminFeqExecutionInputHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 戴義波(中訊) 新規作成
                   2005/08/01 韋念瓊(中訊) レビュー
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionInputService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式出来入力ハンドラ)<BR>
 * 管理者外国株式出来入力ハンドラ<BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionInputHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionInputHandler.class);
    
    /**
     * @@roseuid 42D0DA1602DE
     */
    public WEB3AdminFeqExecutionInputHandler() 
    {
     
    }
    
    /**
     * (get検索画面)<BR>
     * 検索画面表示処理。<BR>
     * <BR>
     * 管理者外国株式出来入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionSearchResponse
     * @@roseuid 42B91022022E
     */
    public WEB3AdminFeqExecutionSearchResponse getQueryScreen(WEB3AdminFeqExecutionSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqExecutionSearchRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecutionSearchResponse l_response = null;
        WEB3AdminFeqExecutionInputService l_service = null;

        try
        {            
            //get管理者外国株式出来入力サービス
            l_service = (WEB3AdminFeqExecutionInputService)
                Services.getService(WEB3AdminFeqExecutionInputService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式出来入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecutionSearchResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecutionSearchResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "検索画面表示処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecutionSearchResponse) l_request.createResponse();
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
                "検索画面表示処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 管理者外国株式出来入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionInputResponse
     * @@roseuid 428C41200379
     */
    public WEB3AdminFeqExecutionInputResponse getInputScreen(WEB3AdminFeqExecutionInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecutionInputResponse l_response = null;
        WEB3AdminFeqExecutionInputService l_service = null;

        try
        {            
            //get管理者外国株式出来入力サービス
            l_service = (WEB3AdminFeqExecutionInputService)
                Services.getService(WEB3AdminFeqExecutionInputService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式出来入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecutionInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecutionInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "入力画面表示処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecutionInputResponse) l_request.createResponse();
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
                "入力画面表示処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate出来)<BR>
     * 出来入力確認処理を行う。<BR>
     * <BR>
     * 管理者外国株式出来入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionConfirmResponse
     * @@roseuid 428C41210165
     */
    public WEB3AdminFeqExecutionConfirmResponse validateExec(WEB3AdminFeqExecutionConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateExec(WEB3AdminFeqExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecutionConfirmResponse l_response = null;
        WEB3AdminFeqExecutionInputService l_service = null;

        try
        {            
            //get管理者外国株式出来入力サービス
            l_service = (WEB3AdminFeqExecutionInputService)
                Services.getService(WEB3AdminFeqExecutionInputService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式出来入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecutionConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecutionConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "validate出来処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecutionConfirmResponse) l_request.createResponse();
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
                "validate出来処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit出来)<BR>
     * 出来入力完了処理を行う。<BR>
     * <BR>
     * 管理者外国株式出来入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqExecutionCompleteResponse
     * @@roseuid 428C412102CD
     */
    public WEB3AdminFeqExecutionCompleteResponse submitExec(WEB3AdminFeqExecutionCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitExec(WEB3AdminFeqExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqExecutionCompleteResponse l_response = null;
        WEB3AdminFeqExecutionInputService l_service = null;

        try
        {            
            //get管理者外国株式出来入力サービス
            l_service = (WEB3AdminFeqExecutionInputService)
                Services.getService(WEB3AdminFeqExecutionInputService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式出来入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqExecutionCompleteResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqExecutionCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "submit出来処理を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqExecutionCompleteResponse) l_request.createResponse();
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
                "submit出来処理を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
