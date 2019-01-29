head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.34.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3OptionProductTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オプション商品区分インタフェイス(WEB3OptionProductTypeDef)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * オプション商品区分
 *                                                                     
 * @@author 景山
 * @@version 1.0
 */
public interface WEB3OptionProductTypeDef
{
	/**
	 * P：プットオプション
	 */
	public static final String PUT_OPTIONS = "P";

	/**
	 * C：コールオプション
	 */
	public static final String CALL_OPTIONS = "C";

}
@
