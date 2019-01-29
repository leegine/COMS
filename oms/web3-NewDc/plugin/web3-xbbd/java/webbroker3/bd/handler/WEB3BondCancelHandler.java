head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消ハンドラ(WEB3BondCancelHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/22 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券取消ハンドラ)<BR>
 * 債券取消ハンドラ
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondCancelHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondCancelHandler.class);
    
    /**
     * @@roseuid 44E3362F0399
     */
    public WEB3BondCancelHandler() 
    {
     
    }
    
    /**
     * (債券取消確認)<BR>
     * 債券取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3BondCancelConfirmResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondCancelConfirmResponse confirmCancel(WEB3BondCancelConfirmRequest l_request) 
    {
        final String STR_METHOD_NAME = "confirmCancel(WEB3BondCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondCancelConfirmResponse l_response = null;
        WEB3BondCancelService l_service = null;
        try
        {
            l_service =
                (WEB3BondCancelService)Services.getService(
                    WEB3BondCancelService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondCancelConfirmResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
    
    /**
     * (債券取消完了)<BR>
     * 債券取消サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3BondCancelCompleteResponse
     * @@roseuid 44B61AB403B8
     */
    public WEB3BondCancelCompleteResponse completeCancel(
        WEB3BondCancelCompleteRequest l_request) 
    {
        final String STR_METHOD_NAME = "completeCancel(WEB3BondCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondCancelCompleteResponse l_response = null;
        WEB3BondCancelService l_service = null;
        try
        {
            l_service =
                (WEB3BondCancelService)Services.getService(
                        WEB3BondCancelService.class);
        }
        catch (Exception l_ex)
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券取消サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        //execute()メソッドをコールする。
        try
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3BondCancelCompleteResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "execute()メソッドをコールすることが失敗しました。", 
                l_ex);
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;        
    }
 
}
@
