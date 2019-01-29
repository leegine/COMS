head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者登録ハンドラ(WEB3AdminMCAdminRegistHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistService;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteRequest;

/**
 * (管理者メニュー制御管理者登録ハンドラ)<BR>
 * 管理者メニュー制御管理者登録ハンドラ<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminRegistHandler implements MessageHandler 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistHandler.class);

    /**
     * @@roseuid 41986430033C
     */
    public WEB3AdminMCAdminRegistHandler() 
    {
      
    }
    
    /**
     * (入力画面表示)<BR>
     * 管理者登録入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者登録サービスを取得し、execute()メソッドをコールする。<BR
     * >
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417CB7520336
     */
    public WEB3AdminMCAdminRegistInputResponse inputScreenDisplay(WEB3AdminMCAdminRegistInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCAdminRegistInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminRegistInputResponse l_response = null;
        WEB3AdminMCAdminRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminRegistService)Services.getService(WEB3AdminMCAdminRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者登録サービスの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminRegistInputResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminRegistInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者メニュー制御管理者登録入力表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (管理者登録確認)<BR>
     * 管理者登録確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者登録サービスを取得し、execute()メソッドをコールする。<BR
     * >
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse
     * @@roseuid 417CB7520345
     */
    public WEB3AdminMCAdminRegistConfirmResponse adminRegistConfirm(WEB3AdminMCAdminRegistConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminRegistConfirm(WEB3AdminMCAdminRegistConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminRegistConfirmResponse l_response = null;
        WEB3AdminMCAdminRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminRegistService)Services.getService(WEB3AdminMCAdminRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者登録サービスの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminRegistConfirmResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminRegistConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者メニュー制御管理者登録確認表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response;
    }
    
    /**
     * (管理者登録完了)<BR>
     * 管理者登録完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者登録サービスを取得し、execute()メソッドをコールする。<BR
     * >
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者登録完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse
     * @@roseuid 417CB7520347
     */
    public WEB3AdminMCAdminRegistCompleteResponse adminRegistComplete(WEB3AdminMCAdminRegistCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminRegistComplete(WEB3AdminMCAdminRegistCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminRegistCompleteResponse l_response = null;
        WEB3AdminMCAdminRegistService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminRegistService)Services.getService(WEB3AdminMCAdminRegistService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者登録サービスの取得に失敗しました", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminRegistCompleteResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminRegistCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者メニュー制御管理者登録完了表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response;
    }
}
@
