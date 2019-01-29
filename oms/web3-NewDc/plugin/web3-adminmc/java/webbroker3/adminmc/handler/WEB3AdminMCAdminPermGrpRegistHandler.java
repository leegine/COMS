head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ登録ハンドラ(WEB3AdminMCAdminPermGrpRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;

/**
 * (管理者メニュー制御管理者権限グループ登録ハンドラ)<BR>
 * 管理者メニュー制御管理者権限グループ登録ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminPermGrpRegistHandler implements MessageHandler 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpRegistHandler.class);
     
    /**
     * @@roseuid 4198642F02DE
     */
    public WEB3AdminMCAdminPermGrpRegistHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 管理者権限グループ入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ登録サービスを取得し，executeメソッドをコー・
     * する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録入力リクエストデータオブジェクト<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4175EF8903BB
     */
    public WEB3AdminMCAdminPermGrpRegistInputResponse inputScreenDisplay(WEB3AdminMCAdminPermGrpRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " inputScreenDisplay(WEB3AdminMCAdminPermGrpRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistInputResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ登録サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpRegistInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者権限グループ入力画面表示に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (権限グループ登録確認)<BR>
     * 権限グループ登録確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ登録サービスを取得し，executeメソッドをコー・
     * する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse
     * @@roseuid 4175EF8903CB
     */
    public WEB3AdminMCAdminPermGrpRegistConfirmResponse permGrpRegistConfirm(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " permGrpRegistConfirm(WEB3AdminMCAdminPermGrpRegistConfirmRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistConfirmResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ登録サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "権限グループ登録確認に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (権限グループ登録完了)<BR>
     * 権限グループ登録完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ登録サービスを取得し，executeメソッドをコー・
     * する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者グループ登録完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse
     * @@roseuid 4175EF8903CD
     */
    public WEB3AdminMCAdminPermGrpRegistCompleteResponse permGrpRegistComplete(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + " permGrpRegistComplete(WEB3AdminMCAdminPermGrpRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminPermGrpRegistCompleteResponse l_response = null;
        WEB3AdminMCAdminPermGrpRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpRegistService)Services.getService(WEB3AdminMCAdminPermGrpRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ登録サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "権限グループ登録完了に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
