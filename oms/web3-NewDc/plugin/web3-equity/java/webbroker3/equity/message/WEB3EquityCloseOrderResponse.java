head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityCloseOrderResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式失効通知レスポンスクラス(WEB3EquityCloseOrderResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 張生 (中訊) 新規作成
                   2004/12/06 岡村和明(SRA) 残案件対応 Ｎｏ.３３５
                   2004/12/21 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式失効通知レスポンス）。<br>
 * <br>
 * 株式失効通知レスポンスクラス
 * @@version 1.0
 */
public class WEB3EquityCloseOrderResponse extends WEB3BackResponse 
{
   
    /**
     * <p>（PTYPE）</p>
     */
    public static  final String PTYPE = "equity_close_order";

    /**
     * <p>（SerialVersionUID）</p>
     */
    public static  final long serialVersionUID = 200405200156L;
   
    /**
     * <p>（株式失効通知レスポンス）。</p>
     * <p>コンストラクタ。</p>
     */
   public WEB3EquityCloseOrderResponse() 
   {
    
   }

   /**
    * <p>（株式失効通知レスポンス）。</p>
    * <p>コンストラクタ。</p>
    * @@param l_request 株式失効通知リクエスト
    */  
   public WEB3EquityCloseOrderResponse(WEB3EquityCloseOrderRequest l_request)
   {
       super(l_request);
   }   
}
@
