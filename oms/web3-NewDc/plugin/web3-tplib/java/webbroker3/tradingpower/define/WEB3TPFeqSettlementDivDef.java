head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFeqSettlementDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name       : 外株決済区分定義(WEB3TPSettlementDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 外株決済区分
 */
public interface WEB3TPFeqSettlementDivDef 
{
	
	/**
	 * 0：円貨決済　@
	 */
	public static String JAPANESE_CURRENCY = "0";	

	/**
	 * 1：外貨決済
	 * */
	public static String FOREIGN_CURRENCY = "1";	
	
}
@
