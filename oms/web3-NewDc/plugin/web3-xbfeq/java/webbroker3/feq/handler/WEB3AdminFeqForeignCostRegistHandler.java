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
filename	WEB3AdminFeqForeignCostRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式現地手数料登録ハンドラ(WEB3AdminFeqForeignCostRegistHandler.java)
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
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputRequest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqForeignCostRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式現地手数料登録ハンドラ)<BR>
 * 外国株式現地手数料登録ハンドラクラス<BR>
 * 
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3AdminFeqForeignCostRegistHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqForeignCostRegistHandler.class);
    
    /**
     * @@roseuid 42D0DA160167
     */
    public WEB3AdminFeqForeignCostRegistHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 外国株式現地手数料登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputResponse
     * @@roseuid 4214996902C0
     */
    public WEB3AdminFeqForeignCostRegistInputResponse getInputScreen(
        WEB3AdminFeqForeignCostRegistInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getInputScreen(WEB3AdminFeqForeignCostRegistInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqForeignCostRegistInputResponse l_response = null;
        WEB3AdminFeqForeignCostRegistService l_service = null;

        try
        {            
            //get外国株式現地手数料登録サービス
            l_service = (WEB3AdminFeqForeignCostRegistService)
                Services.getService(WEB3AdminFeqForeignCostRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式現地手数料登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqForeignCostRegistInputResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqForeignCostRegistInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqForeignCostRegistInputResponse) l_request.createResponse();
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
     * (validate登録)<BR>
     * 外国株式現地手数料登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistConfirmResponse
     * @@roseuid 4214996902D0
     */
    public WEB3AdminFeqForeignCostRegistConfirmResponse validateRegist(
        WEB3AdminFeqForeignCostRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "validateRegist(WEB3AdminFeqForeignCostRegistConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqForeignCostRegistConfirmResponse l_response = null;
        WEB3AdminFeqForeignCostRegistService l_service = null;

        try
        {            
            //get外国株式現地手数料登録サービス
            l_service = (WEB3AdminFeqForeignCostRegistService)
                Services.getService(WEB3AdminFeqForeignCostRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式現地手数料登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqForeignCostRegistConfirmResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqForeignCostRegistConfirmResponse)l_request.createResponse();
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
                "validate登録を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqForeignCostRegistConfirmResponse) l_request.createResponse();
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
                "validate登録を実施に失敗しました。",
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
     * 外国株式現地手数料登録サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCompleteResponse
     * @@roseuid 4214996902EF
     */
    public WEB3AdminFeqForeignCostRegistCompleteResponse submitRegist(
        WEB3AdminFeqForeignCostRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "submitRegist(WEB3AdminFeqForeignCostRegistCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqForeignCostRegistCompleteResponse l_response = null;
        WEB3AdminFeqForeignCostRegistService l_service = null;

        try
        {            
            //get外国株式現地手数料登録サービス
            l_service = (WEB3AdminFeqForeignCostRegistService)
                Services.getService(WEB3AdminFeqForeignCostRegistService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "外国株式現地手数料登録サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqForeignCostRegistCompleteResponse)
                l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqForeignCostRegistCompleteResponse)l_request.createResponse();
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
                "submit登録を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqForeignCostRegistCompleteResponse) l_request.createResponse();
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
                "submit登録を実施に失敗しました。",
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
