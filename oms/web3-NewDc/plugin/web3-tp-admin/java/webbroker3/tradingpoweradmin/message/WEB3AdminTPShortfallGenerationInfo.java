head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : s«à­¶îñ(WEB3AdminTPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Ð±ì (u) VKì¬ fNo.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (s«à­¶îñ)<BR>
 * <BR>
 * @@author Ð±ì
 * @@version 1.0
 */
public class WEB3AdminTPShortfallGenerationInfo extends Message
{

    /**
     * (úú(T+0))<BR>
     */
    public Date closeDate0 = null;

    /**
     * (úú(T+1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (úú(T+2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (úú(T+3))<BR>
     */
    public Date closeDate3 = null;

    /**
     * (úú(T+4))<BR>
     */
    public Date closeDate4 = null;

    /**
     * (úú(T+5))<BR>
     */
    public Date closeDate5 = null;

    /**
     * (Kvüàz(T+0))<BR>
     */
    public String requiredPayAmt0 = "0";

    /**
     * (Kvüàz(T+1))<BR>
     */
    public String requiredPayAmt1 = "0";

    /**
     * (Kvüàz(T+2))<BR>
     */
    public String requiredPayAmt2 = "0";

    /**
     * (Kvüàz(T+3))<BR>
     */
    public String requiredPayAmt3 = "0";

    /**
     * (Kvüàz(T+4))<BR>
     */
    public String requiredPayAmt4 = "0";

    /**
     * (Kvüàz(T+5))<BR>
     */
    public String requiredPayAmt5 = "0";

    /**
     * (¸Zz(T+0))<BR>
     */
    public String adjustedAmt0 = "0";

    /**
     * (¸Zz(T+1))<BR>
     */
    public String adjustedAmt1 = "0";

    /**
     * (úvèS©à(T+0))<BR>
     */
    public String dayTradeRestraint0 = "0";

    /**
     * (úvèS©à(T+1))<BR>
     */
    public String dayTradeRestraint1 = "0";

    /**
     * (ÛØà©çÌUÖz(T+0))<BR>
     */
    public String transferFromMarginDeposit0 = "0";

    /**
     * (ÛØà©çÌUÖz(T+1))<BR>
     */
    public String transferFromMarginDeposit1 = "0";

    /**
     * @@roseuid 48EC7033019F
     */
    public WEB3AdminTPShortfallGenerationInfo()
    {

    }
}
@
