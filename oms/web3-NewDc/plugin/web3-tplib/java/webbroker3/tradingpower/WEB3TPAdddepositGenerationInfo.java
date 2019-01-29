head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdddepositGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証発生情報(WEB3TPAdddepositGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 新規作成 仕様変更モデル307,315,317
*/
package webbroker3.tradingpower;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (追証発生情報)<BR>
 * (追証発生情報)<BR>
 * <BR>
 * 追証発生情報を格納するクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPAdddepositGenerationInfo
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPAdddepositGenerationInfo.class);

    /**
     * (保証金自動振替後判定フラグ)<BR>
     * (保証金自動振替後判定フラグ)<BR>
     * <BR>
     * false：保証金自動振替前<BR>
     * true ：保証金自動振替後<BR>
     */
    protected boolean depositAutoTransferDivFlag = false;

    /**
     * (追証情報)<BR>
     * (追証情報)<BR>
     */
    protected WEB3TPAdddepositInfo adddepositInfo = null;

    /**
     * @@roseuid 48F5856301D4
     */
    public WEB3TPAdddepositGenerationInfo()
    {

    }

    /**
     * (get保証金自動振替後判定フラグ)<BR>
     * (get保証金自動振替後判定フラグ)<BR>
     * <BR>
     * this.保証金自動振替後判定フラグを返却する。<BR>
     * @@return boolean
     * @@roseuid 48C76BD801AC
     */
    public boolean getDepositAutoTransferDivFlag()
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
     * @@roseuid 48C76BD801BC
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnIsDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnIsDepositAutoTransferDivFlag;
    }

    /**
     * (get追証情報)<BR>
     * (get追証情報)<BR>
     * <BR>
     * this.追証情報を返却する。<BR>
     * @@return WEB3TPAdddepositInfo
     * @@roseuid 48C76D5F00DC
     */
    public WEB3TPAdddepositInfo getAdddepositInfo()
    {
        return this.adddepositInfo;
    }

    /**
     * (set追証情報)<BR>
     * (set追証情報)<BR>
     * <BR>
     * 引数.追証情報をthis.追証情報にセットする。<BR>
     * @@param l_adddepositInfo - (追証情報)<BR>
     * (追証情報)<BR>
     * @@roseuid 48C76D5F00FB
     */
    public void setAdddepositInfo(WEB3TPAdddepositInfo l_adddepositInfo)
    {
        this.adddepositInfo = l_adddepositInfo;
    }

    /**
     * (create追証発生情報)<BR>
     * (staticメソッド)(create追証発生情報)<BR>
     * <BR>
     * 追証発生情報インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(追証発生情報)create追証発生情報」参照<BR>
     * @@param l_paymentRequisitionManagement - (入金請求管理)<BR>
     * (入金請求管理)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 48EC5C3202E4
     * @@throws WEB3BaseException
     */
    public static WEB3TPAdddepositGenerationInfo createAdddepositGenerationInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdddepositGenerationInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPAdddepositGenerationInfo." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //追証発生情報を生成する。
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();

        //保証金自動振替後判定フラグを取得する。
        boolean l_blnDepositAutoTransferDivFlag =
            l_paymentRequisitionManagement.isDepositAutoTransferDivFlag();

        //set保証金自動振替後判定フラグ
        //取得した保証金自動振替後判定フラグをセットする。
        //[引数]
        //保証金自動振替後判定フラグ：　@is保証金自動振替後判定フラグ()の戻り値
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);

        //(create第二水準追証情報)
        //第二水準追証情報を取得する。
        //[引数]
        //・入金請求管理：　@引数.入金請求管理
        WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
            WEB3TPSecondAdddepositInfo.createSecondAdddepositInfo(l_paymentRequisitionManagement);

        //(set追証情報)
        //生成した第二水準追証情報をセットする。
        //[引数]
        //・追証情報：　@create第二水準追証情報()の戻り値
        l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);

        //create第二水準追証情報()の戻り値 == NULL
        if (l_secondAdddepositInfo == null)
        {
            //(create第一水準追証情報)
            //第一水準追証情報を取得する。
            //[引数]
            //・入金請求管理：　@引数.入金請求管理
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                WEB3TPFirstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);

            //(set追証情報)
            //生成した第一水準追証情報をセットする。
            //[引数]
            //・追証情報：　@create第一水準追証情報()の戻り値
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }
}
@
