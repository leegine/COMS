head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.05.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SpecifyDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 指定定数定義インタフェイス(WEB3SpecifyDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/22 李海波(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 指定 定数定義インタフェイス
 *
 * @@author 李海波
 * @@version 1.0
 */
public interface WEB3SpecifyDivDef
{

    /**
     * 1：口数　@  　@　@
     */
    public final static String COUNT = "1";

    /**
     * 2：金額
     */
    public final static String MONEY = "2";

    /**
     * 3：全部
     */
    public final static String ALL = "3";
}
@
