head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : æêÇØîñ(WEB3AdminTPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Ð±ì (u) VKì¬ fNo.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (æêÇØîñ)<BR>
 * <BR>
 * @@author Ð±ì
 * @@version 1.0
 */
public class WEB3AdminTPFirstAdditionalInfo extends Message
{

    /**
     * (oßú)<BR>
     */
    public String firstDepositPassDay = "0";

    /**
     * (Løoßú)<BR>
     */
    public String firstDepositPassDayValid = "0";

    /**
     * (­¶ú)<BR>
     */
    public Date firstDepositOccurredDate = null;

    /**
     * (ÛØà¦)<BR>
     */
    public String firstMarginDepositRate = "0";

    /**
     * (ÛØàÛ¦)<BR>
     */
    public String firstDepositRate = "0";

    /**
     * (ÇØàz)<BR>
     */
    public String firstDepositAmount = "0";

    /**
     * (ÇØÏKvz)<BR>
     */
    public String firstSettlement = "0";

    /**
     * (ÛØà¸)<BR>
     */
    public String firstMarginDepositInDe = "0";

    /**
     * (ÛØà¸(©àz))<BR>
     */
    public String firstMarginDepositInDeExpect = "0";

    /**
     * (ÏÏÊ)<BR>
     */
    public String firstSettledContract = "0";

    /**
     * (¢ðÁàz)<BR>
     */
    public String firstUncancelAmt = "0";

    /**
     * (¢ðÁÏKvz)<BR>
     */
    public String firstUncancelSettleRequiredAmt = "0";

    /**
     * @@roseuid 48EC703400C4
     */
    public WEB3AdminTPFirstAdditionalInfo()
    {

    }
}
@
