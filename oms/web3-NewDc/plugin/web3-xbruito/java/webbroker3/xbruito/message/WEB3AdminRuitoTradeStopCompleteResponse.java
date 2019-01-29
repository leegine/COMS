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
filename	WEB3AdminRuitoTradeStopCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投銘柄別売買停止完了レスポンス(WEB3AdminRuitoTradeStopCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (累投銘柄別売買停止完了レスポンス)<BR>
 * 累投銘柄別売買停止完了レスポンスクラス
 */
public class WEB3AdminRuitoTradeStopCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_complete";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (累投銘柄別売買停止完了レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40922CB302AF
     */
    public WEB3AdminRuitoTradeStopCompleteResponse()
    {
    }
    
    /**
     * (累投銘柄別売買停止完了レスポンス)<BR>
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminRuitoTradeStopCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
