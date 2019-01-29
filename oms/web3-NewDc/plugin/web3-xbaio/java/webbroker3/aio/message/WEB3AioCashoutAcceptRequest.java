head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金受付リクエスト(WEB3AioCashoutAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (出金受付リクエスト)<BR>
 * 出金受付リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashoutAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_accept";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410100947L;
        
    /**
     * @@roseuid 4158EB5D03AC
     */
    public WEB3AioCashoutAcceptRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金受付レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158EB5D03C0
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashoutAcceptResponse(this);
    }
}
@
