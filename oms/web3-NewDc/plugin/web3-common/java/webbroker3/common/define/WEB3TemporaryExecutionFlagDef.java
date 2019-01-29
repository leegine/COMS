head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TemporaryExecutionFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 仮約定フラグ定数定義インタフェイス(WEB3FeqTemporaryExecutionFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 大澤(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 仮約定フラグ 定数定義インタフェイス
 *
 * @@author SRA大澤
 * @@version 1.0
 */
public interface WEB3TemporaryExecutionFlagDef 
{
	/**
	 * 0：DEFAULT（仮約定ではない）
	 */
	public static final String DEFAULT = "0";
	
	/**
	 * 1：仮約定
	 */
	public static final String TEMPORARY_EXEC = "1";
}
@
