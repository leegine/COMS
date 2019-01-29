head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ変更ハンドラ(WEB3AdminMCAdminPermGrpChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/29 屈陽 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者権限グループ変更ハンドラ)<BR>
 * 管理者メニュー制御管理者権限グループ変更ハンドラ<BR>
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermGrpChangeHandler implements MessageHandler 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpChangeHandler.class);
    
    /**
     * (入力画面表示)<BR>
     * 管理者権限グループ変更入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ変更サービスを取得し，executeメソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 41771F0902DE
     */
    public WEB3AdminMCAdminPermGrpChangeInputResponse inputScreenDisplay(WEB3AdminMCAdminPermGrpChangeInputRequest l_request) 
        throws WEB3BaseException 
    {
        String l_strMethodName = 
            "inputScreenDisplay(WEB3AdminMCAdminPermGrpChangeInputRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminMCAdminPermGrpChangeService l_service;
        WEB3AdminMCAdminPermGrpChangeInputResponse l_response;
        
        try
        {
            l_service = 
                (WEB3AdminMCAdminPermGrpChangeService)Services.getService(
                    WEB3AdminMCAdminPermGrpChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__fail in getService 入力画面表示__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__fail in excecute 入力画面表示__",
                l_ex);
            return l_response;
        }
      
        log.exiting(l_strMethodName);    
        
        return l_response;
    }
    
    /**
     * (権限グループ変更確認)<BR>
     * 権限グループ変更確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ変更サービスを取得し，executeメソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse
     * @@roseuid 41771F0902E0
     */
    public WEB3AdminMCAdminPermGrpChangeConfirmResponse permGrpChangeConfirm(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request) 
    {
        String l_strMethodName = 
            "permGrpChangeConfirm(WEB3AdminMCAdminPermGrpChangeConfirmRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminMCAdminPermGrpChangeService l_service;
        WEB3AdminMCAdminPermGrpChangeConfirmResponse l_response;
        
        try
        {
            l_service = 
                (WEB3AdminMCAdminPermGrpChangeService)Services.getService(
                    WEB3AdminMCAdminPermGrpChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__fail in getService 権限グループ変更確認__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__fail in excecute 権限グループ変更確認__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);  
          
        return l_response;
    }
    
    /**
     * (権限グループ変更完了)<BR>
     * 権限グループ変更完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ変更サービスを取得し，executeメソッドをコールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ変更完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse
     * @@roseuid 41771F0902EE
     */
    public WEB3AdminMCAdminPermGrpChangeCompleteResponse permGrpChangeComplete(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request) 
    {
        String l_strMethodName = 
            "permGrpChangeComplete(WEB3AdminMCAdminPermGrpChangeCompleteRequest l_request)";
        log.entering(l_strMethodName);
        
        WEB3AdminMCAdminPermGrpChangeService l_service;
        WEB3AdminMCAdminPermGrpChangeCompleteResponse l_response;
        
        try
        {
            l_service = 
                (WEB3AdminMCAdminPermGrpChangeService)Services.getService(
                    WEB3AdminMCAdminPermGrpChangeService.class);
        }
        catch (Exception l_ex)
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request, 
                "__fail in getService 権限グループ変更完了__",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        
        try
        {
            l_response = 
                (WEB3AdminMCAdminPermGrpChangeCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "__fail in excecute 権限グループ変更完了__",
                l_ex);
            return l_response;
        }
        
        log.exiting(l_strMethodName);  
        
        return l_response;
    }
}
@
