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
filename	WEB3AdminFeqOrderAndExecutionHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定入力ハンドラ(WEB3AdminFeqOrderAndExecutionHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 郭英 (中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー   
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式約定入力ハンドラ)<BR>
 * 管理者外国株式約定入力ハンドラ<BR>
 *
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAndExecutionHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAndExecutionHandler.class);        
    
    /**
     * @@roseuid 42D0DA18032C
     */
    public WEB3AdminFeqOrderAndExecutionHandler() 
    {
     
    }
    
    /**
     * (get検索画面)<BR>
     * 検索画面表示処理。<BR>
     * <BR>
     * 管理者外国株式約定入力サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionSearchResponse
     * @@roseuid 42B914BF02DA
     */
    public WEB3AdminFeqOrderAndExecutionSearchResponse getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest l_request) 
    {
        final String STR_METHOD_NAME = " getQueryScreen(WEB3AdminFeqOrderAndExecutionSearchRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderAndExecutionSearchResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get管理者外国株式約定入力サービス
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse)l_request.createResponse();
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
                "get検索画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionSearchResponse) l_request.createResponse();
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
                "get検索画面を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面の表示に必要なデータを取得する。<BR>
     * <BR>
     * 管理者外国株式約定入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputResponse
     * @@roseuid 429FFB2B0252
     */
    public WEB3AdminFeqOrderAndExecutionInputResponse getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAndExecutionInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderAndExecutionInputResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get管理者外国株式約定入力サービス
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse)l_request.createResponse();
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
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionInputResponse) l_request.createResponse();
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
                "get入力画面を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate約定)<BR>
     * 約定入力確認処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionConfirmResponse
     * @@roseuid 429FFB2B0261
     */
    public WEB3AdminFeqOrderAndExecutionConfirmResponse validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateExec(WEB3AdminFeqOrderAndExecutionConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderAndExecutionConfirmResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get管理者外国株式約定入力サービス
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse)l_request.createResponse();
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
                "validate約定を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionConfirmResponse) l_request.createResponse();
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
                "validate約定を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit約定)<BR>
     * 約定入力完了処理を行う。<BR>
     * <BR>
     * 管理者外国株式約定入力サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionCompleteResponse
     * @@roseuid 429FFB2B0271
     */
    public WEB3AdminFeqOrderAndExecutionCompleteResponse submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitExec(WEB3AdminFeqOrderAndExecutionCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqOrderAndExecutionCompleteResponse l_response = null;
        WEB3AdminFeqOrderAndExecutionService l_service = null;

        try
        {            
            //get管理者外国株式約定入力サービス
            l_service = (WEB3AdminFeqOrderAndExecutionService)
                Services.getService(WEB3AdminFeqOrderAndExecutionService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式約定入力サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse)l_request.createResponse();
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
                "submit約定を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqOrderAndExecutionCompleteResponse) l_request.createResponse();
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
                "submit約定を実施に失敗しました。",
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
