head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPayAutoCalcDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 本部一括余力拘束区分定義(WEB3TPPayAutoCalcDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/13 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 本部一括余力拘束区分
 */

public interface WEB3TPPayAutoCalcDivDef 
{
	
	/**
	 * "0"：Ｔ+0の余力非拘束
	 */
	public static String NOT_INCLUDE_T0 = "0";
	
	/**
	 * "1"：Ｔ+0の余力拘束
	 */
	public static String INCLUDE_T0 = "1";
	
}
@
