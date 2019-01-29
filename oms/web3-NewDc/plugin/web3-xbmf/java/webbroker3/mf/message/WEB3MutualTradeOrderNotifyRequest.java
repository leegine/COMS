head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知リクエストクラス(WEB3MutualTradeOrderNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * 投資信託売買注文通知リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualTradeOrderNotifyRequest extends WEB3BackRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_trade_order_notify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408130925L;
        
    /**
     * (投信売買注文通知リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A13C00B7
     */
    public WEB3MutualTradeOrderNotifyRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 投信売買注文通知レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A4326A02DD
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualTradeOrderNotifyResponse(this);
    }
}
@
