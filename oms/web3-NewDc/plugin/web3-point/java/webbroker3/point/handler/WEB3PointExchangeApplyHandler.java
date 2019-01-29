head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointExchangeApplyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換申込ハンドラ(WEB3PointExchangeApplyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3PointApplyCompleteRequest;
import webbroker3.point.message.WEB3PointApplyCompleteResponse;
import webbroker3.point.message.WEB3PointApplyConfirmRequest;
import webbroker3.point.message.WEB3PointApplyConfirmResponse;
import webbroker3.point.message.WEB3PointApplyInputRequest;
import webbroker3.point.message.WEB3PointApplyInputResponse;
import webbroker3.point.message.WEB3PointApplyReferenceRequest;
import webbroker3.point.message.WEB3PointApplyReferenceResponse;
import webbroker3.point.service.delegate.WEB3PointExchangeApplyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (ポイント交換申込ハンドラ)<BR>
 * ポイント交換申込ハンドラクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointExchangeApplyHandler implements MessageHandler 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3PointExchangeApplyHandler.class);
    
    /**
     * (サービス画面表示)<BR>
     * サービス画面の表示を行う。<BR>
     * <BR>
     * ポイント交換申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyReferenceResponse
     * @@roseuid 419DA01B0190
     */
    public WEB3PointApplyReferenceResponse serviceScreenDisplay(WEB3PointApplyReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " serviceScreenDisplay(WEB3PointApplyReferenceRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointApplyReferenceResponse l_response = null;
        WEB3PointExchangeApplyService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3PointExchangeApplyService)Services.getService(
                    WEB3PointExchangeApplyService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "ポイント交換申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3PointApplyReferenceResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyReferenceResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "サービス画面表示に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (選択画面表示)<BR>
     * 申込の選択画面を表示する。<BR>
     * <BR>
     * ポイント交換申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyInputResponse
     * @@roseuid 419DA01B01AF
     */
    public WEB3PointApplyInputResponse selectScreenDisplay(WEB3PointApplyInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " selectScreenDisplay(WEB3PointApplyInputRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointApplyInputResponse l_response = null;
        WEB3PointExchangeApplyService l_service = null;      

        try
        {
            //get service
            l_service = 
                (WEB3PointExchangeApplyService)Services.getService(
                    WEB3PointExchangeApplyService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "ポイント交換申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response = (WEB3PointApplyInputResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "選択画面表示に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (申込確認)<BR>
     * 申込の審査を行う。<BR>
     * <BR>
     * ポイント交換申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyConfirmResponse
     * @@roseuid 419DA0D70171
     */
    public WEB3PointApplyConfirmResponse applyConfirm(WEB3PointApplyConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " applyConfirm(WEB3PointApplyConfirmRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointApplyConfirmResponse l_response = null;
        WEB3PointExchangeApplyService l_service = null;      
            
        try
        {
            //get service
            l_service = 
                (WEB3PointExchangeApplyService)Services.getService(
                    WEB3PointExchangeApplyService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "ポイント交換申込サービスに失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "申込確認に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (申込完了)<BR>
     * 申込の登録を行う。<BR>
     * <BR>
     * ポイント交換申込サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3PointApplyCompleteResponse
     * @@roseuid 419DA01B01BF
     */
    public WEB3PointApplyCompleteResponse applyComplete(WEB3PointApplyCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " applyComplete(WEB3PointApplyCompleteRequest )";    
        log.entering(STR_METHOD_NAME);
        
        WEB3PointApplyCompleteResponse l_response = null;
        WEB3PointExchangeApplyService l_service = null;      
            
        try
        {
            //get service
            l_service = 
                (WEB3PointExchangeApplyService)Services.getService(
                    WEB3PointExchangeApplyService.class);
        }
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "ポイント交換申込サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;                  
        }

        try
        {
            //execute()メソッドをコールする
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }
        catch (WEB3BaseException l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3PointApplyCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "申込完了に失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
}
@
