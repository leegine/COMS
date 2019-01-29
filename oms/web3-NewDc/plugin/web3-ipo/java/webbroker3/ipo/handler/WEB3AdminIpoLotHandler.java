head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO抽選割当ハンドラ(WEB3AdminIpoLotHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 鄭徳懇 (中訊) 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO抽選割当ハンドラ)<BR>
 * 管理者IPO抽選割当ハンドラクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminIpoLotHandler implements MessageHandler
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotHandler.class);
    
    /**
     * @@roseuid 4112EEA7253C
     */
    public WEB3AdminIpoLotHandler()
    {
        
    }
    
    /**
     * (IPO抽選割当入力)<BR>
     * 管理者IPO抽選割当入力処理<BR>
     * <BR>
     * 管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO抽選割当入力リクエストオブジェクト。
     * @@return WEB3AdminIPOLotInputResponse
     * */
    public WEB3AdminIPOLotInputResponse getLotInput(WEB3AdminIPOLotInputRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotInput(WEB3AdminIPOLotInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotInputResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService) Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選割当サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当入力処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotInputResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当入力処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (IPO抽選割当確認)<BR>
     * 管理者IPO抽選割当確認処理<BR>
     * <BR>
     * 管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO抽選割当確認リクエストオブジェクト。
     * @@return WEB3AdminIPOLotConfirmResponse
     * */
    public WEB3AdminIPOLotConfirmResponse getLotConfirm(WEB3AdminIPOLotConfirmRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotConfirm(WEB3AdminIPOLotConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotConfirmResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService)Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選割当サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当確認処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotConfirmResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当確認処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (IPO抽選割当完了)<BR>
     * 管理者IPO抽選割当完了処理<BR>
     * <BR>
     * 管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者IPO抽選割当完了リクエストオブジェクト。
     * @@return WEB3AdminIPOLotCompleteResponse
     * */
    public WEB3AdminIPOLotCompleteResponse getLotComplete(WEB3AdminIPOLotCompleteRequest l_request)
    {
        final String STR_METHOD_NAME = " getLotComplete(WEB3AdminIPOLotCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOLotCompleteResponse l_response = null;
        WEB3AdminIpoLotService l_service = null;
        
        try
        {
            l_service =
                (WEB3AdminIpoLotService) Services.getService(
                    WEB3AdminIpoLotService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO抽選割当サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        //管理者IPO抽選割当サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当完了処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIPOLotCompleteResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "管理者IPO抽選割当完了処理の実施に失敗しました。",
                l_response.errorInfo, 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //レスポンスオブジェクトを返却する。
        return l_response;
    } 
}
@
