head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseOrderRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知リクエストクラス(WEB3IfoCloseOrderRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (先物OP失効通知リクエスト)<BR>
 * 先物OP失効通知リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoCloseOrderRequest extends WEB3BackRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "ifo_closeOrder";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112120L;

    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0AE4E01D4
     */
    public WEB3IfoCloseOrderRequest() 
    {
     
    }
    /**
     * @@roseuid 40C0AE4E00BB
     */
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3IfoCloseOrderResponse(this);
    }
}
@
