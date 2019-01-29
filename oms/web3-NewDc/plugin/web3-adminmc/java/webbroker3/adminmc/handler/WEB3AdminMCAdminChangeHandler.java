head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminChangeHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : (管理者メニュー制御管理者変更ハンドラ(WEB3AdminMCAdminChangeHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 王敏 (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;

/**
 * (管理者メニュー制御管理者変更ハンドラ)<BR>
 * 管理者メニュー制御管理者変更ハンドラ<BR>
 * @@author 王敏
 * @@version 1.0
 */
public class WEB3AdminMCAdminChangeHandler implements MessageHandler 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminChangeHandler.class);

    /**
     * @@roseuid 419864310109
     */
    public WEB3AdminMCAdminChangeHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 管理者変更入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更入力リクエストデータオブジェクト<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 417DCA550192
     */
    public WEB3AdminMCAdminChangeInputResponse inputScreenDisplay(WEB3AdminMCAdminChangeInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCAdminChangeInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeInputResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "管理者メニュー制御管理者変更サービスの取得に失敗しました", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminChangeInputResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "管理者メニュー制御管理者変更入力表示処理に失敗しました",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (管理者変更確認)<BR>
     * 管理者変更確認処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更確認リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse
     * @@roseuid 417DCA550194
     */
    public WEB3AdminMCAdminChangeConfirmResponse adminChangeConfirm(WEB3AdminMCAdminChangeConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 getClass().getName() + ".adminChangeConfirm(WEB3AdminMCAdminChangeConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeConfirmResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "管理者メニュー制御管理者変更サービスの取得に失敗しました", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminChangeConfirmResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "管理者メニュー制御管理者変更確認表示処理に失敗しました",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (管理者変更完了)<BR>
     * 管理者変更完了処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者変更サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者変更完了リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse
     * @@roseuid 417DCA5501A2
     */
    public WEB3AdminMCAdminChangeCompleteResponse adminChangeComplete(WEB3AdminMCAdminChangeCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME =
                 getClass().getName() + ".adminChangeComplete(WEB3AdminMCAdminChangeCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminChangeCompleteResponse l_response = null;
        WEB3AdminMCAdminChangeService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminChangeService)Services.getService(WEB3AdminMCAdminChangeService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, 
                        "管理者メニュー制御管理者変更サービスの取得に失敗しました", 
                        l_response.errorInfo,
                        l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminChangeCompleteResponse)l_service.execute(l_request);

        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminChangeCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error( l_request,
                          "管理者メニュー制御管理者変更完了表示処理に失敗しました",
                         l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
}
@
