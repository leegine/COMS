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
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : ‘æ“ñ…€’ÇØî•ñ(WEB3TPSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 ’£“«‰Fi’†ujV‹Kì¬ ƒ‚ƒfƒ‹No.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (‘æ“ñ…€’ÇØî•ñ) <BR>
 * (‘æ“ñ…€’ÇØî•ñ)<BR>
 * <BR>
 * @@author ’£“«‰F
 * @@version 1.0
 */
public class WEB3TPSecondAdditionalInfo extends Message
{
    /**
     * (Šú“ú(¿‹2))<BR>
     */
    public Date secondCloseDate2;

    /**
     * (Šú“ú(¿‹1))<BR>
     */
    public Date secondCloseDate1;

    /**
     * (Šú“ú(¿‹Œ©))<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (”­¶“ú(¿‹2))<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (”­¶“ú(¿‹1))<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (”­¶“ú(¿‹Œ©))<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (•ÛØ‹àˆÛ—¦)<BR>
     */
    public String secondDepositRate;

    /**
     * (•ÛØ‹à–ß‚µˆÛ—¦)<BR>
     */
    public String secondDepositBackRate;

    /**
     * (•ÛØ‹à—¦(¿‹2))<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (•ÛØ‹à—¦(¿‹1))<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (•ÛØ‹à—¦(¿‹Œ©))<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (’ÇØ‹àŠz(–¢“ü‹à))<BR>
     */
    public String secondDepositNonPay;

    /**
     * (’ÇØ‹àŠz(¿‹2))<BR>
     */
    public String secondDeposit2;

    /**
     * (’ÇØ‹àŠz(¿‹1))<BR>
     */
    public String secondDeposit1;

    /**
     * (’ÇØŒˆÏ•K—vŠz(–¢“ü‹à))<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (’ÇØŒˆÏ•K—vŠz(¿‹2))<BR>
     */
    public String secondSettlement2;

    /**
     * (’ÇØŒˆÏ•K—vŠz(¿‹1))<BR>
     */
    public String secondSettlement1;

    /**
     * (•ÛØ‹à‘Œ¸)<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (•ÛØ‹à‘Œ¸(Œ©‹àŠz))<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (ŒˆÏÏŒš‹Ê)<BR>
     */
    public String secondSettledContract;

    /**
     * (–¢‰ğÁ‹àŠz(–¢“ü‹à))<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (–¢‰ğÁ‹àŠz(¿‹2))<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (–¢‰ğÁ‹àŠz(¿‹1))<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (–¢‰ğÁ‹àŠz(¿‹Œ©))<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (–¢‰ğÁŒˆÏ•K—vŠz(–¢“ü‹à))<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (–¢‰ğÁŒˆÏ•K—vŠz(¿‹2))<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (–¢‰ğÁŒˆÏ•K—vŠz(¿‹1))<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (–¢‰ğÁŒˆÏ•K—vŠz(¿‹Œ©))<BR>
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
