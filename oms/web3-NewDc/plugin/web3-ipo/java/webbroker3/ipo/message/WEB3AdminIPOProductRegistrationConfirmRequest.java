head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.35.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿VKo^mFNGXg(WEB3AdminIPOProductRegistrationConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 o£ VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒIPOÁ¿VKo^mFNGXg)<BR>
 * IPOÁ¿VKo^mFNGXgNX
 * 
 * @@author o£
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161043L;
    
    /**
     * (Á¿îñ)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112DF8D014B
     */
    public WEB3AdminIPOProductRegistrationConfirmRequest() 
    {
     
    }
    
    /**
     * this.IPOÁ¿îñ.validate() ðR[·éB
     * @@roseuid 40C410300147
     */
    public void validate() throws WEB3BaseException
    {
        this.ipoProductInfo.validate();
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D015F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationConfirmResponse(this);
    }
}
@
