head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信残高照会ハンドラクラス(WEB3MutualBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 于美麗 (中訊) 新規作成
*/

package webbroker3.mf.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信残高照会ハンドラ)<BR>
 * 投信残高照会ハンドラクラス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public class WEB3MutualBalanceReferenceHandler implements MessageHandler 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBalanceReferenceHandler.class);
   
   /**
    * (get残高照会)<BR>
    * 投信残高照会処理を行う。<BR>
    * <BR>
    * 投信残高照会サービスImplを取得し、<BR>
    * execute()メソッドをコールする。
    * @@param l_request - 投信残高照会リクエストオブジェクト
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceResponse
    * @@roseuid 41AD8D930224
    */
   public WEB3MutualBalanceReferenceResponse getBalanceReference(WEB3MutualBalanceReferenceRequest l_request) 
   {
       final String STR_METHOD_NAME = "getBalanceReference()";
       log.entering(STR_METHOD_NAME);

       WEB3MutualBalanceReferenceService l_service = null;
       WEB3MutualBalanceReferenceResponse l_response = null;

       try
       {
           l_service =
               (WEB3MutualBalanceReferenceService) Services.getService(
                   WEB3MutualBalanceReferenceService.class);
       }
       // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
       catch (Exception l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "投信残高照会サービスの取得に失敗しました。",
               l_response.errorInfo,
               l_ex);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceResponse) l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "投信残高照会処理が失敗しました。",
               l_ex.getErrorInfo(),
               l_ex);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
   
   /**
    * (get残高合計)<BR>
    * 投信残高合計処理を行う。<BR>
    * <BR>
    * 投信残高照会サービスImplを取得し、<BR>
    * execute()メソッドをコールする。
    * @@param l_request - 投信残高照会残高合計リクエストオブジェクト
    * @@return webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse
    * @@roseuid 41AD8DD80020
    */
   public WEB3MutualBalanceReferenceTotalResponse getBalanceReferenceTotal(WEB3MutualBalanceReferenceTotalRequest l_request) 
   {
       final String STR_METHOD_NAME = "getBalanceReferenceTotal()";
       log.entering(STR_METHOD_NAME);

       WEB3MutualBalanceReferenceService l_service = null;
       WEB3MutualBalanceReferenceTotalResponse l_response = null;

       try
       {
           l_service =
               (WEB3MutualBalanceReferenceService) Services.getService(
                   WEB3MutualBalanceReferenceService.class);
       }
       // サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
       catch (Exception l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "投信残高照会サービスの取得に失敗しました。",
               l_response.errorInfo,
               l_ex);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3MutualBalanceReferenceTotalResponse) l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "投信残高合計処理が失敗しました。",
               l_ex.getErrorInfo(),
               l_ex);
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}
@
