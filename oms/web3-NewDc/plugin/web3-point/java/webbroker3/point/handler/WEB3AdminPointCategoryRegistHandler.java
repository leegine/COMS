head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.02.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー登録ハンドラ(WEB3AdminPointCategoryRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputRequest;
import webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (カテゴリー登録ハンドラ)<BR>
 * カテゴリー登録ハンドラクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistHandler implements MessageHandler 
{
    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryRegistHandler.class);
    
    /**
     * (入力画面表示)<BR>
     * 入力画面の表示を行う。<BR>
     * <BR>
     * カテゴリー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistInputResponse
     * @@roseuid 4191A8480214
     */
    public WEB3AdminPointCategoryRegistInputResponse inputScreenDisplay(WEB3AdminPointCategoryRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " inputScreenDisplay(WEB3AdminPointCategoryRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistInputResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;
        
        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistInputResponse) l_request.createResponse();
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
                " カテゴリー登録サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistInputResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointCategoryRegistInputResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " カテゴリー登録サービスを取得リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
    
    /**
     * (登録確認)<BR>
     * 登録の審査を行う。<BR>
     * <BR>
     * カテゴリー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistConfirmResponse
     * @@roseuid 41917B2F0017
     */
    public WEB3AdminPointCategoryRegistConfirmResponse registConfirm(WEB3AdminPointCategoryRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " registConfirm(WEB3AdminPointCategoryRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistConfirmResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistConfirmResponse) l_request.createResponse();
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
                " カテゴリー登録サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistConfirmResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointCategoryRegistConfirmResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " カテゴリー登録サービスを取得リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    
    }
    
    /**
     * (登録完了)<BR>
     * カテゴリーの登録を行う。<BR>
     * <BR>
     * カテゴリー登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointCategoryRegistCompleteResponse
     * @@roseuid 41917BA603B1
     */
    public WEB3AdminPointCategoryRegistCompleteResponse registComplete(WEB3AdminPointCategoryRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = " registComplete(WEB3AdminPointCategoryRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminPointCategoryRegistCompleteResponse l_response = null;
        WEB3AdminPointCategoryRegistService l_service = null;

        try
        {
            l_service = (WEB3AdminPointCategoryRegistService)
                Services.getService(WEB3AdminPointCategoryRegistService.class);//Exception
        }        
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            if (l_request == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_response = (WEB3AdminPointCategoryRegistCompleteResponse) l_request.createResponse();
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
                " カテゴリー登録サービスを取得に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response = (WEB3AdminPointCategoryRegistCompleteResponse)l_service.execute(l_request);//WEB3BaseException
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
            l_response = (WEB3AdminPointCategoryRegistCompleteResponse)l_request.createResponse();
            if (l_response == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, " カテゴリー登録サービスを取得リクエストに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
