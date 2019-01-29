head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引余力画面表示リクエストクラス(WEB3TPTradingPowerRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (取引余力画面表示リクエスト)<BR>
 * 取引余力画面表示リクエストクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPTradingPowerRequest extends WEB3GenRequest 
{

   /**
    * PTYPE
    */
   public static final String PTYPE = "tradingpower_tradingpower";
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B551F30056
    */
   public WEB3TPTradingPowerRequest() 
   {

   }
   
   /**
    * (コンストラクタ)
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41B551F30075
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPTradingPowerResponse(this);
   }
}
@
