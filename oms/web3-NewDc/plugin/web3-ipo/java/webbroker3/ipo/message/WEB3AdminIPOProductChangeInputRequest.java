head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.39.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿ù³üÍNGXg(WEB3AdminIPOProductChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 dÙ (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒIPOÁ¿ù³üÍNGXg)<BR>
 * ÇÒIPOÁ¿ù³üÍNGXgf[^NX
 * 
 * @@author dÙ
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161038L;
    
    /**
     * (hc)<BR>
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * @@roseuid 4112E37F016B
     */
    public WEB3AdminIPOProductChangeInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E37F017F
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductChangeInputResponse(this);
    }
}
@
