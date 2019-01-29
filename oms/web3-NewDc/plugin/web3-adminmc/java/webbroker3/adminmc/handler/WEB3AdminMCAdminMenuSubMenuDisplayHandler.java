head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御サブメニュー表示ハンドラ(WEB3AdminMCAdminMenuSubMenuDisplayHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;


/**
 * (管理者メニュー制御サブメニュー表示ハンドラ)<BR>
 * 管理者メニュー制御サブメニュー表示ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminMenuSubMenuDisplayHandler implements MessageHandler 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayHandler.class);
    
    /**
     * @@roseuid 4198642E01B5
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayHandler() 
    {
     
    }
    
    /**
     * (サブメニュー表示)<BR>
     * サブメニューで利用できる機@能カテゴリコード一覧を返却する。<BR>
     * <BR>
     * 管理者メニュー制御サブメニュー表示サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御サブメニュー表示リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse
     * @@roseuid 417769C70242
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayResponse subMenuDisplay(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".subMenuDisplay(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminMenuSubMenuDisplayResponse l_response = null;
        WEB3AdminMCAdminMenuSubMenuDisplayService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminMenuSubMenuDisplayService)Services.getService(WEB3AdminMCAdminMenuSubMenuDisplayService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御サブメニュー表示サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "サブメニューで利用できる機@能カテゴリコード一覧に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }

}
@
