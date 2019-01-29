head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.42.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者一覧ハンドラ(WEB3AdminMCAdminListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;


/**
 * (管理者メニュー制御管理者一覧ハンドラ)<BR>
 * 管理者メニュー制御管理者一覧ハンドラ<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminListHandler implements MessageHandler 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminListHandler.class);
    
    /**
     * @@roseuid 4198642E038A
     */
    public WEB3AdminMCAdminListHandler() 
    {
     
    }
    
    /**
     * (入力画面表示)<BR>
     * 管理者一覧条件入力画面表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧入力リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse
     * @@roseuid 417DEF8C02F9
     */
    public WEB3AdminMCAdminListInputResponse inputScreenDisplay(WEB3AdminMCAdminListInputRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".inputScreenDisplay(WEB3AdminMCAdminListInputRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminListInputResponse l_response = null;
        WEB3AdminMCAdminListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminListService)Services.getService(WEB3AdminMCAdminListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者一覧サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminListInputResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminListInputResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者一覧条件入力画面表示に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 
    }
    
    /**
     * (管理者一覧表示)<BR>
     * 管理者一覧表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者一覧サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者一覧リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminListResponse
     * @@roseuid 417DEF8C0309
     */
    public WEB3AdminMCAdminListResponse adminListDisplay(WEB3AdminMCAdminListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".adminListDisplay(WEB3AdminMCAdminListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminMCAdminListResponse l_response = null;
        WEB3AdminMCAdminListService l_service = null;     
        try
        {
            l_service= (WEB3AdminMCAdminListService)Services.getService(WEB3AdminMCAdminListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者一覧サービスImplの取得に失敗しました.", l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者一覧表示に失敗しました.",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);      
        
        return l_response; 

    }
}
@
