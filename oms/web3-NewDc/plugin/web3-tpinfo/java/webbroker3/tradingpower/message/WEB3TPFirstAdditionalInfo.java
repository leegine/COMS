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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第一水準追証情報(WEB3TPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 張騰宇（中訊）新規作成 モデルNo.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (第一水準追証情報) <BR>
 * (第一水準追証情報)<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3TPFirstAdditionalInfo extends Message
{
    /**
     * (経過日数)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (有効経過日数)<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (発生日)<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (保証金率)<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (保証金維持率)<BR>
     */
    public String firstDepositRate;

    /**
     * (追証金額)<BR>
     */
    public String firstDepositAmount;

    /**
     * (追証決済必要額)<BR>
     */
    public String firstSettlement;

    /**
     * (保証金増減)<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (保証金増減(見込金額))<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (決済済建玉)<BR>
     */
    public String firstSettledContract;

    /**
     * (未解消金額)<BR>
     */
    public String firstUncancelAmt;

    /**
     * (未解消決済必要額)<BR>
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
