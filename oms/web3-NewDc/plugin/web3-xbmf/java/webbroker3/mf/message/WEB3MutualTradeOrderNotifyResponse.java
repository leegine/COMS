head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualTradeOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託売買注文通知レスポンスクラス(WEB3MutualTradeOrderNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 黄建 (中訊) 新規作成
                   2004/08/23 于美麗 (中訊) レビュー 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 投資信託売買注文通知レスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualTradeOrderNotifyResponse extends WEB3BackResponse 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_trade_order_notify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408130915L;
        
    
    /**
     * (投信売買注文通知レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A18C001B
     */
    public WEB3MutualTradeOrderNotifyResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3MutualTradeOrderNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    
}
@
