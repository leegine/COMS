head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投銘柄別売買停止入力画面リクエスト(WEB3AdminRuitoTradeStopInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (累投銘柄別売買停止入力画面リクエスト)<BR>
 * 累投銘柄別売買停止入力画面リクエストクラス
 */
public class WEB3AdminRuitoTradeStopInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_input";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * (累投銘柄別売買停止入力画面リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CA1005D
     */
    public WEB3AdminRuitoTradeStopInputRequest()
    {
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 累投銘柄別売買停止入力画面レスポンスオブジェクトを生成し、返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407150F201D7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminRuitoTradeStopInputResponse(this);
    }
}@
