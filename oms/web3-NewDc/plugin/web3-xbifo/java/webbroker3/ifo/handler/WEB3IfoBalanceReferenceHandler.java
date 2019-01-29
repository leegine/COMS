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
filename	WEB3IfoBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP残高照会ハンドラクラス(WEB3IfoBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP残高照会ハンドラ)<BR>
 * 先物OP残高照会ハンドラクラス
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceHandler.class);
  
    /**
     * (get残高照会)<BR>
     * 株価指数先物/オプション残高照会処理を行う。<BR>
     * <BR>
     * 先物OP残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 先物OP残高照会リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse
     * @@roseuid 41AAC6FC00B1
     */
    public WEB3FuturesOptionsBalanceReferenceResponse getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOptionsBalanceReferenceResponse l_response = null;
        WEB3IfoBalanceReferenceService l_service = null;
  
        //株価指数オプションの新規建発注審査を行う。
        try
        {
            l_service = (WEB3IfoBalanceReferenceService)Services.getService(WEB3IfoBalanceReferenceService.class);
  
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {
  
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション残高照会サービスに失敗しました。", l_response.errorInfo, l_ex);
  
            return l_response;
        }
         
        //株価指数オプション残高照会サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション残高照会の残高照会処理に失敗しました。", l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
  
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
    
    /**
     * (get残高合計)<BR>
     * 株価指数先物/オプション残高合計処理を行う。<BR>
     * <BR>
     * 先物OP残高照会サービスImplを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - 先物OP残高照会残高合計リクエストオブジェクト
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse
     * @@roseuid 41AD2DBD0178
     */
    public WEB3FuturesOptionsBalanceReferenceTotalResponse 
        getBalanceReferenceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request) 
    {
        final String STR_METHOD_NAME = ".getBalanceReferenceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3FuturesOptionsBalanceReferenceTotalResponse l_response = null;
        WEB3IfoBalanceReferenceService l_service = null;
  
        //株価指数オプションの残高照会発注審査を行う。
        try
        {
            l_service = (WEB3IfoBalanceReferenceService)Services.getService(WEB3IfoBalanceReferenceService.class);
  
            //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        }
        catch (Exception l_ex)
        {
  
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数先物オプション残高合計サービスに失敗しました。", l_response.errorInfo, l_ex);
  
            return l_response;
        }
         
        //株価指数オプション新規建注文サービスを取得し、execute()メソッドをコールする。
        try
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse) l_service.execute(l_request);
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数先物オプション残高合計の残高合計処理に失敗しました。", l_ex);
            return l_response;
        }
         
        log.exiting(STR_METHOD_NAME);
  
        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
