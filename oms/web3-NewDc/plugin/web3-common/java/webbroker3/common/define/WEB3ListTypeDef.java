head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.43.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ListTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 上場区分 定数定義インタフェイス(WEB3ListTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 上場区分　@定数定義インタフェイス
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3ListTypeDef
{

    /**
     * 1 : 一部上場
     */
    public static final String FIRST_SECTION = "1";

    /**
     * 2 : 二部上場
     */
    public static final String SECOND_SECTION = "2";

    /**
     * 3 : 非上場
     */
    public static final String UNLISTING = "3";

    /**
     * 4 : 外国部上場
     */
    public static final String FOREIGN_SECITION = "4";

    /**
     * 5 : 店頭
     */
    public static final String OTC = "5";

    /**
     * 7 : 新二部上場
     */
    public static final String NEW_SECOND_SECTION = "7";
}
@
