head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFeqExecFileSendStatusDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定データファ@イル送信フラグ（区分）定義(WEB3TPFeqExecFileSendStatusDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/28 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 約定データファ@イル送信区分
 */
public interface WEB3TPFeqExecFileSendStatusDivDef 
{
	
	/**
	 * SONAR送信済
	 */
	public static String SENT = "1";	

	/**
	 * 
	 * */
	public static String PROCESSED = "2";	
	
}
@
