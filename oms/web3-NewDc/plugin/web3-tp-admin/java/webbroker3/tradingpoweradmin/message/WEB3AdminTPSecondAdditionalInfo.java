head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æñÇØîñ(WEB3AdminTPSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Ð±ì (u) VKì¬ fNo.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (æñÇØîñ)<BR>
 * <BR>
 * @@author Ð±ì
 * @@version 1.0
 */
public class WEB3AdminTPSecondAdditionalInfo extends Message
{

    /**
     * (úú(¿2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (úú(¿1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (úú(¿©))<BR>
     */
    public Date closeDateExpect = null;

    /**
     * (­¶ú(¿2))<BR>
     */
    public Date secondDepositOccurredDate2 = null;

    /**
     * (­¶ú(¿1))<BR>
     */
    public Date secondDepositOccurredDate1 = null;

    /**
     * (­¶ú(¿©))<BR>
     */
    public Date secondDepositOccurredDateExpect = null;

    /**
     * (ÛØàÛ¦)<BR>
     */
    public String secondDepositRate = "0";

    /**
     * (ÛØàßµÛ¦)<BR>
     */
    public String secondDepositBackRate = "0";

    /**
     * (ÛØà¦(¿2))<BR>
     */
    public String secondMarginDepositRate2 = "0";

    /**
     * (ÛØà¦(¿1))<BR>
     */
    public String secondMarginDepositRate1 = "0";

    /**
     * (ÛØà¦(¿©))<BR>
     */
    public String secondMarginDepositRateExpect = "0";

    /**
     * (ÇØàz(¢üà))<BR>
     */
    public String secondDepositNonPay = "0";

    /**
     * (ÇØàz(¿2))<BR>
     */
    public String secondDeposit2 = "0";

    /**
     * (ÇØàz(¿1))<BR>
     */
    public String secondDeposit1 = "0";

    /**
     * (ÇØÏKvz(¢üà))<BR>
     */
    public String secondSettlementNonPay = "0";

    /**
     * (ÇØÏKvz(¿2))<BR>
     */
    public String secondSettlement2 = "0";

    /**
     * (ÇØÏKvz(¿1))<BR>
     */
    public String secondSettlement1 = "0";

    /**
     * (ÛØà¸)<BR>
     */
    public String secondMarginDepositInDe = "0";

    /**
     * (ÛØà¸(©àz))<BR>
     */
    public String secondMarginDepositInDeExpect = "0";

    /**
     * (ÏÏÊ)<BR>
     */
    public String secondSettledContract = "0";

    /**
     * (¢ðÁàz(¢üà))<BR>
     */
    public String secondUncancelAmtNonPay = "0";

    /**
     * (¢ðÁàz(¿2))<BR>
     */
    public String secondUncancelAmt2 = "0";

    /**
     * (¢ðÁàz(¿1))<BR>
     */
    public String secondUncancelAmt1 = "0";

    /**
     * (¢ðÁàz(¿©))<BR>
     */
    public String secondUncancelAmtExpect = "0";

    /**
     * (¢ðÁÏKvz(¢üà))<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay = "0";

    /**
     * (¢ðÁÏKvz(¿2))<BR>
     */
    public String secondUncancelSettleRequiredAmt2 = "0";

    /**
     * (¢ðÁÏKvz(¿1))<BR>
     */
    public String secondUncancelSettleRequiredAmt1 = "0";

    /**
     * (¢ðÁÏKvz(¿©))<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect = "0";

    /**
     * @@roseuid 48EC70330383
     */
    public WEB3AdminTPSecondAdditionalInfo()
    {

    }
}
@
