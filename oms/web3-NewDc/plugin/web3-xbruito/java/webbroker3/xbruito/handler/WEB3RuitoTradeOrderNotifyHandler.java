head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoTradeOrderNotifyHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知ハンドラクラス(WEB3RuitoTradeOrderNotifyHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 杜森 (中訊) 新規作成
*/
package webbroker3.xbruito.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;

/**
 * 累積投資売買注文通知ハンドラクラス
 */
public class WEB3RuitoTradeOrderNotifyHandler implements MessageHandler 
{
       /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyHandler.class);
   
   /**
    * @@roseuid 40B40A7C03C8
    */
   public WEB3RuitoTradeOrderNotifyHandler() 
   {
    
   }
   
   /**
    * 累積投資売買注文通知を行う。<BR>
    * <BR>
    * 累積投資売買注文通知サービスを取得し、<BR>
    * <BR>
    * execute()メソッドをコールする。<BR>
    * <BR>
    * MAXASよりリクエストを受け、累投売買注文通知処理を実行する。<BR>
    * （システム実装方針ガイド 4.5.ハンドラ　@参照）<BR>
    * <BR>
    * １）　@累投売買注文通知サービスオブジェクトを取得する。<BR>
    * <BR>
    * ２）　@累投売買注文通知サービスオブジェクト.execute（）をコールし、<BR>
    *       処理結果である累投売買注文通知レスポンスオブジェクトを取得する。<BR>
    * <BR>
    * ３）　@サービスで例外が発生した場合は、<BR>
    *       エラー情報をレスポンスメッセージに設定する。<BR>
    * <BR>
    * ４）　@累投売買注文通知レスポンスオブジェクトを返却する。<BR>
    * @@param l_request - 累積投資売買注文通知リクエストオブジェクト <BR>
    * @@return webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse
    * @@roseuid 405A4E4F008B
    */
   public WEB3RuitoDealingOrderNotifyResponse tradeOrderNotifyRequest(
       WEB3RuitoDealingOrderNotifyRequest l_request) 
   {
     // 累積投資売買注文通知サービスオブジェクトを取得する
         final String STR_METHOD_NAME = 
             "WEB3RuitoDealingOrderNotifyRequest(WEB3BackRequest)";

       log.entering(STR_METHOD_NAME);
       if (l_request == null)
       {
           log.debug(" パラメータ値がNULL ");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME, 
               "パラメータ値がNULL");            
       }
        WEB3RuitoDealingOrderNotifyResponse l_response = null;
        WEB3RuitoTradeOrderNotifyService    l_service  = null;
         
         // 累積投資売買注文通知サービスオブジェクト.execute（）をコールし、
         // 処理結果であるレスポンスオブジェクトを取得する。
         try
         {
             l_service =(WEB3RuitoTradeOrderNotifyService) 
                 Services.getService(WEB3RuitoTradeOrderNotifyService.class);
             l_response =
                 (WEB3RuitoDealingOrderNotifyResponse) 
                 l_service.execute(l_request);
         }
         catch (WEB3BaseException e)
         {
             l_response =
                 (WEB3RuitoDealingOrderNotifyResponse) 
                 l_request.createResponse();
             l_response.errorInfo = e.getErrorInfo();
             log.error(l_request, "累積投資売買注文通知に失敗しました。", e);
             return l_response;
         }

         log.exiting(STR_METHOD_NAME);

         // レスポンスオブジェクトを返却する。
         return l_response;
   }
}
@
