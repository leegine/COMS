head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.45.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiStreamHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用債券連携ハンドラ(WEB3SrvRegiStreamHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/19 馮海濤 新規作成 モデル371、モデル377
*/
package webbroker3.srvregi.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用債券連携ハンドラ)<BR>
 * サービス利用債券連携ハンドラクラス<BR>
 *
 * @@author 馮海濤
 * @@version 1.0
 */
public class WEB3SrvRegiStreamHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiStreamHandler.class);

   /**
    * @@roseuid 4831260B03D8
    */
   public WEB3SrvRegiStreamHandler()
   {

   }

   /**
    * (債券連携リクエスト)<BR>
    * サービス利用債券連携処理を行う。<BR>
    * <BR>
    * サービス利用債券連携サービスを取得し、execute( )メソッドをコールする。<BR>
    * @@roseuid 4815933F03C3
    */
   public WEB3SrvRegiStreamResponse srvRegiStreamRequest(WEB3SrvRegiStreamRequest l_request)
   {
       final String STR_METHOD_NAME = "srvRegiStreamRequest(WEB3SrvRegiStreamRequest)";
       log.entering(STR_METHOD_NAME);

       WEB3SrvRegiStreamResponse l_response = null;
       WEB3SrvRegiStreamService l_service = null;

       try
       {
           l_service =
               (WEB3SrvRegiStreamService)Services.getService(WEB3SrvRegiStreamService.class);
       }
       catch (Exception l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.error(
               l_request,
               "サービス利用債券連携サービスを取得に失敗しました。",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }

       try
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_service.execute(l_request);
       }
       catch (WEB3BaseException l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();

           log.error(
               l_request,
               "サービス利用債券連携処理が失敗しました。",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch (WEB3BaseRuntimeException l_ex)
       {
           l_response =
               (WEB3SrvRegiStreamResponse)l_request.createResponse();
           l_response.errorInfo = l_ex.getErrorInfo();
           log.error(
               l_request,
               "サービス利用債券連携処理が失敗しました。",
               l_response.errorInfo,
               l_ex);

           log.exiting(STR_METHOD_NAME);
           return l_response;
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }
}@
