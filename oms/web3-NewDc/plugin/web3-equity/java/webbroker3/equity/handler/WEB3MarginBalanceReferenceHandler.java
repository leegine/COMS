head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引残高照会ハンドラ(WEB3MarginBalanceReferenceHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3MarginBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalResponse;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * （信用取引残高照会ハンドラ）。<BR>
 * <BR>
 * 信用取引残高照会ハンドラクラス<BR>
 */
public class WEB3MarginBalanceReferenceHandler implements MessageHandler 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceHandler.class);

     /**
      * @@roseuid 4206CDD40386<BR>
      */
     public WEB3MarginBalanceReferenceHandler() 
     {
      
     }
     
     /**
      * (get残高照会)<BR>
      * <BR>
      * 信用取引残高照会処理を行う。<BR>
      * <BR>
      * 信用取引残高照会サービスImplを取得し、<BR>
      * execute()メソッドをコールする。<BR>
      * @@param l_request - (リクエストデータ) 信用取引残高照会リクエストオブジェクト<BR>
      * @@return WEB3MarginBalanceReferenceResponse<BR>
      * @@roseuid 41BFCEC8037C<BR>
      */
     public WEB3MarginBalanceReferenceResponse getBalanceReference(WEB3MarginBalanceReferenceRequest l_request) 
     {
         final String STR_METHOD_NAME = "getBalanceReference(WEB3MarginBalanceReferenceRequest)";
         log.entering(STR_METHOD_NAME);

         WEB3MarginBalanceReferenceService l_service = null;
         WEB3MarginBalanceReferenceResponse l_response = null;

         try
         {
             // 信用取引残高照会サービスを取得
             l_service =
                 (WEB3MarginBalanceReferenceService) Services.getService(
                     WEB3MarginBalanceReferenceService.class);
         }
         catch (Exception e)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "信用取引残高照会サービスの取得に失敗しました。",
                 l_response.errorInfo,e);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
                
         try
         {        
             //execute()メソッドをコールする。
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_service.execute(l_request);
         }
         catch (WEB3BaseException l_be)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = l_be.getErrorInfo();
             log.error(l_request, "信用取引残高照会に失敗しました。", l_be);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (WEB3BaseRuntimeException l_bre)
         {
             l_response =
                 (WEB3MarginBalanceReferenceResponse) l_request.createResponse();
             l_response.errorInfo = l_bre.getErrorInfo();
             log.error(l_request, "信用取引残高照会に失敗しました。", l_bre);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
            
         log.exiting(STR_METHOD_NAME);

         // レスポンスオブジェクトを返却する。
         return l_response;
     }
     
     /**
      * (get残高合計)<BR>
      * <BR>
      * 信用取引残高合計処理を行う。<BR>
      * <BR>
      * 信用取引残高照会サービスImplを取得し、<BR>
      * execute()メソッドをコールする。<BR>
      * @@param l_request - (リクエストデータ) 信用取引残高照会残高合計リクエストオブジェクト<BR>
      * @@return WEB3MarginBalanceReferenceTotalResponse<BR>
      * @@roseuid 41BFCF360040<BR>
      */
     public WEB3MarginBalanceReferenceTotalResponse getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest l_request) 
     {
         final String STR_METHOD_NAME = "getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest)";
         log.entering(STR_METHOD_NAME);

         WEB3MarginBalanceReferenceService l_service = null;
         WEB3MarginBalanceReferenceTotalResponse l_response = null;

         try
         {
             //信用取引残高照会サービスを取得
             l_service =
                 (WEB3MarginBalanceReferenceService) Services.getService(
                     WEB3MarginBalanceReferenceService.class);
         }
         catch (Exception e)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "信用取引残高照会サービスの取得に失敗しました。",
                 l_response.errorInfo,
                 e);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
                
         try
         {        
             //execute()メソッドをコールする。
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_service.execute(l_request);
         }
         catch (WEB3BaseException l_be)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = l_be.getErrorInfo();
             log.error(l_request, "信用取引残高合計に失敗しました。", l_be);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (WEB3BaseRuntimeException l_bre)
         {
             l_response =
                 (WEB3MarginBalanceReferenceTotalResponse) l_request.createResponse();
             l_response.errorInfo = l_bre.getErrorInfo();
             log.error(l_request, "信用取引残高合計に失敗しました。", l_bre);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
            
         log.exiting(STR_METHOD_NAME);

         // レスポンスオブジェクトを返却する。
         return l_response;
     }
}
@
