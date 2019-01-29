head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecutionNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文受付出来通知ハンドラ(WEB3FeqOrderAcceptExecutionNotifyHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 齊珂 (中訊) 新規作成
*/

package webbroker3.feq.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文受付出来通知ハンドラ)<BR>
 * 外国株式注文受付出来通知ハンドラクラス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptExecutionNotifyHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecutionNotifyHandler.class);
    
    /**
     * (注文受付出来通知)<BR>
     * 外国株式注文受付出来通知サービスを取得し、execute()メソッドをコールする。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3FeqOrderAcceptExecNotifyResponse
     * @@roseuid 4214980A032E
     */
    public WEB3FeqOrderAcceptExecNotifyResponse orderAcceptExecNotify(
    	WEB3FeqOrderAcceptExecNotifyRequest l_request)
    {
        final String STR_METHOD_NAME = " orderAcceptExecNotify(" +
        		"WEB3FeqOrderAcceptExecNotifyRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3FeqOrderAcceptExecNotifyResponse l_response = null;
        WEB3FeqOrderAcceptExecutionNotifyService l_service = null;
        
        try
        {            
            //get管理者外国株式注文受付取消認証サービス
            l_service = (WEB3FeqOrderAcceptExecutionNotifyService)
                Services.getService(WEB3FeqOrderAcceptExecutionNotifyService.class);
        }
        catch (Exception l_ex)
        {
            //サービスで例外が発生した場合は、
            //エラー情報をレスポンスメッセージに設定する
            l_response =
                (WEB3FeqOrderAcceptExecNotifyResponse)l_request.createResponse();
            l_response.errorInfo = 
                WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "外国株式注文受付出来通知に失敗しました。",
                l_response.errorInfo,
                l_ex);
            return l_response;
        }
        try
        {
            l_response = (WEB3FeqOrderAcceptExecNotifyResponse)l_service.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            l_response =
                (WEB3FeqOrderAcceptExecNotifyResponse) l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(
                l_request, 
                "外国株式注文受付出来通知に失敗しました。", 
                l_ex);
            return l_response;
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
