head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPwdLockCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者パスワードロック解除ハンドラ(WEB3AdminMCAdminPwdLockCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;


/**
 * (管理者メニュー制御管理者パスワードロック解除ハンドラ)<BR>
 * 管理者メニュー制御管理者パスワードロック解除ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPwdLockCancelHandler implements MessageHandler 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPwdLockCancelHandler.class);
    
    /**
     * @@roseuid 4198642E0280
     */
    public WEB3AdminMCAdminPwdLockCancelHandler() 
    {
     
    }
    
    /**
     * (解除確認)<BR>
     * 管理者パスワードロック解除確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者パスワードロック解除サービスを取得し、execute()メソッド・
     * コールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御ﾊﾟｽﾜｰﾄﾞ解除確認ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse
     * @@roseuid 417DE4AA0154
     */
    public WEB3AdminMCAdminPwdLockCancelConfirmResponse cancelConfirm(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelConfirm(WEB3AdminMCAdminPwdLockCancelConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPwdLockCancelConfirmResponse l_response = null;
        WEB3AdminMCAdminPwdLockCancelService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPwdLockCancelService)Services.getService(WEB3AdminMCAdminPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者パスワードロック解除サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPwdLockCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者パスワードロック解除確認に失敗しました.",
                l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

    
    /**
     * (解除完了)<BR>
     * 管理者パスワードロック解除完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者パスワードロック解除サービスを取得し、execute()メソッド・
     * コールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御ﾊﾟｽﾜｰﾄﾞ解除完了ﾘｸｴｽﾄデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse
     * @@roseuid 417DE4AA0163
     */
    public WEB3AdminMCAdminPwdLockCancelCompleteResponse cancelComplete(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".cancelComplete(WEB3AdminMCAdminPwdLockCancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPwdLockCancelCompleteResponse l_response = null;
        WEB3AdminMCAdminPwdLockCancelService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPwdLockCancelService)Services.getService(WEB3AdminMCAdminPwdLockCancelService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者パスワードロック解除サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPwdLockCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者パスワードロック解除完了に失敗しました.",
                l_ex);
            log.exiting(STR_METHOD_NAME);      
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
