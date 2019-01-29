head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoDealingOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累積投資売買注文通知レスポンスクラス(WEB3RuitoDealingOrderNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 累積投資売買注文通知レスポンスクラス<BR>
 */
public class WEB3RuitoDealingOrderNotifyResponse extends WEB3BackResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "ruito_dealing_order_notify";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408031539L;  
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3RuitoDealingOrderNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    

    /**
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CE10280
     */
    public WEB3RuitoDealingOrderNotifyResponse()
    {

    }
}
@
