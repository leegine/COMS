head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : T[rXpวาฺqo^ฎนNGXg(WEB3AdminSrvRegiCustomerRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ฃw VK์ฌ
*/

package webbroker3.srvregi.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (T[rXpวาฺqo^ฎนNGXg)<BR>
 * T[rXpวาฺqo^ฎนNGXgNX<BR>
 * @@author ฃw
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerRegistCompleteRequest extends WEB3AdminSrvRegiCustomerRegistCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerRegistComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;
    
    /**
     * (รุิ)
     */
    public String password;
    
    /**
     * (T[rXpวาฺqo^ฎนNGXg)<BR>
     * ftHgRXgN^<BR>
     * @@roseuid 40F22F94028F
     */
    public WEB3AdminSrvRegiCustomerRegistCompleteRequest() 
    {
     
    }
    
    /**
     * (createX|X)<BR>
     * T[rXpวาฺqo^ฎนX|X๐ถฌตฤิpท้B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F22F9402AF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiCustomerRegistCompleteResponse(this);
    }
}
@
