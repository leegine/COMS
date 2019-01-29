head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第一水準追証情報(WEB3PvInfoFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 柴双紅(中訊) 新規作成 モデルNo.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (第一水準追証表示情報)<BR>
 * 第一水準追証表示情報クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3PvInfoFirstAdditionalInfo extends Message
{
    /**
     * (取引停止区分)<BR>
     * 取引停止区分<BR>
     * <BR>
     * 0：取引可能<BR>
     * 1：取引停止中<BR>
     */
    public String firstTradeStopDiv;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * 保証金自動振替後判定フラグ<BR>
     * <BR>
     * false：保証金自動振替前<BR>
     * true ：保証金自動振替後<BR>
     */
    public boolean firstAutoTransferAfterJudgeFlag;

    /**
     * (経過日数)<BR>
     * 経過日数<BR>
     */
    public String firstDepositPassDay;

    /**
     * (有効経過日数)<BR>
     * 有効経過日数<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (発生日)<BR>
     * 発生日<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (保証金率)<BR>
     * 保証金率<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (保証金維持率)<BR>
     * 保証金維持率<BR>
     */
    public String firstDepositRate;

    /**
     * (追証金額)<BR>
     * 追証金額<BR>
     */
    public String firstDepositAmount;

    /**
     * (追証決済必要額)<BR>
     * 追証決済必要額<BR>
     */
    public String firstSettlement;

    /**
     * (保証金増減)<BR>
     * 保証金増減<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (保証金増減(見込金額))<BR>
     * 保証金増減(見込金額)<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (決済済建玉)<BR>
     * 決済済建玉<BR>
     */
    public String firstSettledContract;

    /**
     * (未解消金額)<BR>
     * 未解消金額<BR>
     */
    public String firstUncancelAmt;

    /**
     * (未解消決済必要額)<BR>
     * 未解消決済必要額<BR>
     */
    public String firstUncancelSettleRequiredAmt;

    /**
     * (第一水準追証発生表示情報)<BR>
     * コンストラクタ<BR>
     */
    public WEB3PvInfoFirstAdditionalInfo()
    {
        
    }
}
@
