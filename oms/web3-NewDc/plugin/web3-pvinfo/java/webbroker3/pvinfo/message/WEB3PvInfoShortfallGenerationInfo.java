head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生情報(WEB3PvInfoShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 劉仁和(中訊) 新規作成 モデルNo.109
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (不足金発生表示情報)<BR>
 * 不足金発生表示情報クラス<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3PvInfoShortfallGenerationInfo extends Message
{
    /**
     * (取引停止区分)<BR>
     * 取引停止区分<BR>
     * <BR>
     * 0：取引可能<BR>
     * 1：取引停止中<BR>
     */
	public String tradeStopDiv;

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * 保証金自動振替後判定フラグ<BR>
     * <BR>
     * false：保証金自動振替前<BR>
     * true：保証金自動振替後<BR>
     */
	public boolean autoTransferAfterJudgeFlag;

    /**
     * (期日(T+0))<BR>
     * 期日(T+0)<BR>
     */
	public Date closeDate0;

    /**
     * (期日(T+1))<BR>
     * 期日(T+1)<BR>
     */
	public Date closeDate1;

    /**
     * (期日(T+2))<BR>
     * 期日(T+2)<BR>
     */
	public Date closeDate2;

    /**
     * (期日(T+3))<BR>
     * 期日(T+3)<BR>
     */
	public Date closeDate3;

    /**
     * (期日(T+4))<BR>
     * 期日(T+4)<BR>
     */
	public Date closeDate4;

    /**
     * (期日(T+5))<BR>
     * 期日(T+5)<BR>
     */
	public Date closeDate5;

    /**
     * (必要入金額(T+0))<BR>
     * 必要入金額(T+0)<BR>
     */
	public String requiredPayAmt0;

    /**
     * (必要入金額(T+1))<BR>
     * 必要入金額(T+1)<BR>
     */
	public String requiredPayAmt1;

    /**
     * (必要入金額(T+2))<BR>
     * 必要入金額(T+2)<BR>
     */
	public String requiredPayAmt2;

    /**
     * (必要入金額(T+3))<BR>
     * 必要入金額(T+3)<BR>
     */
	public String requiredPayAmt3;

    /**
     * (必要入金額(T+4))<BR>
     * 必要入金額(T+4)<BR>
     */
	public String requiredPayAmt4;

    /**
     * (必要入金額(T+5))<BR>
     * 必要入金額(T+5)<BR>
     */
	public String requiredPayAmt5;

    /**
     * (精算額(T+0))<BR>
     * 精算額(T+0)<BR>
     */
	public String adjustedAmt0;

    /**
     * (精算額(T+1))<BR>
     * 精算額(T+1)<BR>
     */
	public String adjustedAmt1;

    /**
     * (日計り拘束金(T+0))<BR>
     * 日計り拘束金(T+0)<BR>
     */
	public String dayTradeRestraint0;

    /**
     * (日計り拘束金(T+1))<BR>
     * 日計り拘束金(T+1)<BR>
     */
	public String dayTradeRestraint1;

    /**
     * (保証金からの振替額(T+0))<BR>
     * 保証金からの振替額(T+0)<BR>
     */
	public String transferFromMarginDeposit0;

    /**
     * (保証金からの振替額(T+1))<BR>
     * 保証金からの振替額(T+1)<BR>
     */
	public String transferFromMarginDeposit1;

    /**
     * (不足金発生情報)<BR>
     * コンストラクタ。<BR>
     */
	public WEB3PvInfoShortfallGenerationInfo()
	{

	}
}
@
