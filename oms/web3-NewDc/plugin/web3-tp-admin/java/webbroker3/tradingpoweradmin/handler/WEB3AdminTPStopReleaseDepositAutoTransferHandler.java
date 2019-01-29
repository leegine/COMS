head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.08.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPStopReleaseDepositAutoTransferHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金自動振替停止登録/解除ハンドラクラス(WEB3AdminTPStopReleaseDepositAutoTransferHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} 堀野和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPStopReleaseDepositAutoTransferService;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRequest;

/**
 * 保証金自動振替停止登録/解除ハンドラクラス
 */
public class WEB3AdminTPStopReleaseDepositAutoTransferHandler implements MessageHandler
{
    WEB3AdminTPStopReleaseDepositAutoTransferService service;

   /**
    * @@roseuid 41DBCAAD01A7
    */
   public WEB3AdminTPStopReleaseDepositAutoTransferHandler()
   {
       service = (WEB3AdminTPStopReleaseDepositAutoTransferService)Services.getService(WEB3AdminTPStopReleaseDepositAutoTransferService.class);
   }

   /**
    * (get保証金自動振替停止登録入力画面)
    * 保証金自動振替停止登録入力画面取得処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止登録入力画面リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResp
    * onse
    * @@roseuid 41BE508003B7
    */
   public WEB3AdminTPStopDepositAutoTransferInputResponse stopDepositAutoTransferInputRequest(WEB3AdminTPStopDepositAutoTransferInputRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferInputResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate保証金自動振替停止登録)
    * 保証金自動振替停止登録確認処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止登録確認リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRe
    * sponse
    * @@roseuid 41C91E280038
    */
   public WEB3AdminTPStopDepositAutoTransferConfirmResponse stopDepositAutoTransferConfirmRequest(WEB3AdminTPStopDepositAutoTransferConfirmRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit保証金自動振替停止登録)
    * 保証金自動振替停止登録完了処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止登録完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteR
    * esponse
    * @@roseuid 41BE508E006B
    */
   public WEB3AdminTPStopDepositAutoTransferCompleteResponse stopDepositAutoTransferCompleteRequest(WEB3AdminTPStopDepositAutoTransferCompleteRequest l_request)
   {
       WEB3AdminTPStopDepositAutoTransferCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPStopDepositAutoTransferCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPStopDepositAutoTransferCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (get保証金自動振替停止顧客検索結果)
    * 保証金自動振替停止顧客検索処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止顧客検索リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRespo
    * nse
    * @@roseuid 41BE505802FB
    */
   public WEB3AdminTPFindDepositAutoTransferStopResponse findDepositAutoTransferStopRequest(WEB3AdminTPFindDepositAutoTransferStopRequest l_request)
   {
       WEB3AdminTPFindDepositAutoTransferStopResponse l_response;
       try
       {
           l_response = (WEB3AdminTPFindDepositAutoTransferStopResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPFindDepositAutoTransferStopResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate保証金自動振替停止解除)
    * 保証金自動振替停止解除確認処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止解除確認リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfir
    * mResponse
    * @@roseuid 41BE5096026F
    */
   public WEB3AdminTPReleaseDepositAutoTransferConfirmResponse releaseDepositAutoTransferConfirmRequest(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest l_request)
   {
       WEB3AdminTPReleaseDepositAutoTransferConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReleaseDepositAutoTransferConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReleaseDepositAutoTransferConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit保証金自動振替停止解除)
    * 保証金自動振替停止解除完了処理を行う。
    *
    * 保証金自動振替停止/解除サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 保証金自動振替停止解除完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferComple
    * teResponse
    * @@roseuid 41BE50D502FB
    */
   public WEB3AdminTPReleaseDepositAutoTransferCompleteResponse releaseDepositAutoTransferCompleteRequest(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest l_request)
   {
       WEB3AdminTPReleaseDepositAutoTransferCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReleaseDepositAutoTransferCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReleaseDepositAutoTransferCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
