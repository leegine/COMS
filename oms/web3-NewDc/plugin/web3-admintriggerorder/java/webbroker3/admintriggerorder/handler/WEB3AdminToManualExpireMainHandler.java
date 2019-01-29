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
filename	WEB3AdminToManualExpireMainHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・手動失効メインハンドラ(WEB3AdminToManualExpireMainHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/17　@余新敏(中訊) 新規作成
*/

package webbroker3.admintriggerorder.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToManualExpireMainService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (トリガー注文管理者・手動失効メインハンドラ)<BR>
 * トリガー注文管理者・手動失効メインハンドラクラス<BR>
 * 
 * @@author 余新敏
 * @@version 1.0
 */
public class WEB3AdminToManualExpireMainHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToManualExpireMainHandler.class);
    
    /**
     * @@roseuid 4419312B0290
     */
    public WEB3AdminToManualExpireMainHandler() 
    {
     
    }
    
    /**
     * (手動失効リクエスト)<BR>
     * （非同期）手動失効処理を起動する。<BR>
     * <BR>
     * トリガー注文管理者・手動失効メインサービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・手動失効メインリクエストオブジェクト<BR>
     * @@return WEB3AdminToManualLapseMainResponse
     * @@roseuid 440BCE6B0066
     */
    public WEB3AdminToManualLapseMainResponse manualExpireRequest(WEB3AdminToManualLapseMainRequest l_request) 
    {
        final String STR_METHOD_NAME = " manualExpireRequest(WEB3AdminToManualLapseMainRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminToManualLapseMainResponse l_response = null;
        WEB3AdminToManualExpireMainService l_service = null;
        
        try
        {
            //getトリガー注文管理者・手動失効メインサービス
            l_service = (WEB3AdminToManualExpireMainService)
                Services.getService(WEB3AdminToManualExpireMainService.class);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "トリガー注文管理者・手動失効メインサービスの取得に失敗しました。", 
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        try
        {
            //execute
            l_response = (WEB3AdminToManualLapseMainResponse) l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "（非同期）手動失効処理の起動に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            l_response = (WEB3AdminToManualLapseMainResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "（非同期）手動失効処理の起動に失敗しました。", l_response.errorInfo, l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
