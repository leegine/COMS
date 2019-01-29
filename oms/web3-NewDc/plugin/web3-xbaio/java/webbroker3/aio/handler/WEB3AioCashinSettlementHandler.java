head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.24.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettlementHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済依頼ハンドラクラス(WEB3AioCashinSettlementHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 屈陽 (中訊) 新規作成
                   2004/10/23 于美麗 (中訊) レビュー 
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.aio.message.WEB3AioCashinConfirmRequest;
import webbroker3.aio.message.WEB3AioCashinConfirmResponse;
import webbroker3.aio.message.WEB3AioCashinSettlementRequest;
import webbroker3.aio.message.WEB3AioCashinSettlementResponse;
import webbroker3.aio.service.delegate.WEB3AioCashinSettlementService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済依頼ハンドラ)<BR>
 * 決済依頼ハンドラクラス<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettlementHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettlementHandler.class);
    
    /**
     * (confirm決済)<BR>
     * 決済依頼の発注審査を行う。<BR>
     * <BR>
     * 決済依頼サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * @@return WEB3AioCashinConfirmResponse
     * @@roseuid 40F25E7502EF
     */
    public WEB3AioCashinConfirmResponse handleConfirmSettlement(
        WEB3AioCashinConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "handleConfirmSettlement(WEB3AioCashinConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSettlementService l_service;
        WEB3AioCashinConfirmResponse l_response;
        
        try
        {
            //決済依頼サービスを取得し
            l_service = 
                (WEB3AioCashinSettlementService)Services.getService(
                WEB3AioCashinSettlementService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AioCashinConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__confirm決済__決済依頼サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            log.debug("Before the execute ---------------------------------------------");
            //execute()メソッドをコールする
            l_response = (WEB3AioCashinConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__confirm決済__決済依頼の発注審査処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (start決済)<BR>
     * 決済依頼処理を行う。<BR>
     * <BR>
     * 決済依頼サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 
     * @@return WEB3AioCashinSettlementResponse
     * @@roseuid 40F25E80036C
     */
    public WEB3AioCashinSettlementResponse handleStartSettlement(WEB3AioCashinSettlementRequest l_request) 
    {
        String l_strMethodName = "handleStartSettlement(WEB3AioCashinSettlementRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AioCashinSettlementService l_service;
        WEB3AioCashinSettlementResponse l_response;
        
        try
        {
            l_service = 
                (WEB3AioCashinSettlementService)Services.getService(WEB3AioCashinSettlementService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AioCashinSettlementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__start決済__決済依頼サービスの取得に失敗しました",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = (WEB3AioCashinSettlementResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AioCashinSettlementResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__start決済__決済依頼処理処理に失敗しました",
                l_ex);
            return l_response;
        }

        log.exiting(l_strMethodName);
        
        return l_response;
    }
}
@
