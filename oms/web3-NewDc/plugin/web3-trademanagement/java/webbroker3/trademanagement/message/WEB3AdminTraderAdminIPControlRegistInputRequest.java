head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒEOCÛIPo^NGXg(WEB3AdminTraderAdminIPControlRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 £«F (u) VKì¬ f004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ÇÒEOCÛIPo^NGXg)<BR>
 * ÇÒEOCÛIPo^NGXgNXB<BR>
 * @@author £«F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlRegistInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221808L;

    /**
     * @@roseuid 48D75CD7002D
     */
    public WEB3AdminTraderAdminIPControlRegistInputRequest()
    {

    }

    /**
     * X|Xf[^ðì¬·éB<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlRegistInputResponse(this);
    }
}
@
