head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderAcceptHistoryUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¶ótð(WEB3BondOrderAcceptHistoryUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 g (u) VKì¬ dlÏXEfNo.216
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (¶ótð)<BR>
 * ¶ótð<BR>
 * <BR>
 * @@author g
 * @@version 1.0
 */
public class WEB3BondOrderAcceptHistoryUnit extends Message
{

    /**
     * (¶ótú)<BR>
     * ¶ótú<BR>
     */
    public Date orderDate;

    /**
     * (¶àz)<BR>
     * ¶àz<BR>
     */
    public String orderAmount;

    /**
     * (¶)<BR>
     * ¶<BR>
     */
    public String orderNumber;

    /**
     * (Ýv)<BR>
     * Ýv<BR>
     */
    public String accumulatedTotal;

    /**
     * (¶ótð)<BR>
     * RXgN^<BR>
     * @@roseuid 46637A000148
     */
    public WEB3BondOrderAcceptHistoryUnit()
    {

    }
}
@
