head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPReCalcHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算Handlerクラス(WEB3AdminTpAssetReCalcHandler.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/31 kikuchi(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPReCalcService;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;


/**
 * (余力再計算ハンドラクラス)
 * 
 * MessegeHandlerクラスを拡張。
 */
public class WEB3AdminTPReCalcHandler implements MessageHandler 
{
    WEB3AdminTPReCalcService service;
   
   /**
    * @@roseuid 41DBC9CD007E
    */
   public WEB3AdminTPReCalcHandler() 
   {
       service = (WEB3AdminTPReCalcService)Services.getService(WEB3AdminTPReCalcService.class);    
   }
      
   /**
    * (submit余力再計算)
    * 余力再計算実行処理を行う。 
    * 
    * 余力再計算サービスImplを取得し、 
    * execute()メソッドをコールする。
    * @@param l_request - 余力再計算実行リクエスト
    * @@return 
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPReCalcResponse reCalcRequest(WEB3AdminTPReCalcRequest l_request) 
   {
       WEB3AdminTPReCalcResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReCalcResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReCalcResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
   
   /**
    * (get余力再計算画面)
    * 余力再計算入力処理を行う。 
    * 
    * 余力再計算サービスImplを取得し、 
    * execute()メソッドをコールする。
    * @@param l_request - 余力再計算入力リクエスト
    * @@return 
    * webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse
    * @@roseuid 41B8FB720038
    */
   public WEB3AdminTPReCalcInputResponse reCalcInputRequest(WEB3AdminTPReCalcInputRequest l_request) 
   {
       WEB3AdminTPReCalcInputResponse l_response;
       try
       {
           l_response = (WEB3AdminTPReCalcInputResponse)service.execute(l_request);           
       }
       catch(WEB3BaseException e)
       {
           l_response =  new WEB3AdminTPReCalcInputResponse();
           l_response.errorInfo = e.getErrorInfo();
       }
       return l_response;
   }
}
@
