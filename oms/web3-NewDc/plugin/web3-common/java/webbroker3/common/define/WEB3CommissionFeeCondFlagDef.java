head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionFeeCondFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料条件決定FLAG定数定義クラス(WEB3CommissionFeeCondFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/06 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 手数料条件決定FLAGの定数を定義する。
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3CommissionFeeCondFlagDef
{

    /**
     * 原注文の条件
     */
    public final static String FIRST_ORDER = "F";

    /**
     * 最終注文の条件
     */
    public final static String LAST_ORDER = "L";
}
@
