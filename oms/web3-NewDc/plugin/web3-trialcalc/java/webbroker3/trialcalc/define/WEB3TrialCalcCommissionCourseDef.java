head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcCommissionCourseDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : Webbroker3-TrialCalc WEB3TrialCalcCommissionCourseDef(WEB3CommissionCourseDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/
package webbroker3.trialcalc.define;

/**
 * Webbroker3-TrialCalc WEB3TrialCalcCommissionCourseDef.<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3TrialCalcCommissionCourseDef
{
    /**
     * 02：定率手数料（スタンダード）
     */
    public final static String FIXED_RATE_COMMISSION_STANDARD = "02";

    /**
     * 03：約定代金合計
     */
    public final static String EXECUTED_TURNOVER_COUNT = "03";

    /**
     * 04：約定回数
     */
    public final static String EXECUTED_TIMES = "04";

    /**
     * 05：一日定額制
     */
    public final static String FIXED_AMOUNT = "05";

    /**
     * 12：定率手数料（ハイパーボックス）※徴収率
     */
    public final static String FIXED_RATE_COMMISSION_HYPERBOX = "12";

}@
