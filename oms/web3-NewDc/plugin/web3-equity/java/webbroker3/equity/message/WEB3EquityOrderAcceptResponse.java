head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文受付レスポンスクラス(WEB3EquityOrderAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 洪華 (中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * （株式注文受付レスポンス）。<br>
 * <br>
 * 株式注文受付レスポンスクラス
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptResponse extends WEB3BackResponse 
{
   
   /**
    * ポリモルフィックタイプ。<BR>
    */
   public static  final String PTYPE = "equity_order_accept";
   
   /**
    * シリアルバージョンUID。<BR>
    */
   public static  final long serialVersionUID = 200402241330L;
   
   /**
    * コンストラクタ。<BR>
    * @@param request リクエストクラス
    * @@param l_request
    * @@roseuid 4034961F0150
    */
   public WEB3EquityOrderAcceptResponse(WEB3BackRequest l_request) 
   {
       super(l_request);
   }
   
   /**
    * コンストラクタ。<BR>
    * @@roseuid 4034961F0141
    */
   public WEB3EquityOrderAcceptResponse() 
   {
      
   }
}
@
