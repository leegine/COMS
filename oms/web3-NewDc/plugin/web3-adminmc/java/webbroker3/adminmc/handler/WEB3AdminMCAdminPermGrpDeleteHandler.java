head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpDeleteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ削除ハンドラ(WEB3AdminMCAdminPermGrpDeleteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;


/**
 * (管理者メニュー制御管理者権限グループ削除ハンドラ)<BR>
 * 管理者メニュー制御管理者権限グループ削除ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpDeleteHandler implements MessageHandler 
{

    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpDeleteHandler.class);
    
    /**
     * @@roseuid 4198642F01B5
     */
    public WEB3AdminMCAdminPermGrpDeleteHandler() 
    {
     
    }
    
    /**
     * (権限グループ削除確認)<BR>
     * 権限グループ削除確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ削除サービスを取得し，executeメソッドをコー・
     * する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ削除確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse
     * @@roseuid 4177370E02FD
     */
    public WEB3AdminMCAdminPermGrpDeleteConfirmResponse permGrpDeleteConfirm(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDeleteConfirm(WEB3AdminMCAdminPermGrpDeleteConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpDeleteConfirmResponse l_response = null;
        WEB3AdminMCAdminPermGrpDeleteService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpDeleteService)Services.getService(WEB3AdminMCAdminPermGrpDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ削除サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDeleteConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者権限グループ削除確認に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (権限グループ削除完了)<BR>
     * 権限グループ削除完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ削除サービスを取得し，executeメソッドをコー・
     * する。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ削除完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse
     * @@roseuid 4177370E02FF
     */
    public WEB3AdminMCAdminPermGrpDeleteCompleteResponse permGrpDeleteComplete(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDeleteComplete(WEB3AdminMCAdminPermGrpDeleteCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpDeleteCompleteResponse l_response = null;
        WEB3AdminMCAdminPermGrpDeleteService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpDeleteService)Services.getService(WEB3AdminMCAdminPermGrpDeleteService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ削除サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDeleteCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者権限グループ削除完了に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
