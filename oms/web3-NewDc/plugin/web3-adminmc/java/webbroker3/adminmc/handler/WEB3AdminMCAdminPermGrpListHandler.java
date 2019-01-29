head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.41.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermGrpListHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御管理者権限グループ一覧ハンドラ(WEB3AdminMCAdminPermGrpListHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 Tongwei(中訊) 新規作成
*/

package webbroker3.adminmc.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者メニュー制御管理者権限グループ一覧ハンドラ)<BR>
 * 管理者メニュー制御管理者権限グループ一覧ハンドラ<BR>
 * 
 * @@author Tongwei
 * @@version 1.0  
 */
public class WEB3AdminMCAdminPermGrpListHandler implements MessageHandler 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminPermGrpListHandler.class);
    
    /**
     * @@roseuid 4198642F00AB
     */
    public WEB3AdminMCAdminPermGrpListHandler() 
    {
     
    }
    
    /**
     * (権限グループ一覧表示)<BR>
     * 権限グループ一覧表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ一覧サービスを取得し、execute()メソッドをコ・
     * ルする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ一覧リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse
     * @@roseuid 41774B2D02CE
     */
    public WEB3AdminMCAdminPermGrpListResponse permGrpListDisplay(WEB3AdminMCAdminPermGrpListRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpListDisplay(WEB3AdminMCAdminPermGrpListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminPermGrpListResponse l_response = null;
        WEB3AdminMCAdminPermGrpListService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpListService)Services.getService(WEB3AdminMCAdminPermGrpListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ一覧サービスImplの取得に失敗しました", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpListResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpListResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "権限グループ一覧表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
    
    /**
     * (権限グループ詳細表示)<BR>
     * 権限グループ詳細表示処理を実施する。<BR>
     * <BR>
     * 管理者メニュー制御管理者権限グループ一覧サービスを取得し、execute()メソッドをコ・
     * ルする。<BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御管理者権限グループ詳細リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse
     * @@roseuid 41774B2D02D0
     */
    public WEB3AdminMCAdminPermGrpDetailsResponse permGrpDetailDisplay(WEB3AdminMCAdminPermGrpDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME =
                getClass().getName() + ".permGrpDetailDisplay(WEB3AdminMCAdminPermGrpDetailsRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminPermGrpDetailsResponse l_response = null;
        WEB3AdminMCAdminPermGrpListService l_service = null;
             
        try
        {
            l_service= (WEB3AdminMCAdminPermGrpListService)Services.getService(WEB3AdminMCAdminPermGrpListService.class); 
        }
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "管理者メニュー制御管理者権限グループ一覧サービスImplの取得に失敗しました", 
                l_response.errorInfo, l_ex);
            return l_response;
        }
                 
        try
        {
            //execute()メソッドをコールする
            l_response = (WEB3AdminMCAdminPermGrpDetailsResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminMCAdminPermGrpDetailsResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "権限グループ詳細表示処理に失敗しました",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);           
        return l_response;
    }
}
@
