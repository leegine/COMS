head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文受付リクエストクラス(WEB3MutualOrderAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 投資信託注文受付リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualOrderAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_order_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121100L;
    
    /**
     * (投信注文受付リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A9A12D01C1
     */
    public WEB3MutualOrderAcceptRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR> 
     * <BR>
     * 投信注文受付レスポンスオブジェクトを生成して返す。
     * @@return WEB3BackResponse
     * @@roseuid 40A4324A0260
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualOrderAcceptResponse(this);
    }
}
@
