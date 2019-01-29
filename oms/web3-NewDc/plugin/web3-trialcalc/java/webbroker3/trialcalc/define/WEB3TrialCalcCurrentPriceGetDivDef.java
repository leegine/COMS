head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.48.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcCurrentPriceGetDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : Webbroker3-TrialCalc WEB3TrialCalcCurrentPriceGetDivDef.(WEB3TrialCalcCurrentPriceGetDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/
package webbroker3.trialcalc.define;

/**
 * Webbroker3-TrialCalc WEB3TrialCalcCurrentPriceGetDivDef.<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public interface WEB3TrialCalcCurrentPriceGetDivDef
{
    /**
     * 1：時価
     */
    public final static String CURRENT_PRICE = "1";

    /**
     * 2：前日終値
     */
    public final static String LAST_CLOSE_PRICE = "2";

    /**
     * 3：当日終値
     */
    public final static String TODAY_CLOSE_PRICE = "3";
}@
