head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDisplayTargetDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright             : (株)大和総研 証券ソリューションシステム第二部
File Name             : Webbroker3-TrialCalc WEB3TrialCalcDisplayTargetDef(WEB3TrialCalcDisplayTargetDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/27 戴義波(中訊) 作成
*/
package webbroker3.trialcalc.define;

/**
 * Webbroker3-TrialCalc WEB3TrialCalcDisplayTargetDef.<BR>
 * @@author Umadevi
 * @@version 1.0
 */
public interface WEB3TrialCalcDisplayTargetDef
{
    /**
     * 1：ポートフォリオ登録分のみ
     */
    public final static String ONLY_PORTFOLIO = "1";

    /**
     *2：特定分のみ
     */
    public final static String ONLY_SPECIAL_ACCOUNT = "2";

    /**
     *3：両方合せる
     */
    public final static String BOTH = "3";
}@
