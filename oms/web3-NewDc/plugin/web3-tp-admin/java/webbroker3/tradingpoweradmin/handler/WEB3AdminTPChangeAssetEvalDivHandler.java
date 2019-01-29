head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPChangeAssetEvalDivHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算方法@変更ハンドラクラス(WEB3AdminTPChangeAssetEvalDivHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : ${date} 堀野和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeAssetEvalDivService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (余力計算方法@変更ハンドラクラス)
 *
 * MessegeHandlerクラスを拡張。
 */
public class WEB3AdminTPChangeAssetEvalDivHandler implements MessageHandler
{
    WEB3AdminTPChangeAssetEvalDivService service;

   /**
    * @@roseuid 41DBC9CD007E
    */
   public WEB3AdminTPChangeAssetEvalDivHandler()
   {
       service = (WEB3AdminTPChangeAssetEvalDivService)Services.getService(WEB3AdminTPChangeAssetEvalDivService.class);
   }

   /**
    * (get余力計算方法@変更入力画面)
    * 余力計算方法@変更入力画面表示処理を行う。
    *
    * 余力計算方法@変更サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 余力計算方法@変更入力リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse
    * @@roseuid 41B8FB5201FE
    */
   public WEB3AdminTPChangeAssetEvalDivInputResponse changeAssetEvalDivInputRequest(WEB3AdminTPChangeAssetEvalDivInputRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivInputResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (validate余力計算方法@変更)
    * 余力計算方法@変更確認処理を行う。
    *
    * 余力計算方法@変更サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 余力計算方法@変更確認リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRespons
    * e
    * @@roseuid 41C7DDA30124
    */
   public WEB3AdminTPChangeAssetEvalDivConfirmResponse changeAssetEvalDivConfirmRequest(WEB3AdminTPChangeAssetEvalDivConfirmRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivConfirmResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivConfirmResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivConfirmResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }

   /**
    * (submit余力計算方法@変更)
    * 余力計算方法@変更完了処理を行う。
    *
    * 余力計算方法@変更サービスImplを取得し、
    * execute()メソッドをコールする。
    * @@param l_request - 余力計算方法@変更完了リクエスト
    * @@return
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRespon
    * se
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPChangeAssetEvalDivCompleteResponse changeAssetEvalDivCompleteRequest(WEB3AdminTPChangeAssetEvalDivCompleteRequest l_request)
   {
       WEB3AdminTPChangeAssetEvalDivCompleteResponse l_response;
       try
       {
           l_response = (WEB3AdminTPChangeAssetEvalDivCompleteResponse)service.execute(l_request);
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPChangeAssetEvalDivCompleteResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
