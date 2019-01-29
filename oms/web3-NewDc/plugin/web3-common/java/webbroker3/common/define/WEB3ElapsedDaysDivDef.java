head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.45.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ElapsedDaysDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 経過日数区分定数定義インタフェイス(WEB3ElapsedDaysDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/07 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 経過日数区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3ElapsedDaysDivDef
{
    /**
     * 1:暦日ベース片端
     */
    public final static String CALENDAR_BASE_END = "1";

    /**
     * 2:暦日ベース両端
     */
    public final static String CALENDAR_BASE_BOTH = "2";

    /**
     * 3:一ヶ月30日片端
     */
    public final static String A_MONTH_30_END = "3";

    /**
     * 4:一ヶ月30日両端MAX30日
     */
    public final static String A_MONTH_30_BOTH_MAX_30 = "4";

    /**
     * 5:一ヶ月30日片端月末30日
     */
    public final static String A_MONTH_30_END_END_30 = "5";

    /**
     * 6:一ヶ月30日片端MAX30日
     */
    public final static String A_MONTH_30_END_MAX_30 = "6";
}
@
