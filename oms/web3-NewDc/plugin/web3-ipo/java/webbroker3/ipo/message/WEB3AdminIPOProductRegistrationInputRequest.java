head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductRegistrationInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿VKo^üÍNGXg(WEB3AdminIPOProductRegistrationInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 o£ VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒIPOÁ¿VKo^üÍNGXg)<BR>
 * IPOÁ¿VKo^üÍNGXgNX
 * 
 * @@author o£
 * @@version 1.0
 */
public class WEB3AdminIPOProductRegistrationInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productRegistrationInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161058L;
    
    /**
     * @@roseuid 4112DF8D005A
     */
    public WEB3AdminIPOProductRegistrationInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112DF8D006E
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductRegistrationInputResponse(this);
    }
}
@
