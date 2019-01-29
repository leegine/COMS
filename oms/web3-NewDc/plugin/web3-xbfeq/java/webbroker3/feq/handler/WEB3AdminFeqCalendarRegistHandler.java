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
filename	WEB3AdminFeqCalendarRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式カレンダー登録ハンドラ(WEB3AdminFeqCalendarRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 鄭海良(中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー  
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqCalendarRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式カレンダー登録ハンドラ)<BR>
 * 外国株式カレンダー登録ハンドラクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistHandler.class);
    
    /**
     * @@roseuid 42D0DA150280
     */
    public WEB3AdminFeqCalendarRegistHandler() 
    {
     
    }
    
    /**
     * (get検索条件入力画面)<BR>
     * 外国株式カレンダー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarSearchCondInputResponse
     * @@roseuid 42107CC603CD
     */
    public WEB3AdminFeqCalendarSearchCondInputResponse getQueryCondInputScreen(
        WEB3AdminFeqCalendarSearchCondInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getQueryCondInputScreen(WEB3AdminFeqCalendarSearchCondInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("リクエストがnullです。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqCalendarSearchCondInputResponse l_response = null;
        WEB3AdminFeqCalendarRegistService l_service = null;

        try
        {            
            //get外国株式カレンダー登録サービス
            l_service = (WEB3AdminFeqCalendarRegistService)
                Services.getService(WEB3AdminFeqCalendarRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("外国株式カレンダー登録サービスを取得に失敗しました。");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式カレンダー登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqCalendarSearchCondInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCalendarSearchCondInputResponse)l_request.createResponse();
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
                "get検索条件入力画面に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCalendarSearchCondInputResponse) l_request.createResponse();
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
                "get検索条件入力画面に失敗しました。",
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
     * 外国株式カレンダー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistInputResponse
     * @@roseuid 42107CDB02B3
     */
    public WEB3AdminFeqCalendarRegistInputResponse getInputScreen(WEB3AdminFeqCalendarRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqCalendarRegistInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqCalendarRegistInputResponse l_response = null;
        WEB3AdminFeqCalendarRegistService l_service = null;

        try
        {            
            //get外国株式カレンダー登録サービス
            l_service = (WEB3AdminFeqCalendarRegistService)
                Services.getService(WEB3AdminFeqCalendarRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式カレンダー登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqCalendarRegistInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCalendarRegistInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqCalendarRegistInputResponse) l_request.createResponse();
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
     * (validate登録)<BR>
     * 外国株式カレンダー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistConfirmResponse
     * @@roseuid 42107CEC00BF
     */
    public WEB3AdminFeqCalendarRegistConfirmResponse validateRegist(WEB3AdminFeqCalendarRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " validateRegist(WEB3AdminFeqCalendarRegistConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqCalendarRegistConfirmResponse l_response = null;
        WEB3AdminFeqCalendarRegistService l_service = null;

        try
        {            
            //get外国株式カレンダー登録サービス
            l_service = (WEB3AdminFeqCalendarRegistService)
                Services.getService(WEB3AdminFeqCalendarRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式カレンダー登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqCalendarRegistConfirmResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCalendarRegistConfirmResponse)l_request.createResponse();
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
                "validate登録に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCalendarRegistConfirmResponse) l_request.createResponse();
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
                "validate登録に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit登録)<BR>
     * 外国株式カレンダー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqCalendarRegistCompleteResponse
     * @@roseuid 42107CF300CF
     */
    public WEB3AdminFeqCalendarRegistCompleteResponse submitRegist(WEB3AdminFeqCalendarRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " submitRegist(WEB3AdminFeqCalendarRegistCompleteRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqCalendarRegistCompleteResponse l_response = null;
        WEB3AdminFeqCalendarRegistService l_service = null;

        try
        {            
            //get外国株式カレンダー登録サービス
            l_service = (WEB3AdminFeqCalendarRegistService)
                Services.getService(WEB3AdminFeqCalendarRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式カレンダー登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqCalendarRegistCompleteResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqCalendarRegistCompleteResponse)l_request.createResponse();
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
                "submit登録に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqCalendarRegistCompleteResponse) l_request.createResponse();
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
                "submit登録に失敗しました。",
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
