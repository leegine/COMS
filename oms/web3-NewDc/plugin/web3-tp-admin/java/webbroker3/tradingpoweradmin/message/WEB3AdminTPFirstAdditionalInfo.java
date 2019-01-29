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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第一水準追証情報(WEB3AdminTPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (第一水準追証情報)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPFirstAdditionalInfo extends Message
{

    /**
     * (経過日数)<BR>
     */
    public String firstDepositPassDay = "0";

    /**
     * (有効経過日数)<BR>
     */
    public String firstDepositPassDayValid = "0";

    /**
     * (発生日)<BR>
     */
    public Date firstDepositOccurredDate = null;

    /**
     * (保証金率)<BR>
     */
    public String firstMarginDepositRate = "0";

    /**
     * (保証金維持率)<BR>
     */
    public String firstDepositRate = "0";

    /**
     * (追証金額)<BR>
     */
    public String firstDepositAmount = "0";

    /**
     * (追証決済必要額)<BR>
     */
    public String firstSettlement = "0";

    /**
     * (保証金増減)<BR>
     */
    public String firstMarginDepositInDe = "0";

    /**
     * (保証金増減(見込金額))<BR>
     */
    public String firstMarginDepositInDeExpect = "0";

    /**
     * (決済済建玉)<BR>
     */
    public String firstSettledContract = "0";

    /**
     * (未解消金額)<BR>
     */
    public String firstUncancelAmt = "0";

    /**
     * (未解消決済必要額)<BR>
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
