head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引注文取消確認リクエストクラス(WEB3SuccMarginCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCancelConfirmRequest;

/**
 * (（連続）信用取引注文取消確認リクエストクラス)<BR>
 * （連続）信用取引注文取消確認リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0 
 */
public class WEB3SuccMarginCancelConfirmRequest extends WEB3MarginCancelConfirmRequest 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCancelConfirm";
    
    /**
     * @@roseuid 4369CC7E032C
     */
    public WEB3SuccMarginCancelConfirmRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginCancelConfirmResponse(this);
    }
}
@
