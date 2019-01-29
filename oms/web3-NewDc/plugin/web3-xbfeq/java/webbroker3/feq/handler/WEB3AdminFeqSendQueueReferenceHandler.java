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
filename	WEB3AdminFeqSendQueueReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式SENDキュー照会ハンドラ(WEB3AdminFeqSendQueueReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成 
*/
package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式SENDキュー照会ハンドラ )<BR>
 * 管理者外国株式SENDキュー照会ハンドラクラス<BR>
 *   
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueReferenceHandler.class);
    
	public WEB3AdminFeqSendQueueReferenceHandler()
	{
		
	}
	
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理。 	<BR>
     * <BR>
     * 管理者外国株式SENDキュー照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceInputResponse
     * @@roseuid 429FECBF005E
     */
    public WEB3AdminFeqSendQueueReferenceInputResponse getInputScreen(
    	WEB3AdminFeqSendQueueReferenceInputRequest l_request) 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqSendQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqSendQueueReferenceInputResponse l_response = null;
        WEB3AdminFeqSendQueueReferenceService l_service = null;

        try
        {            
            l_service = (WEB3AdminFeqSendQueueReferenceService)
                Services.getService(WEB3AdminFeqSendQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式SENDキュー照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqSendQueueReferenceInputResponse) l_request.createResponse();
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
     * (get一覧画面)<BR>
     * 一覧画面表示処理。 <BR>
     * <BR>
     * 管理者外国株式SENDキュー照会サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceResponse
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqSendQueueReferenceResponse getListScreen(
    	WEB3AdminFeqSendQueueReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqSendQueueReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqSendQueueReferenceResponse l_response = null;
        WEB3AdminFeqSendQueueReferenceService l_service = null;

        try
        {            
            l_service = (WEB3AdminFeqSendQueueReferenceService)
                Services.getService(WEB3AdminFeqSendQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式SENDキュー照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqSendQueueReferenceResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceResponse)l_request.createResponse();
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
                "get一覧画面を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqSendQueueReferenceResponse) l_request.createResponse();
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
                "get一覧画面を実施に失敗しました。",
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
