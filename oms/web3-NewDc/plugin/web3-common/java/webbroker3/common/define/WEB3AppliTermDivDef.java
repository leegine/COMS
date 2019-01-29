head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AppliTermDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 適用期間区分(WEB3AppliTermDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 張宝楠 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 適用期間区分 定数定義インタフェイス
 *
 * @@author 張宝楠
 * @@version 1.0
 */
public interface WEB3AppliTermDivDef
{

    /**
     * 0：DEFAULT（期間制限なし）
     */
    public final static String DEFAULT  = "0";

    /**
     * 1：年数指定
     */
    public final static String YEARS_DESIGNATED = "1";

    /**
     * 2：月数指定
     */
    public final static String MONTHS_DESIGNATED = "2";

    /**
     * 3：日数指定
     */
    public final static String DAYS_DESIGNATED = "3";

}@
