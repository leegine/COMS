head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeRequestNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : UΦΏΚmNGXgNX(WEB3AccTransChangeRequestNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 °όν (u) VKμ¬
                   2004/10/25 όE(u) r[
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (UΦΏΚmNGXg)<BR>
 * (UΦΏΚmNGXgNX)
 * 
 * @@author °όν(u)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestNotifyRequest extends WEB3BackRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_request_notify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
        
    
    /**
     * @@roseuid 4158E8E3036F
     */
    public WEB3AccTransChangeRequestNotifyRequest() 
    {
     
    }
    
    /**
     * icreateResponseΜΐj <BR>
     * <BR>
     * UΦΏΚmX|XIuWFNgπΆ¬΅ΔΤ·B <BR>
     * @@return WEB3BackResponse
     * @@roseuid 4158E8E30383
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AccTransChangeRequestNotifyResponse(this);
    }
}
@
