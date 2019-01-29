head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallOccurInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 不足金発生情報(WEB3TPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 仕様変更モデル307,315,317
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (不足金発生情報)<BR>
 * (不足金発生情報)<BR>
 * <BR>
 * 不足金発生情報を格納するクラス<BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPShortfallOccurInfo
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPShortfallOccurInfo.class);

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * <BR>
     * false：保証金自動振替前<BR>
     * true ：保証金自動振替後<BR>
     */
    public boolean depositAutoTransferDivFlag = false;

    /**
     * (立替金)<BR>
     * (立替金)<BR>
     */
    public double debitAmount = 0;

    /**
     * (特別立替金)<BR>
     * (特別立替金)<BR>
     */
    public double specialDebitAmount = 0;

    /**
     * (期日(T+0))<BR>
     * (期日(T+0))<BR>
     */
    public Date closeDate0 = null;

    /**
     * (期日(T+1))<BR>
     * (期日(T+1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (期日(T+2))<BR>
     * (期日(T+2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (期日(T+3))<BR>
     * (期日(T+3))<BR>
     */
    public Date closeDate3 = null;

    /**
     * (期日(T+4))<BR>
     * (期日(T+4))<BR>
     */
    public Date closeDate4 = null;

    /**
     * (期日(T+5))<BR>
     * (期日(T+5))<BR>
     */
    public Date closeDate5 = null;

    /**
     * (必要入金額(T+0))<BR>
     * (必要入金額(T+0))<BR>
     */
    public double requiredPayAmt0 = 0;

    /**
     * (必要入金額(T+1))<BR>
     * (必要入金額(T+1))<BR>
     */
    public double requiredPayAmt1 = 0;

    /**
     * (必要入金額(T+2))<BR>
     * (必要入金額(T+2))<BR>
     */
    public double requiredPayAmt2 = 0;

    /**
     * (必要入金額(T+3))<BR>
     * (必要入金額(T+3))<BR>
     */
    public double requiredPayAmt3 = 0;

    /**
     * (必要入金額(T+4))<BR>
     * (必要入金額(T+4))<BR>
     */
    public double requiredPayAmt4 = 0;

    /**
     * (必要入金額(T+5))<BR>
     * (必要入金額(T+5))<BR>
     */
    public double requiredPayAmt5 = 0;

    /**
     * (精算額(T+0))<BR>
     * (精算額(T+0))<BR>
     */
    public double adjustedAmt0 = 0;

    /**
     * (精算額(T+1))<BR>
     * (精算額(T+1))<BR>
     */
    public double adjustedAmt1 = 0;

    /**
     * (日計り拘束金(T+0))<BR>
     * (日計り拘束金(T+0))<BR>
     */
    public double dayTradeRestraint0 = 0;

    /**
     * (日計り拘束金(T+1))<BR>
     * (日計り拘束金(T+1))<BR>
     */
    public double dayTradeRestraint1 = 0;

    /**
     * (保証金からの振替額(T+0))<BR>
     * (保証金からの振替額(T+0))<BR>
     */
    public double transferFromMarginDeposit0 = 0;

    /**
     * (保証金からの振替額(T+1))<BR>
     * (保証金からの振替額(T+1))<BR>
     */
    public double transferFromMarginDeposit1 = 0;

    /**
     * @@roseuid 48F585630389
     */
    public WEB3TPShortfallOccurInfo()
    {

    }

    /**
     * (create不足金発生情報)<BR>
     * (staticメソッド)(create不足金発生情報)<BR>
     * <BR>
     * 不足金発生情報インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(不足金発生情報)create不足金発生情報」参照<BR>
     * @@param l_paymentRequisitionManagement - (入金請求管理)<BR>
     * (入金請求管理)<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 48E17E6D03E7
     * @@throws WEB3BaseException
     */
    public static WEB3TPShortfallOccurInfo createShortfallGenerationInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createShortfallGenerationInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPShortfallGenerationInfo." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //不足金発生情報を生成する。
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo = new WEB3TPShortfallOccurInfo();

        //保証金自動振替後判定フラグ = 引数.入金請求管理.is保証金自動振替後判定フラグ()
        l_shortfallGenerationInfo.depositAutoTransferDivFlag =
            l_paymentRequisitionManagement.isDepositAutoTransferDivFlag();
        //立替金 = get立替金()
        l_shortfallGenerationInfo.debitAmount =
            l_paymentRequisitionManagement.getDebitAmount();
        //特別立替金 = get特別立替金()
        l_shortfallGenerationInfo.specialDebitAmount =
            l_paymentRequisitionManagement.getSpecialDebitAmount();
        //指定日(T+0) = get指定日(T+0)
        l_shortfallGenerationInfo.closeDate0 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_0);
        //指定日(T+1) = get指定日(T+1)
        l_shortfallGenerationInfo.closeDate1 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_1);
        //指定日(T+2) = get指定日(T+2)
        l_shortfallGenerationInfo.closeDate2 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_2);
        //指定日(T+3) = get指定日(T+3)
        l_shortfallGenerationInfo.closeDate3 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_3);
        //指定日(T+4) = get指定日(T+4)
        l_shortfallGenerationInfo.closeDate4 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_4);
        //指定日(T+5) = get指定日(T+5)
        l_shortfallGenerationInfo.closeDate5 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_5);
        //必要入金額(T+0) = get預り金不足額(T+0)
        l_shortfallGenerationInfo.requiredPayAmt0 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_0);
        //必要入金額(T+1) = get預り金不足額(T+1)
        l_shortfallGenerationInfo.requiredPayAmt1 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_1);
        //必要入金額(T+2) = get預り金不足額(T+2)
        l_shortfallGenerationInfo.requiredPayAmt2 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_2);
        //必要入金額(T+3) = get預り金不足額(T+3)
        l_shortfallGenerationInfo.requiredPayAmt3 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_3);
        //必要入金額(T+4) = get預り金不足額(T+4)
        l_shortfallGenerationInfo.requiredPayAmt4 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_4);
        //必要入金額(T+5) = get預り金不足額(T+5)
        l_shortfallGenerationInfo.requiredPayAmt5 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_5);
        //精算額(T+0) = calc精算額(T+0)
        l_shortfallGenerationInfo.adjustedAmt0 =
            l_paymentRequisitionManagement.calcAdjustedAmt(WEB3TPSpecifiedPointDef.T_0);
        //精算額(T+1) = calc精算額(T+1)
        l_shortfallGenerationInfo.adjustedAmt1 =
            l_paymentRequisitionManagement.calcAdjustedAmt(WEB3TPSpecifiedPointDef.T_1);
        //日計り拘束金(T+0) = calc日計り拘束金(T+0)
        l_shortfallGenerationInfo.dayTradeRestraint0 =
            l_paymentRequisitionManagement.calcDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
        //日計り拘束金(T+1) = calc日計り拘束金(T+1)
        l_shortfallGenerationInfo.dayTradeRestraint1 =
            l_paymentRequisitionManagement.calcDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
        //保証金からの振替額(T+0) = get保証金からの振替額(T+0)
        l_shortfallGenerationInfo.transferFromMarginDeposit0 =
            l_paymentRequisitionManagement.getTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        //保証金からの振替額(T+1) = get保証金からの振替額(T+1)
        l_shortfallGenerationInfo.transferFromMarginDeposit1 =
            l_paymentRequisitionManagement.getTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }
}
@
