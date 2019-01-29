head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.32.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 出金受付状況画面表示レスポンス(WEB3TPPaymentAcceptResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) 新規作成
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金受付状況画面表示レスポンス)<BR>
 * 出金受付状況画面表示レスポンスクラス。<BR>
 * 
 * @@author asano(SCS)
 */
public class WEB3TPPaymentAcceptResponse extends WEB3GenResponse 
{

    public static final String PTYPE = "tradingpower_payment_status";
     
   /**
    * 余力計算結果ID
    */
   public String calcResultId;

   /**
    * 出金受付状況明細一覧
    */
   public WEB3TPPaymentAcceptUnit[] paymentAcceptUnits;
   
   /**
    * (コンストラクタ)
    * @@roseuid 41B932E60036
    */
   public WEB3TPPaymentAcceptResponse() 
   {
    
   }
   
   /**
    * (コンストラクタ)
    * @@param request
    */
   public WEB3TPPaymentAcceptResponse(WEB3TPPaymentAcceptRequest l_request) 
   {
        
       super(l_request);
   }
}
@
