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
filename	WEB3BondAutoExecuteHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定ハンドラ(WEB3BondAutoExecuteHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 齊珂 (中訊) 新規作成
*/

package webbroker3.bd.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券自動約定ハンドラ)<BR>
 * 債券自動約定ハンドラクラス<BR>
 * 
 * @@author 齊珂(中訊)
 * @@version 1.0 
 */
public class WEB3BondAutoExecuteHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteHandler.class);
    
    public WEB3BondAutoExecuteHandler() 
    {
     
    }
    
    /**
     * (complete自動約定)<BR>
     * complete自動約定 <BR>
     * <BR>
     * 　@債券自動約定サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     * @@return WEB3BondAutoExecResponse
     */
    public WEB3BondAutoExecResponse completeAutoExecute(WEB3BondAutoExecRequest l_request)
    {
        final String STR_METHOD_NAME = " completeAutoExecute(WEB3BondAutoExecRequest)";
        log.entering(STR_METHOD_NAME);
        
        //債券自動約定サービスを取得
        WEB3BondAutoExecuteService l_service = null;
        WEB3BondAutoExecResponse l_response = null;
        try
        {
            l_service =(WEB3BondAutoExecuteService) 
                Services.getService(WEB3BondAutoExecuteService.class);
        }
        catch(Exception l_ex)
        {
            l_response = (WEB3BondAutoExecResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "債券自動約定サービスを取得に失敗しました。",
                l_response.errorInfo,
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //execute()メソッドをコールする。
        try
        {
            l_response = (WEB3BondAutoExecResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            l_response = (WEB3BondAutoExecResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, 
                "complete自動約定が失敗しました。", 
                l_ex.getErrorInfo(), 
                l_ex);
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
}
@
