head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoSecondAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第二水準追証情報(WEB3PvInfoSecondAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 柴双紅(中訊) 新規作成 モデルNo.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (第二水準追証表示情報)<BR>
 * 第二水準追証表示情報クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3PvInfoSecondAdditionalInfo extends Message
{
    /**
     * (取引停止区分)<BR>
     * 取引停止区分<BR>
     * <BR>
     * 0：取引可能<BR>
     * 1：取引停止中<BR>
     */
    public String secondTradeStopDiv;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * 保証金自動振替後判定フラグ<BR>
     * <BR>
     * false：保証金自動振替前<BR>
     * true ：保証金自動振替後<BR>
     */
    public boolean secondAutoTransferAfterJudgeFlag;

    /**
     * (期日(請求2))<BR>
     * 期日(請求2)<BR>
     */
    public Date secondCloseDate2;

    /**
     * (期日(請求1))<BR>
     * 期日(請求1)<BR>
     */
    public Date secondCloseDate1;

    /**
     * (期日(請求見込))<BR>
     * 期日(請求見込)<BR>
     */
    public Date secondCloseDateExpect;

    /**
     * (発生日(請求2))<BR>
     * 発生日(請求2)<BR>
     */
    public Date secondDepositOccurredDate2;

    /**
     * (発生日(請求1))<BR>
     * 発生日(請求1)<BR>
     */
    public Date secondDepositOccurredDate1;

    /**
     * (発生日(請求見込))<BR>
     * 発生日(請求見込)<BR>
     */
    public Date secondDepositOccurredDateExpect;

    /**
     * (保証金維持率)<BR>
     * 保証金維持率<BR>
     */
    public String secondDepositRate;

    /**
     * (保証金戻し維持率)<BR>
     * 保証金戻し維持率<BR>
     */
    public String secondDepositBackRate;

    /**
     * (保証金率(請求2))<BR>
     * 保証金率(請求2)<BR>
     */
    public String secondMarginDepositRate2;

    /**
     * (保証金率(請求1))<BR>
     * 保証金率(請求1)<BR>
     */
    public String secondMarginDepositRate1;

    /**
     * (保証金率(請求見込))<BR>
     * 保証金率(請求見込)<BR>
     */
    public String secondMarginDepositRateExpect;

    /**
     * (追証金額(未入金))<BR>
     * 追証金額(未入金)<BR>
     */
    public String secondDepositNonPay;

    /**
     * (追証金額(請求2))<BR>
     * 追証金額(請求2)<BR>
     */
    public String secondDeposit2;

    /**
     * (追証金額(請求1))<BR>
     * 追証金額(請求1)<BR>
     */
    public String secondDeposit1;

    /**
     * (追証決済必要額(未入金))<BR>
     * 追証決済必要額(未入金)<BR>
     */
    public String secondSettlementNonPay;

    /**
     * (追証決済必要額(請求2))<BR>
     * 追証決済必要額(請求2)<BR>
     */
    public String secondSettlement2;

    /***
     * (追証決済必要額(請求1))<BR>
     * 追証決済必要額(請求1)<BR>
     */
    public String secondSettlement1;

    /**
     * (保証金増減)<BR>
     * 保証金増減<BR>
     */
    public String secondMarginDepositInDe;

    /**
     * (保証金増減(見込金額))<BR>
     * 保証金増減(見込金額)<BR>
     */
    public String secondMarginDepositInDeExpect;

    /**
     * (決済済建玉)<BR>
     * 決済済建玉<BR>
     */
    public String secondSettledContract;

    /**
     * (未解消金額(未入金))<BR>
     * 未解消金額(未入金)<BR>
     */
    public String secondUncancelAmtNonPay;

    /**
     * (未解消金額(請求2))<BR>
     * 未解消金額(請求2)<BR>
     */
    public String secondUncancelAmt2;

    /**
     * (未解消金額(請求1))<BR>
     * 未解消金額(請求1)<BR>
     */
    public String secondUncancelAmt1;

    /**
     * (未解消金額(請求見込))<BR>
     * 未解消金額(請求見込)<BR>
     */
    public String secondUncancelAmtExpect;

    /**
     * (未解消決済必要額(未入金))<BR>
     * 未解消決済必要額(未入金)<BR>
     */
    public String secondUncancelSettleRequiredAmtNonPay;

    /**
     * (未解消決済必要額(請求2))<BR>
     * 未解消決済必要額(請求2)<BR>
     */
    public String secondUncancelSettleRequiredAmt2;

    /**
     * (未解消決済必要額(請求1))<BR>
     * 未解消決済必要額(請求1)<BR>
     */
    public String secondUncancelSettleRequiredAmt1;

    /**
     * (未解消決済必要額(請求見込))<BR>
     * 未解消決済必要額(請求見込)<BR>
     */
    public String secondUncancelSettleRequiredAmtExpect;

    /**
     * (第二水準追証発生表示情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3PvInfoSecondAdditionalInfo()
    {

    }
}
@
