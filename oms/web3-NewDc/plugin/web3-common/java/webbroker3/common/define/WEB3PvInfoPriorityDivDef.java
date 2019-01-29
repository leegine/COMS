head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.34.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PvInfoPriorityDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 優先区分(WEB3PvInfoPriorityDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李丁銀(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 優先区分
 * @@author 李丁銀
 * @@version 1.0
 */
public interface WEB3PvInfoPriorityDivDef
{

    /**
     * 0:ダイレクト指定　@　@　@　@　@  　@　@
     */
    public final static String DIRECT_ASSIGN = "0";

    /**
     * 1:最優先　@
     */
    public final static String MAX_PRIORITY = "1";

    /**
     * 2:優先　@
     */
    public final static String MID_PRIORITY = "2";

    /**
     * 3:通常　@　@
     */
    public final static String NORMAL_PRIORITY = "3";

}
@
