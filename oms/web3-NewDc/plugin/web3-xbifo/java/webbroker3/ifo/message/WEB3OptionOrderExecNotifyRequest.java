head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式指数オプション出来通知リクエスト
(WEB3OptionOrderExecNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (株式指数オプション出来通知リクエスト)<BR>
 * 株式指数オプション出来通知リクエストクラス<BR>                                                                    
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_orderExecNotify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111835L;
    
    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0AE49035B
     */
    public WEB3OptionOrderExecNotifyRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3OptionOrderExecNotifyResponse(this);
    }
}
@
