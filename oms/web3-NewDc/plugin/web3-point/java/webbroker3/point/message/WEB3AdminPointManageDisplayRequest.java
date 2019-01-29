head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointManageDisplayRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント管理画面リクエスト(WEB3AdminPointManageDisplayRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント管理画面リクエスト)<BR>
 * ポイント管理画面リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointManageDisplayRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_manageDisplay";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290085L;
    
    /**
     * @@roseuid 41D1254C02FD
     */
    public WEB3AdminPointManageDisplayRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254C031C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointManageDisplayResponse(this);
    }
}
@
