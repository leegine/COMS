head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.04.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ų\[VVXeęń
File Name        : ĻAg|[güĶNGXgNX(WEB3AdminAioSettleReportInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ččOął (u) VKģ¬
                   2004/10/27 üE(u) r[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ĻAg|[güĶNGXg)<BR>
 * ĻAg|[güĶNGXgNX
 * 
 * @@author ččOął(u)
 * @@version 1.0 
 */

public class WEB3AdminAioSettleReportInputRequest extends WEB3GenRequest 
{        
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_settle_report_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410121142L;   
  
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB67000D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioSettleReportInputResponse(this);
    }
}
@
