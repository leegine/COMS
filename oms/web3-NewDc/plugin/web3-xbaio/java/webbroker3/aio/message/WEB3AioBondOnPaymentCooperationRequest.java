head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioBondOnPaymentCooperationRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΒoΰAgNGXg(WEB3AioBondOnPaymentCooperationRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 GΜ (u) VKμ¬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * (ΒoΰAgNGXg)<BR>
 * ΒoΰAgNGXgNX<BR>
 * <BR>
 * @@author GΜ(u)
 * @@version 1.0
 */
public class WEB3AioBondOnPaymentCooperationRequest
    extends WEB3AioOnPaymentCooperationRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_bond_on_payment_cooperation";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610170915L;

    /**
     * RXgN^
     */
    public WEB3AioBondOnPaymentCooperationRequest()
    {

    }

    /**
     *icreateResponseΜΐj<BR>
     * <BR>
     * ΒoΰAgX|XIuWFNgπΤp·ιB<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AioBondOnPaymentCooperationResponse(this);
    }
}
@
