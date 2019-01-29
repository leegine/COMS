head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliStartDateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 変更適用開始日指定区分(WEB3AppliStartDateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 変更適用開始日指定区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AppliStartDateDivDef
{

    /**
     * 1：申込日の翌月（月初営業日）
     */
    public final static String NEXT_MONTH_OF_APPLI_DAY  = "1";

    /**
     * 2：申込日の翌々月（月初営業日）
     */
    public final static String TWO_MONTHS_AFTER_OF_APPLI_DAY = "2";

    /**
     * 9：日数指定
     */
    public final static String DAYS_DESIGNATED = "9";

}@
