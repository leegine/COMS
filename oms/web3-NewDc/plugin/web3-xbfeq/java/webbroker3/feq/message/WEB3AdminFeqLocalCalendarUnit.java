head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqLocalCalendarUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : »nJ_[ξρ(WEB3AdminFeqLocalCalendarUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKμ¬
                 : 2005/08/02 €ϋU (u) r[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (»nJ_[ξρ)<BR>
 * »nJ_[ξρNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqLocalCalendarUnit extends Message 
{
    
    /**
     * (ϊt)<BR>
     * ϊt
     */
    public Date bizDate;
    
    /**
     * (ϊtζͺ)<BR>
     * ϊtζͺ<BR>
     * <BR>
     * 0FρcΖϊ<BR>
     * 1FcΖϊ<BR>
     * <BR>
     * ¦VKo^A’IπΜκΝAnull<BR>
     * ¦NGXgΕZbg³κιlΝAΟXγΜlB
     */
    public String bizDateDiv;
    
    /**
     * (XVϊ)<BR>
     * XVϊ<BR>
     * <BR>
     * ¦VKo^ΜκΝAnull
     */
    public Date updateDate;
    
    /**
     * (»nJ_[ξρ)<BR>
     * RXgN^
     * @@roseuid 4200C1A30133
     */
    public WEB3AdminFeqLocalCalendarUnit() 
    {
     
    }
}
@
