head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 出金受付状況画面表示リクエストクラス(WEB3TPPaymentAcceptRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金受付状況画面表示リクエスト)<BR>
 * 出金受付状況画面表示リクエストクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPPaymentAcceptRequest extends WEB3GenRequest 
{
 
    /**
     * PTYPE
     */   
    public static final String PTYPE = "tradingpower_payment_status";
   
   /**
    * (コンストラクタ)<BR>
    * @@roseuid 41B930F600B3
    */
   public WEB3TPPaymentAcceptRequest() 
   {
   }
   
   /**
    * (createレスポンス)
    * @@return WEB3GenResponse
    * @@roseuid 41B930F600D2
    */
   public WEB3GenResponse createResponse() 
   {
        return new WEB3TPPaymentAcceptResponse(this);
   }
}
@
