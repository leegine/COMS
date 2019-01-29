head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecondAdddepositNotClearInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第二水準追証未解消情報(WEB3TPSecondAdddepositNotClearInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 仕様変更モデル308
*/
package webbroker3.tradingpower;

/**
 * (第二水準追証未解消情報)<BR>
 * (第二水準追証未解消情報)<BR>
 * <BR>
 * 第二水準追証の未解消金額に関する情報を格納するクラス<BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPSecondAdddepositNotClearInfo
{
    /**
     * (追証金額(未入金))<BR>
     * (追証金額(未入金))<BR>
     */
    public double secondDepositNonPay = 0;

    /**
     * (追証金額(請求2))<BR>
     * (追証金額(請求2))<BR>
     */
    public double secondDeposit2 = 0;

    /**
     * (追証金額(請求1))<BR>
     * (追証金額(請求1))<BR>
     */
    public double secondDeposit1 = 0;

    /**
     * (追証金額(見込金額))<BR>
     * (追証金額(見込金額))<BR>
     */
    public double secondDepositExpect = 0;

    /**
     * (決済必要額(未入金))<BR>
     * (決済必要額(未入金))<BR>
     */
    public double secondSettlementNonPay = 0;

    /**
     * (決済必要額(請求2))<BR>
     * (決済必要額(請求2))<BR>
     */
    public double secondSettlement2 = 0;

    /**
     * (決済必要額(請求1))<BR>
     * (決済必要額(請求1))<BR>
     */
    public double secondSettlement1 = 0;

    /**
     * (決済必要額(見込金額))<BR>
     * (決済必要額(見込金額))<BR>
     */
    public double secondSettlementExpect = 0;

    /**
     * @@roseuid 48F5856403E7
     */
    public WEB3TPSecondAdddepositNotClearInfo()
    {

    }
}
@
