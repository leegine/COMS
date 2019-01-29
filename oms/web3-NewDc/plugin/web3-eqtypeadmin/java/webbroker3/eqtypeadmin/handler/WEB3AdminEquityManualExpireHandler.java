head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityManualExpireHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式手動失効ハンドラ(WEB3AdminEquityManualExpireHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@肖志偉(中訊) 新規作成
*/

package webbroker3.eqtypeadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・株式手動失効ハンドラ)<BR>
 * 管理者・株式手動失効ハンドラクラス<BR>
 * <BR>
 * @@author 肖志偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminEquityManualExpireHandler implements MessageHandler 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityManualExpireHandler.class);
    
    /**
     * @@roseuid 447AC0CF01F4
     */
    public WEB3AdminEquityManualExpireHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 株式手動失効入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者・株式手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効入力リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse
     * @@roseuid 44692FC10116
     */
    public WEB3AdminEquityManualLapseInputResponse getInputScreen(WEB3AdminEquityManualLapseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminEquityManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityManualLapseInputResponse l_response = null;
        WEB3AdminEquityManualExpireService l_service = null;
        
        try
        {            
            //管理者・株式手動失効サービスImplを取得し
            l_service = (WEB3AdminEquityManualExpireService)Services.getService(WEB3AdminEquityManualExpireService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminEquityManualLapseInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効の処理ステータスの実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm手動失効)<BR>
     * 株式手動失効確認処理を行う。<BR>
     * <BR>
     * 管理者・株式手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効確認リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse
     * @@roseuid 446930230264
     */
    public WEB3AdminEquityManualLapseConfirmResponse confirmManualExpire(WEB3AdminEquityManualLapseConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManualExpire(WEB3AdminEquityManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityManualLapseConfirmResponse l_response = null;
        WEB3AdminEquityManualExpireService l_service = null;
        
        try
        {            
            //管理者・株式手動失効サービスImplを取得し
            l_service = (WEB3AdminEquityManualExpireService)Services.getService(WEB3AdminEquityManualExpireService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminEquityManualLapseConfirmResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効の処理ステータスの実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (run手動失効)<BR>
     * 株式手動失効起動を行う。<BR>
     * <BR>
     * 管理者・株式手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効処理起動リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse
     * @@roseuid 4469305701C2
     */
    public WEB3AdminEquityManualLapseRunResponse runManualExpire(WEB3AdminEquityManualLapseRunRequest l_request) 
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminEquityManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityManualLapseRunResponse l_response = null;
        WEB3AdminEquityManualExpireService l_service = null;
        
        try
        {            
            //管理者・株式手動失効サービスImplを取得し
            l_service = 
                (WEB3AdminEquityManualExpireService)Services.getService(
                    WEB3AdminEquityManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminEquityManualLapseRunResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者・株式手動失効起動の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効の処理ステータスの実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (confirm処理ステータス)<BR>
     * 株式手動失効の処理ステータスを確認する。<BR>
     * <BR>
     * 管理者・株式手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・株式手動失効処理ステータス確認リクエストオブジェクト
     * @@return webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse
     * @@roseuid 446930AA03B9
     */
    public WEB3AdminEquityManualLapseStatusResponse confirmStatus(
        WEB3AdminEquityManualLapseStatusRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmStatus(WEB3AdminEquityManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityManualLapseStatusResponse l_response = null;
        WEB3AdminEquityManualExpireService l_service = null;
        
        try
        {            
            //管理者・株式手動失効サービスImplを取得し
            l_service = 
                (WEB3AdminEquityManualExpireService)Services.getService(
                    WEB3AdminEquityManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・株式手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminEquityManualLapseStatusResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効の処理ステータスの実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminEquityManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "株式手動失効の処理ステータスの実施に失敗しました。",
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
