head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.49.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoManagementDetailsHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者IPO銘柄管理・詳細ハンドラ(WEB3AdminIpoManagementDetailsHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/19 李頴淵 新規作成
*/

package webbroker3.ipo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者IPO銘柄管理・詳細ハンドラ)<BR>
 * 管理者IPO銘柄管理・詳細ハンドラクラス
 * 
 * @@author 李頴淵
 * @@version 1.0
 */
public class WEB3AdminIpoManagementDetailsHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoManagementDetailsHandler.class);
    
    /**
     * @@roseuid 4112EE5700FE
     */
    public WEB3AdminIpoManagementDetailsHandler() 
    {
     
    }
    
    /**
     * (銘柄管理)<BR>
     * 管理者IPO銘柄管理処理<BR>
     * <BR>
     * 管理者IPO銘柄管理・詳細サービスを取得し、execute()メソッドをコールする。<BR>
     * @@roseuid 40C66718028D
     */
    public WEB3AdminIPOManagementResponse productManagement(WEB3AdminIPOManagementRequest l_request) 
    {
        final String STR_METHOD_NAME = " productManagement(WEB3AdminIPOManagementRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOManagementResponse l_response = null;
        WEB3AdminIpoManagementDetailsService l_service = null;
        
        //管理者IPO銘柄管理・詳細サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoManagementDetailsService)Services.getService(
                    WEB3AdminIpoManagementDetailsService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄管理・詳細サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄管理・詳細サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOManagementResponse)l_request.createResponse();
//            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者IPO銘柄管理・詳細サービスに失敗しました。",
                l_ex);
            return l_response; 
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (銘柄詳細表示)<BR>
     * 管理者IPO銘柄詳細処理<BR>
     * <BR>
     * 管理者IPO銘柄管理・詳細サービスを取得し、execute()メソッドをコールする。<BR>
     * @@roseuid 40C6672A00C8
     */
    public WEB3AdminIPOProductDetailsResponse productDetailsIndication(WEB3AdminIPOProductDetailsRequest l_request) 
    {
        final String STR_METHOD_NAME = " productDetailsIndication(WEB3AdminIPOProductDetailsRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminIPOProductDetailsResponse l_response = null;
        WEB3AdminIpoManagementDetailsService l_service = null;
        
        //管理者IPO銘柄管理・詳細サービスを取得
        try
        {
            l_service =
                (WEB3AdminIpoManagementDetailsService)Services.getService(
                    WEB3AdminIpoManagementDetailsService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "管理者IPO銘柄管理・詳細サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            return l_response;      
        }
        
        //管理者IPO銘柄管理・詳細サービス.execute()メソッドをコールする
        try
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_service.execute(
                    l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3AdminIPOProductDetailsResponse)l_request.createResponse();
//            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request,
                "管理者IPO銘柄管理・詳細サービスに失敗しました。",
                l_ex);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
     
    }
}
@
