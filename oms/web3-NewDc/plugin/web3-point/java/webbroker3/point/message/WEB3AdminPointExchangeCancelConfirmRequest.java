head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換取消確認リクエスト(WEB3AdminPointExchangeCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント交換取消確認リクエスト)<BR>
 * ポイント交換取消確認リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointExchangeCancelConfirmRequest extends WEB3AdminPointExchangeCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_exchangeCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290100L;
    
    /**
     * @@roseuid 41D1254B002E
     */
    public WEB3AdminPointExchangeCancelConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125480167
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointExchangeCancelConfirmResponse(this);
    }

}
@
