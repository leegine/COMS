head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDayTradeTradingPowerResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 日計り銘柄取引余力試算画面表示レスポンス(WEB3TPDayTradeTradingPowerResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * (日計り銘柄取引余力試算画面表示レスポンス)<BR>
 * 日計り銘柄取引余力試算画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPDayTradeTradingPowerResponse extends WEB3GenResponse 
{
   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_daytrade_tradingpower";
   
   /**
    * 銘柄コード
    */
   public String productCode;
   
   /**
    * 銘柄名
    */
   public String productName;
   
   /**
    * 指定銘柄買付余力
    */
   public String dayTradeEquityTradingPower;
   
   /**
    * 指定銘柄売付可能数量
    */
   public String dayTradeEquitySellPossQuantity;
   
   
   /**
    * (コンストラクタ)
    * @@param l_request
    * @@roseuid 41B69EEE0260
    */
   protected WEB3TPDayTradeTradingPowerResponse(WEB3GenRequest l_request) 
   {
        super( l_request );
   }
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B5544C03B1
    */
   public WEB3TPDayTradeTradingPowerResponse() 
   {
    
   }
   
}
@
