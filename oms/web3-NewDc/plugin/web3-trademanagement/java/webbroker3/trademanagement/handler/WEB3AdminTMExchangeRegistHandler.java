head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMExchangeRegistHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者為替登録ハンドラ(WEB3AdminTMExchangeRegistHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者為替登録ハンドラ)<BR>
 * 管理者為替登録ハンドラクラス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMExchangeRegistHandler implements MessageHandler
{
    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AdminTMExchangeRegistHandler.class);

     /**
      * コンストラクタ
      */
     public WEB3AdminTMExchangeRegistHandler()
     {

     }

    /**
     * (get入力画面)<BR>
     * 管理者為替登録サービスを取得し、execute()メソッドをコールする。
     * <BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3AdminTMExchangeRegistInputResponse
     */
     public WEB3AdminTMExchangeRegistInputResponse getInputScreen(
         WEB3AdminTMExchangeRegistInputRequest l_request)
     {
         final String STR_METHOD_NAME =
             "getInputScreen(WEB3AdminTMExchangeRegistInputRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("パラメータ値不正。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }
         WEB3AdminTMExchangeRegistService l_service = null;
         WEB3AdminTMExchangeRegistInputResponse l_response = null;

         try
         {
             //get管理者為替登録サービス
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("管理者為替登録サービスを取得に失敗しました。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "get入力画面を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistInputResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "get入力画面を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }

     /**
      * (validate為替登録)<BR>
      * 管理者為替登録サービスを取得し、execute()メソッドをコールする。<BR>
      * <BR>
      * @@param l_request - リクエストデータ
      * @@return WEB3AdminTMExchangeRegistConfirmResponse
      */
     public WEB3AdminTMExchangeRegistConfirmResponse validateExchangeRegist(
         WEB3AdminTMExchangeRegistConfirmRequest l_request)
     {
         final String STR_METHOD_NAME =
             "validateExchangeRegist(WEB3AdminTMExchangeRegistConfirmRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("パラメータ値不正。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }

         WEB3AdminTMExchangeRegistService l_service = null;
         WEB3AdminTMExchangeRegistConfirmResponse l_response = null;

         try
         {
             //get管理者為替登録サービス
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("管理者為替登録サービスを取得に失敗しました。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "validate為替登録を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistConfirmResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "validate為替登録を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }

     /**
      * (submit為替登録)<BR>
      * 管理者為替登録サービスを取得し、execute()メソッドをコールする。
      * <BR>
      * @@param l_request - リクエストデータ
      * @@return WEB3AdminTMExchangeRegistCompleteResponse
      */
     public WEB3AdminTMExchangeRegistCompleteResponse submitExchangeRegist(
         WEB3AdminTMExchangeRegistCompleteRequest l_request)
     {
         final String STR_METHOD_NAME =
             "submitExchangeRegist(WEB3AdminTMExchangeRegistCompleteRequest)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.debug("パラメータ値不正。");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME);
         }

         WEB3AdminTMExchangeRegistCompleteResponse l_response = null;
         WEB3AdminTMExchangeRegistService l_service = null;

         try
         {
             //get管理者為替登録サービス
             l_service = (WEB3AdminTMExchangeRegistService)Services.getService(
                 WEB3AdminTMExchangeRegistService.class);
             if (l_service == null)
             {
                 log.debug("管理者為替登録サービスを取得に失敗しました。");
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             //execute
             //WEB3BaseException
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_service.execute(l_request);
         }
         catch (WEB3BaseException l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                     this.getClass().getName() + STR_METHOD_NAME);
             }
             l_response.errorInfo = l_ex.getErrorInfo();
             log.error(
                 l_request,
                 "submit為替登録を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }
         catch (Exception l_ex)
         {
             l_response = (WEB3AdminTMExchangeRegistCompleteResponse)l_request.createResponse();
             if (l_response == null)
             {
                 log.exiting(STR_METHOD_NAME);
                 throw new WEB3BaseRuntimeException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME);
             }

             l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
             log.error(
                 l_request,
                 "submit為替登録を実施に失敗しました。",
                 l_response.errorInfo,
                 l_ex);
             log.exiting(STR_METHOD_NAME);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     }
}
@
