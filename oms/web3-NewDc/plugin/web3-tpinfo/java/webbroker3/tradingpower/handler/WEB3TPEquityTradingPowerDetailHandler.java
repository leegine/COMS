head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPEquityTradingPowerDetailHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 現物株買付余力詳細画面表示ハンドラクラス(WEB3TPEquityTradingPowerDetailHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailRequest;
import webbroker3.tradingpower.message.WEB3TPEquityTradingPowerDetailResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (現物株買付余力詳細画面表示ハンドラ)<BR>
 * 現物株買付余力詳細画面表示ハンドラ。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPEquityTradingPowerDetailHandler implements MessageHandler 
{
    
    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPEquityTradingPowerDetailHandler.class);
   
   /**
    * (コンストラクタ)<BR>
    * @@roseuid 41B650A6005D
    */
   public WEB3TPEquityTradingPowerDetailHandler() 
   {
    
   }
   
   /**
    * (create現物株買付余力詳細画面)<BR>
    * 現物株買付余力詳細画面表示処理を行う。<BR>
    * <BR>
    * 資産余力情報画面表示サービスを取得し、execute()メソッドをコールする。<BR>
    * @@param l_request
    * @@return WEB3TPEquityTradingPowerDetailResponse
    * @@roseuid 41B64F9A0260
    */ 
   public WEB3TPEquityTradingPowerDetailResponse equityTradingPowerDetailRequest(WEB3TPEquityTradingPowerDetailRequest l_request) 
   {
       final String STR_METHOD_NAME = "equityTradingPowerDetailRequest";
       log.entering(STR_METHOD_NAME);

       //資産余力情報画面表示サービス
       WEB3TPAssetTradingPowerService l_service = null;
       //現物株買付余力詳細画面表示レスポンス
       WEB3TPEquityTradingPowerDetailResponse l_response = null;

       try
       {
           //資産余力情報画面表示サービス取得
           l_service =
               (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
           //資産余力情報画面表示サービス実行
           l_response =(WEB3TPEquityTradingPowerDetailResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPEquityTradingPowerDetailResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
   }

}
@
