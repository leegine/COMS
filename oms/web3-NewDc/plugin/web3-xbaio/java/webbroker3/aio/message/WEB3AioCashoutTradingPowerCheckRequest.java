head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerCheckRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックリクエスト(WEB3AioCashoutTradingPowerCheckRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (出金余力チェックリクエスト)<BR>
 * 出金余力チェックリクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutTradingPowerCheckRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_trading_power_check";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101355L;    
    
    /**
     * (証券会社コード)
     */
    public String institutionCode;
    
    /**
     * (処理フラグ)<BR>
     * 処理フラグ<BR>
     * <BR>
     * "0"： 全件データ処理<BR>
     * "1"： 当日振込分データ処理<BR>
     * "2"： 翌日振込分データ処理<BR>
     */
    public String processFlag;
    
    /**
     * @@roseuid 4158E9B60001
     */
    public WEB3AioCashoutTradingPowerCheckRequest() 
    {
     
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金余力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B60015
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashoutTradingPowerCheckResponse(this);
    }
}
@
