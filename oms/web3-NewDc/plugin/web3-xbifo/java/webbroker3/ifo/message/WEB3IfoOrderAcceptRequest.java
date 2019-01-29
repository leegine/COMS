head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付リクエスト(WEB3IfoOrderAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (先物OP注文受付リクエスト)<BR>
 * 先物OP注文受付リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3IfoOrderAcceptRequest extends WEB3BackRequest 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "ifo_orderAccept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112145L;
        
    /**
     * 識別コードプレフィクス一覧<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * スレッドNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0A9DE00AB
     */
    public WEB3IfoOrderAcceptRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3IfoOrderAcceptResponse(this);
    }
}
@
