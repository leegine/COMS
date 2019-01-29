head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.31.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClosingDateCalDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ClosingDateCalDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 決算日算出区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3ClosingDateCalDivDef
{
    /**
     * 0:なし
     */
    public static final String NO_SLIDE = "0";

    /**
     * 1:休日ｽﾗｲﾄﾞ
     */
    public static final String HOLIDAY_SLIDE = "1";

    /**
     * 2:2営業日連続までｽﾗｲﾄﾞ（ｱﾀｯｸ型）
     */
    public static final String TWO_BIZ_DATE_SLIDE = "2";
}
@
