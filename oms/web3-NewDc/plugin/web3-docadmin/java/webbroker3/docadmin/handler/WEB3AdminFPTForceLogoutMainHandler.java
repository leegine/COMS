head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウトメインハンドラ(WEB3AdminFPTForceLogoutMainHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainResponse;
import webbroker3.docadmin.message.WEB3AdminFPTForceLogoutMainRequest;
import webbroker3.docadmin.service.delegate.WEB3AdminFPTForceLogoutMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * 管理者 書面未承諾 強制ログアウトメインハンドラ
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutMainHandler implements MessageHandler
{
	
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutMainHandler.class);
    
    /**
     * @@roseuid 47DF467602D6
     */
    public WEB3AdminFPTForceLogoutMainHandler() 
    {
     
    }
    
    /**
     * exec強制ログアウト
     * 
     * 管理者 書面未承諾 強制ログアウトメイン処理を行う。 
     * 
     * 管理者 書面未承諾 強制ログアウトメインサービスImplを取得し、 
     * execute()メソッドをコールする。
     * @@param 管理者 書面未承諾 強制ログアウトメインリクエスト - 管理者 書面未承諾 
     * 強制ログアウトメインリクエスト
     * @@return 
     * docadmin.書面未承諾強制ログアウトサービスモデル（管理者）.メッセージ.WEB3AdminFP
     * TForceLogoutMainResponse
     * @@roseuid 47D64C040019
     */
    public WEB3AdminFPTForceLogoutMainResponse execForceLogout(WEB3AdminFPTForceLogoutMainRequest l_adminFPTForceLogoutMainRequest) 
    {
        {
            final String STR_METHOD_NAME =
                "execForceLogout(WEB3AdminFPTForceLogoutMainRequest)";
            log.entering(STR_METHOD_NAME);
            WEB3AdminFPTForceLogoutMainResponse l_response = null;
            WEB3AdminFPTForceLogoutMainService l_service = null;

            //管理者 書面未承諾 強制ログアウトメインサービスImplを取得
            try
            {
                l_service =
                    (WEB3AdminFPTForceLogoutMainService) Services.getService(
                    		WEB3AdminFPTForceLogoutMainService.class);
            } 
            catch (Exception l_exp)
            {
                l_response =
                    (WEB3AdminFPTForceLogoutMainResponse) l_adminFPTForceLogoutMainRequest.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error(
                	l_adminFPTForceLogoutMainRequest,
                    "Failed while acquiring WEB3AdminFPTForceLogoutMainService",
                    l_response.errorInfo,
                    l_exp);
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

            //execute()メソッドをコールする。
            try
            {
                l_response =
                    (WEB3AdminFPTForceLogoutMainResponse) l_service.execute(l_adminFPTForceLogoutMainRequest);
            } 
            catch (WEB3BaseException l_exp)
            {
                l_response =
                    (WEB3AdminFPTForceLogoutMainResponse) l_adminFPTForceLogoutMainRequest.createResponse();
                l_response.errorInfo = l_exp.getErrorInfo();
                log.error(l_adminFPTForceLogoutMainRequest, "Failed to access adminFPTForceLogoutMainRequest", l_exp);
            }
            catch (WEB3BaseRuntimeException l_exp)
            {
                l_response =
                    (WEB3AdminFPTForceLogoutMainResponse) l_adminFPTForceLogoutMainRequest.createResponse();
                l_response.errorInfo = l_exp.getErrorInfo();
                log.error(l_adminFPTForceLogoutMainRequest, "Failed to access adminFPTForceLogoutMainRequest", l_exp);
            }
            catch (Throwable l_t)
			{
                l_response =
                    (WEB3AdminFPTForceLogoutMainResponse) l_adminFPTForceLogoutMainRequest.createResponse();
                l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
                log.error("Unexpected Error.", l_t);            	
			}
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
    }
}
@
