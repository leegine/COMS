head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : έΑΏΚβ~όΝζΚNGXg(WEB3AdminRuitoTradeStopInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 όE (u) VKμ¬
*/
package webbroker3.xbruito.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (έΑΏΚβ~όΝζΚNGXg)<BR>
 * έΑΏΚβ~όΝζΚNGXgNX
 */
public class WEB3AdminRuitoTradeStopInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_input";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;      

    /**
     * (έΑΏΚβ~όΝζΚNGXg)<BR>
     * ftHgRXgN^<BR>
     * @@roseuid 40922CA1005D
     */
    public WEB3AdminRuitoTradeStopInputRequest()
    {
    }

    /**
     * icreateResponseΜΐj<BR>
     * <BR>
     * έΑΏΚβ~όΝζΚX|XIuWFNgπΆ¬΅AΤp·ιB<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407150F201D7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminRuitoTradeStopInputResponse(this);
    }
}@
