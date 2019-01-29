head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPriceRangeValueDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制値幅（値幅区分）定数定義インタフェース(WEB3AdminPriceRangeValueDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/08 景山　@忠嗣(SRA) 新規作成
*/
package webbroker3.eqtypeadmin.define;

/**
 * 強制値幅(値幅区分)の定数を定義する。
 *
 * @@author 景山　@忠嗣(SRA)
 * @@version 1.0
 */
public interface WEB3AdminPriceRangeValueDef
{
	/**
	 * 円
	 */
	public final static String YEN = "円";

	/**
	 * ％
	 */
	public final static String PERCENT = "％";

	/**
	 * 1/XX
	 */
	public final static String FRACTION = "1/";
}@
