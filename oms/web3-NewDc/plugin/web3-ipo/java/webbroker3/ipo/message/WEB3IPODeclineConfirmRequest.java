head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.40.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODeclineConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO辞退確認リクエストクラス(WEB3IPODeclineConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 彭巍 (中訊) 新規作成
*/
package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * IPO辞退確認リクエストクラス]
 * 
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3IPODeclineConfirmRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "IPO_declineConfirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408100942L;
    
    /**
     * (ID)<BR>
     * IPO申告ＩＤ
     */
    public String id;
    
    /**
     * @@roseuid 4112E4E00367
     */
    public WEB3IPODeclineConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E4E00399
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3IPODeclineConfirmResponse(this);
    }
}
@
