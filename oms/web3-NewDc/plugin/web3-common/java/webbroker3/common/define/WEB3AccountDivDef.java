head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.00.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AccountDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 jia-yuanchun(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 口座区分　@定数定義インタフェイス
 *                                                                      
 * @@author jia-yuanchun
 * @@version 1.0
 */
public interface WEB3AccountDivDef
{
    /**
     * 0:一般
     */
    public static final String NORMAL = "0";

    /**
     * 1:特定、対象取引以外はブランク
     */
    public static final String SPECIAL = "1";

}
@
