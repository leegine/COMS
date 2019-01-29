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
filename	WEB3AdminFeqMarketLinkChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式市場連動区分変更ハンドラ(WEB3AdminFeqMarketRequestDivChangeHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 何文敏 (中訊) 新規作成 
*/
package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputRequest;
import webbroker3.feq.message.WEB3AdminFeqMarketLinkChangeInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqMarketLinkChangeService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式市場連動区分変更ハンドラ)<BR>
 * 管理者外国株式市場連動区分変更ハンドラクラス<BR>
 *   
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminFeqMarketLinkChangeHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqMarketLinkChangeHandler.class);
    
    /**
     * @@roseuid 42D0DA1602DE
     */
    public WEB3AdminFeqMarketLinkChangeHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示処理を行なう。<BR>
     * <BR>
     * 管理者外国株式市場連動区分変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeInputResponse
     * @@roseuid 429FECBF005E
     */
    public WEB3AdminFeqMarketLinkChangeInputResponse getInputScreen(
    	WEB3AdminFeqMarketLinkChangeInputRequest l_request) 
    {
        final String STR_METHOD_NAME = "getInputScreen（WEB3AdminFeqMarketLinkChangeInputRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqMarketLinkChangeInputResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get管理者外国株式市場連動区分変更サービス
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式市場連動区分変更サービスを取得に失敗しました。");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse)l_request.createResponse();
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
            l_response = (WEB3AdminFeqMarketLinkChangeInputResponse) l_request.createResponse();
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
     * (validate変更)<BR>
     * 管理者外国株式市場連動区分変更確認処理を行なう。 <BR>
     * <BR>
     * 管理者外国株式市場連動区分変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqMarketLinkChangeConfirmResponse validateChange(
    	WEB3AdminFeqMarketLinkChangeConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = "validateChange(WEB3AdminFeqMarketLinkChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqMarketLinkChangeConfirmResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get管理者外国株式市場連動区分変更サービス
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式市場連動区分変更サービスを取得に失敗しました。");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse)l_request.createResponse();
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
                "validate変更を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeConfirmResponse) l_request.createResponse();
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
                "validate変更を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit変更)<BR>
     * 管理者外国株式市場連動区分変更完了処理を行なう。<BR>
     * <BR>
     * 管理者外国株式市場連動区分変更サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3AdminFeqMarketLinkChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    public WEB3AdminFeqMarketLinkChangeCompleteResponse submitChange(
    	WEB3AdminFeqMarketLinkChangeCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AdminFeqMarketLinkChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "リクエストがnullです。");
        }

        WEB3AdminFeqMarketLinkChangeCompleteResponse l_response = null;
        WEB3AdminFeqMarketLinkChangeService l_service = null;
        
        try
        {            
            //get管理者外国株式市場連動区分変更サービス
            l_service = (WEB3AdminFeqMarketLinkChangeService)
                Services.getService(WEB3AdminFeqMarketLinkChangeService.class);
            if (l_service == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "管理者外国株式市場連動区分変更サービスを取得に失敗しました。");
            }
            
            //execute
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse)l_request.createResponse();
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
                "submit変更を実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminFeqMarketLinkChangeCompleteResponse) l_request.createResponse();
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
                "submit変更を実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

}
@
