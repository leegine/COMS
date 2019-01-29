head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCloseChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引訂正返済入力リクエストクラス(WEB3SuccMarginCloseChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeInputRequest;

/**
 * (（連続）信用取引訂正返済入力リクエストクラス)<BR>
 * （連続）信用取引訂正返済入力リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0 
 */
public class WEB3SuccMarginCloseChangeInputRequest extends WEB3MarginCloseMarginChangeInputRequest 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCloseChangeInput";
    
    /**
     * @@roseuid 4369CBED0222
     */
    public WEB3SuccMarginCloseChangeInputRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginCloseChangeInputResponse(this);
    }
}
@
