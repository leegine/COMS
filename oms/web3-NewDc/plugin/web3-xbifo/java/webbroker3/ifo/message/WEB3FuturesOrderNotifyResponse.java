head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物注文通知レスポンス(WEB3FuturesOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 凌建平 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (株価指数先物注文通知レスポンス)<BR>
 * 株価指数先物注文通知レスポンスクラス
 * @@author  : 凌建平
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyResponse extends WEB3BackResponse 
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
    * @@roseuid 41AD654700AB
    */
   public WEB3FuturesOrderNotifyResponse() 
   {
    
   }
   
   /**
    * (コンストラクタ。)<BR>
    * 引数で与えられたリクエストオブジェクトを基に<BR>
    * レスポンスオブジェクトを生成する。<BR>
    *<BR>
    * @@param l_request リクエストオブジェクト
    */
   public WEB3FuturesOrderNotifyResponse(WEB3FuturesOrderNotifyRequest l_request)
   {
       super(l_request);
   }
}
@
