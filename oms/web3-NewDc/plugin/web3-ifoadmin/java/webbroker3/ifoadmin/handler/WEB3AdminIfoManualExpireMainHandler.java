head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoManualExpireMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・先物OP手動失効メインハンドラ(WEB3AdminIfoManualExpireMainHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・先物OP手動失効メインハンドラ)<BR>
 * 管理者・先物OP手動失効メインハンドラクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminIfoManualExpireMainHandler implements MessageHandler 
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log = 
         WEB3LogUtility.getInstance(WEB3AdminIfoManualExpireMainHandler.class);
     
    /**
     * @@roseuid 447AC0CF02EE
     */
    public WEB3AdminIfoManualExpireMainHandler() 
    {
     
    }
    
    /**
     * (手動失効リクエスト)<BR>
     * （非同期）手動失効処理を起動する。<BR>
     * <BR>
     * 管理者・先物OP手動失効メインサービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者・先物OP手動失効メインリクエストオブジェクト
     * @@return webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainResponse
     * @@roseuid 446934A70279
     */
    public WEB3AdminIfoManualLapseMainResponse manualExpireRequest(WEB3AdminIfoManualLapseMainRequest l_request) 
    {
        final String STR_METHOD_NAME =
            "manualExpireRequest(WEB3AdminIfoManualLapseMainRequest)";
        log.entering(STR_METHOD_NAME);
        WEB3AdminIfoManualLapseMainResponse l_response = null;
        WEB3AdminIfoManualExpireMainService l_service = null;

        try
        {
            l_service =
                (WEB3AdminIfoManualExpireMainService) Services.getService(
                        WEB3AdminIfoManualExpireMainService.class);
        } 
        catch (Exception l_exp)
        {
            l_response =
                (WEB3AdminIfoManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "Failed while acquiring WEB3AdminIfoManualExpireMainService",
                l_response.errorInfo,
                l_exp);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        try
        {
            l_response =
                (WEB3AdminIfoManualLapseMainResponse) l_service.execute(l_request);
        } 
        catch (WEB3BaseException l_exp)
        {
            l_response =
                (WEB3AdminIfoManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Failed to access manualExpireRequest", l_exp);
        }
        catch (WEB3BaseRuntimeException l_exp)
        {
            l_response =
                (WEB3AdminIfoManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_exp.getErrorInfo();
            log.error(l_request, "Failed to access manualExpireRequest", l_exp);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
