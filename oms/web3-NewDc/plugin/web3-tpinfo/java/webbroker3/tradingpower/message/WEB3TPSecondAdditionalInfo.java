head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æñÇØîñ(WEB3TPSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 £«FiujVKì¬ fNo.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (æñÇØîñ) <BR>
 * (æñÇØîñ)<BR>
 * <BR>
 * @@author £«F
 * @@version 1.0
 */
public class WEB3TPSecondAdditionalInfo extends Message
{
    /**
     * (úú(¿2))<BR>
     */
    public Date secondCloseDate2;

    /**
     * (úú(¿1))<BR>
     */
    public Date secondCloseDate1;

    /**
     * (úú(¿©))<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (­¶ú(¿2))<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (­¶ú(¿1))<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (­¶ú(¿©))<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (ÛØàÛ¦)<BR>
     */
    public String secondDepositRate;

    /**
     * (ÛØàßµÛ¦)<BR>
     */
    public String secondDepositBackRate;

    /**
     * (ÛØà¦(¿2))<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (ÛØà¦(¿1))<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (ÛØà¦(¿©))<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (ÇØàz(¢üà))<BR>
     */
    public String secondDepositNonPay;

    /**
     * (ÇØàz(¿2))<BR>
     */
    public String secondDeposit2;

    /**
     * (ÇØàz(¿1))<BR>
     */
    public String secondDeposit1;

    /**
     * (ÇØÏKvz(¢üà))<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (ÇØÏKvz(¿2))<BR>
     */
    public String secondSettlement2;

    /**
     * (ÇØÏKvz(¿1))<BR>
     */
    public String secondSettlement1;

    /**
     * (ÛØà¸)<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (ÛØà¸(©àz))<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (ÏÏÊ)<BR>
     */
    public String secondSettledContract;

    /**
     * (¢ðÁàz(¢üà))<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (¢ðÁàz(¿2))<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (¢ðÁàz(¿1))<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (¢ðÁàz(¿©))<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (¢ðÁÏKvz(¢üà))<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (¢ðÁÏKvz(¿2))<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (¢ðÁÏKvz(¿1))<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (¢ðÁÏKvz(¿©))<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect;

    /**
     * @@roseuid 48EC703400C2
     */
    public WEB3TPSecondAdditionalInfo()
    {

    }
}
@
