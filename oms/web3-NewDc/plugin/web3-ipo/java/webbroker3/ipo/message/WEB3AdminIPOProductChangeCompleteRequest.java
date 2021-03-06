head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.37.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIPOProductChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒIPOÁ¿ù³®¹NGXg(WEB3AdminIPOProductChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 dÙ (u) VKì¬
*/

package webbroker3.ipo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒIPOÁ¿ù³®¹NGXg)<BR>
 * ÇÒIPOÁ¿ù³®¹NGXgf[^NX
 * 
 * @@author dÙ
 * @@version 1.0
 */
public class WEB3AdminIPOProductChangeCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_IPO_productChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200408161024L;
    
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
     * (Á¿îñ)<BR>
     */
    public WEB3IPOProductInfo ipoProductInfo;
    
    /**
     * @@roseuid 4112E37F03C4
     */
    public WEB3AdminIPOProductChangeCompleteRequest() 
    {
     
    }
    
    /**
     * this.IPOÁ¿îñ.validate() ðR[·éB
     * @@roseuid 40CE765D0113
     */
    public void validate() throws WEB3BaseException 
    {
        this.ipoProductInfo.validate();
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4112E3800018
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminIPOProductChangeCompleteResponse(this);
    }
}
@
