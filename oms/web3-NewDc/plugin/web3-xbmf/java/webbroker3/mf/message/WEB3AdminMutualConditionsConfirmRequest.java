head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : MυΑΏπo^mFNGXg(WEB3AdminMutualConditionsConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 © (u) VKμ¬
                   2004/08/23 °όν (u) r[ 
                   2004/12/10 © (u) c
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * MυΑΏπo^mFNGXg<BR>
 * 
 * @@author ©(u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsConfirmRequest 
    extends WEB3MutualProductConditionsCommonRequest 
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131015L;
    
    /**
     * (MΑΏπo^mFNGXg)<BR>
     * ftHgRXgN^<BR>
     * @@roseuid 40DF812F021A
     */
    public WEB3AdminMutualConditionsConfirmRequest() 
    {
     
    }
    
    /**
     * icreateResponseΜΐj<BR>
     * <BR>
     * MΑΏπo^mFX|XIuWFNgπΆ¬΅ΔΤ·B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF81440100
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsConfirmResponse(this);
    }
}
@
