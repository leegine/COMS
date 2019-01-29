head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式指数先物出来通知リクエストクラス(WEB3FuturesOrderExecNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 艾興 (中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * 株式指数先物出来通知リクエストクラス<BR>
 */
public class WEB3FuturesOrderExecNotifyRequest extends WEB3BackRequest 
{
    /**
    * PTYPE<BR>
    */
    public final static String PTYPE = "futures_orderExecNotify";

    /**
    * SerialVersionUID<BR>
    */
    public final static long serialVersionUID = 200407221304L;

    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;
    
    /**
     * @@roseuid 40F7AE0E0138
     */
    public WEB3FuturesOrderExecNotifyRequest() 
    {
     
    }
    
    /**
     * @@return WEB3BackResponse
     * @@roseuid 40F7AE0E0148
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3FuturesOrderExecNotifyResponse(this);
    }
}
@
