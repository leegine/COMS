head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionAccountDetailInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客詳細情報(WEB3TPPaymentRequisitionAccountDetailInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 新規作成 仕様変更モデル307,315,317,340
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (入金請求顧客詳細情報)<BR>
 * (入金請求顧客詳細情報)<BR>
 * <BR>
 * 入金請求の顧客詳細に関する情報を格納するクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionAccountDetailInfo
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionAccountDetailInfo.class);

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * <BR>
     * TRUE：　@保証金自動振替後<BR>
     * FALSE：　@保証金自動振替前<BR>
     */
    protected boolean depositAutoTransferDivFlag = false;

    /**
     * (顧客属性)<BR>
     * (顧客属性)<BR>
     * <BR>
     * 0：現物(前金制)<BR>
     * 1：現物(預り証券評価制)<BR>
     * 2：信用<BR>
     */
    protected String accountAttribute = null;

    /**
     * (計算日)<BR>
     * (計算日)<BR>
     */
    protected Date calcDate = null;

    /**
     * (不足金発生情報)<BR>
     * (不足金発生情報)<BR>
     */
    protected WEB3TPShortfallOccurInfo shortfallGenerationInfo = null;

    /**
     * (追証発生情報)<BR>
     * (追証発生情報)<BR>
     */
    protected WEB3TPAdddepositGenerationInfo adddepositGenerationInfo = null;

    /**
     * @@roseuid 48F5856302DE
     */
    public WEB3TPPaymentRequisitionAccountDetailInfo()
    {

    }

    /**
     * (is保証金自動振替後判定フラグ)<BR>
     * (is保証金自動振替後判定フラグ)<BR>
     * <BR>
     * this.保証金自動振替後判定フラグを返却する。<BR>
     * @@return boolean
     * @@roseuid 48DB61B5033C
     */
    public boolean isDepositAutoTransferDivFlag()
    {
        return this.depositAutoTransferDivFlag;
    }

    /**
     * (set保証金自動振替後判定フラグ)<BR>
     * (set保証金自動振替後判定フラグ)<BR>
     * <BR>
     * 引数.保証金自動振替後判定フラグをthis.保証金自動振替後判定フラグにセットする。<BR>
     * @@param l_blnIsDepositAutoTransferDivFlag - (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * @@roseuid 48DB61B5033D
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnIsDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnIsDepositAutoTransferDivFlag;
    }

    /**
     * (get顧客属性)<BR>
     * (get顧客属性)<BR>
     * <BR>
     * this.顧客属性を返却する。<BR>
     * @@return String
     * @@roseuid 48C6065000C7
     */
    public String getAccountAttribute()
    {
        return this.accountAttribute;
    }

    /**
     * (set顧客属性)<BR>
     * (set顧客属性)<BR>
     * <BR>
     * 引数.顧客属性をthis.顧客属性にセットする。<BR>
     * @@param l_strAccountAttribute - (顧客属性)<BR>
     * (顧客属性)<BR>
     * @@roseuid 48C60678025A
     */
    public void setAccountAttribute(String l_strAccountAttribute)
    {
        this.accountAttribute = l_strAccountAttribute;
    }

    /**
     * (get計算日)<BR>
     * (get計算日)<BR>
     * <BR>
     * this.計算日を返却する。<BR>
     * @@return Date
     * @@roseuid 48C606E20146
     */
    public Date getCalcDate()
    {
        return this.calcDate;
    }

    /**
     * (set計算日)<BR>
     * (set計算日)<BR>
     * <BR>
     * 引数.計算日をthis.計算日にセットする。<BR>
     * @@param l_datCalcDate - (計算日)<BR>
     * (計算日)<BR>
     * @@roseuid 48C606E20147
     */
    public void setCalcDate(Date l_datCalcDate)
    {
        this.calcDate = l_datCalcDate;
    }

    /**
     * (get不足金発生情報)<BR>
     * (get不足金発生情報)<BR>
     * <BR>
     * this.不足金発生情報を返却する。<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 48C606E303E6
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo()
    {
        return this.shortfallGenerationInfo;
    }

    /**
     * (set不足金発生情報)<BR>
     * (set不足金発生情報)<BR>
     * <BR>
     * 引数.不足金発生情報をthis.不足金発生情報にセットする。<BR>
     * @@param l_shortfallGenerationInfo - (不足金発生情報)<BR>
     * (不足金発生情報)<BR>
     * @@roseuid 48C606E303E7
     */
    public void setShortfallGenerationInfo(WEB3TPShortfallOccurInfo l_shortfallGenerationInfo)
    {
        this.shortfallGenerationInfo = l_shortfallGenerationInfo;
    }

    /**
     * (get追証発生情報)<BR>
     * (get追証発生情報)<BR>
     * <BR>
     * this.追証発生情報を返却する。<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 48C6091A0035
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo()
    {
        return this.adddepositGenerationInfo;
    }

    /**
     * (set追証発生情報)<BR>
     * (set追証発生情報)<BR>
     * <BR>
     * 引数.追証発生情報をthis.追証発生情報にセットする。<BR>
     * @@param l_adddepositGenerationInfo - (追証発生情報)<BR>
     * (追証発生情報)<BR>
     * @@roseuid 48C6091A0036
     */
    public void setAdddepositGenerationInfo(WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo)
    {
        this.adddepositGenerationInfo = l_adddepositGenerationInfo;
    }

    /**
     * (create入金請求顧客詳細情報)<BR>
     * @@param l_paymentRequisitionManagement - (入金請求管理)<BR>
     * (入金請求管理)<BR>
     * @@return WEB3TPPaymentRequisitionAccountDetailInfo
     * @@roseuid 48EC80F402EF
     * @@throws WEB3BaseException
     */
    public static WEB3TPPaymentRequisitionAccountDetailInfo createPaymentRequisitionAccountDetailInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createPaymentRequisitionAccountDetailInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPPaymentRequisitionAccountDetailInfo." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //入金請求顧客詳細情報を生成する。
        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            new WEB3TPPaymentRequisitionAccountDetailInfo();

        //(create不足金発生情報)
        //不足金発生情報を生成する。
        //[引数]
        //・入金請求管理：　@引数.入金請求管理
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            WEB3TPShortfallOccurInfo.createShortfallGenerationInfo(l_paymentRequisitionManagement);

        //保証金自動振替後判定フラグ = create不足金発生情報()の戻り値.保証金自動振替後判定フラグ
        l_paymentRequisitionAccountDetailInfo.depositAutoTransferDivFlag =
            l_shortfallGenerationInfo.depositAutoTransferDivFlag;
        //不足金発生情報 = create不足金発生情報()の戻り値
        l_paymentRequisitionAccountDetailInfo.shortfallGenerationInfo = l_shortfallGenerationInfo;
        //顧客属性 = get顧客属性()の戻り値
        l_paymentRequisitionAccountDetailInfo.accountAttribute =
            l_paymentRequisitionManagement.getAccountAttribute();
        //計算日 = get計算日()の戻り値
        l_paymentRequisitionAccountDetailInfo.calcDate = l_paymentRequisitionManagement.getCalcDate();
        //追証発生情報 = create追証発生情報()の戻り値
        l_paymentRequisitionAccountDetailInfo.adddepositGenerationInfo =
            WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionAccountDetailInfo;
    }
}
@
