head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.15.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文要求中止リクエスト(WEB3AioCashinSettleCancelRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (注文要求中止リクエスト)<BR>
 * 注文要求中止リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AioCashinSettleCancelRequest extends WEB3AioCashinSettleCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_settle_cancel";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101430L;    
    
    /**
     * @@roseuid 4158EB330103
     */
    public WEB3AioCashinSettleCancelRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 注文要求中止レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinSettleCancelResponse(this);
    }
}
@
