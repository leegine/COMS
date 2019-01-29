head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文通知リクエスト(WEB3FuturesOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (株価指数先物注文通知リクエスト)<BR>
 * 株価指数先物注文通知リクエストクラス
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_orderNotify";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412081451L;
    
   /**
    * @@roseuid 41AD6547000F
    */
   public WEB3FuturesOrderNotifyRequest() 
   {
    
   }
   
   /**
    * @@return WEB3GenResponse
    * @@roseuid 41AD6547001F
    */
   public WEB3BackResponse createResponse() 
   {
       return new WEB3FuturesOrderNotifyResponse(this);
   }
}
@
