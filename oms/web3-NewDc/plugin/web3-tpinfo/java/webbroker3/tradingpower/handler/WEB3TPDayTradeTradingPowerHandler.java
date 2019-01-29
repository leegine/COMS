head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayTradeTradingPowerHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 日計り銘柄取引余力試算画面表示ハンドラクラス(WEB3TPDayTradeTradingPowerHandler.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerRequest;
import webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPAssetTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (日計り銘柄取引余力試算画面表示ハンドラ)<BR>
 * 日計り銘柄取引余力試算画面表示ハンドラ。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPDayTradeTradingPowerHandler implements MessageHandler 
{
    /**
     * ログユーティリティ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPDayTradeTradingPowerHandler.class);
   
   /**
    * コンストラクタ<BR>
    * @@roseuid 41B650A502CE
    */
   public WEB3TPDayTradeTradingPowerHandler() 
   {
    
   }
   
   /**
    * (create日計り銘柄取引余力試算画面)<BR>
    * 日計り銘柄取引余力試算画面表示処理を行う。<BR>
    * <BR>
    * 資産余力情報画面表示サービスを取得し、execute()メソッドをコールする。<BR>
    * @@param l_request
    * @@return webbroker3.tradingpower.message.WEB3TPDayTradeTradingPowerResponse)
    * @@roseuid 41B64D9400BA
    */
   public WEB3TPDayTradeTradingPowerResponse dayTradeTradingPowerRequest(WEB3TPDayTradeTradingPowerRequest l_request) 
   {
       final String STR_METHOD_NAME = "dayTradeTradingPowerRequest";
       log.entering(STR_METHOD_NAME);

       //資産余力情報画面表示サービス
       WEB3TPAssetTradingPowerService l_service = null;
       //日計り銘柄取引余力試算画面表示レスポンス
       WEB3TPDayTradeTradingPowerResponse l_response = null;

       try
       {
           //資産余力情報画面表示サービス取得
           l_service =
               (WEB3TPAssetTradingPowerService)Services.getService(WEB3TPAssetTradingPowerService.class);
           //資産余力情報画面表示サービス実行
           l_response =(WEB3TPDayTradeTradingPowerResponse)l_service.execute(l_request);
       } 
       catch (WEB3BaseException ex)
       {
           l_response = (WEB3TPDayTradeTradingPowerResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(WEB3BaseRuntimeException ex)
       {
           l_response = (WEB3TPDayTradeTradingPowerResponse)l_request.createResponse();
           l_response.errorInfo = ex.getErrorInfo();
           log.exiting(STR_METHOD_NAME);
           return l_response;
       }
       catch(Exception  ex)
       {
           l_response = (WEB3TPDayTradeTradingPowerResponse)l_request.createResponse();
           l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           log.exiting(STR_METHOD_NAME);
           return l_response;       
       }

       log.exiting(STR_METHOD_NAME);
       return l_response;
       
   }
   
}
@
