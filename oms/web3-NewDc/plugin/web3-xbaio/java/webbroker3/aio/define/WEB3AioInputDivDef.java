head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.49.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioInputDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/31 徐大方 (中訊) 新規作成
*/

package webbroker3.aio.define;

/**
 * 入出金入力區分  定数定義インタフェイス
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public interface WEB3AioInputDivDef
{
	/**
	 * 0:全て
	 */
	public static final String ALL = "0";
	
	/**
	 * 1:顧客
	 */
	public static final String CUSTOMER = "1";
	
	/**
	 * 2:SONAR
	 */
	public static final String SONAR = "2";
}
@
