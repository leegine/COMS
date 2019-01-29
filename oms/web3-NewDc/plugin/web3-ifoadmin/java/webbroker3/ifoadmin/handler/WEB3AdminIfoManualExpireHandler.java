head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効ハンドラ(WEB3AdminIfoManualExpireHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/

package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効ハンドラ)<BR>
 * 管理者・先物OP手動失効ハンドラクラス<BR>
 * <BR>
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireHandler implements MessageHandler 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireHandler.class);
    
    /**
     * @@roseuid 447AC0CF01F4
     */
    public WEB3AdminIfoManualExpireHandler() 
    {
     
    }
    
    /**
     * (get入力画面)<BR>
     * 先物OP手動失効入力画面表示処理を行う。<BR>
     * <BR>
     * 管理者・先物OP手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効入力リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputResponse
     * @@roseuid 44692FC10116
     */
    public WEB3AdminIfoManualLapseInputResponse getInputScreen(WEB3AdminIfoManualLapseInputRequest l_request) 
    {
        final String STR_METHOD_NAME = 
            "getInputScreen(WEB3AdminIfoManualLapseInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseInputResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //管理者・先物OP手動失効サービスImplを取得し
            l_service = (WEB3AdminIfoManualExpireService)Services.getService(WEB3AdminIfoManualExpireService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・先物OP手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効入力画面表示処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効の処理ステータスの実施に失敗しました。",
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
     * 先物OP手動失効確認処理を行う。<BR>
     * <BR>
     * 管理者・先物OP手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効確認リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmResponse
     * @@roseuid 446930230264
     */
    public WEB3AdminIfoManualLapseConfirmResponse confirmManualExpire(WEB3AdminIfoManualLapseConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmManualExpire(WEB3AdminIfoManualLapseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseConfirmResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //管理者・先物OP手動失効サービスImplを取得し
            l_service = (WEB3AdminIfoManualExpireService)Services.getService(WEB3AdminIfoManualExpireService.class);
        }
        // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・先物OP手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効確認処理の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効の処理ステータスの実施に失敗しました。",
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
     * 先物OP手動失効起動を行う。<BR>
     * <BR>
     * 管理者・先物OP手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効処理起動リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunResponse
     * @@roseuid 4469305701C2
     */
    public WEB3AdminIfoManualLapseRunResponse runManualExpire(WEB3AdminIfoManualLapseRunRequest l_request) 
    {
        final String STR_METHOD_NAME = " runManualExpire(WEB3AdminIfoManualLapseRunRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseRunResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //管理者・先物OP手動失効サービスImplを取得し
            l_service = 
                (WEB3AdminIfoManualExpireService)Services.getService(
                    WEB3AdminIfoManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・先物OP手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者・先物OP手動失効起動の実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseRunResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効の処理ステータスの実施に失敗しました。",
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
     * 先物OP手動失効の処理ステータスを確認する。<BR>
     * <BR>
     * 管理者・先物OP手動失効サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効処理ステータス確認リクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusResponse
     * @@roseuid 446930AA03B9
     */
    public WEB3AdminIfoManualLapseStatusResponse confirmStatus(
        WEB3AdminIfoManualLapseStatusRequest l_request) 
    {
        final String STR_METHOD_NAME = " confirmStatus(WEB3AdminIfoManualLapseStatusRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIfoManualLapseStatusResponse l_response = null;
        WEB3AdminIfoManualExpireService l_service = null;
        
        try
        {            
            //管理者・先物OP手動失効サービスImplを取得し
            l_service = 
                (WEB3AdminIfoManualExpireService)Services.getService(
                    WEB3AdminIfoManualExpireService.class);
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者・先物OP手動失効サービスの取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_service.execute(l_request);
        }        
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効の処理ステータスの実施に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminIfoManualLapseStatusResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "先物OP手動失効の処理ステータスの実施に失敗しました。",
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
