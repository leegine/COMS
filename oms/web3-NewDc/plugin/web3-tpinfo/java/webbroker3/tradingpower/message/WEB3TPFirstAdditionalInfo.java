head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æêÇØîñ(WEB3TPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 £«FiujVKì¬ fNo.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (æêÇØîñ) <BR>
 * (æêÇØîñ)<BR>
 * <BR>
 * @@author £«F
 * @@version 1.0
 */
public class WEB3TPFirstAdditionalInfo extends Message
{
    /**
     * (oßú)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (Løoßú)<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (­¶ú)<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (ÛØà¦)<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (ÛØàÛ¦)<BR>
     */
    public String firstDepositRate;

    /**
     * (ÇØàz)<BR>
     */
    public String firstDepositAmount;

    /**
     * (ÇØÏKvz)<BR>
     */
    public String firstSettlement;

    /**
     * (ÛØà¸)<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (ÛØà¸(©àz))<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (ÏÏÊ)<BR>
     */
    public String firstSettledContract;

    /**
     * (¢ðÁàz)<BR>
     */
    public String firstUncancelAmt;

    /**
     * (¢ðÁÏKvz)<BR>
     */
    public String firstUncancelSettleRequiredAmt;

    /**
     * @@roseuid 48EC703400C1
     */
    public WEB3TPFirstAdditionalInfo()
    {

    }
}
@
