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
filename	WEB3AdminFeqRcvdQueueReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式RCVDキュー照会ハンドラ(WEB3AdminFeqRcvdQueueReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 徐大方 (中訊) 新規作成
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqRcvdQueueReferenceResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqRcvdQueueReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式RCVDキュー照会ハンドラ)<BR>
 * 管理者外国株式RCVDキュー照会ハンドラクラス<BR>
 * 
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminFeqRcvdQueueReferenceHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqRcvdQueueReferenceHandler.class);
    
    /**
     * @@roseuid 42D0DA1600BB
     */
    public WEB3AdminFeqRcvdQueueReferenceHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理。<BR>
     * <BR>
     * 管理者外国株式RCVDキュー照会サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceInputResponse
     * @@roseuid 4214980A032E
     */
    public WEB3AdminFeqRcvdQueueReferenceInputResponse getInputScreen(WEB3AdminFeqRcvdQueueReferenceInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqRcvdQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqRcvdQueueReferenceInputResponse l_response = null;
        WEB3AdminFeqRcvdQueueReferenceService l_service = null;

        try
        {            
            //管理者外国株式RCVDキュー照会サービス
            l_service = (WEB3AdminFeqRcvdQueueReferenceService)
                Services.getService(WEB3AdminFeqRcvdQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式RCVDキュー照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqRcvdQueueReferenceInputResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqRcvdQueueReferenceInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqRcvdQueueReferenceInputResponse) l_request.createResponse();
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
     * 一覧画面表示処理。<BR>
     * <BR>
     * 管理者外国株式RCVDキュー照会サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3AdminFeqRcvdQueueReferenceResponse
     * @@roseuid 4214980A032E
     */
    public WEB3AdminFeqRcvdQueueReferenceResponse getListScreen(WEB3AdminFeqRcvdQueueReferenceRequest l_request)
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqRcvdQueueReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqRcvdQueueReferenceResponse l_response = null;
        WEB3AdminFeqRcvdQueueReferenceService l_service = null;

        try
        {            
            //管理者外国株式RCVDキュー照会サービス
            l_service = (WEB3AdminFeqRcvdQueueReferenceService)
                Services.getService(WEB3AdminFeqRcvdQueueReferenceService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式RCVDキュー照会サービスを取得に失敗しました。");
            }
            //execute
            l_response = (WEB3AdminFeqRcvdQueueReferenceResponse)l_service.execute(l_request);//WEB3BaseException
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqRcvdQueueReferenceResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqRcvdQueueReferenceResponse) l_request.createResponse();
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
