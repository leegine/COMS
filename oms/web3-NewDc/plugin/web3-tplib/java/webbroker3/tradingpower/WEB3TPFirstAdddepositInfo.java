head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFirstAdddepositInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 第一水準追証情報(WEB3TPFirstAdddepositInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 孟亞南 (中訊) 新規作成 仕様変更モデル307,315
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
 * (第一水準追証情報)<BR>
 * (第一水準追証情報)<BR>
 * <BR>
 * 第一水準追証情報を格納するクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3TPFirstAdddepositInfo implements WEB3TPAdddepositInfo
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPFirstAdddepositInfo.class);

    /**
     * (経過日数)<BR>
     * (経過日数)<BR>
     */
    public int firstDepositPassDay = 0;

    /**
     * (有効経過日数)<BR>
     * (有効経過日数)<BR>
     */
    public int firstDepositPassDayValid = 0;

    /**
     * (発生日)<BR>
     * (発生日)<BR>
     */
    public Date firstDepositOccurredDate = null;

    /**
     * (保証金率)<BR>
     * (保証金率)<BR>
     */
    public double firstMarginDepositRate = 0;

    /**
     * (保証金維持率)<BR>
     * (保証金維持率)<BR>
     */
    public double firstDepositRate = 0;

    /**
     * (追証金額)<BR>
     * (追証金額)<BR>
     */
    public double firstDepositAmount = 0;

    /**
     * (追証決済必要額)<BR>
     * (追証決済必要額)<BR>
     */
    public double firstSettlement = 0;

    /**
     * (保証金増減)<BR>
     * (保証金増減)<BR>
     */
    public double firstMarginDepositInDe = 0;

    /**
     * (保証金増減(見込金額))<BR>
     * (保証金増減(見込金額))<BR>
     */
    public double firstMarginDepositInDeExpect = 0;

    /**
     * (決済済建玉)<BR>
     * (決済済建玉)<BR>
     */
    public double firstSettledContract = 0;

    /**
     * (未解消金額)<BR>
     * (未解消金額)<BR>
     */
    public double firstUncancelAmt = 0;

    /**
     * (未解消決済必要額)<BR>
     * (未解消決済必要額)<BR>
     */
    public double firstUncancelSettleRequiredAmt = 0;

    /**
     * @@roseuid 48F585630261
     */
    public WEB3TPFirstAdddepositInfo()
    {

    }

    /**
     * (create第一水準追証情報)<BR>
     * (staticメソッド)(create第一水準追証情報)<BR>
     * <BR>
     * 第一水準追証情報インスタンスを作成する <BR>
     * <BR>
     * ※シーケンス図「(第一水準追証情報)create第一水準追証情報」参照<BR>
     * ======================================================== <BR>
     * シーケンス図 ：((第一水準追証情報)create第一水準追証情報) <BR>
     * 具体位置：((*)分岐フローget信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_paymentRequisitionManagement - (入金請求管理)<BR>
     * (入金請求管理)<BR>
     * @@return WEB3TPFirstAdddepositInfo
     * @@roseuid 48E183900232
     * @@throws WEB3BaseException
     */
    public static WEB3TPFirstAdddepositInfo createFirstAdddepositInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFirstAdddepositInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPFirstAdddepositInfo." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //get信用現物判定フラグ
        String l_strMarginEquityFlag = l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

        //第一水準追証情報を生成する。
        WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = null;

        //(*)分岐フロ－
        //get信用現物判定フラグ()の戻り値　@==　@"0"(現物顧客) の場合
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_firstAdddepositInfo;
        }

        //is第一水準追証発生
        boolean l_blnIsFirstAdddeposit = l_paymentRequisitionManagement.isFirstAdddeposit();

        //(*)分岐フロ－
        //is第一水準追証発生( )の戻り値　@==　@TRUE の場合
        if (l_blnIsFirstAdddeposit)
        {
            l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            //第一水準追証情報のプロパティを以下のようにセットする。
            //・経過日数　@=　@get第一水準追証経過日数()
            l_firstAdddepositInfo.firstDepositPassDay =
                l_paymentRequisitionManagement.getFirstAdddepositPassDay();
            //・有効経過日数　@=　@get第一水準追証有効経過日数()
            l_firstAdddepositInfo.firstDepositPassDayValid =
                l_paymentRequisitionManagement.getFirstAdddepositPassDayValid();
            //・発生日　@=　@get第一水準追証発生日()
            l_firstAdddepositInfo.firstDepositOccurredDate =
                l_paymentRequisitionManagement.getFirstAdddepositOccurredDate();
            //・保証金率　@=　@get第一水準追証保証金率()
            l_firstAdddepositInfo.firstMarginDepositRate =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositRate();
            //・保証金維持率　@=　@get第一水準追証保証金維持率()
            l_firstAdddepositInfo.firstDepositRate =
                l_paymentRequisitionManagement.getFirstAdddepositDepositRate();
            //・追証金額　@=　@get第一水準追証金額()
            l_firstAdddepositInfo.firstDepositAmount =
                l_paymentRequisitionManagement.getFirstAdddepositAmount();
            //・追証決済必要額　@=　@get第一水準追証決済必要額()
            l_firstAdddepositInfo.firstSettlement =
                l_paymentRequisitionManagement.getFirstAdddepositSettlement();
            //・保証金増減　@=　@get第一水準追証保証金増減()
            l_firstAdddepositInfo.firstMarginDepositInDe =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositInDe();
            //・保証金増減(見込金額)　@=　@get第一水準追証保証金増減(見込金額)()
            l_firstAdddepositInfo.firstMarginDepositInDeExpect =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositInDeExpect();
            //・決済済建玉　@=　@get第一水準追証決済済建玉()
            l_firstAdddepositInfo.firstSettledContract =
                l_paymentRequisitionManagement.getFirstAdddepositSettledContract();
            //・未解消金額　@=　@get第一水準追証未解消金額()
            l_firstAdddepositInfo.firstUncancelAmt =
                l_paymentRequisitionManagement.getFirstAdddepositUncancelAmt();
            //・未解消決済必要額　@=　@get第一水準追証未解消決済必要額()
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt =
                l_paymentRequisitionManagement.getFirstAdddepositUncancelSettleRequiredAmt();
        }

        log.exiting(STR_METHOD_NAME);
        return l_firstAdddepositInfo;
    }
}
@
