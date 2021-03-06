head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込入力リクエスト(WEB3AioCashoutInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込入力リクエスト)<BR>
 * 出金申込入力リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101200L;
        
    /**
     * @@roseuid 4158EB620197
     */
    public WEB3AioCashoutInputRequest() 
    {
     
    } 

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金申込入力レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB6201A1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutInputResponse(this);
    }
}
@
