head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込確認リクエスト(WEB3AioCashoutConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/25 周勇(中訊) レビュー
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込確認リクエスト)<BR>
 * 出金申込確認リクエストクラス<BR>
 *
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3AioCashoutConfirmRequest extends WEB3AioCashoutCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101050L; 
       
    /**
     * @@roseuid 4158EB620056
     */
    public WEB3AioCashoutConfirmRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金申込確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB5E01FF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashoutConfirmResponse(this);
    }
}
@
