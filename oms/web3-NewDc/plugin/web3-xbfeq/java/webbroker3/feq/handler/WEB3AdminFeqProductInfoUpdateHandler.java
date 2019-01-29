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
filename	WEB3AdminFeqProductInfoUpdateHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄情報更新ハンドラ(WEB3AdminFeqProductInfoUpdateHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー       
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式銘柄情報更新ハンドラ)<BR>
 * 外国株式銘柄情報更新ハンドラクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfoUpdateHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfoUpdateHandler.class);
    
    /**
     * @@roseuid 42D0DA180119
     */
    public WEB3AdminFeqProductInfoUpdateHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 外国株式銘柄情報更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse
     * @@roseuid 42BF94240055
     */
    public WEB3AdminFeqProductInformationUpdateInputResponse getInputScreen(
        WEB3AdminFeqProductInformationUpdateInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqProductInformationUpdateInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqProductInformationUpdateInputResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get外国株式銘柄情報更新サービス
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式銘柄情報更新サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse)
                l_request.createResponse();
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
                "get入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateInputResponse) 
                l_request.createResponse();
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
                "get入力画面に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get明細入力画面)<BR>
     * 外国株式銘柄情報更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse
     * @@roseuid 421AB50E03C2
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse getDetailInputScreen(
        WEB3AdminFeqProductInformationUpdateDetailsInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " getDetailInputScreen(WEB3AdminFeqProductInformationUpdateDetailsInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqProductInformationUpdateDetailsInputResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get外国株式銘柄情報更新サービス
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式銘柄情報更新サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)
                l_request.createResponse();
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
                "get明細入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateDetailsInputResponse) 
                l_request.createResponse();
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
                "get明細入力画面に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate更新)<BR>
     * 外国株式銘柄情報更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse
     * @@roseuid 421AB52902A9
     */
    public WEB3AdminFeqProductInformationUpdateConfirmResponse validateUpdate(
        WEB3AdminFeqProductInformationUpdateConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqProductInformationUpdateConfirmResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get外国株式銘柄情報更新サービス
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式銘柄情報更新サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse)
                l_request.createResponse();
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
                "validate更新に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateConfirmResponse) 
                l_request.createResponse();
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
                "validate更新に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit更新)<BR>
     * 外国株式銘柄情報更新サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse
     * @@roseuid 421AB5510103
     */
    public WEB3AdminFeqProductInformationUpdateCompleteResponse submitUpdate(
        WEB3AdminFeqProductInformationUpdateCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            " submitUpdate(WEB3AdminFeqProductInformationUpdateCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqProductInformationUpdateCompleteResponse l_response = null;
        WEB3AdminFeqProductInfoUpdateService l_service = null;

        try
        {            
            //get外国株式銘柄情報更新サービス
            l_service = (WEB3AdminFeqProductInfoUpdateService)
                Services.getService(WEB3AdminFeqProductInfoUpdateService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式銘柄情報更新サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse)
                l_request.createResponse();
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
                "submit更新に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqProductInformationUpdateCompleteResponse) 
                l_request.createResponse();
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
                "submit更新に失敗しました。",
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
