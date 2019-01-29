head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 計算サービス損益計算レスポンス(WEB3TrialCalcProfitLossCalcResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * （計算サービス損益計算レスポンス）<BR>
 * <BR>
 * 計算サービス損益計算サービス（損益計算実行）のレスポンスデータ。<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalcResponse
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalcResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_profitloss_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101150L;

    /**
     * （買手数料）<BR>
     * <BR>
     * buyCommission<BR>
     */
    public String buyCommission;

    /**
     * （買手数料消費税）<BR>
     * <BR>
     * buyCommissionTax<BR>
     */
    public String buyCommissionTax;

    /**
     * （売手数料）<BR>
     * <BR>
     * sellCommission<BR>
     */
    public String sellCommission;

    /**
     * （売手数料消費税）<BR>
     * <BR>
     * sellCommissionTax<BR>
     */
    public String sellCommissionTax;

    /**
     * （手数料コース）<BR>
     * <BR>
     * 手数料コース。<BR>
     * （02：定額手数料(スタンダード)　@03：約定代金合計　@04：約定回数<BR>
     * 　@05：一日定額制　@12：定額手数料(ハイパーボックス)）<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * commissionCourse<BR>
     * (02 : Fixed commission(standard)  03 : Contract price total<BR>
     *  04 : Contract frequency             05 : One day fixed charge system<BR>
     *  12 : Fixed commission(hyper box))<BR>
     */
    public String commissionCourse;

    /**
     * （損益）<BR>
     * <BR>
     * prolossAmount<BR>
     */
    public String prolossAmount;

    /**
     * @@roseuid 41C817AE0010
     */
    public WEB3TrialCalcProfitLossCalcResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3TrialCalcProfitLossCalcRequest
     */
    public WEB3TrialCalcProfitLossCalcResponse(WEB3TrialCalcProfitLossCalcRequest l_request)
    {
        super(l_request);
    }
}
@
