head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoDepShortageInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : Ψΰs«σ΅ξρ(WEB3IfoDepShortageInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 Κζ(u) VKμ¬ fNo.004
*/
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (Ψΰs«σ΅ξρ)<BR>
 * Ψΰs«σ΅ξρNX<BR>
 * <BR>
 * @@author Κζ(u)
 * @@version 1.0
 */
public class WEB3IfoDepShortageInfo extends Message
{

    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;

    /**
     * (ΪqR[h)<BR>
     * ΪqR[h<BR>
     */
    public String accountCode;

    /**
     * (ΪqΌ)<BR>
     * ΪqΌ<BR>
     */
    public String accountName;

    /**
     * (Ώz)<BR>
     */
    public String claimAmount;

    /**
     * (»έ’όΰz)<BR>
     */
    public String curNonPayAmt = null;

    /**
     * (»έΨΰvz)<BR>
     */
    public String curIfoDepositNecessaryAmt;

    /**
     * (ΚL³tO)<BR>
     * ΚL³tO<BR>
     * iOP­j<BR>
     * <BR>
     * trueF@@L <BR>
     * falseF@@³<BR>
     * <BR>
     */
    public boolean contractExistFlag;

    /**
     * (ΆL³tO)<BR>
     * ΆL³tO<BR>
     * iOP­j<BR>
     * <BR>
     * trueF@@L<BR>
     * falseF@@³<BR>
     * <BR>
     * <BR>
     */
    public boolean orderExistFlag;

    /**
     * (Ψΰs«σ΅ξρ)<BR>
     * RXgN^<BR>
     * @@roseuid 4998F2BB0071
     */
    public WEB3IfoDepShortageInfo()
    {

    }
}
@
