head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerCheckResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックレスポンス(WEB3AioCashoutTradingPowerCheckResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (出金余力チェックレスポンス)<BR>
 * 出金余力チェックレスポンスクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerCheckResponse extends WEB3BackResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_trading_power_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101425L;  
      
    /**
     * @@roseuid 4158E9B50349
     */
    public WEB3AioCashoutTradingPowerCheckResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashoutTradingPowerCheckResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }  
}
@
