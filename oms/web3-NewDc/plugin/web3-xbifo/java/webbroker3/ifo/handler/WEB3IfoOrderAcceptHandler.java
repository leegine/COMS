head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright       : (株)大和総研 証券ソリューションシステム第二部
File Name       :先物OP注文受付ハンドラクラス(WEB3IfoOrderAcceptHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22   王暁傑 (Sinocom) 新規作成 
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.ifo.message.WEB3IfoOrderAcceptRequest;
import webbroker3.ifo.message.WEB3IfoOrderAcceptResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文受付ハンドラ)<BR>
 * 先物OP注文受付ハンドラクラス<BR>
 * @@author  : 王暁傑
 * @@version : 1.0
 */
public class WEB3IfoOrderAcceptHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOrderAcceptHandler.class);
            
    /**
     * @@roseuid 40C07553031C
     */
    public WEB3IfoOrderAcceptHandler() 
    {
     
    }
    
    /**
     * (先物OP注文受付リクエスト)<BR>
     * 先物OP注文受付処理を実施する。<BR>
     * <BR>
     * 先物OP注文受付サービスを取得し、execute()メソッドをコールする。<BR>
     * @@param l_request - 株式指数オプション注文受付リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3IfoOrderAcceptRequest
     * @@roseuid 4057CFE50169
     */
    public WEB3IfoOrderAcceptResponse optionOrderAcceptRequest(WEB3IfoOrderAcceptRequest l_request) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + "optionOrderAcceptRequest(WEB3IfoOrderAcceptRequest l_request)";
                
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoOrderAcceptResponse l_response = null;
        WEB3IfoOrderAcceptService l_service = null;
        
        //株価指数オプション訂正新規建サービスを取得する
        try
        {
            log.debug("Enter the try path");
            l_service =
                (WEB3IfoOrderAcceptService)Services.getService(
            WEB3IfoOrderAcceptService.class);
            log.debug("Exit the try path");
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            log.debug("Enter the catch path");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(
                l_request,
                "株価指数オプション注文受付サービスの取得に失敗しました。",
                l_response.errorInfo,l_ex);
            log.debug("Exit the catch path");
            return l_response;      
        }
        
        //株価指数オプション訂正新規建サービス.execute()メソッドをコールする
        try
        {
            log.debug("Enter the try path");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_service.execute(
                    l_request);
            log.debug("Exit the try path");
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug("Enter the catch path.");
            l_response =
                (WEB3IfoOrderAcceptResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション注文受付に失敗しました。", l_ex);
            log.debug("Exit the try path.");
            return l_response;
        }

        log.exiting(STR_METHOD_NAME);

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
