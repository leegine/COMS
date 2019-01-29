head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecondAdddepositInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第二水準追証情報(WEB3TPSecondAdddepositInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 仕様変更モデル307,315
Revision History : 2008/11/11 三島淳一郎 (SCS) 仕様変更モデル365
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (第二水準追証情報)<BR>
 * (第二水準追証情報)<BR>
 * <BR>
 * 第二水準追証情報を格納するクラス<BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPSecondAdddepositInfo implements WEB3TPAdddepositInfo
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSecondAdddepositInfo.class);

    /**
     * (期日(請求2))<BR>
     * (期日(請求2))<BR>
     */
    public Date secondCloseDate2 = null;

    /**
     * (期日(請求1))<BR>
     * (期日(請求1))<BR>
     */
    public Date secondCloseDate1 = null;

    /**
     * (期日(請求見込))<BR>
     * (期日(請求見込))<BR>
     */
    public Date secondCloseDateExpect = null;

    /**
     * (発生日(請求2))<BR>
     * (発生日(請求2))<BR>
     */
    public Date secondDepositOccurredDate2 = null;

    /**
     * (発生日(請求1))<BR>
     * (発生日(請求1))<BR>
     */
    public Date secondDepositOccurredDate1 = null;

    /**
     * (発生日(請求見込))<BR>
     * (発生日(請求見込))<BR>
     */
    public Date secondDepositOccurredDateExpect = null;

    /**
     * (保証金維持率)<BR>
     * (保証金維持率)<BR>
     */
    public double secondDepositRate = 0;

    /**
     * (保証金戻し維持率)<BR>
     * (保証金戻し維持率)<BR>
     */
    public double secondDepositBackRate = 0;

    /**
     * (保証金率(請求2))<BR>
     * (保証金率(請求2))<BR>
     */
    public double secondMarginDepositRate2 = 0;

    /**
     * (保証金率(請求1))<BR>
     * (保証金率(請求1))<BR>
     */
    public double secondMarginDepositRate1 = 0;

    /**
     * (保証金率(請求見込))<BR>
     * (保証金率(請求見込))<BR>
     */
    public double secondMarginDepositRateExpect = 0;

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
     * (追証決済必要額(未入金))<BR>
     * (追証決済必要額(未入金))<BR>
     */
    public double secondSettlementNonPay = 0;

    /**
     * (追証決済必要額(請求2))<BR>
     * (追証決済必要額(請求2))<BR>
     */
    public double secondSettlement2 = 0;

    /**
     * (追証決済必要額(請求1))<BR>
     * (追証決済必要額(請求1))<BR>
     */
    public double secondSettlement1 = 0;

    /**
     * (保証金増減)<BR>
     * (保証金増減)<BR>
     */
    public double secondMarginDepositInDe = 0;

    /**
     * (保証金増減(見込金額))<BR>
     * (保証金増減(見込金額))<BR>
     */
    public double secondMarginDepositInDeExpect = 0;

    /**
     * (決済済建玉)<BR>
     * (決済済建玉)<BR>
     */
    public double secondSettledContract = 0;

    /**
     * (未解消金額(未入金))<BR>
     * (未解消金額(未入金))<BR>
     */
    public double secondUncancelAmtNonPay = 0;

    /**
     * (未解消金額(請求2))<BR>
     * (未解消金額(請求2))<BR>
     */
    public double secondUncancelAmt2 = 0;

    /**
     * (未解消金額(請求1))<BR>
     * (未解消金額(請求1))<BR>
     */
    public double secondUncancelAmt1 = 0;

    /**
     * (未解消金額(請求見込))<BR>
     * (未解消金額(請求見込))<BR>
     */
    public double secondUncancelAmtExpect = 0;

    /**
     * (未解消決済必要額(未入金))<BR>
     * (未解消決済必要額(未入金))<BR>
     */
    public double secondUncancelSettleRequiredAmtNonPay = 0;

    /**
     * (未解消決済必要額(請求2))<BR>
     * (未解消決済必要額(請求2))<BR>
     */
    public double secondUncancelSettleRequiredAmt2 = 0;

    /**
     * (未解消決済必要額(請求1))<BR>
     * (未解消決済必要額(請求1))<BR>
     */
    public double secondUncancelSettleRequiredAmt1 = 0;

    /**
     * (未解消決済必要額(請求見込))<BR>
     * (未解消決済必要額(請求見込))<BR>
     */
    public double secondUncancelSettleRequiredAmtExpect = 0;

    /**
     * @@roseuid 48F585630147
     */
    public WEB3TPSecondAdddepositInfo()
    {

    }

    /**
     * (create第二水準追証情報)<BR>
     * (staticメソッド)(create第二水準追証情報)<BR>
     * <BR>
     * 第二水準追証情報インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(第二水準追証情報)create第二水準追証情報」参照<BR>
     * ======================================================== <BR>
     * シーケンス図 ：((第二水準追証情報)create第二水準追証情報) <BR>
     * 具体位置：((*)分岐フローget信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_paymentRequisitionManagement - (入金請求管理)<BR>
     * (入金請求管理)<BR>
     * @@return WEB3TPSecondAdddepositInfo
     * @@roseuid 48E183CF015A
     * @@throws WEB3BaseException
     */
    public static WEB3TPSecondAdddepositInfo createSecondAdddepositInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSecondAdddepositInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPSecondAdddepositInfo." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //get信用現物判定フラグ
        String l_strMarginEquityFlag = l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

        //第二水準追証情報を生成する。
        WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = null;

        //(*)分岐フロ－
        //get信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_secondAdddepositInfo;
        }

        //is第二水準追証発生
        boolean l_blnSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

        //(*)分岐フロ－
        //is第二水準追証発生( )の戻り値　@==　@TRUE の場合
        if (l_blnSecondAdddeposit)
        {
            l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
            //第二水準追証情報のプロパティを以下のようにセットする。
            //　@・期日(請求2)　@=　@get第二水準追証期日(請求2)()
            l_secondAdddepositInfo.secondCloseDate2 =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDate2();
            //　@・期日(請求1)　@=　@get第二水準追証期日(請求1)()
            l_secondAdddepositInfo.secondCloseDate1 =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDate1();
            //　@・発生日(請求2)　@=　@get第二水準追証発生日(請求2)()
            l_secondAdddepositInfo.secondDepositOccurredDate2 =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDate2();
            //　@・発生日(請求1)　@=　@get第二水準追証発生日(請求1)()
            l_secondAdddepositInfo.secondDepositOccurredDate1 =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDate1();
            //　@・保証金維持率　@=　@get第二水準追証保証金維持率()
            l_secondAdddepositInfo.secondDepositRate =
                l_paymentRequisitionManagement.getSecondAdddepositDepositRate();
            //　@・保証金戻し維持率　@=　@get第二水準追証保証金戻し維持率()
            l_secondAdddepositInfo.secondDepositBackRate =
                l_paymentRequisitionManagement.getSecondAdddepositDepositBackRate();
            //　@・保証金率(請求2)　@=　@get第二水準追証保証金率(請求2)()
            l_secondAdddepositInfo.secondMarginDepositRate2 =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRate2();
            //　@・保証金率(請求1)　@=　@get第二水準追証保証金率(請求1)()
            l_secondAdddepositInfo.secondMarginDepositRate1 =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRate1();
            //　@・金額(未入金)　@=　@get第二水準追証金額(未入金)()
            l_secondAdddepositInfo.secondDepositNonPay =
                l_paymentRequisitionManagement.getSecondAdddepositDepositNonPay();
            //　@・金額(請求2)　@=　@get第二水準追証金額(請求2)()
            l_secondAdddepositInfo.secondDeposit2 =
                l_paymentRequisitionManagement.getSecondAdddepositDeposit2();
            //　@・金額(請求1)　@=　@get第二水準追証金額(請求1)()
            l_secondAdddepositInfo.secondDeposit1 =
                l_paymentRequisitionManagement.getSecondAdddepositDeposit1();
            //　@・決済必要額(未入金)　@=　@get第二水準追証決済必要額(未入金)()
            l_secondAdddepositInfo.secondSettlementNonPay =
                l_paymentRequisitionManagement.getSecondAdddepositSettlementNonPay();
            //　@・決済必要額(請求2)　@=　@get第二水準追証決済必要額(請求2)()
            l_secondAdddepositInfo.secondSettlement2 =
                l_paymentRequisitionManagement.getSecondAdddepositSettlement2();
            //　@・決済必要額(請求1)　@=　@get第二水準追証決済必要額(請求1)()
            l_secondAdddepositInfo.secondSettlement1 =
                l_paymentRequisitionManagement.getSecondAdddepositSettlement1();
            //　@・保証金増減　@=　@get第二水準追証保証金増減()
            l_secondAdddepositInfo.secondMarginDepositInDe =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositInDe();
            //　@・決済済建玉　@=　@get第二水準追証決済済建玉()
            l_secondAdddepositInfo.secondSettledContract =
                l_paymentRequisitionManagement.getSecondAdddepositSettledContract();

            //第二水準追証未解消情報オブジェクトを生成する。
            WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();

            //　@・未解消金額(未入金)　@=　@第二水準追証未解消情報.追証金額(未入金)
            l_secondAdddepositInfo.secondUncancelAmtNonPay =
                l_secondAdddepositNotClearInfo.secondDepositNonPay;
            //　@・未解消金額(請求2)　@=　@第二水準追証未解消情報.追証金額(請求2)
            l_secondAdddepositInfo.secondUncancelAmt2 =
                l_secondAdddepositNotClearInfo.secondDeposit2;
            //　@・未解消金額(請求1)　@=　@第二水準追証未解消情報.追証金額(請求1)
            l_secondAdddepositInfo.secondUncancelAmt1 =
                l_secondAdddepositNotClearInfo.secondDeposit1;
            //　@・未解消決済必要額(未入金)　@=　@第二水準追証未解消情報.決済必要額(未入金)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay =
                l_secondAdddepositNotClearInfo.secondSettlementNonPay;
            //　@・未解消決済必要額(請求2)　@=　@第二水準追証未解消情報.決済必要額(請求2)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 =
                l_secondAdddepositNotClearInfo.secondSettlement2;
            //　@・未解消決済必要額(請求1)　@=　@第二水準追証未解消情報.決済必要額(請求1)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 =
                l_secondAdddepositNotClearInfo.secondSettlement1;
            //　@・期日(請求見込)　@=　@get第二水準追証期日(見込金額)()
            l_secondAdddepositInfo.secondCloseDateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDateExpect();
            //　@・発生日(請求見込)　@=　@get第二水準追証発生日(見込金額)()
            l_secondAdddepositInfo.secondDepositOccurredDateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDateExpect();
            //　@・保証金率(請求見込)　@=　@get第二水準追証保証金率(見込金額)()
            l_secondAdddepositInfo.secondMarginDepositRateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRateExpect();
            //　@・保証金増減(見込金額)　@=　@get第二水準追証保証金増減(見込金額)()
            l_secondAdddepositInfo.secondMarginDepositInDeExpect =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositInDeExpect();
            //　@・未解消金額(請求見込)　@=　@第二水準追証未解消情報.追証金額(見込金額)
            l_secondAdddepositInfo.secondUncancelAmtExpect =
                l_secondAdddepositNotClearInfo.secondDepositExpect;
            //　@・未解消決済必要額(請求見込)　@=　@第二水準追証未解消情報.決済必要額(見込金額)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect =
                l_secondAdddepositNotClearInfo.secondSettlementExpect;
        }

        log.exiting(STR_METHOD_NAME);
        return l_secondAdddepositInfo;
    }
}
@
