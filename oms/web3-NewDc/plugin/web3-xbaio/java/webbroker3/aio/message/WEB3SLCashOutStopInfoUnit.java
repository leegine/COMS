head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLCashOutStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : oàâ~îñ(WEB3SLCashOutStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 àiujVKì¬ dlÏXf764
*/
package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (oàâ~îñ)<BR>
 * oàâ~îñNX<BR>
 *
 * @@author à
 * @@version 1.0
 */
public class WEB3SLCashOutStopInfoUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709140917L;

    /**
     * (ûÀID)<BR>
     */
    public long accountId;

    /**
     * (XR[h)<BR>
     */
    public String branchCode;

    /**
     * (ÚqR[h)<BR>
     */
    public String accountCode;

    /**
     * (Úq¼)<BR>
     */
    public String accountName;

    /**
     * (pÂ\g)<BR>
     */
    public String cashoutLimit;

    /**
     * (oàS©à)<BR>
     */
    public String cashoutRestraint;

    /**
     * (oàÂ\z)<BR>
     */
    public String cashoutPossAmt;

    /**
     * (oàâ~æª)<BR>
     * <BR>
     * 0FÊí<BR>
     * 1Fâ~<BR>
     */
    public String cashoutStopDiv;

}
@
