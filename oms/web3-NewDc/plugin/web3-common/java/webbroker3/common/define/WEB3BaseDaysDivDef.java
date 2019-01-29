head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3BaseDaysDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 基準日数区分定数定義インタフェイス(WEB3BaseDaysDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 基準日数区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3BaseDaysDivDef
{
    /**
     * 1:基準日数360日
     */
    public final static String BASE_DAYS_360 = "1";

    /**
     * 2:基準日数365日
     */
    public final static String BASE_DAYS_365 = "2";

    /**
     * 3:閏年考慮
     */
    public final static String LEAP_YEAR_CONSIDERATION = "3";

    /**
     * 4:利払期間考慮
     */
    public final static String PAYMENT_PERIOD_CONSIDERATION = "4";

    /**
     * 5:利払回数考慮
     */
    public final static String PAYMENT_FREQUENCY_CONSIDERATION = "5";

    /**
     * 6:基準日閏年
     */
    public final static String BASE_DATE_LEAP_YEAR = "6";
}
@
