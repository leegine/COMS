head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.36.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿í®¹NGXg(WEB3AdminIPOProductDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 o£ VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒIPOÁ¿í®¹NGXg)<BR>
 * ÇÒIPOÁ¿í®¹NGXgf[^NX
 * 
 * @@author o£
 * @@version 1.0
 */
public class WEB3AdminIPOProductDeleteCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_IPO_productDeleteComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408161032L;
    
    /**
     * (hc)<BR>
     * IPOÁ¿hc
     */
    public String id;
    
    /**
     * ÃØÔ
     */
    public String password;
    
    /**
     * @@roseuid 4112E3390369
     */
    public WEB3AdminIPOProductDeleteCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E339039B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductDeleteCompleteResponse(this);
    }
}
@
