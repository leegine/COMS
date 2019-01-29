head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金受付リクエスト(WEB3AioCashinAcceptRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (入金受付リクエスト)<BR>
 * (入金受付リクエストクラス)<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashinAcceptRequest extends WEB3BackRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_cashin_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101505L;   
        
    /**
     * @@roseuid 4158E9B600C9
     */
    public WEB3AioCashinAcceptRequest() 
    {
     
    }

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金受付レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E9B600DD
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashinAcceptResponse(this);
    }
}
@
