head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualConditionsRefInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : MυΑΏπo^όΝNGXg(WEB3AdminMutualConditionsRefInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 © (u) VKμ¬
                   2004/08/23 °όν (u) r[ 
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * MυΑΏπo^όΝNGXg<BR>
 * 
 * @@author ©(u)
 * @@version 1.0 
 */

public class WEB3AdminMutualConditionsRefInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_conditions_ref_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408131330L;
 
    /**
     * (MΑΏπo^ΖοόΝNGXg)<BR>
     * ftHgRXgN^<BR>
     * @@roseuid 40DF772F01FA
     */
    public WEB3AdminMutualConditionsRefInputRequest() 
    {
     
    }
    
    /**
     * icreateResponseΜΐj<BR>
     * <BR>
     * MΑΏπo^ΖοόΝX|XIuWFNgπΆ¬΅ΔΤ·B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40DF773A02C6
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminMutualConditionsRefInputResponse(this);
    }
}
@
